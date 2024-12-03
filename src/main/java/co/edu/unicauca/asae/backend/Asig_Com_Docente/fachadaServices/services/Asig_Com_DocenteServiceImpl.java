package co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.DTO.Asig_Com_DocenteDTO;
import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocentePK;

@Service
public class Asig_Com_DocenteServiceImpl implements IAsig_Com_DocenteService {

    private List<Asig_Com_DocenteDTO> listaRelaciones = new ArrayList<>();

    @Override
    public List<Asig_Com_DocenteDTO> findAll() {
        return listaRelaciones;
    }
    @Override
    public Asig_Com_DocenteDTO findById(Asig_Com_DocentePK id) {
        return listaRelaciones.stream()
                .filter(rel -> rel.getId().equals(id)) 
                .findFirst()
                .orElse(null);
    }

    @Override
    public Asig_Com_DocenteDTO save(Asig_Com_DocenteDTO relacion) {
        listaRelaciones.add(relacion);
        return relacion;
    }

    @Override
    public Asig_Com_DocenteDTO update(Asig_Com_DocentePK id, Asig_Com_DocenteDTO relacion) {
        Asig_Com_DocenteDTO existingRelacion = findById(id);
        if (existingRelacion != null) {
            listaRelaciones.remove(existingRelacion);
            listaRelaciones.add(relacion);
            return relacion;
        }
        return null;
    }

    @Override
    public boolean delete(Asig_Com_DocentePK id) {
        Asig_Com_DocenteDTO relacion = findById(id);
        if (relacion != null) {
            listaRelaciones.remove(relacion);
            return true;
        }
        return false;
    }
}
