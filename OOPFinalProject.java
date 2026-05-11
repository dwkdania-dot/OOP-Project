/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oopprojecttest;

// ============================================================
//  SMART UNIVERSITY SYSTEM
//  Single-file NetBeans project
//  Includes: All classes, Swing GUI, File Handling (Save/Load)
// ============================================================

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;


// ============================================================
//  SECTION 1 — CORE MODEL CLASSES
// ============================================================

// Abstract base class for all persons
abstract class Person {
    protected String name;
    protected int    age;
    protected String cnic;

    public Person(String name, int age, String cnic) {
        this.name = name;
        this.age  = age;
        this.cnic = cnic;
    }

    public abstract void displayInfo();
    public String getName() { return name; }
}

// ── Student ──────────────────────────────────────────────────
class Student extends Person {
    private String       studentID;
    private double       gpa;
    private List<Course> enrolledCourses;

    public Student(String name, int age, String cnic, String studentID) {
        super(name, age, cnic);
        this.studentID      = studentID;
        this.enrolledCourses = new ArrayList<>();
    }

    public void   registerCourse(Course c)  { enrolledCourses.add(c); }
    public void   setGpa(double gpa)        { this.gpa = gpa; }
    public double getGpa()                  { return gpa; }
    public String getStudentID()            { return studentID; }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + " | ID: " + studentID + " | GPA: " + gpa);
    }

    // Convert to CSV line for file saving
    public String toCsv() {
        return name + "," + age + "," + cnic + "," + studentID + "," + gpa;
    }

    // Rebuild a Student object from a saved CSV line
    public static Student fromCsv(String line) {
        String[] p  = line.split(",");
        Student  st = new Student(p[0], Integer.parseInt(p[1]), p[2], p[3]);
        st.setGpa(Double.parseDouble(p[4]));
        return st;
    }
}

// ── Teacher ───────────────────────────────────────────────────
class Teacher extends Person {
    private String designation;
    private String subject;

    public Teacher(String name, int age, String cnic, String designation, String subject) {
        super(name, age, cnic);
        this.designation = designation;
        this.subject     = subject;
    }

    public String getDesignation() { return designation; }
    public String getSubject()     { return subject; }

    @Override
    public void displayInfo() {
        System.out.println("Teacher: " + name + " | " + designation + " | Subject: " + subject);
    }

    public String toCsv() {
        return name + "," + age + "," + cnic + "," + designation + "," + subject;
    }

    public static Teacher fromCsv(String line) {
        String[] p = line.split(",");
        return new Teacher(p[0], Integer.parseInt(p[1]), p[2], p[3], p[4]);
    }
}

// ── Admin ─────────────────────────────────────────────────────
class Admin extends Person {
    private String employeeID;

    public Admin(String name, int age, String cnic, String employeeID) {
        super(name, age, cnic);
        this.employeeID = employeeID;
    }

    public void generateReport() { System.out.println("Admin generating report..."); }

    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name + " | ID: " + employeeID);
    }
}

// ── Course ────────────────────────────────────────────────────
class Course {
    private String courseCode;
    private int    creditHours;

    public Course(String courseCode, int creditHours) {
        this.courseCode  = courseCode;
        this.creditHours = creditHours;
    }

    public String getCourseCode()  { return courseCode; }
    public int    getCreditHours() { return creditHours; }

    public void displayCourse() {
        System.out.println("Course: " + courseCode + " | Credit Hours: " + creditHours);
    }

    public String toCsv() { return courseCode + "," + creditHours; }

    public static Course fromCsv(String line) {
        String[] p = line.split(",");
        return new Course(p[0], Integer.parseInt(p[1]));
    }
}

// ── Department ────────────────────────────────────────────────
class Department {
    private String       departmentName;
    private List<Course> courses;
    private Teacher      teacher;

    public Department(String name) {
        this.departmentName = name;
        courses = new ArrayList<>();
    }

