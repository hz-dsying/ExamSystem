package com.zzxx.exam.test;

import com.zzxx.exam.bean.EntityContext;
import com.zzxx.exam.service.Controller;
import com.zzxx.exam.ui.ClientContext;
import com.zzxx.exam.ui.ExamFream;
import com.zzxx.exam.ui.LoginFrame;
import com.zzxx.exam.ui.MenuFrame;

import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        ClientContext clientContext = new ClientContext();
        EntityContext entityContext = new EntityContext();
        LoginFrame loginFrame = new LoginFrame();
        MenuFrame menuFrame = new MenuFrame();
        ExamFream examFream = new ExamFream();

        // 依赖注入
        clientContext.setLoginFrame(loginFrame);
        clientContext.setC(controller);
        clientContext.setMenuFrame(menuFrame);
        clientContext.setExamFream(examFream);

        controller.setCc(clientContext);
        controller.setEntityContext(entityContext);

        loginFrame.setClientContext(clientContext);
        menuFrame.setClientContext(clientContext);
        examFream.setClientContext(clientContext);

        clientContext.showMe();
    }
}
