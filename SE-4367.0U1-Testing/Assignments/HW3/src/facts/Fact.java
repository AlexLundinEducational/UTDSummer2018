package facts;

public class Fact {

	private String author;
	private String type;
	private String text;

	// HW3 addition
	// validateFactData
	// author and text must be non empty Strings
	// type can only be the Fact or Fallacy String
	public int validateFactData(){
		int errorCode = 0;
		
		String emptyString = "";
		String factString = "Fact";;
		String fallacyString = "Fallacy";
		
		if(this.author == emptyString ){
			errorCode = 1;
		}

		if(this.text == emptyString){
			errorCode = 2;
		}
		
		// 2 step check
		// first for nulls
		if ((this.type == null) || (this.type == null)){
			return errorCode = 3;
		}
		
		// second for valid strings
		if(!this.type.equals(factString) && !this.type.equals(fallacyString)){
			errorCode = 3;
		}
			


		
		return errorCode;
	}
	// HW3 addition value constructor
	public Fact(String author, String type, String text) {
		this.author=author;
		this.type=type;
		this.text=text;
	}
	
	// HW3 addition 
	// toString method
	public String toString(){
		String tempString = "";
		
		System.out.println("Fact toString method:");
		System.out.println(this.author);
		System.out.println(this.type);
		System.out.println(this.text);
		
		return tempString;
	}
	
	public Fact() {}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	
	public void setText(String text) {
		this.text = text;
	}




}
