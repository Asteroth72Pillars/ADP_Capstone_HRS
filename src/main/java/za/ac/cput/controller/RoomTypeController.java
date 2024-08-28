package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.RoomType;
import za.ac.cput.service.IRoomTypeService;
import za.ac.cput.service.RoomTypeService;
import java.util.*;

@WebMvcTest(RoomTypeController.class)
@RestController
@RequestMapping("/api/roomType")
public class RoomTypeController
{
    private final IRoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(IRoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<RoomType> create(@RequestBody RoomType roomType) {
        RoomType newRoomType = roomTypeService.create(roomType);
        //return new ResponseEntity<>(newRoomType, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomType);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<RoomType> read(@PathVariable long id) {
        RoomType roomType = roomTypeService.read(id);
        return roomType != null ? ResponseEntity.ok(roomType) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<RoomType> update(@RequestBody RoomType roomType) {
        RoomType updatedRoomType = roomTypeService.update(roomType);
        return updatedRoomType != null ? ResponseEntity.ok(updatedRoomType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Long id) {
        roomTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
/*
    @GetMapping("/all")
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        List<RoomType> roomTypes = roomTypeService.findAll();
        return new ResponseEntity<>(roomTypes, HttpStatus.OK);
    }*/
}
