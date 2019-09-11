package team.ict.ddnstask.ddnsService;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LocalPublicIpv4 {
    Pattern pattern = Pattern.compile("\\\"cip\\\": \\\"(.*?)\\\"");
    public String getPublicIp(){
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        String buf = null;
        String str = null;
        String ipV4 = "";
        try {
            //调用外部网站的公网IP检测
            url = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            urlconn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
            while((buf = br.readLine()) != null){
                str += buf;
            }
            Matcher matcher = pattern.matcher(str);
            if(matcher.find()){{
                ipV4 = matcher.group(1);
            }}

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipV4;
    }
}
