package liu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Receives commands from View, after that, processes some information
 * and then puts them back the View.
 * @author Liu
 */

public class Model implements ActionListener {
    // View view = new View();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Chuyển")) {
            // View.setText_hannom(View.getText_quocngu());

            final int enterKeyUnicode = 10;
            String[] inputParagraphs = View.getText_quocngu().split((char) enterKeyUnicode + "");
            View.resetOutput();
            for (int i = 0; i < inputParagraphs.length; i++) {
                View.appendOutput(inputParagraphs[i]);
            }
        }
    }

}
