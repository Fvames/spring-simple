package thinking.in.spring.boot.samples.spring3.server;

import org.springframework.stereotype.Component;

/**
 * Http 服务器
 * @version 2019/10/17 11:03
 */
@Component
public class HttpServer implements Server{
	@Override
	public void start() {
		System.out.println("HTTP 服务器 启动......");
	}

	@Override
	public void stop() {
		System.out.println("HTTP 服务器 关闭......");
	}
}
