package View.Statistics;

import Model.SustainabilityScoreType;
import View.Panel;
import View.Style;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel, welches die durchschnittlichen Nachhaltigkeit-Scores anzeigt ("Nachhaltigkeit").
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class SustainabilityPanel extends StatisticsEntryPanel{

    private HeaderPanel headerPanel;
    private ContentPanel contentPanel;

    private ArrayList<Float> averageScores =  new ArrayList<>();

    /**
     * Konstruktor der Klasse {@link SustainabilityPanel}.
     * Setzt das Layout.
     */

    public SustainabilityPanel(){
        super("Nachhaltigkeit");
    }

    /**
     * Initialisiert das Panel, indem das {@link HeaderPanel} und das {@link ContentPanel} erstellt werden.
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
     * Panel, welches die durchschnittlichen Nachhaltigkeit-Scores anzeigt.
     *
     * @author juliansteinhauser - ufxsr@student.kit.edu
     * @version 2025-06-14
     */

    public class ContentPanel extends JPanel implements Panel{

        /**
         * Konstruktor.
         * Setzt das Layout.
         */

        public ContentPanel(){
            setLayout(new GridLayout(0,2));
        }

        /**
         * Initialisiert das Panel, indem Labels mit den durchschnittlichen Nachhaltigkeit-Scores erstellt werden.
         * Färbt die Scores außerdem entsprechend ihrer Bedeutung farbig ein.
         */

        @Override
        public void initialize(){
            setBorder(BorderFactory.createEmptyBorder(0,5,0,0));

            for (int i = 0; i < SustainabilityScoreType.values().length; i++) {

                JLabel titleLabel =
                        new JLabel(SustainabilityScoreType.getTypeToString(SustainabilityScoreType.values()[i]));
                titleLabel.setFont(Style.MAIN_FONT);
                JLabel valueLabel =
                        new JLabel(StatisticsGridPanel.format(averageScores.get(i)) + " " +
                                SustainabilityScoreType.getTypeUnit(SustainabilityScoreType.values()[i]));
                valueLabel.setFont(Style.MAIN_FONT);


                if(averageScores.get(i) > 2 && i < 4 ){
                    valueLabel.setForeground(new Color(0,128,0));
                }
                else if(averageScores.get(i) > 1 && averageScores.get(i) <= 2 && i < 4){
                    valueLabel.setForeground(new Color(255,142,20));
                }
                else if(averageScores.get(i) > 0 && averageScores.get(i) <= 1 && i < 4) {
                    valueLabel.setForeground(new Color(200,0,35));
                }
                else{
                    valueLabel.setForeground(Color.BLACK);
                }

                add(titleLabel);
                add(valueLabel);
            }
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
            dataLabel = new JLabel("Durchschnitt");
            dataLabel.setFont(Style.BOLD_FONT);

            add(nameLabel);
            add(dataLabel);
        }
    }

    /**
     * Standard-Setter, um bei Aktualisierung der Statistikübersicht die durchschnittlichen Nachhaltigkeit-Scores
     * neu zu setzen.
     *
     * @param averageScores eine Liste mit den durchschnittlichen Nachhaltigkeit-Scores.
     */

    public void setAverageScores(ArrayList<Float> averageScores){
        this.averageScores = averageScores;
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
