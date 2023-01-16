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
@Table(name = "TP_TypeImmobilisation")
public class TypeImmobilisation implements Serializable {
    private static final long serialVersionUID = 3606820922208657245L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TyI_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "TyI_code")
    private String code;

    @Size(max = 100)
    @Column(name = "TyI_libelle")
    private String libelle;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TyI_Cat_id")
    private CatImmobilisation catImmobilisation;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeImmobilisation typeImmobilisation = (TypeImmobilisation) o;
        return getId().equals(typeImmobilisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
