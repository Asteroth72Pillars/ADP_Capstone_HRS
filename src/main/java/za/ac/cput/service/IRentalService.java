package za.ac.cput.service;

import za.ac.cput.domain.Rental;

import java.util.List;

public interface IRentalService extends IService<Rental, Integer> {
    Rental create(Rental rental);

    Rental read(Integer id);

    Rental update(int id, Rental rental);

    boolean delete(Integer id);

    List<Rental> getAll();

    boolean existsById(Integer id);
}