    public void    addCourse(Course c)     { courses.add(c); }
    public void    assignTeacher(Teacher t){ this.teacher = t; }
    public String  getName()               { return departmentName; }
    public Teacher getTeacher()            { return teacher; }

    public void displayDepartment() {
        System.out.println("Department: " + departmentName);
        if (teacher != null) teacher.displayInfo();
        for (Course c : courses) c.displayCourse();
    }
}

// ── University ────────────────────────────────────────────────
class University {
    private String           uniName;
    private List<Department> departments;

    public University(String name) {
        this.uniName    = name;
        departments = new ArrayList<>();
    }

    public void addDepartment(Department d) { departments.add(d); }
    public String getName()                  { return uniName; }

    public void displayUniversity() {
        System.out.println("University: " + uniName);
        for (Department d : departments) d.displayDepartment();
    }
}


// ============================================================
//  SECTION 2 — ACADEMIC CLASSES
// ============================================================

// Enrollment links a student to a course
class Enrollment {
    private Student student;
    private Course  course;

    public Enrollment(Student s, Course c) {
        this.student = s;
        this.course  = c;
    }

    public Student getStudent() { return student; }
    public Course  getCourse()  { return course; }

    public void showEnrollment() {
        System.out.println(student.getName() + " enrolled in " + course.getCourseCode());
    }
}

// Result calculates grade and GPA from marks
class Result {
    private Enrollment enrollment;
    private double     marks;
    private char       grade;
    private double     gpa;

    public Result(Enrollment e) { this.enrollment = e; }

    public void calculateResult(double marks) {
        this.marks = marks;
        if      (marks >= 85) { grade = 'A'; gpa = 4.0; }
        else if (marks >= 75) { grade = 'B'; gpa = 3.5; }
        else if (marks >= 65) { grade = 'C'; gpa = 3.0; }
        else                  { grade = 'F'; gpa = 0.0; }
    }

    public double getGpa()   { return gpa; }
    public char   getGrade() { return grade; }
    public double getMarks() { return marks; }

    public void displayResult() {
        System.out.println("Grade: " + grade + " | GPA: " + gpa);
    }
}


// ============================================================
//  SECTION 3 — FINANCE CLASSES
// ============================================================

// Fee tracks payments and remaining balance
class Fee {
    private double totalFee;
    private double paidAmount;

    public Fee(double totalFee)    { this.totalFee = totalFee; }
    public void pay(double amount) { paidAmount += amount; }
    public double getRemaining()   { return totalFee - paidAmount; }
    public double getPaid()        { return paidAmount; }
    public double getTotal()       { return totalFee; }

    public void showStatus() {
        System.out.println("Remaining Fee: " + getRemaining());
    }
}

// Salary calculates net pay after tax
class Salary {
    private double baseSalary;
    private double tax;

    public Salary(double baseSalary, double tax) {
        this.baseSalary = baseSalary;
        this.tax        = tax;
    }

    public double calculateSalary() { return baseSalary - tax; }
}

// Scholarship gives a discount based on GPA
class Scholarship {
    private Student student;
    private double  discount;

    public Scholarship(Student s) { this.student = s; }

    public void apply() {
        discount = (student.getGpa() >= 3.5) ? 20 : 0;
    }

    public double getDiscount() { return discount; }

    public void showScholarship() {
        System.out.println("Scholarship: " + discount + "%");
    }
}


// ============================================================
//  SECTION 4 — RESOURCE CLASS
// ============================================================

class Resource {
    private String name;
    private int    quantity;

    public Resource(String name, int quantity) {
        this.name     = name;
        this.quantity = quantity;
    }

    public String getName()     { return name; }
    public int    getQuantity() { return quantity; }

    public void display() {
        System.out.println("Resource: " + name + " | Quantity: " + quantity);
    }
}


// ============================================================
//  SECTION 5 — FILE HANDLER (Save & Load)
// ============================================================

class FileHandler {

