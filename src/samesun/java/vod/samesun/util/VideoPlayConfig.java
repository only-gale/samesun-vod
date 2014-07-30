/*
 *  Copyright 2004 Clinton Begin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package vod.samesun.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * Wrapper class to simplify use of DBCP
 */
public class VideoPlayConfig {

	/**
	 * 
	 * for Singleton pattern
	 * 
	 */

	static Logger logger = Logger.getLogger(VideoPlayConfig.class);

	private static VideoPlayConfig m_instance = null;

	/**
	 * the path of properties for Database configuration file;
	 */

	public String videoplay_videoFileExt = "";// videoplay.videoFileExt=mp4
	public String videoplay_Absolute_BasePath = "";

	/**
	 * Constructor to supply a map of properties
	 * 
	 * @param properties
	 *            - the map of configuration properties
	 * @throws Exception
	 */

	private VideoPlayConfig() throws Exception {
		try {
			Properties props = getProperties();
			if (props != null) {
				ProperConfiguration(props);
			}

		} catch (Exception e) {
			throw new Exception(
					"Error initializing DbcpDataSourceFactory.  Cause: " + e);
		}
	}

	/**
	 * Sigleton Design Pattern
	 * 
	 * 
	 * 
	 */
	synchronized public static VideoPlayConfig getInstance() {
		if (m_instance == null) {
			try {
				m_instance = new VideoPlayConfig();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.toString());
			}
		}

		return m_instance;
	}

	@SuppressWarnings("unused")
	private void ProperConfiguration(Properties properties) {

		BasicDataSource basicDataSource = null;

		if (properties.containsKey("videoplay.videoFileExt")) {
			basicDataSource = new BasicDataSource();

			// 录频文件存在的相对目录
			videoplay_Absolute_BasePath = (String) properties
					.get("videoplay.absolute.basePath");
			// daaSource=basicDataSource;
			videoplay_videoFileExt = (String) properties
					.get("videoplay.videoFileExt");
		}
	}

	public Properties getProperties() {

		// Java.properties
		Properties props = new Properties();
		try {
			props.load(VideoPlayConfig.class.getClassLoader()
					.getResourceAsStream("VideoPlay.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return props;
	}

	public static void main(String[] args) {
		/*
		 * String str= VideoPlayConfig.getInstance().videoplay_videoFileExt;
		 * str=str+"---- "+
		 * VideoPlayConfig.getInstance().videoplay_Absolute_BasePath;
		 * System.out.println("====="+str);
		 */
	}

}
