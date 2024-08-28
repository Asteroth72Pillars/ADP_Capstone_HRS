package za.ac.cput.repository;

import za.ac.cput.domain.RoomType;
import java.util.*;

public interface iRepository<T, ID>
{
    T create(T entity);
    T read(ID id);
    T update(T entity);
    boolean delete(ID id);
}
