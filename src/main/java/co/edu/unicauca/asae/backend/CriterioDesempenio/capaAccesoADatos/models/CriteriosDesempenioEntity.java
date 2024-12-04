package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JoinColumn(name= "idRubrica", nullable = false)
    private RubricaEntity rubrica;

    @OneToMany(fetch = FetchType.EAGER)
    private List<NivelDesempenioEntity> nivelDesempeño;
    
    public CriteriosDesempenioEntity(){

    }

    public void setNivelDesemp(List<NivelDesempenioEntity> nivelDesemp) {
    }
}
