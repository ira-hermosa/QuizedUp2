package com.qa.quizedup.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qa.quizedup.model.QuizMaking;
import com.qa.quizedup.repo.Repo;

@Service
public class ServiceDB {
	
	private Repo repo;
	
	public ServiceDB(Repo repo) {
		super();
		this.repo=repo;
	}
	
	public boolean createQuestion(QuizMaking question){
		repo.save(question);
		return true;
	}
	
	public ArrayList<QuizMaking> createMultipleQuestions(QuizMaking[] questions){
		ArrayList<QuizMaking>questionBucket = new ArrayList<>();
		for (QuizMaking question: questions) {
			questionBucket.add(question);
		}
		return (ArrayList<QuizMaking>) repo.saveAll(questionBucket);
	}
	
	public QuizMaking getQuestionById(long id) {
		return repo.findById(id).get();
	}
	
	public List<QuizMaking> getQuestionsByCategory(String category){
		for(QuizMaking question: repo.findAll()) {
			if(question.getCategory() == category) {
			}
		}return repo.findByCategory(category);
	}
	
	public List<QuizMaking> getAllQuestions(){
		return repo.findAll();
	}
	
	public QuizMaking update (long id, QuizMaking question){
		
		 QuizMaking oldQuestion = getQuestionById(id);
		 
		 oldQuestion.setCategory(question.getCategory());
		 oldQuestion.setQuestion(question.getQuestion());
		 oldQuestion.setCorrectAnswer(question.getCorrectAnswer());
		 oldQuestion.setKeywords(question.getKeywords());
		 oldQuestion.setFinalExam(question.isFinalExam());
		 
		 return repo.save(oldQuestion);
	}
	
	public boolean deleteById(long id) {
		repo.deleteById(id);
		return true;
	}
	
	public boolean deleteAll() {
		repo.deleteAll();
		return true;
		
	}
	


}
