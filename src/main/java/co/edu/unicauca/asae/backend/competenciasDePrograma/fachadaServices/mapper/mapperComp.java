package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperComp {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
