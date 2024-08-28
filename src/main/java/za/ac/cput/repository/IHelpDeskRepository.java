package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.HelpDesk;

import java.util.ArrayList;

@Repository
public interface IHelpDeskRepository extends JpaRepository<HelpDesk, Integer> {
    public ArrayList<HelpDesk> findAllByCategory(String category);
}
