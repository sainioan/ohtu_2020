Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
       Given command login is selected
       When  username "pekka" and password "akkep" are entered
       Then  system will respond with "logged in"

    Scenario: user can login with correct password
       Given command login is selected
       When  username "pekka" and password " " are entered
       Then  system will respond with "wrong username or password"

    Scenario: user can login with correct password
       Given command login is selected
       When  username " " and password "none" are entered
       Then  system will respond with "wrong username or password"
