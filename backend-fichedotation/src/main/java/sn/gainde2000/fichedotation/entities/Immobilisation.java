package sn.gainde2000.fichedotation.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Table(name = "TD_Immobilisation")
public class Immobilisation implements Serializable {
    private static final long serialVersionUID = 3606820922208657243L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Imm_id", updatable = false, nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "Imm_designation")
    private String designation;

    @Size(max = 255)
    @Column(name = "Imm_description")
    private String description;
    @Size(max = 100)
    @Column(name = "Imm_modele")
    private String modele;

    @Size(max = 100)
    @Column(name = "Imm_numeroSerie")
    private String numeroSerie;

    @Size(max = 255)
    @Column(name = "Imm_accessoires")
    private String accessoires;
    @Size(max = 100)
    @Column(name = "Imm_refCommercial")
    private String refCommercial;

    @Column(name = "Imm_dateAcquisition")
    private Date dateAcquisition;
    @Column(name = "Imm_dateMiseEnService")
    private Date dateMiseEnService;

    @Column(name = "Imm_prixAcquisition")
    private Double prixAcquisition;

    @Size(max = 100)
    @Column(name = "Imm_referenceInterne")
    private String referenceInterne;

    @Size(max = 100)
    @Column(name = "Imm_adresseMac")
    private String adresseMac;

    @Column(name = "Imm_dureeGarantie")
    private Integer dureeGarantie;

    @Column(name = "Imm_valeurNette")
    private Double valeurNette;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_TyI_id")
    private TypeImmobilisation typeImmobilisation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_Sta_id")
    private Statut statut;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_Mar_id")
    private Marque marque;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_TyM_id")
    private TypeMaintenance typeMaintenance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_Fou_id")
    private Fournisseur fournisseur;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Immobilisation immobilisation = (Immobilisation) o;
        return getId().equals(immobilisation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

