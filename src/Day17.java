import tetris.Piece;
import tetris.Point;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day17 {
    private static BufferedReader br = null;
    private static String rd;

    private static void open( )  {
        String path = (isExample)? "examples":"utils";
        try {
            br = new BufferedReader(new FileReader(path+"/day"+ numTxt +".txt"));
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








    private static boolean isRange( Piece piece,int [] dir ){
        return piece.getCubs()
                .stream()
                .map(point -> point.move( dir ) )
                .anyMatch( point -> {
                    int [] vec = point.getPoint();
                    if ( vec[1] == left || vec [1] == righ || vec[0] ==down ) return false;
                return rock.stream().noneMatch( r -> r.equals( point ) );
                });
    }




    private static void  problem1(){
        open();
        int idPiece = 0;
        Point point = new Point( new int [] { 4 , 4} );
        boolean isPut = false;
        Piece piece = null;
        while ( rd != null ){
            char [] charRd = rd.toCharArray();
            int indx = 0;
            while ( indx < charRd.length ) {
                if (isPut) piece = Piece.generatePiece(idPiece, point);
                isPut = false;
                while (indx < charRd.length && !isPut) {
                    char dir = charRd[ indx++ ];
                    if (dir == '>' && isRange( piece , new int[]{0, 1})) {
                        piece.right();
                    } else if ( dir == '<' && isRange( piece , new int[]{0, -1})) {
                        piece.left();
                    }
                    if ( isRange( piece , new int[]{ -1 ,  0 } ) ) {
                        piece.down();
                        continue;
                    }
                    point.move(piece.,0)
                    isPut = true;
                    rock.addAll( piece.getCubs( ) );
                }
            }
        }

    }





    private static void  problem2(){

    }

    static ArrayList<Point> rock = new ArrayList<>();
    static int left = 0 ;
    static int righ = 8 ;
    static  int down = 0;

    static int top = 4;

    static boolean isExample = true;
    static int numTxt = 0;
    public static void main(String[] args) {



    }
}
