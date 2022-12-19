import Objects.Valve;
import Objects.ValvePoint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Day16 {
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

    public static boolean  isNum(char a){ return '0'<= a && a <= '9';}
    public  static boolean isLowerCase(char a){ return  'a' <= a && a <= 'z' ;}
    public static boolean isUpperCase(char a){ return  'A' <= a && a <= 'Z' ;}
    static  public void initGraf (){
        open();
        while (rd!= null){
            String valveName = rd.charAt(6)+ "" + rd.charAt(7);
            String numString = "";
            int idx = 23;
            while( isNum( rd.charAt( idx ) ) )numString = numString + rd.charAt(idx++);
            int flowRate = Integer.parseInt(numString);
            ArrayList<String>  tunelsNames = new ArrayList<>();
            idx = rd.length()-1;
            String tunelName = "";
            while ( ! isLowerCase(rd.charAt(idx) ) ){
                if(isUpperCase(rd.charAt(idx)))
                    tunelName = rd.charAt(idx) + tunelName;
                else if ( tunelName.length() > 0 ){
                    tunelsNames.add(0,tunelName);
                    tunelName = "";
                }
                idx--;
            }
            graf.add( new Valve(valveName,flowRate,tunelsNames) );
            read();
        }
        for( Valve valve :graf){
            ArrayList<String> tunelsNames = valve.getTunelsNames();
            for(String tunelName : tunelsNames)
                for( Valve valve1:graf){
                    if(!valve1.getName().equals(tunelName) )continue;
                    valve.addTunel( valve1 );
                    break;
                }
        }
        graf.forEach(System.out::println);

    }







    private static boolean isOpen( Valve valve , ArrayList<String> noUsats ){
        for (String tunels : noUsats)
            if(tunels.equals( valve.getName() ) )return true;
        return false;
    }
    private static void setList(String str,ArrayList<String> usats){
        for(String s : usats) if(s.equals(str)) return;
        usats.add(str);

    }
    private static Valve searchString(String str){
        for(Valve valve: graf){
            if(valve.getName().equals(str)) return valve;
        }
        return graf.get(0);
    }
    private static int heuristic(Valve valve,ArrayList<String> noUsats,int total, int time){
        ArrayList<String> greedyPath = new ArrayList<>(noUsats);
        while (!greedyPath.isEmpty()){
            AtomicReference<String> strMax = new AtomicReference<>("");
            AtomicInteger max = new AtomicInteger();
            int finalTime = time;
            Valve finalValve = valve;
            greedyPath.forEach(str -> {
                Valve vAux = searchString(str);
                int timeAux = finalTime -  vAux.rute(finalValve) ;
                int totalAux = vAux.getFlowRate()*timeAux;
                if(totalAux > max.get()){
                    max.set(totalAux);
                    strMax.set(str);
                }
            } );
            greedyPath.remove(strMax.get());
            Valve child = searchString(strMax.get());
            time -= ( child.rute(valve) + 1 );

            if( time <= 0 ) break;
            total += ( time )*child.getFlowRate();
            valve = child;
        }
        return  total;

    }
    private static boolean isDiscovered(Valve v, ArrayList<Valve> discovereds){
        for(Valve valve:discovereds){
            if(valve.getName().equals(v.getName())) return true;
        }
        return false;
    }
    private static void dijkstrat(){
        initGraf();
        ArrayList<ValvePoint> openList = new ArrayList<>();
        ArrayList<String> valueGraf = new ArrayList<>();
        graf.forEach( g-> { if( g.getFlowRate() > 0 ) valueGraf.add( g.getName() );});
        // ( P , total , time , ( li queden ) )
        // heuristic seria si p.getflorw + h <  maxF --> continue
        // maxF sera el primer que es el gready  agafa el més gran i dirigirse al proxim més gran
        int maxF = heuristic(graf.get(0),valueGraf,0,30); //solvGready
        openList.add(new ValvePoint(graf.get(0),30,0,maxF, valueGraf));
        while (!openList.isEmpty()){
            // treim per total més gran
           // System.out.println(openList.size());
            openList.sort(ValvePoint::comp);
            ValvePoint point = openList.get(0);
            openList.remove(0 );
            //System.out.println(maxF);
            Valve valve = point.getValve();
            ArrayList<String> unsetsString = point.getValves();
            int total = point.getTotal();
            int time = point.getTime();
            //if( isDiscovered( valve , discovereds ) ) continue;
            // no afagir un punt no te sentit
            // heuristic va aqui. Si p.getflow*(time-dist(V,H)) + heuristic < maxF --> continue
            for (String str : unsetsString){
                Valve newValve = searchString(str);
                int dist = newValve.rute( valve );
                int newTime =  ( time - ( dist + 1 ) );
                if( newTime <= 0) continue;
                int newTotal = total + newValve.getFlowRate()*newTime;
                ArrayList<String> newUnset = new ArrayList<>(unsetsString);
                newUnset.remove(str);
                int h = heuristic(newValve,newUnset,newTotal,newTime);
                //if( h < maxF) continue;
                if( newTotal > 1792){
                    System.out.println("MAL");
                }
                maxF = Math.max( maxF , newTotal );
                //ValvePoint children1 = new ValvePoint( newValve , newTime + 1 , total , heuristic,new ArrayList<>(unsetsString) );
                ValvePoint children2 = new ValvePoint( newValve , newTime  , newTotal ,  newTotal , newUnset );
                //openList.add(children1);
                openList.add(children2);
            }

        }
        System.out.println(heuristic(graf.get(0),valueGraf,0,30));
        System.out.println(maxF);



    }
    /*






     */


    private static int play (Valve valve,ArrayList<String> noUsats,int time , int total){
        ArrayList<String> noUsatsNous = new ArrayList<>(noUsats);
        if( isOpen( valve,noUsatsNous ) ){
            time--;
            total += time * valve.getFlowRate();
            noUsatsNous.remove( valve.getName() );
        }
        time--;
        // int h = heuristic( noUsatsNous , total , time );
        if( time  <= 0   || noUsats.size() == 0  )  return total;
        int auxTotal = total;
        for( Valve valve1 : valve.getTunels() )
            if( isOpen( valve1, noUsatsNous ) )
                total = Math.max( total , play( valve1 , noUsatsNous, time , auxTotal ) );
        return total;
    }


    private static void  problem1(){
        initGraf();
        Valve v = graf.get(0);
        ArrayList<String> noUsats = new ArrayList<>();
        for(Valve valve : graf) noUsats.add(valve.getName() );

        int total = play( v ,noUsats ,30  ,0);
        System.out.println(total);

    }
    private static void  problem2(){

    }


    static ArrayList<Valve> graf = new ArrayList<>();
    static boolean isExample = false;
    static int numTxt = 16;
    public static void main(String[] args) {
        //problem1();
        dijkstrat();
    }
}
