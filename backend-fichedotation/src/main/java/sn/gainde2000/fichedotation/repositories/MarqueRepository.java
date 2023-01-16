package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Marque;
import sn.gainde2000.fichedotation.entities.Profil;

import java.util.Optional;

@Repository
public interface MarqueRepository extends JpaRepository <Marque, Integer>  {
    Optional<Marque> findByCode(String code);
}
