package learn.springmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描mapper接口
@MapperScan({"learn.springmybatis"})
@SpringBootApplication
public class SpringMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisApplication.class, args);
	}

}
