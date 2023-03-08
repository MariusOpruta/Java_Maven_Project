package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class Course {
    private int idCurs;
    private String nameCurs;
    private double pretCurs;

    private LocalDate dataInceput;


    public Course(int idCurs, String nameCurs, double pretCurs,LocalDate dataInceput) {
        this.idCurs = idCurs;
        this.nameCurs = nameCurs;
        this.pretCurs = pretCurs;
        this.dataInceput = dataInceput;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public String getNameCurs() {
        return nameCurs;
    }

    public void setNameCurs(String nameCurs) {
        this.nameCurs = nameCurs;
    }

    public double getPretCurs() {
        return pretCurs;
    }

    public void setPretCurs(double pretCurs) {
        this.pretCurs = pretCurs;
    }

    @Override
    public String toString() {
        return idCurs +","+ nameCurs +","+ pretCurs +","+ dataInceput;
//                "Course{" +
//                "idCurs=" + idCurs +
//                ", nameCurs='" + nameCurs + '\'' +
//                ", pretCurs=" + pretCurs +
//                ", dataInceput=" + dataInceput +
//                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return idCurs == course.idCurs && Double.compare(course.pretCurs, pretCurs) == 0 && nameCurs.equals(course.nameCurs) && dataInceput.equals(course.dataInceput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurs, nameCurs, pretCurs, dataInceput);
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDate dataInceput) {
        this.dataInceput = dataInceput;
    }



//    public String getName() {
//        return nameCurs;
//    }
//
//    public double getPrice() {
//        return pretCurs;
//    }
}
