package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.RoomType;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTypeFactoryTest
{
    @Test
    void testCreateRoomType() {
        RoomType roomType = RoomTypeFactory.createRoomType(1 ,"Deluxe", 150.00);
        assertNotNull(roomType);
        assertEquals("Deluxe", roomType.getRoomtypeName());
        assertEquals(150.00, roomType.getRoomPrice());
    }
/*
    @Test
    void testCreateRoomTypeInvalidData() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RoomTypeFactory.createRoomType(null, 150.00));
        assertEquals("Room type name is required", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () ->
                RoomTypeFactory.createRoomType("Standard", -100.00));
        assertEquals("Room price must be greater than zero", exception.getMessage());
    }
*/

}
