package co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.repositories;

import co.edu.unicauca.asae.backend.Asignatura.capaAccesoADatos.models.AsignaturaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, Integer> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}