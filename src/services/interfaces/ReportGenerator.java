package services.interfaces;

import entities.Pedagogue;
import entities.Person;
import entities.Student;
import entities.Teacher;

import java.util.List;

public interface ReportGenerator {
    public void reportManager(List<Student> students, List<Teacher> teachers, List<Pedagogue> pedagogues);
}
