package org.example.task3;

import java.util.ArrayList;
import java.util.List;

public class ETable {

    private String groupName;
    private List<Student> students = new ArrayList<>();

    ETable(String groupName, int studentAmount){
        this.groupName = groupName;
        for(int i=0; i<studentAmount; i++){
            students.add(new Student());
        }
    }

    public String getGroupName(){
        return groupName;
    }
    public List<Student> getStudents(){
        return students;
    }
    public void printTable(){
        System.out.println("E-Table for the group " + groupName);
        for(int i=0; i<students.size(); i++){
            System.out.println("STUDENT " + i + " : " + students.get(i).getMarks().toString());
        }
    }


    
}
