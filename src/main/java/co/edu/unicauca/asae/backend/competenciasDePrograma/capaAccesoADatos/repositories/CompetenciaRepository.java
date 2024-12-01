package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;

@Repository
public class CompetenciaRepository {
    
    private int pos;
    private ArrayList<CompetenciaEntity> listaCompetencia;

    public CompetenciaRepository(){
        this.listaCompetencia = new ArrayList<CompetenciaEntity>();
        this.pos = this.listaCompetencia.size() + 1;
    }

    public boolean existeCompetencia(Integer idAsignatura){
        System.out.println("Consultando si existe Competencia con id.");
        boolean bandera = false;
        for(CompetenciaEntity competencia : listaCompetencia){
            System.out.println("La Competencia es: " + competencia.getIdComp());
            bandera = true;
            break;
        }
        return bandera;
    }

    //Obtener todos los comp
    public ArrayList<CompetenciaEntity> findAll(){
        System.out.println("Invocando a listar Competencias...");
        return listaCompetencia;
    }
    
    public CompetenciaEntity findById(int idComp) {
        System.out.println("Invocando a listar Competencias...");
        CompetenciaEntity objComp = null;
        for (CompetenciaEntity comp : listaCompetencia) {
            if (comp.getIdComp() == idComp) {
                objComp = comp;
                break;
            }
        }
        return objComp;
    }

    public CompetenciaEntity save(CompetenciaEntity comp){
        System.out.println("Invocando a guardar Competencias...");
        comp.setIdComp(pos);
        CompetenciaEntity objCompetencia = null;
        if(this.listaCompetencia.add(comp)){
            objCompetencia = comp;
            pos ++;
        }
        return objCompetencia;
    }
    public CompetenciaEntity update(Integer idcomp, CompetenciaEntity comp){
        System.out.println("Invocando a actualizar Competencias...");
        CompetenciaEntity objcomp = null;
        for(int i = 0; i < this.listaCompetencia.size(); i++){
            if(this.listaCompetencia.get(i).getIdComp() == idcomp){
                this.listaCompetencia.set(i, comp);
                objcomp = comp;
                break;
            }
        }
        return objcomp;
    }

    public boolean deleteById(Integer idcomp){
        System.out.println("Invocando a eliminar Competencias...");
        boolean bandecomp = false;

        for(int i = 0; i < this.listaCompetencia.size(); i++){
            if(this.listaCompetencia.get(i).getIdComp() == idcomp){
                this.listaCompetencia.remove(i);
                bandecomp = true;
                break;
            }
        }
        return bandecomp;
    }

}
