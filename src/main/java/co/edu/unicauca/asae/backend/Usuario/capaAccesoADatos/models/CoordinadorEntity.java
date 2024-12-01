package co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "coordinador")
public class CoordinadorEntity extends UsuarioEntity {

    private List<String> programas;

    public CoordinadorEntity (){

    }
}
