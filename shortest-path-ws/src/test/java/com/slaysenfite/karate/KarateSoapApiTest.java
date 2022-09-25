package com.slaysenfite.karate;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Karate test for the soap web service
 *
 * Ensure that the webservice is running before executing these test cases
 */
@RunWith(Karate.class)
@CucumberOptions(features = "classpath:karate")
public class KarateSoapApiTest {
//...     
}
