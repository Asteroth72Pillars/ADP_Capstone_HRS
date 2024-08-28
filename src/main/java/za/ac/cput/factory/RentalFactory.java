package za.ac.cput.factory;


import org.springframework.stereotype.Component;
import za.ac.cput.domain.Room;
import za.ac.cput.domain.Rental;
import za.ac.cput.domain.User;
import za.ac.cput.factory.IFactory;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class RentalFactory implements IFactory<Rental>
{
    public Rental create(int id, User user, Room room, LocalDateTime inDate, LocalDateTime outDate) {

        return new Rental.Builder()
                .setId(id)
                .setUser(user)
                .setRoom(room)
                .setInDate(inDate)
                .setOutDate(outDate)
                .build();
    }
    public Rental create() {
        return new Rental.Builder()
                .setId(new Random().nextInt(1000000))
                .build();
    }

    public Rental create(Rental rental) {
        return new Rental.Builder()
                .copy(rental)
                .build();
    }
}



