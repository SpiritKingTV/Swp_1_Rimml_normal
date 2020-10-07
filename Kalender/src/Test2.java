import org.json.JSONException;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Test2 extends Application {


    static ArrayList<Integer> Jahr = new ArrayList<Integer>();
    static ArrayList<Integer> Monat = new ArrayList<Integer>();
    static ArrayList<Integer> Tag = new ArrayList<Integer>();
    static int mocount = 0, dicount = 0, micount = 0, docount = 0, frcount = 0, sacount = 0, socount = 0;
    static String itemA = "Montag", itemB = "Dienstag", itemC = "Mittwoch", itemD = "Donnerstag", itemE = "Freitag";

    public static void main(String[] args) throws IOException, JSONException {
        int count = 0;
        int eingabeJahr = 2019;
        int eingabeJahr2 = 2069;
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
            }
        }

        System.out.println("Montag: " + mocount);
        System.out.println("Dienstag: " + dicount);
        System.out.println("Wednesday: " + micount);
        System.out.println("Donnerstag: " + docount);
        System.out.println("Freitag: " + frcount);


        launch(args);
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
        try {
            final NumberAxis xAxis = new NumberAxis();
            final CategoryAxis yAxis = new CategoryAxis();

            // Anlegen der BarChart und angabe wie die Anordnung
            final BarChart<Number, String> barChart = new BarChart<Number, String>(xAxis, yAxis);
            barChart.setTitle("Verbreitung");
            xAxis.setLabel("Wert");
            yAxis.setLabel("Betriebssystem");

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
        }catch (Exception e) {
            System.out.println("Error!!!");
        }
    }
}




