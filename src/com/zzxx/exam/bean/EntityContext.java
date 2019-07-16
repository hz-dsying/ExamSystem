package com.zzxx.exam.bean;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟数据库
 * 提供数据 - get方法
 */
public class EntityContext {
    private Map<Integer, List> allQuestions;
    private Map<String, User> users;
    private String rule;

    public EntityContext() throws IOException {
        jxQuestion();
        jxUser();
        jxRule();
    }

    public Map<Integer, List> getAllQuestions() {
        return allQuestions;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public String getRule() {
        return rule;
    }



    // 试题解析
    private Map<Integer, List> jxQuestion() throws IOException {
        allQuestions = new HashMap();
        // 类路径 getResouse
        String path = EntityContext.class.getResource("../util/corejava.txt").getFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
        String str = null;
        int level = 0;
        int score = 0;
        int ans = 0;
        List trueAnswer = null;
        while ((str = br.readLine()) != null){
            Question q = new Question();
            q.setInformation(str);
            String[] ss = str.split(",");
            String[] answers = ss[0].split("/");
            trueAnswer = new ArrayList();
            for (int i = 0; i < answers.length; i++){
                ans = Integer.valueOf(answers[i].substring(answers[i].length() - 1));
                trueAnswer.add(ans);
            }
            score = Integer.valueOf(ss[1].substring(ss[1].length() - 1));
            level =Integer.valueOf(ss[2].substring(ss[2].lastIndexOf("=") + 1));
            q.setTrueAnswer(trueAnswer);
            q.setScore(score);
            q.setLevel(level);
            q.setTigan(br.readLine());
            q.setAnswer0(br.readLine());
            q.setAnswer1(br.readLine());
            q.setAnswer2(br.readLine());
            q.setAnswer3(br.readLine());
            List list = allQuestions.get(level);
            if (list == null){
                list = new ArrayList();
                list.add(q);
                allQuestions.put(level, list);
            }else {
                list.add(q);
                allQuestions.put(level, list);
            }
        }
        br.close();
        return allQuestions;
    }

    // 用户解析
    private Map<String, User> jxUser() throws IOException {
        // 类路径 getResouse
        String path = EntityContext.class.getResource("../util/user.txt").getFile();
        users = new HashMap<String, User>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
        String str = null;
        while ((str = br.readLine()) != null){
            // 跳过注释和空行
            if (str.startsWith("#") || str.trim().equals("")){
                continue;
            }
            String[] every = str.split(":");
            User user = new User(every[0], every[1], every[2], every[3], every[4]);
            users.put(every[0], user);
        }
        return users;
    }

    // 规则解析
    private String jxRule() throws IOException {
        // 类路径 getResouse
        String path = EntityContext.class.getResource("../util/rule.txt").getFile();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
        String str = null;
        while ((str = br.readLine()) != null){
            sb.append(str);
            sb.append("\n");
        }
        rule = sb.toString();
        return rule;
    }

    public User getUserByID(String id) {
        User user = users.get(id);
        return user;
    }
}
