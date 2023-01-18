package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.TypeCession;
import sn.gainde2000.fichedotation.entities.TypeImmobilisation;

import java.util.Optional;

@Repository
public interface TypeImmobilisationRepository extends JpaRepository <TypeImmobilisation, Integer>  {
    Optional<TypeImmobilisation> findByCode(String code);
}
