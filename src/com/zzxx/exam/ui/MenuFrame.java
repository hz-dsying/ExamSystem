package com.zzxx.exam.ui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MenuFrame extends JFrame {
    private JLabel tu1;
    private JLabel tu2;
    private JLabel t1;
    private JLabel start;
    private JLabel score;
    private JLabel rule;
    private JLabel leave;
    private JLabel w1;
    private JLabel w2;
    private JLabel w3;
    private JLabel w4;
    private ImageIcon img;
    private ClientContext clientContext;

    public MenuFrame(){
        img = new ImageIcon("ExamSystem/src/img/tou.png");
        tu1 = new JLabel(setImg(img, 100, 100));
        img = new ImageIcon("ExamSystem/src/img/biaoti.png");
        tu2 = new JLabel(setImg(img, 500, 100));
        img = new ImageIcon("ExamSystem/src/img/start.png");
        start = new JLabel(setImg(img, 100, 100));
        img = new ImageIcon("ExamSystem/src/img/score.png");
        score = new JLabel(setImg(img, 100, 100));
        img = new ImageIcon("ExamSystem/src/img/rule.png");
        rule = new JLabel(setImg(img, 100, 100));
        img = new ImageIcon("ExamSystem/src/img/leave.png");
        leave = new JLabel(setImg(img, 100, 100));
        t1 = new JLabel("陈贺贺" + "同学你好！");
        w1 = new JLabel("开始考试");
        w2 = new JLabel("  成绩查询");
        w3 = new JLabel("   考试规则");
        w4 = new JLabel("      离开");
        init();
    }

    private ImageIcon setImg(ImageIcon img, int width, int height){
        img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return img;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    private JPanel createHead(){
        JPanel panel = new JPanel();
        panel.add(tu1);
        panel.add(tu2);
        return panel;
    }

    private JPanel createCenter(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        t1.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 25));
        t1.setLocation(300, 30);
        t1.setSize(200, 30);
        panel.add(t1);
        JPanel panel1 = new JPanel();
        panel1.add(start);
        panel1.add(score);
        panel1.add(rule);
        panel1.add(leave);
        panel1.setSize(600,100);
        panel1.setLocation(100,100);
        panel.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,4));
        panel2.add(w1);
        panel2.add(w2);
        panel2.add(w3);
        panel2.add(w4);
        panel2.setSize(400,100);
        panel2.setLocation(220,180);
        panel.add(panel2);
        return panel;
    }

    public void MyListener(){
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    clientContext.startExam();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        score.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    clientContext.getGrades();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                score.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        rule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clientContext.readRule();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                rule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        leave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clientContext.leaveMenu();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                leave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }

    public void init(){
        this.setTitle("指针信息在线测评");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(createHead(), BorderLayout.NORTH);
        panel.add(createCenter(), BorderLayout.CENTER);
        MyListener();
        this.add(panel);
        this.setSize(800, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(String name) {
        t1.setText(name + "同学你好！");
    }
}
