package View.Statistics;

import View.Panel;
import View.Style;
import edu.kit.aifb.atks.mensascraper.lib.MensaMealType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel, welches die Gesamtzahl der Gerichte in den jeweiligen Kategorien und die Veggie-Ratio anzeigt
 * ("Ernährungsprofil").
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class ProfilePanel extends StatisticsEntryPanel {

    private ContentPanel contentPanel;
    private HeaderPanel headerPanel;

    private ArrayList<Integer> mealTypeCount = new ArrayList<>();
    private float veggieRatio;

    /**
     * Konstruktor der Klasse {@link ProfilePanel}.
     * Setzt das Layout.
     */

    public ProfilePanel() {
        super("Ernährungsprofil");
    }

    /**
     * Initialisiert das Panel, indem das {@link HeaderPanel} und das {@link ContentPanel} erstellt werden.
     */

    @Override
    public void initialize() {
        headerPanel = new HeaderPanel();
        headerPanel.initialize();
        add(headerPanel, BorderLayout.NORTH);
        contentPanel = new ContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Panel, welches die Gesamtzahl der Gerichte in den jeweiligen Kategorien und die Veggie-Ratio anzeigt.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class ContentPanel extends JPanel implements Panel{

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public ContentPanel() {
            setLayout(new GridLayout(0,2));
        }

        /**
         * Initialisiert das Panel, indem Labels mit den Typbezeichnungen und den in der HISTORY auftretenden
         * Häufigkeiten erstellt werden. Außerdem wird die Veggie-Ratio dem Panel als Label hinzugefügt.
         */

        @Override
        public void initialize() {
            String[] titles =
                    new String[] {"Unbekannt", "Schweinefleisch", "Rindfleisch", "Fisch", "Vegetarisch", "Vegan"};
            setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
            for (int i = 0; i < MensaMealType.values().length; i++) {
                JLabel titleLabel =
                        new JLabel(titles[i]+ ": ");
                titleLabel.setFont(Style.MAIN_FONT);
                JLabel valueLabel =
                        new JLabel(mealTypeCount.get(i).toString());
                valueLabel.setFont(Style.MAIN_FONT);

                add(titleLabel);
                add(valueLabel);
            }

            JLabel veggieLabel = new JLabel("Veggie-Anteil: ");
            veggieLabel.setFont(Style.MAIN_FONT);
            add(veggieLabel);
            JLabel veggieValueLabel = new JLabel(StatisticsGridPanel.format(veggieRatio *100) + " %");
            add(veggieValueLabel);

            JLabel emptyLabel = new JLabel(" ");
            add(emptyLabel);
        }

    }

    /**
     * Panel, welches die Kopfzeile des Panels anzeigt.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class HeaderPanel extends StatisticsEntryHeaderPanel {

        private JLabel nameLabel, dataLabel;

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public HeaderPanel() {
            super();
        }

        /**
         * Initialisiert das Panel, indem Beschriftungen dem Panel hinzugefügt werden.
         */

        @Override
        public void initialize() {
            setBorder(BorderFactory.createEmptyBorder(10,5,10,5));

            nameLabel = new JLabel("Gerichttyp");
            nameLabel.setFont(Style.BOLD_FONT);
            dataLabel = new JLabel("Anzahl");
            dataLabel.setFont(Style.BOLD_FONT);

            add(nameLabel);
            add(dataLabel);

        }

    }

    /**
     * Standard-Setter, um bei Aktualisierung der Statistikübersicht die Anzahl der Gerichte in den jeweiligen
     * Kategorien neu zu setzen.
     *
     * @param mealTypeCount Liste mit den Anzahlen, wie oft ein Gericht in der HISTORY auftaucht.
     */

    public void setMealTypeCount(ArrayList<Integer> mealTypeCount) {
        this.mealTypeCount = mealTypeCount;
    }
    /**
     * Standard-Setter, um bei Aktualisierung der Statistikübersicht die Veggie-Ratio neu zu setzen.
     *
     * @param veggieRatio die aktuelle Veggie-Ratio.
     */

    public void setVeggieRatio(float veggieRatio) {
        this.veggieRatio = veggieRatio;
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

}
