

package lafo.proses.DataBase;

import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.util.converter.LocalDateStringConverter;
import javax.swing.text.DateFormatter;


public class tes {
    public static void main(String[] args) {
        LocalDateTime myDateObj = LocalDateTime.now();  
    System.out.println("Before Formatting: " + myDateObj);  
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyy");  
    
    String formattedDate = myDateObj.format(myFormatObj);  
    System.out.println("After Formatting: " + formattedDate); 
    }
}
