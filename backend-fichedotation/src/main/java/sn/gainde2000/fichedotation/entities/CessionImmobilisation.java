package sn.gainde2000.fichedotation.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TD_CesionImmobilisation")
public class CessionImmobilisation implements Serializable {
    private static final long serialVersionUID = 3606820922208657247L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CeI_id", updatable = false, nullable = false)
    private Integer id;
    @Column(name = "CeI_date")
    private Date date;

    @Column(name = "CeI_prix")
    private String prix;
    @Size(max = 100)
    @Column(name = "CeI_observation")
    private String observation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Ces_Imm_id")
    private Immobilisation immobilisation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Ces_TyC_id")
    private TypeCession typeCession;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CessionImmobilisation cession = (CessionImmobilisation) o;
        return getId().equals(cession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
