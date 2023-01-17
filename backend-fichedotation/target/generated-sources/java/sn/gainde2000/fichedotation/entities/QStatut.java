package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatut is a Querydsl query type for Statut
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStatut extends EntityPathBase<Statut> {

    private static final long serialVersionUID = 533905278L;

    public static final QStatut statut = new QStatut("statut");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public QStatut(String variable) {
        super(Statut.class, forVariable(variable));
    }

    public QStatut(Path<? extends Statut> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatut(PathMetadata metadata) {
        super(Statut.class, metadata);
    }

}

