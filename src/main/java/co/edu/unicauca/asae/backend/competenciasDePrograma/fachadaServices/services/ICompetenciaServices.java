package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;

import java.util.List;

public interface ICompetenciaServices {
    public List<CompetenciaDTO> findAll();
    public CompetenciaDTO findById(Integer idComp);
    public CompetenciaDTO save(CompetenciaDTO comp);
    public List<ResultadosAprendizajeDTO> findRAsByCompetenciaProgramaId(Integer idComp);
    public CompetenciaDTO update(Integer idComp, CompetenciaDTO comp);
    public boolean delete(Integer idComp);
    void vincularResultadoAprendizajeACompetencia(Integer competenciaId, Integer raId);
    public CompetenciaDTO findByIdWithResultadosAprendizajes(Integer idComp);
}
