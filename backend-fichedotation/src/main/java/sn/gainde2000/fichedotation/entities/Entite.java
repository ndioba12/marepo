package sn.gainde2000.fichedotation.entities;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Tag(name = "Entite", description = "Cette table renvoie à l'entité ou au département auquel appartient un utilisateur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TP_Entite")
public class Entite implements Serializable {
    private static final long serialVersionUID = 3606820922208657243L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Ent_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "Ent_code")
    private String code;

    @Size(max = 100)
    @Column(name = "Ent_libelle")
    private String libelle;

    public Entite(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return getId().equals(entite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
