package View.DailyMenu;

import View.Panel;
import View.Style;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Panel, in dem das gewünschte Datum ausgewählt und der Speiseplan aktualisiert werden kann.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class DatePanel extends JPanel implements Panel {

    private JTextField selectDateField;
    private JButton refreshMenuButton;
    private JSpinner dateSpinner;

    /**
     * Konstruktor der Klasse {@link DatePanel}.
     * Setzt das Layout.
     */

    public DatePanel() {
        setLayout(new GridLayout(1,3,0,0));
        setPreferredSize(new Dimension(1200,40));
    }

    /**
     * Initialisiert das Panel, in dem das Panel mit einem Textfeld, einem Datums-Auswahl-Feld und einem Knopf zum
     * Aktualisieren des Speiseplans befüllt wird.
     */

    @Override
    public void initialize(){

        selectDateField = new JTextField("Datum auswählen:");
        selectDateField.setFont(Style.MAIN_FONT);
        selectDateField.setEditable(false);
        selectDateField.setFocusable(false);

        refreshMenuButton = new JButton("Speiseplan aktualisieren");
        refreshMenuButton.setFont(Style.MAIN_FONT);
        refreshMenuButton.setToolTipText("Aktualisiert den Speiseplan auf das eingestellte Datum.");

        SpinnerDateModel model = new SpinnerDateModel();
        dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd.MM.yyyy");
        dateSpinner.setEditor(editor);

        add(selectDateField);
        add(dateSpinner);
        add(refreshMenuButton);

    }

    /**
     * Standard-Getter, welcher das aktuelle, im DateSpinner eingestellte Datum zurückgibt.
     *
     * @return das eingestellte Datum.
     */

    public Date getSelectedDate(){
        return (Date) dateSpinner.getValue();
    }

    /**
     * Standard-Getter, welcher eine Referenz auf den "Speiseplan aktualisieren" Knopf zurückgibt.
     *
     * @return eine Referenz auf den "Speiseplan aktualisieren" Knopf.
     */

    public JButton getRefreshMenuButton() {
        return refreshMenuButton;
    }

}
