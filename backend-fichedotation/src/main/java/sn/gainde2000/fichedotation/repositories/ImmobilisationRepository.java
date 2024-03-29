package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Immobilisation;
import sn.gainde2000.fichedotation.entities.Utilisateur;

import java.util.Optional;

@Repository
public interface ImmobilisationRepository extends JpaRepository <Immobilisation, Integer>, QuerydslPredicateExecutor<Immobilisation> {

        Optional<Immobilisation> findByDesignation(String designation);
    //Optional<Immobilisation> findById(Integer id);
}
