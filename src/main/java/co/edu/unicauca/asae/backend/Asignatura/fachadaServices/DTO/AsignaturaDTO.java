package co.edu.unicauca.asae.backend.Asignatura.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer semestre;
    private List<String> compA;

    public AsignaturaDTO(){
        
    }
}
