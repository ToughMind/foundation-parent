package lq.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lq.common.utils.config.AutoConfig;

/**
 * 注解形式读入其他properties文件路径，再利用父类工具类等实现动态加载属性文件内容。
 * 
 * @author 刘泉
 * @data 2016年10月10日 上午10:56:00
 */
@Configuration
public class UserConfig extends AutoConfig {
	
	@Value("${user.property.path}")
	private String fileName;
	private boolean reload = true;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public boolean getReload() {
		return this.reload;
	}
	
	public String getUserName() {
		return super.getValue("name", "default");
	}

}
