package liu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Initialises programme's window.
 */
public class View extends JFrame {
    private static Dictionary dictionary = new Dictionary();
    /**
     * creates a listener for receiving commands.
     */
    private ActionListener model;
    /**
     * text area containing Quoc Ngu texts.
     */
    private static JTextArea textQuocngu;
    /**
     * text area containing Han Nom texts.
     */
    private static JTextArea textHannom;

    /**
     * creates an object does solving works about solving data,
     * building crucial data structure, at cetera...
     */

    public View() {
        khoitao();
        dictionaryManipulations();

        final int fontSize = 20;
        model = new Model();
        Font font = new Font("PlanMiNguyen", Font.PLAIN, fontSize);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        menuBar.setFont(font);

        JMenu trogiup = new JMenu("Trợ giúp");
        trogiup.addActionListener(model);
        menuBar.add(trogiup);
        JMenuItem thongtin = new JMenuItem("Hỏi Liu2k5");
        trogiup.add(thongtin);

        JLabel mainLabel = new JLabel("Chuyển từ quốc ngữ sang chữ Hán Nôm", JLabel.CENTER);
        mainLabel.setFont(font);
        this.add(mainLabel, BorderLayout.NORTH);

        JPanel qngu = new JPanel();
        qngu.setLayout(new BorderLayout());

        JLabel quocnguLabel = new JLabel("Quốc ngữ");
        quocnguLabel.setFont(font);
        qngu.add(quocnguLabel, BorderLayout.NORTH);

        final int verticalSpace = 20;
        final int horizontalSpace = 20;
        JPanel thanungdung = new JPanel();
        thanungdung.setLayout(new GridLayout(1, 2, verticalSpace, horizontalSpace));

        textQuocngu = new JTextArea();
        textQuocngu.setLineWrap(true);
        textQuocngu.setFont(font);

        JScrollPane qnguScrollPane = new JScrollPane(textQuocngu,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        qngu.add(qnguScrollPane, BorderLayout.CENTER);
        thanungdung.add(qngu);

        JPanel hnom = new JPanel();
        hnom.setLayout(new BorderLayout());

        JLabel hannomLabel = new JLabel("Hán Nôm");
        hannomLabel.setFont(font);
        hnom.add(hannomLabel, BorderLayout.NORTH);

        textHannom = new JTextArea();
        textHannom.setLineWrap(true);
        textHannom.setFont(font);
        
        JScrollPane hnomScrollPane = new JScrollPane(textHannom,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        hnom.add(hnomScrollPane, BorderLayout.CENTER);
        thanungdung.add(hnom);

        this.add(thanungdung, BorderLayout.CENTER);

        JPanel button = new JPanel();
        button.setLayout(new BorderLayout());

        JButton chuyen = new JButton("Chuyển");
        chuyen.setFont(font);
        chuyen.addActionListener(model);
        button.add(chuyen, BorderLayout.CENTER);

        this.add(button, BorderLayout.SOUTH);

        // pack();

    }

    private void khoitao() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Công cụ chuyển đổi cơ bản chữ Quốc ngữ sang Hán Nôm");
        final int screenWidth = 1000;
        final int screenHeight = 600;
        this.setSize(screenWidth, screenHeight);
        this.setLocationRelativeTo(null);

        final int verticalSpace = 20;
        final int horizontalSpace = 20;
        this.setLayout(new BorderLayout(verticalSpace, horizontalSpace));
        this.setVisible(true);

    }

    public static String getText_quocngu() {
        return textQuocngu.getText();
    }

    public static String getText_hannom() {
        return textHannom.getText();
    }

    public static void setText_hannom(String string) {
        textHannom.setText(string);
    }

    public static void appendOutput(String inputParagraph) {
        textHannom.setText(textHannom.getText() + dictionary.performance(inputParagraph) + "\n");
    }

    public static void resetOutput() {
        textHannom.setText("");
    }

    public static void dictionaryManipulations() {
        
        // dictionary.analyses("qngu.txt", "hnom.txt");
        // dictionary.saveWordList("wl.dat");
        // dictionary.saveWordPairList("pl.dat");
        // dictionary.saveWordGroupList("gl.dat");
        dictionary.loadWordList("wl.dat");
        dictionary.loadWordPairList("pl.dat");
        dictionary.loadWordGroupList("gl.dat");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            // fixes the issue that the screen appears white only when it starts
            public void run() {
                new View();
            }
        });
    }

}
