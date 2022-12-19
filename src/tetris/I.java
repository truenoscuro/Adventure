package tetris;

public class I extends Piece{

    protected I( Point point ){
        super();
        leftPoint = point;
        downPoint = point;
        rightPoint = point;
        for( int i = 0 ; i < 4 ; i++) {
            cubs.add( point );
            point = point.move( new int[] { 1 , 0 } );
        }

    }
}
