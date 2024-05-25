package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            Teacher teacherObj=null;
            for(String t:teacherMap.keySet()){
                if(t.equals(teacher)){
                    teacherObj=teacherMap.get(t);
                    break;
                }
            }
            if(teacherObj!=null){
                teacherObj.setNumberOfStudents(1+teacherObj.getNumberOfStudents());
            }
            teacherStudentMapping.get(teacher).add(student);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        Student stud=null;
        for(String s:studentMap.keySet()){
            if(s.equals(student)){
                stud=studentMap.get(s);
                break;
            }
        }
        return stud;
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        Teacher techr=null;
        for(String t:teacherMap.keySet()){
            if(t.equals(teacher)){
                techr=teacherMap.get(t);
                break;
            }
        }
        return techr;
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        List<String> students=new ArrayList<>();
        for(String t:teacherStudentMapping.keySet()){
            if(t.equals(teacher)){
                return teacherStudentMapping.get(t);
            }
        }
        return students;
    }

    public List<String> findAllStudents(){
        // your code goes here
        List<String> studs=new ArrayList<>();
        for(String s:studentMap.keySet()){
            studs.add(s);
        }
        return studs;
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        for(String t:teacherMap.keySet()){
            if(t.equals(teacher)){
                teacherMap.remove(t);
            }
        }
    }

    public void deleteAllTeachers(){
        // your code goes here
        for(String t:teacherMap.keySet()){
            teacherMap.remove(t);
        }
    }
}