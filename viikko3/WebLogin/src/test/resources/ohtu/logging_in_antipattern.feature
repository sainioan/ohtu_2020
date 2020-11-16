Feature: As a registered user can log in with valid username/password-combination

    Scenario: user can login with correct password
        Given login is selected
        When username "jukka" and password "akkuj" are given
        Then system will respond "Ohtu Application main page"

    Scenario: user can not login with incorrect password
        Given login is selected
        When username "jukka" and password "wrong" are given
        Then system will respond "invalid username or password"

    Scenario: a nonexistent user can not login 
        Given login is selected
        When username "bogus" and password "bogus2" are given 
        Then user is not logged in and error message is given

