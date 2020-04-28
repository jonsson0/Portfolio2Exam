import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        while (true) {
            System.out.println("what would u like to do?");
            System.out.println("1. If you wish to have all the students presented: enter 1");
            System.out.println("2. If you wish to have the avg grade for a student presented: enter 2");
            System.out.println("3. If you wish to have the avg grade for a course presented: enter 3");
            System.out.println("4. If you with to end the program: enter 4");
            int num = scanANumber();

            if (num == 1) {
                databaseHandler.presentStudents();
                System.out.println();

            } else if (num == 2) {
                databaseHandler.getAvgGradeFromSpecStudent();
                System.out.println();

            } else if (num == 3) {
                databaseHandler.getAvgGradeForACourse();
                System.out.println();
            } else {
                return;
            }
        }
    }

    public static int scanANumber() {
        Scanner scanner = new Scanner(System.in);

        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("sorry you need to enter an int which matches one of the options");
            scanner.next();
        }
        return scanANumber();
    }
}
