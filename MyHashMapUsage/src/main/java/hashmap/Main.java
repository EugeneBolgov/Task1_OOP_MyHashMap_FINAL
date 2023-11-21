package hashmap;/* учет студентов и их оценок и предметов. Можно использовать MyHashMap для хранения информации
о студентах и их оценках. А также MyHashMultiMap для хранения информации о предметах.
Ключами будут имена студентов, а значениями - списки оценок и списки предметов*/


import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        MyHashMap<String, List<Integer>> studentGrades = new MyHashMap<>();
        MyHashMultiMap<String, String> studentSubjects = new MyHashMultiMap<>();

        initializeStudentData(studentGrades, studentSubjects);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addStudentData(scanner, studentGrades, studentSubjects);
                    break;
                case "2":
                    searchStudentData(scanner, studentGrades, studentSubjects);
                    break;
                case "3":
                    System.out.println("Завершение программы.");
                    return;
                default:
                    System.out.println("Недопустимый выбор. Пожалуйста, выберите 1, 2 или 3.");
            }
        }

    }

    private static void initializeStudentData(MyHashMap<String, List<Integer>> studentGrades, MyHashMultiMap<String, String> studentSubjects) {
        studentGrades.put("stud1", List.of(90, 85, 88));
        studentGrades.put("stud2", List.of(78, 92, 87));
        studentGrades.put("stud3", List.of(95, 91, 89));
        studentGrades.put("stud4", List.of(88, 95, 78));

        studentSubjects.put("stud1", "Math");
        studentSubjects.put("stud1", "History");
        studentSubjects.put("stud2", "Physics");
        studentSubjects.put("stud2", "English");
        studentSubjects.put("stud2", "Chemistry");
        studentSubjects.put("stud3", "Biology");
        studentSubjects.put("stud4", "Math");
        studentSubjects.put("stud4", "French");
    }

    private static void printMenu() {
        System.out.println("Опции:");
        System.out.println("1. Добавить новую пару ключ-значение");
        System.out.println("2. Получить данные по ключу");
        System.out.println("3. Завершить программу");
        System.out.print("Введите ваш выбор: ");
    }

    private static void addStudentData(Scanner scanner, MyHashMap<String, List<Integer>> studentGrades, MyHashMultiMap<String, String> studentSubjects) {
        System.out.print("Введите имя студента: ");
        String studentName = scanner.nextLine();

        System.out.print("Введите оценки, разделенные запятыми: ");
        String gradesInput = scanner.nextLine();
        List<Integer> newGrades = parseGradeInput(gradesInput);

        System.out.print("Введите предметы, разделенные запятыми: ");
        String subjectsInput = scanner.nextLine();
        List<String> newSubjects = parseSubjectInput(subjectsInput);

        studentGrades.put(studentName, newGrades);
        for (String subject : newSubjects) {
            studentSubjects.put(studentName, subject);
        }

        System.out.println("Данные для студента " + studentName + " добавлены.");
    }

    private static List<Integer> parseGradeInput(String input) {
        return List.of(input.split(",")).stream()
                .map(Integer::parseInt)
                .toList();
    }

    private static List<String> parseSubjectInput(String input) {
        return List.of(input.split(","));
    }

    private static void searchStudentData(Scanner scanner, MyHashMap<String, List<Integer>> studentGrades, MyHashMultiMap<String, String> studentSubjects) {
        System.out.print("Введите имя студента: ");
        String studentName = scanner.nextLine();

        List<Integer> grades = studentGrades.get(studentName);
        if (grades != null) {
            System.out.println("Оценки для " + studentName + ": " + grades);
        } else {
            System.out.println("Студент " + studentName + " не найден.");
        }

        List<String> subjects = studentSubjects.get(studentName);
        if (subjects != null) {
            System.out.println("Предметы для " + studentName + ": " + subjects);
        } else {
            System.out.println("Предметы для " + studentName + " не найдены.");
        }
    }
}
