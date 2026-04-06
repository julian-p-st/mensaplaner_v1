package View.History;

import Model.HistoryModel;
import View.Panel;
import View.Style;
import edu.kit.aifb.atks.mensascraper.lib.MensaMeal;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Panel, welches einen {@link Model.HistoryModel.HistoryEntry} in der Historie darstellt. Kann als eine
 * "Zeile" in der Historie aufgefasst werden.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryListEntryPanel extends JPanel implements Panel {

    private HistoryModel.HistoryEntry entry;
    private JLabel nameLabel, priceLabel, proteinLabel, fatLabel, kcalLabel, carbsLabel;
    private JButton removeFromHistoryButton;
    private WestPanel westPanel;
    private CenterPanel centerPanel;
    private EastPanel eastPanel;

    /**
     * Konstruktor der Klasse {@link HistoryListEntryPanel}.
     * Setzt das Layout.
     *
     * @param entry ein Eintrag aus der HISTORY.
     */

    public HistoryListEntryPanel(HistoryModel.HistoryEntry entry) {
        this.entry = entry;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 0, 5, 0),
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        ));
        setPreferredSize(new Dimension(1000,50));
    }

    /**
     * Initialisiert das Panel, indem die Unter-Panels erstellt werden.
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
    Panel, welches den Namen des Historien-Eintrags enthält.
     */

    private class WestPanel extends JPanel implements Panel {

        /*
        Konstruktor.
        Setzt das Layout.
         */

        public WestPanel() {
            setLayout(new GridLayout(1,1));
            setPreferredSize(new Dimension(450,40));
        }

        /*
        Initialisiert das Panel.
         */

        @Override
        public void initialize() {
            nameLabel = new JLabel(entry.getMeal().getName());
            add(nameLabel);
        }
    }

    /*
    Panel, welches Labels mit den Kennzahlen des Historien-Eintrags enthält.
     */

    private class CenterPanel extends JPanel implements Panel {

        /*
        Konstruktor.
        Setzt das Layout.
         */

        public CenterPanel() {
            setLayout(new GridLayout(1,5));
            setPreferredSize(new Dimension(500,40));
            setMinimumSize(new Dimension(500,40));
            setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 10));
        }

        /*
        Initialisiert das Panel.
         */

        @Override
        public void initialize() {
            DecimalFormat dfPrice = new DecimalFormat("0.00");
            DecimalFormat dfNutrients = new DecimalFormat("#####.#");

            priceLabel = new JLabel(dfPrice.format(entry.getMeal().getPrice()) +" €", JLabel.LEFT);
            priceLabel.setFont(Style.MAIN_FONT);
            kcalLabel = new JLabel(dfNutrients.format(entry.getMeal().getKcal())+ " kcal", JLabel.LEFT);
            kcalLabel.setFont(Style.MAIN_FONT);
            proteinLabel = new JLabel(dfNutrients.format(entry.getMeal().getProteins())+ " g", JLabel.LEFT);
            proteinLabel.setFont(Style.MAIN_FONT);
            fatLabel = new JLabel(dfNutrients.format(entry.getMeal().getFat()) + " g", JLabel.LEFT);
            fatLabel.setFont(Style.MAIN_FONT);
            carbsLabel = new JLabel(dfNutrients.format(entry.getMeal().getCarbs()) + " g", JLabel.LEFT);
            carbsLabel.setFont(Style.MAIN_FONT);

            add(priceLabel);
            add(kcalLabel);
            add(proteinLabel);
            add(fatLabel);
            add(carbsLabel);
        }
    }

    /*
    Panel, welches den "Entfernen"-Knopf enthält.
     */

    private class EastPanel extends JPanel implements Panel {

        private JButton removeFromHistoryButton;

        /*
        Konstruktor.
        Setzt das Layout.
         */

        public EastPanel() {
            setLayout(new GridLayout(1,1));
            setPreferredSize(new Dimension(150,40));
        }

        /*
        Initialisiert das Panel.
         */

        @Override
        public void initialize() {
            removeFromHistoryButton = new JButton("Entfernen");
            removeFromHistoryButton.setFont(Style.MAIN_FONT);
            removeFromHistoryButton.setToolTipText("Entfernt das Gericht aus der Historie");
            setRemoveFromHistoryButton(removeFromHistoryButton);
            add(removeFromHistoryButton);
        }
    }

    /**
     * Standard-Setter, um eine Referenz auf den "Entfernen"-Knopf zu setzen.
     */

    public void setRemoveFromHistoryButton(JButton removeFromHistoryButton) {
        this.removeFromHistoryButton = removeFromHistoryButton;
    }

    /**
     * Standard-Getter, um eine Referenz auf den "Entfernen"-Knopf zu erhalten.
     *
     * @return eine Referenz auf den "Entfernen"-Knopf.
     */

    public JButton getRemoveFromHistoryButton() {
        return removeFromHistoryButton;
    }

    /**
     * Standard-Getter, um das im {@link HistoryListEntryPanel} gespeicherte {@link MensaMeal} zu erhalten.
     *
     * @return das im Historien-Eintrag gespeicherte Gericht.
     */

    public MensaMeal getMeal(){
        return entry.getMeal();
    }

    /**
     * Standard-Getter, um das im {@link HistoryListEntryPanel} gespeicherte {@link LocalDate} zu erhalten.
     *
     * @return das im Historien-Eintrag gespeicherte Datum.
     */

    public LocalDate getDate(){
        return entry.getDate();
    }

}
