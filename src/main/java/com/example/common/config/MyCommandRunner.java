package com.example.common.config;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.util.logging.Logger;

public class MyCommandRunner implements CommandLineRunner {
    private static Logger logger= (Logger) LoggerFactory.getLogger(MyCommandRunner.class);

    @Value("${spring.web.loginurl}")
    private String loginUrl;

    @Value("${spring.web.googleexcute}")
    private String googleExcutePath;

    @Value("${spring.auto.openurl}")
    private boolean isOpen;



//    @Override
//    public void run(String... args) throws Exception {
//        if(isOpen){
////            String cmd = googleExcutePath +" "+ loginUrl;
//            Runtime run = Runtime.getRuntime();
//            try{
////                run.exec(cmd);
//                Runtime.getRuntime().exec("cmd /c start " + loginUrl);
//                logger.info("启动浏览器打开项目成功");
//            }catch (Exception e){
//                e.printStackTrace();
//                logger.info(e.getMessage());
//            }
//        }
//
//    }

    @Override
    public void run(String... args) throws Exception{
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8888/index.html");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
