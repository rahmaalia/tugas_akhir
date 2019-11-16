package com.rahma.antriyuk.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_ANTRI="spAntri";
    public static final String SP_NAMA ="spNama";
    public static final String SP_USERNAME="spUsername";
    public static final String SP_TELP="spTelp";

//    //polianak
//    public static final String SP_NPOLI="spNpoli";
//    public static final String SP_NDOKTER="spNdokter";
//    public static final String SP_JBUKA="spJbuka";
//    public static final String SP_JTUTUP="spJtutup";
//    public static final String SP_IDPOLI = "spIdpoli";
//    //poligigi
//    public static final String SP_NPOLI_G="spNpoli_g";
//    public static final String SP_NDOKTER_G="spNdokter_g";
//    public static final String SP_JBUKA_G="spJbuka_g";
//    public static final String SP_JTUTUP_G="spJtutup_g";
//    public static final String SP_IDPOLI_G = "spIdpoli_g";
//    //poliumum
//    public static final String SP_NPOLI_U="spNpoli_u";
//    public static final String SP_NDOKTER_U="spNdokter_u";
//    public static final String SP_JBUKA_U="spJbuka_u";
//    public static final String SP_JTUTUP_U="spJtutup_u";
//    public static final String SP_IDPOLI_U = "spIdpoli_u";
//    //polimata
//    public static final String SP_NPOLI_M="spNpoli_m";
//    public static final String SP_NDOKTER_M="spNdokter_m";
//    public static final String SP_JBUKA_M="spJbuka_m";
//    public static final String SP_JTUTUP_M="spJtutup_m";
//    public static final String SP_IDPOLI_M = "spIdpoli_m";


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



}
