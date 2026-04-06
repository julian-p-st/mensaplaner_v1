package Controller;

import Model.MensaModel;
import View.OptionPane;
import View.MainView;
import View.DailyMenu.DatePanel;
import View.DailyMenu.MealPanel;
import edu.kit.aifb.atks.mensascraper.lib.MensaLocation;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import edu.kit.aifb.atks.mensascraper.lib.MensaScraperException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Controller, der die Logik für die Speiseplanübersicht bereitstellt.
 * Enthält Methoden, um {@link ActionListener} zu registrieren
 * ({@link #registerRefreshMenuListener()}, {@link #registerShowAdditivesButton(MealPanel)})
 * und um die DailyMenuView basierend auf dem gewähltem Datum zu aktualisieren {@link #updateDailyMenuView()}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class DailyMenuController {

    private final MainView mainView;
    private final MensaModel mensaModel;
    private final HistoryController historyController;
    private LocalDate currentDate;

    /**
     * Konstruktor der Klasse {@link DailyMenuController}.
     * Registriert den "Speiseplan aktualisieren"-Button {@link #registerRefreshMenuListener()}.
     * Lädt den Speiseplan einmalig beim Start des Programms.
     *
     * @param mainView das Hauptfenster des Programms.
     * @param mensaModel das Modell, welches die KITMensaScraper-API verwaltet.
     * @param historyController der Controller der Historie.
     */

    public DailyMenuController(MainView mainView, MensaModel mensaModel, HistoryController historyController) {
        this.mainView = mainView;
        this.mensaModel = mensaModel;
        this.historyController = historyController;

        registerRefreshMenuListener();
        mainView.getDailyMenuPanel().getDatePanel().getRefreshMenuButton().doClick();

    }

    /**
     * Registriert einen {@link ActionListener} für den "Speiseplan aktualisieren"-Button und aktualisiert die View
     * basierend auf dem gewählten Datum.
     */

    public void registerRefreshMenuListener() {
        DatePanel datePanel = mainView.getDailyMenuPanel().getDatePanel();
        datePanel.getRefreshMenuButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Aktuelles Datum holen:
                Date selectedDate = datePanel.getSelectedDate();
                currentDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                //Die Meals in der View setzen
                updateDailyMenuView();
            }
        });
    }

    /**
     * Registriert einen {@link ActionListener} auf dem "Zusatzstoffe"-Button eines {@link MealPanel},
     * um die enthaltenen Zusatzstoffe des {@link MensaMeal}-Gerichts anzuzeigen.
     *
     * @param mealPanel eine "Zeile" in der Speiseplanübersicht.
     */

    public void registerShowAdditivesButton(MealPanel mealPanel) {

        mealPanel.getShowAdditivesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> additives = mealPanel.getMeal().getAdditives();
                StringBuilder message = new StringBuilder("Enthaltene Zusatzstoffe: ");
                for (int i = 0; i < additives.size(); i++) {
                    message.append(additives.get(i));
                    if (i < additives.size() - 1) {
                        message.append(", ");
                    }
                }
                new OptionPane(message.toString(), OptionPane.OptionType.INFORMATION);
            }
        });
    }

    /**
     * Aktualisiert die Speiseplanübersicht, indem das Tagesmenü geladen und aus jedem {@link MensaMeal}-Gericht ein
     * {@link MealPanel} erzeugt wird.
     * Tritt beim Laden des Tagesmenüs eine {@link MensaScraperException} auf, wird diese intern behandelt
     * und dem Nutzer über eine Fehlermeldung per {@link OptionPane} angezeigt.
     */

    public void updateDailyMenuView() {
        try {
            List<MensaMeal> dailyMenu = mensaModel.getMeals(MensaLocation.ADENAUERRING, currentDate);

            if (dailyMenu.isEmpty()) {
                new OptionPane("Keine Daten für diesen Tag vorhanden.", OptionPane.OptionType.INFORMATION);
            }
            else {
                mainView.getDailyMenuPanel().getMealListPanel().removeAll();
                for (MensaMeal meal : dailyMenu) {
                    MealPanel mealPanel = new MealPanel(meal);
                    mealPanel.initialize();

                    historyController.registerAddButton(mealPanel, currentDate);
                    registerShowAdditivesButton(mealPanel);

                    mainView.getDailyMenuPanel().getMealListPanel().add(mealPanel);
                }
                mainView.getDailyMenuPanel().getMealListPanel().revalidate();
                mainView.getDailyMenuPanel().getMealListPanel().repaint();

            }
        }
        catch (MensaScraperException e) {
            new OptionPane("Es können keine Daten aus der Vergangenheit abgerufen werden.", OptionPane.OptionType.ERROR);
        }
    }

}


