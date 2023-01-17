package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUtilisateur is a Querydsl query type for Utilisateur
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUtilisateur extends EntityPathBase<Utilisateur> {

    private static final long serialVersionUID = -838843688L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUtilisateur utilisateur = new QUtilisateur("utilisateur");

    public final sn.gainde2000.fichedotation.entities.audit.QAuditable _super = new sn.gainde2000.fichedotation.entities.audit.QAuditable(this);

    public final StringPath adresse = createString("adresse");

    public final NumberPath<Integer> createdBy = createNumber("createdBy", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final BooleanPath firstLog = createBoolean("firstLog");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isDeleteted = createBoolean("isDeleteted");

    public final NumberPath<Integer> lastModifiedBy = createNumber("lastModifiedBy", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QProfil linkedProfil;

    public final StringPath nom = createString("nom");

    public final StringPath password = createString("password");

    public final StringPath prenom = createString("prenom");

    public final SetPath<Profil, QProfil> profils = this.<Profil, QProfil>createSet("profils", Profil.class, QProfil.class, PathInits.DIRECT2);

    public final BooleanPath status = createBoolean("status");

    public final StringPath telephone = createString("telephone");

    public QUtilisateur(String variable) {
        this(Utilisateur.class, forVariable(variable), INITS);
    }

    public QUtilisateur(Path<? extends Utilisateur> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUtilisateur(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUtilisateur(PathMetadata metadata, PathInits inits) {
        this(Utilisateur.class, metadata, inits);
    }

    public QUtilisateur(Class<? extends Utilisateur> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.linkedProfil = inits.isInitialized("linkedProfil") ? new QProfil(forProperty("linkedProfil")) : null;
    }

}

