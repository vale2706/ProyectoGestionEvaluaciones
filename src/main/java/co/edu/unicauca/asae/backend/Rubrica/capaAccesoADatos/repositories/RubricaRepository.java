package co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;


@Repository
public class RubricaRepository {
    private int pos;
    private ArrayList<RubricaEntity> listarRubrica;

    public RubricaRepository(){
        this.listarRubrica = new ArrayList<RubricaEntity>();
        this.pos = this.listarRubrica.size() + 1;
    }
    
    //Obtener todas las Rubrica
    public ArrayList<RubricaEntity> findAll(){
    System.out.println("Invocando a listar rubricas...");
    return this.listarRubrica;
    }

    public boolean existeRubrica(Integer idRubrica){
        System.out.println("Consultando si existe rubrica con id.");
        boolean bandera = false;
        for(RubricaEntity rubrica : listarRubrica){
            System.out.println("El rubrica es: " + rubrica.getNomDescriptivo());
            bandera = true;
            break;
        }
        return bandera;
    }

    //Buscar rubrica por ID
    public RubricaEntity findById(Integer idRubrica){
        System.out.println("Invocando a listar rubricas...");
        RubricaEntity objRubrica = null;
        for(RubricaEntity rubrica : listarRubrica){
            if(rubrica.getId() == idRubrica){
                objRubrica = rubrica;
                break;
            }
        }
        return objRubrica;
    }

    public RubricaEntity save(RubricaEntity rubrica){
        System.out.println("Invocando a guardar rubrica");
        rubrica.setId(pos);
        RubricaEntity objRubrica = null;
        if(this.listarRubrica.add(rubrica)){
            objRubrica = rubrica;
            pos++;
        }
        return objRubrica;
    }

    public RubricaEntity update(Integer idRubrica, RubricaEntity rubrica){
        System.out.println("Invocando a Actualizar rubrica...");
        RubricaEntity objRubrica = null;
        for(int i = 0; i < this.listarRubrica.size(); i++){
            if(this.listarRubrica.get(i).getId() == idRubrica){
                this.listarRubrica.set(i, rubrica);
                objRubrica = rubrica;
                break;
            }
        }
        return objRubrica;
    }

    public boolean deleteById(Integer idRubrica) {

        System.out.println("Invocando a eliminar rubrica...");
        boolean bandera = false;

        for (int i = 0; i < this.listarRubrica.size(); i++) {
            if (this.listarRubrica.get(i).getId() == idRubrica) {
                this.listarRubrica.remove(i);
                bandera = true;
                break;
            }
        }
        return bandera;
    }    
}
