Feature: User Registeration with DDT
	I want to check that the user can register in our e-commerce website.
	
	Scenario Outline: User Rgisteration with DDT
	Given 	The user in the home page
	When 	I click on register link
	And     I entered "<firstName>", "<lastName>", "<email>", "<password>"
	Then 	The registeration page displayed successfully
	
	Examples:
		| firstName | lastName	| email					| password |
		| Anas		| Musta		| testBDD001@gmail.com	| 12345678 |
		| Musta		| Anas		| testBDD002@gmail.com	| 87654321 |