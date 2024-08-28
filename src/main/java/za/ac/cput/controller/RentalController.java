package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Rental;
import za.ac.cput.service.impl.RentalServiceImpl;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user/rentals")
public class RentalController {
    @Autowired
    private RentalServiceImpl rentalService;

 /*   @GetMapping("/list/all")
    public ArrayList<Rental> getAll() {
        ArrayList<Rental> rentals = new ArrayList<>(rentalService.getAll());
        return rentals;
    }*/

    @PostMapping("/create")
    public Rental createRental(@RequestBody Rental rental) {
        // Retrieve user and room based on their IDs
    /*    System.out.println(rental.getUser());
        System.out.println(rental.getRoom());
        System.out.println(rental.getIssuedDate());
        System.out.println(rental.getReturnedDate());*/

        //user + rom + times == rental
        //User
        //1 receive the token - header
        //2 find the user by their email from the token (extract the email from the token)//jwtutils
        //3 find user via repository and return user object

        //Room
        //4 find the room by its id - room service -> room repo = room object

        //Rental
        //5 create a rental object with the user, room and times

        /* return rentalService.create(rental);*/

        System.out.println("/api/user/rentals/create was triggered");
        //  System.out.println("RentalService was created...attempting to create rental...");

        // Retrieve user and room based on their IDs
        System.out.println(rental.getUser());
        System.out.println(rental.getRoom());
        System.out.println(rental.getInDate());
        System.out.println(rental.getOutDate());
        return rentalService.create(rental);


    }

    @GetMapping("/read/{rentalId}")
    public Rental readRental(@PathVariable Integer rentalId) {
        System.out.println("/api/admin/rentals/read was triggered");
        System.out.println("RentalService was created...attempting to read rental...");
        Rental readRental = rentalService.read(rentalId);
        return readRental;
    }


}

