package za.ac.cput.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.PriceGroup;
import za.ac.cput.domain.Rental;
import za.ac.cput.domain.Room;
import za.ac.cput.domain.User;
import za.ac.cput.exceptions.RoomNotAvailableException;
import za.ac.cput.exceptions.UserCannotRentMoreThanOneRoomException;
import za.ac.cput.factory.RentalFactory;
import za.ac.cput.repository.RentalRepository;
import za.ac.cput.repository.RoomRepository;
import za.ac.cput.service.IRentalService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("rentalServiceImpl")
public class RentalServiceImpl implements IRentalService {

    @Autowired
    private RentalRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RentalFactory rentalFactory;
    private Room room;


    public boolean isRoomAvailableByRoomId(Room room) {
        this.room = room;
        return roomRepository.existsByIdAndIsAvailableIsTrue((int) room.getId());
    }




    @Override
    @Transactional
    public Rental create(Rental rental) {
        if (isRoomAvailable(rental)) {
            if (isCurrentlyRenting(rental.getUser())) {
                throw new UserCannotRentMoreThanOneRoomException(
                        generateUserRentingErrorMessage(rental.getUser()));
            }
            Rental newRental = rentalFactory.create(rental);
            if (newRental.getOutDate() != null) {
                roomRepository.setIsAvailableToTrue((int) newRental.getRoom().getId());
                System.out.println("Is room available after update: " + newRental.getRoom().isAvailable());
            }else {
                roomRepository.setIsAvailableToFalse((int) newRental.getRoom().getId());
                System.out.println("Is room available after update: " + newRental.getRoom().isAvailable());
            }
            // roomRepository.setIsAvailableToFalse((int) newRental.getRoom().getId());
            return repository.save(newRental);
        } else {
            throw new RoomNotAvailableException(generateRoomNotAvailableErrorMessage(rental.getRoom()));
        }
    }

    @Override
    public Rental read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Rental update(Rental rental) {
        if (repository.existsById(rental.getId())) {
            Rental updatedRental = rentalFactory.create(rental);
            if (updatedRental.getOutDate() != null) {
                Room room = updatedRental.getRoom();
                room.setAvailable(true);
                roomRepository.save(room);
            } else {
                Room room = updatedRental.getRoom();
                room.setAvailable(false);
                roomRepository.save(room);
            }
            return repository.save(updatedRental);
        }
        return null;
    }

    @Override
    @Transactional
    public Rental update(int id, Rental rental) {
        // Ensure the id matches for updating
        if (repository.existsById(id)) {
            rental.setId(id);
            return update(rental);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Rental> getAll() {
        return repository.findAll();
    }

    public List<Rental> getAllAvailableRooms() {
        List<Rental> allRentals = repository.findAll();
        return filterAvailableRooms(allRentals);
    }

    private List<Rental> filterAvailableRooms(List<Rental> rentals) {
        List<Rental> availableRooms = new ArrayList<>();
        for (Rental rental : rentals) {
            if (isRoomAvailable(rental)) {
                availableRooms.add(rental);
            }
        }
        return availableRooms;
    }

    private String generateRoomNotAvailableErrorMessage(Room room) {
        return room.getRoomType() + " " + room.getRoomType() + " is not available for rental at this time.";
    }

    private String generateUserRentingErrorMessage(User user) {
        return user.getUserName() + " is already renting a room.";
    }

    private boolean isRoomAvailable(Rental rental) {
        Rental rentalRecord = repository.findTopByRoomIdOrderByOutDateDesc(rental.getRoom().getId());
        if (rentalRecord != null && rentalRecord.getOutDate() != null) {
            Duration duration = Duration.between(LocalDateTime.now(), rentalRecord.getOutDate());
            return duration.isNegative();
        }
        return true;
    }

    private boolean isCurrentlyRenting(User user) {
        return repository.findByUserIdAndOutDateIsNull(user.getId()).size() > 0;
    }

    public List<Room> getAvailableRoomsByPrice(PriceGroup priceGroup)
    {
        // Join to rentals table and check availability
        // check if room is available
        ArrayList<Room> availableRooms = new ArrayList<>(roomRepository.findByPriceGroup(priceGroup));
        for (Room room : availableRooms)   //for each room in available rooms
        {
            if (!isRoomAvailableByRoomId(room)) //if room is not available
            {
                availableRooms.remove(room); //remove room from available rooms
            }
        }
        return availableRooms;


    }


    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}
