package za.ac.cput.service;

import za.ac.cput.domain.Room;

import java.util.ArrayList;

public interface IRoomService extends IService<Room, Integer> {
    Room create(Room room);

    Room read(Integer id);

    Room update(Room room);

    boolean delete(Integer id);

    ArrayList<Room> getAll();
}
