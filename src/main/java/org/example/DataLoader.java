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

        System.out.println(dataMap);
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
    private void mapStudentsToCourses() throws IOException {
        List<String> listaMapping = DataLoaderUtils.readFile(DataLoaderUtils.MAPPING_FILE_PATH);
        for(String mapare: listaMapping){
            String[] iduri = mapare.split(",");
            int idStundent = Integer.parseInt(iduri[0]);
            int idCurs = Integer.parseInt(iduri[1]);

            Student studentGasit = null;
            Course cursGasit = null;
            for(Student student: students){
                if (student.getIdStudent()==idStundent){
                    studentGasit = student;
                    break;
                }
            }

            for (Course curs: dataMap.keySet()){
                if (curs.getIdCurs() ==idCurs){
                    cursGasit = curs;
                    break;
                }
            }

            if(studentGasit !=null && cursGasit !=null){
                double bugetStudent = studentGasit.getIdStudent();
                double pretCurs = cursGasit.getPretCurs();

                double bugetRamas = bugetStudent-pretCurs;

                try {
                    studentGasit.setBugetStudent(bugetRamas);
                    dataMap.get(cursGasit).add(studentGasit);
                }catch (BugetInvalidException e){
                    System.out.println("Studentul nu poate fi adaugat");


                }


            }
        }

    }

    public Map<Course, List<Student>> getDataMap() {
        return dataMap;
    }
    public static void afiseazaCursuri(Map<Course,List<Student>> dataMap){
        System.out.println(dataMap.keySet());
    }
}

