/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/25
 * \* Time: 15:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Student {
    private String sname;
    private int age;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String sname, int age) {
        this.sname = sname;
        this.age = age;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}