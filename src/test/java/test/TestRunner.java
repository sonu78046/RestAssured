package test;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/feature",glue= {"stepDefination"}, plugin = {"pretty", "html:target/cucumber.html"})
public class TestRunner {

}
