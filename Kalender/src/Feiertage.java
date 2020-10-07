import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;

import static java.util.Calendar.TUESDAY;



public class Feiertage {
    static ArrayList<LocalDate> freieTage = new ArrayList<LocalDate>();



        ArrayList<LocalDate> freieTage2 = new ArrayList<LocalDate>();

        // static LocalDate[] freietage = new LocalDate[1000000];
        int eingabeJahr = 2019;
        int eingabeJahr2 = 2069;
        int jahre = eingabeJahr2 - eingabeJahr;



    /*    String Feiertag10 = getWert(json, "Karfreitag");
        String Feiertag11= getWert(json, "Karfreitag");
        String Feiertag12= getWert(json, "Karfreitag");
        String Feiertag13= getWert(json, "Karfreitag");


*/


        //LocalDate localDate = LocalDate.parse(Feiertag1);
        //LocalDate localDate2 = LocalDate.parse(Feiertag1);



      /*  LocalDate localDate = LocalDate.parse(Feiertag1);
        System.out.println(localDate.getDayOfWeek());
        DayOfWeek hello=localDate.getDayOfWeek();
        DayOfWeek hello2= localDate.getDayOfWeek();

        if(1 ==TUESDAY){
            count++;

        }
        System.out.println(hello2);
        LocalDate localDate1 = LocalDate.parse(Feiertag2);
        System.out.println(localDate1.getDayOfWeek());
        LocalDate localDate2 = LocalDate.parse(Feiertag3);
        System.out.println(localDate2.getDayOfWeek());
        LocalDate localDate3 = LocalDate.parse(Feiertag4);
        System.out.println(localDate3.getDayOfWeek());
        LocalDate localDate4 = LocalDate.parse(Feiertag5);
        System.out.println(localDate4.getDayOfWeek());
        LocalDate localDate5 = LocalDate.parse(Feiertag6);
        System.out.println(localDate5.getDayOfWeek());
        LocalDate localDate6 = LocalDate.parse(Feiertag7);
        System.out.println(localDate6.getDayOfWeek());
        LocalDate localDate7 = LocalDate.parse(Feiertag8);
        System.out.println(localDate7.getDayOfWeek());
        LocalDate localDate8= LocalDate.parse(Feiertag9);
        System.out.println(localDate8.getDayOfWeek());
       /* LocalDate localDate9 = LocalDate.parse(Feiertag10);
        System.out.println(localDate.getDayOfWeek());
        LocalDate localDate10 = LocalDate.parse(Feiertag1);
        System.out.println(localDate.getDayOfWeek());*/




    private static String getWert(JSONObject json, String key) throws JSONException {

        JSONObject json2 = (JSONObject) json.get(key);
        String datum = json2.getString("datum");
        String[] DatumString = new String[100000];
        DatumString[0] = datum;

        return datum;
    }

    public static void fillArrayList( int eingabeJahr, int eingabeJahr2) throws JSONException, IOException {
        for (int i = 2000; i < 2069; i++) {
            String URL = "https://feiertage-api.de/api/?jahr=" + i + "&nur_land=BY";
            JSONObject json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8")));

            String Feiertag1 = getWert(json, "Neujahrstag");//
            String Feiertag2 = getWert(json, "Heilige Drei Könige");//
            String Feiertag3 = getWert(json, "Mariä Himmelfahrt");//
            String Feiertag4 = getWert(json, "Ostermontag");//
            String Feiertag5 = getWert(json, "Tag der Arbeit");
            String Feiertag6 = getWert(json, "Christi Himmelfahrt");//
            String Feiertag7 = getWert(json, "Pfingstmontag");//
            String Feiertag8 = getWert(json, "Fronleichnam");//
            String Feiertag9 = getWert(json, "Allerheiligen");
            String Feiertag10 = getWert(json, "1. Weihnachtstag");
            String Feiertag11 = getWert(json, "2. Weihnachtstag");



            freieTage.add(LocalDate.of(2020,1,1));

           LocalDate feiertagLocal = LocalDate.parse(Feiertag1);
            LocalDate feiertagLocal2 = LocalDate.parse(Feiertag2);
            LocalDate feiertagLocal3 = LocalDate.parse(Feiertag3);
            LocalDate feiertagLocal4 = LocalDate.parse(Feiertag4);
            LocalDate feiertagLocal5 = LocalDate.parse(Feiertag5);
            LocalDate feiertagLocal6 = LocalDate.parse(Feiertag6);
            LocalDate feiertagLocal7 = LocalDate.parse(Feiertag7);
            LocalDate feiertagLocal8 = LocalDate.parse(Feiertag8);
            LocalDate feiertagLocal9 = LocalDate.parse(Feiertag9);
            LocalDate feiertagLocal10 = LocalDate.parse(Feiertag10);
            LocalDate feiertagLocal11 = LocalDate.parse(Feiertag11);



            freieTage.add(feiertagLocal);
            freieTage.add(feiertagLocal2);
            freieTage.add(feiertagLocal3);
            freieTage.add(feiertagLocal4);
            freieTage.add(feiertagLocal5);
            freieTage.add(feiertagLocal6);
            freieTage.add(feiertagLocal7);
            freieTage.add(feiertagLocal8);
            freieTage.add(feiertagLocal9);
            freieTage.add(feiertagLocal10);
            freieTage.add(feiertagLocal11);





        }


  /*  private static LocalDate getDateinLocal(){
  */



    }
}