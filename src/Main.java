import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        try {
            Calculator.calculator(str);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
