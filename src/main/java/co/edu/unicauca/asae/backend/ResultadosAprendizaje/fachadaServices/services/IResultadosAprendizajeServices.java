package co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.services;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

import java.util.List;

public interface IResultadosAprendizajeServices {
    List<ResultadosAprendizajeDTO> findAll();
    ResultadosAprendizajeDTO findById(Integer idRa);
    ResultadosAprendizajeDTO save(ResultadosAprendizajeDTO ra);
    ResultadosAprendizajeDTO update(Integer idRa, ResultadosAprendizajeDTO ra);
    boolean delete(Integer idRa);
}
