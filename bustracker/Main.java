package bustracker;

public class Main {
	public static void main(String[] args) {

		ModelManager modelManager = new ModelManager();

		modelManager.close();
		System.out.println("Application Finished");
	}
}