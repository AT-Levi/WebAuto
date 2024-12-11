package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.WebAuto.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.owner.id = :ownerId")
    Company findByOwnerId(@Param("ownerId") Long ownerId);

    @Modifying
    @Transactional
    @Query("UPDATE Company c SET c.deleted = true WHERE c.id = :id")
    void deleteCompanyById(@Param("id") Long id);

    Company findByName(String companyName);
}
