import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Scanner;


public class test {
    static String s;
    static List<LocalDate> dateList = new ArrayList<>();
    static ArrayList<Double> closeWerte = new ArrayList<>();

    public static void main(String[] args) throws IOException, JSONException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie die Firma ein die Sie auslesen wollen: [AAPL,TSLA,IBM,AMZN]");
         s = scanner.next();

        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+s+"&interval=&apikey=4RM4YPCEWJ1VANI3";



        getAktienwert2(URL);
        test p1 = new test();
        p1.connect();
        p1.createNewTable();
        p1.insert();
        p1.selectAll();


    }private static void getAktienwert2(String URL) throws JSONException, IOException {
        JSONObject json = Aktieneinlesen(URL);

        json = json.getJSONObject("Time Series (Daily)");
        for (int i=0; i<100; i++) {
           dateList.add(LocalDate.parse((CharSequence) json.names().get(i)));
           closeWerte.add(json.getJSONObject(LocalDate.parse((CharSequence)json.names().get(i)).toString()).getDouble("4. close"));

       }
    }public static JSONObject Aktieneinlesen(String URL) throws IOException, JSONException {
        JSONObject json = new JSONObject();

        json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8"))); //legt json von URL eingabe an

        return json;
    }public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\sebas\\IdeaProjects\\Aktien\\"+s+".db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\sebas\\IdeaProjects\\Aktien\\"+s+".db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Feiertage("
                + "Datum text,\n"
                + "Close Double );"
                ;


        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }private Connection connection() {
        String url = "jdbc:sqlite:C:\\Users\\sebas\\IdeaProjects\\Aktien\\"+s+".db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }public  void insert() {
        String sql = "INSERT INTO Feiertage(Datum,Close) VALUES(?,?)";

        try{ Connection conn = this.connection();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            for(int i=0; i<100; i++) {

               pstmt.setString(1, dateList.get(i).toString());
                pstmt.setDouble(2, closeWerte.get(i));



                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Hier");
        }
    }public   void selectAll(){
        System.out.println("lul");
        String sql = "SELECT * FROM Feiertage ";

        try {
            Connection conn = this.connection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            while (rs.next()) {
                System.out.println("Datum               Close");
                System.out.println(
                                rs.getString("Datum") + "\t \t \t " +
                                rs.getDouble("Close") + "\t \t \t") ;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("lul");
        }
    }
}


