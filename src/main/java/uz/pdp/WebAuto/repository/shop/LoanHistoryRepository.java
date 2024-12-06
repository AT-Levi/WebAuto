package uz.pdp.WebAuto.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.WebAuto.entity.shop.LoanHistory;

@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {


}

