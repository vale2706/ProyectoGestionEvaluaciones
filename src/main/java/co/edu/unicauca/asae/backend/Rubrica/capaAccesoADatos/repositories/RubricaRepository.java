package co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.Rubrica.capaAccesoADatos.models.RubricaEntity;


@Repository
public interface RubricaRepository extends JpaRepository<RubricaEntity, Integer> {

}
