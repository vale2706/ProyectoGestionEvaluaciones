package co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Asig_Com_DocentePK implements Serializable {
    @Column(name = "asignatura_id")
    private int asignaturaId;

    @Column(name = "docente_id")
    private int docenteId;

    @Column(name = "competencia_id")
    private int competenciaId;

    // Getters y Setters
    public int getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(int asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public int getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(int docenteId) {
        this.docenteId = docenteId;
    }

    public int getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(int competenciaId) {
        this.competenciaId = competenciaId;
    }

    // Métodos equals y hashCode (necesarios para claves primarias compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asig_Com_DocentePK that = (Asig_Com_DocentePK) o;
        return asignaturaId == that.asignaturaId &&
               docenteId == that.docenteId &&
               competenciaId == that.competenciaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignaturaId, docenteId, competenciaId);
    }
}
