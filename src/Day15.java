import Objects.Interval;
import Objects.SetInteger;
import Objects.Vec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    3 --> M1 - C > 0 --> Deim D = M1-C aleshores x1 pertany a [ a1 - D , D + a1]) (D-a1)-(a1-D) = (2*(M1-C)-2a1)
     [    ]   [    ]
        [       ] --> la idea es ordenar per la y1 [y1,y2]
     */
    static int manhatam (int [] x ,int [] y){
        return Math.abs(x[0]-y[0]) + Math.abs(x[1]-y[1]);
    }
    static int calcC( int [] x, int i ){
        return Math.abs(x[1] - i );
    }
    static int searchEqual(int i){
        while( i < rd.length() && rd.charAt(i) != '=') i++;
        return ++i;
    }
    static int[][] calcPoints (){
        int [][] solv = { {0,0},{0,0} };
        int indx = 0;
        for(int r = 0 ; r < 2; r++){
            for(int c = 0; c < 2; c++){
                indx = searchEqual( indx );
                String numString = "";
                while(indx < rd.length() &&rd.charAt(indx)!=',' && rd.charAt(indx)!=':'){
                    numString =numString +rd.charAt(indx);
                    indx++;
                }
                solv[r][c] = Integer.parseInt(numString);
            }

        }
        //System.out.println(Arrays.deepToString(solv));
        return solv;

    }
    static ArrayList<Vec> sensors = new ArrayList<>();
    static ArrayList<Integer> manhatans = new ArrayList<>();
    static  int yMax ;
    static  int xMax ;
    static  int yMin ;
    static  int xMin ;
    static void initVec(){
        yMax = 0;
        yMin = 4000000;
        xMin = 4000000;
        xMax = 0;
        while(rd!= null){
            int[][] para = calcPoints();
            manhatans.add(manhatam(para[0],para[1]));
            sensors.add(new Vec(para[0]));

            yMax = Math.max(para[0][1],yMax);
            xMax = Math.max(para[0][0],xMax);
            if( para[0][0] > 0 ) xMin = Math.min(para[0][0],xMin);
            if( para[0][1] > 0 ) yMin = Math.min(para[0][1],yMin);

            read();
        }

    }
    static ArrayList<Interval> initIntervals(  int yInit ){

        ArrayList<Interval> intervals  = new ArrayList<>();
        int maxM = 0;
        while(rd!= null){
            int[][] para = calcPoints();
            int M = manhatam(para[0],para[1]);
            maxM = Math.max(M,maxM);
            int C = calcC(para[0],yInit);
            int D = M - C;
            if( D == 0 ) intervals.add(new Interval(para[0][0],para[0][0]));
            if( D > 0) intervals.add( new Interval(para[0][0] - D , D + para[0][0] ) );

            read();
        }
        intervals.sort(Interval::comp);
        return intervals;

    }



    static ArrayList<Interval> prova(  int yInit ,int[] range){
        ArrayList<Interval> intervals  = new ArrayList<>();
        open(true,15);
        while(rd!= null){
            int[][] para = calcPoints();
            int M = manhatam(para[0],para[1]);
            int C = calcC(para[0],yInit);
            int D = M - C;
            int left = D + para[0][0] ;
            int right = para[0][0] - D ;
            intervals.add( new Interval( range[0],right ) );
           // System.out.println(new Interval( 0,right ) );
            intervals.add( new Interval( left,range[1] ) );
           // System.out.println(new Interval( left,20 ) );
            read();
        }


       // intervals.forEach(System.out::println);
        return intervals;

    }



    private static void  problem1(){
        ArrayList<Interval> intervals = initIntervals( yInit );
        int x = intervals.get(0).getX();
        int y = intervals.get(0).getY();
        for(Interval interval :intervals){
            int xAux = interval.getX();
            int yAux = interval.getY();
            if( y < xAux){
                total += (y - x ) + 1  ;
                x = xAux;
                y = yAux;
                continue;
            }
            x = Math.min( x , xAux )  ;
            y = Math.max( y , yAux )  ;
        }
        total += (y - x) + 1;
        System.out.println("TOTAL --> " + total);

    }
    private static void  problem2(){
    }
    public static  boolean isContinus( int yInit ) {

        ArrayList<Interval> intervals = new ArrayList<>();
        int derMax = xMax;
        int izqMin = xMin;
        for(int i = 0 ; i < manhatans.size(); i++){
            int C = calcC(sensors.get(i).getV(),yInit);
            int D = manhatans.get(i) - C;
            int izq = sensors.get(i).getV()[0] - D;
            int der = D + sensors.get(i).getV()[0];
            if( izq < izqMin && der < izqMin ) continue;
            if( D <= 0 ) continue;
            izqMin = Math.min(izqMin,izq);
            derMax = Math.max(derMax,der);
            intervals.add( new Interval( izq , der ) );
        }
        intervals.sort(Interval::comp);
        int derFinal = intervals.get(0).getY();
        for( Interval interval: intervals ){
            int der = Math.min(interval.getY(),derMax);
            int izq = interval.getX();
            if( izq <= derFinal || izq + 1 == der  ){
                derFinal = Math.max( der , derFinal );
            }else{
                System.out.println(" y = "+ yInit + " x = "+ (izq-1));
                double solv = (izq-1)*4000000 + yInit;
                System.out.println("Solve --> " + solv);
                return false;
            }
            if( derMax == der ) break;

        }

        return true;

    }

    static  int yInit = 10;
    static int total = 0;
    public static Interval pop (ArrayList<Interval> intervals){
        Interval interval = intervals.get(0);
        intervals.remove(0);
        return interval;

    }
    public static  void setInterval(Interval interval,ArrayList<Interval> intervals){
        for(Interval i : intervals){
            if(interval.comp(i) == 0) return;
        }
        intervals.add(interval);
    }


    public static void insersection(ArrayList<Interval> intervals){
        ArrayList<Interval> mult = new ArrayList<>();
        mult.add(pop(intervals)); // aqui fer pop
        mult.add(pop(intervals));
        while (!intervals.isEmpty()){
            ArrayList<Interval> aux = new ArrayList<>();
            Interval i1 = pop(intervals); // aqui fer pop
            Interval i2 = pop(intervals);
            for( Interval i : mult ){
                Interval i1Aux = i.interseccio( i1 );
                if(i1Aux.getX() < i1Aux.getY()) setInterval(i1Aux,aux);
                Interval i2Aux = i.interseccio( i2 );
                if(i2Aux.getX() < i2Aux.getY())  setInterval(i2Aux,aux);
            }
            for( Interval i : aux){
                setInterval(i,mult);
            }

        }
        mult.forEach(System.out::println);

    }
    public static void main(String[] args) {
        open(false,15);
        initVec();
        for(int i = 2906626 ; i < yMax; i++){
            if(isContinus( i ) ) continue;
            break;
            //interseccion
        }



    }
}
