package com.alientools.connectionhub.launcher;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Process proc;
    static ColorPane jTextPane;

    public static void main(String[] args) throws IOException, BadLocationException {
        createUi();
        startPorcess();

    }

    private static void startPorcess() throws IOException, BadLocationException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "java -jar \"C:\\Users\\Amardeep Yadav\\WebstormProjects\\Sourcecontrolroot\\AlienSoftDev\\LEVEL_1\\hub\\hub-0.0.1-SNAPSHOT.jar\"");
        proc = builder.start();

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));
// Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            jTextPane.appendANSI("\n" + s);
            System.out.println(s);
        }
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }

    private static void createUi() {
        JFrame frame = new JFrame("Connection Hub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        frame.setSize(width, height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jTextPane = new ColorPane();
        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jTextPane.setBackground(Color.BLACK);
        jTextPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        frame.getContentPane().setLayout(new GridLayout(1, 1));
        frame.getContentPane().add(jScrollPane);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    proc.destroy();
                    try {
                        Runtime.getRuntime().exec("taskkill /F /IM java.exe");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.exit(0);
                }
            }
        });
    }
}
