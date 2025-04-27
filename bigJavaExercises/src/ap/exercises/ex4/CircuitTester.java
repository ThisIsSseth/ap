package ap.exercises.ex4;

import java.util.ArrayList;
import java.util.List;

public class CircuitTester {
    private HallwayLamp hallwayLamp;
//    final int numberOfSwitches;
//    int[][] switches;


    public CircuitTester(HallwayLamp hallwayLamp) {
        this.hallwayLamp = hallwayLamp;
//        this.numberOfSwitches = 2;
//        switches = new int[(int)Math.pow(2,numberOfSwitches)][numberOfSwitches];
    }

    public void test(){
        for(int i = 2; i > 0 ; i -- ){
            for (int j = 2; j > 0 ; j --){
                System.out.println("First Switch:\t" + hallwayLamp.getFirstSwitchState());
                System.out.println("Second Switch:\t" + hallwayLamp.getSecondSwitchState());
                System.out.println("Lamp State:\t" + hallwayLamp.getLampState());
                System.out.println("Expected: " + (hallwayLamp.getFirstSwitchState() + hallwayLamp.getSecondSwitchState() == 1? 1 : 0));
                hallwayLamp.toggleFirstSwitch();
            }
            hallwayLamp.toggleSecondSwitch();
        }
    }
}


/*
* HallwayLamp hallwayLamp = new HallwayLamp();
    ArrayList<Integer> switchList = new ArrayList<>();
    int[][] allSwitches = new int[][];
    int numberOfSwitches = 0;

    public CircuitTester() {
        switchList.add(hallwayLamp.getFirstSwitchState());
        switchList.add(hallwayLamp.getSecondSwitchState());
        numberOfSwitches = switchList.size();
    }

    public void test() {
        for (int i = 1; i < numberOfSwitches; i++) {

        }
    }
* */