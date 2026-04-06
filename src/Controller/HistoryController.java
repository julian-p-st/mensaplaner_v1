package Controller;

import Model.HistoryModel;
import View.History.HistoryListEntryPanel;
import View.History.HistoryListPanel;
import View.DailyMenu.MealPanel;
import View.MainView;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Controller, der die Logik für die Historienverwaltung bereitstellt.
 * Enthält Methoden, um {@link ActionListener} zu registrieren
 * ({@link #registerAddButton(MealPanel, LocalDate)}, {@link #registerRemoveButton(HistoryListEntryPanel)})
 * und um die HistoryView zu aktualisieren {@link #updateHistoryView()}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryController {

    private final HistoryModel historyModel;
    private final MainView mainView;
    private final StatisticsController statisticsController;

    /**
     * Konstruktor der Klasse {@link HistoryController}.
     *
     * @param mainView das Hauptfenster des Programms.
     * @param historyModel das Modell, welches die Historie verwaltet.
     * @param statisticsController der Controller der Statistikübersicht.
     */

    public HistoryController(MainView mainView, HistoryModel historyModel, StatisticsController statisticsController) {
        this.mainView = mainView;
        this.historyModel = historyModel;
        this.statisticsController = statisticsController;
    }

    /**
     * Registriert einen {@link ActionListener} auf dem "Hinzufüge"-Button eines {@link MealPanel}, welcher ein
     * {@link MensaMeal}-Gericht der HISTORY hinzufügt und die View aktualisiert.
     *
     * @param mealPanel eine "Zeile" in der Speiseplanübersicht.
     * @param currentDate das aktuell eingestellte Datum.
     */

    public void registerAddButton(MealPanel mealPanel, LocalDate currentDate){
        mealPanel.getAddToHistoryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyModel.addToHistory(mealPanel.getMeal(), currentDate);
                updateHistoryView();
                statisticsController.updateStatisticsView();
            }
        });
    }

    /**
     * Registriert einen {@link ActionListener} auf dem "Entfernen" Button eines {@link HistoryListEntryPanel},
     * welcher einen Eintrag aus der HISTORY entfernt und die View aktualisiert.
     *
     * @param historyListEntryPanel eine "Zeile" innerhalb der Historienansicht.
     */

    public void registerRemoveButton(HistoryListEntryPanel historyListEntryPanel) {
        historyListEntryPanel.getRemoveFromHistoryButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyModel.removeFromHistory(historyListEntryPanel.getMeal(), historyListEntryPanel.getDate()) ;
                updateHistoryView();
                statisticsController.updateStatisticsView();
            }
        });
    }

    /**
     * Aktualisiert die Historienansicht, indem für jedes in der HISTORY vorkommende Datum ein
     * eigenes {@link HistoryListPanel} erstellt wird, welches anschließend mit {@link HistoryListEntryPanel}-Einträgen
     * befüllt wird.
     * Somit erscheinen die {@link Model.HistoryModel.HistoryEntry} der HISTORY als einzelne "Zeilen"
     * nach Datum gruppiert in der Historienansicht.
     */

    public void updateHistoryView() {
        HistoryListPanel currentHistoryListPanel = null;
        LocalDate currentDate = null;
        mainView.getHistoryPanel().getHistoryCenterPanel().removeAll();

        for (HistoryModel.HistoryEntry entry : historyModel.getHistory()) {
            LocalDate entryDate = entry.getDate();

            if (currentDate == null || !currentDate.equals(entryDate)) {
                currentHistoryListPanel = new HistoryListPanel(entryDate);
                currentHistoryListPanel.initialize();
                mainView.getHistoryPanel().getHistoryCenterPanel().add(currentHistoryListPanel);
                currentDate = entryDate;
            }

            registerRemoveButton(currentHistoryListPanel.createHistoryListEntryPanel(entry));
        }

        mainView.getHistoryPanel().getHistoryCenterPanel().revalidate();
        mainView.getHistoryPanel().getHistoryCenterPanel().repaint();

    }

}
