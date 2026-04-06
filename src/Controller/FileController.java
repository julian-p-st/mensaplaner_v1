package Controller;

import Model.HistoryModel;
import View.MainView;
import View.OptionPane;
import edu.kit.aifb.atks.mensascraper.lib.MensaLine;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller, der die Logik für die Dateiverwaltung bereitstellt.
 * Enthält Methoden, um die HISTORY zu speichern {@link #saveHistory()} und zu laden {@link #readHistory()}
 * sowie um einen {@link WindowAdapter} auf dem Hauptfenster zu registrieren {@link #registerMainWindowListener()}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class FileController {

    private final HistoryModel historyModel;
    private final MainView mainView;
    private final HistoryController historyController;
    private final StatisticsController statisticsController;
    private final File historyFile;

    /**
     * Konstruktor der Klasse {@link FileController}.
     * Legt den Dateipfad der Historie fest und liest die Historie beim Programmstart ein {@link #readHistory()}.
     * Registriert den {@link WindowAdapter}, welcher beim Schließen des Programms die HISTORY abspeichert
     * {@link #registerMainWindowListener()}.
     *
     * @param mainView das Hauptfenster des Programms.
     * @param historyModel das Modell, welches die Historie verwaltet.
     * @param historyController der Controller der Historie.
     * @param statisticsController der Controller der Statistikübersicht.
     */

    public FileController(MainView mainView, HistoryModel historyModel,
                          HistoryController historyController, StatisticsController statisticsController) {

        this.historyModel = historyModel;
        this.mainView = mainView;
        this.historyController = historyController;
        this.statisticsController = statisticsController;

        historyFile = new File("data/history.csv");

        registerMainWindowListener();
        readHistory();
    }

    /**
     * Registriert einen {@link WindowAdapter} auf der {@link MainView}, welcher beim Schließen des Programms
     * die HISTORY abspeichert.
     */

    public void registerMainWindowListener(){

        mainView.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveHistory();
            }
        });
    }

    /**
     * Ruft eine Hilfsmethode auf, um die CSV-Datei bei Bedarf zu erstellen.
     * Liest die Historie aus der CSV-Datei ein, prüft diese auf Korrektheit, übergibt sie an das {@link HistoryModel}
     * und aktualisiert die Historien- sowie Statistikansicht.
     * Tritt beim Anlegen oder Lesen der CSV-Datei eine {@link IOException} auf, wird diese intern behandelt
     * und dem Nutzer über eine Fehlermeldung per {@link OptionPane} angezeigt.
     */

    public void readHistory(){

        createCSV();

        try(BufferedReader reader = new BufferedReader(new FileReader(historyFile))){

            reader.readLine();
            String line;

            while((line = reader.readLine()) != null){
                String[] data = line.split(";");

                if (data.length != 19) {
                   new OptionPane("Eine Zeile in der CSV-Datei ist fehlerhaft und wurde übersprungen.",
                           OptionPane.OptionType.WARNING);
                   continue;
                }

                LocalDate date = LocalDate.parse(data[0]);

                String name = data[1];
                MensaLine lineName = MensaLine.valueOf(data[2]);
                float price = Float.parseFloat(data[3]);
                MensaMealType type = MensaMealType.valueOf(data[4]);
                float kcal = Float.parseFloat(data[5]);
                float proteins = Float.parseFloat(data[6]);
                float carbs = Float.parseFloat(data[7]);
                float sugar = Float.parseFloat(data[8]);
                float fat  = Float.parseFloat(data[9]);
                float saturated = Float.parseFloat(data[10]);
                float salt = Float.parseFloat(data[11]);
                short scoreCo2 = Short.parseShort(data[12]);
                short scoreWater = Short.parseShort(data[13]);
                short scoreAnimals = Short.parseShort(data[14]);
                short scoreRainforest = Short.parseShort(data[15]);
                float co2Emissions = Float.parseFloat(data[16]);
                float waterConsumption = Float.parseFloat(data[17]);

                String[] additivesString = data[18].split(",");
                List<String> additives = new ArrayList<>();
                for (String additive : additivesString){
                    additives.add(additive.trim());
                }

                MensaMeal meal = new MensaMeal(name,
                        lineName,
                        price,
                        type,
                        kcal,
                        proteins,
                        carbs,
                        sugar,
                        fat,
                        saturated,
                        salt,
                        scoreCo2,
                        scoreWater,
                        scoreAnimals,
                        scoreRainforest,
                        co2Emissions,
                        waterConsumption,
                        additives);

                historyModel.addToHistory(meal, date);
                historyController.updateHistoryView();
            }
            statisticsController.updateStatisticsView();
        }
        catch (IOException e){
            new OptionPane("Es ist ein Fehler beim Lesen der Historie aufgetreten.",
                    OptionPane.OptionType.ERROR);
        }

    }

    /**
     * Holt sich aus dem {@link HistoryModel} die aktuelle HISTORY und speichert diese in der CSV-Datei ab.
     * Tritt beim Schreiben in die CSV-Datei eine {@link IOException} auf, wird diese intern behandelt und dem Nutzer
     * über eine Fehlermeldung per {@link OptionPane} angezeigt.
     */

    public void saveHistory() {

        ArrayList<HistoryModel.HistoryEntry> HISTORY = historyModel.getHistory();
        String header = HistoryModel.HistoryEntry.getHeaderForCSV();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile))){

            writer.write(header);
            writer.newLine();

            for(HistoryModel.HistoryEntry entry : HISTORY){
                String record = entry.toString();
                writer.write(record);
                writer.newLine();
            }
        }
        catch (IOException e){
            new OptionPane("Es ist ein Fehler beim Schreiben der Historie aufgetreten.",
                    OptionPane.OptionType.ERROR);
        }
    }

    /*
    Erstellt eine CSV-Datei, sofern nicht bereits vorhanden.
     */

    private void createCSV(){
        try {
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }
        }
        catch (IOException e){
            new OptionPane("Es ist ein Fehler beim Anlegen der CSV-Datei aufgetreten.",
                    OptionPane.OptionType.ERROR);
        }
    }

}
