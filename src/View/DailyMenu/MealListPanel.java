package View.DailyMenu;

import View.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel, in das die einzelnen "Zeilen" aus Gerichten eingebettet sind.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class MealListPanel extends JPanel implements Panel {

    /**
     * Konstruktor der Klasse {@link MealListPanel}.
     * Setzt das Layout.
     */

    public MealListPanel(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
    }

    /**
     * Initialisiert das Panel.
     */

    @Override
    public void initialize(){}

}
