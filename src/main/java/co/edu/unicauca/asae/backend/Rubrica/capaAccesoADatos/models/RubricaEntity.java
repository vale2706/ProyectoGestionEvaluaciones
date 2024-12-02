package co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class RubricaEntity {
    private Integer id;
    private String nomDescriptivo;
    private List<CriteriosDesempenioEntity> CriterioDes;

    public RubricaEntity(){
        
    }
}
