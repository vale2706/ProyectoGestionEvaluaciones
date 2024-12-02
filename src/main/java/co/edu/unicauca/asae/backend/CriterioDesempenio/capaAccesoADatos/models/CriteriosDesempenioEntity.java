package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class CriteriosDesempenioEntity {
    private Integer id;
    private String descripcion;
    private String ponderacionDesemp;
    private List<NivelDesempenioEntity> nivelDesemp;
    
    public CriteriosDesempenioEntity(){

    }
}
