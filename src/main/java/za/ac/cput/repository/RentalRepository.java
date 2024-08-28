package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Rental;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {


    List<Rental> findAllByRoomId(int id);

    Rental findTopByRoomIdOrderByOutDateDesc(int id);

    List<Rental> findByUserIdAndOutDateIsNull(int id);


}