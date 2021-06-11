package io.neeraj.codingblogmachine;

import io.neeraj.codingblogmachine.entity.Level;
import io.neeraj.codingblogmachine.entity.Question;
import io.neeraj.codingblogmachine.entity.User;
import io.neeraj.codingblogmachine.service.ContestService;
import io.neeraj.codingblogmachine.service.QuestionService;
import io.neeraj.codingblogmachine.service.UserService;
import io.neeraj.codingblogmachine.service.impl.MemoryContestServiceImpl;
import io.neeraj.codingblogmachine.service.impl.MemoryQuestionServiceImpl;
import io.neeraj.codingblogmachine.service.impl.MemoryUserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodingBlogMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingBlogMachineApplication.class, args);

		testRun();
	}

	private static void testRun() {

		UserService userService = new MemoryUserServiceImpl();
		QuestionService questionService = new MemoryQuestionServiceImpl();
		ContestService contestService = new MemoryContestServiceImpl( userService, questionService );

//		createUsers();
//		createQuestions();
//		createContests();
//		attendContest();
//		runContest();
//		getUsers();

		// createUser

		User neeraj = new User("neeraj");
		User trisha = new User( "trisha" );
		User varun = new User( "varun" );
		userService.createUser( neeraj );
		userService.createUser( trisha );
		userService.createUser( varun );

		// create questions
		questionService.createQuestion( Level.LOW, 10 );
		questionService.createQuestion( Level.LOW, 15 );
		questionService.createQuestion( Level.LOW, 25 );
		questionService.createQuestion( Level.LOW, 20 );
		questionService.createQuestion( Level.LOW, 5 );

		questionService.createQuestion( Level.MEDIUM, 30 );
		questionService.createQuestion( Level.MEDIUM, 35 );
		questionService.createQuestion( Level.MEDIUM, 40 );
		questionService.createQuestion( Level.MEDIUM, 45 );
		questionService.createQuestion( Level.MEDIUM, 50 );

		questionService.createQuestion( Level.HIGH, 60 );
		questionService.createQuestion( Level.HIGH, 70 );
		questionService.createQuestion( Level.HIGH, 80 );
		questionService.createQuestion( Level.HIGH, 90 );
		questionService.createQuestion( Level.HIGH, 100 );

		// create contest
		contestService.createContest("contest1", Level.HIGH, neeraj);
		contestService.createContest("contest2", Level.HIGH, trisha);

		contestService.attendContest( 1, "neeraj" );
		contestService.attendContest( 1, "varun" );
		contestService.runContest(1, "trisha");


	}



}