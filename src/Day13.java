import Objects.Signal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( boolean isExample , int num )  {
        String path = (isExample)? "examples":"utils";
        try {
            br = new BufferedReader(new FileReader(path+"/day"+ num +".txt"));
            read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // <-- initial read
    }
    private static void read()  {
        try {
            rd = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


















    private static void  problem1(){
        ArrayList<Signal> signals = new ArrayList<>();
        rd = "[[2]]";
        Signal s1 = new Signal( rd , 0 );
        s1.add();
        signals.add( s1 );
        rd = "[[6]]";
        Signal s2 = new Signal(rd,0);
        s2.add();
        signals.add( s2 );

        open(false ,13);

        while( rd != null ){
            s1 = new Signal(rd,0);
            s1.add();
            signals.add(s1);
            read();
            s2 = new Signal(rd,0);
            s2.add();
            signals.add(s2);
            read();
            read();

        }
       // sort(signals);
        signals.sort(Signal::compare);
        signals.forEach(System.out::println);
        String r1 = "[[2]]";
        String r2 = "[[6]]";
        int solv = 1;
        for(int i = 0 ; i < signals.size(); i++){

            if (signals.get(i).getRd().equals( r1 ) ) {

                solv*=(i+1);
            }
            if (signals.get(i).getRd().equals(r2)){

                solv*=(i+1);
            }
        }
        System.out.println("Solv --> " + solv);

    }

    public static void main(String[] args) {
        problem1();

        /*
        ArrayList<Signal> signals = new ArrayList<>();
        rd = " [1,[2,[3,[4,[5,6,7]]]]],8,9]";

        Signal s1 = new Signal( rd , 0 );
        s1.add();
        signals.add( s1 );
        System.out.println(s1);
        rd = "[1,[2,[3,[4,[5,6,0]]]],8,9]";
        Signal s2 = new Signal(rd,0);
        s2.add();
        System.out.println(s2);

        signals.add( s2 );
        s1.compare(s2);


         */



    }
}
