import java.util.Scanner;

// Interface for gradable things
interface Gradable {
    boolean isPassing(double grade);
    String getGradeLevel(double grade);
}

// Student class - abstract
abstract class Student implements Gradable {
    private static int counter = 0;
    private int studentId;
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.studentId = ++counter;
        this.name = name;
        this.age = age;
    }
    
    public int getStudentId() { 
        return studentId; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public int getAge() { 
        return age; 
    }
    
    public abstract String getStudentType();
    public abstract void displayStudentDetails();
}

// Regular student
class RegularStudent extends Student {
    
    public RegularStudent(String name, int age) {
        super(name, age);
    }
    
    @Override
    public String getStudentType() {
        return "Regular";
    }
    
    @Override
    public void displayStudentDetails() {
        System.out.println("ID: " + getStudentId() + " | Name: " + getName() + " | Age: " + getAge() + " | Type: " + getStudentType());
    }
    
    @Override
    public boolean isPassing(double grade) {
        if (grade >= 50) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String getGradeLevel(double grade) {
        if (grade >= 90) {
            return "A";
        } else if (grade >= 80) {
            return "B";
        } else if (grade >= 70) {
            return "C";
        } else if (grade >= 60) {
            return "D";
        } else if (grade >= 50) {
            return "E";
        } else {
            return "F";
        }
    }
}

// Honors student
class HonorsStudent extends Student {
    
    public HonorsStudent(String name, int age) {
        super(name, age);
    }
    
    @Override
    public String getStudentType() {
        return "Honors";
    }
    
    @Override
    public void displayStudentDetails() {
        System.out.println("ID: " + getStudentId() + " | Name: " + getName() + " | Age: " + getAge() + " | Type: " + getStudentType());
    }
    
    @Override
    public boolean isPassing(double grade) {
        if (grade >= 60) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String getGradeLevel(double grade) {
        if (grade >= 90) {
            return "A+";
        } else if (grade >= 80) {
            return "A";
        } else if (grade >= 70) {
            return "B";
        } else if (grade >= 60) {
            return "C";
        } else {
            return "F";
        }
    }
}

// Subject class
abstract class Subject {
    private String subjectName;
    private String subjectCode;
    
    public Subject(String subjectName, String subjectCode) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
    }
    
    public String getSubjectName() { 
        return subjectName; 
    }
    
    public String getSubjectCode() { 
        return subjectCode; 
    }
    
    public abstract String getSubjectType();
    public abstract void displaySubjectInfo();
}

// Core subject
class CoreSubject extends Subject {
    
    public CoreSubject(String subjectName, String subjectCode) {
        super(subjectName, subjectCode);
    }
    
    @Override
    public String getSubjectType() {
        return "Core";
    }
    
    @Override
    public void displaySubjectInfo() {
        System.out.println(getSubjectName() + " [" + getSubjectCode() + "] - Core Subject");
    }
}

// Elective subject
class ElectiveSubject extends Subject {
    
    public ElectiveSubject(String subjectName, String subjectCode) {
        super(subjectName, subjectCode);
    }
    
    @Override
    public String getSubjectType() {
        return "Elective";
    }
    
    @Override
    public void displaySubjectInfo() {
        System.out.println(getSubjectName() + " [" + getSubjectCode() + "] - Elective Subject");
    }
}

// Grade class
class Grade {
    private static int counter = 0;
    private int gradeId;
    private int studentId;
    private Subject subject;
    private double score;
    private String gradeLevel;
    private boolean passing;
    
    public Grade(int studentId, Subject subject, double score, String gradeLevel, boolean passing) {
        this.gradeId = ++counter;
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
        this.gradeLevel = gradeLevel;
        this.passing = passing;
    }
    
    public int getStudentId() { 
        return studentId; 
    }
    
    public Subject getSubject() { 
        return subject; 
    }
    
    public double getScore() { 
        return score; 
    }
    
    public String getGradeLevel() { 
        return gradeLevel; 
    }
    
    public boolean isPassing() { 
        return passing; 
    }
}

// Manages students
class StudentManager {
    private Student[] students;
    private int count;
    
    public StudentManager() {
        students = new Student[50];
        count = 0;
        
        // Add some default students
        students[count++] = new RegularStudent("Alice", 18);
        students[count++] = new RegularStudent("Bob", 17);
        students[count++] = new RegularStudent("Charlie", 19);
        students[count++] = new HonorsStudent("Diana", 18);
        students[count++] = new HonorsStudent("Edward", 17);
    }
    
