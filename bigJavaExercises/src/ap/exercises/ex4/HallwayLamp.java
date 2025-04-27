package ap.exercises.ex4;

/**
 * Test all possible outcomes of HallwayLamp class
 */
class HallwayLamp {
    boolean switch1 = false;
    boolean switch2 = false;
    boolean lamp = false;


    public int getFirstSwitchState() {
        return switch1 ? 1 : 0;
    }

    public int getSecondSwitchState() {
        return switch2 ? 1 : 0;
    }

    public int getLampState() {
        return lamp ? 1 : 0;
    }

    public void toggleFirstSwitch() {
        switch1 = !switch1;
        lamp = !lamp;
    }

    public void toggleSecondSwitch() {
        switch2 = !switch2;
        lamp = !lamp;
    }
}
