package co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.repositories;

import java.util.ArrayList;

import co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.models.CoordinadorEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CoordinadorRepository {

    private ArrayList<CoordinadorEntity> listaCoordinadores;
    private Integer pos;

    public CoordinadorRepository() {
        this.listaCoordinadores = new ArrayList<CoordinadorEntity>();
        this.pos = this.listaCoordinadores.size() + 1;
    }

    // Obtener todos los Coordinadores
    public ArrayList<CoordinadorEntity> findAll() {
        System.out.println("Invocando a listar Coordinadores...");
        return this.listaCoordinadores;
    }

    public boolean existeCorreo(String correo){
        System.out.println("Consultando si existe Coordinador con correo.");
        boolean bandera = false;
        for(CoordinadorEntity Coordinador : listaCoordinadores){
            System.out.println("El correo del coordinador es "+ Coordinador.getCorreo());
            bandera = true;
            break;
        }
        return bandera;
    }

    public boolean existeCoordinador(Integer id){
        System.out.println("Consultando si existe Coordinador.");
        boolean bandera = false;
        for(CoordinadorEntity Coordinador : listaCoordinadores){
            System.out.println("El coordinador es "+ Coordinador.getNombre());
            bandera = true;
            break;
        }
        return bandera;
    }

    // Buscar Coordinador por ID
    public CoordinadorEntity findById(Integer idCoordi) {
        System.out.println("Invocando a buscar CoordinadorEntity por ID...");
        CoordinadorEntity objCoordi = null;
        for(CoordinadorEntity Coordinador : listaCoordinadores){
            if(Coordinador.getId() == idCoordi){
                objCoordi = Coordinador;
                break;
            }
        }
        return objCoordi;
    }

    // Guardar un Coordinador
    public CoordinadorEntity save(CoordinadorEntity Coordinador) {
        System.out.println("Invocando a guardar Coordinador...");
        Coordinador.setId(pos);
        CoordinadorEntity objCoordi = null;
        if(this.listaCoordinadores.add(Coordinador)){
            objCoordi = Coordinador;
            pos++;
        }
        return objCoordi;
    }

    // Actualizar un Coordinador
    public CoordinadorEntity update(Integer idCoordi, CoordinadorEntity Coordinador) {
        System.out.println("Invocando a actualizar Coordinador...");
        CoordinadorEntity objCoordi = null;
        for(int i = 0; i < this.listaCoordinadores.size(); i++){
            if(this.listaCoordinadores.get(i).getId() == idCoordi){
                this.listaCoordinadores.set(i, Coordinador);
                objCoordi = Coordinador;
                break;
            }
        }
        return objCoordi;
    }

    public boolean deleteById(Integer idCoordi) {
        System.out.println("Invocando a eliminar Coordinador...");
        boolean bandera = false;

        for (int i = 0; i < this.listaCoordinadores.size(); i++) {
            if (this.listaCoordinadores.get(i).getId() == idCoordi) {
                this.listaCoordinadores.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }
}
