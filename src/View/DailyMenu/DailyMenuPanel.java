package View.DailyMenu;

import View.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Haupt-Speiseplan-Panel, welches den Speiseplan darstellt und in das die Unter-Panels eingebettet sind.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class DailyMenuPanel extends JPanel implements Panel {

    private DatePanel datePanel;
    private JScrollPane scrollPane;
    private MealListPanel mealListPanel;

    /**
     * Konstruktor der Klasse {@link DailyMenuPanel}.
     * Setzt das Layout.
     */

    public DailyMenuPanel() {
        setLayout(new BorderLayout());
    }

    /**
     * Initialisiert das Panel, indem es die Unterkomponenten erstellt und dem Panel hinzufügt.
     */

    @Override
    public void initialize(){

        datePanel = new DatePanel();
        datePanel.initialize();
        add(datePanel, BorderLayout.NORTH);

        mealListPanel = new MealListPanel();
        mealListPanel.initialize();
        scrollPane = new JScrollPane(mealListPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Standard-Getter, welcher eine Referenz auf das {@link DatePanel} zurückliefert.
     *
     * @return eine Referenz auf das DatePanel-Objekt.
     */

    public DatePanel getDatePanel() {
        return datePanel;
    }

    /**
     * Standard-Getter, welcher eine Referenz auf das {@link MealListPanel} zurückliefert.
     *
     * @return eine Referenz auf das MealListPanel-Objekt.
     */

    public MealListPanel getMealListPanel() {
        return mealListPanel;
    }

}
