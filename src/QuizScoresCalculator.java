import java.util.Scanner;

public class QuizScoresCalculator {

	static Scanner scan = new Scanner(System.in);
	
	static int scoresPerStudent = 0; // to be specified by user with scanner
	static String studentName = ""; // each name entered with scanner; each stored in studentNames array
	static int scoreCounter = 0; // counts total number of all class scores
	static int quizNumber = 0; // specifies quiz number taken by students
	
	static String[] studentNames = new String[3]; // stores student names
	
	static double[] quizScoresStudent1;
	static double[] quizScoresStudent2;
	static double[] quizScoresStudent3; // three score arrays corresponding to three students, parallel with studentNames
	
	static double classTotal = 0;
	static double classAverage = 0; // classTotal used to sum up all quiz scores; classAverage for average of all
	
	static double studentAverage = 0; // calculates average of all quizzes for a particular student
	static double quizAverage = 0; // calculates the average score by all students for one particular quiz
	
	static double quizScore = 0; // specified by user input; determines the values of the quizScores arrays
	
	public static void main(String[] args) {

		numberofScores(); // calls method taking in user input for total number of quizzes
		
		quizScoresStudent1 = new double[scoresPerStudent];
		quizScoresStudent2 = new double[scoresPerStudent];
		quizScoresStudent3 = new double[scoresPerStudent]; // initializing quizScores arrays, with size being specified by user in the numberofScores method
		
		namesScoresInput(); // calls method taking in user input for all students and quiz scores

		averageMenu(); // calls method opening up a menu to calculate the class, student, or quiz average
		
		scan.close();
		
	}
	
	public static void numberofScores() // takes in user input for total number of quizzes
	{
		System.out.printf("How many scores per student? ");
		scoresPerStudent = Integer.parseInt(scan.nextLine());
	} // end numberofScores method
	
	public static void namesScoresInput() // takes in user input for all students and quiz scores
	{
		for(int i = 0; i < 3; i++)
		{
			System.out.printf("\nEnter name for student %d: ", i+1);
			studentName = scan.nextLine();
			studentNames[i] = studentName;
			System.out.printf("\nEntering scores for %s\n", studentName.toUpperCase());
			
			for(int j = 0; j < scoresPerStudent; j++)
			{
				System.out.printf("Quiz %d: ", j+1);
				quizScore = Double.parseDouble(scan.nextLine());
				if(i == 0)
					{
						quizScoresStudent1[j] = quizScore;
					}
				else if(i == 1)
					{
						quizScoresStudent2[j] = quizScore;
					}
				else if(i == 2)
					{
						quizScoresStudent3[j] = quizScore;
					}
				classTotal += quizScore;
				scoreCounter++;
			}
		}
	} // end namesScoresInput method
	
	public static void averageMenu() // opens up a menu to calculate the class, student, or quiz average (by calling the method of the respective choice), which is contained within a do while loop
	{
		String choiceNumber = "";
		do
		{
			studentAverage = 0;
			quizAverage = 0;
			studentName = "";
		
			System.out.println("\n\n\n\n\n\t\tMenu");
			System.out.println("1. Class Average");
			System.out.println("2. Student Average");
			System.out.println("3. Quiz Average");
			System.out.print("\nEnter choice number, or x to exit: ");
		
			choiceNumber = scan.nextLine();
		
			switch(choiceNumber)
			{
				case "1":
					classAverageCalculator();
					break;
				
				case "2":
					studentAverageCalculator();
					break;
				
				case "3":
					quizAverageCalculator();
					break;
				
				default:
					break;
			}
		
			if(!(choiceNumber.equalsIgnoreCase("x"))) 
			{
				System.out.print("\nPress Enter to continue...");
				scan.nextLine();
			}
		
		} while(!(choiceNumber.equalsIgnoreCase("x")));
	} // end averageMenu method
	
	public static void classAverageCalculator() // calculates the class average for all quizzes and students combined
	{
		classAverage = classTotal/scoreCounter;
		System.out.printf("Class average for all quizzes is %.2f\n", classAverage);
	} // end classAverageCalculator method
	
	public static void studentAverageCalculator() // calculates the average on all quizzes for a particular student
	{
		System.out.println("\nCalculating average by student.\n");
		System.out.print("Enter student name: ");
		studentName = scan.nextLine();
		
		if(!(studentName.equalsIgnoreCase(studentNames[0])) && !(studentName.equalsIgnoreCase(studentNames[1])) && !(studentName.equalsIgnoreCase(studentNames[2]))) {
			System.out.println("Student not found.");
		}
		
		else 
		{
			System.out.printf("%s's scores are: ", studentName);
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < scoresPerStudent; j++)
				{
					if(studentName.equalsIgnoreCase(studentNames[i]) && i == 0) {
						System.out.printf("%.2f ", quizScoresStudent1[j]);
						studentAverage += quizScoresStudent1[j];
					}
					else if(studentName.equalsIgnoreCase(studentNames[i]) && i == 1) {
						System.out.printf("%.2f ", quizScoresStudent2[j]);
						studentAverage += quizScoresStudent2[j];
					}
					else if(studentName.equalsIgnoreCase(studentNames[i]) && i == 2) {
						System.out.printf("%.2f ", quizScoresStudent3[j]);
						studentAverage += quizScoresStudent3[j];
					}
				}
			}
		}
		
		studentAverage /= scoresPerStudent;
		if((studentName.equalsIgnoreCase(studentNames[0])) || (studentName.equalsIgnoreCase(studentNames[1])) || (studentName.equalsIgnoreCase(studentNames[2])))
		{
			System.out.printf("\n%s's average is %.2f\n", studentName, studentAverage);
		}
	} // end studentAverageCalculator method
	
	public static void quizAverageCalculator() // calculates the average on a particular quiz by all students
	{
		System.out.println("\nCalculating average by Quiz Number");
		System.out.print("Enter Quiz number: ");
		quizNumber = Integer.parseInt(scan.nextLine());
		
		for(int i = 0; i < scoresPerStudent; i++)
		{
			if((quizNumber == 1 && i == 0) || (quizNumber == 2 && i == 1) || (quizNumber == 3 && i == 2) || (quizNumber == 4 && i == 3) || (quizNumber == 5 && i == 4))
			{
				quizAverage = quizScoresStudent1[i] + quizScoresStudent2[i] + quizScoresStudent3[i];
			}
		}
		
		quizAverage /= 3;
		System.out.printf("Quiz %d average is %.2f\n", quizNumber, quizAverage);
	} // end quizAverageCalculator method

} // end class