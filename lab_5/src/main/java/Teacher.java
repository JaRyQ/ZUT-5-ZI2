import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor

public class Teacher  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String title;
    private String firstName;
    private String familyName;

    public Teacher(String name, String surname, String title, List<Module> modules) {
        this.familyName = surname;
        this.firstName = name;
        this.title= title;
        this.modules= modules;
    }

    public Teacher() {

    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getFamilyName(){return familyName;}
    public void setFamilyName(String familyName){this.familyName = familyName;}

    public String getNickName(){
        return this.firstName.toLowerCase()+"."+this.familyName.toLowerCase();
    }
    public void setNickName(String nickName) {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }


    @OneToOne
    Classes cl;
    public Classes getCl(){return cl;}
    public  void setCl(Classes cl){this.cl = cl;}

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Module> modules;
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
    public List<Module> getModules() {
        return modules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id.equals(teacher.id) &&
                firstName.equals(teacher.firstName)&&
                familyName.equals(teacher.familyName)&&
                title.equals(teacher.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName,familyName,title);
    }
}
