package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfil is a Querydsl query type for Profil
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfil extends EntityPathBase<Profil> {

    private static final long serialVersionUID = 446574023L;

    public static final QProfil profil = new QProfil("profil");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath libelle = createString("libelle");

    public final SetPath<Menu, QMenu> menus = this.<Menu, QMenu>createSet("menus", Menu.class, QMenu.class, PathInits.DIRECT2);

    public QProfil(String variable) {
        super(Profil.class, forVariable(variable));
    }

    public QProfil(Path<? extends Profil> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProfil(PathMetadata metadata) {
        super(Profil.class, metadata);
    }

}

