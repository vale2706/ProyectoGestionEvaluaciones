package co.edu.unicauca.asae.backend.Rubrica.fachadaServices.services;

import co.edu.unicauca.asae.backend.Rubrica.fachadaServices.DTO.RubricaDTO;
import java.util.List;

public interface IRubricaService {
    public List<RubricaDTO> findAll();
    public RubricaDTO findById(Integer idNivelD);
    public RubricaDTO save(RubricaDTO nivelD);
    public RubricaDTO update(Integer idNivelD, RubricaDTO nivelD);
    public boolean delete(Integer idNivelD);
}
