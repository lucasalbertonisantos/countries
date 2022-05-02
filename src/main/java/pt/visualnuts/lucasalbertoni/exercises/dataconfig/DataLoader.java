package pt.visualnuts.lucasalbertoni.exercises.dataconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private DataCreationHelper dataCreationHelper;
	
	public void run(ApplicationArguments args) {
		dataCreationHelper.create("US", new String[] {"en"});
		dataCreationHelper.create("BE", new String[] {"nl", "fr", "de"});
		dataCreationHelper.create("NL", new String[] {"nl", "fy"});
		dataCreationHelper.create("DE", new String[] {"de"});
		dataCreationHelper.create("ES", new String[] {"es"});
    }
}
