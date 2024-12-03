package co.edu.unicauca.asae.backend.Docente.fachadaServices.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.EntidadNoExisteException;
import co.edu.unicauca.asae.backend.ControladorExcepciones.excepcionesPropias.ReglaNegocioExcepcion;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models.DocenteEntity;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.repositories.DocenteRepository;
import co.edu.unicauca.asae.backend.Docente.fachadaServices.DTO.DocenteDTO;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios.UserRepository;

@Service
public class DocenteServicesImpl implements IDocenteServices {

    private DocenteRepository docenteRepository;
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public DocenteServicesImpl(DocenteRepository docenteRepository, ModelMapper modelMapper) {
        this.docenteRepository = docenteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DocenteDTO> findAll() {
        List<DocenteEntity> listaDocente = this.docenteRepository.findAll();
        return listaDocente.stream()
                .map(entity -> this.modelMapper.map(entity, DocenteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocenteDTO findById(Integer idDocente) {
        DocenteEntity docenteEntity = this.docenteRepository.findById(idDocente).orElse(null);
        if (docenteEntity == null) {
            throw new EntidadNoExisteException("Error, el Docente con id " + idDocente + " no existe");
        }
        return this.modelMapper.map(docenteEntity, DocenteDTO.class);
    }

    @Override
    public DocenteDTO save(DocenteDTO docenteDTO) {
        // Verificar si el correo ya existe
        if (userRepository.existsByEmail(docenteDTO.getEmail())) {
            throw new ReglaNegocioExcepcion("Ya existe un Docente con ese correo, no se permite crear Docente");
        }

        // Convertir el DTO a entidad
        DocenteEntity docenteEntity = this.modelMapper.map(docenteDTO, DocenteEntity.class);
        // Guardar en la base de datos
        DocenteEntity docenteEntityGuardado = this.docenteRepository.save(docenteEntity);
        // Convertir la entidad guardada en DTO
        return this.modelMapper.map(docenteEntityGuardado, DocenteDTO.class);
    }

    @Override
    public DocenteDTO update(Integer idDocente, DocenteDTO docenteDTO) {
        // Verificar si el docente existe
        DocenteEntity existingDocente = this.docenteRepository.findById(idDocente).orElse(null);
        if (existingDocente == null) {
            throw new EntidadNoExisteException("Error, el Docente con id " + idDocente + " no existe");
        }

        // Verificar si el correo ya está registrado
        if (userRepository.existsByEmail(docenteDTO.getEmail()) && !existingDocente.getEmail().equals(docenteDTO.getEmail())) {
            throw new ReglaNegocioExcepcion("Ya existe un Docente con ese correo, no se permite actualizar");
        }

        // Convertir el DTO a entidad
        DocenteEntity docenteEntity = this.modelMapper.map(docenteDTO, DocenteEntity.class);
        // Establecer el ID para la actualización
        docenteEntity.setId(idDocente);

        // Guardar el docente actualizado
        DocenteEntity docenteEntityActualizado = this.docenteRepository.save(docenteEntity);

        return this.modelMapper.map(docenteEntityActualizado, DocenteDTO.class);
    }

    @Override
    public boolean delete(Integer idDocente) {
        // Verificar si el docente existe
        if (!this.docenteRepository.existsById(idDocente)) {
            throw new EntidadNoExisteException("Error, el Docente con id " + idDocente + " no existe");
        }

        // Eliminar el docente
        this.docenteRepository.deleteById(idDocente);
        return true;
    }
}
