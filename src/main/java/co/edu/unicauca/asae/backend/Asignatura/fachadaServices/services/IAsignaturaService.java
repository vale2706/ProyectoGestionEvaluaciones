package co.edu.unicauca.asae.backend.Asignatura.fachadaServices.services;

import co.edu.unicauca.asae.backend.Asignatura.fachadaServices.DTO.AsignaturaDTO;
import java.util.List;

public interface IAsignaturaService {
    public List<AsignaturaDTO> findAll();
    public AsignaturaDTO findById(Integer idAsignatura);
    public AsignaturaDTO save(AsignaturaDTO asignatura);
    public AsignaturaDTO update(Integer idAsignatura, AsignaturaDTO asignatura);
    public boolean delete(Integer idAsignatura);

}
