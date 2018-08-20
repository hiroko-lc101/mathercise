package org.launchcode.models.data;

import org.launchcode.models.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TestDao extends CrudRepository<Test, Integer> {
    List<Test> findAllByOrderByLevelAsc();
}