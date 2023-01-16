package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

/**
 * @author : alndiaye (Amadou Lamine NDIAYE)
 */

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>, QuerydslPredicateExecutor<Utilisateur> {
    Optional<Utilisateur> findUtilisateurByEmail(String username);

    List<Utilisateur> findAllByEmail(String email);
}
