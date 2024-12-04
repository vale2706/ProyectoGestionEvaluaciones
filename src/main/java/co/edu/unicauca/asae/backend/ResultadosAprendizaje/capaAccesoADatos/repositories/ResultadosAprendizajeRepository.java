package co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.ResultadosAprendizaje.capaAccesoADatos.models.ResultadosAprendizajeEntity;

@Repository
public interface ResultadosAprendizajeRepository extends JpaRepository<ResultadosAprendizajeEntity, Integer> {

}