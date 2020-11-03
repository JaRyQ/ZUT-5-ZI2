
import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;

import java.util.*;


public class App {


    public static void main(String [] args) {

        //initialize entity manager
        System.out.println("LAB 5 ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("moja-baza");
        EntityManager em = emf.createEntityManager();

        //creating grouped students
        Student [] firstClassStudents = new Student[]{
                new Student("Aaron","Nowak"),
                new Student("Adam","Kowalski"),
                new Student("Caleb","Blaszczykowski"),
                new Student("Dale","Lewandowski"),
                new Student("Earl","Nowak"),
        };

        Student [] secondClassStudents = new Student[]{
                new Student("Fabian","Malarz"),
                new Student("Gabriel","Zabka"),
                new Student("Harold","Podgorzala"),
                new Student("Elvis","Presley"),
                new Student("Karl","Kani"),
        };
        Student [] thirdClassStudents = new Student[]{
                new Student("Emma","Mrozonka"),
                new Student("Ella","Pilot"),
                new Student("Devin","Patton"),
                new Student("Avery","English"),
                new Student("Mathew","Moodem"),
        };
        //opening transaction
        em.getTransaction().begin();

        //creating school modules -> class mentors -> classes
        //fist group
        Module math = new Module("Math");
        Module polish =  new Module("Polish");
        Module english =  new Module("English");
        Module history =  new Module("History");

        Teacher firstTeacher = new Teacher("Rob", "Pochmurny","mgr",
                List.of(math,polish,english, history));
        math.setMentor(firstTeacher);
        polish.setMentor(firstTeacher);
        english.setMentor(firstTeacher);
        history.setMentor(firstTeacher);

        em.persist(firstTeacher);
        em.flush();

        Classes firstClass = new Classes("A", "1", firstTeacher, List.of(math,polish,english, history),
                Arrays.asList(firstClassStudents));
        em.persist(firstClass);
        em.flush();
//------------------------------------------------------------------------------------
        //second group
        Module art =  new Module("Art");
        Module biology =  new Module("Biology");
        Module chemistry =  new Module("Chemistry");
        Module physics =  new Module("Physics");

        Teacher secondTeacher = new Teacher("Jan", "Wielki","dr",
                List.of(art,biology,chemistry, physics));
        art.setMentor(secondTeacher);
        biology.setMentor(secondTeacher);
        chemistry.setMentor(secondTeacher);
        physics.setMentor(secondTeacher);

        em.persist(secondTeacher);
        em.flush();

        Classes secondClass = new Classes("B", "4", secondTeacher,
                List.of(art,biology,chemistry, physics),
                Arrays.asList(secondClassStudents));
        em.persist(secondClass);
        em.flush();
//--------------------------------------------------------------------------------------------------------------
        //third group
        Module pe =  new Module("PE");
        Module it =  new Module("IT");
        Module geo =  new Module("Geography");
        Module economy =  new Module("Economy");

        Teacher thirdTeacher = new Teacher("Anna", "Strzelka","prof.",
                List.of(pe,it,geo, economy));
        pe.setMentor(thirdTeacher);
        it.setMentor(thirdTeacher);
        geo.setMentor(thirdTeacher);
        economy.setMentor(thirdTeacher);

        em.persist(thirdTeacher);
        em.flush();

        Classes thirdClass = new Classes("C", "6", thirdTeacher,
                List.of(pe,it,geo, economy),
                Arrays.asList(thirdClassStudents));
        em.persist(thirdClass);
        em.flush();

        em.getTransaction().commit();



        //dla wybranej (poprzez id )osoby ucznia wyświetlić jej przedmiot
        Query q = em.createQuery("SELECT student FROM Student student where student.id = 5");
        Student student = (Student) q.getSingleResult();
        System.out.println("\n\nResulted student =>"+student.getNickName()+' '+student.getClasses().getName()+ student.getClasses().getLevel());
       for(Module md : student.getClasses().getModules()){
           System.out.println(md.getId()+" "+md.getName()+" "+md.getMentor().getNickName());
       }




      //dla wybranego nauczyciela wyświetlić wszystkich jego uczniów z klas, z którymi ma realizuje przedmioty.

        System.out.println("\n\nstudents of teacher with id equal to 3");
        Query q2 = em.createQuery("SELECT  student FROM Student student " +
                "inner join student.classes classes where classes.mentor.id = 3");
       List<Student> teacher_students = (List<Student>) q2.getResultList();
        for(Student st: teacher_students){
            System.out.println(st.getId() + " "+ st.getNickName());
        }




    }
}
