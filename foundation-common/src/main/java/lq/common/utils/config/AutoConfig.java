package lq.common.utils.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * 抽象类，可自动重新载入资源配置。属性注入由具体子类实现,体现出了java的多态性。
 * 
 * @author 刘泉
 * @data 2016年10月9日 下午7:48:50
 */
public abstract class AutoConfig { 
	
	public abstract String getFileName();
	
	public abstract boolean getReload();
	
	public Set<String> getKeys() {
		FileConfigWatch fcw = FileConfigWatch.getFileConfigWatch(this.getFileName(), this.getReload());
		return fcw.getKeys();
	}
	
	public String getValue(String key) {
		FileConfigWatch fcw = FileConfigWatch.getFileConfigWatch(this.getFileName(), this.getReload());
		return fcw.getValue(key);
	}
	
	public String getValue(String key, String def) {
		String value = this.getValue(key);
		if (StringUtils.isEmpty(value)) {
			return def;
		}
		return value;
	}
	
	public int getInt(String key, int def) {
		String value = getValue(key);
		if (StringUtils.isEmpty(value)) {
			return def;
		}
		return Integer.valueOf(value).intValue();
	}
	
	public long getLong(String key, long def) {
		String value = getValue(key);
		if (StringUtils.isEmpty(value)) {
			return def;
		}
		return Long.valueOf(value).longValue();
	}
	
	public boolean getBoolean(String key, boolean def) {
		String value = getValue(key);
		if (StringUtils.isEmpty(value)) {
			return def;
		}
		return Boolean.valueOf(value).booleanValue();
	}
	
	public Date getDate(String key, Date def) {
		String value = getValue(key);
		if (StringUtils.isEmpty(value)) {
			return def;
		}
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return def;
	}
	 
}
