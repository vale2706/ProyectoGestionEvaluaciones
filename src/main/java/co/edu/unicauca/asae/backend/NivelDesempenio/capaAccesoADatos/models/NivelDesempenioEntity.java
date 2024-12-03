package co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    public NivelDesempenioEntity(){
        
    }
}
