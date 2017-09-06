package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 5
 *  returns their name and a
 *  modified string 
 *  
 *  @author Bob
 *  @version 1.1
 */
public class Person5 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
  public Person5(String pname) {
    name = pname;
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		if (input == null || input == "") {
			return "";
		} else if ((input.length() == 1) || (input.length() == 2)) {
			return input;
		} else if (input.length() == 3){
			String rotated = "xxx";
			rotated.charAt(0) = input.charAt(2);
			rotated.charAt(1) = input.charAt(0);
			rotated.charAt(2) = input.charAt(1);
		} else if (input.length() == 4){
			String rotated = "xxxx";
			rotated.charAt(0) = input.charAt(2);
			rotated.charAt(1) = input.charAt(3);
			rotated.charAt(2) = input.charAt(0);
			rotated.charAt(3) = input.charAt(1);
		} else {
		    String rotated = "";
		    rotated.charAt(0) = input.charAt(input.length() - 1);
		    rotated.charAt(1) = input.charAt(input.length() - 2);
		    rotated.charAt(input.length() - 1) = input.charAt(1);
			rotated.charAt(input.length() - 2) = input.charAt(0);
		    for (int i = 2; i < input.length() - 4; i++) {
			   rotated.charAt(i) = input.charAt(i + 2);
		    }
	    }
	}
	
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}

	public static void main(String[] args) {
		Person5 bobby = new Person5("bobby");
		System.out.println(bobby.calc("shmurda"));
	}

}
