package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models;

import java.util.List;


import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Competencia")
public class CompetenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComp;
    private Nivel nivel;
    private String descripcion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objCompetencia")
    private List<ResultadosAprendizajeEntity> resultadosAprendizajes;
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
