import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class readAktien {
    static  ArrayList<Double>  closeWerte = new ArrayList<>();
    public static void main(String[] args) throws IOException, JSONException {

        String Tesla= "TSLA";
        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+ Tesla + "&interval=5min&apikey=4RM4YPCEWJ1VANI3";
        getAktienwert2(URL);





    }private static void getAktienwert2(String URL ) throws JSONException, IOException {
        JSONObject json = Aktieneinlesen(URL);

       for( LocalDate i = LocalDate.now().minusDays(1); i.isAfter(LocalDate.now().minusDays(100)); i = i.minusDays(1)) {

               double closeWert = json.getJSONObject("Time Series (Daily)").getJSONObject(i.toString()).getDouble("4. close");
               closeWerte.add(closeWert);
               for (int d = 0; d < closeWerte.size(); d++) {
                   System.out.println(d);
               }


       }

    }public static JSONObject Aktieneinlesen(String URL) throws IOException, JSONException {
        JSONObject json = new JSONObject();

        json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8"))); //legtjson von URL eingabe an

        return json;
    }
}
