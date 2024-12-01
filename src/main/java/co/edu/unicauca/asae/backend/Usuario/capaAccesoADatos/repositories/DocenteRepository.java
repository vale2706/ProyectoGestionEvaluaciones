package co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.repositories;

import java.util.ArrayList;

import co.edu.unicauca.asae.backend.Usuario.capaAccesoADatos.models.DocenteEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DocenteRepository {

    private ArrayList<DocenteEntity> listaDocentes;
    private Integer pos;

    public DocenteRepository() {
        this.listaDocentes = new ArrayList<DocenteEntity>();
        this.pos = this.listaDocentes.size() + 1;
    }

    // Obtener todos los Docentes
    public ArrayList<DocenteEntity> findAll() {
        System.out.println("Invocando a listar Docentes...");
        return this.listaDocentes;
    }

    public boolean existeCorreo(String correo){
        System.out.println("Consultando si existe Docente con correo.");
        boolean bandera = false;
        for(DocenteEntity Docente : listaDocentes){
            System.out.println("El correo es "+ Docente.getCorreo());
            bandera = true;
            break;
        }
        return bandera;
    }

    public boolean existeDocente(Integer id){
        System.out.println("Consultando si existe Docente.");
        boolean bandera = false;
        for(DocenteEntity Docente : listaDocentes){
            System.out.println("El Docente es "+ Docente.getNombre());
            bandera = true;
            break;
        }
        return bandera;
    }

    // Buscar Docente por ID
    public DocenteEntity findById(Integer idDocente) {
        System.out.println("Invocando a buscar DocenteEntity por ID...");
        DocenteEntity objDocente = null;
        for(DocenteEntity Docente : listaDocentes){
            if(Docente.getId() == idDocente){
                objDocente = Docente;
                break;
            }
        }
        return objDocente;
    }

    // Guardar un Docente
    public DocenteEntity save(DocenteEntity Docente) {
        System.out.println("Invocando a guardar Docente...");
        Docente.setId(pos);
        DocenteEntity objDocente = null;
        if(this.listaDocentes.add(Docente)){
            objDocente = Docente;
            pos++;
        }
        return objDocente;
    }

    // Actualizar un Docente
    public DocenteEntity update(Integer idDocente, DocenteEntity Docente) {
        System.out.println("Invocando a actualizar Docente...");
        DocenteEntity objDocente = null;
        for(int i = 0; i < this.listaDocentes.size(); i++){
            if(this.listaDocentes.get(i).getId() == idDocente){
                this.listaDocentes.set(i, Docente);
                objDocente = Docente;
                break;
            }
        }
        return objDocente;
    }

    public boolean deleteById(Integer idDocente) {
        System.out.println("Invocando a eliminar Docente...");
        boolean bandera = false;

        for (int i = 0; i < this.listaDocentes.size(); i++) {
            if (this.listaDocentes.get(i).getId() == idDocente) {
                this.listaDocentes.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }
}
