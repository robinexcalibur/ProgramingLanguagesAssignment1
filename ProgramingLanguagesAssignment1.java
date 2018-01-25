
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramingLanguagesAssignment1 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Ajava.txt");
        Scanner scanner = new Scanner(file);
        String input = "";
        String text = "";

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            text += input + "\n";
        }

        Pattern pattern = Pattern.compile("(.*\\s(\\w[^\\s{]*)\\s(\\w[^\\s={;]*)\\s*=\\s(.[^=\\s\"]*);)|(.*\\s(\\w[^\\s{]*)\\s(\\w[^\\s={;]*)\\s*=\\s*\"(.[^=]*)\";)|(.*\\s(\\w[^\\s{]*)\\s(\\w[^\\s={;]*)\\s*();)");
        Matcher matcher = pattern.matcher(text);
        int counter = 0;
        while (matcher.find()) {
            if(matcher.group(2) != null) {
                System.out.println("Type: " + matcher.group(2));
                System.out.println("Variable name: " + matcher.group(3));
                System.out.println("Value: " + matcher.group(4));
            } else if (matcher.group(6) != null) {
                System.out.println("Type: " + matcher.group(6));
                System.out.println("Variable name: " + matcher.group(7));
                System.out.println("Value: " + matcher.group(8));
            } else if (matcher.group(10) != null) {
                System.out.println("Type: " + matcher.group(10));
                System.out.println("Variable name: " + matcher.group(11));
                System.out.println("Value: " + matcher.group(12));
            }
            System.out.println("^^^^^^^^^^^^^^^^");
            ++counter;
        }
        System.out.println("Number of groups should be: 12, is: " + counter);
    }
}