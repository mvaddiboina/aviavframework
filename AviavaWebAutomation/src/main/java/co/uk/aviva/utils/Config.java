package co.uk.aviva.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public static String getConfigData(String key) throws IOException {
		String basepath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(new File(basepath + "\\config\\config.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		return prop.get(key).toString();
	}

}
