package com.wh.webmanager.plugins.ueditor.config;

import javax.annotation.PostConstruct;

import com.wh.webmanager.plugins.ueditor.ActionEnter;
import com.wh.webmanager.plugins.ueditor.ConfigManager;
import com.wh.webmanager.plugins.ueditor.hunter.FileManager;
import com.wh.webmanager.plugins.ueditor.upload.StorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UeditorProperties.class)
@ConditionalOnClass(ActionEnter.class)
@ConditionalOnProperty(prefix="ueditor",value="enabled",matchIfMissing=true)
public class UeditorAutoConfiguration {

	@Autowired
	private UeditorProperties ueditorProperties;
	
	@Bean
	@ConditionalOnMissingBean(ActionEnter.class)
	public ActionEnter actionEnter(){
		ActionEnter actionEnter = new ActionEnter( new ConfigManager(ueditorProperties.getConfig()));
		return actionEnter;
	}
	@PostConstruct
	public void storagemanager(){
		StorageManager.accessKey = FileManager.accessKey = ueditorProperties.getAccessKey();
		StorageManager.secretKey = FileManager.secretKey = ueditorProperties.getSecretKey();
		StorageManager.baseUrl  = FileManager.baseUrl = ueditorProperties.getBaseUrl();
		StorageManager.bucket = FileManager.bucket  = ueditorProperties.getBucket();
		StorageManager.baseUrl = FileManager.baseUrl  = ueditorProperties.getBaseUrl();
		StorageManager.uploadDirPrefix = FileManager.uploadDirPrefix  = ueditorProperties.getUploadDirPrefix();
		StorageManager.zone = FileManager.zone  = ueditorProperties.getZoneObj();

		
	}
	
}
