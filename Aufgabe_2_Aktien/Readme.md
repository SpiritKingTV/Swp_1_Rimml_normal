Was ist die Aufgabe des Programms?
Diese Programm dient zu auswertung einer Aktien-API von der Webside https://www.alphavantage.co. Aus dieser API wird der Close Wert und das Datum ausgelesen und in einer 
Datenbank mithilfe von mySQL abgespeichert. Um die Aktien einzulesen benötigt man eine Textdatei in der die Abkürzungen der einzelnen Aktien geschrieben stehen. (z.b.: AAPL für Apple)
Wichtig zu beachten bei der Textdatei:
Nicht mehr als 5 AKtien angeben. (Die API kann über den Key nur ein paar mal pro Minute aufgerufen werden)
Der API Key wird ebenfalls in einer Textdatei eingelesen.
Was benötigt man alles zum Starten bzw zum Automatischen Starten:
1. Man benötigt einen API Key von https://www.alphavantage.co
2. Download der Programme
3. Java fx und json lib und mySQL Connector einbinden (https://www.jetbrains.com/help/idea/library.html)
4. Für java Fx müssen noch die VM-Options geändert werden  
  Man geht auf dem Menü Punkt Run:
-> Edit Configurations bei VM options.
--module-path Pfad --add-modules javafx.controls,javafx.fxml eingeben. Bei Pfad muss der Pfad bis zur lib eingefügt werden.
 Zum Schluss muss nur no Apply gedrückt werden und Ok.
5. Schreiben der Textdateien (eine Datei mit den Aktien, eine mit dem API Key, eine mit dem Password für mySQL)
6. Pfad der einzelnen Textdateien + Pfad und localhost (von mySQL) ändern.
7. Datenbank erstellen
8. Programm starten
[ Auto Start]
1. Programm starten und gesamte erste Zeile kopieren.
2. Diese Kopierte Zeile in einer Editor Datei Kopieren und sie als .cmd abspeichern.
3. Öffnen der Computerverwaltung
4. Aufgabe erstellen
5. Name, Beschreibung, Benutzer, Betriebssystem auswählen!

6. Trigger setzen (Wann das Programm gestarten werden soll [jeden Tag, nur einmal, wöchentlich]
7. AKtion setzten (Die cmd Datei auswählen)
8. Mit 2 mal OK bestätigen

Was macht das Programm im Detail?
Es liest die Close Werte (adjusted CLose Werte wegen split correction) ein und speichert diese in einer Datenbank ab. Aus diesen Werten in der Datenbank wird nun der 200 Durchschnitt gebildet.
Nun zeichnet das Programm mithilfe von javaFX und den zwei Linien (die Close Werte, die Durchschnittswerte) einen Graphen. Dieser Graphen verändert die Hintergrundfarbe je nach AKtie.
Wenn der letzte Durchschnitt über dem letzten Close Wert liegt bekommt der Graph einen Roten Hintergrund. WEnn der Durchschnitt darunter liegt einen Grünen.
![GME2021-04-05](https://user-images.githubusercontent.com/59960768/113630860-de94f000-9668-11eb-8b31-9cacbd518d2e.png)
![AMZN2021-04-05](https://user-images.githubusercontent.com/59960768/113630889-e9e81b80-9668-11eb-8684-7f1d314597f0.png)
![CBOE2021-04-05](https://user-images.githubusercontent.com/59960768/113630936-f8cece00-9668-11eb-929e-db839c6e78cd.png)
