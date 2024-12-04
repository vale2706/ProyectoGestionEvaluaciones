package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name= "CriterioDesempenio")
public class CriteriosDesempenioEntity {
    @Id
    private Integer id;
    private String descripcion;
    private String ponderacionDesemp;

    @ManyToOne
    @JoinColumn(name= "idRubrica", nullable = false)
    private RubricaEntity rubrica;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<NivelDesempenioEntity> nivelDesempenio;
    
    public CriteriosDesempenioEntity(){

    }

    public void setNivelDesemp(List<NivelDesempenioEntity> nivelDesemp) {
    }
}
