package it.saonzo.instrapp;


import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.ClientParamsStack;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.Header;
import org.apache.http.protocol.HttpContext;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.saonzo.annotations.ClassWithInstrMethods;

@ClassWithInstrMethods
public class HelperClass {
    public static final String TAG = "###-INSTR-###";
    private static int var = 0;
    public static HashMap<Integer, String> env = new HashMap<Integer,String>();

    private static String freshVar() {
        return "t_" + var++;
    }

    public static void CreateVariable(int hash, String type, String constructor) {
        String var = freshVar();
        Log(type + " " + var + " = " + constructor + ";\n");
        env.put(new Integer(hash), var);
    }

    public static String simpleDecl(String type) {
        return type + " " + freshVar();
    }

    public static void LogMeth(String msg) {
        String methName = Thread.currentThread().getStackTrace()[3].getMethodName();
        String log = "'" + methName +"'{'" + msg.toString() + "'}\n";
        plog(log);
    }

    public static void Log(String codeline) {
        plog("\t" + codeline + "\n");
    }

    public static void LogException(String msg) {
        LogMeth("@Exception: " + msg);
    }


    public static String get(Object o) {
        return env.get(new Integer(o.hashCode()));
    }

    public static boolean containsKey(Object o) {
        return env.containsKey(o.hashCode());
    }

    public static void LogHttpClient(HttpClient client) {

        String log = client.toString() + "\n";

        CookieStore cs;
        if(client instanceof DefaultHttpClient)
            cs = ((DefaultHttpClient)client).getCookieStore();
        else
            cs = null;

        if(cs != null) {
            for(Cookie c : cs.getCookies()) {
                log += "COOKIE > " + c.getName() + " = " + c.getValue() + "\n";
            }
        }

        plog(log);
    }

    public static void LogHttpUriRequest(HttpUriRequest req) {

        String log = req.toString() +":\n" +
                "> " + req.getMethod() + " " + req.getURI().toString() + "\n";

        for(Header h : req.getAllHeaders()) {
            log += "HEADER > " + h.getName() + " = " + h.getValue() + "\n";
        }

        if(req instanceof  HttpPost) {

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ((HttpPost) req).getEntity().writeTo(baos);
                baos.flush();
                String body = new String(baos.toByteArray());
                log += "BODY > " + body + "\n";
            }
            catch (Exception e) {}
        }

        plog(log);
    }

    private static void plog(String log) {
        FileWriter fw;

        try {
            File saveFile = new File(Environment.getExternalStorageDirectory(), "instrlog.txt");
            if(!saveFile.exists())
                saveFile.createNewFile();
            fw = new FileWriter(saveFile, true);

            fw.write(log);

            fw.flush();
            fw.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        Log.d(TAG, log);
    }

    public static void LogCredentials(Credentials c) {
        String log = "USERNAME > " + c.getUserPrincipal().toString() + "\n";
        log += "PASSWORD > " + c.getPassword() + "\n";

        plog(log);

    }

    public static void LogHttpRespose(HttpResponse res) {
        String log = res.toString() +":\n" +
                "> " + res.getStatusLine().getStatusCode() + " " + res.getStatusLine().getReasonPhrase() + "\n";

        for(Header h : res.getAllHeaders()) {
            log += "HEADER > " + h.getName() + " = " + h.getValue() +"\n";
        }

        try {
            log += "CONTENT  > " + res.getEntity().getContentType().getName();
            log += " = " + res.getEntity().getContentType().getValue();
            log += "; " + res.getEntity().getContentEncoding().getName();
            log += " = " + res.getEntity().getContentEncoding().getValue() + "\n";
        }
        catch (NullPointerException e) { log += "\n"; }
        plog(log);
    }

/*    public static String map2string(Map<String,List<String>> m) {
        String c = "";
        for(String k : m.keySet()) {
            c += k + " = ";
            for(String t : m.get(k)) {
                c += t + " ; ";
            }
            c += "\n";
        }
        return c;
    }*/

    public static String map2string(Map<String, ?> m) {
        String c = "";
        for(String k : m.keySet()) {
            c += k + " = " + m.get(k).toString() + "\n";
        }
        return c;
    }

}
