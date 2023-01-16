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
@Table(name = "TP_TypeMaintenance")
public class TypeMaintenance implements Serializable {
    private static final long serialVersionUID = 3606820922208657250L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TyM_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "TyM_code")
    private String code;

    @Size(max = 100)
    @Column(name = "TyM_libelle")
    private String libelle;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeMaintenance typeMaintenance = (TypeMaintenance) o;
        return getId().equals(typeMaintenance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
