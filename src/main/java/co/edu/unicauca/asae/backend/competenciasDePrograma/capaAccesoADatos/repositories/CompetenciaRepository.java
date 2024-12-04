package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaEntity, Integer> {
    boolean existsById(Integer id);

}
