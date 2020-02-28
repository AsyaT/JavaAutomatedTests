Feature: Settings
	User do scanning on Inventory operation

Scenario: Wants select operation without settings set
	When I press button 'Выберите операцию'
	Then I see error alert message 'Настройки не заданы!'

Scenario: Wants to input incorrect settings
	When I press button 'Настройки'
	And I enter IP adress '0.0.0.0'
	And I enter username 'name'
	And I enter password 'pwd'
	When I press button 'Сохранить'
	When I go back
	When I press button 'Выберите операцию'
	Then I see error screen 'Сервер не отвечает.'

Scenario: Wants to input correct settings
	When I press button 'Настройки'
	And I enter IP adress '10.74.255.29'
	And I enter username 'tsd_ish2'
	And I enter password '123'
	When I press button 'Сохранить'
	When I go back
	When I press button 'Выберите операцию'
	Then I see operation selection activity screen