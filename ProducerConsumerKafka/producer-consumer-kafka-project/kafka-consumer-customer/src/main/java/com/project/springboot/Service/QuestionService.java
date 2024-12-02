package com.project.springboot.Service;

import com.project.springboot.Model.Question;
import com.project.springboot.Repository.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);
    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Question question) {
        if (question.getTypeOfRequest().equals("delete")) {
            LOGGER.info(String.format("Delete Query received Successfully -> %s ", question.toString()));
            questionRepo.deleteById(question.getId());
        } else if (question.getTypeOfRequest().equals("post")) {
            LOGGER.info(String.format("Save Query received Successfully -> %s ", question.toString()));
            questionRepo.save(question);
        } else {
            LOGGER.info(String.format("Update Query received Successfully -> %s ", question.toString()));
            questionRepo.save(question);
            Optional<Question> oldQuestion = questionRepo.findById(question.getId());
            if (oldQuestion.isPresent()) {
                Question tmpQuestion = oldQuestion.get();
                tmpQuestion.setId(question.getId());
                tmpQuestion.setQuestion(question.getQuestion());
                tmpQuestion.setCategory(question.getCategory());
                questionRepo.save(tmpQuestion);
            }
        }
    }

        public List<Question> findAll () {
            LOGGER.info(String.format("All Question  Query received Successfully"));
            return questionRepo.findAll();
        }

        public Question findById (Long id){
            LOGGER.info(String.format("Specific  Question  Query received Successfully"));
            Optional<Question> employee = questionRepo.findById(id);
            return employee.orElse(null);
        }
}
