package co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.services;

import co.edu.unicauca.asae.backend.NivelDesempenio.fachadaServices.DTO.NivelDesempenioDTO;
import java.util.List;

public interface INivelDesempenioService {
    public List<NivelDesempenioDTO> findAll();
    public NivelDesempenioDTO findById(Integer idNivelD);
    public NivelDesempenioDTO save(NivelDesempenioDTO nivelD);
    public NivelDesempenioDTO update(Integer idNivelD, NivelDesempenioDTO nivelD);
    public boolean delete(Integer idNivelD);
}
