package CW.view;

import CW.controller.AllViewsController;
import CW.model.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;

import static javax.swing.LayoutStyle.ComponentPlacement.UNRELATED;

public class AdminLoginView extends AbstractView{
    private JTextField loginField;
    private JPasswordField passwordField;
    private Database database;
    private String filePath = "resources\\adminlogin.txt";
    private AllViewsController controllerAll;

    public AdminLoginView(Database database){
        this.database = database;
        database.registerObserver(this);
        initUI();
        initialize();
        setTitle("Login");
        setLocationRelativeTo(null);
        controller = new AllViewsController(database,this);
    }

    public void initUI(){
        var lblLogin = new JLabel("Login");
        var lblPassword = new JLabel("Password");

        loginField = new JTextField(15);
        passwordField = new JPasswordField(15);

        var submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitAction());

        createLayout(lblLogin, loginField, lblPassword, passwordField, submitButton);

//        setTitle("Login");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setModel(Database model) {
        this.database = database;
    }

    private class SubmitAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            doSubmitAction();
        }

        private void doSubmitAction(){
            String login = loginField.getText();
            char[] passwd = passwordField.getPassword();

            if (!login.isEmpty() && passwd.length != 0) {

                database.loadAdmin(new File(filePath));

                if(database.adminCheck(login, String.valueOf(passwd))){
                    controller.addAction("AdminWorkView", database);

                    System.out.println("Correct");
                    AdminLoginView.this.dispose();
                }
                else {
                    System.out.println("Wrong");
                    JOptionPane.showMessageDialog(AdminLoginView.this, "Incorrect password or login");
                }
            }

            Arrays.fill(passwd, '0');
        }
    }

    private void createLayout(Component... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
                .addGap(50)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(50)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1], GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(arg[2])
                        .addComponent(arg[3], GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addComponent(arg[4]))
                .addGap(50)
        );

        pack();
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
