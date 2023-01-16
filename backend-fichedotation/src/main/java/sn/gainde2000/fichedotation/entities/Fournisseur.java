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
@Table(name = "TP_Fournisseur")
public class Fournisseur implements Serializable {
    private static final long serialVersionUID = 3606820922208657251L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Fou_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "Fou_nomOuRaiSociale")
    private String nomOuRaiSociale;
    @Size(max = 100)
    @Column(name = "Fou_adresse")
    private String adresse;
    @Size(max = 50)
    @Column(name = "Fou_telephoneFixe")
    private String telephoneFixe;
    @Size(max = 50)
    @Column(name = "Fou_email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fournisseur fournisseur = (Fournisseur) o;
        return getId().equals(fournisseur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
