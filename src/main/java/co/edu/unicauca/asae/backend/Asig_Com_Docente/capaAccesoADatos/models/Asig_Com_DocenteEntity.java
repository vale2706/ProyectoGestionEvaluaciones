package co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models.DocenteEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Asig_Com_Docente")
public class Asig_Com_DocenteEntity {
    @EmbeddedId
    private Asig_Com_DocentePK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("asignaturaId")
    @JoinColumn(name = "asignatura_id", referencedColumnName = "id")
    private AsignaturaEntity asignatura;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("docenteId")
    @JoinColumn(name = "docente_id", referencedColumnName = "id")
    private DocenteEntity docente;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("competenciaId")
    @JoinColumn(name = "competencia_id", referencedColumnName = "idComp")
    private CompetenciaEntity competencia;

    @Column(name = "periodo")
    private String periodo;

   
}
