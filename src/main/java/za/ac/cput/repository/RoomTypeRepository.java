package za.ac.cput.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.RoomType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long>
{
    //RoomType update(RoomType roomtype);
    @Query("SELECT r FROM RoomType r WHERE r.roomtypeName = :name")
    RoomType findByName(@Param("name") String name);
}
