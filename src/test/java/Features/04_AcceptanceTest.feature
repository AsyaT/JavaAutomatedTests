
Feature: User at acceptance operation
@Success
  Scenario: Open acceptance activity
    When I select 'Приемка' in list of operation types
    Then I see AccountingAreaSelection activity
    When I select 'Приемка на 6-4-1' in list of accounting areas
    Then I see Acceptance activity
@Success
  Scenario: I press system back button once
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I press system button back
    Then I see operation selection activity screen
    When I select 'Приемка' in list of operation types
    Then I see AccountingAreaSelection activity
    When I select 'Приемка на 6-4-1' in list of accounting areas
    Then I see Acceptance activity
@Success
  Scenario: I scan wrong order or not order
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I scan 'EAN13' barcode '4603502137574'
    Then I see offer scan order fragment with message 'Документ не найден или недоступен в этом участке учета!'
    When I scan 'Code-128' barcode '296023429174452073866327149433046492099'
    Then I see offer scan order fragment with message 'Нельзя использовать Заказ клиента в статусе Закрыт!'

  Scenario: I scan correct order
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'
    When I scan 'Code-128' barcode ''
    Then offer to scan order is closed
    And I see information with order name ''

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
    When I scan 'GS1_DATABAR_EXP' barcode ''
    Then I see string in table with number '1'
    And nomenclature name is ''
    And weight is '8.3'
    And quantity is '1'
    When I scan 'GS1_DATABAR_EXP' barcode ''
    Then I see string in table with number '2'
    And nomenclature name is ''
    And weight is '7.9'
    And quantity is '1'

  Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order ''
    Then I see string table for product '' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |        56 | ‭65,473 | -9,473 |          7 |       8 |      -1 |
    Then I see string table for product '' follow
      | orderedKg | doneKg | leftKg  | orderedItm | doneItm | leftItm |
      |        56 | 67,413 | -11,413 |          7 |       8 |      -1 |
    When I press system button back
    Then screen with order table is closed

  Scenario: remove selected string
    When I press string number '2'
    Then String number '2' is highlighted with yellow color
    When I press button 'Удалить строку'
    Then I see string in table with number '1'
    And nomenclature name is ''
    Then I do not see string in table with number '2'

  Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order ''
    Then I see string table for product 'Бедрышко куриное' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |        56 | ‭65,473 | -9,473 |          7 |       8 |      -1 |
    Then I see string table for product 'Голень куриная' follow
      | orderedKg | doneKg | leftKg | orderedItm | doneItm | leftItm |
      |        56 | 59,513 | -3,513 |          7 |       7 |       0 |
    When I press system button back
    Then screen with order table is closed

  Scenario: remove all strings
    When I press button 'Удалить всё'
    Then the table is empty

  Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table of products for order ''
    Then I see string table for product '' follow
      | orderedKg | doneKg  | leftKg | orderedItm | doneItm | leftItm |
      |        56 | ‭57,173 | -1,173 |          7 |       7 |       0 |
    Then I see string table for product ' follow
      | orderedKg | doneKg | leftKg | orderedItm | doneItm | leftItm |
      |        56 | 59,513 | -3,513 |          7 |       7 |       0 |
    When I press system button back
    Then screen with order table is closed

  Scenario: Execute button
