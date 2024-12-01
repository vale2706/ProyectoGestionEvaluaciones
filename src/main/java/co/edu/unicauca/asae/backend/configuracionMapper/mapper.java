package co.edu.unicauca.asae.backend.configuracionMapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mapper {
    @Bean
    public ModelMapper crearMapeador() {
        System.out.println("Ejecución del método");
        return new ModelMapper();
    }
}
