package hellocucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.junit.Assert.assertEquals;

class CanIGetRefund {

    static double myNewSold;
    static double clientBudget;
    static int stock;

    static void processRefund(double clientBudget,
                              double myBudget,
                              int quantity,
                              int stock,
                              double unitPrice,
                              boolean hasReceipt,
                              boolean managerIsPresent,
                              boolean hasproof) {
        CanIGetRefund.clientBudget = clientBudget;
        CanIGetRefund.myNewSold = myBudget;
        CanIGetRefund.stock = stock;
        if (hasReceipt || (managerIsPresent && hasproof)) {
            CanIGetRefund.clientBudget += unitPrice * quantity;
            CanIGetRefund.myNewSold -= unitPrice * quantity;
            CanIGetRefund.stock += quantity;
        }
    }

}

public class Stepdefs {

    private int quantity;
    private double clientBudget;
    private double myBudget;
    private int stock;
    private double unitPrice;
    private boolean hasReceipt;
    private boolean managerIsPresent;
    private boolean hasProof;

    @Given("Client budget {double}")
    public void Client_budget(double initialClientbudget) {
        this.clientBudget = initialClientbudget;
    }


    @Given("My budget {double}")
    public void My_budget(double budget) {
        this.myBudget = budget;
    }

    @Given("Client has receipt {int}")
    public void HasReceipt(int hasReceipt) {
        if (hasReceipt == 1)
            this.hasReceipt = true;
        else this.hasReceipt= false ;
    }

    @Given("Manager is present {int}")
    public void managerIsPresent(int managerIsPresent) {
        if (managerIsPresent == 1)
            this.managerIsPresent = true;
        else this.managerIsPresent=false ;
    }

    @Given("Client has proof {int}")
    public void setHasProof(int hasProof) {
        if (hasProof == 1)
            this.hasProof = true;
        else this.hasProof = false;
    }

    @Given("Price of a unit is {double}")
    public void Price_of_a_unit_is(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Given("The number of objects is {int}")
    public void The_number_of_objects_is(int quantity) {
        this.quantity = quantity;
    }

    @Given("The number of objects in our stock is {int}")
    public void The_number_of_objects_in_our_stock_is(int stock) {
        this.stock = stock;
    }

    @When("Refund me")
    public void Refund_me() {
        CanIGetRefund.processRefund(clientBudget, myBudget, quantity, stock, unitPrice, hasReceipt, managerIsPresent, hasProof);
        stock = CanIGetRefund.stock;
        clientBudget = CanIGetRefund.clientBudget;
        myBudget = CanIGetRefund.myNewSold;
    }

    @Then("The client budget will be {double}")
    public void The_client_budget_will_be(double newClientBudget) {
        assertEquals(newClientBudget, clientBudget, 0.001);
    }

    @Then("My budget will be {double}")
    public void My_budget_will(double newBudget) {
        assertEquals(newBudget, myBudget, 0.001);
    }

    @Then("My new stock will be {int}")
    public void My_new_stock_will_be(int newStock) {
        assertEquals(newStock, stock);
    }
}
