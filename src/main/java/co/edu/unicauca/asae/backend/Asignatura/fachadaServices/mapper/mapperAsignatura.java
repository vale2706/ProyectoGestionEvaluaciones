package co.edu.unicauca.asae.backend.Asignatura.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapperAsignatura {
    @Bean
    public ModelMapper crearMapeador() {
        System.out.println("Ejecución del método");
        return new ModelMapper();
    }
}
