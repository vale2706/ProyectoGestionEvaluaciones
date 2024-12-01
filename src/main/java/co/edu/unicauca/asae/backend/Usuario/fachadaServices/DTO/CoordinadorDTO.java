package co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoordinadorDTO extends UsuarioDTO {
    private List<String> programas;

    public CoordinadorDTO(){
        
    }
}
