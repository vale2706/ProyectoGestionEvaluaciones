package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.DTO.Asig_Com_DocenteDTO;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity.Tipo;

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
    private Integer tblCompId;
    private Tipo tipo;
    private List<ResultadosAprendizajeDTO> resultadosAprendizaje;
    private List<Asig_Com_DocenteDTO> relaciones;
    
    public enum Tipo{
        Programa,
        Asignatura 
    }
    public enum Nivel {
        Basico,
        Intermedio,
        Avanzado
    }
    public CompetenciaDTO(){
        
    }
}
