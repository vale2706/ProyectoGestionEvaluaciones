package co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NivelDesempenioDTO {
    private Integer id;
    private String nomDescriptivo;

    public NivelDesempenioDTO(){
        
    }
}