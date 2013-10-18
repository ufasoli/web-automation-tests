A simple project to get started using selenium and maven for the automation of acceptance tests.
For the moment the tests are executed only using the chrome  web driver.
Eventually it will grow to use selenium grid to execute parallel tests in multiple browsers
to run the tests execute

### To run selenium "simple" tests

checkout the develop branch and run

```
mvn clean verify -Pselenium
```

### To run the grid tests

checkout the selenium-grid branch

 - modifiy the startLocalNode script with your PATH and options