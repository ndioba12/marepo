package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Marque;
import sn.gainde2000.fichedotation.entities.TypeCession;

import java.util.Optional;

@Repository
public interface TypeCessionRepository extends JpaRepository <TypeCession, Integer>  {
    Optional<TypeCession> findByCode(String code);
}
