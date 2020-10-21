package com.epidemiologicSurvey;

import org.nutz.boot.NbApp;
import org.nutz.ioc.loader.annotation.*;

@IocBean
public class MainLauncher {

    public static void main(String[] args) throws Exception {
        new NbApp().setArgs(args).setPrintProcDoc(true).run();
    }

}
