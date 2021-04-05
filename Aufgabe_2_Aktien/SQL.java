import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;



public class SQL{
    public static String DBurl = "jdbc:mysql://localhost:3306/db_Aktien?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static Statement myStmt;
    public static Connection connection;

    static List<LocalDate> dateList = new ArrayList<>();
    static ArrayList<Double> closeWerte = new ArrayList<>();
    static ArrayList<Double> adjustedCloseWerte = new ArrayList<>();
    static ArrayList<Double> durchschnitt = new ArrayList<>();
    static ArrayList<Double> closeWerteDB = new ArrayList<>();
    static ArrayList<String> DatumDB = new ArrayList<>();
    static ArrayList<Double> adjustedcloseWerteDB = new ArrayList<>();

    static void getAktienwert2(String URL) throws JSONException, IOException {
        JSONObject json = Aktieneinlesen(URL);

        json = json.getJSONObject("Time Series (Daily)");
        System.out.println(URL);
        for (int i=0; i<json.names().length(); i++) {
            dateList.add(LocalDate.parse((CharSequence) json.names().get(i)));
            closeWerte.add(json.getJSONObject(LocalDate.parse((CharSequence)json.names().get(i)).toString()).getDouble("4. close"));
            adjustedCloseWerte.add(json.getJSONObject(LocalDate.parse((CharSequence)json.names().get(i)).toString()).getDouble("5. adjusted close"));
        }



    }

    public static JSONObject Aktieneinlesen(String URL) throws IOException, JSONException {
        JSONObject json;

        json = new JSONObject(IOUtils.toString(new URL(URL), StandardCharsets.UTF_8));  //legt json von URL eingabe an

        return json;
    }


    public static void avgBerechnen() {
        int count = 0;
        double wert = 0, x,avg;
        for(int i = 0; i <= adjustedcloseWerteDB.size()-1; i++){
            count++;
            if(count <= 200){
                wert = wert + adjustedcloseWerteDB.get(i);
                avg = wert/count;
                durchschnitt.add(avg);
            }
            if(count > 200) {
                x = adjustedcloseWerteDB.get(i-200);
                wert = wert - x;
                wert = wert + adjustedcloseWerteDB.get(i);
                avg = wert/200;
                durchschnitt.add(avg);
            }
        }

    }
    static void connectToMySql(String pwd)  {
        {
            try {
                connection = DriverManager.getConnection(DBurl, "root", "Airbase11");
                myStmt = connection.createStatement();
                System.out.println("Datenbank verknüpft");

            } catch (SQLException e) {
                System.out.println("Datenbank wurde nicht verknüpft");
                e.printStackTrace();
            }

        }
    }

    static void createTable(String Stock)  {
        try{

            myStmt = connection.createStatement();
            String createtable = "create table if not exists "+Stock+" (datum varchar(255) primary key, close double, adjustedclose double);";
            //String createtableAVG = "create table if not exists "+Stock+"AVG (datumAVG varchar(255) primary key, avg double);";
            myStmt.executeUpdate(createtable);
            //  myStmt.executeUpdate(createtableAVG);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void writeDataInDB(String Stock){
        try {
            myStmt = connection.createStatement();
            for(int i = 0; i < dateList.size(); i++) {
                String writeData = "insert ignore into " + Stock + " (datum, close,adjustedclose ) values('" + dateList.get(i) + "', '" + closeWerte.get(i) + "','" + adjustedCloseWerte.get(i) + "');";
                myStmt.executeUpdate(writeData);
            }
            System.out.println("Datensatz eingetragen");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getData(String Stock) {
        try {
            Statement myStmt = connection.createStatement();


            ResultSet rsNormal = myStmt.executeQuery( "SELECT * from " + Stock + " order by datum");
            System.out.println("Stock=" + Stock);
          //  System.out.println("Datum               Close Werte           ");
            while (rsNormal.next()) {
               /* System.out.println(
                        rsNormal.getString("datum") + "\t \t \t \t" +
                                rsNormal.getDouble("close") + "\t \t \t \t" +
                                rsNormal.getDouble("adjustedclose") + "\t \t \t \t"
                );*/

                DatumDB.add(rsNormal.getString("datum"));
                closeWerteDB.add(rsNormal.getDouble("close"));
                adjustedcloseWerteDB.add(rsNormal.getDouble("adjustedclose"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Tabele für Durchschnitts werte anlegen
    static void createTableAVG(String Stock)  {
        try{
            myStmt = connection.createStatement();
            String createtable = "create table if not exists "+Stock+"AVG (datum varchar(255) primary key, closeAVG double);";
            //String createtableAVG = "create table if not exists "+Stock+"AVG (datumAVG varchar(255) primary key, avg double);";
            myStmt.executeUpdate(createtable);
            //  myStmt.executeUpdate(createtableAVG);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    static void writeDataInDBAVG(String Stock){
        try {
            myStmt = connection.createStatement();
            for(int i = 0; i < dateList.size(); i++) {
                String writeData = "insert ignore into " + Stock + "AVG (datum, closeAVG ) values('" + dateList.get(i) + "', '" + durchschnitt.get(i) + "');";
                myStmt.executeUpdate(writeData);
            }
            System.out.println("Datensatz Table AVG eingetragen");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getDataAVG(String Stock) {
        try {
            Statement myStmt = connection.createStatement();


            ResultSet rsNormal = myStmt.executeQuery( "SELECT * from " + Stock + "AVG order by datum");

         //   System.out.println("Datum               AVG Werte           ");
            while (rsNormal.next()) {
             /*   System.out.println(
                        rsNormal.getString("datum") + "\t \t \t \t" +
                                rsNormal.getDouble("closeAVG") + "\t \t \t \t"

                );*/

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}