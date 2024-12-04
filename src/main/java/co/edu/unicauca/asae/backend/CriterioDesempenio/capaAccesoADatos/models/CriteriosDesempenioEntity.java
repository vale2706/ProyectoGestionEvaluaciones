package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;
import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String ponderacionDesemp;

    @ManyToOne
    @JoinColumn(name= "idRubrica")
    private RubricaEntity rubrica;

    @OneToMany(mappedBy ="criterio",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<NivelDesempenioEntity> nivelDesempenio;
    
    public CriteriosDesempenioEntity(){

    }

    public void setNivelDesemp(List<NivelDesempenioEntity> nivelDesemp) {
    }
}
