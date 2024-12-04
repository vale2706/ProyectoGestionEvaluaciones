package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Docente",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" })
    })
public class DocenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoId tipoId;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoDocente tipoDocente;
    private String tituloDocente;
    private String nombreCompleto;
    private String email;

    // Relación de uno a muchos
    @OneToMany(mappedBy = "docente")
    private List<Asig_Com_DocenteEntity> asignaciones;

    public DocenteEntity() {
    }

    public enum TipoId {
        CC,
        CE
    }

    public enum TipoDocente {
        Catedra,
        TCompleto,
        Planta
    }
}
