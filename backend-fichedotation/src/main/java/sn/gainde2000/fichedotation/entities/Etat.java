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
@Table(name = "TP_Etat")
public class Etat implements Serializable {
    private static final long serialVersionUID = 3606820922208657277L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Eta_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "Eta_code")
    private String code;

    @Size(max = 100)
    @Column(name = "Eta_libelle")
    private String libelle;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etat etat = (Etat) o;
        return getId().equals(etat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
