
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor

public class Classes  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    Long id;
    String name;
    String level;

    public Classes(String name, String level, Teacher mentor, List<Module> modules, List<Student> students) {
        this.name = name;
        this.level = level;
        this.setMentor(mentor);
        this.setModules(modules);
        this.setStudents(students);
    }

    public Classes() {

    }


    public Long getId(){return this.id;}
    public void setId(Long id){this.id = id;}

    public String getName() {return name;}
    public void setName(String name){this.name = name;}

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    @OneToOne(cascade = CascadeType.MERGE)
    private Teacher mentor;
    public Teacher getMentor() { return mentor; }
    public void setMentor(Teacher mentor) { this.mentor = mentor; }

    @OneToMany(cascade = CascadeType.ALL)
    List<Student> students;
    public List<Student> getStudents() {return students; }
    public void setStudents(List<Student> students){this.students = students;}


    @OneToMany(cascade = CascadeType.PERSIST)
    List<Module> modules;
    public List<Module> getModules() { return modules; }
    public void setModules(List<Module> modules) { this.modules = modules; }

    public void printModules(){
        for( Module md: modules){
            System.out.println( " "+ md.getName()+" "+md.getMentor().getNickName());
        }
    }

    public void printStudents(){
        for(Student st : students){
            System.out.println(" "+st.getNickName());
        }
    }

    @PrePersist
    private void prePersist() {
        if (students != null)
            this.students.forEach(student -> student.setClasses(this));

        if (mentor != null) {
            this.mentor.setCl(this);
        }
    }
}
