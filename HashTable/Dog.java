import java.util.Calendar;
import java.util.Date;

public class Dog {
    private String name;
    private int age;
    private Date birthday;
    public Dog(){
        name = "WangCai";
        age = 4;
        birthday = new Date(2020, Calendar.JUNE, 16);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age + birthday.hashCode();
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.hashCode();

    }
}
