package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCessionImmobilisation is a Querydsl query type for CessionImmobilisation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCessionImmobilisation extends EntityPathBase<CessionImmobilisation> {

    private static final long serialVersionUID = 223633127L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCessionImmobilisation cessionImmobilisation = new QCessionImmobilisation("cessionImmobilisation");

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QImmobilisation immobilisation;

    public final StringPath observation = createString("observation");

    public final StringPath prix = createString("prix");

    public final QTypeCession typeCession;

    public QCessionImmobilisation(String variable) {
        this(CessionImmobilisation.class, forVariable(variable), INITS);
    }

    public QCessionImmobilisation(Path<? extends CessionImmobilisation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCessionImmobilisation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCessionImmobilisation(PathMetadata metadata, PathInits inits) {
        this(CessionImmobilisation.class, metadata, inits);
    }

    public QCessionImmobilisation(Class<? extends CessionImmobilisation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.immobilisation = inits.isInitialized("immobilisation") ? new QImmobilisation(forProperty("immobilisation"), inits.get("immobilisation")) : null;
        this.typeCession = inits.isInitialized("typeCession") ? new QTypeCession(forProperty("typeCession")) : null;
    }

}

