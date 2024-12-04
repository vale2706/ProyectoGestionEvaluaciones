package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories;

import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaEntity, Integer>{

    @Query("SELECT c FROM CompetenciaEntity c LEFT JOIN FETCH c.resultadosAprendizajes")
    List<CompetenciaEntity> findAllWithResultados();
}
