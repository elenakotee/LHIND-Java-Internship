package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<ENTITY, ID> {

    List<ENTITY> findAll();

    Optional<ENTITY> findById(ID id);

    void save(ENTITY entity);

    Integer update(ENTITY entity);

    void remove(ID id);

}
