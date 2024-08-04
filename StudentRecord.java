package Assignment;

import java.util.Scanner;

public class AcademicRegistry {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RegistryManager registry = new RegistryManager();

        while (true) {
            System.out.println("Enter your preference:");
            System.out.println("1. Enroll Scholar");
            System.out.println("2. Retrieve Scholar by ID");
            System.out.println("3. Display all Scholars");
            System.out.println("4. Exit");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    registry.enrollScholar();
                    break;
                case 2:
                    System.out.println("Enter Scholar ID:");
                    int scholarId = input.nextInt();
                    Scholar scholar = registry.getScholar(scholarId);
                    if (scholar != null) {
                        System.out.println(scholar);
                    } else {
                        System.out.println("Scholar not found");
                    }
                    break;
                case 3:
                    registry.displayAllScholars();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Scholar {
    private int scholarId, age;
    private String faculty, name;

    public Scholar(int id, int age, String faculty, String name) {
        this.scholarId = id;
        this.age = age;
        this.faculty = faculty;
        this.name = name;
    }

    public int getScholarId() {
        return scholarId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFaculty() {
        return faculty;
    }

    public String toString() {
        return "ScholarId: " + scholarId + ", Name: " + name + ", Age: " + age + ", Faculty: " + faculty;
    }
}

class RegistryManager {
    Scanner scanner = new Scanner(System.in);

    private int scholarCount;
    private Scholar[] scholars;

    public void enrollScholar() {
        System.out.println("Enter number of scholars:");
        scholarCount = scanner.nextInt();
        scholars = new Scholar[scholarCount];

        for (int i = 0; i < scholarCount; i++) {
            System.out.println("Enter scholar ID:");
            int id = scanner.nextInt();
            System.out.println("Enter scholar age:");
            int age = scanner.nextInt();
            System.out.println("Enter scholar faculty:");
            String faculty = scanner.next();
            System.out.println("Enter scholar name:");
            String name = scanner.next();
            scholars[i] = new Scholar(id, age, faculty, name);
        }
    }

    public Scholar getScholar(int id) {
        for (int i = 0; i < scholarCount; i++) {
            if (scholars[i].getScholarId() == id) {
                return scholars[i];
            }
        }
        return null;
    }

    public void displayAllScholars() {
        for (int i = 0; i < scholarCount; i++) {
            System.out.println(scholars[i]);
        }
    }
}
