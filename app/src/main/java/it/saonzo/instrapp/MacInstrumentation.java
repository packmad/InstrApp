package it.saonzo.instrapp;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;

import javax.crypto.Mac;
import javax.crypto.ShortBufferException;

import it.saonzo.annotations.ClassWithInstrMethods;
import it.saonzo.annotations.InstrumentedMethod;

@ClassWithInstrMethods
public class MacInstrumentation {  // javax.crypto.Mac


    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = true)
    public static Mac getInstance(String algo) throws NoSuchAlgorithmException {
        HelperClass.LogMeth(algo);
        try {
            return Mac.getInstance(algo);
        } catch (NoSuchAlgorithmException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = true)
    public static Mac getInstance(String algo, Provider provider) throws NoSuchAlgorithmException {
        HelperClass.LogMeth(algo + ", r" + provider);
            try {
                return Mac.getInstance(algo, provider);
            } catch (NoSuchAlgorithmException e) {
                HelperClass.LogException(e.toString());
                throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = true)
    public static Mac getInstance(String algo, String provider) throws NoSuchProviderException, NoSuchAlgorithmException {
        HelperClass.LogMeth(algo + ", r" + provider);
        try {
            return Mac.getInstance(algo, provider);
        } catch (NoSuchAlgorithmException|NoSuchProviderException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    /*
    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = true)
    public static void init(Mac m, Key key ){
        Log.d("INST", "javax.crypto.Mac.init" + ":" + m.toString() + ", "+ key);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Mac.init" + ":" + m.toString() + ", "+ key);
            try {
                m.init(key);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init(Mac m, Key key, AlgorithmParameterSpec params ){
        Log.d("INST", "javax.crypto.Mac.init" + ":" + m.toString()+ ", "+key + ", "+ params);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Mac.init" + ":" + m.toString()+ ", "+key + ", "+ params);
            try {
                m.init(key, params);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static byte [] doFinal(Mac m){
        HelperClass.LogMeth(m.getAlgorithm() + ", " + m.toString());
        return m.doFinal();
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static byte [] doFinal(Mac m, byte[] input){
        String s = new String(input);
        HelperClass.LogMeth(m.getAlgorithm() + ", " + m.toString() + ", " + s);
        return m.doFinal(input);
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static void doFinal(Mac m, byte[] output, int outOffset) throws ShortBufferException {
        String s = new String(output);
        HelperClass.LogMeth(m.getAlgorithm() + ", " + m.toString() + ", " + s + ", " + outOffset);
        try {
            m.doFinal(output, outOffset);
        } catch (ShortBufferException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static void update(Mac m, byte input){
        HelperClass.LogMeth(Byte.toString(input));
        m.update(input);
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static void update(Mac m, byte[] input){
        String s = new String(input);
        HelperClass.LogMeth(new String(input));
        m.update(input);
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static void update(Mac m, byte[] input, int offset, int len){
        String s = new String(input);
        HelperClass.LogMeth(new String(input) + ", " + offset + ", " + len);
        m.update(input, offset, len);
    }

    @InstrumentedMethod(defClass = "javax.crypto.Mac", isStatic = false)
    public static void update(Mac m, ByteBuffer input){
        HelperClass.LogMeth(input.toString());
        m.update(input);
    }

}