    public void addStudent(Student student) {
        students[count++] = student;
        System.out.println("Student added! ID: " + student.getStudentId());
    }
    
    public void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        for (int i = 0; i < count; i++) {
            students[i].displayStudentDetails();
        }
    }
    
    public Student findStudent(int id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId() == id) {
                return students[i];
            }
        }
        return null;
    }
}

// Manages grades
class GradeManager {
    private Grade[] grades;
    private int count;
    
    public GradeManager() {
        grades = new Grade[100];
        count = 0;
    }
    
    public void recordGrade(Student student, Subject subject, double score) {
        String level = student.getGradeLevel(score);
        boolean pass = student.isPassing(score);
        
        grades[count++] = new Grade(student.getStudentId(), subject, score, level, pass);
        
        System.out.println("\nGrade recorded!");
        System.out.println("Student: " + student.getName());
        System.out.println("Subject: " + subject.getSubjectName());
        System.out.println("Score: " + score);
        System.out.println("Grade: " + level);
        if (pass) {
            System.out.println("Status: PASS");
        } else {
            System.out.println("Status: FAIL");
        }
    }
    
    public void viewGradeReport(Student student) {
        System.out.println("\n--- Grade Report ---");
        student.displayStudentDetails();
        System.out.println("\nGrades:");
        
        int gradeCount = 0;
        double total = 0;
        
        for (int i = 0; i < count; i++) {
            if (grades[i].getStudentId() == student.getStudentId()) {
                System.out.println(grades[i].getSubject().getSubjectName() + ": " + grades[i].getScore() + " (" + grades[i].getGradeLevel() + ")");
                total = total + grades[i].getScore();
                gradeCount++;
            }
        }
        
        if (gradeCount > 0) {
            double average = total / gradeCount;
            System.out.println("\nAverage: " + average);
        } else {
            System.out.println("No grades yet.");
        }
    }
}

// Main program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        GradeManager gradeManager = new GradeManager();
        
        System.out.println("Student Grade Management System");
        
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. View Students");
            System.out.println("2. Add Student");
            System.out.println("3. Record Grade");
            System.out.println("4. View Grade Report");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                studentManager.viewAllStudents();
                
            } else if (choice == 2) {
                System.out.println("\n1. Regular Student");
                System.out.println("2. Honors Student");
                System.out.print("Type: ");
                int type = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                
                if (type == 1) {
                    studentManager.addStudent(new RegularStudent(name, age));
                } else {
                    studentManager.addStudent(new HonorsStudent(name, age));
                }
                
            } else if (choice == 3) {
                System.out.print("Student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                
                Student student = studentManager.findStudent(id);
                if (student == null) {
                    System.out.println("Student not found!");
                    continue;
                }
                
                System.out.println("\n1. Core Subject");
                System.out.println("2. Elective Subject");
                System.out.print("Type: ");
                int type = scanner.nextInt();
                scanner.nextLine();
                
                Subject subject = null;
                if (type == 1) {
                    System.out.println("1. Math  2. English  3. Science");
                    System.out.print("Choice: ");
                    int s = scanner.nextInt();
                    scanner.nextLine();
                    if (s == 1) {
                        subject = new CoreSubject("Math", "MATH101");
                    } else if (s == 2) {
                        subject = new CoreSubject("English", "ENG101");
                    } else {
                        subject = new CoreSubject("Science", "SCI101");
                    }
                } else {
                    System.out.println("1. Music  2. Art  3. PE");
                    System.out.print("Choice: ");
                    int s = scanner.nextInt();
                    scanner.nextLine();
                    if (s == 1) {
                        subject = new ElectiveSubject("Music", "MUS101");
                    } else if (s == 2) {
                        subject = new ElectiveSubject("Art", "ART101");
                    } else {
                        subject = new ElectiveSubject("PE", "PE101");
                    }
                }
                
                System.out.print("Score (0-100): ");
                double score = scanner.nextDouble();
                scanner.nextLine();
                
                gradeManager.recordGrade(student, subject, score);
                
            } else if (choice == 4) {
                System.out.print("Student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                
                Student student = studentManager.findStudent(id);
                if (student == null) {
                    System.out.println("Student not found!");
                    continue;
                }
                
                gradeManager.viewGradeReport(student);
                
            } else if (choice == 5) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
        
        scanner.close();
    }
}