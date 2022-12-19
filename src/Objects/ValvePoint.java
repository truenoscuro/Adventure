package Objects;

import java.util.ArrayList;

public class ValvePoint {

    private Valve valve;
    private int time;

    private int total;
    private int heuristic;

    private ArrayList<String> valves;
    private ArrayList<String> valvesElefant;

    public ValvePoint(Valve valve, int time, int total,int heuristic, ArrayList<String> valves) {
        this.valve = valve;
        this.time = time;
        this.total = total;
        this.heuristic = heuristic;
        this.valves = new ArrayList<>(valves);
    }
    public ValvePoint(Valve valve, int time, int total,int heuristic, ArrayList<String> valves, ArrayList<String> valvesElefant) {
        this.valve = valve;
        this.time = time;
        this.total = total;
        this.heuristic = heuristic;
        this.valves = new ArrayList<>(valves);
        this.valvesElefant = new ArrayList<>(valvesElefant);
    }

    public int getHeuristic() {
        return heuristic;
    }

    public ArrayList<String> getValvesElefant() {
        return valvesElefant;
    }

    public int comp(ValvePoint valvePoint){
        int val = valvePoint.getHeuristic();
        int result = 1;
        if(val == heuristic) result  = 0;
        if(heuristic > val) result = -1;
        return result;
    }


    public Valve getValve() {
        return valve;
    }

    public int getTime() {
        return time;
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<String> getValves() {
        return valves;
    }
}
