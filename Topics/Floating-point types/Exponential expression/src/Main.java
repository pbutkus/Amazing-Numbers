import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here

        double num = scanner.nextDouble();
        double answer = Math.pow(num, 3) + Math.pow(num, 2) + num + 1;

        System.out.println(answer);
    }
}