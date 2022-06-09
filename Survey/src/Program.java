import java.util.*;

public class Program {
	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the survey application!");
		System.out.println("What do you want to do?");
		System.out.println("0: Exit");
		System.out.println("1: Create a new survey");
		int choice = scnr.nextInt();

		Survey surv = new Survey();
		Candidate cand;
		ArrayList<SurveyResult> allResults = new ArrayList<>();

		while(choice != 0) {
			switch(choice) {
				//create new survey
				case 1:
					surv = createSurvey(scnr);
					allResults.clear();
					System.out.println("Survey created.");
					break;
				//take survey as a candidate
				case 2:
					cand = createCandidate(scnr);
					allResults.add(takeSurvey(scnr, surv, cand)); //add result to list of results
					break;
				//find most given answer on survey
				case 3:
					String answer = findMostGivenAnswer(allResults);
					System.out.println("The most given answer on the survey across all candidates is " + answer);
					break;
				//Print survey's results
				case 4:
					printSurveyResults(allResults, surv);
					break;

				//Find answers given by candidate on survey
				case 5:
					printCandidateAnswers(allResults, scnr);
					break;

				//Find candidate that took the most surveys
				case 6:
					findCandMostSurveys(allResults);
					break;

				//Add remove question from survey
				case 7:
					addRemoveQ(surv, scnr);
					break;

			}
			printPrompts();
			choice = scnr.nextInt();
		}
		System.out.println("Goodbye!");
	}


	//creates new survey makes sure question number bounds are met
	private static Survey createSurvey(Scanner scnr) {
		System.out.println("Enter survey title below:");
		scnr.nextLine();
		String title = scnr.nextLine();
		System.out.println("Enter survey topic below:");
		String topic = scnr.nextLine();
		System.out.println("Enter survey description below:");
		String description = scnr.nextLine();

		//get numQuestions and verify it's within bounds
		int qNum;
		while(true) {
			System.out.println("Enter number of questions your survey will have (must be between 10 and 40)");
			qNum = scnr.nextInt();
			if (qNum < 10 || qNum > 40) {
				System.out.println("Can't have " + qNum + " questions. Reenter number of questions");
			} else {
				break;
			}
		}
		//add input questions to list
		System.out.println("Enter survey question below:");
		ArrayList<Question> questions = new ArrayList<>();
		scnr.nextLine();
		for(int i = 1; i <= qNum ; i++) {
			System.out.print("Q" + i + ":");
			questions.add(new Question(scnr.nextLine()));
		}

		return new Survey(title, topic, description, questions);
	}


	//created new candidate
	private static Candidate createCandidate(Scanner scnr) {
		scnr.nextLine();
		System.out.println("Enter your first name:");
		String first = scnr.nextLine();
		System.out.println("Enter your last name:");
		String last = scnr.nextLine();
		System.out.println("Enter your email:");
		String email = scnr.nextLine();
		System.out.println("Enter your phone number:");
		String phoneNum = scnr.nextLine();
		return new Candidate(first, last, email, phoneNum);
	}


	//candidate takes the survey as its questions get printed and the results are recorded
	private static SurveyResult takeSurvey(Scanner scnr, Survey surv, Candidate cand) {
		SurveyResult result = new SurveyResult(surv, cand);
		String choice;
		Answer ans;
		int numQ = surv.getQuestionNum();
		//loop through questions and get answers
		System.out.println("Welcome to the survey!");
		System.out.println("Title: " + surv.getTitle());
		System.out.println("Topic: " + surv.getTopic());
		System.out.println("Description: " + surv.getDescription());
		System.out.println("Please answer the following questions by selecting one of the alternatives:");

		for(int i = 0; i < numQ; i++) {
			surv.printQuestion(i);
			printAlternatives();
			choice = scnr.nextLine();
			ans = choiceToAnswer(choice);
			result.addResult(surv.getQuestion(i), ans);
		}
		System.out.println("Thank you for taking the survey!");
		return result;
	}


	//converts alternative choice to an answer type
	private static Answer choiceToAnswer(String choice) {
		return switch (choice) {
			case "a" -> Answer.DISAGREE;
			case "b" -> Answer.SLIGHTLY_DISAGREE;
			case "c" -> Answer.SLIGHTLY_AGREE;
			case "d" -> Answer.AGREE;
			default -> Answer.SKIP;
		};
	}


	//prints alternatives user can choose from
	private static void printAlternatives() {
		System.out.println("a: Disagree");
		System.out.println("b: Slightly disagree");
		System.out.println("c: Slightly agree");
		System.out.println("d: Agree");
		System.out.println("e: Skip question");
	}

	private static int[] getTotalAnswers(ArrayList<SurveyResult> results) {
		int d = 0;
		int sd = 0;
		int sa = 0;
		int a = 0;
		int skip = 0;
		for (SurveyResult result : results) {
			d += result.getD();
			sd += result.getSD();
			sa += result.getSA();
			a += result.getA();
			skip += result.getSkip();
		}
		return new int[] {d, sd, sa, a, skip};
	}


	//finds most given answer on an entire survey across all candidates
	private static String findMostGivenAnswer(ArrayList<SurveyResult> results) {
		int[] arr = getTotalAnswers(results);
		int max = Math.max(Math.max(arr[0],arr[1]),Math.max(arr[2],arr[3]));
		if(max == arr[0]) {
			return "Disagree";
		} else if (max == arr[1]) {
			return "Slightly disagree";
		} else if (max == arr[2]) {
			return "Slightly agree";
		} else {
			return "Agree";
		}
	}

	//prints number of each answer for each question on survey
	private static void printSurveyResults(ArrayList<SurveyResult> results, Survey surv) {

		System.out.println("The survey's results are:");
		int[] arr;
		for(int i = 0; i < surv.getQuestionNum(); i++) {
			System.out.println("Question " + (i+1) + ": " + surv.getQuestion(i).getQuestion() + "?");
			arr = getQuestionAnswers(results, surv.getQuestion(i));
			System.out.println(arr[0] + " answered Disagree");
			System.out.println(arr[1] + " answered Slightly disagree");
			System.out.println(arr[2] + " answered Slightly agree");
			System.out.println(arr[3] + " answered Agree");
			System.out.println(arr[4] + " chose not to answer");
			System.out.println();
		}

	}

	//finds num of each answer for question across all results
	private static int[] getQuestionAnswers(ArrayList<SurveyResult> results, Question q) {
		int d = 0;
		int sd = 0;
		int sa = 0;
		int a = 0;
		int s = 0;

		for(SurveyResult result:results) {
			Answer ans = result.getAnswer(q);
			if(ans == Answer.DISAGREE) {
				d++;
			} else if(ans == Answer.SLIGHTLY_DISAGREE) {
				sd++;
			} else if(ans == Answer.SLIGHTLY_AGREE) {
				sa++;
			} else if(ans == Answer.AGREE) {
				a++;
			} else {
				s++;
			}
		}
		return new int[] {d, sd, sa, a, s};
	}

	private static void printCandidateAnswers(ArrayList<SurveyResult> results, Scanner scnr) {
		scnr.nextLine();
		System.out.println("Please enter the candidate's first name");
		String first = scnr.nextLine();
		System.out.println("Please enter the candidate's1 last name");
		String last = scnr.nextLine();
		System.out.println("Please enter the candidate's email");
		String email = scnr.nextLine();
		System.out.println("Please enter the candidate's phone number");
		String phoneNum = scnr.nextLine();
		boolean found = false;

		Candidate cand = new Candidate(first, last, email, phoneNum);
		for(SurveyResult result:results) {
			if(result.getCandidate().equals(cand)) {
				found = true;
				result.printSurveyResult();
			}
		}
		if(!found) {
			System.out.println("No such candidate found");
		}
	}

	private static void findCandMostSurveys(ArrayList<SurveyResult> results) {
		ArrayList<Candidate> cands = new ArrayList<>();
		int[] arr = new int[100];
		Candidate cand;
		boolean found = false;

		for(SurveyResult result:results) {
			cand = result.getCandidate();
			for(Candidate c:cands) {
				if(cand.equals(c)) {
					arr[cands.indexOf(c)]++;
					found = true;
					break;
				}
			}
			if(!found) {
				cands.add(cand);
				arr[cands.indexOf(cand)]++;
			}
			found = false;
		}
		int max = arr[0];
		int maxIndex = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
				maxIndex = i;
			}
		}
		System.out.println("The candidate that took the most surveys is " + cands.get(maxIndex) +
				". They completed " + max + " survey(s).");
	}


	private static void addRemoveQ(Survey surv, Scanner scnr) {
	System.out.println("If you want to add a question, type 'A', if you want to remove " +
			"a question type 'R':");
	String choice = scnr.next();
	//adding question
	if(choice.equals("A")) {
		if(surv.getQuestionNum() == 40) {
			System.out.println("You cannot have more than 40 questions on a survey.");
		} else {
			scnr.nextLine();
			System.out.println("Please enter the question you want to add below:");
			surv.addQuestion(new Question(scnr.nextLine()));
			System.out.println("Question was added successfully.");
		}
	//removing question
	} else if (choice.equals("R")) {
		if(surv.getQuestionNum() == 10) {
			System.out.println("You cannot have less than 10 questions on a survey.");
		} else {
			scnr.nextLine();
			System.out.println("Please enter the question you want to remove below:");
			surv.removeQuestion(new Question(scnr.nextLine()), "date");
			System.out.println("Question was removed successfully.");
		}

	} else {
		System.out.println("You did not type 'A' or 'R'.");
		}
	}

	//print prompts given to user
	private static void printPrompts() {
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("0: Exit");
		System.out.println("1: Create a new survey");
		System.out.println("2: Fill out survey as a candidate");
		System.out.println("3: Find most given answer on survey");
		System.out.println("4: Print survey's result");
		System.out.println("5: Find answers given by a candidate on survey");
		System.out.println("6: Find candidate who has taken the most surveys");
		System.out.println("7: Add/remove a question from survey");

	}
}
