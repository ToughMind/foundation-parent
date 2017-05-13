package lq.common.utils.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * 定时自动加载配置文件。
 * 
 * @author 刘泉
 * @data 2016年10月9日 下午8:41:02
 */
public class FileConfigWatch extends Thread{

	private static Logger logger = LoggerFactory.getLogger(FileConfigWatch.class);

	private static Map<String, FileConfigWatch> watchMap = new ConcurrentHashMap<String, FileConfigWatch>();

	private String fileName;

	private long delay = 6000L;

	private Resource file;

	private long lastModify = 0L;

	private boolean warnedAlready = false;

	/**
	 * 自动重加载是否终止
	 */
	private boolean interrupted = false;

	private Properties prop;
	
	public FileConfigWatch(String fileName, boolean reload) {
		this.fileName = fileName;
		this.file = new ClassPathResource(fileName);
		checkAndConfig();
		if (reload) {
			setDaemon(true);
			start();
		}
	}
	
	public static FileConfigWatch getFileConfigWatch(String fileName, boolean reload) {
		FileConfigWatch fcw = watchMap.get(fileName);
		if (fcw == null) {
			synchronized (watchMap) {
				fcw = watchMap.get(fileName);
				if (fcw == null) {
					fcw = new FileConfigWatch(fileName, reload);
					watchMap.put(fileName, fcw);
				}
			}
		}
		return fcw;
	}
	
	private void checkAndConfig() {
		boolean fileExists;
		try {
			fileExists = this.file.exists();
		} catch (Exception e) {
			logger.warn(" ===> [op: check] Config: not allowed to check file exist. file={}", this.fileName);
			this.interrupted = true;
			return;
		}
		if (!fileExists) {
			this.fileName = FilenameUtils.getName(this.fileName);
			try {
				URL url = Thread.currentThread().getContextClassLoader().getResource(this.fileName);
				if (url != null) {
					this.file = new FileSystemResource(new File(url.toURI()));
					fileExists = this.file.exists();
				}
			} catch (Exception e) {
				logger.error(" ===> [op: check] Config: check file exist FAILED. file={}", this.fileName, e);
			}
		}
		if (!fileExists) {
			try {
				Properties p = new Properties();
				p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.fileName));
				this.prop = p;
			} catch (Exception e) {
				
			}
		}
		if (fileExists) {
			try {
				long modify = this.file.lastModified();
				if (modify > this.lastModify) {
					this.lastModify = modify;
					doChange();
					this.warnedAlready = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (!this.warnedAlready) {
			this.warnedAlready = true;
		}
	}

	private void doChange() {
		logger.info(" ===> [op: change] Config: refresh config.");
		Properties p = new Properties();
		InputStream in = null;
		try {
			String innerFile = this.file.getFile().getAbsolutePath();
			BufferedReader bin = new BufferedReader(new InputStreamReader(new FileInputStream(innerFile), "UTF-8"));
			p.load(bin);
			this.prop = p;
		} catch (Exception e) {
			logger.error(" ===> [op: load FAILED] Config: file={}", this.fileName);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					logger.error(" ===> [op: change FAILED] Config: IO exception", e);
				}
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
	
	public Set<String> getKeys() {
		return this.prop.stringPropertyNames();
	}
	
	public String getValue(String key) {
		if (this.prop == null) {
			return null;
		}
		return this.prop.getProperty(key);
	}

}
