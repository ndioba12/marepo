/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :06/12/2021
 */

package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.Profil;

import java.util.Optional;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer> {
    Optional<Profil> findByCode(String code);
}
