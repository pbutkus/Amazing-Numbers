package numbers;

import numbers.ui.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);

        ui.start();
    }

}
