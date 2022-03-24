package com.qa.quizedup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.quizedup.model.QuizMaking;

public interface Repo extends JpaRepository<QuizMaking, Long> {
	
	public List<QuizMaking> findByCategory(String category);
	public List<QuizMaking> findByQuestion(String question);
	public List<QuizMaking> findByKeywords(String keywords);
	public List<QuizMaking> findByFinalExam(boolean finalExam);
	public int save(int totalScore);

}
