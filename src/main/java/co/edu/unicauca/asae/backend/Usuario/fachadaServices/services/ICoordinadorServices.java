package co.edu.unicauca.asae.backend.Usuario.fachadaServices.services;
import co.edu.unicauca.asae.backend.Usuario.fachadaServices.DTO.CoordinadorDTO;

import java.util.List;

public interface ICoordinadorServices {
    public List<CoordinadorDTO> findAll();
    public CoordinadorDTO findById(Integer idCoordi);
    public CoordinadorDTO save(CoordinadorDTO Coordi);
    public CoordinadorDTO update(Integer idCoordi, CoordinadorDTO Coordi);
    public boolean delete(Integer idCoordi);
}
