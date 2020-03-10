
Feature: User at realization operation

  Scenario: Open realization activity
    When I select 'Реализация' in list of operation types
    Then I see Realization activity

  Scenario: I press system back button
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I press system button back
    Then I see operation selection activity screen
    When I select 'Реализация' in list of operation types
    Then I see Realization activity

  Scenario: I scan wrong order or not order
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I scan 'EAN13' barcode '4603502137574'
    Then I see offer scan order fragment with message 'Документ не найден или недоступен в этом участке учета!'
    When I scan 'Code-128' barcode '296023429174452073866327149433046492099'
    Then I see offer scan order fragment with message 'Нельзя использовать Заказ клиента в статусе Закрыт!'

  Scenario: I scan correct order
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I scan 'Code-128' barcode '291671781878991262704239534516498916291'
    Then offer to scan order is closed
    And I see information with order name 'Заказ клиента ЗФER-111187 от 04.12.2019 15:44:11'

  Scenario: I scan package list

  Scenario: Do scan error barcodes
    Then I see Realization activity
    When I scan 'EAN13' barcode '4603502137574'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'EAN13' barcode '2209983009489'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302530010082011190820171908252100001923000'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Этот продукт не содержится в документе-основании'

  Scenario: I scan success barcodes
    When I scan 'GS1_DATABAR_EXP' barcode '0104630049291051310300830010082011190820171908252100001923000'
    Then I see string in table with number '1'
    And nomenclature name is 'Бедрышко куриное "365 дней", охл.~0,80 кг*10/~8,0 кг/ (подложка, стрейч)Характеристика: Лента'
    And weight is '8.3'
    And quantity is '1'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630049290931310300790010082011190820171908252100001923000'
    Then I see string in table with number '2'
    And nomenclature name is 'Голень куриная "365 дней", охл.~0,80 кг*10/~8,0 кг/ (подложка, стрейч)Характеристика: Лента'
    And weight is '7.9'
    And quantity is '1'

  Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order 'Заказ клиента ЗФER-111187 от 04.12.2019 15:44:11'
    Then I see string table for product 'Бедрышко куриное' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |        56 | ‭65,473 	| -9,473 |          7 |       8 |      -1 |
    Then I see string table for product 'Голень куриная' follow
      | orderedKg | doneKg | leftKg  | orderedItm | doneItm | leftItm |
      |        56 | 67,413 | -11,413 |          7 |       8 |      -1 |
    When I press system button back
    Then screen with order table is closed


  Scenario: remove selected string
    When I press string number '2'
    Then String number '2' is highlighted with yellow color
    When I press button 'Удалить строку'
    Then I see string in table with number '1'
    And nomenclature name is 'Бедрышко куриное "365 дней", охл.~0,80 кг*10/~8,0 кг/ (подложка, стрейч)Характеристика: Лента'
    Then I do not see string in table with number '2' 
    
  Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order 'Заказ клиента ЗФER-111187 от 04.12.2019 15:44:11'
    Then I see string table for product 'Бедрышко куриное' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |        56 | ‭65,473 	| -9,473 |          7 |       8 |      -1 |
    Then I see string table for product 'Голень куриная' follow
      | orderedKg | doneKg | leftKg  | orderedItm | doneItm | leftItm |
      |        56 | 59,513 | -3,513 |          7 |       7 |      0 |
    When I press system button back
    Then screen with order table is closed

  Scenario: remove all strings
  	When I press button 'Удалить всё'
  	Then the table is empty
  	
  	Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order 'Заказ клиента ЗФER-111187 от 04.12.2019 15:44:11'
    Then I see string table for product 'Бедрышко куриное' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |        56 | ‭57,173 	| -1,173 |          7 |       7 |      0 |
    Then I see string table for product 'Голень куриная' follow
      | orderedKg | doneKg | leftKg  | orderedItm | doneItm | leftItm |
      |        56 | 59,513 | -3,513 |          7 |       7 |      0 |
    When I press system button back
    Then screen with order table is closed
  	
  Scenario: Execute button

  Scenario: press button back to list of operations
  	When I press button 'Назад к списку операций'
  	Then I see operation selection activity screen