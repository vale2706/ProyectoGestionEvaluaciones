package co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import jakarta.persistence.*;
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

    @Column(length = 250)
    private String descripcion;

    @Column(length = 250)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "idAsignatura")
    private AsignaturaEntity objAsignatura;

    @ManyToOne
    @JoinColumn(name = "idCompetencia")
    private CompetenciaEntity competencia;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "resultadosAprendizaje")
//    private List<CompetenciaEntity> competencias;

    public ResultadosAprendizajeEntity() {
    }
}
