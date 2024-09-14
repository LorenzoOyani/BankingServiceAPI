package org.example.bankingportal.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;

import static org.hibernate.cfg.AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION;
import static org.hibernate.tool.schema.Action.CREATE;


import java.util.Map;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence
                        .createEntityManagerFactory("book_persistence",
                                Map.of(JAKARTA_HBM2DDL_DATABASE_ACTION, CREATE));

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            //entity  persist
            inSession(entityManagerFactory, entityManager1 -> {
                entityManager1.persist(new Book("running mile", "123456543"));
            });


            inSession(entityManagerFactory, entityManager1 -> {
                var builder =entityManagerFactory.getCriteriaBuilder();
                CriteriaQuery<String > bookCriteriaQuery = builder.createQuery(String.class);
                var book=bookCriteriaQuery.from(Book.class);

            });

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

    }

    private static void inSession(EntityManagerFactory factory, Consumer<EntityManager> action) {
        var entityManager = factory.createEntityManager();
        var transaction =entityManager.getTransaction();

        try{
            transaction.begin();;
            action.accept(entityManager);
            transaction.commit();
        }catch (
                Exception e
        ){
            if(transaction.isActive()){
                transaction.rollback();
            }

        }

    }
}
