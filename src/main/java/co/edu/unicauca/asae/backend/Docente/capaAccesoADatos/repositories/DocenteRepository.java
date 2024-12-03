package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models.DocenteEntity;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteEntity, Integer> {

    // Aquí se pueden agregar métodos personalizados si es necesario
    // Ejemplo de un método de búsqueda personalizada
    // Optional<DocenteEntity> findByNombreCompleto(String nombreCompleto);
}
