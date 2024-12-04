package co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class NivelDesempenioEntity {
    @Id
    private Integer id;
    private String nomDescriptivo;

    @ManyToOne
    @JoinColumn(name = "idCriterio",nullable = false)
    private CriteriosDesempenioEntity criterio;
    public NivelDesempenioEntity(){
        
    }
}
