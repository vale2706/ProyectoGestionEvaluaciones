package co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity.Tipo;
import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories.CompetenciaRepository;
import co.edu.unicauca.asae.backend.competenciasDePrograma.fachadaServices.DTO.CompetenciaDTO;
import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.repositories.ResultadosAprendizajeRepository;
import co.edu.unicauca.asae.backend.ResultadosAprendizaje.fachadaServices.DTO.ResultadosAprendizajeDTO;

@Service
public class CompetenciaServicesImpl implements ICompetenciaServices {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private ResultadosAprendizajeRepository raRepository;

    @Autowired
    private ModelMapper modelMapper; // Para mapear entre Entity y DTO

    @Override
    public List<CompetenciaDTO> findAll() {
        List<CompetenciaEntity> competenciaEntities = competenciaRepository.findAll();
        return competenciaEntities.stream()
                .map(competencia -> modelMapper.map(competencia, CompetenciaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompetenciaDTO findById(Integer idComp) {
        Optional<CompetenciaEntity> competenciaEntity = competenciaRepository.findById(idComp);
        if (competenciaEntity.isPresent()) {
            return modelMapper.map(competenciaEntity.get(), CompetenciaDTO.class);
        } else {
            throw new EntidadNoExisteException("Competencia no encontrada con el id: " + idComp);
        }
    }

    @Override
    public CompetenciaDTO save(CompetenciaDTO compDTO) {
        // Convierte el DTO a entidad
        CompetenciaEntity competenciaEntity = modelMapper.map(compDTO, CompetenciaEntity.class);
        CompetenciaEntity savedEntity = competenciaRepository.save(competenciaEntity);
        // Convierte la entidad guardada de nuevo a DTO
        return modelMapper.map(savedEntity, CompetenciaDTO.class);
    }

    @Override
    public CompetenciaDTO update(Integer idComp, CompetenciaDTO compDTO) {
        Optional<CompetenciaEntity> existingCompetencia = competenciaRepository.findById(idComp);
        // Si la competencia existe, la actualizamos
        if (existingCompetencia.isPresent()) {
            CompetenciaEntity updatedCompetencia = existingCompetencia.get();
            // validacion: si el no es nulo lo actualiza sino toma el valor por defecto que
            // es basico
            if (compDTO.getNivel() != null) {
                updatedCompetencia.setNivel(CompetenciaEntity.Nivel.valueOf(compDTO.getNivel().name()));
            } else {
                updatedCompetencia.setNivel(CompetenciaEntity.Nivel.Basico);
            }
            updatedCompetencia.setDescripcion(compDTO.getDescripcion());
            // Para manejar las listas correctamente se convierten a entidades y
            // luego se agrega a la entidad principal
            // List<ResultadosAprendizajeEntity> newResultados =
            // compDTO.getResultadosAprendizajes().stream()
            // .map(dto -> modelMapper.map(dto, ResultadosAprendizajeEntity.class)) //
            // Convertir DTOs a entidades
            // .collect(Collectors.toList());
            // updatedCompetencia.setResultadosAprendizajes(newResultados);
            List<Asig_Com_DocenteEntity> newRelaciones = compDTO.getRelaciones().stream()
                    .map(dto -> modelMapper.map(dto, Asig_Com_DocenteEntity.class)) // Convertir DTOs a entidades
                    .collect(Collectors.toList());
            updatedCompetencia.setAsignaciones(newRelaciones);

            // Guardamos la competencia actualizada en la base de datos
            CompetenciaEntity savedEntity = competenciaRepository.save(updatedCompetencia);

            // Convertimos la entidad actualizada de nuevo a DTO y la retornamos
            return modelMapper.map(savedEntity, CompetenciaDTO.class);
        }
        // Si la competencia no existe, lanzamos una excepción
        throw new EntidadNoExisteException("Competencia no encontrada para actualizar con el id: " + idComp);
    }

    @Override
    public boolean delete(Integer idComp) {
        if (competenciaRepository.existsById(idComp)) {
            competenciaRepository.deleteById(idComp);
            return true;
        }
        return false;
    }

    @Override
    public List<ResultadosAprendizajeDTO> findRAsByCompetenciaProgramaId(Integer idComp) {

        Optional<CompetenciaEntity> competencia = competenciaRepository.findById(idComp);
        // Validar que exista y que sea de tipo "programa"
        if (competencia.isPresent() && competencia.get().getTipo().equals(Tipo.Programa)) {
            // Código que se ejecuta si ambas condiciones son verdaderas
           // return raRepository.findByidComp(idComp);
        }

        // Si no existe o no es de tipo "programa", lanzar una excepción o retornar o retornar lista vacia
        throw new EntidadNoExisteException("La competencia no existe o no es de tipo programa.");

    }

    // Implementación en CompetenciaServicesImpl.java
    @Override
    public void vincularResultadoAprendizajeACompetencia(Integer competenciaId, Integer raId) {
        CompetenciaEntity competencia = competenciaRepository.findById(competenciaId)
                .orElseThrow(() -> new EntidadNoExisteException("Competencia no encontrada"));

        ResultadosAprendizajeEntity ra = raRepository.findById(raId)
                .orElseThrow(() -> new EntidadNoExisteException("Resultado de Aprendizaje no encontrado"));

        ra.setCompetencia(competencia);
        competenciaRepository.save(competencia);
        raRepository.save(ra);
    }

    public CompetenciaDTO findByIdWithResultadosAprendizajes(Integer idComp) {
        CompetenciaEntity competenciaEntity = competenciaRepository.findByCompetenciaIdWithResultadosAprendizajes(idComp);
        if (competenciaEntity != null) {
            return modelMapper.map(competenciaEntity, CompetenciaDTO.class);
        } else {
            throw new EntidadNoExisteException("Competencia no encontrada con el id: " + idComp);
        }
    }
}
