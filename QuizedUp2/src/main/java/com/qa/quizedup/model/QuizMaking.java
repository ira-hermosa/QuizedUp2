package com.qa.quizedup.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuizMaking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false, length=30)
	private String category;
	
	@Column(nullable=false, length=30)
	private String keywords;
	
	//Questions will all be of multiple choice type with options included, hence the length
	@Column(nullable=false, length=500)
	private String question;
	
	@Column(nullable=false, length=20)
	private String correctAnswer;
	
	@Column(nullable=false)
	private boolean finalExam;

	//Default constructor
	public QuizMaking() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public boolean isFinalExam() {
		return finalExam;
	}

	public void setFinalExam(boolean finalExam) {
		this.finalExam = finalExam;
	}

	//Constructor with ID
	public QuizMaking(long id, String category, String keywords, String question, String correctAnswer,
			boolean finalExam) {
		super();
		this.id = id;
		this.category = category;
		this.keywords = keywords;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.finalExam = finalExam;
	}

	//Constructor without ID
	
	public QuizMaking(String category, String keywords, String question, String correctAnswer,
			boolean finalExam) {
		super();
		this.category = category;
		this.keywords = keywords;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.finalExam = finalExam;
	}

	//Hash Code
	@Override
	public int hashCode() {
		return Objects.hash(category, correctAnswer, id, keywords, question, finalExam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizMaking other = (QuizMaking) obj;
		return Objects.equals(category, other.category) && Objects.equals(correctAnswer, other.correctAnswer)
				&& id == other.id && Objects.equals(keywords, other.keywords)
				&& Objects.equals(question, other.question) && finalExam == other.finalExam;
	}

	//toString
	@Override
	public String toString() {
		return "QuizMaking [id=" + id + ", category=" + category + ", keywords=" + keywords + ", question=" + question
				+ ", correctAnswer=" + correctAnswer + ", finalExam=" + finalExam + "]";
	}
	
	
	
	
	
	

	
}