package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypeMaintenance is a Querydsl query type for TypeMaintenance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTypeMaintenance extends EntityPathBase<TypeMaintenance> {

    private static final long serialVersionUID = -1778745266L;

    public static final QTypeMaintenance typeMaintenance = new QTypeMaintenance("typeMaintenance");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public QTypeMaintenance(String variable) {
        super(TypeMaintenance.class, forVariable(variable));
    }

    public QTypeMaintenance(Path<? extends TypeMaintenance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypeMaintenance(PathMetadata metadata) {
        super(TypeMaintenance.class, metadata);
    }

}

