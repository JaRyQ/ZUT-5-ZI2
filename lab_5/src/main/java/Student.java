
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor

public class Student  {

    public Student(String name,String surname
    ) {
        this.firstName= name;
        this.familyName = surname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String familyName;

    @ManyToOne
    private Classes classes;

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Classes getClasses() {
        return classes;
    }

    public Student() {

    }


    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getFamilyName(){return familyName;}
    public void setFamilyName(String familyName){this.familyName = familyName;}

    public String getNickName(){
        return this.firstName.toLowerCase().charAt(0)+"."+this.familyName.toLowerCase();
    }
    public void setNickName(String nickName) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) &&
                firstName.equals(student.firstName)&&
                familyName.equals(student.familyName) &&
                Objects.equals(classes, student.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName,familyName, classes);
    }


}
