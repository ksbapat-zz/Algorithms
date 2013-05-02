public class FizzBuzz {

	private static final int start = 1;
	private static final int end = 100;

	public static void main(String[] args) {
		print(start, end);
	}

	private static void print(int i, int j) {
		String printText = null;
		for (int num = i; num <= j; num++) {
			if (0 == num % 15) {
				printText = "FizzBuzz";
			} else if (0 == num % 3) {
				printText = "Fizz";
			} else if (0 == num % 5) {
				printText = "Buzz";
			} else {
				printText = String.valueOf(num);
			}
			System.out.println(printText);
		}
	}
}