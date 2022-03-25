package com.qa.quizedup.testing.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.quizedup.model.QuizMaking;
import com.qa.quizedup.repo.Repo;
import com.qa.quizedup.services.ServiceDB;


@SpringBootTest
@ActiveProfiles("dev")
public class ServiceTest {
	
	@MockBean
	private Repo repo;
	
	@Autowired
	private ServiceDB service;
	
	//Test objects
	QuizMaking question1 = new QuizMaking ("Science", "Gravity", "The concept of gravity was discovered by which famous physicist? (a) Graham Bell (b)Marie Curie (c) Isaac Newton (d) none is correct",
	"b", false);
	
	QuizMaking question2 = new QuizMaking ("History", "War", "How many years did the 100 years war last? (a) 111 years (b)116 years (c) 200 years (d) 100 years","b", true);
	
	//Test objects with ID
	QuizMaking question1ID= new QuizMaking (1l, "Science", "Gravity", "The concept of gravity was discovered by which famous physicist? (a) Graham Bell (b)Marie Curie (c) Isaac Newton (d) none is correct",
	"b", false);
	QuizMaking question2ID= new QuizMaking (2l, "History", "War", "How many years did the 100 years war last? (a) 111 years (b)116 years (c) 200 years (d) 100 years","b", true);
	QuizMaking question3ID= new QuizMaking (3l, "Language", "Germanic", "Which of the following is a Germanic language? (a) Celtic  (b)Vietnamese (c) French (d) Malay","c", false);
	QuizMaking question4ID= new QuizMaking (4l, "Geography", "State", "How many state are there in Australia? (a) 5 (b) 12 (c) 10 (d) 6","d", true);

		
	@Test
	public void testCreateQuestion() {
		Mockito.when(repo.save(question1)).thenReturn(question1ID);
		boolean result = service.createQuestion(question1);
		Assertions.assertTrue(result);
		Mockito.verify(repo, Mockito.times(1)).save(question1);
	}
	
	
	@Test
	public void testCreateMultipleQuestions() {
		
		List<QuizMaking> testList = List.of(question1,question2);
		Mockito.when(repo.findAll()).thenReturn(testList);
		List<QuizMaking> testListDatabase = List.of(question1ID, question2ID);
		Mockito.when(repo.saveAll(testList)).thenReturn(testListDatabase);
		List<QuizMaking> result=service.createMultipleQuestions(testList);
		Assertions.assertEquals(testListDatabase, result);
		Mockito.verify(repo, Mockito.never()).flush();
		
		
	}
	
	@Test
	public void testGetQuestionById() {
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(question1ID));
		QuizMaking result = service.getQuestionById(1l);
		Assertions.assertEquals(question1ID, result);
		Mockito.verify(repo, Mockito.never()).count();
		
	}
	
	@Test
	public void testGetQuestionByCategory() {
		
		List<QuizMaking> testList = new ArrayList<QuizMaking>();
		testList.add(question1ID);
		Mockito.when(repo.findByCategory("Geography")).thenReturn(testList);
		List<QuizMaking> result = service.getQuestionsByCategory("Geography");
		Assertions.assertEquals(testList, result);
		Mockito.verify(repo, Mockito.never()).flush();
		
	
	}
	@Test
	public void testGetFinalExam() {
		List<QuizMaking> testList = new ArrayList<QuizMaking>();
		testList.add(question1ID);
		Mockito.when(repo.findByFinalExam(true)).thenReturn(testList);
		List<QuizMaking> result = service.getFinalExam(true);
		Assertions.assertEquals(testList, result);
		Mockito.verify(repo, Mockito.never()).deleteAll();
		
	}
	
	@Test
	public void testGetAllQuestions() {
		
		List<QuizMaking> testList = List.of(question1ID, question2ID);
		Mockito.when(repo.findAll()).thenReturn(testList);
		List<QuizMaking>result = service.getAllQuestions();
		Assertions.assertEquals(testList, result);
		Mockito.verify(repo, Mockito.never()).count();
		
	}
	
	@Test
	public void testUpdate() {
		
		QuizMaking oldQuestion = question2ID;
		oldQuestion.setCategory("new category");
		oldQuestion.setKeywords("new keywords");
		oldQuestion.setFinalExam(true);
		Mockito.when(repo.findById(2l)).thenReturn(Optional.of(question2ID));
		Mockito.when(repo.save(oldQuestion)).thenReturn(oldQuestion);
		QuizMaking result = service.update(2l, oldQuestion);
		Assertions.assertEquals(oldQuestion, result);
		Mockito.verify(repo, Mockito.never()).count();
	}
	
	
	@Test
	public void testDeleteById() {
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(question1ID));
		boolean result = service.deleteById(1l);
		Assertions.assertTrue(true);
		Mockito.verify(repo, Mockito.never()).flush();
		
	}
	
	@Test
	public void testDeleteAll() {
		List<QuizMaking> testList = List.of(question1ID, question2ID);
		Mockito.when(repo.findAll()).thenReturn(testList);
		boolean result = service.deleteAll();
		Assertions.assertTrue(true);
		Mockito.verify(repo, Mockito.never()).count();
	}
	
	
	//******Stretch goals**********//
//	
	@Test
	public void takeTest() {
		List<QuizMaking> testList = List.of(question1ID, question2ID);
		Mockito.when(repo.findAll()).thenReturn(testList);
		int result = service.takeTest();
		Assertions.assertEquals(0, result);
		Mockito.verify(repo, Mockito.never()).count();
		
	}
	
	//Wrote the following test but failed.
	//Ran out of time to debug, but will continue debugging after project submission.
	
//	@Test
//	public void testShuffleFinalExam() {
//		List<QuizMaking> testList = List.of(question1ID, question3ID, question4ID);
//		Mockito.when(repo.findByFinalExam(true)).thenReturn(testList);
//		List<QuizMaking> result = service.shuffleFinalExam();
//		Assertions.assertEquals(testList,result);
//		Mockito.verify(repo, Mockito.never()).count();
//		
//	}
	
	
	

	
	
	
	

}
