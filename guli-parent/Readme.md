# 在开发项目时遇到的严重问题记录
## 1.
2020-12-20 23:04:17.934  INFO 9220 --- [  restartedMain] tech.jinguo.eduservice.EduApplication    : Starting EduApplication using Java 1.8.0_271 on jinguo with PID 9220 (D:\Projects\my_repository\guli\guli-parent\service\service-edu\target\classes started by Think in D:\Projects\my_repository\guli\guli-parent)
2020-12-20 23:04:17.935  INFO 9220 --- [  restartedMain] tech.jinguo.eduservice.EduApplication    : No active profile set, falling back to default profiles: default
2020-12-20 23:04:18.787  INFO 9220 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2020-12-20 23:04:18.788  INFO 9220 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-12-20 23:04:18.789  INFO 9220 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.41]
2020-12-20 23:04:18.847  INFO 9220 --- [  restartedMain] o.a.c.c.C.[Tomcat-5].[localhost].[/]     : Initializing Spring embedded WebApplicationContext
2020-12-20 23:04:18.848  INFO 9220 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 893 ms
2020-12-20 23:04:19.065  WARN 9220 --- [  restartedMain] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'eduTeacherController': Unsatisfied dependency expressed through field 'teacherService'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'eduTeacherServiceImpl': Unsatisfied dependency expressed through field 'baseMapper'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'eduTeacherMapper' defined in file [D:\Projects\my_repository\guli\guli-parent\service\service-edu\target\classes\tech\jinguo\eduservice\mapper\EduTeacherMapper.class]: Unsatisfied dependency expressed through bean property 'sqlSessionFactory'; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'sqlSessionFactory' defined in class path resource [com/baomidou/mybatisplus/autoconfigure/MybatisPlusAutoConfiguration.class]: Unsatisfied dependency expressed through method 'sqlSessionFactory' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception; nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Failed to determine a suitable driver class
2020-12-20 23:04:19.066  INFO 9220 --- [  restartedMain] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2020-12-20 23:04:19.081  INFO 9220 --- [  restartedMain] ConditionEvaluationReportLoggingListener :  
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-12-20 23:04:19.096 ERROR 9220 --- [  restartedMain] o.s.b.d.LoggingFailureAnalysisReporter   :  
***************************
APPLICATION FAILED TO START
***************************
Description:
Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.
Reason: Failed to determine a suitable driver class
Action:
Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).

    解决办法： Tomcat端口号启动的不正确，SpringBoot没有正常启动，可以检查是否存在循环依赖,或者依赖配置的是否正确
               Menu->Analyze->Analyze Cyclic Dependencices，找出存在循环依赖的模块 
               比如A->B  B->A  A依赖了B B依赖了A,可以得到存在循环的依赖的模块
               然后在项目根目录Open Module Settings 对A模块中点击Decependences，移除B模块
               在B模块中点击Decependences，移除A模块
               其实在
                
## 2. Error:java: Annotation processing is not supported for module cycles. Please ensure that all modules from cycle [A,B] are excluded from annotation processing
    解决办法:查看maven多个模块的依赖，如modules 是否配置正确的子模块
    这个报错和上个报错连着出现
    
## 3.Error:(49, 12) java: 找不到符号
       符号:   类 Result
       位置: 类 tech.jinguo.eduservice.controller.EduTeacherController
1.查看maven配置文件，比如build时提示提示maven配置文件中profile配置有问题       
2.查看idea的Maven中的Runner有没有勾选->File-Settings-Build-Runner-Delegate IDE build/run actions to Maven，不勾选则没有链接到Maven,会出现包不存在的问题   
 
### 4.Could not find artifact tech.jinguo:common-utils:pom:0.0.1-SNAPSHOT 承接上个问题 
     依赖的模块没有打包
###  Failed to execute goal org.springframework.boot:spring-boot-maven-plugin:2.4.1:repackage (repackage) on project service-base: Execution repackage of goal org.springframework.boot:spring-boot-maven-plugin:2.4.1:repackage failed: Unable to find main class
解决办法：
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
 </build>
 这个插件不需要放在pom父项目中，放在需要运行的模块中
 如jar   service-base, common-base, service-edu
 不需要放在pom  guli, service, common模块下
 
 如果执行mvn clean package时Unable to find main class
 那么就配置成：
  <build>
         <plugins>
             <plugin>
                 <groupId>org.springframework.boot</groupId>
                 <artifactId>spring-boot-maven-plugin</artifactId>
                 <!--没有主类的时候配置-->
                 <configuration>
                     <skip>true</skip>
                 </configuration>
             </plugin>
         </plugins>
  </build>
  
   如果还是提示Could not find artifact
   说明没有把依赖的模块打包并部署到本地maven仓库
   
   
   mvn clean package依次执行了clean、resources、compile、testResources、testCompile、test、jar(打包)等７个阶段。
   mvn clean install依次执行了clean、resources、compile、testResources、testCompile、test、jar(打包)、install等8个阶段。
   mvn clean deploy依次执行了clean、resources、compile、testResources、testCompile、test、jar(打包)、install、deploy等９个阶段
   
   
   package命令完成了项目编译、单元测试、打包功能，但没有把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库和远程maven私服仓库
   install命令完成了项目编译、单元测试、打包功能，同时把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库，但没有布署到远程maven私服仓库
   deploy命令完成了项目编译、单元测试、打包功能，同时把打好的可执行jar包（war包或其它形式的包）布署到本地maven仓库和远程maven私服仓库
  
  
