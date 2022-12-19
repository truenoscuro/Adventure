package tetris;

public class O extends Piece{
    protected O ( Point point ){
        super();

        leftPoint = point;
        downPoint = point;
        rightPoint =  point.move( new int [] {0,1} );

        cubs.add( point );
        cubs.add( point.move( new int [] {1,0} ) );
        cubs.add( point.move( new int [] {0,1} ) );
        cubs.add( point.move( new int [] {1,1} ) );
    }
}
