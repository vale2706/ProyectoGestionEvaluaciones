package co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RA")
public class ResultadosAprendizajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "idAsignatura", nullable = false)
    private AsignaturaEntity objAsignatura;

    @ManyToOne
    @JoinColumn(name = "idCompetencia", nullable = false)
    private CompetenciaEntity objCompetencia;


    public ResultadosAprendizajeEntity() {
    }
}
