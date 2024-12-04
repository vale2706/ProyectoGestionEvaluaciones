package co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class RubricaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomDescriptivo;
    private int nota;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "rubrica")
    private List<CriteriosDesempenioEntity> criterioDesempenio;

    public RubricaEntity(){
        
    }
}
