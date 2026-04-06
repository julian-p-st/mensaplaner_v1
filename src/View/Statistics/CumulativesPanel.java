package View.Statistics;

import Model.CalculationType;
import View.Panel;
import View.Style;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel, welches die aggregierten Kennzahlen anzeigt ("Gesamtwerte").
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class CumulativesPanel extends StatisticsEntryPanel{

    private HeaderPanel headerPanel;
    private ContentPanel contentPanel;
    private ArrayList<Float> cumulatives = new ArrayList<>();

    /**
     * Konstruktor der Klasse {@link CumulativesPanel}.
     * Setzt das Layout.
     */

    public CumulativesPanel(){
        super("Gesamtwerte");
    }

    /**
     * Initialisiert das Panel, indem das {@link HeaderPanel} und das {@link ContentPanel} erstellt werden.
     */

    @Override
    public void initialize(){
        headerPanel = new HeaderPanel();
        headerPanel.initialize();
        add(headerPanel, BorderLayout.NORTH);

        contentPanel = new CumulativesPanel.ContentPanel();
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Panel, welches die aggregierten Kennzahlen anzeigt.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class ContentPanel extends JPanel implements Panel {

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public ContentPanel(){
            setLayout(new GridLayout(0,2));
        }

        /**
         * Initialisiert das Panel, indem Labels mit den Gesamtwerten erstellt werden.
         */

        @Override
        public void initialize(){
            setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
            for (int i = 0; i < CalculationType.values().length; i++) {
                JLabel titleLabel =
                        new JLabel(CalculationType.getTypeToString(CalculationType.values()[i]) + ": ");
                titleLabel.setFont(Style.MAIN_FONT);
                JLabel valueLabel =
                        new JLabel (StatisticsGridPanel.format(cumulatives.get(i)) + " " +
                                CalculationType.getTypeUnit(CalculationType.values()[i]));
                valueLabel.setFont(Style.MAIN_FONT);
                add(titleLabel);
                add(valueLabel);
            }
        }

    }

    /**
     * Panel, welches die Kopfzeile des Panels anzeigt.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class HeaderPanel extends StatisticsEntryHeaderPanel{

        private JLabel nameLabel, dataLabel;

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public HeaderPanel(){
            super();
        }

        /**
         * Initialisiert das Panel, indem Beschriftungen dem Panel hinzugefügt werden.
         */

        @Override
        public void initialize(){
            setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
            nameLabel = new JLabel("Kennzahl");
            nameLabel.setFont(Style.BOLD_FONT);
            dataLabel = new JLabel("Summe");
            dataLabel.setFont(Style.BOLD_FONT);

            add(nameLabel);
            add(dataLabel);

        }

    }

    /**
     * Standard-Setter, um bei Aktualisierung der Statistikübersicht die aggregierten Kennzahlen neu zu setzen.
     *
     * @param cumulatives eine Liste mit den aggregierten Kennzahlen.
     */


    public void setCumulatives(ArrayList<Float> cumulatives){
        this.cumulatives = cumulatives;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link ContentPanel}, welches die Durchschnittswerte anzeigt,
     * zu erhalten.
     *
     * @return eine Referenz auf das Inhalts-Panel.
     */

    public ContentPanel getContentPanel(){
        return contentPanel;
    }

}
