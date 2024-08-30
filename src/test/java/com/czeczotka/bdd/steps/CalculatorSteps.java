package com.czeczotka.bdd.steps;

import com.czeczotka.bdd.calculator.Calculator;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CalculatorSteps {

 private    Calculator calculator;
    
   private  Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        calculator = new Calculator();
        this.scenario = scenario;
    }

    @Given("^I have a calculator$")
    public void i_have_a_calculator() throws Throwable {
        assertNotNull(calculator);
        embedScreenshot(scenario);
    }

    @When("^I add (-?\\d+) and (-?\\d+)$")
    public void i_add_and(int arg1, int arg2) throws Throwable {
        calculator.add(arg1, arg2);
        embedScreenshot(scenario);
    }

    @Then("^the result should be (-?\\d+)$")
    public void the_result_should_be(int result) throws Throwable {
        assertEquals(result, calculator.getResult());
        embedScreenshot(scenario);
    }
    
    @After
    public void finalize(Scenario scenario) {
    	 	embedScreenshot(scenario);
    }
    
    public void embedScreenshot(Scenario scenario) {  
            try {  
            		Path path = Paths.get("./1.png");

		    byte[] screenshot = Files.readAllBytes(path);
			scenario.attach(screenshot, "image/png", "Screenshot");



		    
            } catch (ClassCastException cce) {  
                cce.printStackTrace();  
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
    }  
}
