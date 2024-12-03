package co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models;
import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Asignatura")
public class AsignaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private int creditos;
    private int semestre;
    private List<String> compA;
    private ResultadosAprendizajeEntity objRa;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Asig_Com_DocenteEntity> relaciones;
   

    
    public AsignaturaEntity(){

    }
}
