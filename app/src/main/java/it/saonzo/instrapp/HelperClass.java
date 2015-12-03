package it.saonzo.instrapp;


import android.content.Context;
//import android.net.http.AndroidHttpClient;
import android.os.Environment;
import android.util.Log;

//import org.apache.http.HttpResponse;
//import org.apache.http.auth.Credentials;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.ClientParamsStack;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.Header;
//import org.apache.http.protocol.HttpContext;

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
    public static HashMap<Object, String> env = new HashMap<Object,String>();
    private static FileWriter fw = null;

    private static String freshVar() {
        return "t_" + var++;
    }

    public static void CreateVariable(Object o, String type, String constructor) {
        String var = freshVar();
        Log(type + " " + var + " = " + constructor);
        env.put(o, var);
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
        if(codeline.endsWith(";"))
            plog("\t" + codeline + "\n");
        else
            plog("\t" + codeline + ";\n");
    }

    public static void LogComment(String codeline) {
        plog("\t/* " + codeline + " */\n");
    }

    public static void LogException(String msg) {
        LogMeth("@Exception: " + msg);
    }


    public static String get(Object o) {
        if(env.containsKey(o))
            return env.get(o);
        else
            return "/* could not find in environment */ \"" + o.toString() + "\"";
    }

    public static boolean containsKey(Object o) {
        return env.containsKey(o.hashCode());
    }

    private static void plog(String log) {

        if(fw == null) {

            try {
                File saveFile = new File(Environment.getExternalStorageDirectory(), "instrlog.txt");
                if (!saveFile.exists())
                    saveFile.createNewFile();
                fw = new FileWriter(saveFile, true);
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }

        }

        if(fw != null)
            try {
                fw.write(log);
                fw.flush();
            } catch (IOException e) {
                Log.e(TAG, e.toString());
            }
    }

    public static String map2string(Map<String, ?> m) {
        String c = "";
        for(String k : m.keySet()) {
            c += k + " = " + m.get(k).toString() + "\n";
        }
        return c;
    }

}
