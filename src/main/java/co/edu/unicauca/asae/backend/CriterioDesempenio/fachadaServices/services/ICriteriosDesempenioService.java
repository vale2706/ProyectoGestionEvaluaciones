package co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.services;

import co.edu.unicauca.asae.backend.CriterioDesempenio.fachadaServices.DTO.CriteriosDesempenioDTO;
import java.util.*;

public interface ICriteriosDesempenioService {
    public List<CriteriosDesempenioDTO> findAll();
    public CriteriosDesempenioDTO findById(Integer idDesempenio);
    public CriteriosDesempenioDTO save(CriteriosDesempenioDTO desempenio);
    public CriteriosDesempenioDTO update(Integer idDesempenio, CriteriosDesempenioDTO desempenio);
    public boolean delete(Integer idDesempenio);
}
