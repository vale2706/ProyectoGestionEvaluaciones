package co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.NivelDesempenio.capaAccesoADatos.models.NivelDesempenioEntity;

@Repository
public interface NivelDesempenioRepository extends JpaRepository<NivelDesempenioEntity, Integer> {

}
