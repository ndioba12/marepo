package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFournisseur is a Querydsl query type for Fournisseur
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFournisseur extends EntityPathBase<Fournisseur> {

    private static final long serialVersionUID = -1441613322L;

    public static final QFournisseur fournisseur = new QFournisseur("fournisseur");

    public final StringPath adresse = createString("adresse");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nomOuRaiSociale = createString("nomOuRaiSociale");

    public final StringPath telephoneFixe = createString("telephoneFixe");

    public QFournisseur(String variable) {
        super(Fournisseur.class, forVariable(variable));
    }

    public QFournisseur(Path<? extends Fournisseur> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFournisseur(PathMetadata metadata) {
        super(Fournisseur.class, metadata);
    }

}

