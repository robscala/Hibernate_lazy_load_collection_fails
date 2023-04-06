package org.hibernate.bugs;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JPAUnitTestCase {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );

        // Populate records in the database:
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Creating database records");
        ManufacturerCompany manufacturerCompany = new ManufacturerCompany();
        manufacturerCompany.setComputerSystem(new ManufacturerComputerSystem());
        Person person = new Person();
        person.setFirstName("Henry");
        manufacturerCompany.addPerson(person);
        entityManager.getTransaction().begin();
        entityManager.persist(manufacturerCompany);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    @Test
    public void refresh_test() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Finding manufacturer");
        ManufacturerCompany manufacturerCompany = entityManager.find(ManufacturerCompany.class, 1L);
        System.out.println("Refreshing manufacturer");
        entityManager.refresh(manufacturerCompany);
        System.out.println("Calling manufacturer.getPeople()");
        List<Person> people = manufacturerCompany.getPeople();
        System.out.println("Calling people.get(0)");
        Person person1 = people.get(0);     // <-- This is where it fails.
        System.out.println("Changing name");
        String newFirstName = "name1".equals(person1.getFirstName()) ? "name2" : "name1";
        entityManager.getTransaction().begin();
        person1.setFirstName(newFirstName);
        entityManager.getTransaction().commit();

        assertEquals("Get latest name", newFirstName, person1.getFirstName());

        entityManager.close();
    }
}