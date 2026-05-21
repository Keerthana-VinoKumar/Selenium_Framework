Feature: SauceDemo End-to-End Purchase Flow
  As a customer
  I want to buy products on SauceDemo
  So that I can verify the complete purchase journey

  Background:
    Given user is on the SauceDemo login page

  @smoke @ui
  Scenario: Complete end to end purchase flow
    When  user logs in with "standard_user" and "secret_sauce"
    Then  products page should be displayed
    When  user adds "Sauce Labs Backpack" to cart
    And   user adds "Sauce Labs Bike Light" to cart
    Then  cart should have 2 items
    When  user proceeds to cart
    And   user proceeds to checkout
    And   user fills shipping info "John" "Doe" "12345"
    And   user clicks continue
    Then  order total should be displayed
    When  user clicks finish
    Then  order confirmation should show "Thank you for your order"
    @regression @ui
  Scenario: Locked user cannot login
    When  user logs in with "locked_out_user" and "secret_sauce"
    Then  error message "Epic sadface" should appear

  @regression @ui
  Scenario Outline: Purchase with different products
    When  user logs in with "standard_user" and "secret_sauce"
    And   user adds "<product>" to cart
    When  user proceeds to cart
    And   user proceeds to checkout
    And   user fills shipping info "Test" "User" "99999"
    And   user clicks continue
    When  user clicks finish
    Then  order confirmation should show "Thank you for your order"

    Examples:
      | product                      |
      | Sauce Labs Backpack          |
      | Sauce Labs Bike Light        |
      | Sauce Labs Bolt T-Shirt      |
    