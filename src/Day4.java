import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day4 {


    private static BufferedReader br = null;
    private static String rd;

    private static void open( int num) throws IOException {
        br = new BufferedReader(new FileReader("utils/day"+ num +".txt"));
        read(); // <-- initial read
    }
    private static void read() throws IOException {
        rd = br.readLine();
    }
    private static boolean isNumber(char r){
        String numbers= "0123456789";
        int leng = numbers.length();
        for( int i = 0 ; i < leng; i++)
            if ( numbers.charAt( i ) == r) return true;

        return  false;
    }
    private static  int searchComa(){
        int leng = rd.length();
        int i = 0;
        while (i<leng && rd.charAt( i ) != ',' )
            i++;
        return i;
    }
    private static  int [] sectionElf ( int p ){
      int [] section = {0,0};
      int leng = rd.length();
      int indxS = 0;
      String numbAux = "";
      char r ;
      for( int i = p ; i < leng  && indxS < 2 ; i++){
          r = rd.charAt( i );
          if( isNumber(r) ) {
              numbAux += r ;
              continue;
          }
          section[indxS++] = Integer.parseInt(numbAux);
          numbAux = "";
      }
      if ( p > 0){
          section[indxS] = Integer.parseInt(numbAux);
      }
      return section;
    }
    private static boolean isFullyContainElfFirst(int [] elf1,int [] elf2){
        //elf 1 fully Contain en elf2
        // elf2[0] <elf1[0] && elf1[1] < elf2[1]
        return elf2[0] <= elf1[0] && elf1[1] <= elf2[1];
    }
    private static boolean isSectionContainElfFirst(int [] elf1, int [] elf2){
        // elf 1 no is contain in elf 2
        //  elf1[1] < elf2[0] || elf2[1] < elf1[0]
        return !( elf1[1] < elf2[0] || elf2[1] < elf1[0]);
    }

    public static void main(String[] args) throws IOException {
/*
        open(4);
        System.out.println(rd);
        System.out.println(isNumber(rd.charAt(2)));
        int [] numbers = sectionElf(0);
        System.out.println(Arrays.toString(numbers));
        System.out.println(rd.charAt(searchComa()));
        int  [] ex1 = {2,8};
        int [] ex2 = {3,7};
        System.out.println(
                isFullyContainElfFirst(ex1, ex2) + "\n" +
                isFullyContainElfFirst(ex2, ex1) + "\n"
        );
 */
    // part 1
        open(4);
        int numFullyContain = 0;
        int [] elf1 ;
        int [] elf2 ;

        while( rd != null){
            elf1 = sectionElf(0);
            elf2 = sectionElf(searchComa() + 1 );

            if(     isFullyContainElfFirst( elf1 , elf2 ) ||
                    isFullyContainElfFirst( elf2 , elf1 ) ) {
                numFullyContain++;
            }
            read();
        }

        System.out.println(" numFullyContain --> " + numFullyContain);

        open(4);
        int sectionContain = 0;
        while( rd != null){
            elf1 = sectionElf(0);
            elf2 = sectionElf(searchComa() + 1 );

            if(     isSectionContainElfFirst( elf1 , elf2 ) ||
                    isSectionContainElfFirst( elf2 , elf1 ) ) {
                sectionContain++;
            }
            read();
        }

        System.out.println(" numFullyContain --> " + sectionContain);




    }
}
