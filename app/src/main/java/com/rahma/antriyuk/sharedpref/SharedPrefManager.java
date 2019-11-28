package com.rahma.antriyuk.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_ANTRI="spAntri";
    public static final String SP_NAMA ="spNama";
    public static final String SP_USERNAME="spUsername";
    public static final String SP_TELP="spTelp";

    public static final int SP_IDUSER = 1;
    public static final int SP_NOANTRI = 2;
    public static final int SP_ID_ANTRI = 3;
    public static final String SP_LOGIN="spLogin";



    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp=context.getSharedPreferences(SP_ANTRI,Context.MODE_PRIVATE);
        spEditor=sp.edit();
    }
    public void saveSPString(String keySp, String value){
        spEditor.putString(keySp, value);
        spEditor.commit();
    }
    public void saveSPint(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public String getSpNama(){
        return sp.getString(SP_NAMA, "");
    }
    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }
    public String getSpTelp(){
        return sp.getString(SP_TELP, "");
    }
    public Boolean getSpLogin(){
        return sp.getBoolean(SP_LOGIN, false);
    }
    public int getSpNoantri(){
        return sp.getInt(String.valueOf(SP_NOANTRI),1);
    }
    public int getSpIduser(){
        return sp.getInt(String.valueOf(SP_IDUSER),2);
    }
    public int getSpIdAntri() {
        return sp.getInt(String.valueOf(SP_ID_ANTRI),2);
    }


}
