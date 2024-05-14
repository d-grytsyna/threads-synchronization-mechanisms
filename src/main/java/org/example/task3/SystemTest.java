package org.example.task3;

import java.util.ArrayList;
import java.util.List;

public class SystemTest {
    public static void main(String[] args){
        int studentAmount = 20;
        int marksAmount = 400;

        // SET GROUPS
        List<String> groups = new ArrayList<>();
        groups.add("GROUP-1");
        groups.add("GROUP-2");
        groups.add("GROUP-3");

        // SET E-TABLES
        List<ETable> tables = new ArrayList<>();
        tables.add(new ETable(groups.get(0),studentAmount));
        tables.add(new ETable(groups.get(1),studentAmount));
        tables.add(new ETable(groups.get(2),studentAmount));


        // SET TEACHERS WITH MARKS
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(groups, studentAmount, marksAmount, " Lector ", tables));
        teachers.add(new Teacher(List.of(groups.get(0)), studentAmount, marksAmount, "Teacher-1 ", List.of(tables.get(0))));
        teachers.add(new Teacher(List.of(groups.get(1)), studentAmount, marksAmount, "Teacher-2 ", List.of(tables.get(1))));
        teachers.add(new Teacher(List.of(groups.get(2)), studentAmount, marksAmount, "Teacher-3 ", List.of(tables.get(2))));

        for(int i=0; i<teachers.size(); i++){
            teachers.get(i).printGroupMarks();
        }

        // SET MARKS TO STUDENTS
        Thread[] threads = new Thread[teachers.size()];
        for (int i = 0; i < teachers.size(); i++) {
            int startIndex = i;
            threads[i] = new Thread(() -> {
                teachers.get(startIndex).setStudentMarks();
            });
            threads[i].start();
        }
        for (int i = 0; i < teachers.size(); i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        // RESULT TABLES
        for(int i=0; i<tables.size(); i++){
            tables.get(i).printTable();
        }

        // CHECK IF ALL STUDENT HAVE 20 MARKS
        System.out.println("ALL STUDENTS GOT THEIR MARKS: " + studentsHaveAllMarks(tables, marksAmount));
    }

    private static boolean studentsHaveAllMarks(List<ETable> tables, int marksAmount){
        for(int i=0; i<tables.size(); i++){
            for(int j=0; j<tables.get(0).getStudents().size(); j++){
                System.out.println("MARKS SIZE " + tables.get(i).getStudents().get(j).getMarks().size());
                if(tables.get(i).getStudents().get(j).getMarks().size()!=marksAmount){
                    return false;
                }
            }
        }
        return true;
    }
}
