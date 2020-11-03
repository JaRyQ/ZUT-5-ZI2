
import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class App {

    private  static List<Student> CreateStudents(String [] names, String[] surnames){
        List<Student> students = new ArrayList<Student>();
        for(int i = 0; i < names.length; i++) {
            Student st = new Student();
            st.setFirstName(names[i]);
            st.setFamilyName(surnames[i]);
            students.add(st);

        }
        return students;
    }

   private static List<Teacher> CreateTeachers(String [] names, String [] surnames,String [] titles ){
       List<Teacher> teachers = new ArrayList<Teacher>();
       for(int i = 0; i < names.length; i++) {
           Teacher tr = new Teacher();
           tr.setFirstName(names[i]);
           tr.setFamilyName(surnames[i]);
           tr.setTitle(titles[i]);
           teachers.add(tr);

       }

       return teachers;
   }

      private static List<Classes> CreateClasses(String [] names, String[] levels, List<Student> students){
        List<Classes> classes = new ArrayList<Classes>();
          for(int i = 0; i < names.length; i++) {
              Classes cls = new Classes();
              cls.setLevel(levels[i]);
              cls.setName(names[i]);
              cls.setStudents(students.subList(i*5,(i+1) * 5 -1)); //przypisanie uczniów do klas
              classes.add(cls);
          }
          return classes;
       }

          private static List<Module> CreateModules(String [] names, List<Teacher> mentors) {
              List<Module> modules = new ArrayList<Module>();
              for(int i = 0; i < names.length; i++) {
                  Module md = new Module();
                  md.setName(names[i]);
                  int signed_mentor = 0 + (int) (new Random().nextFloat() * 3 );
                  md.setMentor(mentors.get(signed_mentor)); //przypisanie mentora do modułu
                  modules.add(md);
              }
              return modules;
          }

          private static void AssignModulesToClasses(List<Module> modules, List<Classes> classes){

            for(Classes cls : classes){
                List<Module>md = new ArrayList<Module>();
                for(int i=0 ; i<5;i++){
                    int signed_module = 0 + (int) (new Random().nextFloat() * 12 );
                    md.add(modules.get(signed_module));
                }
                cls.setModules(md);
            }
          }

          private static void AssignClassesToTeacher(List<Classes> classes, List<Teacher> teachers){
            int i =0;
            for(Teacher tr : teachers){
                tr.setCl(classes.get(i++));
            }
          }
    public static void main(String [] args) {

        //inicjalizaja entity managera
        System.out.println("LAB 5 ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("moja-baza");
        EntityManager em = emf.createEntityManager();

       em.getTransaction().begin();


        //tworzenie listy uczniów
        String names[] = new String[]{
                "Aaron", "Adam", "Caleb", "Dale", "Earl",
                "Fabian", "Gabriel", "Harold", "Elvis", "Karl",
                "Emma", "Ella", "Devin", "Avery", "Mathew"};
        String familynames[] = new String[]{
                "Nowak", "Kowalski", "Błaszczykowski", "Kowal", "Lewandowski",
                "Malarz", "Zabka", "Podgorzała", "Presley", "Kani",
                "Mrozonka", "Pilot", "Patton", "English", "Moodem"};
        List<Student> students= CreateStudents(names, familynames);

        //tworzenie listy nauczycieli
        String teacher_names[] = new String[]{"Jan", "Robert", "Anna"};
        String techer_surnames[] = new String[]{"Wielki", "Pochmurny", "Strzelka"};
        String titles[] = new String[]{"dr", "mgr", "prof."};
        List<Teacher> teachers = CreateTeachers(teacher_names,techer_surnames,titles);

        //tworzenie listy klas szkolnych
        String class_names [] = new String[]{"A","B", "C"};
        String class_levels[] = new String[]{"4", "6", "1"};
        List<Classes> classes = CreateClasses(class_names,class_levels, students);

        //tworzenie listy modułów
        String module_names[] = new String[]{"Matematyka", "Polski", "Angielski", "Rosyjski", "Informatyka", "WF", "Basen",
        "Historia", "Plastyka", "Biologia", "Chemia", "Fizyka", "WOS"};
        List<Module> modules = CreateModules(module_names, teachers);

        //przypisanie modułów do klas
        AssignModulesToClasses(modules,classes);

        //przypisanie klasy do nauczyciela
        AssignClassesToTeacher(classes, teachers);
        for(Classes cl :classes){
            System.out.println(cl.getName());
            cl.printModules();
        }
       em.getTransaction().commit();
/*
        //dla wybranej osoby ucznia wyświetlić jej przedmiot
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT Module FROM Classes cl WHERE Student IN ");

        List<Module> student_modules = (List<Module>) q.getResultList();
        for(Module md: student_modules){
            System.out.println(md.getId() + " "+ md.getName() +" "+ md.getMentor().getNickName());
        }
        em.getTransaction().commit();


      //dla wybranego nauczyciela wyświetlić wszystkich jego uczniów z klas, z którymi ma realizuje przedmioty.

        em.getTransaction().begin();
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.age < 18");
        List<Student> teacher_students = (List<Student>) q.getResultList();
        for(Student st: teacher_students){
            System.out.println(st.getId() + " "+ st.getNickName());
        }
        em.getTransaction().commit();*/



    }
}
