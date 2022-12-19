package Objects;

import java.util.ArrayList;
import java.util.Objects;

public class Valve {
    private boolean isOpen ;
    private ArrayList<Valve> tunels;
    private ArrayList<String> tunelsNames;
    private int flowRate;
    private String name;

    public Valve (String name, int flowRate,ArrayList<String> tunelsNames){
        isOpen = false;
        tunels = new ArrayList<>();
        this.flowRate = flowRate;
        this.name = name;
        this.tunelsNames = tunelsNames;
    }
    public Valve (String name, int flowRate,ArrayList<String> tunelsNames,
                  ArrayList<Valve> tunels) {
        isOpen = false;
        tunels = new ArrayList<>();
        this.flowRate = flowRate;
        this.name = name;
        this.tunelsNames = tunelsNames;
        this.tunels = tunels;
    }
    public void addTunel( Valve valve ){ tunels.add( valve ); }
    public void addTunelName( String tunelName ){ tunelsNames.add( tunelName ); }

    public ArrayList<String> getTunelsNames() { return tunelsNames; }

    public boolean isOpen() { return isOpen; }
    public void open(){ isOpen = true; }

    public ArrayList<Valve> getTunels() { return tunels; }

    public String getName() { return name; }

    public int getFlowRate() { return flowRate; }

    public ArrayList<String> cloneNames(){
        return new ArrayList<>(tunelsNames);

    }

    public boolean isChildren(String str){
        return tunelsNames.contains( str );
    }

    public int rute( Valve v ){
        if( isChildren( v.getName()) ) return 1;
        ArrayList<Valve> tunelsV = new ArrayList<>();
        tunelsV.add( v );
        int dist = 1;
        while( true ){
            extens( tunelsV );
            if( tunelsV.stream().anyMatch(t -> Objects.equals(t.getName(), getName())) ) break;
            dist++;
        }


        return dist;
    }

    private void extens(ArrayList<Valve> tunelsThis) {
        ArrayList<Valve> aux2 = new ArrayList<>();
        for( int i = 0; i < tunelsThis.size();i++) {
            tunelsThis.get(i).getTunels().forEach( t->{
                if(!tunelsThis.contains(t)) aux2.add(t);
            });
        }
        tunelsThis.addAll(aux2);
    }


    @Override
    public String toString() {
        String str =name +" flow rate = " + flowRate + "; tunnels lead to valves ";
        for (int i = 0; i < tunelsNames.size()-1; i++){
            str += tunelsNames.get(i) + ", ";
        }
        str += tunelsNames.get(tunelsNames.size()-1);
        return str;

    }
}
