import JDBC.JdbcImp;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static JdbcImp jdbcImp = new JdbcImp();
    static void pressEnterKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("press enter to continue!");
        scanner.nextLine();
    }

    private static void selectAllStudent() {
        //load and register driver class
        try (Connection connection = jdbcImp.dataSource().getConnection()) {
            //create statement object
            String selectSql = "SELECT * FROM student";
            connection.prepareStatement(selectSql);
            PreparedStatement statement = connection.prepareStatement(selectSql);
            statement.executeQuery();
            ResultSet resultSet = statement.executeQuery();
            //create a list to store all the student
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer phoneNumber = resultSet.getInt("phone_number");
                String className = resultSet.getString("class_name");
                //add student to the list
                students.add(new Student(id, name, phoneNumber, className));
                //print out the student's information
                System.out.println("id: " + id + " name: " + name +
                        " phone number: " + phoneNumber + " class name: " + className);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectById(Student student) {
        try (Connection connection = jdbcImp.dataSource().getConnection()) {
            String selectById = "SELECT * FROM student WHERE id = ? ";
            connection.prepareStatement(selectById);
            PreparedStatement statement = connection.prepareStatement(selectById);
            statement.setInt(1, student.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Integer phoneNumber = resultSet.getInt("phone_number");
                String className = resultSet.getString("class_name");
                System.out.println("id: " + id + " name: " + name +
                        " phone_number: " + phoneNumber + " class_name: " + className);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void selectByName(Student student) {
        try (Connection connection = jdbcImp.dataSource().getConnection()) {
            String selectByName = "SELECT * FROM student WHERE name = ? ";
            connection.prepareStatement(selectByName);
            PreparedStatement statement = connection.prepareStatement(selectByName);
            statement.setString(1, student.getName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Integer phoneNumber = resultSet.getInt("phone_number");
                String className = resultSet.getString("class_name");
                System.out.println("id: " + id + " name: " + name +
                        " phone_number: " + phoneNumber + " class_name: " + className);

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertStudent(Student student) {
        try (Connection connection = jdbcImp.dataSource().getConnection()) {
            String insertSql = "INSERT INTO student (name,  phone_number, class_name) VALUES (?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setString(1, student.getName());

            statement.setInt(2, student.getPhoneNumber());

            statement.setString(3, student.getClassName());
            int inserted = statement.executeUpdate();
            System.out.println(inserted);
            if (inserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void updateStudent(Student student) {
        Scanner scanner = new Scanner(System.in);
        try (Connection connection = jdbcImp.dataSource().getConnection()) {
//            System.out.print("Enter the id -> ");
//            int tempId = scanner.nextInt();
            String updateStudent = "UPDATE student SET name = ? ,phone_number = ? , class_name = ? WHERE id =  ?";
            PreparedStatement statement = connection.prepareStatement(updateStudent);


            statement.setString(1, student.getName());
            statement.setInt(2, student.getPhoneNumber());
            statement.setString(3, student.getClassName());
            statement.setInt(4, student.getId());
            statement.executeUpdate();
            int sUpdate = statement.executeUpdate();
            if (sUpdate > 0) {
                System.out.println("student updated!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void deleteStudentById(Student student) {
        try (Connection connection = jdbcImp.dataSource().getConnection()) {
            String deleteSqlById = "DELETE FROM student WHERE id = ? ";
            PreparedStatement statement = connection.prepareStatement(deleteSqlById);
            statement.setInt(1, student.getId());
            int deletedId = statement.executeUpdate();
            System.out.println(deletedId + "was deleted!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        char check; // to hold Y and N

        int option;

        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("---- Crud operation ---");
            System.out.println("1. Add new student ");
            System.out.println("2. Select all student ");
            System.out.println("3. delete student ");
            System.out.println("4. update student");
            System.out.println("5. select student by id ");
            System.out.println("6. select student by name ");
            System.out.println("7 . exit ");
            System.out.println("choose form 1 -> 7 ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("----| Add New Student |-----");
                    do {
                        System.out.print("Enter student name : ");
                        scanner.nextLine();
                        student.setName(scanner.nextLine());

                        System.out.println("Enter phone number : ");
                        student.setPhoneNumber(scanner.nextInt());
                        scanner.nextLine();
                        System.out.println("Enter class : ");
                        student.setClassName(scanner.nextLine());
                        insertStudent(student);
                        System.out.println("Do you want to calculate it again? write y for yes and n for no :");
                        check = scanner.next().charAt(0);
                    } while (check == 'y' || check == 'Y');

                    break;
                case 2:
                    System.out.println("----| List of all student |-----");
                    selectAllStudent();
                    pressEnterKey();
                    break;
                case 3:
                    System.out.println("-----| Delete Student |-----");
                    System.out.println("Enter the id : ");
                    student.setId(scanner.nextInt());
                    deleteStudentById(student);
                    pressEnterKey();
                    break;
                case 4:
                    System.out.println("----| update student |-----");
                    System.out.println("Enter the id : ");
                    student.setId(scanner.nextInt());
                    System.out.println("Enter new name : ");
                    scanner.nextLine();
                    student.setName(scanner.nextLine());

                    System.out.println("Enter new phone number : ");
                    student.setPhoneNumber(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Enter new class");
                    student.setClassName(scanner.nextLine());

                    updateStudent(student);
                    pressEnterKey();

                    break;
                case 5:
                    System.out.println("select by id ");
                    System.out.println("Enter id  : ");
                    student.setId(scanner.nextInt());
                    selectById(student);
                    pressEnterKey();
                    break;
                case 6:
                    System.out.println("select by name ");
                    scanner.nextLine();
                    System.out.println("Enter name  : ");
                    student.setName(scanner.nextLine());
                    selectByName(student);
                    pressEnterKey();
                    break;
                case 7:
                    System.out.println("Exiting the program");
                    break;

            }
        } while (option != 7);

    }
}