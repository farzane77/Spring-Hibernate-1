package org.j2os.repository;

import org.hibernate.Session;
import org.hibernate.jdbc.AbstractWork;
import org.j2os.common.JPA;
import org.j2os.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class PersonDA {
    public void howToGetJDBCConnectionInJpa()
    {
        EntityManager entityManager = JPA.getEntityManager();//JPA
        Session session= entityManager.unwrap(Session.class);//Hibernate
        session.doWork(new AbstractWork() {
            @Override
            public void execute(Connection connection) throws SQLException {
                connection.prepareCall("blah blah blah");//JDBC
            }
        });

        entityManager.close();
    }
    public static void findAll5() {
        EntityManager entityManager = JPA.getEntityManager();
        Query query = entityManager.createQuery("select new org.j2os.entity.Person (o.name,o.family) from person o");
        List<Person> personList = query.getResultList();
        entityManager.close();

        for (Person person : personList) {
            System.out.println(person.getName());
        }
    }

    public static void findAll4() {
        EntityManager entityManager = JPA.getEntityManager();
        Query query = entityManager.createQuery("select o.name as z from person o", Tuple.class);


        List<Tuple> tuples = query.getResultList();
        entityManager.close();

        for (Tuple tuple : tuples) {
            System.out.println(tuple.get("z"));
        }
    }

    public static void findAll3() {
        EntityManager entityManager = JPA.getEntityManager();
        Query query = entityManager.createQuery("select o from person o where o.name=:n").setParameter("n", "Reza");

        List<Person> personList = query.getResultList();
        entityManager.close();

        for (Person person : personList) {
            System.out.println(person.getId());
            System.out.println(person.getName());
            System.out.println(person.getFamily());
        }
    }

    public static void findAll2() { // ravesh khoob
        EntityManager entityManager = JPA.getEntityManager();
        Query query = entityManager.createQuery("select o from person o where o.name=:n").setParameter("n", "Reza");

        List<Person> personList = query.getResultList();
        entityManager.close();

        for (Person person : personList) {
            System.out.println(person.getId());
            System.out.println(person.getName());
            System.out.println(person.getFamily());
        }
    }

    public static void findAll() {
        EntityManager entityManager = JPA.getEntityManager();
        Query query = entityManager.createNativeQuery("select * from person where name = :n or family=:f", Person.class);
        query.setParameter("n", "Reza");
        query.setParameter("f", "Taherzadeh");
        List<Person> personList = query.getResultList();
        entityManager.close();

        for (Person person : personList) {
            System.out.println(person.getId());
            System.out.println(person.getName());
            System.out.println(person.getFamily());
        }
    }

    public static void remove() {

        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Person person = new Person();
        person.setId(1L);
        person.setName("Maryam1");
        person.setFamily("Taherzadeh");
        person.setRecordVersion(2l);
        entityManager.remove(entityManager.merge(person));
        entityTransaction.commit();
        entityManager.close();
    }

    public static void update3() { //khatarnak find nakardim effect data base ra nemibinim
        long start = System.nanoTime();
        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Person person = new Person();
        person.setId(1L);
        person.setName("Maryam1");
        person.setFamily("Taherzadeh");
        person.setRecordVersion(2l);
        entityManager.merge(person); // bayad khatar ra az beyn bbrim
        entityTransaction.commit();
        entityManager.close();
        long end = System.nanoTime();
        System.out.println(end - start);
    }

    public static void update2() {
        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Person person = entityManager.find(Person.class, 1L);
        entityManager.detach(person);
        //person = entityManager.find(Person.class,person.getId());
        person.setName("Daryosh1");
        //entityManager.persist(person);
        entityTransaction.commit();
        entityManager.close();
    }

    public static void update1() {
        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Person person = entityManager.find(Person.class, 1L);
        person.setName("Ahmad");
        entityManager.persist(person);
        entityTransaction.commit();
        entityManager.close();
    }

    public static void findOne() {
        EntityManager entityManager = JPA.getEntityManager();
        Person person1 = entityManager.find(Person.class, 1L);
        Person person2 = entityManager.find(Person.class, 1L);
        entityManager.close();
        System.out.println(person1.getId());
        System.out.println(person1.getName());
        System.out.println(person1.getFamily());
        System.out.println(person2.getId());
        System.out.println(person2.getName());
        System.out.println(person2.getFamily());
    }

    public static void save() {
        EntityManager entityManager = JPA.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
      /*  Person person = new Person().setFamily("Taherzadeh").setName("Reza");

        entityManager.persist(person);*/

        entityTransaction.commit();
        entityManager.close();
    }

    public static void main(String[] args) {
        findAll5();
    }
}
