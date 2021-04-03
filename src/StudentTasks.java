import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentTasks {

    //Задание 1
    public Map<String, List<String>> getStudentsMapByGroup(){
        String studentsStr = " Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82";
        List<String> student = Arrays.asList(studentsStr.split(";"));
        Set<String> groups = student.stream().map(s -> s.split(" - ")[1]).collect(Collectors.toSet());

        Map<String, List<String>> hashStudents = new HashMap<>();

        for(String group: groups){
            List<String> students = student.stream()
                    .filter(s -> s.split(" - ")[1].equals(group)).map(s -> s.split(" - ")[0].substring(1))
                    .collect(Collectors.toList());
            hashStudents.put(group, students);
        }
        return hashStudents;
    }
    //Задание 2
    public Map<String, Map<String, Integer[]>> getAllStudentsRatingByGroup(Map<String, List<String>> studentsMap) {

        Map<String, Map<String, Integer[]>> studentsRating = new HashMap<>();

        for(String group: studentsMap.keySet()){
            Map<String, Integer[]> studentRating = new HashMap<>();
            for(String student: studentsMap.get(group)){
                studentRating.put(student, getRating());
            }
            studentsRating.put(group, studentRating);
        }
        return studentsRating;
    }
    //задание 3
    public Map<String, Map<String, Integer>> getAllStudentsRatingSum(Map<String, Map<String, Integer[]>> studentsRating){
        Map<String, Map<String, Integer>> studentsRatingSum = new HashMap<>();

        studentsRating.forEach((group, students) -> {
            Map<String, Integer> studentRatingSum = new HashMap<>();
            students.forEach((student, rating) -> {
                Integer sum = 0;
                for(Integer i: rating){
                    sum += i;
                }
                studentRatingSum.put(student, sum);
            });
            studentsRatingSum.put(group, studentRatingSum);
        });
        return studentsRatingSum;
    }
    //задание 4
    public Map<String, Double> getAllGroupRatingAverage(Map<String, Map<String, Integer>> studentsRating){
        Map<String, Double> groupRatingSum = new HashMap<>();

        studentsRating.forEach((group, students) -> {
            List<Double> studentRatingSum = new ArrayList<>();
            students.forEach((student, rating) -> {
                studentRatingSum.add(Double.valueOf(rating));
            });
            Double average = 0.0;
            for(Double i: studentRatingSum){
                average += i;
            }
            average /= studentRatingSum.size();
            groupRatingSum.put(group, average);
        });
        return groupRatingSum;
    }
    //задание 5
    public Map<String, List<String>> getAllStudentsPassRating(Map<String, Map<String, Integer>> studentsRating){
        Map<String, List<String>> groupPassingStudents = new HashMap<>();
        studentsRating.forEach((group, students) ->{
            List<String> passingStudents = new ArrayList<>();
            students.forEach((student, rating) -> {
                if(rating >= 60){
                    passingStudents.add(student);
                }
            });
            groupPassingStudents.put(group, passingStudents);
        });
        return groupPassingStudents;
    }


    //рандомный рейтинг студентов (6 значений от 5 до 14)
    private Integer[] getRating(){
        Integer[] rating = new Integer[6];
        for(int i = 0; i < rating.length; i++){
            rating[i] = (int)Math.ceil(Math.random()*(14.0 - 5) + 5);
        }
        return rating;
    }

    public static void main(String[] args) {
        StudentTasks tasks = new StudentTasks();

        Map<String, List<String>> studentsMapByGroup = tasks.getStudentsMapByGroup();

        Map<String, Map<String, Integer[]>> allStudentsRatingByGroup =
                tasks.getAllStudentsRatingByGroup(studentsMapByGroup);

        Map<String, Map<String, Integer>> allStudentsRatingSum =
                tasks.getAllStudentsRatingSum(allStudentsRatingByGroup);

        Map<String, Double> allGroupRatingAverage = tasks.getAllGroupRatingAverage(allStudentsRatingSum);

        Map<String, List<String>> allStudentsPassRating = tasks.getAllStudentsPassRating(allStudentsRatingSum);


        tasks.printStudentsMapByGroup(studentsMapByGroup);
        tasks.printAllStudentsRatingByGroup(allStudentsRatingByGroup);
        tasks.printAllStudentRating(allStudentsRatingSum);
        tasks.printAllGroupRatingAverage(allGroupRatingAverage);
        tasks.printPassingStudents(allStudentsPassRating);
    }



    public void printStudentsMapByGroup(Map<String, List<String>> studentsByGroup){
        System.out.println("Задание 1\n{");
        for(String group: studentsByGroup.keySet()){
            StringBuilder groupString = new StringBuilder(" " + group + "=[");
            for(String student: studentsByGroup.get(group)){
                groupString.append(student).append(", ");
            }

            System.out.println(groupString.substring(0, groupString.length()-2) + "]");
        }
        System.out.println("}");
    }

    public void printAllStudentsRatingByGroup(Map<String, Map<String, Integer[]>> studentsRating){

        System.out.println("Задание 2\n{");

        studentsRating.forEach((group, students) -> {
            StringBuilder groupString = new StringBuilder(group + "=[\n");
            students.forEach((student, rating) -> {
                groupString.append("    ").append(student).append(" = [ ");
                Arrays.stream(rating).forEach(integer -> groupString.append(integer).append(", "));
                groupString.delete(groupString.length()-2, groupString.length()).append("]\n");
            });
            groupString.append("]");
            System.out.println(groupString);
        });
        System.out.println("}");
    }

    public void printAllStudentRating(Map<String, Map<String, Integer>> studentsRating){

        System.out.println("Задание 3\n{");

        studentsRating.forEach((group, students) -> {
            StringBuilder groupString = new StringBuilder(group + "=[ ");
            students.forEach((student, rating) -> {
                groupString.append(student).append(": ");
                groupString.append(rating).append(", ");
            });
            groupString.delete(groupString.length()-2, groupString.length()).append(" ]");
            System.out.println(groupString);
        });
        System.out.println("}");
    }

    public void printAllGroupRatingAverage(Map<String, Double> ratingAverage){
        System.out.println("Задание 4");
        System.out.println(ratingAverage.toString());
    }

    public void printPassingStudents(Map<String, List<String>> passRating){
        System.out.println("Задание 5");
        System.out.println(passRating.toString());
    }
}