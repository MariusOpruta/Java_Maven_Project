package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        DataLoader dl = new DataLoader();
        DatSaver ds = new DatSaver();
        dl.loadData();
//        dl.getDataMap();
        Map<Course, List<Student>> date = dl.getDataMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Puteti introduce urmatoarele optiuni:\n" +
                "0 - Ies din program.\n" +
                "1 - Afiseaza cursuri\n" +
                "2 - Introduceti un curs nou\n" +
                "3 = Introduceti un student nou si inrolati-l la curs\n" +
                "4 - Cautati un student dupa nume (OPTIONAL)\n" +
                "5 = Afiseaza studentii si cursul la care participa. (OPTIONAL)\n");

        while (true){
            System.out.println("Va rugam introduceti o optiune");
            int optiune = Integer.parseInt(scanner.nextLine());
            switch (optiune) {
                case 0 -> System.exit(0);
                case 1 -> System.out.println("Afiseaza cursuri");
                case 2 -> {
                    System.out.println("Introduceti un curs nou");
                    System.out.println("Introduceti id-ul cursului");
                    int idCurs = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduceti numele cursului");
                    String numeCurs = scanner.nextLine();
                    System.out.println("Introduceti pretul cursului");
                    double pretCurs = Double.parseDouble(scanner.nextLine());
                    System.out.println("Introduceti data cursului");
                    String dataCursString = scanner.nextLine();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataCurs = LocalDate.parse(dataCursString,format);
                    Course cursNou = new Course(idCurs,numeCurs,pretCurs,dataCurs);
                    metode.adaugaCurs(cursNou,date);
                    metode.afiseazaCursuri(cursNou,date);
                    ds.saveData(date);
                } break;
                case 3 -> {
                    System.out.println("Introduceti un student nou si inrolati-l la curs");
                    //metode.afiseazaCursuri(cursNou,date);
                    int cursuriDisponibile = 0;
                    for(Map.Entry<Course,List<Student>> felie: date.entrySet()){
                        if (felie.getValue().size() <9){
                            System.out.println("Curs disponibil:"+felie.getKey());
                        }
                    }
                    if (cursuriDisponibile==0){
                        System.out.println("Ne pare rau nu sunt cursuri");
                        String numeCursCautat  = scanner.next();
                    }

                    System.out.println("Introduceti numele uui curs");
                    String numeCursCautat = scanner.nextLine();
                    Course cursgasit = null;
                    LocalDate acum = LocalDate.now();

                    for(Course curs: date.keySet()){
                        if (curs.getNameCurs().equals(numeCursCautat) && acum.isBefore(curs.getDataInceput())){
                            System.out.println("am gasit cursul");
                            cursgasit = curs;
                            break;
                        }
                    }
                    if (cursgasit==null){
                        System.out.println("Acest curs nu exista");
                        break;
                    }

                    System.out.println("Introdu Id student");
                    int idStudent = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introdu bugetul stuentului");
                    String numeStudent = scanner.nextLine();
                    System.out.println("Introdu bugetul");
                    double bugetStudent = Double.parseDouble(scanner.nextLine());
                    Student student = new Student(idStudent,numeStudent,bugetStudent);
                    System.out.println("Ati introdus studentul:"+student);
                    try {
                        student.setBugetStudent(bugetStudent-cursgasit.getPretCurs());
                        date.get(cursgasit).add(student);
                    }catch (BugetInvalidException e){
                        System.out.println("Budgetul este invalid");
                    }

                } break;

                case 4 -> {
                        System.out.println("Cautati un student dupa nume (OPTIONAL)");
                        String numeStudent = scanner.nextLine();
                        for (List<Student> lista: date.values()){
                            for (Student studentDinLista:lista){
                                if (studentDinLista.getNumeStudent().contains(numeStudent)){
                                    System.out.println("Am gasit studentul"+studentDinLista);
                                }
                            }
                        }

                    }

                case 5 -> { System.out.println("Afiseaza studentii si cursul la care participa.");
                    for (Course curs: date.keySet()){
                        System.out.println("La cursul "+ curs.getNameCurs()+ "participa studentii ");
                        for (Student studentDeLaCurs: date.get(curs)){
                            System.out.println(studentDeLaCurs.getNumeStudent());
                        }
                        System.out.println(("\n"));
                    }
                }
            }

        }

    }
}