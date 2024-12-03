package uz.pdp.WebAuto.repository;

import uz.pdp.WebAuto.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
