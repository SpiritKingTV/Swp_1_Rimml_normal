import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.chart.*;
import javafx.scene.image.WritableImage;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javax.imageio.ImageIO;
import java.io.File;



public class readAktien extends Application  {
    static String Stock;
    static List<LocalDate> dateList = new ArrayList<>();
    static ArrayList<Double> closeWerte = new ArrayList<>();
    static ArrayList<Double> durchschnitt = new ArrayList<>();
    static ArrayList<Double> closeWerteDB = new ArrayList<>();
    static ArrayList<String> DatumDB = new ArrayList<>();

    public static String DBurl = "jdbc:mysql://localhost:3306/db_Aktien?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static Statement myStmt;
    public static Connection connection;


    public static void main(String[] args) throws IOException, JSONException {


        /*for( int i=0; i<c.dataCompanies.length; i++) {
            System.out.println("Geben Sie die Firma ein die Sie auslesen wollen: [AAPL,TSLA,IBM,AMZN]");
            Stock = scanner.next();


            Stock = c.dataCompanies[i];

            String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + c.dataCompanies[i]  + "&outputsize=full&apikey=A4RM4YPCEWJ1VANI3";

            getAktienwert2(URL);
            //  System.out.println(dateList);
            // System.out.println(closeWerte);
            readAktien p1 = new readAktien();
            p1.connect();
            p1.createNewTable();
            p1.insert();
            p1.selectAll();
            p1.selectAll();
            avgBerechnen();

        }

*/
        Application.launch(args);
    }


    private static void getAktienwert2(String URL) throws JSONException, IOException {
        JSONObject json = Aktieneinlesen(URL);

        json = json.getJSONObject("Time Series (Daily)");
        System.out.println(URL);
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
    static boolean connectToMySql() throws SQLException {
        {
            try {
                connection = DriverManager.getConnection(DBurl, "root", "Airbase11");
                myStmt = connection.createStatement();
                System.out.println("Datenbank verknüpft");
                return true;
            } catch (SQLException e) {
                System.out.println("Datenbank wurde nicht verknüpft");
                e.printStackTrace();
            }
            return false;
        }
    }

    static boolean createTable() throws SQLException {
        try{
            myStmt = connection.createStatement();
            String createtable = "create table if not exists "+Stock+" (datum varchar(255) primary key, close double);";
            //String createtableAVG = "create table if not exists "+Stock+"AVG (datumAVG varchar(255) primary key, avg double);";
            myStmt.executeUpdate(createtable);
          //  myStmt.executeUpdate(createtableAVG);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    static void writeDataInDB(){
        try {
            myStmt = connection.createStatement();
            for(int i = 0; i < dateList.size(); i++) {
                String writeData = "insert ignore into " + Stock + " (datum, close) values('" + dateList.get(i) + "', '" + closeWerte.get(i) + "');";
                myStmt.executeUpdate(writeData);
            }
            System.out.println("Datensatz eingetragen");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getData() {
        try {
            Statement myStmt = connection.createStatement();


            ResultSet rsNormal = myStmt.executeQuery( "SELECT * from " + Stock + " order by datum");

            System.out.println("Datum               Close Werte           ");
            while (rsNormal.next()) {
                System.out.println(
                        rsNormal.getString("datum") + "\t \t \t \t" +
                                rsNormal.getDouble("close") + "\t \t \t \t"
                                );
                System.out.println("Stock=" + Stock);
                DatumDB.add(rsNormal.getString("datum"));
                closeWerteDB.add(rsNormal.getDouble("close"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        File file;
        try {
            File companies = new File("C:\\Users\\Anwender\\IdeaProjects\\Aufgabe_2_Aktien\\Aktien\\src\\companies");
            readCompanies c = new readCompanies(companies);

            c.read();

            System.out.println(Arrays.toString(c.dataCompanies));
            c.close();


            for( int firma=0; firma<c.dataCompanies.length; firma++) {
                /*System.out.println("Geben Sie die Firma ein die Sie auslesen wollen: [AAPL,TSLA,IBM,AMZN]");
                Stock = scanner.next();*/


                Stock = c.dataCompanies[firma];

                String URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + Stock + "&outputsize=full&apikey=A4RM4YPCEWJ1VANI3";

                getAktienwert2(URL);
                //  System.out.println(dateList);
                // System.out.println(closeWerte);
                readAktien p1 = new readAktien();
                p1.connectToMySql();
                p1.createTable();
                p1.writeDataInDB();

                p1.getData();


                avgBerechnen();
                //Angaben wie die Axen sein sollten
                final NumberAxis yAxis = new NumberAxis();
                final CategoryAxis xAxis = new CategoryAxis();

                // Anlegen der BarChart und angabe wie die Anordnung
                final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
                lineChart.setTitle("Aktienkurs von " + Stock);
                xAxis.setLabel("Datum ");
                yAxis.setLabel("CloseWerte");


                XYChart.Series<String, Number> CloseWerte = new XYChart.Series();

                for (int i = 0; i < closeWerteDB.size() - 1; i++) {
                    CloseWerte.getData().add(new XYChart.Data(DatumDB.get(i), closeWerteDB.get(i)));
                }
                XYChart.Series<String, Number> AVG = new XYChart.Series();

                for (int i = 0; i < closeWerteDB.size() - 1; i++) {
                    AVG.getData().add(new XYChart.Data(DatumDB.get(i), durchschnitt.get(i)));
                }


                Scene scene = new Scene(lineChart, 1000, 600);
                lineChart.getData().add(CloseWerte);
                lineChart.getData().add(AVG);
                lineChart.setCreateSymbols(false);

                for (int i = 0; i < closeWerteDB.size(); i++) {
                    if (closeWerteDB.get(i) > durchschnitt.get(i)) {
                        scene.getStylesheets().add("site.css");

                    }
                    if (closeWerteDB.get(i) < durchschnitt.get(i)) {
                        scene.getStylesheets().add("site2.css");

                    }
                }
                // Collections.reverse(closeWerteDB);
                yAxis.setAutoRanging(false);
                double AbstandOBEN = Collections.max(closeWerteDB);
                double AbstandUNTEN = Collections.min(closeWerteDB);

                yAxis.setLowerBound(AbstandUNTEN - 20);
                yAxis.setUpperBound(AbstandOBEN + 20);
                CloseWerte.setName("CloseWerte");
                AVG.setName("AVG");
                primaryStage.setScene(scene);
                primaryStage.show();

                closeWerte.clear();
                dateList.clear();
                DatumDB.clear();
                closeWerteDB.clear();
                durchschnitt.clear();

                file = new File("C:\\Users\\Anwender\\IdeaProjects\\Aufgabe_2_Aktien\\PNG\\"+Stock+LocalDate.now()+".png");
                WritableImage writableImage = new WritableImage((int)lineChart.getWidth() + 400,(int)lineChart.getHeight() + 400);
                lineChart.snapshot(null, writableImage);
                RenderedImage rImage = SwingFXUtils.fromFXImage(writableImage,
                        null);
                ImageIO.write(rImage, "png", file);
               primaryStage.close();


            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    }



