import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String DELIM = "\\|";
    private static final String OUT_DELIM = "|";

    public static List<Student> loadStudents(String path) {
        List<Student> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            try {
                if (f.getParentFile() != null) f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Could not create data file: " + e.getMessage());
            }
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(DELIM);
                if (parts.length < 5) continue;
                try {
                    int roll = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String course = parts[3].trim();
                    double marks = Double.parseDouble(parts[4].trim());
                    Student s = new Student(roll, name, email, course, marks);
                    list.add(s);
                } catch (NumberFormatException ignore) {
                    // skip malformed line
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static boolean saveStudents(String path, List<Student> students) {
        File f = new File(path);
        try {
            if (f.getParentFile() != null) f.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, false))) {
                for (Student s : students) {
                    String line = s.getRollNo() + OUT_DELIM
                            + s.getName() + OUT_DELIM
                            + s.getEmail() + OUT_DELIM
                            + s.getCourse() + OUT_DELIM
                            + s.getMarks();
                    bw.write(line);
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }
}
