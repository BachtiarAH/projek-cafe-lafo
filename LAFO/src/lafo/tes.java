/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo;

import lafo.entity.user;
/**
 *
 * @author user
 */
public class tes {
    
    public static void main(String[] args) {
        user pengguna = new user();
        
        pengguna.setKode("1234");
        pengguna.setHakAkses("pegawai");
        pengguna.setNama("dika");
        
        System.out.println(pengguna.getKode());
        System.out.println(pengguna.getNama());
        System.out.println(pengguna.getHakAkses());
        
    }
}
