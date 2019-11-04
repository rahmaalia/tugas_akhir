package com.rahma.antriyuk.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EPolianak {
    @SerializedName("nama_dokter")
    @Expose
    private String namaDokter;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dokters_id")
    @Expose
    private Integer doktersId;
    @SerializedName("nama_poli")
    @Expose
    private String namaPoli;
    @SerializedName("jam_buka")
    @Expose
    private String jamBuka;
    @SerializedName("jam_tutup")
    @Expose
    private String jamTutup;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoktersId() {
        return doktersId;
    }

    public void setDoktersId(Integer doktersId) {
        this.doktersId = doktersId;
    }

    public String getNamaPoli() {
        return namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }

    public String getJamBuka() {
        return jamBuka;
    }

    public void setJamBuka(String jamBuka) {
        this.jamBuka = jamBuka;
    }

    public String getJamTutup() {
        return jamTutup;
    }

    public void setJamTutup(String jamTutup) {
        this.jamTutup = jamTutup;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }
}
