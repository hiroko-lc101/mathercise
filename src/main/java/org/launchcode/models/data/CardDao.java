package org.launchcode.models.data;

import org.launchcode.models.Card;
import org.launchcode.models.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CardDao extends CrudRepository<Card, Integer> {
    List<Card> findByLevel(Level level);
    List<Card> findAllByOrderByLevelAsc();
}
