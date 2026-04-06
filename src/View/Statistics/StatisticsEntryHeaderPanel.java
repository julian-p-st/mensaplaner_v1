package View.Statistics;

import View.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Abstrakte Klasse, welche ein gemeinsames Layout für die Kopfzeile der "Einträge" (die einzelnen Panels, die die
 * statistischen Kennzahlen enthalten) bereitstellt.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public abstract class StatisticsEntryHeaderPanel extends JPanel implements Panel {

    /**
     * Konstruktor der Klasse {@link StatisticsEntryHeaderPanel}.
     * Setzt das Layout.
     */

    public StatisticsEntryHeaderPanel() {
        setLayout(new GridLayout(1,3));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

}
