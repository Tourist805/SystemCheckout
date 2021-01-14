package CW.view;

import javax.swing.*;

public class Welcome extends JFrame {
    public Welcome(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Welcome");
        setSize(400, 200);
        add(new JLabel("Congratulations, you've successfully executed payment"));
        setVisible(true);
    }
}
