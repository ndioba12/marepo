package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeCession is a Querydsl query type for TypeCession
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeCession extends EntityPathBase<TypeCession> {

    private static final long serialVersionUID = 1143877953L;

    public static final QTypeCession typeCession = new QTypeCession("typeCession");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public QTypeCession(String variable) {
        super(TypeCession.class, forVariable(variable));
    }

    public QTypeCession(Path<? extends TypeCession> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeCession(PathMetadata metadata) {
        super(TypeCession.class, metadata);
    }

}

