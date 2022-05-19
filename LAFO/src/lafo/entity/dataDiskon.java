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
public class dataDiskon extends data{
    
    private String jumlahDiskon;
    private String tenggatDiskon;
    
    public dataDiskon (String kode){
        super.kode = kode;
    }

    public String getJumlahDiskon() {
        return jumlahDiskon;
    }

    public void setJumlahDiskon(String jumlahDiskon) {
        this.jumlahDiskon = jumlahDiskon;
    }

    public String getTenggatDiskon() {
        return tenggatDiskon;
    }

    public void setTenggatDiskon(String tenggatDiskon) {
        this.tenggatDiskon = tenggatDiskon;
    }
    
    
    
}
