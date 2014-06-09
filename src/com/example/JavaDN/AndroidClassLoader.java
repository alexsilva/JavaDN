package com.example.JavaDN;

import android.content.Context;
import android.util.Log;
import dalvik.system.DexClassLoader;

import java.io.File;
import java.io.FileOutputStream;

class AndroidClassLoader {
    /*
    * http://stackoverflow.com/questions/11453614/how-can-i-load-a-jar-file-dynamically-in-an-android-application-4-0-3#
    * http://larshamren.blogspot.com.br/2012/02/android-dynamically-loading-classes.html

    * Create your own manisfest for the jar and put this information
    * Manifest-Version: 1.0
    * Module-Class: com.example.myjar.MyPeripheral
    *
    * Export your jar using eclipse and put in parameter that it uses its own manisfest
    *
    * Create the classes.dex associated to the jar (this file is needed by the Dalvik VM, simply jar can not be read by the dalvik VM)
    *
    * dx --dex --output=C:\classes.dex C:\MyJar.jar
    * Be carefull, the name of the dex file MUST BE classes.dex
    *
    * Add the file classes.dex in the jar file
    *
    * aapt add C:\MyJar.jar C:\classes.dex
    */

    public static final String TAG = AndroidClassLoader.class.getSimpleName();

    Context ctx;
    String mResUrl;

    public AndroidClassLoader() {
        super(); // system
    }

    public AndroidClassLoader(Context context) {
        this.ctx = context;
    }

    public String getLibPath() {
        return ctx.getCacheDir().getAbsolutePath() + "/lib";
    }

    public File getLibFile() {
        File baseFile = new File(getLibPath());
        baseFile.mkdirs();
        return new File(baseFile, "objects.jar");
    }

    public void add(String resUrl) {
        mResUrl = resUrl;
    }

    public void load() {
        try {
            File jarFile = getLibFile();

            Log.e(TAG, "Created: " + jarFile.createNewFile());

            HttpRequest.dump(mResUrl, new FileOutputStream(jarFile));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());

        }
    }

    public Class<?> loadClass(String className) {
        String dexDir = ctx.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath();
        DexClassLoader loader = new DexClassLoader(
                getLibFile().getAbsolutePath(), dexDir, null, ctx.getClassLoader());
        Class<?> klass = null;
        try {
            klass = loader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return klass;
    }
}