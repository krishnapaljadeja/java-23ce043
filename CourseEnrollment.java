package Assignment2;

import java.util.Scanner;

class AcademicCourse {
    private int courseId;
    private String courseName;
    private int credits;

    public AcademicCourse(int courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String toString() {
        return "Course ID: " + getCourseId() + "   Course Name: " + getCourseName() + "   Credits: " + getCredits();
    }
}

class EnrollmentManager {
    private int[][] studentCourses;
    private int count;

    public EnrollmentManager(int maxStudents, int maxCourses) {
        studentCourses = new int[maxStudents][maxCourses];
        count = 0;
    }

    public void enrollStudent(int studentId, int courseId) {
        studentCourses[studentId][count] = courseId;
        count++;
    }

    public void dropCourse(int studentId, int courseId) {
        for (int i = 0; i < count; i++) {
            if (studentCourses[studentId][i] == courseId) {
                studentCourses[studentId][i] = studentCourses[studentId][count - 1];
                studentCourses[studentId][count - 1] = 0;
                count--;
                break;
            }
        }
    }

    private AcademicCourse getCourseById(int courseId, AcademicCourse[] courseCatalog) {
        for (AcademicCourse course : courseCatalog) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }

    public void getEnrolledCourses(int studentId, AcademicCourse[] courseCatalog) {
        System.out.println("Courses enrolled by student " + studentId + ":");
        for (int courseId : studentCourses[studentId]) {
            if (courseId!= 0) {
                AcademicCourse course = getCourseById(courseId, courseCatalog);
                if (course!= null) {
                    System.out.println(course);
                }
            }
        }
    }
}

public class CourseEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AcademicCourse[] courses = {
            new AcademicCourse(1, "Mathematics", 3),
            new AcademicCourse(2, "Science", 4),
            new AcademicCourse(3, "History", 2)
        };
        EnrollmentManager enrollmentManager = new EnrollmentManager(100, 10);

        int choice, studentId, courseId;

        do {
            System.out.println("1: Enroll in a course");
            System.out.println("2: Get enrolled courses");
            System.out.println("3: Drop a course");
            System.out.println("4: Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextInt();
                    enrollmentManager.enrollStudent(studentId, courseId);
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextInt();
                    enrollmentManager.getEnrolledCourses(studentId, courses);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    courseId = scanner.nextInt();
                    enrollmentManager.dropCourse(studentId, courseId);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice!= 4);
    }
}
