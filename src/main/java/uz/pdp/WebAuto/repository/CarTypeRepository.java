package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.Query;
import uz.pdp.WebAuto.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {

    @Query("UPDATE CarType ct SET ct.deleted =true WHERE ct.id =: id")
    void deleteByCarTypeId(Long id);
}
