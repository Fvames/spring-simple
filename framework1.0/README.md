## mvc Java Project
## 加载配置
- application.properties 配置 scanPage
- web.xml 配置 dispatcherServlet
- 注解 controller、service、requestMapping、requestParam、autowired
 
## 初始化
- 加载配置文件
- 扫描包路径下文件保存类文件
- 利用反射机制存入 ioc 容器
- 进行依赖注入

## 运行
- servlet 的 doGet、doPost 请求执行到 doDispathcer
- method.invoke 执行方法
- resp.getWriter().write() 输出结果到页面