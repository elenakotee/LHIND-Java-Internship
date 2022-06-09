import java.util.*;

public class Survey {
	private String title;
	private String topic;
	private String desc;
	private ArrayList<Question> questions;
	private ArrayList<Question> delQuestions;

	public Survey() {}

	public Survey(String title, String topic, String desc) {
		this.title = title;
		this.topic = topic;
		this.desc = desc;
		this.questions = new ArrayList<>();
		this.delQuestions = new ArrayList<>();
	}

	public Survey(String title, String topic, String desc, ArrayList<Question> questions) {
		this.title = title;
		this.topic = topic;
		this.desc = desc;
		this.questions = questions;
		this.delQuestions = new ArrayList<>();
	}

	public void addQuestion(Question q) {
		this.questions.add(q);
	}

	public int getQuestionNum() {
		return this.questions.size();
	}

	public void removeQuestion(Question q, String date) {
		int i = 0;
		for(Question question:questions) {
			if(q.getQuestion().equals(question.getQuestion())) {
				question.deleteQuestion(date);
				delQuestions.add(question);
				this.questions.remove(i);
				break;
			}
			i++;
		}
	}

	public Question getQuestion(int i) {
		return this.questions.get(i);
	}

	public String getTitle() {
		return this.title;
	}

	public String getTopic() {
		return this.topic;
	}

	public String getDescription() {
		return this.desc;
	}

	public void printQuestion(int i) {
		System.out.println(this.questions.get(i).getQuestion() + "?");
	}

	@Override
	public boolean equals(Object o) {

		if(o == this) {
			return true;
		}
		if(! (o instanceof Survey)) {
			return false;
		}
		Survey c = (Survey) o;
		return (this.title.equals(c.title)) && (this.topic.equals(c.topic)) &&
				(this.desc.equals(c.desc)) && (this.questions.equals(c.questions));

	}
}





