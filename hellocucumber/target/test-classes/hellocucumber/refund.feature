Feature: refund  ?

  Scenario Outline: Refund process
    Given Client budget <initialClientBudget>
    Given My budget <myInitialBudget>
    Given The number of objects in our stock is <stock>
    Given The number of objects is <quantity>
    Given Price of a unit is <unitPrice>
    Given Manager is present <ManagerIsPresent>
    Given Client has receipt <ClientHasReceipt>
    Given Client has proof <ClientHasProof>
    When Refund me
    Then The client budget will be <newClientBudget>
    Then My new stock will be <newStock>
    Then My budget will be <myNewBudget>

    Examples:
      | initialClientBudget | myInitialBudget | stock | unitPrice | quantity | newStock | myNewBudget | newClientBudget | ClientHasReceipt | ClientHasProof | ManagerIsPresent |
      | 20.0                | 100.0           | 16    | 8.0       | 2        | 18       | 84.0        | 36.0            | 1                | 0              | 0                |
      | 20.0                | 100.0           | 16    | 8.0       | 2        | 18       | 84.0        | 36.0            | 0                | 1              | 1                |
      | 20.0                | 100.0           | 16    | 8.0       | 2        | 16       | 100.0       | 20.0            | 0                | 0              | 1                |
      | 20.0                | 100.0           | 16    | 8.0       | 2        | 16       | 100.0       | 20.0            | 0                | 0              | 0                |
      | 20.0                | 100.0           | 16    | 8.0       | 2        | 16       | 100.0       | 20.0            | 0                | 1              | 0                |