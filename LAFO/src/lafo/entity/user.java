/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.entity;

/**
 *
 * @author user
 */
public class user extends data{
    String hakAkses;
    String Kelamin;
    String noHp;
    String Alamt;
    String TanggalGabung;
    String Status;

    
    
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

    public String getAlamt() {
        return Alamt;
    }

    public void setAlamt(String Alamt) {
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

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    
}
