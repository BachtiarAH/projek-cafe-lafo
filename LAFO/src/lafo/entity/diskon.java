/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo.entity;

import java.util.Date;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author RSI-08
 */
public class diskon extends data{
    Date tenggatWaktu;
    float jumlahDiskon;

    public Date getTenggatWaktu() {
        return tenggatWaktu;
    }
    
    public Date setTenggatWaktu(String tanggal){
        tenggatWaktu.setDate(Integer.valueOf(tanggal.substring(9, 10)));
        tenggatWaktu.setMonth(Integer.valueOf(tanggal.substring(6, 8)));
        tenggatWaktu.setYear(Integer.valueOf(tanggal.substring(0, 5)));
        
        return tenggatWaktu;
    }

    public void setTenggatWaktu(Date tenggatWaktu) {
        this.tenggatWaktu = tenggatWaktu;
    }

    public float getJumlahDiskon() {
        return jumlahDiskon;
    }

    public void setJumlahDiskon(float jumlahDiskon) {
        this.jumlahDiskon = jumlahDiskon;
    }
    
    public static void main(String[] args) {
        diskon dis = new diskon();
        
        dis.setTenggatWaktu("2022-05-17");
        dis.getTenggatWaktu();
    }
    
}
