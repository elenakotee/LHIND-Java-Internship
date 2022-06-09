import java.util.*;

public class SurveyResult {
	private Survey survey;
	private Candidate cand;
	private String dateTaken;
	private HashMap<Question, Answer> results;

	private int disCount;
	private int sliDisCount;
	private int sliAgCount;
	private int agCount;
	private int skipCount;

	public SurveyResult() {}

	public SurveyResult(Survey survey, Candidate cand) {
		this.survey = survey;
		this.cand = cand;
		this.results = new HashMap<>();

	}

	public void addResult(Question question, Answer answer) {
		results.put(question, answer);
		if(answer == Answer.DISAGREE) {
			disCount++;
		} else if(answer == Answer.SLIGHTLY_DISAGREE) {
			sliDisCount++;
		} else if(answer == Answer.SLIGHTLY_AGREE) {
			sliAgCount++;
		} else if(answer == Answer.AGREE) {
			agCount++;
		} else {
			skipCount++;
		}
	}

	public Answer getAnswer(Question q) {
		return results.get(q);
	}

	public Candidate getCandidate() {
		return this.cand;
	}

	public int getD() {
		return disCount;
	}
	public int getSD() {
		return sliDisCount;
	}
	public int getSA() {
		return sliAgCount;
	}
	public int getA() {
		return agCount;
	}
	public int getSkip() {
		return skipCount;
	}

	public void printSurveyResult() {
		System.out.println("Survey Results:");
		System.out.println("Title: " + survey.getTitle());
		int i = 0;
		for(Question q: results.keySet()) {
			System.out.println("Q" + (i+1) + ": " + survey.getQuestion(i).getQuestion() + "?");
			System.out.println("Answer: " + results.get(q));
			i++;
		}

	}

}
