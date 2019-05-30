# springBoot-dubbo
springboot与dubbo整合

注意事项：一定要先下载zookeeper，然后配置好zookeeper的配置文件

详见：https://github.com/Torn1996/dubbo_demo

### 接口类（interface）

```java
public interface IHello {
    public String say(String msg);
}
```

### 提供者（provider）

#### 	配置文件

```properties
#配置应用名称
dubbo.application.name=provider
#配置注册中心地址
dubbo.registry.address=zookeeper://127.0.0.1:2181
#配置协议名称
dubbo.protocol.name=dubbo
#配置协议端口
dubbo.protocol.port=20880
```

#### 	POM文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.cgt.springboot</groupId>
    <artifactId>dubbo-provider</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dubbo-provider</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- 导入需要的接口interface.jar包-->
        <dependency>
            <groupId>com.cgt.springboot.dubbo</groupId>
            <artifactId>dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/interface.jar</systemPath>
        </dependency>
        <!-- 导入springboot和dubbo整合的jar包-->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <includeSystemScope>true</includeSystemScope>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

#### 	实现类

```java
@Service    //声明组件
@com.alibaba.dubbo.config.annotation.Service //暴露服务
public class IHelloController implements IHello {
    @Override
    public String say(String s) {
        System.out.println("你好：" + s);
        return s;
    }
}
```

#### 	启动程序

```java
@SpringBootApplication
@EnableDubbo    //开启dubbo注解
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
```

### 消费者（consumer）

#### 	配置文件

```properties
#配置应用的名称
dubbo.application.name=consumer
#配置注册中心的地址
dubbo.registry.address=zookeeper://127.0.0.1:2181
```

#### 	POM文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.5.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.cgt.springboot</groupId>
    <artifactId>dubbo-consumer</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>dubbo-consumer</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 导入springboot和dubbo整合的jar包-->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.0</version>
        </dependency>
        <dependency>
            <groupId>com.cgt.springboot</groupId>
            <artifactId>dubbo-interface</artifactId>
            <version>1.0-SNAPSHOT</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/interface.jar</systemPath>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <includeSystemScope>true</includeSystemScope>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

#### 	Controller类

```java
@Controller
public class IHelloController {
    @Reference  //引用注册中心
    IHello hello;
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("msg")String msg){
        return hello.say(msg);
    }
}
```

#### 	启动程序

```java
@SpringBootApplication
@EnableDubbo
public class DubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
```