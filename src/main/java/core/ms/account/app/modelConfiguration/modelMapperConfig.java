package core.ms.account.app.modelConfiguration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class modelMapperConfig {
    @Bean
public ModelMapper model(){
    return new ModelMapper();
}
}
