package co.edu.unicauca.asae.backend.competenciasDePrograma.capaAccesoADatos.models;

import java.util.List;

import co.edu.unicauca.asae.backend.Asig_Com_Docente.capaAccesoADatos.models.Asig_Com_DocenteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Competencia")
public class CompetenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComp;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Nivel nivel;
    @Column(name = "tbl_comp_id")
    private Integer tblCompId;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Tipo tipo;
    private String descripcion;


    // Relación de uno a muchos con Asig_Com_DocenteEntity
    @OneToMany(mappedBy = "competencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Asig_Com_DocenteEntity> asignaciones;
    
    public enum Tipo{
        Programa,
        Asignatura 
    }

    public enum Nivel{
        Basico,
        Intermedio,
        Avanzado
    }

    public CompetenciaEntity(){

    }

            
    //}
}
