package thinking.in.spring.boot.samples.spring3.server;

/**
 * 服务器接口
 * @version 2019/10/17 10:54
 */

public interface Server {

	/**
	 * 启动
	 */
	void start();

	/**
	 * 停止
	 */
	void stop();

	enum Type{

		HTTP, // HTTP 服务器
		FTP   // FTP 服务器
	}
}
