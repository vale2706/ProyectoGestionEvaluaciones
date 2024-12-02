package co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocenteDTO{
    private Integer tipoId;
    private TipoDocente tipoDocente;
    private String tituloDocente;
    private String nombreCompleto;
    private Integer id;
    private String email;
    public DocenteDTO() {
    }
    public enum TipoDocente {
        Catedra,
        TCompleto,
        Planta
    }
}
