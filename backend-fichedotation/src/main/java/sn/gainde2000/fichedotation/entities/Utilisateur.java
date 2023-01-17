/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :17/02/2022
 */

package sn.gainde2000.fichedotation.entities;


import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import sn.gainde2000.fichedotation.entities.audit.Auditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TD_Utilisateur")
@SQLDelete(sql = "update TD_Utilisateur set Uti_is_deleted = true where Uti_id =?")
@Where(clause = "Uti_is_deleted = false")

public class Utilisateur extends Auditable<Integer> implements Serializable {
    private static final long serialVersionUID = 2031298762628135741L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Uti_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "Uti_nom")
    private String nom;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "Uti_prenom", nullable = false)
    private String prenom;

    @Size(max = 30)
    @Column(name = "Uti_email")
    private String email;

    @Column(name = "Uti_password")
    private String password;

    @Column(name = "Uti_first_log", columnDefinition = "boolean default true")
    private Boolean firstLog = true;

    @Column(name = "Uti_status", columnDefinition = "boolean default true")
    private Boolean status;

    @Column(name = "Uti_is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleteted = Boolean.FALSE;

    @Size(max = 50)
    @Column(name = "Uti_telephone")
    private String telephone;

    @Column(name = "Uti_adresse")
    private String adresse;

   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Uti_Pro_id")
    private Profil linkedProfil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Utilisateur that = (Utilisateur) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }

    @PrePersist
    void prePersist() {
        this.setIsDeleteted(false);
    }
}
