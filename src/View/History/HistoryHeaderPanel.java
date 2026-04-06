package View.History;

import View.Panel;
import View.Style;

import javax.swing.*;
import java.awt.*;

/**
 * Panel, welches die Kopfzeile in der Historie darstellt.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryHeaderPanel extends JPanel implements Panel {

    WestPanel westPanel;
    CenterPanel centerPanel;
    EastPanel eastPanel;

    /**
     * Konstruktor der Klasse {@link HistoryHeaderPanel}.
     * Setzt das Layout.
     */

    public HistoryHeaderPanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 50));
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5,25 ));
    }

    /**
     * Initialisiert das Panel, in dem die Unter-Panels erstellt werden.
     */

    @Override
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

    /*
    Panel, welches ein Beschriftungslabel enthält.
     */

    private class WestPanel extends JPanel implements View.Panel {

        private JLabel nameLabel;

        /*
        Konstruktor. Setzt das Layout.
         */

        public WestPanel(){
            setLayout(new GridLayout(1,1));
            setPreferredSize(new Dimension(450,40));
        }

        /*
        Initialisiert das Panel.
         */

        @Override
        public void initialize(){
            nameLabel = new JLabel("Gerichtname");
            nameLabel.setFont(Style.BOLD_ITALIC_FONT);
            add(nameLabel);
        }
    }

    /*
    Panel, welches mehrere Beschriftungslabels enthält.
     */

    private class CenterPanel extends JPanel implements View.Panel {

        private JLabel priceLabel, kcalLabel, proteinLabel, fatLabel, carbsLabel;

        /*
        Konstruktor. Setzt das Layout.
         */

        public CenterPanel(){
            setLayout(new GridLayout(1,5));
            setPreferredSize(new Dimension(500,40));
            setMinimumSize(new Dimension(500,40));
            setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 10));
        }

        /*
        Initialisiert das Panel.
         */

        @Override
        public void initialize(){
            priceLabel = new JLabel("Preis",JLabel.LEFT);
            kcalLabel = new JLabel("Kalorien",JLabel.LEFT);
            proteinLabel = new JLabel("Proteine",JLabel.LEFT);
            fatLabel = new JLabel("Fette",JLabel.LEFT);
            carbsLabel = new JLabel("Kohlenhydrate",JLabel.LEFT);

            for(JLabel label: new JLabel[]{priceLabel,kcalLabel,proteinLabel,fatLabel,carbsLabel}){
                label.setFont(Style.BOLD_ITALIC_FONT);
                add(label);
            }
        }
    }

    /*
    Panel, welches einen Platzhalter enthält.
     */

    private class EastPanel extends JPanel implements Panel {
        JButton placeHolder;

        /*
        Konstruktor. Setzt das Layout.
         */

        public EastPanel(){
            setLayout(new GridLayout(1,1));
            setPreferredSize(new Dimension(150,40));
        }

        /*
        Initialisiert das Panel.
         */

        @Override
        public void initialize(){
            placeHolder = new JButton("Entfernen");
            placeHolder.setFocusable(false);
            placeHolder.setVisible(false);
            add(placeHolder);
        }
    }

}
