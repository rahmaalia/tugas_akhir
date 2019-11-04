package com.rahma.antriyuk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rahma.antriyuk.Entity.EPolianak;
import com.rahma.antriyuk.Entity.EPoligigi;
import com.rahma.antriyuk.Entity.EPolimata;
import com.rahma.antriyuk.Entity.EPoliumum;

public class MPoli {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Mdata data;
    @SerializedName("polianak")
    @Expose
    private EPolianak polianak;
    @SerializedName("poligigi")
    @Expose
    private EPoligigi poligigi;
    @SerializedName("poliumum")
    @Expose
    private EPoliumum poliumum;
    @SerializedName("polimata")
    @Expose
    private EPolimata polimata;
    @SerializedName("token")
    @Expose
    private String token;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Mdata getData() {
        return data;
    }

    public void setData(Mdata data) {
        this.data = data;
    }

    public EPolianak getPolianak() {
        return polianak;
    }

    public void setPolianak(EPolianak polianak) {
        this.polianak = polianak;
    }

    public EPoligigi getPoligigi() {
        return poligigi;
    }

    public void setPoligigi(EPoligigi poligigi) {
        this.poligigi = poligigi;
    }

    public EPoliumum getPoliumum() {
        return poliumum;
    }

    public void setPoliumum(EPoliumum poliumum) {
        this.poliumum = poliumum;
    }

    public EPolimata getPolimata() {
        return polimata;
    }

    public void setPolimata(EPolimata polimata) {
        this.polimata = polimata;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
