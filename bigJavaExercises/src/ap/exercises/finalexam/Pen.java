package ap.exercises.finalexam;

public class Pen extends Product {
    Colours colour;

    public Pen(Integer price, String name, Colours colour) {
        super(price, name);
        this.colour = colour;
    }

    public Colours getColour() {
        return colour;
    }

    public void setColour(Colours colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return super.toString() + " | colour: " + colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pen pen = (Pen) o;
        return colour == pen.colour;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), colour);
    }

}
