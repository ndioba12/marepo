/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

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
@Table(name = "TP_Marque")
public class Marque implements Serializable {
    private static final long serialVersionUID = 3606820922208657242L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Mar_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "Mar_code")
    private String code;

    @Size(max = 100)
    @Column(name = "Mar_libelle")
    private String libelle;

    public Marque(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return getId().equals(marque.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
