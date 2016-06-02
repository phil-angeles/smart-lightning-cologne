### smart-lightning-cologne
Repository for project in 'Neue Konzepte' an der DHBW MA

#### Dokumentation smart-light

##### Beschreibung des Szenarios:
  -	Wir sind ein Team von Experten für verteilte Datenverarbeitung
  -	Die Firma für die wir arbeiten besitzt keine Expertise in diesem Fachbereich
  -	Aufgrund des Megatrends „smart city“ fühlen sie sich in der Pflicht mit dieser Technologie zu experimentieren 
    Design und Entwurf eines Konzeptes mit Vorstellung des use-case mit anschließender Realisierung durch entsprechende Framework + Durchführung einer abschließenden Präsentation

#####	Unsere Idee: smart-light

  Die Schaffung von smarten Laternen, die ihren Stromverbrauch nach ihrer Nutzungsdauer anpassen. Eine Laterne selbst soll ihren Stromverbrauch, ihre Leuchtdauer und ihren Status kennen. Sie besitzt X,Y-Koordinaten innerhalb eines Koordinatensystems. Das Koordinatensystem besitzen wir aus unserer frei zugänglichen Datenbasis:
-	Wir nutzen Daten, die unter einer Creative Commons Lizenz http://creativecommons.org/licenses/by-nc-sa/3.0/  vorliegen: das „TAPAS Köln“-Szenario.
  Die "TAPAS Köln" Simulationsszenario beschreibt den Verkehr innerhalb der Stadt Köln für einen ganzen Tag (von 06:00 bis 08:00 Uhr). Die ursprünglichen Nachfragedaten stammen aus TAPAS, ein System, das auf Informationen über Reisegewohnheiten der Deutschen und auf Informationen über die Infrastruktur der Region in der sie leben Basis generiert Mobilität Wünsche für eine Fläche Bevölkerung berechnet.

#####	Frameworks:

  - Apache Flink zur Verarbeitung von Datenströmen
  - Laternen als gängige Plain Old Java Objects

#####	Die Vorgehensweise für die Verarbeitung:

  1.	Einlesen der Traffic-Daten aus TAPAS-Cologne (liegen im CSV-Format vor)
  2.	Laternen erzeugen und positionieren
  - Wir haben uns als relativ realistischen Wert dafür entschieden im ganzen Stadtgebiet 6000 Laternen zu erzeugen. Diese werden folglich per Zufallsprinzip auf den Koordinaten der Autos platziert, die im CSV vorliegen. So ist sichergestellt, dass die Laternen im Stadtgebiet verteilt vorliegen. 
  3.	Einlesen der vorliegenden CSV-Daten über den CSV-Reader
      Es handelt sich an dieser Stelle um die Streaming-Daten von vorbeifahrenden Autos, d.h. Autos die ihre Koordinaten jede Sekunde wechseln etc.
  4.	Aktuell schickt das Server Socket innerhalb eines Datenstroms alle 400 Millisekunden diese Daten an den Client
  5.	Der Client mappt die eingegeben Auto-Daten auf neu erzeugte Passanten-Objekte
  6.	Nachfolgenden wird im Datenstrom der Passanten abgeprüft, ob sich Passanten in der Nähe von Laternen befinden. Der Radius, in dem sich Laternen und Passanten treffen können, ist von uns auf 100m festgelegt 
  7.	Gibt es eine Übereinstimmung von einem Passant mit einer Laterne, so wird die Laterne jeweils 1 Sekunde angeschaltet. Nachfolgend wird dieser Prozess iterativ durchgeführt und geprüft, ob sich Passanten im Radius der Laternen befinden.

#####	Ergebnisse aus der Verarbeitung

  -	Summe der Leuchtdauer -> Aufsummieren der Leuchtdauer aller Laternen und Vergleich zu der Leuchtdauer, die anfallen würden, wenn die Laternen durchgängig angeschaltet wären. 
  -	Summe des Energieverbrauchs -> Multiplizieren der Leuchtdauer mit dem durchschnittlichen Verbrauch einer Laterne pro Minute	- Durchschnittliche Leuchtdauer einer Laterne im Vergleich zur durchgängigen Leuchtdauer

#####	Übergeordnete Ergebnisse

  - Einsparung vom täglichen Energieverbrauch einer Stadt 
  - Umweltfreundlichkeit
  - Kosteneinsparungen für die Stadt
  - Nachhaltige Lösung

##### Präsentation
Link:
https://github.com/phil-angeles/smart-lightning-cologne/blob/master/Praesentation.html
Live Preview:
https://rawgit.com/phil-angeles/smart-lightning-cologne/master/Praesentation.html
  
##### Video auf Youtube
https://youtu.be/rK4X9ldFf90  
