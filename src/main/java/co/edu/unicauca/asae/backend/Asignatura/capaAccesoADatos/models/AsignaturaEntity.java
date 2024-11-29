package co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AsignaturaEntity {
    private int id;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int semestre;
    private List<String> compA;

    public AsignaturaEntity(){
        
    }

    public void vincularRA(){

    }

    public void vincularCompetencias(){
        
    }
}
