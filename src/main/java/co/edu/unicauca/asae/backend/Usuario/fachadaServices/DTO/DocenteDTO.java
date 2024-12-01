package co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocenteDTO extends UsuarioDTO {
    private Integer tipoId;
    private TipoDocente tipoDocente;
    private String tituloDocente;

    public enum TipoDocente{
        Catedra,
        TCompleto,
        Planta
    }
    public DocenteDTO(){
        
    }
}
