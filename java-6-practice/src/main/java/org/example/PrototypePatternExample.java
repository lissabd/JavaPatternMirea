package org.example;

class Student implements Cloneable {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class PrototypePatternExample {
    public static void main(String[] args) {
        // Создаем оригинальный объект студента
        Student originalStudent = new Student("Alice", 20);

        try {
            // Клонируем оригинального студента
            Student clonedStudent = (Student) originalStudent.clone();

            // Изменяем данные клонированного студента
            clonedStudent.setName("Bob");
            clonedStudent.setAge(22);

            System.out.println("Original student: " + originalStudent);
            System.out.println("Cloned student:" + clonedStudent);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
