package View;

import javax.swing.*;

/**
 * Klasse, die ein Info-Fenster darstellt. Informiert den Nutzer bei Erzeugung über aufgetretene Ereignisse.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class OptionPane extends JOptionPane {

    /**
     * Enumeration, welche die verschiedenen Info-Typen als Konstanten enthält.
     */

    public enum OptionType{
        ERROR, INFORMATION, WARNING
    }

    /**
     * Konstruktor der Klasse {@link OptionPane}.
     * Erzeugt ein Info-Fenster, welches den Nutzer auf ein aufgetretenes Ereignis hinweist.
     *
     * @param message Nachricht, die angezeigt werden soll.
     * @param type Typ der Nachricht.
     */

    public OptionPane(String message, OptionType type) {
        if (type == OptionType.INFORMATION) {
            showMessageDialog(null, message,null, JOptionPane.INFORMATION_MESSAGE);
        }
        if (type == OptionType.ERROR) {
            showMessageDialog(null, message,null, JOptionPane.ERROR_MESSAGE);
        }
        if(type == OptionType.WARNING){
            showMessageDialog(null, message,null, JOptionPane.WARNING_MESSAGE);
        }
    }

}
