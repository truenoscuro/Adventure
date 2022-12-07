import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day7 {
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
    private static boolean isFinish(){ return rd == null; }
    private static boolean isCommand(){
        return rd.charAt(0) == '$';
    }

    private static String cd(){
        String dir = " ";
        int i = rd.length()-1;
        while( rd.charAt(i) != ' ')  dir = rd.charAt(i--)+dir;
        return dir;
    }
    private static boolean isNum( int i ){
        return '0' <=  rd.charAt(i) && rd.charAt(i) <= '9';
    }
    private static int sumNum(){
        String num = "";
        int i = 0;
        while ( isNum( i ) ) {
            num +=  rd.charAt( i++ );
        }
        return Integer.parseInt( num );
    }
    private static int [] ls(){
        int [] result = { 0 , 0 }; //{ numDir , totalFile }
        // enter with $ls comand
        read();
        System.out.println("ls --> " + rd );
        while( !isFinish() && !isCommand() ){
            if( !isNum(0 ) ) result[0]++;
            else result[1] += sumNum();
            read();
            System.out.println("ls --> " + rd );
        }
        // finish in comand
        return  result;
    }
    private static void cdMM(){

    }


    private static int sizeDir(int total){
        if(isFinish()) return total;
        // the first element is cd
        String cd = cd();
        read(); // nextLine;
        // if cd == .. return total = 0;
        if ( cd.contains( ".." ) ) {
            System.out.println(".. --> " +rd );
            return total;
        }
        System.out.println("cd --> " + rd );
        // ls return { numDir , totalFiles }
        int [] ls = ls();
        total = ls[1];
        // sum dirFile
        for ( int i = 0 ; i < ls[0] ; i++) {
            total += sizeDir( 0 ); // return cd ..
            read(); //move
        }
        // save file if total > max
        push( cd , total );;
        //if(total > 0 && total <= max);
        return total;
    }


    private static void push( String name , int total ){
        names.add(0, name );
        sizeDirs.add(0,total );
    }

    private static int max = 100000;
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<Integer> sizeDirs = new ArrayList<>();
    public static void main(String[] args) {
        // Part 1
        open(false,7);
        sizeDir( 0 );
        System.out.print("sum Min --> ");
        sizeDirs.stream().filter( num -> num < max).reduce(Integer::sum).ifPresent(System.out::println);
        // Part 2
        int update = 30000000;
        int spaceRest = 70000000-sizeDirs.get(0);
        int neededSpace = update -spaceRest;
        int firstElement=sizeDirs.get(0);
        System.out.println( "Min disc eliminate is --> " +
                sizeDirs.stream().reduce(firstElement,(min,size) ->
                (size - neededSpace < min -neededSpace  && size - neededSpace > 0)?size:min
        ));




    }
}
