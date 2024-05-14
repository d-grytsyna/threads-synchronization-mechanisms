package org.example.task3;

import java.util.ArrayList;

public class Student {
    private ArrayList<Integer> marks = new ArrayList<>();


    public synchronized void addMark(Integer mark){
        marks.add(mark);
    }

    public ArrayList<Integer> getMarks(){
        return marks;
    }


}
