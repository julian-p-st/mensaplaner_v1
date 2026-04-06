package View.Statistics;

import Model.CalculationType;
import View.Panel;
import View.Style;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel, welches die Durchschnittswerte anzeigt ("Durchschnittswerte").
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class AveragesPanel extends StatisticsEntryPanel {

    private HeaderPanel headerPanel;
    private ContentPanel contentPanel;
    private ArrayList<Float> totalAverages = new ArrayList<>();
    private ArrayList<Float> dailyAverages = new ArrayList<>();

    /**
     * Konstruktor der Klasse {@link AveragesPanel}.
     * Setzt das Layout.
     */

    public AveragesPanel() {
        super("Durchschnittswerte");
    }

    /**
     * Initialisiert das Panel, indem das {@link HeaderPanel} und das {@link ContentPanel}
     * mit den Durchschnittswerten erstellt werden.
     */

    @Override
    public void initialize(){
        headerPanel = new HeaderPanel();
        headerPanel.initialize();
        add(headerPanel, BorderLayout.NORTH);

        contentPanel = new ContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Panel, welches die Durchschnittswerte basierend auf der gewählten Berechnungsgrundlage anzeigt.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class ContentPanel extends JPanel implements Panel {

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public ContentPanel() {
            setLayout(new GridLayout(0,2));
        }

        /**
         * Initialisiert das Panel, indem die zu verwendende Berechnungsgrundlage abgefragt und daraufhin das Panel mit
         * Durchschnittswerten befüllt wird.
         */

        @Override
        public void initialize() {

            JComboBox<String> comboBox = headerPanel.getChangeCalcComboBox();

            if(comboBox.getSelectedItem() == comboBox.getItemAt(0))
                setContent(totalAverages);
            if(comboBox.getSelectedItem() == comboBox.getItemAt(1))
                setContent(dailyAverages);
        }

        /**
         * Setzt die Durchschnittswerte.
         *
         * @param averages eine Liste mit den Durchschnittswerten.
         */

        private void setContent(ArrayList<Float> averages){
            setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
            for (int i = 0; i < CalculationType.values().length; i++) {
                JLabel titleLabel =
                        new JLabel(CalculationType.getTypeToString(CalculationType.values()[i]) + ": ");
                titleLabel.setFont(Style.MAIN_FONT);
                JLabel valueLabel =
                        new JLabel (StatisticsGridPanel.format(averages.get(i)) + " " +
                                CalculationType.getTypeUnit(CalculationType.values()[i]));
                valueLabel.setFont(Style.MAIN_FONT);
                add(titleLabel);
                add(valueLabel);
            }
        }

    }

    /**
     * Panel, welches die Kopfzeile des Panels anzeigt. Enthält eine Auswahlbox, mit welcher die Berechnungsgrundlage
     * der Durchschnittsberechnung gewählt werden kann.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class HeaderPanel extends StatisticsEntryHeaderPanel {

        private JComboBox<String> changeCalcComboBox;
        private JLabel changeCalcLabel, emptyLabel;

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public HeaderPanel() {
            super();
        }

        /**
         * Initialisiert das Panel, indem ein Label und eine Auswahlbox auf das Panel gesetzt werden.
         */

        @Override
        public void initialize(){
            changeCalcLabel = new JLabel("Berechnungsgrundlage ändern:");
            changeCalcLabel.setFont(Style.MAIN_FONT);
            emptyLabel = new JLabel(" ");

            String[] elements = {"Gesamtdurchschnitt", "Tagesdurchschnitt"};
            changeCalcComboBox = new JComboBox<>(elements);
            changeCalcComboBox.setToolTipText("Gesamtdurchschnitt: Wert/AnzahlGerichte | Tagesdurchschnitt: Wert/AnzahlTage");

            add(changeCalcLabel);
            add(changeCalcComboBox);
            add(emptyLabel);
        }

        /**
         * Standard-Getter, um eine Referenz auf die Auswahlbox zu erhalten.
         *
         * @return eine Referenz auf die Auswahlbox.
         */

        public JComboBox<String> getChangeCalcComboBox() {
            return changeCalcComboBox;
        }

    }

    /**
     * Standard-Setter, um bei Aktualisierung der Statistikübersicht die Tagesdurchschnittswerte neu zu setzen.
     *
     * @param dailyAverages eine Liste mit den Tagesdurchschnittswerten.
     */

    public void setDailyAverages(ArrayList<Float> dailyAverages) {
        this.dailyAverages = dailyAverages;
    }

    /**
     * Standard-Setter, um bei Aktualisierung der Statistikübersicht die Gesamtdurchschnittswerte neu zu setzen.
     *
     * @param totalAverages eine Liste mit den Gesamtdurchschnittswerten.
     */

    public void setTotalAverages(ArrayList<Float> totalAverages) {
        this.totalAverages = totalAverages;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link ContentPanel}, welches die Durchschnittswerte anzeigt,
     * zu erhalten.
     *
     * @return eine Referenz auf das Inhalts-Panel.
     */

    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link HeaderPanel} zu erhalten.
     *
     * @return eine Referenz auf die Kopfzeile.
     */

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

}
