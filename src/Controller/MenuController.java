package Controller;

import View.LookAndFeelType;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller, der die Logik für die Menüleiste bereitstellt.
 * Enthält eine Methode, um {@link ActionListener} auf den Menüeinträgen der Menüleiste zu registrieren
 * ({@link #registerLFMenuItems()}).
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class MenuController {

    private final MainView mainView;

    /**
     * Konstruktor der Klasse {@link MenuController}.
     * Registriert {@link ActionListener} auf den Menüeinträgen {@link #registerLFMenuItems()}.
     *
     * @param mainView das Hauptfenster des Programms.
     */

    public MenuController(MainView mainView) {
        this.mainView = mainView;

        registerLFMenuItems();
    }

    /**
     * Registriert {@link ActionListener} auf den Menüeinträgen des Untermenüs "Look-and-Feel",
     * womit das Look-and-Feel der Anwendung gesetzt werden kann.
     */

    public void registerLFMenuItems() {
        registerLFItem(mainView.getNimbusLF(), LookAndFeelType.NIMBUS);
        registerLFItem(mainView.getSystemLF(), LookAndFeelType.SYSTEM);
        registerLFItem(mainView.getMetalLF(), LookAndFeelType.METAL);
        registerLFItem(mainView.getMotifLF(), LookAndFeelType.MOTIF);
    }

    /*
    Registriert einen ActionListener auf dem Look-and-Feel Menüeintrag.
     */

    private void registerLFItem(javax.swing.JMenuItem menuItem, LookAndFeelType lfType) {
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.setLookAndFeel(lfType);
            }
        });
    }

}
