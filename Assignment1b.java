import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment1b {

    public static void main(String[] args) throws IOException {
        String quarter;
        String year;
        String initial;

        Scanner useinput = new Scanner(System.in);

        System.out.println("Enter a quarter: ");
        quarter = useinput.nextLine();
        System.out.println("Enter a year: ");
        year = useinput.nextLine();
        System.out.println("Enter the initial for the program: ");
        initial = useinput.nextLine();

        URL url = new URL("https://www.bellevuecollege.edu/classes/" + quarter + year);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String input = "";
        String text = "";
        while ((input = in.readLine()) != null) {
            text += input + "\n";
        }
        //System.out.println(text);


        Pattern pattern = Pattern.compile("<h2 class=.*>" + initial + "</h2>\\s*<ul class=.*>\\s*((<li class=.*>\\s*<a href=.*>(.*)</a>\\s*(.*)\\s*</li>\\s*)+).*</ul>");
        Matcher matcher = pattern.matcher(text);
        String text2 = "";
        while (matcher.find()) {
            //System.out.print(matcher.group());
            text2= text2 + matcher.group();
        }
        //System.out.println(text2);

        Pattern patternsmall = Pattern.compile("<li class=.*>\\s*<a href=.*>(.*)</a>\\s*(.*)\\s*</li>\\s*");
        Matcher matchersmall = patternsmall.matcher(text2);

        System.out.println();
        System.out.println("Programs:");
        while (matchersmall.find()) {
            System.out.print(matchersmall.group(1) + " ");
            System.out.println(matchersmall.group(2));
        }

        // -------- Next Reading -------

        String program;
        String courseID;

        System.out.println("Enter the program's name: ");
        program = useinput.nextLine();
        System.out.println("Enter the course ID: ");
        courseID = useinput.nextLine();

        String[] split = courseID.split("\\s|&");

        URL urltwo = new URL("https://www.bellevuecollege.edu/classes/" + quarter + year + "/" + split[0]);
        System.out.println(urltwo);
        BufferedReader intwo = new BufferedReader(new InputStreamReader(urltwo.openStream()));

        String inputtwo = "";
        String text3 = "";
        while ((inputtwo = intwo.readLine()) != null) {
            text3 += inputtwo + "\n";
        }
        //System.out.println(text3);

        Pattern pattern3 = Pattern.compile("<span class=\"courseID\">" + courseID + "</span>([\\s\\S]*)(<span class=\"courseID\">){1}");
        Matcher matcher3 = pattern3.matcher(text3);
        //Try as I might, this code grabs way too many lines. It appears to grab the very last instance of what comes after
        //the ([\\s\\S]*) (which I needed to grab new lines) rather than the first one, which I need.
        //My thought is to end it with something at the end of the code that includes part or all of the courseID
        //(since I've got it split up in that array), but I didn't spot anything in the code like that.

        System.out.println();
        System.out.println(program + " Courses in " + quarter + " " + year);

        System.out.println();
        System.out.println("regrettably, this was as far as I could make it. Below is the code my regex expression currently grabs; \n" +
                "in my java code a few lines up from this one are some comments on my problem.");

        while (matcher3.find()) {
            System.out.println("Inside the find");
            System.out.println(matcher3.group(1));
        }



    }

}