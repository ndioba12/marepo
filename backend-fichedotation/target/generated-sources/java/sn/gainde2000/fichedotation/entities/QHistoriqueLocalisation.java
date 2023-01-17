package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHistoriqueLocalisation is a Querydsl query type for HistoriqueLocalisation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHistoriqueLocalisation extends EntityPathBase<HistoriqueLocalisation> {

    private static final long serialVersionUID = -1232058328L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHistoriqueLocalisation historiqueLocalisation = new QHistoriqueLocalisation("historiqueLocalisation");

    public final DateTimePath<java.util.Date> dateAffectation = createDateTime("dateAffectation", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImmobilisation immobilisation;

    public final StringPath localisation = createString("localisation");

    public QHistoriqueLocalisation(String variable) {
        this(HistoriqueLocalisation.class, forVariable(variable), INITS);
    }

    public QHistoriqueLocalisation(Path<? extends HistoriqueLocalisation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHistoriqueLocalisation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHistoriqueLocalisation(PathMetadata metadata, PathInits inits) {
        this(HistoriqueLocalisation.class, metadata, inits);
    }

    public QHistoriqueLocalisation(Class<? extends HistoriqueLocalisation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.immobilisation = inits.isInitialized("immobilisation") ? new QImmobilisation(forProperty("immobilisation"), inits.get("immobilisation")) : null;
    }

}

