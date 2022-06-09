public class Question {

	private String question;
	private String subDate;
	private String delDate;
	private boolean isDeleted;


	public Question() {}

	public Question(String question) {
		this.question = question;
		isDeleted = false;
	}

	public void deleteQuestion(String delDate) {
		this.delDate = delDate;
		isDeleted = true;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public String getQuestion() {
		return this.question;
	}

}
