package View.History;

import View.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Haupt-Historien-Panel, welches die Historie darstellt und in das die Unter-Panels eingebettet sind.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryPanel extends JPanel implements Panel {

    private HistoryCenterPanel historyCenterPanel;
    private JScrollPane scrollPane;
    private HistoryHeaderPanel historyHeaderPanel;

    /**
     * Konstruktor der Klasse {@link HistoryPanel}.
     * Setzt das Layout.
     */

    public HistoryPanel() {
        setLayout(new BorderLayout());
    }

    /**
     * Initialisiert das Panel, indem das {@link HistoryHeaderPanel} und das {@link HistoryCenterPanel} erstellt werden.
     */

    @Override
    public void initialize() {
        historyHeaderPanel = new HistoryHeaderPanel();
        historyHeaderPanel.initialize();
        add(historyHeaderPanel, BorderLayout.NORTH);

        historyCenterPanel = new HistoryCenterPanel();
        historyCenterPanel.initialize();
        scrollPane = new JScrollPane(historyCenterPanel);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0,0,0, Color.BLACK));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link HistoryCenterPanel} zu erhalten.
     *
     * @return eine Referenz auf das zentrale Historien-Panel.
     */

    public HistoryCenterPanel getHistoryCenterPanel() {
        return historyCenterPanel;
    }

}

