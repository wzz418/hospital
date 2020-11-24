package com.epidemiologicSurvey;

import org.nutz.boot.NbApp;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@IocBean
@Modules(scanPackage = true)
@IocBy(type = ComboIocProvider.class, args = { "*js", "ioc/", "*jedis", "*anno", "epidemiologicSurvey", "*tx" })
public class MainLauncher {

	public static void main(String[] args) throws Exception {
		new NbApp().setArgs(args).setPrintProcDoc(true).run();
	}

}
