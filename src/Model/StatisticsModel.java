package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Modell, welches auf die Daten des {@link HistoryModel} zugreift und damit statistische Berechnungen durchführt.
 * Enthält Methoden, um diese Berechnungen durchzuführen und nach außen zu geben.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class StatisticsModel {

    private final HistoryModel historyModel;
    private final ArrayList<HistoryModel.HistoryEntry> HISTORY;

    /**
     * Konstruktor der Klasse {@link StatisticsModel}.
     *
     * @param historyModel das Modell der Historie.
     */

    public StatisticsModel(HistoryModel historyModel) {
        this.historyModel = historyModel;
        HISTORY = historyModel.getHistory();
    }

    /*
    Aggregiert für jedes Gericht aus der HISTORY eine spezifizierte Kennzahl auf.
     */

    private float calculateCumulativeSum(CalculationType calculationType) {
        if (HISTORY.isEmpty()) return 0;
        float sum = 0f;

        for (HistoryModel.HistoryEntry entry : HISTORY) {
            MensaMeal meal = entry.getMeal();
            switch (calculationType) {
                case PRICE -> sum += meal.getPrice();
                case KCAL -> sum += meal.getKcal();
                case PROTEINS -> sum += meal.getProteins();
                case CARBS -> sum += meal.getCarbs();
                case SUGAR -> sum += meal.getSugar();
                case FAT -> sum += meal.getFat();
                case SATURATED -> sum += meal.getSaturated();
                case SALT -> sum += meal.getSalt();
            }
        }
        return sum;
    }

    /*
    Aggregiert für jedes Gericht aus der HISTORY einen spezifizierten Scorewert auf.
     */

    private float calculateCumulativeScore(SustainabilityScoreType scoreType) {
        if (HISTORY.isEmpty()) return 0;
        float sum = 0f;

        for (HistoryModel.HistoryEntry entry : HISTORY) {
            MensaMeal meal = entry.getMeal();
            switch (scoreType) {
                case SCORECO2 -> sum += meal.getScoreCo2();
                case SCOREWATER  -> sum += meal.getScoreWater();
                case SCOREANIMALS-> sum += meal.getScoreAnimals();
                case SCORERAINFOREST -> sum += meal.getScoreRainforest();
                case CO2EMISSIONS-> sum += meal.getCo2Emissions();
                case WATERCONSUMPTION-> sum += meal.getWaterConsumption();
            }
        }
        return sum;
    }

    /*
    Berechnet den Durchschnitt einer Kennzahl, bezogen auf die Gesamtanzahl der in der HISTORY vorkommenden Gerichte.
     */

    private float calculateTotalAverage(CalculationType calculationType) {
        if (HISTORY.isEmpty()) return 0;
        return calculateCumulativeSum(calculationType) / HISTORY.size();
    }

    /*
    Berechnet den Durchschnitt einer Kennzahl, bezogen auf die Gesamtanzahl der in der HISTORY vorkommenden,
    einzigartigen Tagen.
     */

    private float calculateDailyAverage(CalculationType calculationType) {
        if (HISTORY.isEmpty()) return 0;
        HashSet<LocalDate> uniqueDays = new HashSet<>();

        for(HistoryModel.HistoryEntry entry : HISTORY) {
            uniqueDays.add(entry.getDate());
        }
        return calculateCumulativeSum(calculationType) / uniqueDays.size();
    }

    /*
    Berechnet den Durchschnitt eines Scores, bezogen auf die Gesamtanzahl der in der HISTORY vorkommenden Gerichte,
    welche einen Score hinterlegt haben.
     */

    private float calculateAverageScore(SustainabilityScoreType scoreType) {
        if(HISTORY.isEmpty()) return 0;
        int size = 0;

        for (HistoryModel.HistoryEntry entry : HISTORY) {
            MensaMeal meal = entry.getMeal();
            switch (scoreType) {
                case SCORECO2 -> {if(meal.getScoreCo2() != 0){size++;}}
                case SCOREWATER  -> {if(meal.getScoreWater() != 0){size++;}}
                case SCOREANIMALS-> {if(meal.getScoreAnimals() != 0){size++;}}
                case SCORERAINFOREST -> {if(meal.getScoreRainforest() != 0){size++;}}
                case CO2EMISSIONS-> {if(meal.getCo2Emissions() != 0){size++;}}
                case WATERCONSUMPTION-> {if(meal.getWaterConsumption() != 0){size++;}}
            }
        }
        if(size == 0){
            return 0;
        }
        else{
            return calculateCumulativeScore(scoreType)/ size;
        }

    }

    /*
    Berechnet den Veggie-Anteil. Hinweis: Die Methode liefert einen Dezimalwert und keine Prozentzahl zurück!
     */

    private float calculateVeggieRatio(){
        int counterVeggi = 0;
        int counterNonVeggi = 0;

        for (HistoryModel.HistoryEntry entry : HISTORY) {
            MensaMeal meal = entry.getMeal();

            if(meal.getType().equals(MensaMealType.VEGAN) || meal.getType().equals(MensaMealType.VEGETARIAN)){
                counterVeggi++;
            }
            else {
                counterNonVeggi++;
            }
        }
        return (float) counterVeggi / (float) (counterNonVeggi+counterVeggi);
    }

    /*
    Zählt, wie oft ein bestimmter Gerichts-Typ in der HISTORY vorkommt.
     */

    private int countMealType(MensaMealType mealType) {
        int count = 0;
        for (HistoryModel.HistoryEntry entry : HISTORY) {
            if (entry.getMeal().getType().equals(mealType)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Berechnet die kumulative Summe eines spezifizierten {@link CalculationType} aus allen Gerichten in der HISTORY
     * und gibt diese zurück.
     *
     * @param calculationType Typ des Wertes, dessen kumulative Summe berechnet werden soll.
     * @return die kumulative Summe.
     */

    public float getCumulativeSum(CalculationType calculationType) {
        return calculateCumulativeSum(calculationType);
    }

    /**
     * Berechnet den Gesamtdurchschnitt eines spezifizierten {@link CalculationType} aus allen Gerichten in der HISTORY
     * und gibt diesen zurück. Hinweis: Als Divisor der Berechnung wird dabei die Gesamtanzahl der in der HISTORY
     * auftretenden Gerichte verwendet.
     *
     * @param calculationType Typ des Wertes, dessen Gesamtdurchschnitt berechnet werden soll.
     * @return den Gesamtdurchschnitt.
     */

    public float getTotalAverage(CalculationType calculationType) {
        return calculateTotalAverage(calculationType);
    }

    /**
     * Berechnet den Tagesdurchschnitt eines spezifizierten {@link CalculationType} aus allen Gerichten in der HISTORY
     * und gibt diesen zurück. Hinweis: Als Divisor der Berechnung wird dabei die Gesamtanzahl der in der HISTORY
     * auftretenden, einzigartigen Tage verwendet.
     *
     * @param calculationType Typ des Wertes, dessen Tagesdurchschnitt berechnet werden soll.
     * @return den Tagesdurchschnitt.
     */

    public float getDailyAverage(CalculationType calculationType) {
        return calculateDailyAverage(calculationType);
    }

    /**
     * Berechnet den Durchschnitt aller Werte eines spezifizierten {@link SustainabilityScoreType}
     * und gibt diesen zurück.
     *
     * @param scoreType Typ des Scores, dessen Durchschnitt berechnet werden soll.
     * @return den durchschnittlichen Score.
     */

    public float getAverageScore(SustainabilityScoreType scoreType) {
        return calculateAverageScore(scoreType);
    }

    /**
     * Berechnet, wie oft der übergebene {@link MensaMealType} in der HISTORY vorkommt und gibt die Anzahl zurück.
     *
     * @param mealType Typ des Gerichts.
     * @return Anzahl, wie oft dieser Typ in der Historie vorkommt.
     */

    public int getMealTypeCount(MensaMealType mealType) {
        return countMealType(mealType);
    }

    /**
     * Berechnet den Veggie-Anteil, d.h. das Verhältnis aus veganen/vegetarischen Gerichten zu allen Gerichten
     * in der HISTORY und gibt diesen zurück. Hinweis: Gerichte, deren Typ unbekannt ist, werden dennoch zur Berechnung
     * der Gesamtzahl herangezogen.
     *
     * @return Veggie-Anteil als Dezimalzahl.
     */

    public float getVeggieRatio() {
        return calculateVeggieRatio();
    }

}
