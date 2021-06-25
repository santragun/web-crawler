import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {

        Scanner input=new Scanner(System.in);
        String user_url;

        System.out.println("Enter the url");
        user_url=input.nextLine();

        Crawler crawler=new Crawler();

        URLConnection connection=crawler.connect(user_url);


        //crawler.parser(connection);




    }
}
