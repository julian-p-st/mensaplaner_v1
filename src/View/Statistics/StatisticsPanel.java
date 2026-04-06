package View.Statistics;

import View.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Haupt-Statistik-Panel, welches die Statistikübersicht darstellt und in das die Unter-Panels eingebettet sind.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class StatisticsPanel extends JPanel implements Panel {

    private StatisticsGridPanel statisticsGridPanel;

    /**
     * Konstruktor der Klasse {@link StatisticsPanel}.
     * Setzt das Layout.
     */

    public StatisticsPanel() {
        setLayout(new BorderLayout());
    }

    /**
     * Initialisiert das Panel, indem das {@link StatisticsGridPanel} angelegt wird, welches dann die vier
     * {@link StatisticsEntryPanel} enthält.
     */

    @Override
    public void initialize(){
        statisticsGridPanel = new StatisticsGridPanel();
        statisticsGridPanel.initialize();
        add(statisticsGridPanel, BorderLayout.CENTER);
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link StatisticsGridPanel} zu erhalten.
     *
     * @return eine Referenz auf das Statistikgitter-Panel
     */

    public StatisticsGridPanel getStatisticsGridPanel() {
        return statisticsGridPanel;
    }

}
