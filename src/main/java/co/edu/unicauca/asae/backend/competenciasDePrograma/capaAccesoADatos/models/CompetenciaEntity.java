package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models;

import java.util.List;


import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
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
    private Nivel nivel;
    private String descripcion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objCompetencia")
    private List<ResultadosAprendizajeEntity> resultadosAprendizajes;

    @ManyToOne
    @JoinColumn(name = "idAsignatura", nullable = false)
    private AsignaturaEntity objAsignatura;

    public enum Nivel{
        Basico,
        Intermedio,
        Avanzado
    }

    public CompetenciaEntity(){

    }

    public void type(){
        
    }
}
