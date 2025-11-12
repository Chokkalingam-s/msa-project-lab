import java.util.HashMap;
import java.util.Map;

// ---------- ENTITY ----------
class Student {
    private int id;
    private String name;
    private String department;

    public Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', dept='" + department + "'}";
    }
}

// ---------- REPOSITORY ----------
class StudentRepository {
    private Map<Integer, Student> studentDb = new HashMap<>();

    public void save(Student student) {
        studentDb.put(student.getId(), student);
    }

    public Student findById(int id) {
        return studentDb.get(id);
    }
}

// ---------- SERVICE ----------
class StudentService {
    private StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void addStudent(Student student) {
        repo.save(student);
    }

    public Student getStudentById(int id) {
        return repo.findById(id);
    }
}

// ---------- CONTROLLER ----------
class StudentController {
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Simulated POST /students
    public void postStudent(int id, String name, String dept) {
        Student s = new Student(id, name, dept);
        service.addStudent(s);
        System.out.println("=== POST /students ===");
        System.out.println("Added Student: " + s);
    }

    // Simulated GET /students/{id}
    public void getStudent(int id) {
        Student s = service.getStudentById(id);
        System.out.println("\n=== GET /students/" + id + " ===");
        if (s != null)
            System.out.println("Fetched Student: " + s);
        else
            System.out.println("Student not found.");
    }
}

// ---------- MAIN ----------
public class Main {
    public static void main(String[] args) {
        StudentRepository repo = new StudentRepository();
        StudentService service = new StudentService(repo);
        StudentController controller = new StudentController(service);

        // Simulate REST API calls
        controller.postStudent(1, "Chokkalingam", "IT");
        controller.getStudent(1);
    }
}
