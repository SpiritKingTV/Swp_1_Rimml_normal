import org.json.JSONException;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
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
        int eingabeJahr2 = 2022;

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
        /* for (int i = 0; i < Jahr.size(); i++) {
            for (int x = 0; x < Monat.size(); x++) {
                for (int y = 0; y < Tag.size(); y++) {
                    int jahr = Test.Jahr.get(i);
                    int monat = Test.Monat.get(x);
                    int tag = Test.Tag.get(y);


                    // String t, m, j;
                    String ausgabe;
                    // int tag, monat, jahr;
                    //jh steht für das Jahrhundert, d.h. die vorderen zwei Ziffern der Jahreszahl
                    //ja steht für das Jahr im Jahrhundert, d.h. die beiden letzten Ziffern der Jahreszahl
                    int jh, ja;
                    int mocount = 0, dicount = 0, micount = 0, docount = 0, frcount = 0, sacount = 0, socount = 0;


                  /*  t = JOptionPane.showInputDialog("Geben Sie den Tag ein:");
                    m = JOptionPane.showInputDialog("Geben Sie den Monat als Zahl ein:");
                    j = JOptionPane.showInputDialog("Geben Sie das Jahr ein. Es muss zwischen 1900 und 2099 liegen:");

                    tag = Integer.parseInt(t);
                    monat = Integer.parseInt(m);
                    jahr = Integer.parseInt(j);
*/
        //Abfrage, ob Datum korrekt eingegeben wurde
                 /*   if (tag < 1 || tag > 31 || monat < 1 || monat > 12 || jahr < 1900 || jahr > 2099) {
                        //JOptionPane.showMessageDialog(null, "Sie haben ein falsches Datum eingegeben.\nStarten Sie das Programm neu.\nTschuess");
                        // System.exit(0);
                        System.out.println("Falsche Eingabe");
                    }


                    jh = jahr / 100;
                    ja = jahr % 100;


                    //Monat ist Januar oder Februar
                    if ((monat == 1) || (monat == 2)) {

                        monat = monat + 9;
                        ja--;
                    } else
                        monat = monat - 3;

                    //Berechnung
                    tag = ((146097 * jh) / 4 + (1461 * ja) / 4 + (153 * monat + 2) / 5 + tag + 1721119) % 7;

                    //Nr. des Tages legt den Wochentag fest, gelöst durch if
                    ausgabe = "";

                    if (tag == 0)
                        ausgabe = "Montag";

                    if (tag == 1)
                        ausgabe = "Dienstag";

                    if (tag == 2)
                        ausgabe = "Mittwoch";

                    if (tag == 3)
                        ausgabe = "Donnerstag";

                    if (tag == 4)
                        ausgabe = "Freitag";

                    if (tag == 5)
                        ausgabe = "Samstag";

                    if (tag == 6)
                        ausgabe = "Sonntag";

                    //JOptionPane.showMessageDialog(null, "Der " + t + "." + m + "." + j + " ist ein " + ausgabe + ".");
                 //   System.out.println(ausgabe);
                   // System.out.println(count);
                    if (ausgabe == "Montag") {
                        mocount++;
                        //System.out.println(mocount);

                    }
                    if (ausgabe == "Dienstag") {
                        dicount++;
                        //  System.out.println(dicount);

                    }
                    if (ausgabe == "Mittwoch") {
                        micount++;
                        //  System.out.println("Mittwoch count"+micount);

                    }
                    if (ausgabe == "Donnerstag") {
                        docount++;
                        //  System.out.println(docount);

                    }
                    if (ausgabe == "Freitag") {
                        frcount++;
                        //    System.out.println(frcount);

                        if (ausgabe == "Samstag") {
                            sacount++;
                            //       System.out.println(sacount);

                        }
                        if (ausgabe == "Sonntag") {
                            socount++;
                            //    System.out.println(socount);

                        }
                       // System.out.println(mocount+" "+dicount+" "+micount+" "+docount+" "+frcount+" "+sacount+" "+socount);
                    }

                }
            }
        }*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        double wahl;
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
           /* System.out.println("Welche Art von Darstellung wollen Sie haben? ");
            System.out.println("Kreisdiagramm [1], Balkendiagramm [2]: ");
            wahl=myConsole.nextDouble();
            double wahl2;
            if(wahl ==1) {

                primaryStage.setScene(scene);
                primaryStage.show();
                System.out.println("Wollen sie sich das KreisDiagramm auch noch ansehen?[3]");
                wahl2 = myConsole.nextDouble();




              if (wahl2 == 3) {
                  Thread.sleep(100000000);

                  primaryStage.close();
                    wahl = 2;


                }
            }
            if(wahl ==2) {
                primaryStage.setScene(scene2);
                primaryStage.show();
            }*/

            primaryStage.setScene(scene);
            primaryStage.show();





        }catch (Exception e) {
            System.out.println("Error!!!");
        }
    }
    /*public void start(Stage primaryStage) {
        Pane root = new Pane();
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Montag", mocount),
                new PieChart.Data("Dienstag", dicount),
                new PieChart.Data("Mittwoch", micount));
                new PieChart.Data("Donnerstag", ));
                new PieChart.Data("Mittwoch", micount));
        // create a pieChart with valueList data.
        PieChart pieChart = new PieChart(valueList);
        pieChart.setTitle("Popularity of Mobile OS");
        //adding pieChart to the root.
        root.getChildren().addAll(pieChart);
        Scene scene = new Scene(root, 450, 450);

        primaryStage.setTitle("Kreisdiagramm Feiertage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    */
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
                + "Sonntag integer );";


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
        String sql = "INSERT INTO Feiertage(Montag,Dienstag,Mittwoch,Donnerstag,Freitag,Samstag,Sonntag) VALUES(?,?,?,?,?,?,?)";

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
                System.out.println("Montag    Dienstag     Mittwoch    Donnerstag    Freitag     Samstag    Sonntag");
                System.out.println(
                        rs.getInt("Montag") + "\t \t \t" +
                                rs.getInt("Dienstag") + "\t \t \t" +
                                rs.getInt("Mittwoch") + "\t \t \t"+
                                rs.getInt("Donnerstag") + "\t \t \t" +
                                rs.getInt("Freitag") + "\t \t \t" +
                                rs.getInt("Samstag") + "\t \t \t"+
                                rs.getInt("Sonntag"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("lul");
        }
    }
}







