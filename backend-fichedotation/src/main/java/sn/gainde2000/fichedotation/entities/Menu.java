/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.entities;

import lombok.*;

import javax.persistence.*;
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
@Table(name = "TP_Menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 5134423900231610029L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Men_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "Men_path")
    private String path;

    @Column(name = "Men_title")
    private String title;

    @Column(name = "Men_type")
    private String type;

    @Column(name = "Men_icon_type")
    private String iconType;

    @Column(name = "Men_collapse")
    private String collapse;

    @Column(name = "Men_ab")
    private String ab;

    @Column(name = "Men_priorite")
    private Integer priorite;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Tr_Men_Sous_menu",
            joinColumns = @JoinColumn(name = "Men_id"),
            inverseJoinColumns = @JoinColumn(name = "Smn_id"))
    private Set<Menu> children = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return getId().equals(menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

