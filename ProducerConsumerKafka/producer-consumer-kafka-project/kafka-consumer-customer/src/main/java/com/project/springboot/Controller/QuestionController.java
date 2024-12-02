package com.project.springboot.Controller;

import com.project.springboot.Model.Question;
import com.project.springboot.Service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Question")
public class QuestionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> findAll() {
        LOGGER.info(String.format("All Question Request[GET All] received Successfully "));
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public Question findById(@PathVariable Long id) {
        LOGGER.info(String.format("Specific Question Request[GET] received Successfully "));
        return questionService.findById(id);
    }

}
