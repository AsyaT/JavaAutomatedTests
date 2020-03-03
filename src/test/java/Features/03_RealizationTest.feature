@Success
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
    Given I see offer to scan order 'СКАНИРУЙТЕ ШТРИХ-КОД ДОКУМЕНТА-ОСНОВАНИЯ'

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

  Scenario: I see ordered items in table
    When I press on informaiton with order name
    Then I see table with ordered products

  Scenario: I scan package list

  Scenario: Do scan error barcodes
    Then I see Realization activity
    When I scan 'EAN13' barcode '4603502137574'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'EAN13' barcode '2209983009489'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302530010082011190820171908252100001923000'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Этот продукт не содержится в документе-основании'
