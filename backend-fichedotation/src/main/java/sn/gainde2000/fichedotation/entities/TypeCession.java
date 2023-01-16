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
@Table(name = "TP_TypeCession")
public class TypeCession implements Serializable {

    private static final long serialVersionUID = 3606820922208657246L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TyC_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "TyC_code")
    private String code;

    @Size(max = 100)
    @Column(name = "TyC_libelle")
    private String libelle;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeCession typeCession = (TypeCession) o;
        return getId().equals(typeCession.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
