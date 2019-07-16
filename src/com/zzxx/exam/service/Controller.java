package com.zzxx.exam.service;

import com.zzxx.exam.bean.EntityContext;
import com.zzxx.exam.bean.Paper;
import com.zzxx.exam.bean.Question;
import com.zzxx.exam.bean.User;
import com.zzxx.exam.ui.ClientContext;
import com.zzxx.exam.ui.IdOrPwdException;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 控制器：完成业务 开始考试、登录、上一题、下一题
 *
 *      数据向数据库要
 */
public class Controller {
    private ClientContext clientContext;
    private EntityContext entityContext;

    public Controller() throws IOException {
        entityContext = new EntityContext();
    }

    public void setCc(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public void setEntityContext(EntityContext entityContext) {
        this.entityContext = entityContext;
    }

    // login
    public User login(String id, String passward) throws IOException, IdOrPwdException {
        User user = entityContext.getUserByID(id);
        if (user == null){
            // 提示id错误
            throw new IdOrPwdException("用户名错误！");
        }else if (!user.getPassword().equals(passward)){
            // 提示密码错误
            throw new IdOrPwdException("密码错误！");
        }else {
            return user;
        }
    }

    // createPaper
    public Map<Integer, Question> createPaper(Paper paper) throws IOException {
        Map<Integer, List> allQuestions = new EntityContext().getAllQuestions();
        Map<Integer, Question> questions = paper.getQuestions();
        int a = 1;
        for (int i = 1; i <= allQuestions.size(); i++){
            List<Question> list = allQuestions.get(i);
            int num = (int) (Math.random()* list.size());
            questions.put(a++, list.get(num));
            int num1 = (int) (Math.random()*list.size());
            while (num1 == num){
                num1 = (int) (Math.random()*list.size());
            }
            questions.put(a++, list.get(num1));
        }
        return questions;
    }

    public void startExam(){
        // 获得用户 loginUser   考试科目：先确定    考试时间：自定义
        // 创建一个试卷
        // 获得试卷中第一道题
    }

    // 上一题
    public int beforeQuestion(int num){
        if (num >= 2 && num <= 20){
            num --;
        }
        return num;
    }

    public EntityContext getEntityContext() {
        return entityContext;
    }

    // 下一题num ++;
    public int nextQuestion(int num){
        if (num >= 1 && num <= 19){

        }
        return num;
    }

    // 获取用户答案
    public List getUserAnswers(JCheckBox[] options){
        List answers = new ArrayList();
        for (int i = 0; i < 4; i++){
            if (options[i].isSelected()){
                answers.add(i);
            }
        }
        return answers;
    }

    // 考试规则
    public String readRule() {
        return entityContext.getRule();
    }


    // 交卷

    // 计算分数
    public int getScore(Map<Integer, List> answers, Map<Integer, Question> questions) {
        int score = 0;
        for (int i = 1; i <= answers.size(); i++){
            List userAns = answers.get(i);
            List trueAns = questions.get(i).getTrueAnswer();
            if (userAns.equals(trueAns)){
                score = score + 5;
            }
        }
        return score;
    }
}
