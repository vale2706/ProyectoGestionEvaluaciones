package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompetenciaDTO {
    private Integer idcomp;
    private Nivel nivel;
    private String descripcion;

    public enum Nivel {
        Basico,
        Intermedio,
        Avanzado
    }
    public CompetenciaDTO(){
        
    }
}
