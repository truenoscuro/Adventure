package tetris;

public class I extends Piece{
    private I(Point point){
        super();
        for( int i = 0 ; i < 4 ; i++) {
            cubs.add( point );
            point = point.move( new int[] { 0 , 1 } );
        }
    }

}
