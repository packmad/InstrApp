package it.saonzo.instrapp;

import android.os.Environment;
import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import it.saonzo.annotations.ClassWithInstrMethods;
import it.saonzo.annotations.InstrumentedMethod;

@ClassWithInstrMethods
public class CipherInstrumentation { // javax.crypto.Cipher

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = true)
    public static Cipher getInstance (String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException {
        HelperClass.LogMeth(transformation);
        try {
            Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
        return null;
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = true)
    public static Cipher getInstance(String transformation, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException {
        HelperClass.LogMeth(transformation + ", " + provider);
        try {
            Cipher.getInstance(transformation, provider);
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
        return null;
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = true)
    public static Cipher getInstance(String transformation, String provider ) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        HelperClass.LogMeth(transformation + ", " + provider);
        try {
            Cipher.getInstance(transformation, provider);
        } catch (NoSuchAlgorithmException|NoSuchPaddingException|NoSuchProviderException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
        return null;
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static AlgorithmParameters getParameters(Cipher c){
        HelperClass.LogMeth(c.toString());
        return c.getParameters();
    }

    /*
    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c,int opmode, java.security.cert.Certificate certificate ){
        Log.d("INST", "javax.crypto.Cipher.init" + ":" + c.toString() + ", "+ opmode+ ", "+ certificate);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.init" + ":" + c.toString()  + ", "+ opmode+ ", "+ certificate);
            try {
                c.init(opmode, certificate);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c,int opmode,  java.security.cert.Certificate certificate, SecureRandom random ){
        Log.d("INST", "javax.crypto.Cipher.init" + ":" + c.toString() + ", " + opmode + ", " + certificate + ", " + random);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.init" + ":" + c.toString()  + ", "+ opmode+ ", "+ certificate+", "+ random);
            try {
                c.init(opmode,certificate,random);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c,int opmode, Key key ){
        Log.d("INST", "javax.crypto.Cipher.init" + ":" + c.toString() + ", "+ opmode+ ", "+ key);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.init" + ":" + c.toString()  + ", "+ opmode+ ", "+ key);
            try {
                c.init(opmode,key);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c, int opmode,Key key, AlgorithmParameters params ){
        Log.d("INST", "javax.crypto.Cipher.init" + ":" + c.toString() + ", "+ opmode+ ", "key + ", "+ params);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.init" + ":" + c.toString() + ", "+ opmode+ ", " +key+ ", "+ params);
            try {
                c.init(opmode,key,params);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c, int opmode,Key key, AlgorithmParameterSpec params ){
        Log.d("INST", "javax.crypto.Cipher.init" + ":" + c.toString() + ", "+ opmode+ ", "key + ", "+ params);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.init" + ":" + c.toString() + ", "+ opmode+ ", " +key+ ", "+ params);
            try {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                c.init(opmode,key,params);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c, int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random ){

    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c, int opmode, Key key, AlgorithmParameters params, SecureRandom random ){

    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void init(Cipher c, int opmode, Key key, SecureRandom random ){

    }
    */

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static byte [] doFinal(Cipher c) throws BadPaddingException, IllegalBlockSizeException {
        HelperClass.LogMeth(c.toString());
        try {
            return c.doFinal();
        } catch (IllegalBlockSizeException|BadPaddingException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static byte [] doFinal(Cipher c, byte[] input) throws BadPaddingException, IllegalBlockSizeException {
        String s = new String(input);
        HelperClass.LogMeth(c.toString() + ", " + s);
        try {
            return c.doFinal(input);
        } catch (IllegalBlockSizeException|BadPaddingException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static int doFinal(Cipher c, byte[] output, int outOffset) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        String s = new String(output);
        HelperClass.LogMeth(c.toString() + ", " + s + ", " + outOffset);
        try {
            return c.doFinal(output, outOffset);
        } catch (IllegalBlockSizeException|BadPaddingException|ShortBufferException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static byte [] doFinal(Cipher c, byte[] input, int inputOffset, int inputLen) throws BadPaddingException, IllegalBlockSizeException {
        String s = new String(input);
        HelperClass.LogMeth(c.toString() + ", " + s + ", " + inputOffset + ", " + inputLen);
        try {
            return c.doFinal(input, inputOffset, inputLen);
        } catch (IllegalBlockSizeException|BadPaddingException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static int doFinal(Cipher c, byte[] input, int inputOffset, int inputLen, byte[] output) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        String s = new String(input);
        HelperClass.LogMeth(c.toString() + ", " + s + ", " + input + ", " + inputOffset+ ", " +inputLen);
        try {
            return c.doFinal(input, inputOffset, inputLen, output);
        } catch (IllegalBlockSizeException|BadPaddingException|ShortBufferException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static int doFinal(Cipher c, byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        String s = new String(input);
        HelperClass.LogMeth(c.toString() + ", " + s + ", " + input + ", " + inputOffset+ ", " +inputLen+ ", " + output + ", " + outputOffset);
        try {
            return c.doFinal(input, inputOffset, inputLen, output, outputOffset);
        } catch (IllegalBlockSizeException|BadPaddingException|ShortBufferException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = false)
    public static int doFinal(Cipher c, ByteBuffer input, ByteBuffer output) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        HelperClass.LogMeth(c.toString() + ", " + input.toString() + ", " + output.toString() );
        try {
            return c.doFinal(input, output);
        } catch (IllegalBlockSizeException|BadPaddingException|ShortBufferException e) {
            HelperClass.LogException(e.toString());
            throw e;
        }
    }





    /*
    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void update(Cipher m, byte input){
        Log.d("INST", "javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input);
            try {
                m.update(input);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void update(Cipher m, byte[] input){
        Log.d("INST", "javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input);
            try {
                m.update(input);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InstrumentedMethod(defClass = "javax.crypto.Cipher", isStatic = )
    public static void update(Cipher m, byte[] input, int offset, int len){
        Log.d("INST", "javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input+ ", "+ offset+ ", "+len);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input+ ", "+ offset+ ", "+len);
            try {
                m.update(input, offset, len);
            } catch(Exception e) {
                fw.write("Exception: " + e.toString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(Cipher m, ByteBuffer input){
        Log.d("INST", "javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input);
        FileWriter fw;
        try {
            fw = new FileWriter(Environment.getExternalStorageDirectory()+"instr.txt", true);
            fw.write("javax.crypto.Cipher.update" + ":" + m.toString()+ ", "+ input);
            try {
                m.update(input);
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

}
