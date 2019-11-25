package com.rahma.antriyuk.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MAntrigigi {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("data_pasien_gigis_id")
    @Expose
    private Integer dataPasienGigisId;
    @SerializedName("polis_id")
    @Expose
    private Integer polisId;
    @SerializedName("no_antrian")
    @Expose
    private String noAntrian;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("nama_poli")
    @Expose
    private String namaPoli;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataPasienGigisId() {
        return dataPasienGigisId;
    }

    public void setDataPasienGigisId(Integer dataPasienGigisId) {
        this.dataPasienGigisId = dataPasienGigisId;
    }

    public Integer getPolisId() {
        return polisId;
    }

    public void setPolisId(Integer polisId) {
        this.polisId = polisId;
    }

    public String getNoAntrian() {
        return noAntrian;
    }

    public void setNoAntrian(String noAntrian) {
        this.noAntrian = noAntrian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNamaPoli() {
        return namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }
}
