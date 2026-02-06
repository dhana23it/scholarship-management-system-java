import java.util.*;

class InvalidInputException extends Exception {
    InvalidInputException(String msg) {
        super(msg);
    }
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void display() {
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }
}

class Student extends Person {
    String studentId;
    String grade;

    Student(String studentId, String name, int age, String grade) {
        super(name, age);
        this.studentId = studentId;
        this.grade = grade;
    }

    @Override
    void display() {
        super.display();
        System.out.println("Student ID : " + studentId);
        System.out.println("Grade      : " + grade);
    }
}

class Scholarship extends Student {
    String scholarshipId;
    String scholarshipName;
    double amount;

    Scholarship(String scholarshipId, String scholarshipName, double amount,
                String studentId, String name, int age, String grade) {
        super(studentId, name, age, grade);
        this.scholarshipId = scholarshipId;
        this.scholarshipName = scholarshipName;
        this.amount = amount;
    }

    @Override
    void display() {
        System.out.println("\n--- Scholarship Details ---");
        System.out.println("Scholarship ID   : " + scholarshipId);
        System.out.println("Scholarship Name : " + scholarshipName);
        System.out.println("Amount           : " + amount);
        System.out.println("--- Student Details ---");
        super.display();
    }
}

public class ScholarshipManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Scholarship> list = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Add Scholarship");
            System.out.println("2. View Scholarships");
            System.out.println("3. Apply for Scholarship");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        addScholarship();
                        break;
                    case 2:
                        viewScholarships();
                        break;
                    case 3:
                        applyScholarship();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        throw new InvalidInputException("Invalid choice");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 4);
    }

    static void addScholarship() throws InvalidInputException {
        System.out.print("Scholarship ID: ");
        String sid = sc.nextLine();

        System.out.print("Scholarship Name: ");
        String sname = sc.nextLine();

        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0)
            throw new InvalidInputException("Invalid amount");

        System.out.print("Student ID: ");
        String stid = sc.nextLine();

        System.out.print("Student Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Grade: ");
        String grade = sc.nextLine();

        Scholarship s = new Scholarship(sid, sname, amount, stid, name, age, grade);
        list.add(s);

        System.out.println("Scholarship added");
    }

    static void viewScholarships() {
        if (list.isEmpty()) {
            System.out.println("No scholarships available");
            return;
        }
        for (Scholarship s : list) {
            s.display();
        }
    }

    static void applyScholarship() throws InvalidInputException {
        System.out.print("Enter Scholarship ID: ");
        String id = sc.nextLine();

        for (Scholarship s : list) {
            if (s.scholarshipId.equalsIgnoreCase(id)) {
                System.out.println("Application successful");
                s.display();
                return;
            }
        }
        throw new InvalidInputException("Scholarship not found");
    }
}
