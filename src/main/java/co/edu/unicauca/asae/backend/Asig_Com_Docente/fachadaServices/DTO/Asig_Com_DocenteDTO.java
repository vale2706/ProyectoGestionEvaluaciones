package co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.DTO;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocentePK;
import co.edu.unicauca.asae.backend.Asignatura.fachadaServices.DTO.AsignaturaDTO;
import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import lombok.Data;

@Data
public class Asig_Com_DocenteDTO {
    private Asig_Com_DocentePK id;
    private AsignaturaDTO asignatura;
    private CompetenciaDTO competencia;
    private DocenteDTO docente;
    private String periodo;
    
}
