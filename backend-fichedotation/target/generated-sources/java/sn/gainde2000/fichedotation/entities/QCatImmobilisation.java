package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCatImmobilisation is a Querydsl query type for CatImmobilisation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCatImmobilisation extends EntityPathBase<CatImmobilisation> {

    private static final long serialVersionUID = -135113033L;

    public static final QCatImmobilisation catImmobilisation = new QCatImmobilisation("catImmobilisation");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public QCatImmobilisation(String variable) {
        super(CatImmobilisation.class, forVariable(variable));
    }

    public QCatImmobilisation(Path<? extends CatImmobilisation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatImmobilisation(PathMetadata metadata) {
        super(CatImmobilisation.class, metadata);
    }

}

