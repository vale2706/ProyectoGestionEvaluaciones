package co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;

@Repository
public class ResultadosAprendizajeRepository {
    
    private ArrayList<ResultadosAprendizajeEntity> listaRA;

    public ResultadosAprendizajeRepository(){
        this.listaRA = new ArrayList<ResultadosAprendizajeEntity>();
    }

    // Implementación del método findResultadoWithCompetencia
    public ResultadosAprendizajeEntity findResultadoWithCompetencia(@Param("id") Integer id) {
        for (ResultadosAprendizajeEntity ra : listaRA) {
            if (ra.getId().equals(id)) {
                return ra; // Devuelve el resultado de aprendizaje que coincide con el ID
            }
        }
        return null; // Devuelve null si no se encuentra
    }

    //Obtener todos los RA
    public ArrayList<ResultadosAprendizajeEntity> findAll(){
        System.out.println("Invocando a listar Resultados de Aprendizaje...");
        return listaRA;
    }
    
    public ResultadosAprendizajeEntity findById(int idRa) {
        System.out.println("Invocando a listar Resultados de Aprendizaje...");
        for (ResultadosAprendizajeEntity ra : listaRA) {
            if (ra.getId() == idRa) {
                return ra;
            }
        }
        return null;
    }

    public ResultadosAprendizajeEntity save(ResultadosAprendizajeEntity ra){
        System.out.println("Invocando a guardar Resultado de Aprendizaje...");
        listaRA.add(ra);
        return ra;
    }
    public ResultadosAprendizajeEntity update(Integer idRA, ResultadosAprendizajeEntity ra){
        System.out.println("Invocando a actualizar Resultados de Aprendizaje...");
        ResultadosAprendizajeEntity objRa = null;
        for(int i = 0; i < this.listaRA.size(); i++){
            if(this.listaRA.get(i).getId().equals(idRA)){
                this.listaRA.set(i, ra);
                objRa = ra;
                break;
            }
        }
        return objRa;
    }

    public boolean deleteById(Integer idRa){
        System.out.println("Invocando a eliminar Resultados de Aprendizaje...");
        boolean bandera = false;

        for(int i = 0; i < this.listaRA.size(); i++){
            if(this.listaRA.get(i).getId().equals(idRa)){
                this.listaRA.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }

}