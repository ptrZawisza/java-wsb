package com.mycompany.notepadprojekt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class Notatnik extends JFrame {

    private RSyntaxTextArea textArea;

    public Notatnik() {
        setTitle("Projekt");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new RSyntaxTextArea(50,100);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        add(sp, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Plik");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Otwórz");
        openItem.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(Notatnik.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    textArea.read(reader, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Notatnik.this, "Nie można otworzyć pliku.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Zapisz");
        saveItem.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(Notatnik.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(writer);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Notatnik.this, "Nie można zapisać pliku", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.add(saveItem);

        JMenu syntaxMenu = new JMenu("Syntax");
        menuBar.add(syntaxMenu);

        JMenuItem javaSyntax = new JMenuItem("Java");
        javaSyntax.addActionListener((ActionEvent e) -> {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        });
        syntaxMenu.add(javaSyntax);

        JMenuItem cSyntax = new JMenuItem("C");
        cSyntax.addActionListener((ActionEvent e) -> {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
        });
        syntaxMenu.add(cSyntax);
        
        JMenuItem cppSyntax = new JMenuItem("C++");
        cSyntax.addActionListener((ActionEvent e) -> {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        });
        syntaxMenu.add(cppSyntax);
    }

 
}
