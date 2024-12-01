package co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public abstract class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;

    public UsuarioEntity(){
        
    }

    public boolean iniciarSesion(String correo, String contrasenia){
        return this.correo.equals(correo) && this.contrasenia.equals(contrasenia);
    }
}
