package View;

import View.DailyMenu.DailyMenuPanel;
import View.History.HistoryPanel;
import View.Statistics.StatisticsPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Haupt-Frame, indem alle anderen Panels eingebettet sind. Enthält Methoden, um zahlreiche GUI-Komponenten zu
 * erstellen und dem Haupt-Frame hinzuzufügen.
 *
 * @author juliansteinhauser - ufxsr@student.kit.edu
 * @version 2025-06-14
 */

public class MainView extends JFrame implements Panel{

    private Container contentPane;
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu menuLookandFeel;
    private JMenuItem nimbusLF, systemLF, metalLF, motifLF;

    private DailyMenuPanel dailyMenuPanel;
    private HistoryPanel historyPanel;
    private StatisticsPanel statisticsPanel;

    /**
     * Konstruktor der Klasse {@link MainView}.
     * Konfiguriert den Haupt-Frame.
     */

    public MainView(){
        contentPane = getContentPane();
        setTitle("Mensa Food Tracker");
        setSize(1400, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1200, 500));
        setLookAndFeel(LookAndFeelType.NIMBUS);
        initialize();

    }

    /**
     * Initialisiert den Haupt-Frame. Erstellt die Tabs und die Menüs.
     */

    @Override
    public void initialize(){
        initializeTabs();
        initializeTabMenu();
        contentPane.add(tabbedPane);
        initializeMenuBar();
        setJMenuBar(menuBar);

    }

    /*
    Initialisiert die Tabs.
     */

    private void initializeTabs(){
        dailyMenuPanel = new DailyMenuPanel();
        dailyMenuPanel.initialize();
        historyPanel = new HistoryPanel();
        historyPanel.initialize();
        statisticsPanel = new StatisticsPanel();
        statisticsPanel.initialize();
    }

    /*
    Initialisiert das Tab-Menü, mit den Tabs "Speiseplan", "Historie" und "Statistik".
     */

    private void initializeTabMenu(){

        tabbedPane = new JTabbedPane();

        tabbedPane.add("", dailyMenuPanel);
        tabbedPane.add("", historyPanel);
        tabbedPane.add("", statisticsPanel);

        JLabel tabLabelMeals = new JLabel("Speiseplan");
        tabLabelMeals.setPreferredSize(new Dimension(110, 20));
        tabbedPane.setTabComponentAt(0, tabLabelMeals);

        JLabel tabLabelHistory = new JLabel("Historie");
        tabLabelHistory.setPreferredSize(new Dimension(110, 20));
        tabbedPane.setTabComponentAt(1, tabLabelHistory);

        JLabel tabLabelStatistics = new JLabel("Statistik");
        tabLabelStatistics.setPreferredSize(new Dimension(110, 20));
        tabbedPane.setTabComponentAt(2, tabLabelStatistics);

    }

    /*
    Initialisiert das Menü.
     */

    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("Menü");
        menu.setFont(Style.MAIN_FONT);
        menuBar.add(menu);
        menuLookandFeel = new JMenu("Look and Feel");
        menuLookandFeel.setFont(Style.MAIN_FONT);
        menu.add(menuLookandFeel);
        nimbusLF = new JMenuItem("Nimbus");
        nimbusLF.setFont(Style.MAIN_FONT);
        menuLookandFeel.add(nimbusLF);
        systemLF = new JMenuItem("System");
        systemLF.setFont(Style.MAIN_FONT);
        menuLookandFeel.add(systemLF);
        metalLF = new JMenuItem("Metal");
        metalLF.setFont(Style.MAIN_FONT);
        menuLookandFeel.add(metalLF);
        motifLF = new JMenuItem("Motif");
        motifLF.setFont(Style.MAIN_FONT);
        menuLookandFeel.add(motifLF);
    }

    /**
     * Methode, um das Look-and-Feel der Anwendung anhand des übergebenen {@link LookAndFeelType} zu setzen.
     * @param type der Typ des Look-and-Feel, das gesetzt werden soll.
     */

    public void setLookAndFeel(LookAndFeelType type){
        try{
            if(type == LookAndFeelType.SYSTEM){
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            else {
                UIManager.setLookAndFeel(LookAndFeelType.getLookAndFeelName(type));
            }
        }
        catch(Exception e){
            new OptionPane("Es ist ein Fehler beim Setzen des LookandFeel aufgetreten!",
                    OptionPane.OptionType.ERROR);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link DailyMenuPanel} zu erhalten.
     *
     * @return eine Referenz auf das {@link DailyMenuPanel}.
     */

    public DailyMenuPanel getDailyMenuPanel() {
        return dailyMenuPanel;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link HistoryPanel} zu erhalten.
     *
     * @return eine Referenz auf das {@link HistoryPanel}.
     */

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }

    /**
     * Standard-Getter, um eine Referenz auf das {@link StatisticsPanel} zu erhalten.
     *
     * @return eine Referenz auf das {@link StatisticsPanel}.
     */

    public StatisticsPanel getStatisticsPanel() {
        return statisticsPanel;
    }

    /**
     * Standard-Getter, um den Pfad des "Nimbus" Look-and-Feel zu erhalten.
     *
     * @return den Pfad des "Nimbus" Look-and-Feel.
     */

    public JMenuItem getNimbusLF() {
        return nimbusLF;
    }

    /**
     * Standard-Getter, um den Pfad des "System" Look-and-Feel zu erhalten.
     *
     * @return den Pfad des "System" Look-and-Feel.
     */

    public JMenuItem getSystemLF() {
        return systemLF;
    }

    /**
     * Standard-Getter, um den Pfad des "Metal" Look-and-Feel zu erhalten.
     *
     * @return den Pfad des "Metal" Look-and-Feel.
     */

    public JMenuItem getMetalLF() {
        return metalLF;
    }

    /**
     * Standard-Getter, um den Pfad des "Motif" Look-and-Feel zu erhalten.
     *
     * @return den Pfad des "Motif" Look-and-Feel.
     */

    public JMenuItem getMotifLF() {
        return motifLF;
    }

}
