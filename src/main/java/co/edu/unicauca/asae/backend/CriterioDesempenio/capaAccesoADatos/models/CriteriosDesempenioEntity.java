package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class CriteriosDesempenioEntity {
    @Id
    private Integer id;
    private String descripcion;
    private String ponderacionDesemp;

    @ManyToOne
    private CriteriosDesempenioEntity criteriosDesempenio;

    @ManyToOne
    private RubricaEntity rubrica;
    
    public CriteriosDesempenioEntity(){

    }

    public void setNivelDesemp(List<NivelDesempenioEntity> nivelDesemp) {
    }
}
