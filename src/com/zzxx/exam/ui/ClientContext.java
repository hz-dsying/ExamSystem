package com.zzxx.exam.ui;

import com.zzxx.exam.bean.Paper;
import com.zzxx.exam.bean.Question;
import com.zzxx.exam.bean.User;
import com.zzxx.exam.service.Controller;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 控制界面的切换：界面控制器
 * 所有的监听，调用这里的代码，数据从控制器获得
 *
 * 界面中，只有update方法，从界面控制器拿到数据，刷洗界面
 */
public class ClientContext {
    private Controller controller;
    private LoginFrame loginFrame;
    private MenuFrame menuFrame;
    private ExamFream examFream;
    private String id;
    private String name;
    private String password;
    private Map<Integer, Question> questions;
    private Map<Integer, List> answers;
    private int num;
    private boolean flag;

    public ClientContext(){
    }

    public void showMe(){
        loginFrame.setVisible(true);
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void setC(Controller controller) {
        this.controller = controller;
    }

    public void setMenuFrame(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    public void setExamFream(ExamFream examFream) {
        this.examFream = examFream;
    }

    // 登录界面切换
    public void login() throws IOException {

        try {
            id = loginFrame.getId().getText();
            password = new String(loginFrame.getPassword().getPassword());
            User loginUser = controller.login(id, password);
            if (loginUser != null){
                name = loginUser.getName();
                menuFrame.update(name);
                menuFrame.setVisible(true);
                loginFrame.setVisible(false);
            }
        } catch (IdOrPwdException e) {
            // 处理用户名/密码错误的异常提示
            loginFrame.showMessage(e.getMessage());
        }
    }

    // 开始考试
    public void startExam() throws IOException {


        Paper paper = new Paper();
        answers = paper.getUserAnswers();
        questions = controller.createPaper(paper);
        flag = true;
        num = 1;
        examFream.update(name, id);
        examFream.update(num, questions.get(num));
        menuFrame.setVisible(false);
        examFream.setVisible(true);
        Thread t = new Thread(new Runnable() {
            int h = 0;
            int m = 5;
            int s = 0;
            @Override
            public void run() {
                while (flag){
                    examFream.update(h,m,s);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (s == 0){
                        m--;
                        s = 60;
                    }
                    s--;
                }
            }
        });
        t.start();
    }

    // 显示考试规则
    public void readRule() {
        JOptionPane option = new JOptionPane();
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.CENTER_BASELINE, 23)));
        option.showMessageDialog(null, controller.readRule(),"考试规则",JOptionPane.ERROR_MESSAGE);
    }
    // 考试界面上一题
    public void beforeQuestion() {
        if (num == 1){
            JOptionPane option = new JOptionPane();
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.CENTER_BASELINE, 20)));
            option.showMessageDialog(null, "当前为第一题，已无上一题","提示",JOptionPane.INFORMATION_MESSAGE);
        }
        List list = controller.getUserAnswers(examFream.getOptions());
        answers.put(num,list);
        num = controller.beforeQuestion(num);
        examFream.update(num, questions.get(num));
        examFream.update(num, answers);
    }

    // 考试界面下一题
    public void nextQuestion() {
        if (num == 20){
            JOptionPane option = new JOptionPane();
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.CENTER_BASELINE, 20)));
            option.showMessageDialog(null, "已到达最后一题,请注意检查","提示",JOptionPane.INFORMATION_MESSAGE);
        }else {
            List list = controller.getUserAnswers(examFream.getOptions());
            answers.put(num,list);
            num = controller.nextQuestion(num);
            examFream.update(num, questions.get(num));
            examFream.update(num, answers);

        }
    }

    // 交卷返回菜单
    public void submit() {
        flag = false;
        List list = controller.getUserAnswers(examFream.getOptions());
        answers.put(num,list);
        int score = controller.getScore(answers, questions);
        new JOptionPane().showMessageDialog(null, "分数为：" + score,"提示",JOptionPane.INFORMATION_MESSAGE);
        examFream.setVisible(false);
        menuFrame.setVisible(true);
    }

    // 菜单点击离开
    public void leaveMenu() {
        menuFrame.setVisible(false);
        loginFrame.setVisible(true);
    }

    // 登录页面取消
    public void cancel() {
        int a = new JOptionPane().showConfirmDialog(null, "确认退出吗?", "取消", JOptionPane.YES_NO_OPTION);
        if(a == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
