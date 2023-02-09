package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.CatImmobilisation;

import java.util.Optional;

@Repository
public interface CatImmobilisationRepository extends JpaRepository <CatImmobilisation, Integer>  {
    Optional<CatImmobilisation> findByCode(String code);
}
