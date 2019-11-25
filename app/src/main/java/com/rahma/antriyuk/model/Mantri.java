package com.rahma.antriyuk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrigigi;
import com.rahma.antriyuk.Entity.MAntrimata;
import com.rahma.antriyuk.Entity.MAntriumum;

import java.util.List;

public class Mantri {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("antri_anak")
    @Expose
    private List<MAntrianak> antriAnak = null;
    @SerializedName("antri_gigi")
    @Expose
    private List<MAntrigigi> antriGigi = null;
    @SerializedName("antri_umum")
    @Expose
    private List<MAntriumum> antriUmum = null;
    @SerializedName("antri_mata")
    @Expose
    private List<MAntrimata> antriMata = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<MAntrianak> getAntriAnak() {
        return antriAnak;
    }

    public void setAntriAnak(List<MAntrianak> antriAnak) {
        this.antriAnak = antriAnak;
    }

    public List<MAntrigigi> getAntriGigi() {
        return antriGigi;
    }

    public void setAntriGigi(List<MAntrigigi> antriGigi) {
        this.antriGigi = antriGigi;
    }

    public List<MAntriumum> getAntriUmum() {
        return antriUmum;
    }

    public void setAntriUmum(List<MAntriumum> antriUmum) {
        this.antriUmum = antriUmum;
    }

    public List<MAntrimata> getAntriMata() {
        return antriMata;
    }

    public void setAntriMata(List<MAntrimata> antriMata) {
        this.antriMata = antriMata;
    }
}
