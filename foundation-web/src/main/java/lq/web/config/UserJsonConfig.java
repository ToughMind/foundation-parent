package lq.web.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 通过properties文件读取json文件路径，再将json字符串反序列化为对象，存入内存中。 一般用于前期数据不存数据库，代替管理后台的配置。
 * 
 * @author 刘泉
 * @data 2016年10月9日 下午3:38:14
 */
@Configuration
public class UserJsonConfig extends Thread {
	
	private static final Logger logger = LoggerFactory.getLogger(UserJsonConfig.class);

	@Value("${user.json.path}")
	private String filePath;

	/**
	 * 检测更新的时间周期
	 */
	private long delay = 10000L;

	private boolean reload = true;

	/**
	 * 资源文件的更新时间
	 */
	private long lastModifyTime = 0;

	/**
	 * 自动重加载是否终止
	 */
	private boolean interrupted = false;

	private String srcJson;
	
	@PostConstruct
	public void init() {
		checkAndConfig();
		if (reload) {
			setDaemon(true);
			start();
		}
	}
	
	/**
	 * 检查数据文件是否有更改，决定是否重新载入数据。
	 *  
	 * @author 刘泉
	 * @data 2016年10月9日 下午4:16:17
	 */
	private void checkAndConfig() {
		Resource res = new ClassPathResource(filePath);
		if (!res.exists()) {
			logger.error(" ===> [op: check FAILED] Config: file is not exist. filePath={}", filePath);
			return;
		}
		// 检测是否修改
		long modifyTime = 0;
		try {
			modifyTime = res.lastModified();
		} catch (Exception e) {
			logger.error(" ===> [op: check FAILED] Config: can not get config file's last modify time.", e);
			return;
		}
		if (modifyTime > lastModifyTime) {
			lastModifyTime = modifyTime;
			doChange(res);
		}
	}

	/**
	 * 从资源文件中读取数据，并刷新内存数据。
	 * 
	 * @author 刘泉
	 * @data 2016年10月9日 下午4:31:03
	 */
	private void doChange(Resource res) {
		logger.info(" ===> [op: change] Config: refresh user json config.");
		File file = null;
		BufferedReader in = null;
		try {
			file = res.getFile();
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			StringBuilder json = new StringBuilder(1024);
			String line;
			while ((line = in.readLine()) != null) {
				json.append(line.trim());
			}
			this.srcJson = json.toString();
		} catch (Exception e) {
			logger.error(" ===> [op: change FAILED] Config: read json config FAILED.", e);
		} finally { 
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				logger.error(" ===> [op: change FAILED] Config: IO exception", e);
			}
		}
	}
	
	@Override
	public void run() {
		while(!this.interrupted) {
			try {
				Thread.sleep(this.delay);
				checkAndConfig();
			} catch (Exception e) {
				logger.error(" ===> [op: reload FAILED] reload config file error.", e);
			}
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	public long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public boolean isInterrupted() {
		return interrupted;
	}

	public void setInterrupted(boolean interrupted) {
		this.interrupted = interrupted;
	}

	public String getSrcJson() {
		return srcJson;
	}

	public void setSrcJson(String srcJson) {
		this.srcJson = srcJson;
	}

}
