package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories.CompetenciaRepository;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

@Service
public class CompetenciaServicesImpl implements ICompetenciaServices {

     @Autowired
    private CompetenciaRepository competenciaRepository;

    public List<CompetenciaEntity> findAll() {
        return competenciaRepository.findAll();
    }

    public Optional<CompetenciaEntity> findById(Integer id) {
        return competenciaRepository.findById(id);
    }

    public CompetenciaEntity save(CompetenciaEntity competencia) {
        return competenciaRepository.save(competencia);
    }

    public CompetenciaEntity update(Integer id, CompetenciaEntity competencia) {
        Optional<CompetenciaEntity> existingCompetencia = competenciaRepository.findById(id);
        if (existingCompetencia.isPresent()) {
            CompetenciaEntity updatedCompetencia = existingCompetencia.get();
            updatedCompetencia.setNombre(competencia.getNombre());
            // Actualiza otros campos si es necesario...
            return competenciaRepository.save(updatedCompetencia);
        }
        return null; // Maneja este caso según tu lógica (ej. lanza una excepción o devuelve un error).
    }

    public boolean deleteById(Integer id) {
        if (competenciaRepository.existsById(id)) {
            competenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
