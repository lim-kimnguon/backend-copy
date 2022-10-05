package com.quiz.quizsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_answers", schema = "quiz")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private int order_number;
    private Double score;
    private int answer_id;
    private int user_id;

    @Column(name = "created", insertable = true, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "is_delete")
    private boolean delete = false;
}
