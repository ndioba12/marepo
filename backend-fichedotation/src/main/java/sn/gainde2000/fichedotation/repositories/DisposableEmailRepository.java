/**
 * Created By alndiaye(Amadou Lamine NDIAYE)
 * Date :15/12/2021
 */

package sn.gainde2000.fichedotation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.gainde2000.fichedotation.entities.other.DisposableEmail;

import java.util.Optional;

@Repository
public interface DisposableEmailRepository extends JpaRepository<DisposableEmail, Integer> {
    Optional<DisposableEmail> findByDomain(String domain);
}
