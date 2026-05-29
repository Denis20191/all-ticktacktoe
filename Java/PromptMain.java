import java.util.Scanner;

public class PromptMain {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PromptTickTackToe game = new PromptTickTackToe();
		game.startGame(in);
	}
}
