
/**
 * \* Created with IntelliJ IDEA.
 * \* User: WJ
 * \* Date: 2019/3/25
 * \* Time: 15:01
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class OneToManyDemo {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("张老师",18,"女");
        Student s1 = new Student("小李",10);
        Student s2 = new Student("小王",12);
        Student s3 = new Student("小赵",11);
        teacher.getStudents().add(s1);
        teacher.getStudents().add(s2);
        teacher.getStudents().add(s3);
        s1.setTeacher(teacher);
        s2.setTeacher(teacher);
        s3.setTeacher(teacher);
        print(teacher);

    }
    private static void print(Teacher teacher){
        System.out.println(teacher.getName());
        System.out.println(teacher.getSex());
        System.out.println(teacher.getAge());
        for (Student s:teacher.getStudents()
             ) {
            System.out.println(s.getClass());

        }
    }
}