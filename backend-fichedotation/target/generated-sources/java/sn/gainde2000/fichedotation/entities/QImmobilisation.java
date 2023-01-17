package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImmobilisation is a Querydsl query type for Immobilisation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImmobilisation extends EntityPathBase<Immobilisation> {

    private static final long serialVersionUID = 549663287L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImmobilisation immobilisation = new QImmobilisation("immobilisation");

    public final StringPath accessoires = createString("accessoires");

    public final StringPath adresseMac = createString("adresseMac");

    public final DateTimePath<java.util.Date> dateAcquisition = createDateTime("dateAcquisition", java.util.Date.class);

    public final DateTimePath<java.util.Date> dateMiseEnService = createDateTime("dateMiseEnService", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath designation = createString("designation");

    public final NumberPath<Integer> dureeGarantie = createNumber("dureeGarantie", Integer.class);

    public final QFournisseur fournisseur;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMarque marque;

    public final StringPath modele = createString("modele");

    public final StringPath numeroSerie = createString("numeroSerie");

    public final NumberPath<Double> prixAcquisition = createNumber("prixAcquisition", Double.class);

    public final StringPath refCommercial = createString("refCommercial");

    public final StringPath referenceInterne = createString("referenceInterne");

    public final QStatut statut;

    public final QTypeImmobilisation typeImmobilisation;

    public final QTypeMaintenance typeMaintenance;

    public final NumberPath<Double> valeurNette = createNumber("valeurNette", Double.class);

    public QImmobilisation(String variable) {
        this(Immobilisation.class, forVariable(variable), INITS);
    }

    public QImmobilisation(Path<? extends Immobilisation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImmobilisation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImmobilisation(PathMetadata metadata, PathInits inits) {
        this(Immobilisation.class, metadata, inits);
    }

    public QImmobilisation(Class<? extends Immobilisation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fournisseur = inits.isInitialized("fournisseur") ? new QFournisseur(forProperty("fournisseur")) : null;
        this.marque = inits.isInitialized("marque") ? new QMarque(forProperty("marque")) : null;
        this.statut = inits.isInitialized("statut") ? new QStatut(forProperty("statut")) : null;
        this.typeImmobilisation = inits.isInitialized("typeImmobilisation") ? new QTypeImmobilisation(forProperty("typeImmobilisation"), inits.get("typeImmobilisation")) : null;
        this.typeMaintenance = inits.isInitialized("typeMaintenance") ? new QTypeMaintenance(forProperty("typeMaintenance")) : null;
    }

}

