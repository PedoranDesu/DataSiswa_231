package com.pedoran.datasiswa_231.database;

import android.os.Parcel;
import android.os.Parcelable;

public class Siswa implements Parcelable {
    private String nis;
    private String nama;
    private String tanggalLahir;
    private String jenisKelamin;
    private String alamat;

    public Siswa() {
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nis);
        dest.writeString(this.nama);
        dest.writeString(this.tanggalLahir);
        dest.writeString(this.jenisKelamin);
        dest.writeString(this.alamat);
    }

    protected Siswa(Parcel in) {
        this.nis = in.readString();
        this.nama = in.readString();
        this.tanggalLahir = in.readString();
        this.jenisKelamin = in.readString();
        this.alamat = in.readString();
    }

    public static final Parcelable.Creator<Siswa> CREATOR = new Parcelable.Creator<Siswa>() {
        @Override
        public Siswa createFromParcel(Parcel source) {
            return new Siswa(source);
        }

        @Override
        public Siswa[] newArray(int size) {
            return new Siswa[size];
        }
    };
}
