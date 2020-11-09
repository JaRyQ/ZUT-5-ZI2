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

public class Module  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    String name;

    public Module(String moduleName) {
        name = moduleName;
    }

    public Module() {

    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Teacher mentor;
    public Teacher getMentor() { return mentor; }
    public void setMentor(Teacher mentor) { this.mentor = mentor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module md = (Module) o;
        return id.equals(md.id) &&
                name.equals(md.name) &&
                Objects.equals(mentor, md.mentor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mentor);
    }
}
