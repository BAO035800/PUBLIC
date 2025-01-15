package exercise.interfacee;

public class Student extends Person {
    private int mark;
    private String grade;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student [mark=" + mark + ", grade=" + grade + "]";
    }

    public Student(String id, String name, int age, int mark) {
        super(id, name, age);
        this.mark = mark;
    }

    public void setGrade(String grade) {
        if (mark >= 8) {
            grade = "A";
        } else if (mark >= 7) {
            grade = "B";
        } else if (mark >= 6) {
            grade = "C";
        } else if (mark >= 5) {
            grade = "D";
        } else if (mark < 5) {
            grade = "F";
        }
    }
}
