package co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperAsig_Com_Docente {
    @Bean(name = "modelMapperRelacion")
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
