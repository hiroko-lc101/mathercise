package org.launchcode.models.data;

import org.launchcode.models.TestQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TestQuestionDao extends CrudRepository<TestQuestion, Integer> {
    List<TestQuestion> findByTestId(int testId);
    List<TestQuestion> findByTestIdOrderByQuestionNumberAsc(int testId);
    TestQuestion findByTestIdAndQuestionNumber(int testId, int questionNumber);
}
