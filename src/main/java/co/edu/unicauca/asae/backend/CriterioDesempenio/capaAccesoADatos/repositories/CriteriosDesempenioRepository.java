package co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.CriterioDesempenio.capaAccesoADatos.models.CriteriosDesempenioEntity;

@Repository
public interface CriteriosDesempenioRepository extends JpaRepository<CriteriosDesempenioEntity, Integer> {

}
