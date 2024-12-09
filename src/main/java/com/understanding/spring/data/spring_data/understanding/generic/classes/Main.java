package com.understanding.spring.data.spring_data.understanding.generic.classes;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PrintGeneric pg1 = new PrintGeneric(); //Here the compiler passes type as Object
        pg1.set("1");
        pg1.set(1);
        PrintGeneric<Integer> pg2 = new PrintGeneric();
        pg2.set(1);
        //pg2.set("1"); wrong as Print is limited to Integer.
        PrintRegular pr1 = new PrintRegular();
        pr1.set("Tanson", new Car());
        PrintWildCard pwc = new PrintWildCard();
        List<Vehicle> lv = new ArrayList();
        lv.add(new Vehicle());
        lv.add(new Vehicle());
        pwc.set(lv);
        pwc.set1(lv);
        List<Bus> lb = new ArrayList();
        lb.add(new Bus());
        lb.add(new Bus());
        pwc.set(lb);
        pwc.set1(lb);
        List<Car> lc = new ArrayList();
        lc.add(new Car());
        lc.add(new Car());
        //pwc.set(lc); -- this will not work as Car is not super of Bus
        pwc.set1(lc);
    }

    public void understandGeneric1(){
        List<Vehicle> vL = new ArrayList<>();
        List<Bus> bL = new ArrayList<>();

        Vehicle v = new Vehicle();
        Bus b = new Bus();

        v=b; //this is valid
        //vL = bL; - this will not work as List of Vehicle will become List of Bus.
    }
}

class PrintGeneric<A>{
    A val;

    void set(A a){
        System.out.println(a);
    }

    A get(){
        return val;
    }
}

class PrintRegular{

    <A,B extends Vehicle & Icar> void set(A a, B b){
        System.out.println(a);
        System.out.println(b);
    }
}

class PrintWildCard{
    void set(List<? super Bus> val){
        val.add(new Bus()); //open for writing and reading
    }

    void set1(List<? extends Vehicle> val){
        //val.add(new Bus()); //not open for writing but can be read.
    }
}


