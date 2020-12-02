Was macht das Programm:
Das Programm dient dazu aus einer JSON von Alphavantage die Schließungen der Aktie von jedem Tag auszulesen und diese in eine Datenbank abzuspeichern. Also vereinfacht gesagt wird eine Liste mit den jeweiligen close Werten plus das dazugehörige Datum abgespeichert. Diese Liste wird dann mithilfe von SQLite in eine Datenbank abgespeichert und ausgegeben.
Zum Starten benötigt man:
1.commons-io-2.7.jar <br>2.java.json.jar  <br> 3.sqlite-jdbx-3.32.3.2.jar


Was muss man beachten wenn man eine Library einbindet?
Dieses Tutorial wird helfen:
https://www.jetbrains.com/help/idea/library.html#define-library
