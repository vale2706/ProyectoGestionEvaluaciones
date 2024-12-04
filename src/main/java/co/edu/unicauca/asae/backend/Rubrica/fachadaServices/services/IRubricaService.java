package co.edu.unicauca.asae.backend.Rubrica.fachadaServices.services;

import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.repositories.RubricaRepository;
import co.edu.unicauca.asae.backend.Rubrica.fachadaServices.DTO.RubricaDTO;
import java.util.List;

public interface IRubricaService {
    public List<RubricaDTO> findAll();
    public RubricaDTO findById(Integer idRubrica);
    public RubricaDTO save(RubricaDTO rubricaDTO);
    public RubricaDTO update(Integer idRubrica, RubricaDTO rubricaDTO);
    public boolean delete(Integer idRubrica);
    public RubricaDTO findRubricaWithDetails(Integer idRubrica);
    void vincularRubricaCriterioNivel(Integer idRubrica, Integer idNivelD, Integer idCriterio);
}
