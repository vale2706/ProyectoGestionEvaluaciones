package co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperRa {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
