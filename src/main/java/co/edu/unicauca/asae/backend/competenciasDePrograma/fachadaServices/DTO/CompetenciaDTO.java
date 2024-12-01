package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO;

import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

import java.util.List;


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
    private List<ResultadosAprendizajeDTO> resultadosAprendizajes;

    public enum Nivel {
        Basico,
        Intermedio,
        Avanzado
    }
    public CompetenciaDTO(){
        
    }
}
