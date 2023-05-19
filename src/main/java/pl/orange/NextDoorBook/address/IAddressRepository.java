package pl.orange.NextDoorBook.address;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "UPDATE Address SET cityName = :cityName, street =:street,numberHouse=:numberHouse,zipCode=:zipCode,district=:district WHERE id =:id")
    @Modifying
    @Transactional
    void updateAddress(@Param("id") Long id,
                       @Param("cityName") String cityName,
                       @Param("street") String street,
                       @Param("numberHouse") int numberHouse,
                       @Param("zipCode") int zipCode,
                       @Param("district") String district);


}
