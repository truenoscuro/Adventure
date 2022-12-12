package Objects;

import java.util.ArrayList;

public class Monkey  {

    int test;
    char operator;
    int numOperator;
    int insp = 0;
    int name;
    int use = 1;
    Zn zn;

    ArrayList<Monkey> monkeys;
    ArrayList<Integer> numMonkeys;
    ArrayList<Integer> list ;
    public Monkey(){
        list = new ArrayList<>();
        monkeys = new ArrayList<>();
        numMonkeys = new ArrayList<>();
    }
    public Monkey( int i ){
        name = i;
        list = new ArrayList<>();
        monkeys = new ArrayList<>();
        numMonkeys = new ArrayList<>();
    }
    public void setZn(Zn zn){
        this.zn = zn;
        use = zn.getZn();
    }

    public ArrayList<Integer> getNumMonkeys() {
        return numMonkeys;
    }


    public void setTest( int test ) {
        this.test = test;
    }
    private boolean isNum(char a){ return '0'<=a && a <= '9'; }
    public  void setOperation( String operation ){
        int leng = operation.length()-1;
        String numString = "";
        while (isNum(operation.charAt(leng))) numString = operation.charAt(leng--) + numString;
        if( numString.equals("") ) {
            numString = "-1";
            leng -= 3;
        }
        this.numOperator = Integer.parseInt(numString);
        this.operator = operation.charAt(--leng);
    }


    public void setMonkeys( Monkey monkey ) {
        monkeys.add( monkey );
    }
    public void setNumMonkeys(int num){
        numMonkeys.add( num );
    }
    public void add( int item ){ list.add( item ); }
    public int getInsp() { return insp; }

    private int action( int item  ){
        int aux = numOperator ;
        if ( numOperator < 0 ) aux = item;
        if( operator == '*'){
            int sum = 0;
            for(int i = 0; i < aux; i++){
                sum += item;
                sum %= use;
            }
            item = sum;
        }
        else item += aux;

        return item  ;
    }



    public void turn()  {
        //System.out.println("-------------------------");
        while ( !list.isEmpty() ){

            insp++;

            int item = list.get( 0 );
            list.remove(0 );


            item = action( item ) ;
            item %= use;
            int indx = 0;
            if (  zn.get( item , test ) > 0 ) indx = 1;

            monkeys.get( indx ).add( item  );
        }
    }









    @Override
    public String toString() {
        return "monkey  "+name + " --> " + insp +" -- "+
                list.toString() + " -- " +numMonkeys.toString();
    }

    public int compara( Monkey o2) {
        int m1 = getInsp();
        int m2 = o2.getInsp();
        int result = 1;
        if( m1 == m2) result = 0;
        if( m1 > m2) result = -1;
        return result;
    }
}
