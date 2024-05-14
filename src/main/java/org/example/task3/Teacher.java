package org.example.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Teacher {

    private String name;
    private List<String> groups = new ArrayList<>();
    private List<TeacherMarks> teacherMarks = new ArrayList<>();

    private List<ETable> tables = new ArrayList<>();
    public Teacher (List<String> groups, int studentAmount, int studentMarksAmount, String name, List<ETable> tables){
        this.name = name;
        this.tables = tables;
        this.groups = groups;
        for(int i=0; i<groups.size(); i++){
            teacherMarks.add(new TeacherMarks(groups.get(i), studentAmount, studentMarksAmount));
        }
    }

    public void printGroupMarks(){
        System.out.println("MARKS FROM TEACHER " + name);
        for(int i=0; i<groups.size(); i++){
            teacherMarks.get(i).printTeacherMarks();
        }
    }

    public void setStudentMarks(){
        while (teacherMarks.size()>0){
            Random random = new Random();
            int randomGroup = random.nextInt(teacherMarks.size());
            TeacherMarks randomMarks = teacherMarks.get(randomGroup);
            List<Integer> availableStudents = randomMarks.studentIndexes();
            int randomStudent = availableStudents.get(random.nextInt(availableStudents.size()));
            String groupName = randomMarks.getGroupName();
            ETable randomTable = new ETable("", 0);
            for(int i=0; i<tables.size(); i++){
               if(tables.get(i).getGroupName().equals(groupName)){
                   randomTable = tables.get(i);
               }
            }
            randomTable.getStudents().get(randomStudent).addMark(randomMarks.getStudentMark(randomStudent));
            randomMarks.removeMark(randomStudent);
            if(randomMarks.allMarksSet()){
                teacherMarks.remove(randomGroup);
            }
        }
    }




}
