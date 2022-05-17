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
public class menu extends data{
    int jumlah;
    float harga;
    String kategori;
    float Subtotal = harga * jumlah;
    float Diskon;

    public float getDiskon() {
        return Diskon;
    }

    public void setDiskon(float Diskon) {
        this.Diskon = Diskon;
    }
    
    

    public float getSubtotal() {
        return Subtotal;
    }

    
    
    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }

    
    
    
}
