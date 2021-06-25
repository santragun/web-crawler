import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Crawler {

    public String domain = null;

    public URLConnection connect(String user_url) {

        URL URL = null;
        URLConnection urlConnection = null;

        //Checks if user url is less than 7
        if (user_url.length() < 7) {
            try {
                URL = new URL("https://" + user_url);
                domain = user_url;

            } catch (MalformedURLException e) {
                System.out.println("can't connect re enter url " + e);
            }
        }
        //Checks if user url have http or https
        else {

            if (user_url.substring(0, 7).equals("http://")) {   //checks if user url have http
                try {

                    URL = new URL(user_url);
                    domain = user_url.substring(7);
                } catch (MalformedURLException e) {
                    System.out.println("can't connect re enter url " + e);
                }
            } else if (user_url.substring(0, 8).equals("https://")) { //checks if user url have https
                try {

                    URL = new URL(user_url);
                    domain = user_url.substring(8);
                } catch (MalformedURLException e) {
                    System.out.println("can't connect re enter url " + e);
                }
            } else {  //add https if user url have no protocol
                try {
                    URL = new URL("https://" + user_url);
                    domain = user_url;
                } catch (MalformedURLException e) {
                    System.out.println("can't connect re enter url " + e);
                }
            }
        }

        //checks if the assigned domain includes www
        if(domain.substring(0,4).equals("www.")){
            domain=domain.substring(4);
        }


        try {
            urlConnection=URL.openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(URL);

        return urlConnection;

    }


    public void parser(URLConnection connection)
    {
        Scanner scanner=null;
        String content=null;

        try {
            scanner=new Scanner(connection.getInputStream());
            scanner.useDelimiter("//Z");
            content=scanner.next();

        } catch (IOException e) {
            System.out.println("connection failed "+e);
        }
        char cot='"';
        Pattern link_pattern=Pattern.compile("href="+cot);

        Matcher matcher=link_pattern.matcher(content);
        int internal_link_count=0;
        int external_link_count=0;
        int image_count=0;
        int video_count=0;
        while (matcher.find()==true)
        {
            int start=matcher.end();
            int end=content.length()-1;

            String from_current_location=content.substring(start,end);
            int index_of_quot=from_current_location.indexOf('"');
            if(content.substring(start,start+4).equals("http"))
            {
                int index_quot=start+index_of_quot;
                if(!(content.substring(index_quot-4,index_quot).equals(".css") ||
                        content.substring(index_quot-4,index_quot).equals(".ico")||
                        content.substring(index_quot-3,index_quot).equals(".js")))
                {
                    //Pattern domain_pattern=Pattern.compile(domain);
                    //Matcher domain__matcher=domain_pattern.matcher(content.substring(start,index_quot));

                    if (domain__matcher.find()==true)
                    {
                        internal_link_count++;
                        System.out.println(" Internal\t"+content.substring(start,index_quot)+"\n");
                    }
                    else
                    {
                        external_link_count++;
                        System.out.println(" External \t"+content.substring(start,index_quot)+"\n");
                    }



                }


            }


        }

        Pattern img_pattern=Pattern.compile("<img\\s");
        matcher=img_pattern.matcher(content);

        while (matcher.find()==true)
        {
            image_count++;
        }

        Pattern video_pattern=Pattern.compile("<video\\s");
        matcher=video_pattern.matcher(content);

        while (matcher.find()==true)
        {
            video_count++;
        }
        //System.out.println("domain "+domain);
        System.out.println(internal_link_count+" internal links found");
        System.out.println(external_link_count+" external links found");
        System.out.println(image_count+" images found");
        System.out.println(video_count+" videos found");

    }


}