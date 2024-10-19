package uz.pdp.WebAuto.repository;

import uz.pdp.WebAuto.entity.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
