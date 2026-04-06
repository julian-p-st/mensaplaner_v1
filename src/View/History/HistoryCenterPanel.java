package View.History;

import View.Panel;

import javax.swing.*;

/**
 * Panel, welches mittig im {@link HistoryPanel} eingebettet ist und in dem die {@link HistoryListPanel}
 * eingefügt werden.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryCenterPanel extends JPanel implements Panel {

    /**
     * Konstruktor der Klasse {@link HistoryCenterPanel}.
     * Setzt das Layout.
     */

    public HistoryCenterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Initialisiert das Panel.
     */

    @Override
    public void initialize(){}

}