    // ── Save students list to a .txt file ──────────────────────
    public static void saveStudents(List<Student> students, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Student s : students) {
            writer.write(s.toCsv());
            writer.newLine();
        }
        writer.close();
    }

    // ── Load students list from a .txt file ────────────────────
    public static List<Student> loadStudents(String filename) throws IOException {
        List<Student> list   = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                list.add(Student.fromCsv(line));
            }
        }
        reader.close();
        return list;
    }

    // ── Save courses list to a .txt file ───────────────────────
    public static void saveCourses(List<Course> courses, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Course c : courses) {
            writer.write(c.toCsv());
            writer.newLine();
        }
        writer.close();
    }

    // ── Load courses list from a .txt file ─────────────────────
    public static List<Course> loadCourses(String filename) throws IOException {
        List<Course>  list   = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                list.add(Course.fromCsv(line));
            }
        }
        reader.close();
        return list;
    }

    // ── Save teachers list to a .txt file ──────────────────────
    public static void saveTeachers(List<Teacher> teachers, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Teacher t : teachers) {
            writer.write(t.toCsv());
            writer.newLine();
        }
        writer.close();
    }

    // ── Load teachers list from a .txt file ────────────────────
    public static List<Teacher> loadTeachers(String filename) throws IOException {
        List<Teacher>  list  = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                list.add(Teacher.fromCsv(line));
            }
        }
        reader.close();
        return list;
    }
}


// ============================================================
//  SECTION 6 — MAIN GUI (Swing JFrame with Tabs)
// ============================================================

public class OOPProjectTest extends JFrame {

    // ── Application data lists ────────────────────────────────
    private List<Student>  students  = new ArrayList<>();
    private List<Teacher>  teachers  = new ArrayList<>();
    private List<Course>   courses   = new ArrayList<>();
    private List<Resource> resources = new ArrayList<>();

    // ── Table models (displayed in the tabs) ──────────────────
    private DefaultTableModel studentTableModel;
    private DefaultTableModel teacherTableModel;
    private DefaultTableModel courseTableModel;
    private DefaultTableModel resultTableModel;
    private DefaultTableModel resourceTableModel;

    // ── Constructor: build the window ─────────────────────────
    public OOPProjectTest() {
        setTitle("Smart University Management System");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabbed pane holds each module
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("🎓 Students",  buildStudentPanel());
        tabs.addTab("👨‍🏫 Teachers",  buildTeacherPanel());
        tabs.addTab("📚 Courses",   buildCoursePanel());
        tabs.addTab("📊 Results",   buildResultPanel());
        tabs.addTab("🖥 Resources", buildResourcePanel());

        add(tabs, BorderLayout.CENTER);
        add(buildStatusBar(), BorderLayout.SOUTH);

        setVisible(true);
    }


    // ============================================================
    //  TAB 1 — STUDENT PANEL
    // ============================================================
    private JPanel buildStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ── Input form ──────────────────────────────────────────
        JTextField tfName = new JTextField(10);
        JTextField tfAge  = new JTextField(4);
        JTextField tfCnic = new JTextField(8);
        JTextField tfID   = new JTextField(6);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Name:")); form.add(tfName);
        form.add(new JLabel("Age:"));  form.add(tfAge);
        form.add(new JLabel("CNIC:")); form.add(tfCnic);
        form.add(new JLabel("ID:"));   form.add(tfID);

        // ── Buttons ─────────────────────────────────────────────
        JButton btnAdd    = new JButton("Add");
        JButton btnRemove = new JButton("Remove");
        JButton btnSave   = new JButton("Save to File");
        JButton btnLoad   = new JButton("Load from File");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(btnAdd);
        buttons.add(btnRemove);
        buttons.add(btnSave);
        buttons.add(btnLoad);

        // ── Table ───────────────────────────────────────────────
        studentTableModel = new DefaultTableModel(
            new String[]{"Name", "Age", "CNIC", "Student ID", "GPA"}, 0
        );
        JTable table = new JTable(studentTableModel);
        JScrollPane scroll = new JScrollPane(table);

