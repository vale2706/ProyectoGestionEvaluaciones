package co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.repositories;


import java.util.ArrayList;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AsignaturaRepository {

    private int pos;
    private ArrayList<AsignaturaEntity> listaAsignatura;

    public AsignaturaRepository(){
        this.listaAsignatura = new ArrayList<AsignaturaEntity>();
        //cargarListaAsignatura();
        this.pos = this.listaAsignatura.size() + 1;
    }
    
    //Obtener todas las asignaturas
    public ArrayList<AsignaturaEntity> findAll(){
    System.out.println("Invocando a listar Asignaturas...");
    return this.listaAsignatura;
    }

    public boolean existeAsignatura(Integer idAsignatura){
        System.out.println("Consultando si existe Asignatura con id.");
        boolean bandera = false;
        for(AsignaturaEntity asignatura : listaAsignatura){
            System.out.println("La asignatura es: " + asignatura.getNombre());
            bandera = true;
            break;
        }
        return bandera;
    }

    //Buscar asignatura por ID
    public AsignaturaEntity findById(Integer idAsignatura){
        System.out.println("Invocando a listar Asignatura...");
        AsignaturaEntity objAsignatura = null;
        for(AsignaturaEntity asignatura : listaAsignatura){
            if(asignatura.getId() == idAsignatura){
                objAsignatura = asignatura;
                break;
            }
        }
        return objAsignatura;
    }

    public AsignaturaEntity save(AsignaturaEntity asignatura){
        System.out.println("Invocando a guardar Asignatura");
        asignatura.setId(pos);
        AsignaturaEntity objAsignatura = null;
        if(this.listaAsignatura.add(asignatura)){
            objAsignatura = asignatura;
            pos++;
        }
        return objAsignatura;
    }

    public AsignaturaEntity update(Integer idAsignatura, AsignaturaEntity asignatura){
        System.out.println("Invocando a Actualizar Asignatura...");
        AsignaturaEntity objAsignatura = null;
        for(int i = 0; i < this.listaAsignatura.size(); i++){
            if(this.listaAsignatura.get(i).getId() == idAsignatura){
                this.listaAsignatura.set(i, asignatura);
                objAsignatura = asignatura;
                break;
            }
        }
        return objAsignatura;
    }

    public boolean deleteById(Integer idAsignatura) {

        System.out.println("Invocando a eliminar Asignatura...");
        boolean bandera = false;

        for (int i = 0; i < this.listaAsignatura.size(); i++) {
            if (this.listaAsignatura.get(i).getId() == idAsignatura) {
                this.listaAsignatura.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }    
}
