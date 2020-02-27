Feature: InventoryScenario
	User do scanning on Inventory operation

Background:
	Given I run application

Scenario: Wants select operation without settings set
	Given I press button 'Select operation'
	Then I see error alert message 'Настройки не заданы!'
