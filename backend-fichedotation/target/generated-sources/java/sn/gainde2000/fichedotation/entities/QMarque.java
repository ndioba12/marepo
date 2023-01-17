package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarque is a Querydsl query type for Marque
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarque extends EntityPathBase<Marque> {

    private static final long serialVersionUID = 345087022L;

    public static final QMarque marque = new QMarque("marque");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public QMarque(String variable) {
        super(Marque.class, forVariable(variable));
    }

    public QMarque(Path<? extends Marque> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarque(PathMetadata metadata) {
        super(Marque.class, metadata);
    }

}

