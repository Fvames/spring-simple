package thinking.in.spring.boot.samples.spring3.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import thinking.in.spring.boot.samples.spring3.server.FtpServer;
import thinking.in.spring.boot.samples.spring3.server.HttpServer;
import thinking.in.spring.boot.samples.spring3.server.Server;

import java.util.Map;

/**
 * 服务器 {@link ImportSelector} 实现
 * @version 2019/10/17 10:56
 */

public class ServerImportSelector implements ImportSelector{


	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableServer.class.getName());
		Server.Type type = (Server.Type) annotationAttributes.getOrDefault("type", Server.Type.HTTP);

		String[] importClassNames = new String[0];
		switch (type) {
			case FTP:
				importClassNames = new String[]{FtpServer.class.getName()};
				break;
			case HTTP:
				importClassNames = new String[]{HttpServer.class.getName()};
				break;
		}

		return importClassNames;
	}
}
