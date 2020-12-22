Was macht das Programm:<br>
Das Programm dient dazu aus einer JSON von Alphavantage die Schließungen der Aktie von jedem Tag auszulesen und diese in eine Datenbank abzuspeichern. Also vereinfacht gesagt wird eine Liste mit den jeweiligen close Werten plus das dazugehörige Datum abgespeichert. Diese Liste wird dann mithilfe von SQLite in eine Datenbank abgespeichert und ausgegeben.<br>
Am Anfang des Programmes wird eine Abfrage des gewünschten Unternehmens stattfinden. Diese beantwortet man mit der erwünschten Abkürzung:<br>1. Apple...AAPL <br> 2. Amazon...AMZN<br> 3. Tesla...TSLA<br>4. International Business Machines Corporation...IBM<br><br>Zum Starten benötigt man:<br>1.commons-io-2.7.jar <br>2.java.json.jar  <br> 3.sqlite-jdbx-3.32.3.2.jar<br> 4.javafx-libary:https://gluonhq.com/products/javafx/<br>

![image](https://user-images.githubusercontent.com/59960768/102935174-e78cf780-44a5-11eb-996a-8183bf285a36.png)
![image](https://user-images.githubusercontent.com/59960768/102935283-2327c180-44a6-11eb-8a7c-5646c11675c3.png)



Was muss man beachten wenn man eine Library einbindet?
Dieses Tutorial wird helfen:
https://www.jetbrains.com/help/idea/library.html#define-library
