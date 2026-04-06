package Controller;

import Model.HistoryModel;
import Model.MensaModel;
import Model.StatisticsModel;
import View.MainView;

/**
 * Controller, der die Hauptlogik des Programms bereitstellt.
 * Steuert den Programmablauf über Initialisierung der anderen Controller
 * innerhalb seines Konstruktors.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class MainController {

    private final MainView mainView;
    private final HistoryModel historyModel;
    private final MensaModel mensaModel;
    private final MenuController menuController;
    private final StatisticsModel statisticsModel;
    private final HistoryController historyController;
    private final DailyMenuController dailyMenuController;
    private final FileController fileController;
    private final StatisticsController statisticsController;

    /**
     * Konstruktor der Klasse {@link MainController}.
     * Erzeugt die MainView, die Modelle, sowie die anderen Controller.
     */

    public MainController() {

        mainView = new MainView();
        mensaModel = new MensaModel();
        historyModel = new HistoryModel();
        statisticsModel = new StatisticsModel(historyModel);
        mainView.setVisible(true);

        menuController = new MenuController(mainView);
        statisticsController = new StatisticsController(mainView, statisticsModel);
        historyController = new HistoryController(mainView, historyModel, statisticsController);
        fileController = new FileController(mainView, historyModel, historyController, statisticsController);
        dailyMenuController = new DailyMenuController(mainView, mensaModel, historyController);
    }

}
