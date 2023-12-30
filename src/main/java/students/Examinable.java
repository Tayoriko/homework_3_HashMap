package students;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Examinable {
    //добавить сдачу студента (в зачет идет только последняя сдача, хранить все сдачи студента по одному и тому же предмету не нужно)
    void addScore(Score score);

    //получить сдачу студента по имени, фамилии и предмету
    Score getScore(Student name, ListSubject subject);

    //вывод средней оценки по предмету всех студентов, кто сдавал более одного раза
    double getAverageScoreForSubject(ListSubject subject);

    //вывод списка студентов, попавших на пересдачу
    Set<String> multipleSubmissionsStudentNames();

    //вывод последних пяти студентов, сдавших на отлично
    Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject(int amount);

    //вывод всех сданных хотя бы одним студентом предметов
    Collection<String> getScoreSubjects();

    //вывод всех сданных предметов выбранным студентом
    Collection<Student> getScoreStudents(ListSubject subject);

    //вывод всех попыток сдать предмет всеми студентами
    Map<String, Score> getAllScores();

    //вывод предметов, сданных в конкретный день, и участвовавших в этом студентов
    Collection<Student> getStudentsScoreByDate(ScoreDate date);

}