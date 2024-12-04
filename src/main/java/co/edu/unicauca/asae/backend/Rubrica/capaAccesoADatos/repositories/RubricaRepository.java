package co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;


@Repository
public interface RubricaRepository extends JpaRepository<RubricaEntity, Integer> {
    // Consulta personalizada para obtener el INNER JOIN entre Rubrica, CriterioDesempenio y NivelDesempenio
    //@Query("SELECT r FROM RubricaEntity r JOIN r.criterioDesempenio c JOIN c.nivelDesemp n")
    //List<RubricaEntity> findRubricaWithCriterioAndNivel();

    // Consulta personalizada para obtener un registro específico por ID
    @Query("SELECT r FROM RubricaEntity r JOIN r.criterioDesempenio c JOIN c.nivelDesemp n WHERE r.id = :idRubrica")
    RubricaEntity findRubricaWithCriterioAndNivelById(Integer idRubrica);

}
