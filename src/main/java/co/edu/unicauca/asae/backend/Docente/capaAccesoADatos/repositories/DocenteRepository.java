package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models.DocenteEntity;

@Repository
public class DocenteRepository {
    private int pos;
    private ArrayList<DocenteEntity> listaDocente;

    public DocenteRepository(){
        this.listaDocente = new ArrayList<DocenteEntity>();
        //cargarListaDocente();
        this.pos = this.listaDocente.size() + 1;
    }
    
    //Obtener todas las Docentes
    public ArrayList<DocenteEntity> findAll(){
    System.out.println("Invocando a listar Docentes...");
    return this.listaDocente;
    }

    public boolean existeDocente(Integer idDocente){
        System.out.println("Consultando si existe Docente con id.");
        boolean bandera = false;
        for(DocenteEntity Docente : listaDocente){
            System.out.println("La Docente es: " + Docente.getNombreCompleto());
            bandera = true;
            break;
        }
        return bandera;
    }

    //Buscar Docente por ID
    public DocenteEntity findById(Integer idDocente){
        System.out.println("Invocando a listar Docente...");
        DocenteEntity objDocente = null;
        for(DocenteEntity Docente : listaDocente){
            if(Docente.getId() == idDocente){
                objDocente = Docente;
                break;
            }
        }
        return objDocente;
    }

    public DocenteEntity save(DocenteEntity Docente){
        System.out.println("Invocando a guardar Docente");
        Docente.setId(pos);
        DocenteEntity objDocente = null;
        if(this.listaDocente.add(Docente)){
            objDocente = Docente;
            pos++;
        }
        return objDocente;
    }

    public DocenteEntity update(Integer idDocente, DocenteEntity Docente){
        System.out.println("Invocando a Actualizar Docente...");
        DocenteEntity objDocente = null;
        for(int i = 0; i < this.listaDocente.size(); i++){
            if(this.listaDocente.get(i).getId() == idDocente){
                this.listaDocente.set(i, Docente);
                objDocente = Docente;
                break;
            }
        }
        return objDocente;
    }

    public boolean deleteById(Integer idDocente) {

        System.out.println("Invocando a eliminar Docente...");
        boolean bandera = false;

        for (int i = 0; i < this.listaDocente.size(); i++) {
            if (this.listaDocente.get(i).getId() == idDocente) {
                this.listaDocente.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }
}
