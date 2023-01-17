package sn.gainde2000.fichedotation.entities.other;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDisposableEmail is a Querydsl query type for DisposableEmail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDisposableEmail extends EntityPathBase<DisposableEmail> {

    private static final long serialVersionUID = -53467053L;

    public static final QDisposableEmail disposableEmail = new QDisposableEmail("disposableEmail");

    public final StringPath domain = createString("domain");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QDisposableEmail(String variable) {
        super(DisposableEmail.class, forVariable(variable));
    }

    public QDisposableEmail(Path<? extends DisposableEmail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDisposableEmail(PathMetadata metadata) {
        super(DisposableEmail.class, metadata);
    }

}

