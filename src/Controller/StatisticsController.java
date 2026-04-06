package Controller;

import Model.CalculationType;
import Model.StatisticsModel;
import Model.SustainabilityScoreType;
import View.MainView;
import View.Statistics.AveragesPanel;
import View.Statistics.CumulativesPanel;
import View.Statistics.ProfilePanel;
import View.Statistics.SustainabilityPanel;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Controller, der die Logik für die Statistikübersicht bereitstellt.
 * Enthält Methoden, um {@link ItemListener} zu registrieren ({@link #registerChangeCalcComboBox(AveragesPanel)}),
 * Daten aus {@link StatisticsModel} zu holen ({@link #fetchStatisticsData()}) sowie damit die View zu aktualisieren
 * ({@link #updateStatisticsView()}, {@link #updateAveragesView()}, {@link #updateCumulativesView()},
 * {@link #updateProfileView()}, {@link #updateSustainabilityView()}).
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class StatisticsController {

    private final MainView mainView;
    private final StatisticsModel statisticsModel;
    private final AveragesPanel averagesPanel;
    private final CumulativesPanel cumulativesPanel;
    private final ProfilePanel profilePanel;
    private final SustainabilityPanel sustainabilityPanel;

    private ArrayList<Float> totalAverages;
    private ArrayList<Float> dailyAverages;
    private ArrayList<Float> cumulatives;
    private ArrayList<Integer> mealTypeCount;
    private ArrayList<Float> averageScores;
    private float veggieRatio;

    /**
     * Konstruktor der Klasse {@link StatisticsController}.
     * Registriert die {@link JComboBox} durch {@link #registerChangeCalcComboBox(AveragesPanel)},
     * womit sich die Berechnungsgrundlage der Durchschnittsberechnung wählen lässt.
     *
     * @param mainView das Hauptfenster des Programms.
     * @param statisticsModel das Modell, welches statistische Daten enthält und auswertet.
     */

    public StatisticsController(MainView mainView, StatisticsModel statisticsModel) {
        this.mainView = mainView;
        this.statisticsModel = statisticsModel;

        averagesPanel = mainView.getStatisticsPanel().getStatisticsGridPanel().getAveragesPanel();
        registerChangeCalcComboBox(averagesPanel);
        cumulativesPanel = mainView.getStatisticsPanel().getStatisticsGridPanel().getCumulativesPanel();
        profilePanel = mainView.getStatisticsPanel().getStatisticsGridPanel().getProfilePanel();
        sustainabilityPanel = mainView.getStatisticsPanel().getStatisticsGridPanel().getSustainabilityPanel();
    }

    /**
     * Registriert einen {@link ItemListener} auf der {@link JComboBox} des {@link AveragesPanel}.
     * Aktualisiert die Statistikübersicht durch {@link #updateStatisticsView()}.
     *
     * @param averagesPanel das Panel in der Statistikübersicht, welches die Durchschnittswerte anzeigt.
     */

    public void registerChangeCalcComboBox(AveragesPanel averagesPanel) {
        JComboBox<String> changeCalcComboBox = averagesPanel.getHeaderPanel().getChangeCalcComboBox();
        changeCalcComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateStatisticsView();
                }
            }
        });
    }

    /**
     * Holt die statistischen Daten aus dem {@link StatisticsModel} und speichert diese.
     */

    public void fetchStatisticsData() {
        totalAverages = new ArrayList<>();
        dailyAverages = new ArrayList<>();
        cumulatives = new ArrayList<>();
        mealTypeCount = new ArrayList<>();
        averageScores = new ArrayList<>();

        for(CalculationType type : CalculationType.values()) {
            totalAverages.add(statisticsModel.getTotalAverage(type));
        }
        for (CalculationType type : CalculationType.values()) {
            dailyAverages.add(statisticsModel.getDailyAverage(type));
        }
        for (CalculationType type : CalculationType.values()) {
            cumulatives.add(statisticsModel.getCumulativeSum(type));
        }
        for(MensaMealType type : MensaMealType.values()) {
            mealTypeCount.add(statisticsModel.getMealTypeCount(type));
        }
        for (SustainabilityScoreType type : SustainabilityScoreType.values()) {
            averageScores.add(statisticsModel.getAverageScore(type));
        }
        veggieRatio = statisticsModel.getVeggieRatio();
    }

    /**
     * Aktualisiert die Statistikübersicht mit aktuellen Werten aus {@link StatisticsModel}.
     * Ruft dazu alle vier Teilbereiche der Ansicht über Hilfsmethoden auf.
     */

    public void updateStatisticsView() {
        fetchStatisticsData();
        updateAveragesView();
        updateCumulativesView();
        updateProfileView();
        updateSustainabilityView();
    }

    /*
    Updatet die Durchschnittswerte.
     */

    private void updateAveragesView() {
        averagesPanel.setTotalAverages(totalAverages);
        averagesPanel.setDailyAverages(dailyAverages);

        AveragesPanel.ContentPanel contentPanelAvg =
                mainView.getStatisticsPanel().getStatisticsGridPanel().getAveragesPanel().getContentPanel();

        contentPanelAvg.removeAll();
        contentPanelAvg.initialize();
        contentPanelAvg.repaint();
        contentPanelAvg.revalidate();
    }

    /*
    Updatet die Gesamtwerte.
     */

    private void updateCumulativesView() {
        cumulativesPanel.setCumulatives(cumulatives);

        CumulativesPanel.ContentPanel contentPanelCum =
                mainView.getStatisticsPanel().getStatisticsGridPanel().getCumulativesPanel().getContentPanel();

        contentPanelCum.removeAll();
        contentPanelCum.initialize();
        contentPanelCum.repaint();
        contentPanelCum.revalidate();
    }

    /*
    Updatet das Ernährungsprofil.
     */

    private void updateProfileView(){
        profilePanel.setMealTypeCount(mealTypeCount);
        profilePanel.setVeggieRatio(veggieRatio);

        ProfilePanel.ContentPanel contentPanelProf =
                mainView.getStatisticsPanel().getStatisticsGridPanel().getProfilePanel().getContentPanel();

        contentPanelProf.removeAll();
        contentPanelProf.initialize();
        contentPanelProf.repaint();
        contentPanelProf.revalidate();
    }

    /*
    Updatet die Nachhaltigkeitsübersicht.
     */

    private void updateSustainabilityView(){
        sustainabilityPanel.setAverageScores(averageScores);

        SustainabilityPanel.ContentPanel contentPanelSus =
                mainView.getStatisticsPanel().getStatisticsGridPanel().getSustainabilityPanel().getContentPanel();

        contentPanelSus.removeAll();
        contentPanelSus.initialize();
        contentPanelSus.repaint();
        contentPanelSus.revalidate();
    }

}
