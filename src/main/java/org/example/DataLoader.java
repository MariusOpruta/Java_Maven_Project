package org.example;


import org.example.Course;
import org.example.Student;
import org.example.DataLoaderUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {

    private Map<Course, List<Student>> dataMap;
    private List<Student> students;

    public DataLoader() {
        dataMap = new HashMap<>();
        students = new ArrayList<>();


     //   dataMap.get(Course).add(students);
    }

    public void loadData() throws IOException {
        loadCourses();
        loadStudents();
        mapStudentsToCourses();
    }

    private void loadCourses() throws IOException {
        List<String> courseInputData = DataLoaderUtils.readFile(DataLoaderUtils.COURSE_FILE_PATH);

        for (String courseInputDatum : courseInputData) {
            String[] data = courseInputDatum.split(",");
            dataMap.put(createCourse(data), new ArrayList<>());
            System.out.println(dataMap);
        }
    }

    private Course createCourse(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        double price = Double.parseDouble(data[2]);
        LocalDate datainceput = LocalDate.parse(data[3]);
        Course cursNou = new Course(id, name, price, datainceput);
        return cursNou;
    }

    private void loadStudents() throws IOException {
        List<String> studenti = DataLoaderUtils.readFile(DataLoaderUtils.STUDENT_FILE_PATH);
        System.out.println(studenti);

        for(String linie : studenti){
            String[] dateDespreStudent = linie.split(",") ;
            Student student = creareStudent(dateDespreStudent);
            students.add(student);
        }

    }
    private Student creareStudent(String[] dateDespreStudent){
        int id = Integer.parseInt(dateDespreStudent[0]);
        String nume = dateDespreStudent[1];
        double budget = Double.parseDouble(dateDespreStudent[2]);

        Student student = new Student(id, nume, budget);

        return student;



    }
    private void mapStudentsToCourses() {

    }

    public Map<Course, List<Student>> getDataMap() {
        return dataMap;
    }

}

