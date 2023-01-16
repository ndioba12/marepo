package sn.gainde2000.fichedotation.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TD_HistoriqueLocalisation")
public class HistoriqueLocalisation {
    private static final long serialVersionUID = 3606820922208657248L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "HiL_id", updatable = false, nullable = false)
    private Integer id;
    @Size(max = 10)
    @Column(name = "HiL_localisation")
    private String localisation;
    @Column(name = "HiL_dateAffectation")
    private Date dateAffectation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Ces_TyC_id")
    private Immobilisation immobilisation;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoriqueLocalisation historiqueLocalisation = (HistoriqueLocalisation) o;
        return getId().equals(historiqueLocalisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
