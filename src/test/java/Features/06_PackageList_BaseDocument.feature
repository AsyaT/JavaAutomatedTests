
Feature: I select 'По заказу' in dialog on Package list activity

  Scenario: I select correct option
    Given I see offer to select action in dialog 'Выберете действие'
      | Без документа-основания |
      | По заказу               |
    When I select in action dialog item 'По заказу'
    Then I see Package list activity

  Scenario: I scan wrong order or not order
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I scan 'EAN13' barcode '4603502137574'
    Then I see offer scan order fragment with message 'Документ не найден или недоступен в этом участке учета!'
    When I scan 'Code-128' barcode '296023429174452073866327149433046492099'
    Then I see offer scan order fragment with message 'Нельзя использовать Заказ клиента в статусе Закрыт!'


  Scenario: I scan correct order
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I scan 'Code-128' barcode '233907567175534467144053627894934209161'
    Then offer to scan order is closed
    And I see information with order name 'Заказ клиента УМER-011860 от 28.09.2019 15:06:59'

  Scenario: I scan package list

  Scenario: Do scan error barcodes
    Then I see Package list activity
    When I scan 'EAN13' barcode '4603502137574'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'EAN13' barcode '2209983009489'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302530010082011190820171908252100001923000'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Этот продукт не содержится в документе-основании'

     Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order 'Заказ клиента УМER-011860 от 28.09.2019 15:06:59'
    Then I see string table for product 'Филе куриное' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |   644			| 691,846	| -47,846|        161 |     161 |      0 |
    When I press system button back
    Then screen with order table is closed
    
  Scenario: I scan success barcodes
    When I scan 'GS1_DATABAR_EXP' barcode '0104660017707260310300430010082011190820171908252100001923000'
    Then I see string in table with number '1'
    And nomenclature name is 'Филе куриное "Здоровая Ферма", охл.~0,80 кг*5/~4,0 кг/ (подложка, стрейч)Характеристика: Монетка'
    And weight is '4.3'
    And quantity is '1'

  Scenario: I see ordered items in table after scanning
    When I press on informaiton with order name
    Then I see table of products for order 'Заказ клиента УМER-011860 от 28.09.2019 15:06:59'
    Then I see string table for product 'Филе куриное' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |   644			| 696,146	| -52,146|        161 |     162 |      -1 |
    When I press system button back
    Then screen with order table is closed
    
    Scenario: remove selected string
    When I press string number '1'
    Then String number '1' is highlighted with yellow color
    When I press button 'Удалить строку'
    Then the table is empty

    Scenario: press button back to list of operations
    When I press button 'Назад к списку операций'
  	Then I see operation selection activity screen