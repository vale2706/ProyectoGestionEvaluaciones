package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models.CompetenciaEntity;

@Repository
public interface CompetenciaRepository extends JpaRepository<CompetenciaEntity, Integer> {
    boolean existsById(Integer id);

    @Query("SELECT c FROM CompetenciaEntity c LEFT JOIN FETCH c.resultadosAprendizaje WHERE c.idComp = :idComp")
    CompetenciaEntity findByCompetenciaIdWithResultadosAprendizajes(@Param("idComp") Integer idComp);
}
