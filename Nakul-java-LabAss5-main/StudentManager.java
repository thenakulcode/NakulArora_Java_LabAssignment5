import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StudentManager implements RecordActions {

    private List<Student> students = new ArrayList<>();

    // Load initial data from file
    public void initializeFromList(List<Student> loaded) {
        if (loaded != null) {
            students.addAll(loaded);
        }
    }

    @Override
    public void addStudent(Student s) throws InvalidInputException {
        for (Student st : students) {
            if (st.getRollNo() == s.getRollNo()) {
                throw new InvalidInputException("Duplicate Roll Number not allowed!");
            }
        }
        students.add(s);
    }

    @Override
    public void deleteStudentByName(String name) throws StudentNotFoundException {
        Iterator<Student> it = students.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name)) {
                it.remove();
                found = true;
                break;
            }
        }

        if (!found) throw new StudentNotFoundException("Student not found with name: " + name);
    }

    @Override
    public void updateStudent(int rollNo, Student newData) throws StudentNotFoundException {
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNo() == rollNo) {
                students.set(i, newData);
                found = true;
                break;
            }
        }

        if (!found) throw new StudentNotFoundException("Cannot update. Roll No not found: " + rollNo);
    }

    @Override
    public Student searchStudentByName(String name) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student not found with name: " + name);
    }

    @Override
    public List<Student> viewAllStudents() {
        return students;
    }

    public void sortByMarksDescending() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }
}
