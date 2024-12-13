package org.example.bankingportal.concurrency;

public class ApplicationRunner {

    public static void main(String[] args) throws InterruptedException {
        Computable computable = new ExpensiveFunc();

        Object s = 200;

       Object c = computable.compute(s);

       Class<?> objClass = c.getClass();

       System.out.println(objClass.getSimpleName().toLowerCase()); // print out the object class name


       Memoirs memoirs = new Memoirs(computable);

      Object result =  memoirs.compute(c);

      System.out.println(result);
    }




}
