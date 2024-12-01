package co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "coordinador")
public class DocenteEntity extends UsuarioEntity {

    private Integer tipoId;
    private TipoDocente tipoDocente;
    private String tituloDocente;

    public enum TipoDocente{
        Catedra,
        TCompleto,
        Planta
    }

    public DocenteEntity (){

    }
}
