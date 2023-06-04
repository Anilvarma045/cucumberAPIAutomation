package stepDefinations;



import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

	@RunWith(Cucumber.class)
	public class StepDefinations {

	    @Given("^user is on landing page$")
	    public void user_is_on_landing_page() throws Throwable {
	    
	    System.out.println("navigate to stepDefination");
	    }

	    @When("^user login into application with username and password$")
	    public void user_login_into_application_with_username_and_password() throws Throwable {
	    System.out.println("when condtion login successfully");
	    }

	    @Then("^Homepage is populate$")
	    public void homepage_is_populate() throws Throwable {
	    System.out.println("done with validations");
	    }

	    @And("^cards are displayed$")
	    public void cards_are_displayed() throws Throwable {
	    }

	}

