import java.util.List;

public interface RecordActions {
    void addStudent(Student s) throws Exception;
    void deleteStudentByName(String name) throws Exception;
    void updateStudent(int rollNo, Student newData) throws Exception;
    Student searchStudentByName(String name) throws Exception;
    List<Student> viewAllStudents();
}
