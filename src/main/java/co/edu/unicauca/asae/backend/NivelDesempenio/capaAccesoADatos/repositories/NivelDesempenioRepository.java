package co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;

@Repository
public class NivelDesempenioRepository {
    private int pos;
    private ArrayList<NivelDesempenioEntity> listaNivelDesempenio;

    public NivelDesempenioRepository(){
        this.listaNivelDesempenio = new ArrayList<NivelDesempenioEntity>();
        //cargarListaNivelDesempenio();
        this.pos = this.listaNivelDesempenio.size() + 1;
    }
    
    //Obtener todas las nivelDesempenios
    public ArrayList<NivelDesempenioEntity> findAll(){
    System.out.println("Invocando a listar Niveles de desempeños...");
    return this.listaNivelDesempenio;
    }

    public boolean existeNivelDesempenio(Integer idNivelDesempenio){
        System.out.println("Consultando si existe Nivele de desempeño con id.");
        boolean bandera = false;
        for(NivelDesempenioEntity nivelDesempenio : listaNivelDesempenio){
            System.out.println("El Nivel de desempeño es: " + nivelDesempenio.getNomDescriptivo());
            bandera = true;
            break;
        }
        return bandera;
    }

    //Buscar nivelDesempenio por ID
    public NivelDesempenioEntity findById(Integer idNivelDesempenio){
        System.out.println("Invocando a listar Niveles de desempeños...");
        NivelDesempenioEntity objNivelDesempenio = null;
        for(NivelDesempenioEntity nivelDesempenio : listaNivelDesempenio){
            if(nivelDesempenio.getId() == idNivelDesempenio){
                objNivelDesempenio = nivelDesempenio;
                break;
            }
        }
        return objNivelDesempenio;
    }

    public NivelDesempenioEntity save(NivelDesempenioEntity nivelDesempenio){
        System.out.println("Invocando a guardar Nivel de desempeño");
        nivelDesempenio.setId(pos);
        NivelDesempenioEntity objNivelDesempenio = null;
        if(this.listaNivelDesempenio.add(nivelDesempenio)){
            objNivelDesempenio = nivelDesempenio;
            pos++;
        }
        return objNivelDesempenio;
    }

    public NivelDesempenioEntity update(Integer idNivelDesempenio, NivelDesempenioEntity nivelDesempenio){
        System.out.println("Invocando a Actualizar Nivel de desempeño...");
        NivelDesempenioEntity objNivelDesempenio = null;
        for(int i = 0; i < this.listaNivelDesempenio.size(); i++){
            if(this.listaNivelDesempenio.get(i).getId() == idNivelDesempenio){
                this.listaNivelDesempenio.set(i, nivelDesempenio);
                objNivelDesempenio = nivelDesempenio;
                break;
            }
        }
        return objNivelDesempenio;
    }

    public boolean deleteById(Integer idNivelDesempenio) {

        System.out.println("Invocando a eliminar Nivel de desempeño...");
        boolean bandera = false;

        for (int i = 0; i < this.listaNivelDesempenio.size(); i++) {
            if (this.listaNivelDesempenio.get(i).getId() == idNivelDesempenio) {
                this.listaNivelDesempenio.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }    
}
