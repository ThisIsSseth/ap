package ap.exercises.ex4;

import java.util.Formatter;

public class Main_EX4_E3_15 {
    public static void main(String[] args) {
        Letter letter = new Letter("sender", "receiver");
        LetterPrinter letterPrinter = new LetterPrinter();
        letter.addLine("First line");
        letter.addLine("Second line");
        letter.addLine("Last line");
        letterPrinter.print(letter);
        System.out.println("""
                
                Expected:
                Dear receiver:
                
                First line
                Second line
                Last line
                
                Sincerely,
                
                sender""");
    }

}

class Letter{
    private String from;
    private String to;
    private String text ="";

    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void addLine(String line) {
        text += line + "\n";
    }

    public String getText() {
        return String.format("Dear %s:\n\n%s\n\nSincerely,\n\n%s", to, text, from);
    }
}

class LetterPrinter{

    public void print(Letter letter) {
        System.out.println(letter.getText());
    }
}