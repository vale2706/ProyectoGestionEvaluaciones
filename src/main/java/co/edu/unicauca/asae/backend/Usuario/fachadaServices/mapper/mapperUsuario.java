package co.edu.unicauca.asae.backend.Usuario.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperUsuario {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
