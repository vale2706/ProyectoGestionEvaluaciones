package co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models;
import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import jakarta.persistence.*;
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
    private int id;

    private String nombre;
    private String descripcion;
    private int creditos;
    private int semestre;
   
    
    @OneToOne
    @JoinColumn(name = "idRA")
    private ResultadosAprendizajeEntity objRa;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Asig_Com_DocenteEntity> relaciones;
   

    
    public AsignaturaEntity(){

    }


    
}
