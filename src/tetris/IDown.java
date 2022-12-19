package tetris;

public class IDown extends Piece{
    protected IDown( Point point ){
        super();
        leftPoint = point;
        downPoint = point;
        for( int i = 0 ; i < 4 ; i++) {
            cubs.add( point );
            point = point.move( new int[] { 0 , 1 } );
        }
        rightPoint = point;
    }

}
