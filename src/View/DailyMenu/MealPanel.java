package View.DailyMenu;

import View.Panel;
import View.Style;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Panel, welches ein Gericht in einer "Zeile" des Speiseplans darstellt. Enthält innere Klassen, die das Panel in die
 * Bereiche Westen, Mitte und Osten unterteilen.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class MealPanel extends JPanel implements View.Panel {

    private MensaMeal meal;

    private CenterPanel centerPanel;
    private EastPanel eastPanel;
    private WestPanel westPanel;

    /**
     * Konstruktor der Klasse {@link MealPanel}.
     * Setzt das Layout.
     *
     * @param meal das Gericht, welches das Panel darstellen soll.
     */
    public MealPanel(MensaMeal meal) {
        setLayout(new BorderLayout());
        this.meal = meal;
    }

    /**
     * Initialisiert das Panel, indem die Unter-Panels erstellt werden.
     */

    public void initialize(){

        westPanel = new WestPanel();
        westPanel.initialize();
        centerPanel = new CenterPanel();
        centerPanel.initialize();
        eastPanel = new EastPanel();
        eastPanel.initialize();

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);

    }

    /**
     * Standard-Getter, welcher eine Referenz auf den "Hinzufügen"-Knopf zurückgibt.
     *
     * @return eine Referenz auf den "Hinzufügen"-Knopf.
     */

    public JButton getAddToHistoryButton() {
        return eastPanel.getAddToHistoryButton();
    }

    /**
     * Standard-Getter, welcher eine Referenz auf den "Zusatzstoffe"-Knopf zurückgibt.
     *
     * @return eine Referenz auf den "Zusatzstoffe"-Knopf.
     */

    public JButton getShowAdditivesButton() {
        return eastPanel.getShowAdditivesButton();
    }

    /*
    Panel, welches die Icons im Westen des MealPanel enthält.
     */

    private class WestPanel extends JPanel implements View.Panel {

        private JLabel iconVeggi, iconVegan, iconMeat;

        /*
        Konstruktor. Setzt das Layout.
         */

        public WestPanel() {
            setLayout(new GridLayout(1,1));
            setBorder(BorderFactory.createEmptyBorder(0,5,0,10));
            setPreferredSize(new Dimension(55, 40));
        }

        /*
        Initialisiert das Panel. Fügt Icons hinzu, welche den Typ des Gerichts repräsentieren.
         */

        @Override
        public void initialize(){

            if(meal.getType().equals(MensaMealType.VEGETARIAN)){
                iconVeggi = new JLabel(new ImageIcon("resources/icons/veggi.png"));
                add(iconVeggi);
            }
            else if(meal.getType().equals(MensaMealType.VEGAN)){
                iconVegan = new JLabel(new ImageIcon("resources/icons/vegan.png"));
                add(iconVegan);
            }
            else if (meal.getType().equals(MensaMealType.PORK) ||
                    meal.getType().equals(MensaMealType.BEEF) ||
                    meal.getType().equals(MensaMealType.MSC)){

                iconMeat = new JLabel(new ImageIcon("resources/icons/meat.png"));
                add(iconMeat);
            }
        }
    }

     /*
    Panel, welches die Daten in der Mitte des MealPanel enthält.
     */

    private class CenterPanel extends JPanel implements View.Panel {

        private JLabel lineLabel, nameLabel, priceLabel;

        /*
        Konstruktor. Setzt das Layout.
         */

        public CenterPanel() {
            setLayout(new GridLayout(1,0));
            setPreferredSize(new Dimension(900,50));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY)
            ));
        }

        /*
        Initialisiert das Panel. Fügt die Daten des Gerichts als Label hinzu.
         */

        @Override
        public void initialize(){
            DecimalFormat df = new DecimalFormat("0.00");

            lineLabel = new JLabel(meal.getLine().toString(), JLabel.LEFT);
            lineLabel.setFont(Style.MAIN_FONT);
            lineLabel.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
            nameLabel = new JLabel(meal.getName());
            nameLabel.setFont(Style.MAIN_FONT);
            nameLabel.setToolTipText(meal.getName());
            priceLabel = new JLabel(df.format(meal.getPrice()) + " €", JLabel.CENTER);
            priceLabel.setFont(Style.MAIN_FONT);

            add(lineLabel);
            add(nameLabel);
            add(priceLabel);

        }
    }

     /*
    Panel, welches die Knöpfe im Osten des MealPanel enthält.
     */

    private class EastPanel extends JPanel implements Panel {

        private JButton addToHistoryButton, showAdditivesButton;

        /*
        Konstruktor. Setzt das Layout.
         */

        public EastPanel() {
            setLayout(new GridLayout(1,2));
            setPreferredSize(new Dimension(200,50));
        }

        /*
        Initialisiert das Panel. Fügt die Knöpfe "Hinzufügen" und "Zusatzstoffe" hinzu.
         */

        @Override
        public void initialize(){
            addToHistoryButton = new JButton("Hinzufügen");
            addToHistoryButton.setFont(Style.MAIN_FONT);
            addToHistoryButton.setToolTipText("Fügt das Gericht der Historie hinzu");

            showAdditivesButton = new JButton("Zusatzstoffe");
            showAdditivesButton.setFont(Style.MAIN_FONT);
            showAdditivesButton.setToolTipText("Zeigt die Zusatzstoffe für das Gericht an");

            add(addToHistoryButton);
            add(showAdditivesButton);
        }

        /*
        Standard Getter.
         */

        public JButton getAddToHistoryButton() {
            return addToHistoryButton;
        }

        /*
        Standard Getter.
         */

        public JButton getShowAdditivesButton() {
            return showAdditivesButton;
        }
    }

    /**
     * Standard-Getter, welcher das im {@link MealPanel} gespeicherte {@link MensaMeal}-Gericht zurückgibt.
     *
     * @return das im Panel gespeicherte Gericht.
     */

    public MensaMeal getMeal() {
        return meal;
    }

}
