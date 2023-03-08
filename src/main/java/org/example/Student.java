package org.example;

public class Student {
    private int idStudent;
    private String numeStudent;
    private double bugetStudent;

    public Student(int idStudent, String numeStudent, double bugetStudent) {
        this.idStudent = idStudent;
        this.numeStudent=numeStudent;
        this.bugetStudent=bugetStudent;

    }
    public void setBugetStudent(double bugetStudent) throws BugetInvalidException {
        if (bugetStudent <0){
            throw new BugetInvalidException("Studentul nu are suficienti bani");
        }
        this.bugetStudent= bugetStudent;
    }

    @Override
    public String toString() {
        return idStudent +","+ numeStudent +","+ bugetStudent;

//                "Student{" +
//                "idStudent=" + idStudent +
//                ", numeStudent='" + numeStudent + '\'' +
//                ", bugetStudent=" + bugetStudent +
//                '}';
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public double getBugetStudent() {
        return bugetStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
}
