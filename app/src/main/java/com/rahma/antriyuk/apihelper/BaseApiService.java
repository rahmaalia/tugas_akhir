package com.rahma.antriyuk.apihelper;

//Class ini berfungsi untuk mengisi perintah-perintah apa saja yang diperlukan
// untuk berkomunikasi dengan API. Seperti GET,POST,UPDATE, DELETE.

import com.rahma.antriyuk.model.Mhistory;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password) ;

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("username") String username,
                                       @Field("no_telp") String no_telp,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("antri")
    Call<ResponseBody> postDataPasien(@Field("id_poli") int id,
                                      @Field("no_identitas") String no_identitas,
                                      @Field("nama") String nama,
                                      @Field("kota_lahir") String kota_lahir,
                                      @Field("tgl_lahir") String tgl_lahir,
                                      @Field("alamat") String alamat,
                                      @Field("jenis_kelamin") String jenis_kelamin,
                                      @Field("no_antrian") String no_antrian);

    @GET("antri/{id_poli}")
    Call<ResponseBody> getAntri(@Path("id_poli") int id_poli);

    @GET("poli")
    Call<ResponseBody> getPoli();

    @GET("gett")
    Call<Mhistory> getHistory ();

    @FormUrlEncoded
    @PUT("/user/{id}")
    Call<ResponseBody> update (@Field("nama") String nama,
                               @Field("no_telp") String no_telp);


}
