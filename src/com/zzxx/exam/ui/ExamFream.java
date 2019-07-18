package com.zzxx.exam.ui;

import com.zzxx.exam.bean.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;


public class ExamFream extends JFrame {
    private JLabel bigtitle;
    private JLabel t1;
    private JLabel t2;
    private JLabel t3;
    private JLabel t4;
    private JLabel t5;
    private JTextArea timu;
    private JCheckBox[] options; // 选项
    private JLabel t6;
    private JButton shang;
    private JButton xia;
    private JButton jiao;
    private JLabel t7;
    private ClientContext clientContext;

    public ExamFream(){
        bigtitle = new JLabel("指针在线测评系统");
        t1 = new JLabel("姓名：" + "陈贺贺");
        t2 = new JLabel("编号：" + "1001");
        t3 = new JLabel("考试时间：1分钟");
        t4 = new JLabel("考试科目：" + "JavaSE阶段测试（一）");
        t5 = new JLabel("题目数量：20");
        timu = new JTextArea(15,100);
        options = new JCheckBox[4];
        options[0] = new JCheckBox("A");
        options[1] = new JCheckBox("B");
        options[2] = new JCheckBox("C");
        options[3] = new JCheckBox("D");
        t6 = new JLabel("题目：20 的 " + 5 + " 题");
        shang = new JButton("上一题");
        xia = new JButton("下一题");
        jiao = new JButton("交卷");
        t7 = new JLabel("剩余时间:" + "0:5:0");
        init();
    }

    public JPanel createInformation(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Font font = new Font("宋体", Font.CENTER_BASELINE, 21);
        t1.setFont(font);
        t1.setSize(150,50);
        t1.setLocation(0,0);
        t2.setFont(font);
        t2.setSize(150,50);
        t2.setLocation(180,0);
        t3.setFont(font);
        t3.setSize(180,50);
        t3.setLocation(330,0);
        t4.setFont(font);
        t4.setSize(400,50);
        t4.setLocation(530,0);
        t5.setFont(font);
        t5.setSize(150,50);
        t5.setLocation(900,0);
        panel.add(t1);
        panel.add(t2);
        panel.add(t3);
        panel.add(t4);
        panel.add(t5);
        return panel;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public JPanel createTimu(){
        JPanel panel = new JPanel();
        Font font = new Font("宋体", Font.CENTER_BASELINE, 20);
        timu.setFont(font);
        timu.setLineWrap(true);
        timu.setEditable(false);
        panel.add(timu);
        return panel;
    }

    public JPanel createButton(){
        JPanel panel = new JPanel();
        panel.add(options[0]);
        panel.add(options[1]);
        panel.add(options[2]);
        panel.add(options[3]);
        return panel;
    }

    public JPanel createFloor(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        Font font = new Font("宋体", Font.CENTER_BASELINE, 20);
        t6.setFont(font);
        t6.setSize(200,50);
        t6.setLocation(50,0);
        shang.setLocation(400,15);
        shang.setSize(100,30);
        xia.setLocation(550,15);
        xia.setSize(100,30);
        jiao.setLocation(700,15);
        jiao.setSize(100,30);
        t7.setForeground(Color.red);
        t7.setFont(font);
        t7.setSize(200,50);
        t7.setLocation(900,0);
        panel.add(t6);
        panel.add(shang);
        panel.add(xia);
        panel.add(jiao);
        panel.add(t7);
        return panel;
    }

    public JCheckBox[] getOptions() {
        return options;
    }

    public void MyListener(){
        shang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.beforeQuestion();
            }
        });
        xia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.nextQuestion();
            }
        });
        jiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.submit();
            }
        });
    }

    public void init(){
        this.setTitle("指针信息在线测评");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        bigtitle.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 40));
        bigtitle.setHorizontalAlignment(SwingConstants.CENTER);
        bigtitle.setSize(1200,100);
        bigtitle.setLocation(0,30);

        panel.add(bigtitle);

        JPanel panel1 = createInformation();
        panel1.setSize(1200,50);
        panel1.setLocation(50,150);

        JPanel panel2 = createTimu();
        panel2.setSize(1100,400);
        panel2.setLocation(40,200);

        JPanel panel3 = createButton();
        panel3.setSize(1100,50);
        panel3.setLocation(0,600);

        JPanel panel4 = createFloor();
        panel4.setSize(1100,50);
        panel4.setLocation(0,650);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);

        MyListener();

        this.add(panel);
        this.setSize(1200, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update(String name, String id) {
        t1.setText("姓名：" + name);
        t2.setText("编号：" + id);
    }

    public void update(int num, Question question) {
        t6.setText("题目：20 的 " + num + " 题");
        timu.setText("题目：\n" + question.toString());
    }

    public void update(int num, Map<Integer, List> answers) {
        for(JCheckBox b : options){
            b.setSelected(false);
        }
        if (answers.get(num) != null){
            for (int i = 0; i < answers.get(num).size(); i++){
                options[(int) answers.get(num).get(i)].setSelected(true);
            }
        }
    }

    public void update(int h, int m, int s) {
        t7.setText("剩余时间:" + h + ":" + m + ":" + s);
    }

    public String getExamName(){
        String str = t4.getText();
        return str.substring(5);
    }
}
