package it.saonzo.instrapp;


import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.saonzo.annotations.ClassWithInstrMethods;

@ClassWithInstrMethods
public class HelperClass {
    public static final String TAG = "###-INSTR-###";

    public static void LogMeth(String msg) {
        String methName = Thread.currentThread().getStackTrace()[3].getMethodName();
        String log = "{'" + methName +"','" + msg.toString() + "}";
        FileWriter fw;
        try {
            File saveFile = new File(Environment.getExternalStorageDirectory(), "instr.txt");
            fw = new FileWriter(saveFile, true);
            fw.write(log);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        Log.d(TAG, log);
    }

    public static void LogException(String msg) {
        LogMeth("@Exception: " + msg);
    }
}
