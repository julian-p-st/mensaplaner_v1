package Model;

import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

/**
 * Enumeration, welche die statistisch im {@link StatisticsModel} auswertbaren Score-Werte eines
 * {@link MensaMeal}-Gerichts als Konstanten enthält. Außerdem werden Methoden bereitgestellt,
 * um eine String-Darstellung des Score-Typs zu erhalten {@link #getTypeToString(SustainabilityScoreType)})}
 * und um die jeweilige Einheit abzufragen {@link #getTypeUnit(SustainabilityScoreType)}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public enum SustainabilityScoreType {

    SCORECO2, SCOREWATER, SCOREANIMALS, SCORERAINFOREST, CO2EMISSIONS, WATERCONSUMPTION;

    /**
     * Liefert eine String-Darstellung des übergebenen {@link SustainabilityScoreType} zurück.
     *
     * @param type der {@link SustainabilityScoreType}, dessen String-Darstellung zurückgegeben werden soll.
     * @return eine String-Darstellung.
     */

    public static String getTypeToString(SustainabilityScoreType type) {
        return switch (type){
            case SCORECO2: yield "CO2-Score";
            case SCOREWATER: yield "Wasser-Score";
            case SCOREANIMALS: yield "Tierwohl-Score";
            case SCORERAINFOREST: yield "Regenwald-Score";
            case CO2EMISSIONS: yield "CO2-Emissionen";
            case WATERCONSUMPTION: yield "Wasserverbrauch";
        };
    }

    /**
     * Liefert die Einheit eines übergebenen {@link SustainabilityScoreType} als String-Darstellung zurück.
     *
     * @param type der {@link SustainabilityScoreType}, dessen Einheit als String-Darstellung zurückgegeben werden soll.
     * @return die Einheit als String-Darstellung.
     */

    public static String getTypeUnit(SustainabilityScoreType type) {
        return switch (type){
            case SCORECO2, SCOREWATER, SCOREANIMALS, SCORERAINFOREST: yield "*";
            case CO2EMISSIONS: yield "g";
            case WATERCONSUMPTION: yield "l";
        };
    }

}
