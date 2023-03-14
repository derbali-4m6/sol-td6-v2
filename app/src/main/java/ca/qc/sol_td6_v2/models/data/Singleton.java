package ca.qc.sol_td6_v2.models.data;

import android.content.Context;

public class Singleton {
    private static Singleton singleInstance;
    public MyDbHelper helper;
    private Singleton(Context context){
        helper = new MyDbHelper(context);
    }

    public static Singleton getSingleInstance(Context context){
        if (singleInstance == null){
            singleInstance = new Singleton(context);
        }
        return singleInstance;
    }

}

