package co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Entidades.ERole;
import co.edu.unicauca.asae.backend.configuracionSeguridad.capaAccesoADatos.Entidades.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
