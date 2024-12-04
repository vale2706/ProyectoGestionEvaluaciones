package co.edu.unicauca.asae.backend.Rubrica.fachadaServices.DTO;

import java.util.List;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RubricaDTO {
    private Integer id;
    private String nomDescriptivo;
    private int nota;
    private List<CriteriosDesempenioEntity> CriterioDesempenio;

    public RubricaDTO(){
        
    }
}
