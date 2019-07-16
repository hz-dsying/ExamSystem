package com.zzxx.exam.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JLabel title;
    private JLabel bianhao;
    private JLabel mima;
    private JTextField id;
    private JPasswordField password;
    private JButton login;
    private JButton cancel;
    private ClientContext clientContext;
    private JLabel tishi;

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public LoginFrame(){
        title = new JLabel("登录考试系统");
        bianhao = new JLabel("编号：");
        mima = new JLabel("密码：");
        id = new JTextField(20);
        password = new JPasswordField(20);
        login = new JButton("Login");
        cancel = new JButton("Cancel");
        tishi = new JLabel();
        init();
    }

    public void MyListener(){
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientContext.login();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.cancel();
            }
        });
    }

    public JPanel createIn(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        JPanel panel1 = new JPanel();
        panel1.add(bianhao);
        panel1.add(id);
        JPanel panel2 = new JPanel();
        panel2.add(mima);
        panel2.add(password);
        panel.add(panel1);
        panel.add(panel2);
        return panel;
    }

    public JPanel createSelect(){
        JPanel panel = new JPanel();
        panel.add(login);
        panel.add(cancel);
        return panel;
    }

    public void init(){
        JPanel panel = new JPanel();
        this.setTitle("登录系统");
        panel.setLayout(null);
        title.setSize(500,60);
        title.setLocation(0,0);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);

        JPanel panel1 = createIn();
        panel1.setSize(500,100);
        panel1.setLocation(0,60);
        panel.add(panel1);

        tishi.setSize(500,60);
        tishi.setLocation(0,135);
        tishi.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(tishi);

        JPanel panel2 = createSelect();
        panel2.setSize(500,100);
        panel2.setLocation(0,200);
        panel.add(panel2);

        MyListener();

        this.add(panel);
        this.setSize(500,300);
         // 设置窗口大小固定
		this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextField getId() {
        return id;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void showMessage(String message) {
        tishi.setText(message);
    }
}
