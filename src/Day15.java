import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day15 {
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

    }
    private static void  problem2(){

    }


    /*
    Manhatan  M(X,Y) = |x1-x2|+|y1-y2|
    tu sabs el manhatan del enunciat
    M1 = |a1-a2|+|b1-b2|
    Larea de manhatan d'un elemnet es si A es el centre
    M(A,Y) = |a1-x2| +|b1-y2| <= M1
    Fixam   y2 = 20000
    |a1-x2| + C <= M1 on C es el resultat de |b1-Y2| >= 0
    tres casos
    1 --> M1 - C < 0 --> No existeix x2
    2 --> M1 - C == 0 -->  un unic cas que a1 = x2
    3 --> M1 - C > 0 --> Deim D = M1-C aleshores x1 pertany a [ a1 - D , D - a1]) (D-a1)-(a1-D) = (2*(M1-C)-2a1)
     [    ]   [    ]
        [       ] --> la idea es ordenar per la y1 [y1,y2]
     */

    public static void main(String[] args) {


    }
}
