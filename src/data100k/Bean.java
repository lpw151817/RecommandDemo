package data100k;

public class Bean {
	private int userid;
	private double similarity;

	public Bean(int userid, double similarity) {
		super();
		this.userid = userid;
		this.similarity = similarity;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
