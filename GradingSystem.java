package Assignment3;

import java.util.Scanner;

public class AcademicGrader {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Grader grader = new Grader();

        while (true) {
            System.out.println("1. Enroll Student");
            System.out.println("2. Record Grade");
            System.out.println("3. Assign Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int scholarId = input.nextInt();
                    System.out.print("Enter student name: ");
                    String scholarName = input.next();

                    Scholar scholar = new Scholar(scholarId, scholarName);
                    grader.enrollScholar(scholar);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    scholarId = input.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseId = input.nextInt();
                    System.out.print("Enter grade: ");
                    char grade = input.next().charAt(0);

                    AcademicRecord record = new AcademicRecord(scholarId, courseId, grade);
                    grader.recordGrade(record);
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    courseId = input.nextInt();
                    System.out.print("Enter course credits: ");
                    int credits = input.nextInt();

                    grader.assignCredits(courseId, credits);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    scholarId = input.nextInt();

                    double gpa = grader.calculateGPA(scholarId);
                    System.out.println("GPA: " + gpa);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}

class Scholar {
    private int scholarId;
    private String scholarName;

    public Scholar(int scholarId, String scholarName) {
        this.scholarId = scholarId;
        this.scholarName = scholarName;
    }

    public int getScholarId() {
        return scholarId;
    }

    public String getScholarName() {
        return scholarName;
    }
}

class AcademicRecord {
    private int scholarId;
    private int courseId;
    private char grade;

    public AcademicRecord(int scholarId, int courseId, char grade) {
        this.scholarId = scholarId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getScholarId() {
        return scholarId;
    }

    public int getCourseId() {
        return courseId;
    }

    public char getGrade() {
        return grade;
    }
}

class Grader {
    private Scholar[] scholars;
    private AcademicRecord[] records;
    private int[] courseCredits;
    private int scholarCount;
    private int recordCount;

    public Grader() {
        scholars = new Scholar[10];
        records = new AcademicRecord[10];
        courseCredits = new int[10];
        scholarCount = 0;
        recordCount = 0;
    }

    public void enrollScholar(Scholar scholar) {
        if (scholarCount < scholars.length) {
            scholars[scholarCount] = scholar;
            scholarCount++;
        } else {
            System.out.println("Scholar limit reached.");
        }
    }

    public void recordGrade(AcademicRecord record) {
        if (recordCount < records.length) {
            records[recordCount] = record;
            recordCount++;
        } else {
            System.out.println("Record limit reached.");
        }
    }

    public void assignCredits(int courseId, int credits) {
        for (int i = 0; i < recordCount; i++) {
            if (courseId == records[i].getCourseId()) {
                courseCredits[i] = credits;
            }
        }
    }

    public double calculateGPA(int scholarId) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < recordCount; i++) {
            if (scholarId == records[i].getScholarId()) {
                int points = gradeToPoints(records[i].getGrade());
                int credits = courseCredits[i];
                totalPoints += points * credits;
                totalCredits += credits;
            }
        }

        return totalPoints / totalCredits;
    }

    public int gradeToPoints(char grade) {
        switch (grade) {
            case 'A':
                return 4;
            case 'B':
                return 3;
            case 'C':
                return
