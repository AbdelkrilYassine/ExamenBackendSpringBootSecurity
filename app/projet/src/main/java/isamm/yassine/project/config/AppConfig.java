package isamm.yassine.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.xebia.extras.selma.Selma;
import isamm.yassine.project.dto.TaskMapper;

@Configuration
public class AppConfig {

	@Bean
	TaskMapper taskMapper() {
		return Selma.builder(TaskMapper.class).build();
	}
}

