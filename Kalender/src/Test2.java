import org.json.JSONException;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//Bytecode halb fertig Kompelierte Datei
public class Test2 extends Application {


    static ArrayList<Integer> Jahr = new ArrayList<Integer>();
    static ArrayList<Integer> Monat = new ArrayList<Integer>();
    static ArrayList<Integer> Tag = new ArrayList<Integer>();
    static int mocount = 0, dicount = 0, micount = 0, docount = 0, frcount = 0, sacount = 0, socount = 0;
    static String itemA = "Montag", itemB = "Dienstag", itemC = "Mittwoch", itemD = "Donnerstag", itemE = "Freitag";

    public static void main(String[] args) throws IOException, JSONException {
        //int count = 0;
        int eingabeJahr = 2020;
        int eingabeJahr2 = 2060;

        Feiertage.fillArrayList(eingabeJahr, eingabeJahr2);
        for (int i = 0; i < Feiertage.freieTage.size(); i++) {
            int y2 = Feiertage.freieTage.get(i).getYear();
            Jahr.add(y2);
            int m2 = Feiertage.freieTage.get(i).getMonthValue();
            Monat.add(m2);
            int t2 = Feiertage.freieTage.get(i).getDayOfMonth();
            Tag.add(t2);

        }
        List<LocalDate> holidayDates = new ArrayList<>();
        for (int i = 0; i < Jahr.size(); i++) {
            holidayDates.add(LocalDate.of(Jahr.get(i), Monat.get(i), Tag.get(i)));
        }
        System.out.println(holidayDates);
        for (int i = 0; i < holidayDates.size(); i++) {
            switch (holidayDates.get(i).getDayOfWeek()) {
                case MONDAY:
                    mocount++;
                    break;
                case TUESDAY:
                    dicount++;
                    break;
                case WEDNESDAY:
                    micount++;
                    break;
                case THURSDAY:
                    docount++;
                    break;
                case FRIDAY:
                    frcount++;
                    break;
                case SATURDAY:
                    sacount++;
                    break;
                case SUNDAY:
                    socount++;
                    break;
            }
        }

        System.out.println("Montag: " + mocount);
        System.out.println("Dienstag: " + dicount);
        System.out.println("Wednesday: " + micount);
        System.out.println("Donnerstag: " + docount);
        System.out.println("Freitag: " + frcount);
        System.out.println("Samstag: " + sacount);
        System.out.println("Sonntag: " + socount);

        Test2 p1 = new Test2();
        p1.connect();
        p1.createNewTable();
        p1.insert();
        p1.selectAll();

        Application.launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        int wahl;
        try {
            //Angaben wie die Axen sein sollten
            final NumberAxis xAxis = new NumberAxis();
            final CategoryAxis yAxis = new CategoryAxis();

            // Anlegen der BarChart und angabe wie die Anordnung
            final BarChart<Number, String> barChart = new BarChart<Number, String>(xAxis, yAxis);
            barChart.setTitle("Verbreitung");
            xAxis.setLabel("Tage ");
            yAxis.setLabel("Feiertage");

            // Anlegen einer Serie mit den jeweiligen Werten und dem Item
            // dem sie zugeordnet werden.
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Feiertage");
            series1.getData().add(new XYChart.Data(mocount, itemA));
            series1.getData().add(new XYChart.Data(dicount, itemB));
            series1.getData().add(new XYChart.Data(micount, itemC));
            series1.getData().add(new XYChart.Data(docount, itemD));
            series1.getData().add(new XYChart.Data(frcount, itemE));
            Scene scene = new Scene(barChart, 640, 480);
            barChart.getData().addAll(series1);
            primaryStage.setScene(scene);

            primaryStage.show();

            //2 Diagramm
            Pane root = new Pane();
            ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                    new PieChart.Data("Montag", mocount),
                    new PieChart.Data("Dienstag", dicount),
                    new PieChart.Data("Mittwoch", micount),
                    new PieChart.Data("Donnerstag", docount),
                    new PieChart.Data("Freitag", frcount));
            // create a pieChart with valueList data.
            PieChart pieChart = new PieChart(valueList);
            pieChart.setTitle("Feiertage im Überblcik ");
            //adding pieChart to the root.
            root.getChildren().addAll(pieChart);
            Scene scene2 = new Scene(root, 450, 450);
            Scanner myConsole = new Scanner(System.in);


            primaryStage.setScene(scene2);

            primaryStage.show();

            System.out.println("Wollen sie sich das KreisDiagramm auch noch ansehen?[3]");
            wahl = myConsole.nextInt();
            if(wahl == 3) {
               // Thread.sleep(10000);
                primaryStage.close();


                primaryStage.setScene(scene);
                primaryStage.show();
            }
            else {
                System.out.println("Das Programm wird beendet");
                System.exit(0);
            }
        }catch (Exception e) {
            System.out.println("Error!!!");
        }
    }


    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Users\\sebas\\IdeaProjects\\Kalender\\KalenderFeiertage.db";
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
        String url = "jdbc:sqlite:C:\\Users\\sebas\\IdeaProjects\\Kalender\\KalenderFeiertage.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Feiertage("
                + "Montag integer,\n"
                + "Dienstag integer ,\n"
                + "Mittwoch integer ,\n"
                + "Donnerstag integer,\n"
                + "Freitag integer,\n "
                + "Samstag integer,\n "
                + "Sonntag integer, \n"
                + "Datum text );"
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
        String url = "jdbc:sqlite:C:\\Users\\sebas\\IdeaProjects\\Kalender\\KalenderFeiertage.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public  void insert() {
        String sql = "INSERT INTO Feiertage(Montag,Dienstag,Mittwoch,Donnerstag,Freitag,Samstag,Sonntag,Datum) VALUES(?,?,?,?,?,?,?,?)";

        try{

            Connection conn = this.connection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, mocount);
            pstmt.setInt(2, dicount);
            pstmt.setInt(3, micount);
            pstmt.setInt(4, docount);
            pstmt.setInt(5, frcount);
            pstmt.setInt(6, sacount);
            pstmt.setInt(7, socount);
            pstmt.setString(8,LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd")));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Hier");
        }
    }
    public   void selectAll(){
        System.out.println("lul");
        String sql = "SELECT * FROM Feiertage ";

        try {
            Connection conn = this.connection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);


            while (rs.next()) {
                System.out.println("Montag    Dienstag     Mittwoch    Donnerstag    Freitag     Samstag    Sonntag     Datum");
                System.out.println(
                        rs.getInt("Montag") + "\t \t \t" +
                                rs.getInt("Dienstag") + "\t \t \t" +
                                rs.getInt("Mittwoch") + "\t \t \t"+
                                rs.getInt("Donnerstag") + "\t \t \t" +
                                rs.getInt("Freitag") + "\t \t \t" +
                                rs.getInt("Samstag") + "\t \t \t"+
                                rs.getInt("Sonntag") + "\t \t \t"+
                                rs.getString("Datum"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("lul");
        }
    }
}







