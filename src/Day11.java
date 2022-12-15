import Objects.Monkey;
import Objects.Num;
import Objects.Zn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

public class Day11 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open(boolean isExample, int num) {
        String path = (isExample) ? "examples" : "utils";
        try {
            br = new BufferedReader(new FileReader(path + "/day" + num + ".txt"));
            read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // <-- initial read
    }

    private static void read() {
        try {
            rd = br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private static void list(Monkey m) {
        String numString = "";
        int leng = rd.length();
        char a;
        for (int i = 18; i < leng; i++) {
            a = rd.charAt(i);
            if ('0' <= a && a <= '9') {
                numString += a;
                if(i + 1 < leng ) continue;
            }
            if ( numString.length() > 0 )
                m.add( Integer.parseInt( numString ) );
            numString = "";
        }
    }
    public static int num( String str){
        String numString = "";

        for( int i = str.length() - 1; i >= 0; i-- ){
            char a = str.charAt( i );
            if( '0'> a || a > '9') break;
            numString = a + numString;
        }
        return Integer.parseInt(numString.toString());
    }
    public static void construcMonkeys( Monkey m ){
        read(); // readMonkey
        list( m );
        read();
        m.setOperation( rd );
        read();
        m.setTest( num( rd ) );
        primers.add(num( rd ));
        read();
        m.setNumMonkeys( num( rd ) );
        read();
        m.setNumMonkeys( num( rd ) );
        read();
        read();


    }

    private static void includeMonkeys(Monkey m){
        m.getNumMonkeys().forEach( idx -> m.setMonkeys(monkeys.get(idx)));
    }
    private static void problem1() {
        open(false,11);
        //init monkeys
        int name = 0;
        while( rd != null){
            Monkey monkey = new Monkey( name++ );
            construcMonkeys( monkey );
            monkeys.add(monkey);
        }

        Zn zn = new Zn( primers );
        monkeys.forEach( monkey -> monkey.setZn( zn ) );
        for (Monkey monkey : monkeys) includeMonkeys( monkey );
        // real problem

        int num = 10000;
        for ( int i = 0; i < num ; i++ ) {
            monkeys.forEach(Monkey::turn);
            System.out.println(i);
        }
        System.out.println("-------------------");
        monkeys.forEach(System.out::println);
        System.out.println("-------------------");
        monkeys.sort(Monkey::compara);
        int m1 = monkeys.get(0).getInsp();
        int m2 = monkeys.get(1).getInsp();
        System.out.println( "Resolv -->" + (double) m1 * m2 );


    }



    static ArrayList<Monkey> monkeys = new ArrayList<>();
    static ArrayList<Integer> primers = new ArrayList<>();
    public static void main(String[] args) {
       //problem1();
        double solv = Integer.MAX_VALUE;
        int  solv2 = (int) solv;
        System.out.println(solv2);

        //int sqrt= ( int ) Math.sqrt( max );
       // System.out.println((sqrt+100)*(sqrt-100));

    }
}
