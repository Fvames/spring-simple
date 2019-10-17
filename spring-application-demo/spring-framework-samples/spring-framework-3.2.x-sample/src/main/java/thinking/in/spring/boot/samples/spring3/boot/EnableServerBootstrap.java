package thinking.in.spring.boot.samples.spring3.boot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.boot.samples.spring3.annotation.EnableServer;
import thinking.in.spring.boot.samples.spring3.server.Server;

/**
 *
 * @version 2019/10/17 11:09
 */
@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootstrap {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(EnableServerBootstrap.class);

		context.refresh();

		Server server = context.getBean(Server.class);
		server.start();
		server.stop();

		context.close();
	}
}
