package co.edu.unicauca.asae.backend.Docente.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;

public interface IDocenteServices {
    public List<DocenteDTO> findAll();
    public DocenteDTO findById(Integer idDocente);
    public DocenteDTO save(DocenteDTO docente);
    public DocenteDTO update(Integer idDocente, DocenteDTO docente);
    public boolean delete(Integer idDocente);
}
