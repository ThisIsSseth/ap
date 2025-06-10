package ap.exercises.ex4;


/**
 * Test all possible outcomes of Circuit class
 */
class CircuitTester {

    private Circuit circuit;
//    final int numberOfSwitches;
//    int[][] switches;


    public CircuitTester(Circuit circuit) {
        this.circuit = circuit;
//        this.numberOfSwitches = 2;
//        switches = new int[(int)Math.pow(2,numberOfSwitches)][numberOfSwitches];
    }

    public void test(){
        for(int i = 2; i > 0 ; i -- ){
            for (int j = 2; j > 0 ; j --){
                System.out.println("First Switch:\t" + circuit.getFirstSwitchState());
                System.out.println("Second Switch:\t" + circuit.getSecondSwitchState());
                System.out.println("Lamp State:\t" + circuit.getLampState());
                System.out.println("Expected: " + (circuit.getFirstSwitchState() + circuit.getSecondSwitchState() == 1? 1 : 0));
                circuit.toggleFirstSwitch();
            }
            circuit.toggleSecondSwitch();
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