package co.edu.unicauca.asae.backend.Docente.capaAccesoADatos.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "Docente")
public class DocenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
