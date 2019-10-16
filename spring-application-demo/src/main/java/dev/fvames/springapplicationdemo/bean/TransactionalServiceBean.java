package dev.fvames.springapplicationdemo.bean;

import dev.fvames.springapplicationdemo.annotation.TransactionalService;

/**
 * @version 2019/10/16 9:58
 */
@TransactionalService(name = "transactionalServiceBean", manager = "txManager")
public class TransactionalServiceBean {

	public void save() {
		System.out.println("保存操作。。。。");
	}
}
