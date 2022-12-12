import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day10 {
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
    private static boolean isNumber( char a ){
        return '0' <= a && a <= '9';
    }
    private static  boolean isNeg(char a){ return a =='-';}
    private static int num( ){
        String numString = "";
        int i = rd.length()-1;
        while( isNumber( rd.charAt( i )) ||isNeg(rd.charAt(i)) ) numString = rd.charAt(i--) + numString;
        if( numString.equals("") )  return 0;
        return Integer.parseInt(numString);
    };

    private static void problem1(){
        open(false,10);
        int total = 0;
        int cumSum = 1;
        int sumNum ;
        int clock = 1;
        int incr ;
        int cicleLimit = 20;
        while(rd != null){
            incr = 1;
            sumNum = num();
            if( sumNum != 0 ) incr = 2;
            if( clock + incr > cicleLimit){
                total += cicleLimit * cumSum;
                System.out.println("T "+cicleLimit+" -- "+total);
                cicleLimit += 40;
            }
            cumSum += sumNum;
            clock += incr;
            read();
        }

        System.out.println(total);
    }

    private static String initSpirte(){
        return "###.....................................";
    }
    private static String change(String str, String change, int pOld,int p){
        if(pOld > 37) pOld = 36;
        if(p > 37) p = 36;
        str = str.substring( 0 , pOld )+"..."+str.substring( pOld + change.length( ) );
        return str.substring( 0 , p )+change+str.substring(p + change.length( ) );
    }


    private static void problem2(){
        open(false,10);
        int cumSum = 0;
        int sumNum ;
        int incr ;
        int cicleLimit = 40;
        String ctr ="";
        int indxCtr = 0;
        String spryte  = initSpirte();
        while( rd != null ){
            incr = 1;
            sumNum = num();
            if( sumNum != 0 ) incr = 2;
            for (int i = 0 ;  i < incr ;  indxCtr++ , i++){
                if( indxCtr == cicleLimit){
                    System.out.println( ctr );
                    indxCtr = 0;
                    ctr = "";
                }
                ctr += spryte.charAt( indxCtr );
            }
            if( incr > 1 ) {
                //System.out.println(Math.abs(cumSum + sumNum));
                spryte = change( spryte , "###" , Math.abs(cumSum) , Math.abs( cumSum + sumNum ) );
                cumSum += sumNum;
            }
            //System.out.println( cumSum + " -- "+spryte);
            read();
        }

        System.out.println(ctr);

    }




    public static void main(String[] args) {
        problem2();

    }
}
