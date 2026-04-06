package View.History;

import Model.HistoryModel;
import View.Panel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Panel, welches ein "Datum" in der Historie darstellt und die einzelnen Gerichte
 * in Form von {@link HistoryListEntryPanel}-Objekten aufnimmt. Fasst also die zu einem Datum gehörenden Gerichte in
 * einem Panel zusammen.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class HistoryListPanel extends JPanel implements Panel {

    private LocalDate date;

    /**
     * Konstruktor der Klasse {@link HistoryListPanel}.
     * Setzt das Layout.
     * @param date
     */

    public HistoryListPanel(LocalDate date) {
        this.date = date;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d. MMMM yyyy");
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 5, 10, 5),
                BorderFactory.createTitledBorder(
                        BorderFactory.createMatteBorder(1,0,0,0,Color.BLACK),
                        formatter.format(date), TitledBorder.CENTER, TitledBorder.TOP)));
    }

    /**
     * Initialisiert das Panel.
     */

    @Override
    public void initialize(){}

    /**
     * Erstellt ein {@link HistoryListEntryPanel}, welches eine "Zeile" in der Historie darstellt und fügt dieses dem
     * dazugehörigen {@link HistoryListPanel} hinzu.
     *
     * @param entry ein Historien-Eintrag.
     * @return eine "Zeile" in der Historie.
     */

    public HistoryListEntryPanel createHistoryListEntryPanel(HistoryModel.HistoryEntry entry){
        HistoryListEntryPanel historyDayEntryPanel= new HistoryListEntryPanel(entry);
        historyDayEntryPanel.initialize();
        add(historyDayEntryPanel);
        return historyDayEntryPanel;
    }

}
