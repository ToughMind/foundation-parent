package lq.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 注解形式静态读取properties文件。
 * 
 * @author 刘泉
 * @data 2016年10月9日 上午10:31:23
 */
@Configuration
public class WebConfig {
	@Value("${environment}")
	private String environment;

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
