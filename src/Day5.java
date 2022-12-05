import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day5 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( int num) throws IOException {
        br = new BufferedReader(new FileReader("utils/day"+ num +".txt"));
        read(); // <-- initial read
    }
    private static void read() throws IOException {
        rd = br.readLine();
    }
    private static void initStacks(ArrayList[] stacks) throws IOException {
        int leng = stacks.length;
        char box;
        open(5);
        do{
            for(int i = 0; i < leng;i++){
                box =  rd.charAt(1+4*i);
                if( box < 'A' || box > 'Z') continue;
                stacks[i].add(box);
            }
            read();
        }while (rd.charAt( 1 ) > '9');
    }
    private static char pop( ArrayList stack ){
        char box = (char) stack.get( 0 );
        stack.remove( 0 );
        return box;
    }
    private static void push ( char box , ArrayList stack){
        stack.add( 0,box );
    }
    private static boolean isNum (char chr){
        return !(chr < '0' || chr > '9');
    }
    private static int [] instruction(){
        int [] inst = new int[3];
        int indx = 0;
        int leng = rd.length();
        char chr;
        String num ;
        for (int i = 0; i < leng; i++){
            chr = rd.charAt( i );
            if( !isNum(chr) ) continue;
            num = "";
            while(  isNum( chr )){
                num += chr;
                if( ++i >= leng ) break;
                chr = rd.charAt( i );
            }
            inst[indx++] = Integer.parseInt(  num );
        }
        return  inst;
    }
    private static void move( int[] inst , ArrayList[] stacks ){
        int total = inst[0] ;
        ArrayList oldStack = stacks[ inst[1] - 1 ] ;
        ArrayList newStack = stacks[ inst[2] - 1 ] ;
        for (int i = 0; i < total; i++) push( pop( oldStack ) , newStack ) ;
    }

    public static void main(String[] args) throws IOException {
        /*
        open(5);
        System.out.println(rd);
        System.out.println(rd.charAt(1));
        read();
        System.out.println(rd);
        System.out.println(rd.charAt(1));
        System.out.println(rd.charAt(1+4));
        read();
        System.out.println(rd);
        System.out.println(rd.charAt(1+4*3));
        open(5);
        System.out.println(rd.charAt(1+4*8));
         */

        ArrayList[] stacks = new ArrayList[9];
        for (int i = 0 ; i < 9 ; i++) stacks[i] = new ArrayList();
        initStacks(stacks);
        read();
        read();
        while (rd != null){
            move( instruction() , stacks);
            read();
        }
        for ( ArrayList stack : stacks){
            System.out.print(pop( stack ) );
        }

    }
}
