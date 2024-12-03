package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CompetenciaPrograma")
public class CompetenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComp;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Nivel nivel;
    private String descripcion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objCompetencia")
    private List<ResultadosAprendizajeEntity> resultadosAprendizajes;

    // Relación de uno a muchos con Asig_Com_DocenteEntity
    @OneToMany(mappedBy = "competencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Asig_Com_DocenteEntity> asignaciones;


    public enum Nivel{
        Basico,
        Intermedio,
        Avanzado
    }

    public CompetenciaEntity(){

    }

    //public void type(){
        
    //}
}
