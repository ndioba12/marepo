package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTypeImmobilisation is a Querydsl query type for TypeImmobilisation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeImmobilisation extends EntityPathBase<TypeImmobilisation> {

    private static final long serialVersionUID = -2009259375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTypeImmobilisation typeImmobilisation = new QTypeImmobilisation("typeImmobilisation");

    public final QCatImmobilisation catImmobilisation;

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public QTypeImmobilisation(String variable) {
        this(TypeImmobilisation.class, forVariable(variable), INITS);
    }

    public QTypeImmobilisation(Path<? extends TypeImmobilisation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTypeImmobilisation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTypeImmobilisation(PathMetadata metadata, PathInits inits) {
        this(TypeImmobilisation.class, metadata, inits);
    }

    public QTypeImmobilisation(Class<? extends TypeImmobilisation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.catImmobilisation = inits.isInitialized("catImmobilisation") ? new QCatImmobilisation(forProperty("catImmobilisation")) : null;
    }

}

