package it.saonzo.instrapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import it.saonzo.annotations.ClassWithInstrMethods;
import it.saonzo.annotations.InstrumentedMethod;

@ClassWithInstrMethods
public class URLInstrumentation {

    @InstrumentedMethod(defClass = "java.net.URL", isStatic = false)
    public static URLConnection openConnection(URL url) {
        HelperClass.LogMeth(url.toString());
        try {
            return new URL(url.toString()).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @InstrumentedMethod(defClass = "java.net.URL", isStatic = false)
    public static URLConnection openConnection(URL url, Proxy p) {
        HelperClass.LogMeth(url.toString() + ", " + p.toString());
        try {
            return new URL(url.toString()).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @InstrumentedMethod(defClass = "java.io.BufferedReader", isStatic = false)
    public static String readLine(BufferedReader br) throws IOException {
        try {
            final String res = br.readLine();
            HelperClass.LogMeth(res);
            return res;
        } catch (IOException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }

    }
}

