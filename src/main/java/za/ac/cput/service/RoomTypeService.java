package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.RoomType;
import za.ac.cput.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.RoomType;
import za.ac.cput.repository.RoomTypeRepository;
import za.ac.cput.service.IRoomTypeService;
import java.util.*;

@Service
public class RoomTypeService implements IRoomTypeService
{

    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType create(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    @Override
    public RoomType read(Long id) {
        Optional<RoomType> roomType = roomTypeRepository.findById(id);
        return roomType.orElse(null);
    }

    @Override
    public RoomType update(RoomType roomType) {
        if (roomTypeRepository.existsById(roomType.getTypeId())) {
            return roomTypeRepository.save(roomType);
        } else {
            throw new IllegalArgumentException("RoomType with ID " + roomType.getTypeId() + " does not exist.");
        }
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
/*
    @Override
    public void delete(Long id) {
        if (roomTypeRepository.existsById(id)) {
            roomTypeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("RoomType with ID " + id + " does not exist.");
        }
    }
*/
    /*
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository)
    {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType create(RoomType roomType)
    {
        return roomTypeRepository.create(roomType);
    }

    @Override
    public RoomType read(Long id)
    {
        return roomTypeRepository.findById(id);
    }

    @Override
    public RoomType update(RoomType roomType)
    {
        RoomType existingRoomType = roomTypeRepository.findById(roomType.getTypeId());
        if (existingRoomType != null)
        {
            return roomTypeRepository.update(roomType);
        }
        return null;
    }

    @Override
    public void delete(RoomType roomType)
    {
        roomTypeRepository.delete(roomType);
    }
    */

}
