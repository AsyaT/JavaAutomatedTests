@Success
Feature: User at Inventory operation
  User scan different barcodes

  Scenario: Open Inventory scan activity
    When I select 'Инвентаризация' in list of operation types
    Then I see Inventory activity

  Scenario: Do scan error barcodes
    Given Inventory activity is open
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

  Scenario: Scan barcode with two or more nomenclatures
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094817550310301219710082011200205172002102100001925000'
    Then I see dialog to chose correct nomenclature
    And select line item '2' with name 'Фарш куриный Котлетный,  "Здоровая Ферма", зам.0,50 кг*24/12,0 кг/ (полиамид. оболочка, гофрокороб) Характеристика: Казахстан Вес: 0.5'
    Then I see string in table with number '3'
    And nomenclature name is 'Фарш куриный Котлетный,  "Здоровая Ферма", зам.0,50 кг*24/12,0 кг/ (полиамид. оболочка, гофрокороб)Характеристика: Казахстан'
    And weight is '12.197'
    And quantity is '1'

  Scenario: Scan again barcode with different nomenclatures
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094817550310301219710082011200205172002102100001925000'
    Then I see string in table with number '3'
    And quantity is '2'

  Scenario: Scan different databars
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094817093310300795610082011190820171908252100001922000'
    Then I see string in table with number '4'
    And nomenclature name is 'Грудка куриная, "Здоровая Ферма", охл., 8,0 кг/ (пакет пнд, гофрокороб)Характеристика: базовая'
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094812999310300615610082011190820171908252100001922000'
    Then I see string in table with number '5'
    And nomenclature name is 'Печень куриная, "Здоровая Ферма", зам., 0,5 кг*12/ 6,0 кг/ (подложка, стрейч)Характеристика: Тандер'
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094816553310300825610082011190820171908252100001922000'
    Then I see string in table with number '6'
    And nomenclature name is 'Бедрышко куриное, "Здоровая Ферма", зам., ~0,8 кг*10/ 8,0 кг/ (подложка, стрейч)Характеристика: базовая'

  Scenario: remove selected string
    When I press string number '4'
    Then String number '4' is highlighted with yellow color
    When I press button 'Удалить строку'
    Then I see string in table with number '4'
    And nomenclature name is 'Печень куриная, "Здоровая Ферма", зам., 0,5 кг*12/ 6,0 кг/ (подложка, стрейч)Характеристика: Тандер'
    Then I see string in table with number '5'
    And nomenclature name is 'Бедрышко куриное, "Здоровая Ферма", зам., ~0,8 кг*10/ 8,0 кг/ (подложка, стрейч)Характеристика: базовая'

  Scenario: remove several strings
    When I press string number '1'
    When I press string number '3'
    When I press string number '5'
    When I press button 'Удалить строку'
    Then I see string in table with number '1'
    And nomenclature name is 'Бедрышко куриное "Здоровая Ферма", охл.~8,00 кг*1/~8,0 кг/ (гофрокороб, пленка пнд)Характеристика: Метро'
    Then I see string in table with number '2'
    And nomenclature name is 'Печень куриная, "Здоровая Ферма", зам., 0,5 кг*12/ 6,0 кг/ (подложка, стрейч)Характеристика: Тандер'

  Scenario: remove all strings
    When I press button 'Удалить всё'
    Then the table is empty

  Scenario: use button bar-code
    When I press button 'Штрихкод'
    Then I see fragment with message '... Сканируйте штрих-код ...'
    When I scan 'EAN13' barcode '4603502137574'
    Then I see fragment with message 'Тип LocalEAN13 запрещен к сканирванию'
    When I scan 'GS1_DATABAR_EXP' barcode '0104630037036817310302530010082011190820171908252100001923000'
    Then I see fragment with message 'Штрих-код: 4630037036817Номенклатура: Филе белое цыпленка-бройлера, охл.~25,00 кг*1/~25,0 кг/ (пакет пнд, полимерный ящик)Характеристика: ЗФДВес: 25.3 кгНомер партии: 0820Дата производства: 20-08-2019Дата истечения срока годности: 25-08-2019Серийный номер: 00001Внутренний код производителя: 3 - УРАЛБРОЙЛЕР ЗАО (Кунашак)Внутренний код оборудования: 0'
    When I press system button back
    Then the fragment disappear

  Scenario: press button execute
  
  Scenario: Scan barcode with two or more nomenclatures AGAIN AFTER EXECUTE
    When I scan 'GS1_DATABAR_EXP' barcode '0104607094817550310301219710082011200205172002102100001925000'
    Then I see dialog to chose correct nomenclature
    And select line item '2' with name 'Фарш куриный Котлетный,  "Здоровая Ферма", зам.0,50 кг*24/12,0 кг/ (полиамид. оболочка, гофрокороб) Характеристика: Казахстан Вес: 0.5'
    Then I see string in table with number '1'
    And nomenclature name is 'Фарш куриный Котлетный,  "Здоровая Ферма", зам.0,50 кг*24/12,0 кг/ (полиамид. оболочка, гофрокороб)Характеристика: Казахстан'
    And weight is '12.197'
    And quantity is '1'

  Scenario: press system button back
    Given Inventory activity is open
    When I press system button back
    Then I see operation selection activity screen

  Scenario: press button back to list of operations
    When I select 'Инвентаризация' in list of operation types
    Then I see Inventory activity
    When I press button 'Назад к списку операций'
    Then I see operation selection activity screen
