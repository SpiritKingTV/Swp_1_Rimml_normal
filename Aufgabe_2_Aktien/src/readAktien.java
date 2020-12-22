import java.awt.desktop.AppForegroundListener;
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.Collections;


import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;

import javax.sound.sampled.Line;

public class readAktien extends Application {
    static String s;
    static List<LocalDate> dateList = new ArrayList<>();
    static ArrayList<Double> closeWerte = new ArrayList<>();
    static ArrayList<Double> durchschnitt = new ArrayList<>();
    static ArrayList<Double> closeWerteDB = new ArrayList<>();
    static ArrayList<String> DatumDB = new ArrayList<>();

    public static void main(String[] args) throws IOException, JSONException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie die Firma ein die Sie auslesen wollen: [AAPL,TSLA,IBM,AMZN]");

        s = scanner.next();

        String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + s + "&outputsize=full&apikey=A4RM4YPCEWJ1VANI3";

        getAktienwert2(URL);
        System.out.println(dateList);
        System.out.println(closeWerte);
        readAktien p1 = new readAktien();
        p1.connect();
        p1.createNewTable();
        p1.insert();
        p1.selectAll();
        avgBerechnen();
        Application.launch(args);




    }private static void getAktienwert2(String URL) throws JSONException, IOException {
        JSONObject json = Aktieneinlesen(URL);

        json = json.getJSONObject("Time Series (Daily)");
        for (int i=0; i<json.names().length(); i++) {
            dateList.add(LocalDate.parse((CharSequence) json.names().get(i)));
            closeWerte.add(json.getJSONObject(LocalDate.parse((CharSequence)json.names().get(i)).toString()).getDouble("4. close"));
        }

    }public static JSONObject Aktieneinlesen(String URL) throws IOException, JSONException {
        JSONObject json = new JSONObject();

        json = new JSONObject(IOUtils.toString(new URL(URL), Charset.forName("UTF-8"))); //legt json von URL eingabe an

        return json;
    }

    public static void avgBerechnen() {



        int count = 0;
        double wert = 0, x,avg;
        for(int i = 0; i <= closeWerteDB.size()-1; i++){
            count++;
            if(count <= 200){
                wert = wert + closeWerteDB.get(i);
                avg = wert/count;
               durchschnitt.add(avg);
            }
            if(count > 200) {
                x = closeWerteDB.get(i-200);
                wert = wert - x;
                wert = wert + closeWerteDB.get(i);
                avg = wert/200;
                durchschnitt.add(avg);
            }
        }

    }

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\Anwender\\IdeaProjects\\Aufgabe_2_Aktien\\Aktien.db";
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
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\Anwender\\IdeaProjects\\Aufgabe_2_Aktien\\Aktien.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS "+s+"("
                + "Datum text PRIMARY KEY,\n"
                + "Close Double );"
                ;


        try{
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connection() {
        String url = "jdbc:sqlite:C:\\Users\\Anwender\\IdeaProjects\\Aufgabe_2_Aktien\\Aktien.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public  void insert() {
        String sql = "REPLACE INTO "+s+"(Datum,Close) VALUES(?,?)";

        try{ Connection conn = this.connection();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            for(int i=0; i< dateList.size(); i++) {

                pstmt.setString(1, dateList.get(i).toString());
                pstmt.setDouble(2, closeWerte.get(i));



                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Hier ist der Fehler Zeile ~189");
        }
    }

    public   void selectAll(){
        System.out.println("Hier ist der Fehler Zeile ~194");
        String sql = "SELECT * FROM "+s+" ORDER BY Datum ASC";


        try {
            Connection conn = this.connection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);



            while (rs.next()) {
                System.out.println("Datum               Close");
                System.out.println(
                                rs.getString("Datum") + "\t \t \t " +
                                rs.getDouble("Close") + "\t \t \t") ;
               closeWerteDB.add(rs.getDouble("Close"));
               DatumDB.add( rs.getString("Datum"));


            }

                    } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Hier ist der Fehler Zeile ~218");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            //Angaben wie die Axen sein sollten
            final NumberAxis yAxis = new NumberAxis();
            final CategoryAxis xAxis = new CategoryAxis();

            // Anlegen der BarChart und angabe wie die Anordnung
            final LineChart<String, Number> lineChart = new LineChart<String,Number>(xAxis, yAxis);
            lineChart.setTitle("Aktienkurs von " +s);
            xAxis.setLabel("Datum ");
            yAxis.setLabel("CloseWerte");


            XYChart.Series<String,Number> CloseWerte = new XYChart.Series();

            for (int i=0; i<closeWerteDB.size() -1; i++) {
                CloseWerte.getData().add(new XYChart.Data(DatumDB.get(i),closeWerteDB.get(i)));
            }
            XYChart.Series<String, Number> AVG = new XYChart.Series();

            for (int i=0; i<closeWerteDB.size() -1; i++) {
                AVG.getData().add(new XYChart.Data(DatumDB.get(i),durchschnitt.get(i)));
            }


            Scene scene = new Scene(lineChart, 1000, 600);
            lineChart.getData().add(CloseWerte);
            lineChart.getData().add(AVG);
            lineChart.setCreateSymbols(false);

        for (int i=0; i<closeWerteDB.size();i++) {
            if (closeWerteDB.get(i) > durchschnitt.get(i)) {
                CloseWerte.getNode().setStyle("-fx-stroke: #6495ED;");
                AVG.getNode().setStyle("-fx-stroke: #EE7600;");

            }
                if(closeWerteDB.get(i) < durchschnitt.get(i)){
                    CloseWerte.getNode().setStyle("-fx-stroke: #EE7600;");
                    AVG.getNode().setStyle("-fx-stroke: #6495ED;");

            }
        }
            CloseWerte.setName("CloseWerte");
            AVG.setName("AVG");

            scene.getStylesheets().add("site.css");
            primaryStage.setScene(scene);

            primaryStage.show();






        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



