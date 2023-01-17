package sn.gainde2000.fichedotation.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 1345612714L;

    public static final QMenu menu = new QMenu("menu");

    public final StringPath ab = createString("ab");

    public final SetPath<Menu, QMenu> children = this.<Menu, QMenu>createSet("children", Menu.class, QMenu.class, PathInits.DIRECT2);

    public final StringPath collapse = createString("collapse");

    public final StringPath iconType = createString("iconType");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath path = createString("path");

    public final NumberPath<Integer> priorite = createNumber("priorite", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public QMenu(String variable) {
        super(Menu.class, forVariable(variable));
    }

    public QMenu(Path<? extends Menu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMenu(PathMetadata metadata) {
        super(Menu.class, metadata);
    }

}

