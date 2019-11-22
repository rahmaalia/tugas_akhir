package com.rahma.antriyuk.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EHistory {
    @SerializedName("nama_poli")
    @Expose
    private String namaPoli;
    @SerializedName("no_antrian")
    @Expose
    private String noAntrian;

    public String getNamaPoli() {
        return namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

}
