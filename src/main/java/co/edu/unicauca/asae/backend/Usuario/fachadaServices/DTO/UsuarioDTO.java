package co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;

    public UsuarioDTO(){
        
    }
}
