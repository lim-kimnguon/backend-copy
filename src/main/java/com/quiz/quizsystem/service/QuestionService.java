package com.quiz.quizsystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.quiz.quizsystem.dto.RequestFillgapQuestion;
import com.quiz.quizsystem.dto.RequestUpdateFillingapQuestion;
import com.quiz.quizsystem.model.Answer;
import com.quiz.quizsystem.model.Question;
import com.quiz.quizsystem.repository.AnswerRepository;
import com.quiz.quizsystem.repository.QuestionRepository;

@Service
@Transactional
public class QuestionService {
  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private AnswerRepository answerRepository;

  public List<Question> ListAllQuestion() {
    return questionRepository.findByDeleteFalse(Sort.by(Sort.Order.desc("id")));
  }

  public int saveQuestionFillingap(RequestFillgapQuestion reqestquestion) {
    Question question = new Question();
    List<Answer> answers = new ArrayList<>();

    question.setName(reqestquestion.getName());
    question.setTimeout(reqestquestion.getTimeout());
    question.setScore(reqestquestion.getScore());
    question.setQuestion_level_id(reqestquestion.getQuestion_level_id());
    question.setQuestion_type_id(reqestquestion.getQuestion_type_id());
    int question_id = questionRepository.save(question).getId();

    int answer_num = reqestquestion.getAnswername().length;
    String[] answer_name = reqestquestion.getAnswername();
    String[] pair = reqestquestion.getPair();
    boolean[] is_correct = reqestquestion.getIs_correct();

    for (int i = 0; i < answer_num; i++) {
      Answer answer = new Answer();
      answer.setCorrect(is_correct[i]);
      answer.setQuestion(question_id);
      answer.setName(answer_name[i]);
      answer.setPair(pair[i]);
      // System.out.println("\n\n\n"+answer.getName()+" "+answer.getPair()+"
      // "+answer.isCorrect()+" "+answer.getQuestion());
      answers.add(answer);
    }

    answerRepository.saveAll(answers);
    return question_id;
  }

  public void EditQuestionFillIngap(RequestUpdateFillingapQuestion reqestquestion, int id) {
    Question question = new Question();
    Question exitQuestion = getQuestion(id);

    List<Answer> answerbefore = exitQuestion.getAnswer();

 
    List<Answer> oldanswer = new ArrayList<>();
    List<Answer> notuseAsnwer = new ArrayList<>(answerbefore);
    List<Answer> answers = new ArrayList<>(answerbefore);

  
    int answer_num = reqestquestion.getAnswername().length;
    String[] answer_name = reqestquestion.getAnswername();

    List<String> newanswers = new ArrayList<>();

    for (int j = 0; j < answer_num; j++) {
      newanswers.add(answer_name[j]);
    }

    // find old answer
    for (int i = 0; i < answerbefore.size(); i++) {
      for (int j = 0; j < answer_num; j++) {
        if (answerbefore.get(i).getName().equals(answer_name[j])) {
          newanswers.remove(j);
          oldanswer.add(answerbefore.get(i));
        }
      }
    }

    notuseAsnwer.removeAll(oldanswer);
    String[] notuserName = new String[notuseAsnwer.size()];
    for (int i = 0; i < notuseAsnwer.size(); i++) {
      notuserName[i]=notuseAsnwer.get(i).getName();
    }

    for (int i = 0; i < newanswers.size(); i++) {
      Answer answer = new Answer();
      answer.setCorrect(true);
      answer.setQuestion(id);
      answer.setName(newanswers.get(i));
      answer.setPair("none");
      answers.add(answer);
    }
    
    answerRepository.DeleteMultipleAnswer(id,notuserName);
    answerRepository.saveAll(answers);

    question.setId(id);
    question.setName(reqestquestion.getName());
    question.setTimeout(reqestquestion.getTimeout());
    question.setScore(reqestquestion.getScore());
    question.setQuestion_level_id(reqestquestion.getQuestion_level_id());
    question.setQuestion_type_id(reqestquestion.getQuestion_type_id());

    questionRepository.save(question);


  }

  public int saveQuestion(Question question) {
    int question_id = questionRepository.save(question).getId();
    return question_id;
  }

  public Question getQuestion(Integer id) {
    return questionRepository.findById(id).get();
  }

  public int deleteQuestion(Integer id) {
    return questionRepository.DeleteQuestion(id);
  }

  public List<Question> getQuestionByQuizId(Integer quizId) {
    return questionRepository.findByQuizId(quizId);
  }

  public List<Question> getQuestionNotInQuizId(Integer quizId) {
    return questionRepository.findAllQuestionNotInQuiz(quizId);
  }

}
