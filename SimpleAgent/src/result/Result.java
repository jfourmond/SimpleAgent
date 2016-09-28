package result;

public class Result {
	private Results result;
	
	//	CONSTRUCTEURS
	public Result() { result = null; }
	
	public Result(Results result) { this.result = result; }
	
	//	GETTERS
	public Results getResult() { return result; }
	
	//	SETTERS
	public void setResult(Results result) { this.result = result; }
	
	//	METHODES
	@Override
	public String toString() {
		return "Result : " + result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		else {
			Result result = (Result) obj;
			return (this.result.equals(result.getResult()));
		}
	}
}
