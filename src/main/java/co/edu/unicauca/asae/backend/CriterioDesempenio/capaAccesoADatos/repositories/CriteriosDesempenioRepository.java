package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;

@Repository
public class CriteriosDesempenioRepository {
    private int pos;
    private ArrayList<CriteriosDesempenioEntity> listaDesempenio;

    public CriteriosDesempenioRepository(){
        this.listaDesempenio = new ArrayList<CriteriosDesempenioEntity>();
        this.pos = this.listaDesempenio.size() + 1;
    }
    
    //Obtener todas las Criterios de desempeño
    public ArrayList<CriteriosDesempenioEntity> findAll(){
    System.out.println("Invocando a listar Criterios de desempeño...");
    return this.listaDesempenio;
    }

    public boolean existeCriteriosDesempenio(Integer idDesempenio){
        System.out.println("Consultando si existe Criterio de desempenio con id.");
        boolean bandera = false;
        for(CriteriosDesempenioEntity desempenio : listaDesempenio){
            System.out.println("La desempenio es: " + desempenio.getDescripcion());
            bandera = true;
            break;
        }
        return bandera;
    }

    //Buscar desempenio por ID
    public CriteriosDesempenioEntity findById(Integer idDesempenio){
        System.out.println("Invocando a listar Criterio de desempenio...");
        CriteriosDesempenioEntity objDesempenio = null;
        for(CriteriosDesempenioEntity desempenio : listaDesempenio){
            if(desempenio.getId() == idDesempenio){
                objDesempenio = desempenio;
                break;
            }
        }
        return objDesempenio;
    }

    public CriteriosDesempenioEntity save(CriteriosDesempenioEntity desempenio){
        System.out.println("Invocando a guardar Criterio de desempenio");
        desempenio.setId(pos);
        CriteriosDesempenioEntity objDesempenio = null;
        if(this.listaDesempenio.add(desempenio)){
            objDesempenio = desempenio;
            pos++;
        }
        return objDesempenio;
    }

    public CriteriosDesempenioEntity update(Integer idDesempenio, CriteriosDesempenioEntity desempenio){
        System.out.println("Invocando a Actualizar Criterio de desempenio...");
        CriteriosDesempenioEntity objDesempenio = null;
        for(int i = 0; i < this.listaDesempenio.size(); i++){
            if(this.listaDesempenio.get(i).getId() == idDesempenio){
                this.listaDesempenio.set(i, desempenio);
                objDesempenio = desempenio;
                break;
            }
        }
        return objDesempenio;
    }

    public boolean deleteById(Integer idDesempenio) {

        System.out.println("Invocando a eliminar Criterio de desempenio...");
        boolean bandera = false;

        for (int i = 0; i < this.listaDesempenio.size(); i++) {
            if (this.listaDesempenio.get(i).getId() == idDesempenio) {
                this.listaDesempenio.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }
}
