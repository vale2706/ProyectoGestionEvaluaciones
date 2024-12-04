package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;

@Repository
public interface CriteriosDesempenioRepository extends JpaRepository<CriteriosDesempenioEntity, Integer> {
    @Query("SELECT c FROM CriteriosDesempenioEntity c JOIN c.nivelDesemp n WHERE n.id = :idNivel")
    List<CriteriosDesempenioEntity> findByNivelDesempenio(@Param("idNivel") Long idNivel);

}
