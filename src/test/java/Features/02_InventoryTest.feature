Feature: User at Inventory operation
  User scan different barcodes

  Scenario: Open Inventory scan activity
    When I select 'Инвентаризация' in list of operation types
    Then I see Inventory activity

  Scenario: Do scan error barcodes
    Given Inventory acativity is open
    When I scan 'EAN13' barcode '4603502137574'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'EAN13' barcode '2209983009489'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Тип LocalEAN13 запрещен к сканирванию'

  Scenario: Do scan correct barcodes
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302530010082011190820171908252100001923000'
    Then I see string in table with number '1'
    And nomenclature name is 'Филе белое цыпленка-бройлера, охл.~25,00 кг*1/~25,0 кг/ (пакет пнд, полимерный ящик)Характеристика: ЗФД'
    And weight is '25.3'
    And quantity is '1'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302530010082011190820171908252100001923000'
    Then I see string in table with number '1'
    And weight is '50.6'
    And quantity is '2'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302470010082011190820171908252100001923000'
    Then I see string in table with number '1'
    And weight is '75.3'
    And quantity is '3'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302470010082011200120172001262100001923000'
    Then I see string in table with number '1'
    And weight is '100.0'
    And quantity is '4'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302470010082011200120172001262100001925000'
    Then I see string in table with number '1'
    And weight is '124.7'
    And quantity is '5'
    When I scan 'GS1_DATABAR_EXP' barcode '0104660017708243310300745610082011190820171908252100001922000'
    Then I see string in table with number '2'
    And nomenclature name is 'Бедрышко куриное "Здоровая Ферма", охл.~8,00 кг*1/~8,0 кг/ (гофрокороб, пленка пнд)Характеристика: Метро'
    And weight is '7.456'
    And quantity is '1'

  Scenario: Do scan error GS1 barcodes
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302470010082011200120172001262100001929000'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Производитель с номером 9 не найден'
    When I scan 'GS1_DATABAR_EXP' barcode '010463004929467011190820171909091008202100001926000'
    Then I see alert title 'Ошибка при сканировании штрих-кода!' and message 'Такой штрих-код не найден в номенклатуре!'

  Scenario: Scan barcode with two nomenclatures
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094817550310301219710082011200205172002102100001925000'
    Then I see dialog to chose correct nomenclature
    And select line item '2' with name 'Фарш куриный Котлетный,  "Здоровая Ферма", зам.0,50 кг*24/12,0 кг/ (полиамид. оболочка, гофрокороб) Характеристика: Казахстан Bec: 0.5'
    Then I see string in table with number '3'
    And nomenclature name is 'Фарш куриный Котлетный,  "Здоровая Ферма", зам.0,50 кг*24/12,0 кг/ (полиамид. оболочка, гофрокороб)Характеристика: Казахстан'
    And weight is '12.197'
    And quantity is '1'
