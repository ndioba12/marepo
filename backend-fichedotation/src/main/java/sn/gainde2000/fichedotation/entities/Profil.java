/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TP_Profil")
@Inheritance(strategy = InheritanceType.JOINED)

public class Profil implements Serializable {
    private static final long serialVersionUID = 3606820922208657228L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Pro_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 10)
    @Column(name = "Pro_code")
    private String code;

    @Size(max = 100)
    @Column(name = "Pro_libelle")
    private String libelle;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Tr_Profil_Menu",
            joinColumns = @JoinColumn(name = "Pro_id"),
            inverseJoinColumns = @JoinColumn(name = "Men_id"))
    private Set<Menu> menus = new HashSet<>();

    public Profil(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profil profil = (Profil) o;
        return getId().equals(profil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
