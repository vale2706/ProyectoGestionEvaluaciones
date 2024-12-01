package co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultadosAprendizajeDTO {
    private Integer id;
    private String descripcion;
    private String tipo;

    public ResultadosAprendizajeDTO(){
        
    }
}
