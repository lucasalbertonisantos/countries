package pt.visualnuts.lucasalbertoni.exercises;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("pt/visualnuts/lucasalbertoni/exercises/Country.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pt.visualnuts.lucasalbertoni.exercises.steps")
public class RunCucumberTests {
	
}
