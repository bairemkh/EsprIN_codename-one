package Utils;

import com.codename1.db.Database;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;


public class UsefulMethodes {
    public static Date  convertStringToDate(String str){
        str=str.subSequence(0,str.indexOf("+")).toString();
        str=str.replace('T',' ');
        return Date.from(Timestamp.valueOf(str).toInstant());
    }
    public static String convertDateToString(Date date){
        SimpleDateFormat dtf =new SimpleDateFormat("dd/MM/yyyy");
        return dtf.format(date);
    }

}
