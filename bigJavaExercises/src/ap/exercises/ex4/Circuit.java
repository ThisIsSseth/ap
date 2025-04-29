package ap.exercises.ex4;


class Circuit {
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

    public int getSwitchState(int switchNum) {
        if (switchNum == 1)
            return getFirstSwitchState();
        else if (switchNum == 2)
            return getSecondSwitchState();
        else return -1;
    }

    public void toggleSwitchState(int switchNum) {
        if (switchNum == 1) {
            toggleFirstSwitch();
        } else if (switchNum == 2) {
            toggleSecondSwitch();
        }
    }
}
