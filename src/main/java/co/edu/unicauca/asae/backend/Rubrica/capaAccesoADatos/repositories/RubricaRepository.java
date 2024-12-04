package co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;


@Repository
public interface RubricaRepository extends JpaRepository<RubricaEntity, Integer> {

    @Query("SELECT r FROM RubricaEntity r " +
            "LEFT JOIN FETCH r.criterioDesempenio cd " +
            "LEFT JOIN FETCH cd.nivelDesempenio nd " +
            "WHERE r.id = :rubricaId")
    RubricaEntity findRubricaWithDetails(@Param("id") Integer rubricaId);
}
