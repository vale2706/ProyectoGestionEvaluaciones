package co.edu.unicauca.asae.backend.Usuario.fachadaServices.services;
import co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO.DocenteDTO;

import java.util.List;

public interface IDocenteServices {
    public List<DocenteDTO> findAll();
    public DocenteDTO findById(Integer idCoordi);
    public DocenteDTO save(DocenteDTO Coordi);
    public DocenteDTO update(Integer idCoordi, DocenteDTO Coordi);
    public boolean delete(Integer idCoordi);
}
