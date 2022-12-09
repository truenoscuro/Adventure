import Objects.Vec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day9 {
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


    private static ArrayList<Vec> visitets = new ArrayList<>();
    private static Map< String , Vec > dirs = new HashMap<>();

    private static  boolean isVisited( int [] point ) {
        return visitets.stream().anyMatch(vec -> {
            int[] v = vec.getV();
            return v[0] == point[0] && v[1] == point[1];
        });

    }
    private static void initDirs(){
        dirs.put("R",Vec.nw( new int [] { 0 , 1 } ) );
        dirs.put("L",Vec.nw( new int [] { 0 , -1 } ) );
        dirs.put("D",Vec.nw( new int [] { 1 , 0 } ) );
        dirs.put("U",Vec.nw( new int [] { -1 , 0 } ) );
    }
    public static void main(String[] args) {
        open(false,9);
        // init dirs



        int [] T = {0,0};
        int [] H = {0,0};
        int numMov;
        int  [] nextMov;
        initDirs();
        visitets.add( Vec.nw( T ) );
        // se direccio
        while( rd != null ){
            /*
            nextMov = update();
            dir = dirs.get("String");
            numMov = nextMov[1];
            for (int i = 0; i < numMov; i++){
                T = H;
                H = sum(T,dir); // <-- new int []
                if(!isVisited( T )){
                    visitets.add(Vec.nw(T));
                }
            }
            */
            read();
        }

    }
}