        // ── Top section holds form + buttons ────────────────────
        JPanel top = new JPanel(new BorderLayout());
        top.add(form,    BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        panel.add(top,    BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // ── Add student action ───────────────────────────────────
        btnAdd.addActionListener(e -> {
            try {
                String name = tfName.getText().trim();
                int    age  = Integer.parseInt(tfAge.getText().trim());
                String cnic = tfCnic.getText().trim();
                String id   = tfID.getText().trim();

                if (name.isEmpty() || id.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name and ID are required.");
                    return;
                }

                Student s = new Student(name, age, cnic, id);
                students.add(s);
                studentTableModel.addRow(new Object[]{name, age, cnic, id, 0.0});
                clearFields(tfName, tfAge, tfCnic, tfID);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.");
            }
        });

        // ── Remove selected student ─────────────────────────────
        btnRemove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                students.remove(row);
                studentTableModel.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to remove.");
            }
        });

        // ── Save students to file ───────────────────────────────
        btnSave.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("students.txt"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    FileHandler.saveStudents(students, fc.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Students saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
                }
            }
        });

        // ── Load students from file ─────────────────────────────
        btnLoad.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<Student> loaded = FileHandler.loadStudents(fc.getSelectedFile().getAbsolutePath());
                    students.clear();
                    studentTableModel.setRowCount(0);
                    for (Student s : loaded) {
                        students.add(s);
                        studentTableModel.addRow(new Object[]{
                            s.getName(), s.age, s.cnic, s.getStudentID(), s.getGpa()
                        });
                    }
                    JOptionPane.showMessageDialog(this, "Students loaded successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error loading: " + ex.getMessage());
                }
            }
        });

        return panel;
    }


    // ============================================================
    //  TAB 2 — TEACHER PANEL
    // ============================================================
    private JPanel buildTeacherPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField tfName  = new JTextField(10);
        JTextField tfAge   = new JTextField(4);
        JTextField tfCnic  = new JTextField(8);
        JTextField tfDesig = new JTextField(8);
        JTextField tfSubj  = new JTextField(8);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Name:"));        form.add(tfName);
        form.add(new JLabel("Age:"));         form.add(tfAge);
        form.add(new JLabel("CNIC:"));        form.add(tfCnic);
        form.add(new JLabel("Designation:")); form.add(tfDesig);
        form.add(new JLabel("Subject:"));     form.add(tfSubj);

        JButton btnAdd    = new JButton("Add");
        JButton btnRemove = new JButton("Remove");
        JButton btnSave   = new JButton("Save to File");
        JButton btnLoad   = new JButton("Load from File");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(btnAdd); buttons.add(btnRemove);
        buttons.add(btnSave); buttons.add(btnLoad);

        teacherTableModel = new DefaultTableModel(
            new String[]{"Name", "Age", "CNIC", "Designation", "Subject"}, 0
        );
        JTable     table  = new JTable(teacherTableModel);
        JScrollPane scroll = new JScrollPane(table);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        panel.add(top,    BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // ── Add teacher ─────────────────────────────────────────
        btnAdd.addActionListener(e -> {
            try {
                String name  = tfName.getText().trim();
                int    age   = Integer.parseInt(tfAge.getText().trim());
                String cnic  = tfCnic.getText().trim();
                String desig = tfDesig.getText().trim();
                String subj  = tfSubj.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Name is required.");
                    return;
                }

                Teacher t = new Teacher(name, age, cnic, desig, subj);
                teachers.add(t);
                teacherTableModel.addRow(new Object[]{name, age, cnic, desig, subj});
                clearFields(tfName, tfAge, tfCnic, tfDesig, tfSubj);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.");
            }
        });

        btnRemove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) { teachers.remove(row); teacherTableModel.removeRow(row); }
            else JOptionPane.showMessageDialog(this, "Select a row to remove.");
        });

        btnSave.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("teachers.txt"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    FileHandler.saveTeachers(teachers, fc.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Teachers saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
                }
            }
        });

        btnLoad.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<Teacher> loaded = FileHandler.loadTeachers(fc.getSelectedFile().getAbsolutePath());
                    teachers.clear();
                    teacherTableModel.setRowCount(0);
                    for (Teacher t : loaded) {
                        teachers.add(t);
                        teacherTableModel.addRow(new Object[]{
                            t.getName(), t.age, t.cnic, t.getDesignation(), t.getSubject()
                        });
                    }
                    JOptionPane.showMessageDialog(this, "Teachers loaded successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error loading: " + ex.getMessage());
                }
            }
        });

        return panel;
    }


    // ============================================================
    //  TAB 3 — COURSE PANEL
    // ============================================================
    private JPanel buildCoursePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField tfCode    = new JTextField(8);
        JTextField tfCredits = new JTextField(4);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Course Code:"));  form.add(tfCode);
        form.add(new JLabel("Credit Hours:")); form.add(tfCredits);

        JButton btnAdd    = new JButton("Add");
        JButton btnRemove = new JButton("Remove");
        JButton btnSave   = new JButton("Save to File");
        JButton btnLoad   = new JButton("Load from File");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(btnAdd); buttons.add(btnRemove);
        buttons.add(btnSave); buttons.add(btnLoad);

        courseTableModel = new DefaultTableModel(
            new String[]{"Course Code", "Credit Hours"}, 0
        );
        JTable      table  = new JTable(courseTableModel);
        JScrollPane scroll = new JScrollPane(table);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        panel.add(top,    BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // ── Add course ──────────────────────────────────────────
        btnAdd.addActionListener(e -> {
            try {
                String code    = tfCode.getText().trim();
                int    credits = Integer.parseInt(tfCredits.getText().trim());

                if (code.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Course code is required.");
                    return;
                }

                Course c = new Course(code, credits);
                courses.add(c);
                courseTableModel.addRow(new Object[]{code, credits});
                clearFields(tfCode, tfCredits);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Credit hours must be a number.");
            }
        });

        btnRemove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) { courses.remove(row); courseTableModel.removeRow(row); }
            else JOptionPane.showMessageDialog(this, "Select a row to remove.");
        });

        btnSave.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("courses.txt"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    FileHandler.saveCourses(courses, fc.getSelectedFile().getAbsolutePath());
                    JOptionPane.showMessageDialog(this, "Courses saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving: " + ex.getMessage());
                }
            }
        });

        btnLoad.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    List<Course> loaded = FileHandler.loadCourses(fc.getSelectedFile().getAbsolutePath());
                    courses.clear();
                    courseTableModel.setRowCount(0);
                    for (Course c : loaded) {
                        courses.add(c);
                        courseTableModel.addRow(new Object[]{c.getCourseCode(), c.getCreditHours()});
                    }
                    JOptionPane.showMessageDialog(this, "Courses loaded successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error loading: " + ex.getMessage());
                }
            }
        });

        return panel;
    }


    // ============================================================
    //  TAB 4 — RESULT PANEL (Enrollment + Grade Calculation)
    // ============================================================
    private JPanel buildResultPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dropdowns populated from Students and Courses lists
        JComboBox<String> cbStudent = new JComboBox<>();
        JComboBox<String> cbCourse  = new JComboBox<>();
        JTextField        tfMarks   = new JTextField(6);

        JButton btnRefresh = new JButton("Refresh Lists");
        JButton btnCalc    = new JButton("Calculate Result");

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Student:"));  form.add(cbStudent);
        form.add(new JLabel("Course:"));   form.add(cbCourse);
        form.add(new JLabel("Marks:"));    form.add(tfMarks);
        form.add(btnRefresh);
        form.add(btnCalc);

        resultTableModel = new DefaultTableModel(
            new String[]{"Student", "Course", "Marks", "Grade", "GPA", "Scholarship %"}, 0
        );
        JTable      table  = new JTable(resultTableModel);
        JScrollPane scroll = new JScrollPane(table);

        panel.add(form,   BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // ── Refresh dropdowns from current data ─────────────────
        btnRefresh.addActionListener(e -> {
            cbStudent.removeAllItems();
            cbCourse.removeAllItems();
            for (Student s : students) cbStudent.addItem(s.getName());
            for (Course  c : courses)  cbCourse.addItem(c.getCourseCode());
        });

        // ── Calculate result and update student GPA ─────────────
        btnCalc.addActionListener(e -> {
            int sIdx = cbStudent.getSelectedIndex();
            int cIdx = cbCourse.getSelectedIndex();

            if (sIdx < 0 || cIdx < 0) {
                JOptionPane.showMessageDialog(this, "Select a student and course. Use Refresh if lists are empty.");
                return;
            }

            try {
                double marks = Double.parseDouble(tfMarks.getText().trim());

                Student    s    = students.get(sIdx);
                Course     c    = courses.get(cIdx);
                Enrollment enrl = new Enrollment(s, c);
                Result     res  = new Result(enrl);

                res.calculateResult(marks);
                s.setGpa(res.getGpa());           // Update student GPA

                // Apply scholarship based on new GPA
                Scholarship sch = new Scholarship(s);
                sch.apply();

                // Update student table GPA column (column index 4)
                studentTableModel.setValueAt(res.getGpa(), sIdx, 4);

                resultTableModel.addRow(new Object[]{
                    s.getName(), c.getCourseCode(), marks,
                    res.getGrade(), res.getGpa(), sch.getDiscount() + "%"
                });

                tfMarks.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Marks must be a number (0–100).");
            }
        });

        return panel;
    }


    // ============================================================
    //  TAB 5 — RESOURCE PANEL
    // ============================================================
    private JPanel buildResourcePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField tfName = new JTextField(12);
        JTextField tfQty  = new JTextField(6);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.add(new JLabel("Resource Name:")); form.add(tfName);
        form.add(new JLabel("Quantity:"));       form.add(tfQty);

        JButton btnAdd    = new JButton("Add");
        JButton btnRemove = new JButton("Remove");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(btnAdd); buttons.add(btnRemove);

        resourceTableModel = new DefaultTableModel(
            new String[]{"Resource Name", "Quantity"}, 0
        );
        JTable      table  = new JTable(resourceTableModel);
        JScrollPane scroll = new JScrollPane(table);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form,    BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        panel.add(top,    BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // ── Add resource ────────────────────────────────────────
        btnAdd.addActionListener(e -> {
            try {
                String name = tfName.getText().trim();
                int    qty  = Integer.parseInt(tfQty.getText().trim());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Resource name is required.");
                    return;
                }

                Resource r = new Resource(name, qty);
                resources.add(r);
                resourceTableModel.addRow(new Object[]{name, qty});
                clearFields(tfName, tfQty);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number.");
            }
        });

        btnRemove.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) { resources.remove(row); resourceTableModel.removeRow(row); }
            else JOptionPane.showMessageDialog(this, "Select a row to remove.");
        });

        return panel;
    }


    // ============================================================
    //  SECTION 7 — HELPER METHODS
    // ============================================================

    // Clear multiple text fields at once
    private void clearFields(JTextField... fields) {
        for (JTextField f : fields) f.setText("");
    }

    // A simple status bar shown at the bottom of the window
    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bar.setBorder(BorderFactory.createEtchedBorder());
        bar.add(new JLabel("Smart University System  |  Use tabs to navigate  |  Save/Load buttons write to .txt files"));
        return bar;
    }


    // ============================================================
    //  SECTION 8 — ENTRY POINT
    // ============================================================
    public static void main(String[] args) {
        // Run on the Event Dispatch Thread (Swing thread safety)
        SwingUtilities.invokeLater(() -> new OOPProjectTest());
    }
}
