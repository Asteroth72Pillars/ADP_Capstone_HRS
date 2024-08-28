/*
package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RoomTypeRepositoryTest
{
    @Autowired
    private RoomTypeRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void testCreateAndFindRoomType() {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Suite")
                .setRoomPrice(200.00)
                .build();
        repository.save(roomType);

        RoomType found = repository.findById(roomType.getTypeId()).orElse(null);
        assertNotNull(found);
        assertEquals("Suite", found.getRoomtypeName());
        assertEquals(200.00, found.getRoomPrice());
    }

    @Test
    void testDeleteRoomType() {
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Standard")
                .setRoomPrice(100.00)
                .build();
        repository.save(roomType);

        repository.deleteById(roomType.getTypeId());
        RoomType found = repository.findById(roomType.getTypeId()).orElse(null);
        assertNull(found);
    }
/*
    @Test
    public void whenDeleteRoomType_thenRoomTypeShouldNotExist() {
        // Given
        RoomType roomType = new RoomType.Builder()
                .setRoomtypeName("Family Room")
                .setRoomPrice(200.0)
                .build();
        entityManager.persist(roomType);
        entityManager.flush();

        // When
        roomTypeRepository.delete(roomType);
        entityManager.flush();

        // Then
        RoomType deletedRoomType = entityManager.find(RoomType.class, roomType.getTypeId());
        assertThat(deletedRoomType).isNull();
    }
}
*/