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
        stack.add( box );
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
        System.out.println(Arrays.toString(stacks));
        System.out.println(rd);
        read();
        System.out.println(rd);
        read();
        System.out.println(rd);



    }
}
