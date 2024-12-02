package com.project.springboot.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String question;
    private String category;
    private String typeOfRequest;

    public Question(String question, String category) {
        this.question = question;
        this.category = category;
    }

    @Override
    public String toString() {
        return " \n Question { " +
                "\n \t id=" + id +
                "\n \t question= '" + question + " , " +
                "\n \t category= '" + category + " , " +
                "\n }";
    }
}
