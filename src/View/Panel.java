package View;

/**
 * Interface, welches allen Panels der GUI eine Methode {@link #initialize()} vorschreibt. Dient der Trennung von der
 * Konfiguration des Panels und dem eigentlichen Befüllen des Panels mit Komponenten.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public interface Panel {

    /**
     * Methode, welche das Panel initialisieren soll. Innerhalb dieser Methode soll das Panel mit Komponenten gefüllt
     * werden.
     */

    void initialize();
}
