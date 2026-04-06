package View.Statistics;

import View.Panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Abstrakte Klasse, welche ein gemeinsames Layout für die "Einträge" (die einzelnen Panels, die die
 * statistischen Kennzahlen enthalten) bereitstellt.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public abstract class StatisticsEntryPanel extends JPanel implements Panel {

    /**
     * Konstruktor der Klasse {@link StatisticsEntryPanel}.
     * Setzt das Layout.
     *
     * @param borderName Name, mit dem das Panel innerhalb seiner Border beschriftet werden soll.
     */

    public StatisticsEntryPanel(String borderName) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.LIGHT_GRAY), borderName, TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
    }

}
