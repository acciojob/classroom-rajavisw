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
        if(student!=null && student.getName()!=null){
            studentMap.put(student.getName(),student);
        }

    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        if(teacher!=null && teacher.getName()!=null){
            teacher.setNumberOfStudents(0);
            teacherMap.put(teacher.getName(), teacher);
        }
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            Teacher teacherObj=teacherMap.get(teacher);
            teacherObj.setNumberOfStudents(1+teacherObj.getNumberOfStudents());
            List<String> studentsList=teacherStudentMapping.get(teacher);
            if(studentsList==null){
                studentsList=new ArrayList<>();
            }
            studentsList.add(student);
            teacherStudentMapping.put(teacher,studentsList);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        Student stud=null;
        if(student!=null){
            stud=studentMap.get(student);
        }
        return stud;
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        Teacher techr=null;
        if(teacher!=null){
            techr=teacherMap.get(teacher);
        }
        return techr;
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        if(teacher!=null){
            return teacherStudentMapping.get(teacher);
        }
        return null;
    }

    public List<String> findAllStudents(){
        // your code goes here
        List<String> studs=new ArrayList<>(studentMap.keySet());
        return studs;
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        if(teacher!=null){
            teacherMap.remove(teacher);
            List<String> stundetsList=teacherStudentMapping.get(teacher);
            if(stundetsList!=null){
                for(String student:stundetsList){
                    studentMap.remove(student);
                }
            }
            teacherStudentMapping.remove(teacher);
        }
    }

    public void deleteAllTeachers(){
        // your code goes here
        for(String teacher:teacherMap.keySet()){
            List<String> stundetsList=teacherStudentMapping.get(teacher);
            if(stundetsList!=null){
                for(String student:stundetsList){
                    studentMap.remove(student);
                }
            }
            teacherStudentMapping.remove(teacher);
        }
        teacherMap.clear();
    }
}