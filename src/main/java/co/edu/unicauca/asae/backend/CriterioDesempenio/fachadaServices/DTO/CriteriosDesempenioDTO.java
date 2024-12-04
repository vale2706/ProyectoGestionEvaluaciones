package co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.DTO;

import java.util.List;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CriteriosDesempenioDTO {
    private Integer id;
    private String descripcion;
    private String ponderacionDesemp;
    private RubricaEntity rubrica;
    private List<NivelDesempenioEntity> nivelDesemp;
    
    public CriteriosDesempenioDTO(){

    }
}
