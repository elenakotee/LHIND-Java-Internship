public class Candidate {
	private String first;
	private String last;
	private String email;
	private String phoneNum;

	public Candidate() {}

	public Candidate(String first, String last, String email, String phoneNum) {
		this.first = first;
		this.last = last;
		this.email = email;
		this.phoneNum = phoneNum;
	}

	@Override
	public boolean equals(Object o) {

		if(o == this) {
			return true;
		}
		if(! (o instanceof Candidate)) {
			return false;
		}
		Candidate c = (Candidate) o;
		return (this.first.equals(c.first)) && (this.last.equals(c.last)) &&
				(this.email.equals(c.email)) && (this.phoneNum.equals(c.phoneNum));

	}

	@Override
	public String toString() {
		return "Name: " + first + " " + last + ", email: " + email + ", phone number: " + phoneNum;
	}
}
