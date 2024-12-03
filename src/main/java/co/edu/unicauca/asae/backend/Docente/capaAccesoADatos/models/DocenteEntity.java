package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id
    private Integer id;
    private TipoId tipoId;
    private TipoDocente tipoDocente;
    private String tituloDocente;
    private String nombreCompleto;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipoId(TipoId tipoId) {
        this.tipoId = tipoId;
    }

    public void setTipoDocente(TipoDocente tipoDocente) {
        this.tipoDocente = tipoDocente;
    }

    public void setTituloDocente(String tituloDocente) {
        this.tituloDocente = tituloDocente;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
