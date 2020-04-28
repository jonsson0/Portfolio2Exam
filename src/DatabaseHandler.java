import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class DatabaseHandler {

    public DatabaseHandler() {

    }

    Connection connection = null;
    String url = "jdbc:sqlite:C:\\Code\\Portfolio2Exam\\Portfolio2Examdb.db";

    public void presentStudents() throws SQLException {
        System.out.println("Here are all the students in the database:");

        connection = getConnection(url);
        Statement statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM Students";
        ResultSet rs = statement.executeQuery(sql);

        if (rs == null)
            System.out.println("No records for Students");
        while (rs != null & rs.next()) {
            int StudentID = rs.getInt("StudentID");
            String studentName = rs.getString("name");
            String address = rs.getString("address");

            System.out.println(studentName + " from " + address + " with studentID: " + StudentID);
        }
    }

    public void getAvgGradeFromSpecStudent() throws SQLException {
        int avgGradeOfStudent;

        connection = getConnection(url);

        System.out.println("Which student (id) would you like to get the avg grade of?");
        int selectedStudentID = scanANumber();

        String sql = "SELECT AVG(Grade) AS AVG FROM StudentRegistrations WHERE StudentID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, selectedStudentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        avgGradeOfStudent = rs.getInt("AVG");

        System.out.println("The avg grade for the student with the studentID: " + selectedStudentID + ": --- " + avgGradeOfStudent);
    }

    public void getAvgGradeForACourse() throws SQLException {
        int avgGradeForCourse;

        connection = getConnection(url);

        System.out.println("Which course would you like to get the avg grades of? SD-2019-Autumn (1), SD-2020-Spring (2), or ES1-2019-Autumn (3)");
        int selectedCourseID = scanANumber();

        String sql = "SELECT AVG(Grade) AS AVG FROM StudentRegistrations WHERE CourseID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (selectedCourseID == 1) {
            preparedStatement.setInt(1, 1);
        } else if (selectedCourseID == 2) {
            preparedStatement.setInt(1, 2);
        } else if (selectedCourseID == 3) {
            preparedStatement.setInt(1, 3);
        }

        ResultSet rs = null;

        try {
            rs = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        avgGradeForCourse = rs.getInt("AVG");

        System.out.println("The avg grade for the course with courseID: " + selectedCourseID + ": --- " + avgGradeForCourse);

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