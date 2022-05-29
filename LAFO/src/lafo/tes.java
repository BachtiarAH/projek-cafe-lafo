/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lafo;

import java.time.LocalDate;
import lafo.entity.user;
import lafo.proses.Utility;
/**
 *
 * @author user
 */
public class tes {
    public static void main(String[] args) {
    LocalDate tgl = LocalDate.now();
        System.out.println(tgl.getYear()+"-"+tgl.getMonthValue());
        
    }
}
