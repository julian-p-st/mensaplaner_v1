package View;

/**
 * Enumeration, welche die auswählbaren Look-and-Feel-Typen als Konstanten enthält.
 * Stellt eine Methode bereit, um den Pfad des jeweiligen Look-and-Feels zu erhalten
 * {@link #getLookAndFeelName(LookAndFeelType)}.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public enum LookAndFeelType {
    NIMBUS, SYSTEM, METAL, MOTIF;

    /**
     * Gibt den Pfad des übergebenen {@link LookAndFeelType} zurück.
     *
     * @param type der Look-and-Feel-Typ.
     * @return der Pfad des Look-and-Feel-Typs.
     */

    public static String getLookAndFeelName(LookAndFeelType type) {
        return switch (type) {
            case NIMBUS: yield "javax.swing.plaf.nimbus.NimbusLookAndFeel";
            case METAL: yield "javax.swing.plaf.metal.MetalLookAndFeel";
            case MOTIF: yield "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            default: yield "";
        };
    }

}

