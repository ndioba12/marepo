package sn.gainde2000.fichedotation.entities.other;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFailedMail is a Querydsl query type for FailedMail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFailedMail extends EntityPathBase<FailedMail> {

    private static final long serialVersionUID = -1787292931L;

    public static final QFailedMail failedMail = new QFailedMail("failedMail");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isSent = createBoolean("isSent");

    public final StringPath subject = createString("subject");

    public final StringPath text = createString("text");

    public QFailedMail(String variable) {
        super(FailedMail.class, forVariable(variable));
    }

    public QFailedMail(Path<? extends FailedMail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFailedMail(PathMetadata metadata) {
        super(FailedMail.class, metadata);
    }

}

