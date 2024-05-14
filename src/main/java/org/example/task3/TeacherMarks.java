package org.example.task3;

import java.util.*;

public class TeacherMarks {
    private String groupName;

    private HashMap<Integer, List<Integer>> studentMarks = new HashMap<>();

    public TeacherMarks(String groupName, int studentAmount, int studentMarksAmount){
        this.groupName = groupName;
        for(int i=0; i<studentAmount; i++){
            List<Integer> marks = new ArrayList<>();
            for(int j=0; j<studentMarksAmount/2; j++){
                Random random = new Random();
                int randomNumber = random.nextInt(41) + 60;
                marks.add(randomNumber);
            }
            studentMarks.put(i, marks);
        }
    }

    public void printTeacherMarks(){
        System.out.println("TEACHER MARKS FOR GROUP " + groupName);
        for(int i=0; i< studentMarks.size(); i++){
            System.out.println("STUDENT " + i + " : " + studentMarks.get(i).toString());
        }
        System.out.println();
    }

    public Boolean allMarksSet(){
        return studentMarks.isEmpty();
    }
    public void removeMark(Integer studentId){
        studentMarks.get(studentId).remove(0);
        if(studentMarks.get(studentId).isEmpty()) studentMarks.remove(studentId);
    }


    public String getGroupName(){
        return groupName;
    }
    public List<Integer> studentIndexes() {
        Set<Integer> keySet = studentMarks.keySet();
        List<Integer> keyList = new ArrayList<>(keySet);
        return keyList;
    }
    public Integer getStudentMark(Integer studentId){
        return  studentMarks.get(studentId).get(0);
    }
}
