@smokeTest
#This is BDD cucumber feature file
#Using Vehicle instead of specifying car,so that other tests can be written based on the type of the vehicle
Feature: User journey for Vehicle Valuation search
  As a User
  I want to be able to search for Vehicle valuation
  So that I can see the Vehicle price results from different vendors

  Background:
    Given user loads the application

  Scenario: Verify user is able to evaluate the car details from the input file
    Given the user reads the carInput text file
    And the user reads the registration number from the input file
    When user enters the registration number and mileage on the home page
    And user clicks on evaluate button
    Then the user evaluates the details from the application with the output text file