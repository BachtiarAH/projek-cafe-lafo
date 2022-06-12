/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.entity;

import java.util.Date;

/**
 *
 * @author user
 */
public class user extends data{
    String noHp;
    String Gender;
    String Alamat;
    String tanggalDaftar;
    String Status;
    String hakakses;
    String Kelamin;
    String Alamt;
    String TanggalGabung;
     String password;
   


    
    
    public String getKelamin() {
        return Kelamin;
    }

    public void setKelamin(String Kelamin) {
        this.Kelamin = Kelamin;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return Alamt;
    }

    public void setAlamat(String Alamt) {
        this.Alamt = Alamt;
    }

    public String getTanggalGabung() {
        return TanggalGabung;
    }

    public void setTanggalGabung(String TanggalGabung) {
        this.TanggalGabung = TanggalGabung;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = Gender;
    }

    public String getAlamt() {
        return Alamat;
    }

    public void setAlamt(String Alamat) {
        this.Alamat = Alamat;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void setHakAkses(String hakakses) {
        this.hakakses = hakakses;
    }

    public String getHakAkses() {
        return hakakses;
    }   
}