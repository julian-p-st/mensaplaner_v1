package View.Statistics;

import View.Panel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Panel, welches die Statistikübersicht in eine Gitterstruktur unterteilt und die vier {@link StatisticsEntryPanel}
 * aufnimmt.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class StatisticsGridPanel extends JPanel implements Panel {

    private AveragesPanel averagesPanel;
    private CumulativesPanel cumulativesPanel;
    private ProfilePanel profilePanel;
    private SustainabilityPanel sustainabilityPanel;

    /**
     * Konstruktor der Klasse {@link StatisticsGridPanel}.
     * Setzt das Layout.
     */

    public StatisticsGridPanel() {
        setLayout(new GridLayout(2, 2, 20,20));
        setBorder(BorderFactory.createEmptyBorder(10,7,18,7));
    }

    /**
     * Klassenmethode, um eine Dezimalzahl zu formatieren.
     *
     * @param data die zu formatierende Dezimalzahl.
     * @return die formatierte Zahle als String.
     */

    public static String format (float data){
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(data);
    }

    /**
     * Initialisiert das Panel, indem die vier {@link StatisticsEntryPanel} bzw. Unter-Panels der Statistikübersicht
     * erstellt werden.
     */

    @Override
    public void initialize(){
        averagesPanel = new AveragesPanel();
        averagesPanel.initialize();
        add(averagesPanel);

        cumulativesPanel = new CumulativesPanel();
        cumulativesPanel.initialize();
        add(cumulativesPanel);

        profilePanel = new ProfilePanel();
        profilePanel.initialize();
        add(profilePanel);

        sustainabilityPanel = new SustainabilityPanel();
        sustainabilityPanel.initialize();
        add(sustainabilityPanel);
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link AveragesPanel} zu erhalten.
     *
     * @return eine Referenz auf das Durchschnittswerte-Panel.
     */

    public AveragesPanel getAveragesPanel() {
        return averagesPanel;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link CumulativesPanel} zu erhalten.
     *
     * @return eine Referenz auf das Gesamtwerte-Panel.
     */

    public CumulativesPanel getCumulativesPanel() {
        return cumulativesPanel;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link ProfilePanel} zu erhalten.
     *
     * @return eine Referenz auf das Ernährungsprofil-Panel.
     */

    public ProfilePanel getProfilePanel() {
        return profilePanel;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link SustainabilityPanel} zu erhalten.
     *
     * @return eine Referenz auf das Nachhaltigkeit-Panel.
     */

    public SustainabilityPanel getSustainabilityPanel() {
        return sustainabilityPanel;
    }

}
