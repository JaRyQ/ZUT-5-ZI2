
import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;


public class App {
    //Run | Debug
    public static void main(String [] args) {

        //inicjalizaja entity managera
        System.out.println("LAB 4 ");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("moja-baza");
        EntityManager em = emf.createEntityManager();

        //5
        em.getTransaction().begin();
        Person p = new Person();
        p.setFirstName("Jhon");
        p.setFamilyName("Snow");
        p.setAge(16);


        em.persist(p);
        em.getTransaction().commit();



        //tworzenie listy dodatkowych użytkowników

        String names[] = new String[]{
                "Aaron", "Adam", "Caleb", "Dale", "Earl",
                "Fabian", "Gabriel", "Harold", "Elvis", "Karl"};
        String familynames[] = new String[]{
                "Nowak", "Kowalski", "Błaszczykowski", "Kowal", "Lewandowski",
                "Malarz", "Zabka", "Podgorzała", "Presley", "Kani"};
        Person[] ppl = new Person[10];
        for (int i = 0; i < names.length; i++) {
            int generatedInteger = 14 + (int) (new Random().nextFloat() * (25 - 14));
            em.getTransaction().begin();
            ppl[i] = new Person();
            ppl[i].setFirstName(names[i]);
            ppl[i].setFamilyName(familynames[i]);
            ppl[i].setAge(generatedInteger);
            em.persist(ppl[i]);
            em.getTransaction().commit();

        }


        //6.
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.age < 18");
        List<Person> too_young = (List<Person>) q.getResultList();
        too_young.forEach(el -> el.setAge(18));
        em.getTransaction().commit();



        //7.

        em.getTransaction().begin();
        Query q1 = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> people1 = q1.getResultList();
        System.out.println("List of people :");
        for(Person prsn : people1) {

            System.out.println(prsn.getId() +". "+ prsn.getFirstName() + " " + prsn.getFamilyName()+ " " + prsn.getAge());
        }
        em.getTransaction().commit();

        //8.
        System.out.println("\nList of people starting with A...:");
        Query q3 = em.createQuery("SELECT p FROM Person p WHERE LOWER(p.firstName) LIKE 'a%'" );
        List<Person> people = q3.getResultList();
        for(Person pr : people){
            System.out.println("    "+pr.getFirstName() + " "+ pr.getFamilyName() + " "+ pr.getAge());
        }




    }
}
