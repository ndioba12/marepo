package sn.gainde2000.fichedotation.entities;

import lombok.*;
import sn.gainde2000.fichedotation.entities.audit.Auditable;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//import java.util.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TD_Immobilisation")
public class Immobilisation extends Auditable<Integer> implements Serializable {
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

    @Nullable
    @Column(name = "Imm_localisationGeo")
    private String localisationGeo;

    @Column(name = "Imm_estAffecte", columnDefinition = "boolean default false")
    private Boolean estAffecte = false;

    @Size(max = 100)
    @Column(name = "Imm_adresseMac")
    private String adresseMac;

    @Column(name = "Imm_dureeGarantie")
    private Integer dureeGarantie;

    @Column(name = "Imm_valeurNette")
    private Double valeurNette;

    @Size(max = 255)
    @Column(name = "Imm_marque")
    private String marque;
    @Size(max = 255)
    @Column(name = "Imm_categorie")
    private String categorie;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_TyI_id")
    private TypeImmobilisation typeImmobilisation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Imm_Sta_id")
    private Statut statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Imm_Eta_id")
    private Etat etat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_TyM_id")
    private TypeMaintenance typeMaintenance;
    @Size(max = 255)
    @Column(name = "Imm_fournisseur")
    private String fournisseur;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Imm_Cat_id")
    private CatImmobilisation catImmobilisation;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Tr_Immobilisation_Utilisateur",
            joinColumns = @JoinColumn(name = "Imm_id"),
            inverseJoinColumns = @JoinColumn(name = "Uti_id"))
    private Set<Utilisateur> users = new HashSet<>();

    @OneToMany(mappedBy = "immobilisation")
    private Set<Effectuer> effectuer= new HashSet<Effectuer>();


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

