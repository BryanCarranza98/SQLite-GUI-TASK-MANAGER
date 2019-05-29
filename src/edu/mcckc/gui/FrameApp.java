package edu.mcckc.gui;

import edu.mcckc.dao.SetupDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameApp
    extends JFrame
{
    private JTabbedPane tabContainer;
    private PanelInput pnlInput;
    private PanelEdit pnlEdit;
    private PanelDisplay pnlDisplay;


    private JMenuBar mbarMenu;
    private JMenu mnuFile;
    private JMenuItem mitemExit;
    private JMenuItem mitemSetup;


    public FrameApp()
    {

        mbarMenu = new JMenuBar();
        mnuFile = new JMenu("File");
        mitemExit = new JMenu("Exit");
        mitemSetup = new JMenu("Setup DB");

        mitemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mitemSetup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetupDao dao = new SetupDao("sqliteconfig.properties");
                dao.createDatabaseTables();
            }
        });

        mnuFile.add(mitemSetup);
        mnuFile.add(mitemExit);

        mbarMenu.add(mnuFile);

        setJMenuBar(mbarMenu);

        ////////////////////////////////

        tabContainer = new JTabbedPane(JTabbedPane.TOP);

        pnlInput = new PanelInput();
        pnlEdit = new PanelEdit();
        pnlDisplay = new PanelDisplay();

        pnlInput.setEditPanelReference(pnlEdit);

        tabContainer.addTab("Input", pnlInput);
        tabContainer.addTab("Edit", pnlEdit);
        tabContainer.addTab("Display", pnlDisplay);

        add(tabContainer, BorderLayout.CENTER);

    }


}
