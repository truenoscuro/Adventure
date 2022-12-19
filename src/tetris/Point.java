package tetris;

public class Point {
    int [] point;

    public  Point(int [] point){
        this.point = point;
    }

    public int[] getPoint() { return point; }

    public Point move(int [] dir){
        return new Point( new int [] { point[0] + dir[0] , point[1] + dir[1] } );
    }

    @Override
    public boolean equals(Object obj) {
        Point p = (Point) obj;
        int [] point2 = p.getPoint();
        return point2[0] == point[0] && point2[1] == point[1];
    }
}
