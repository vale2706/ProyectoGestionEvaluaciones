package co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocentePK;
import co.edu.unicauca.asae.backend.Asig_Com_Docente.fachadaServices.DTO.Asig_Com_DocenteDTO;

public interface IAsig_Com_DocenteService {
    public List<Asig_Com_DocenteDTO> findAll();
    public Asig_Com_DocenteDTO findById(Asig_Com_DocentePK id);
    public Asig_Com_DocenteDTO save(Asig_Com_DocenteDTO relacion);
    public Asig_Com_DocenteDTO update(Asig_Com_DocentePK id, Asig_Com_DocenteDTO relacion);
    public boolean delete(Asig_Com_DocentePK id);

    
}

