package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocenteEntity {
    private Integer tipoId;
    private TipoDocente tipoDocente;
    private String tituloDocente;
    private String nombreCompleto;
    private Integer id;
    private String email;

    public DocenteEntity() {
    }
    public enum TipoDocente {
        Catedra,
        TCompleto,
        Planta
    }
}
