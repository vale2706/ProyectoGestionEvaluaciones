package co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "NivelDesempenio")
public class NivelDesempenioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomDescriptivo;

    @ManyToOne
    @JoinColumn(name = "idCriterio")
    private CriteriosDesempenioEntity criterio;
    public NivelDesempenioEntity(){
        
    }
}
