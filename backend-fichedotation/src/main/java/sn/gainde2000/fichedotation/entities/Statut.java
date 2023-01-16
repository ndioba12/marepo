package sn.gainde2000.fichedotation.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TP_Statut")
public class Statut implements Serializable {
    private static final long serialVersionUID = 3606820922208657249L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Sta_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "Sta_code")
    private String code;

    @Size(max = 100)
    @Column(name = "Sta_libelle")
    private String libelle;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Immobilisation immobilisation = (Immobilisation) o;
        return getId().equals(immobilisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
