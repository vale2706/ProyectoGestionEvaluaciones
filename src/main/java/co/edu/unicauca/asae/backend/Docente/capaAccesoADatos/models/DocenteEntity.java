package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Docente")
public class DocenteEntity {

    private TipoId tipoId;
    private TipoDocente tipoDocente;
    private String tituloDocente;
    private String nombreCompleto;
    private Integer id;
    private String email;

    public DocenteEntity() {
    }
    public enum TipoId{
        CC,
        CE
    }
    public enum TipoDocente {
        Catedra,
        TCompleto,
        Planta
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public TipoId getTipoId() {
        return tipoId;
    }

    public void setTipoId(TipoId tipoId) {
        this.tipoId = tipoId;
    }

    public TipoDocente getTipoDocente() {
        return tipoDocente;
    }

    public void setTipoDocente(TipoDocente tipoDocente) {
        this.tipoDocente = tipoDocente;
    }

    public String getTituloDocente() {
        return tituloDocente;
    }

    public void setTituloDocente(String tituloDocente) {
        this.tituloDocente = tituloDocente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
