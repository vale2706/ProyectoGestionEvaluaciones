package co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.repositories;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;
import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocentePK;

@Repository
public class Asig_Com_DocenteRepository {

    private List<Asig_Com_DocenteEntity> listaRelacionAsig_Com_Docente;

    public Asig_Com_DocenteRepository() {
        this.listaRelacionAsig_Com_Docente = new ArrayList<>();
    }

    // Obtener todas las relaciones
    public List<Asig_Com_DocenteEntity> findAll() {
        System.out.println("Invocando a listar todas las relaciones...");
        return new ArrayList<>(this.listaRelacionAsig_Com_Docente); // Retornamos una copia para evitar modificaciones externas
    }

    // Buscar relación por clave primaria compuesta
    public Optional<Asig_Com_DocenteEntity> findById(Asig_Com_DocentePK id) {
        System.out.println("Invocando a listar relación por ID...");
        return this.listaRelacionAsig_Com_Docente.stream()
                .filter(relacion -> relacion.getId().equals(id))
                .findFirst();
    }

    // Guardar una nueva relación
    public Asig_Com_DocenteEntity save(Asig_Com_DocenteEntity relacion) {
        System.out.println("Invocando a guardar relación...");
        // Verificamos si ya existe
        if (findById(relacion.getId()).isPresent()) {
            throw new IllegalArgumentException("La relación con este ID ya existe: " + relacion.getId());
        }
        this.listaRelacionAsig_Com_Docente.add(relacion);
        return relacion;
    }

    // Actualizar una relación existente
    public Optional<Asig_Com_DocenteEntity> update(Asig_Com_DocentePK id, Asig_Com_DocenteEntity nuevaRelacion) {
        System.out.println("Invocando a actualizar relación...");
        Optional<Asig_Com_DocenteEntity> existente = findById(id);

        existente.ifPresent(relacion -> {
            this.listaRelacionAsig_Com_Docente.remove(relacion);
            this.listaRelacionAsig_Com_Docente.add(nuevaRelacion);
        });

        return existente.isPresent() ? Optional.of(nuevaRelacion) : Optional.empty();
    }

    // Eliminar una relación por clave primaria compuesta
    public boolean deleteById(Asig_Com_DocentePK id) {
        System.out.println("Invocando a eliminar relación...");
        return this.listaRelacionAsig_Com_Docente.removeIf(relacion -> relacion.getId().equals(id));
    }
}
