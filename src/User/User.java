package User;

import Theatre.ModelController;
import Theatre.Movie;

public class User {

	ModelController modelController;

	public User(){
		modelController = new ModelController();
	}

	public static void main(String[] args) {
		User user = new User();
		// Example movie object
		Movie m = user.modelController.getMovieById(1);
		System.out.println("Done");
	}

}
