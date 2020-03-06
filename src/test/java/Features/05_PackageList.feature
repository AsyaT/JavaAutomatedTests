@Success
Feature: I open Package list operation

  Scenario: Open Inventory scan activity
    When I select 'Упаковочный лист' in list of operation types
    Then I see Package list activity

  Scenario: I press system back button
    Given I see offer to select action in dialog 'Выберете действие'
    | Без документа-основания |
    | По заказу 							|
    When I select in action dialog item 'Без документа-основания'
    When I press system button back
    Then I see operation selection activity screen
    When I select 'Упаковочный лист' in list of operation types
    Then I see Package list activity