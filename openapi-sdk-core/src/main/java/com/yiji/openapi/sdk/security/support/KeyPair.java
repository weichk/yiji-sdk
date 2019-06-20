/*
 * acooly.cn Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * create by zhike@yiji.com 
 * date:2016年4月4日
 *
 */
package com.yiji.openapi.sdk.security.support;

import com.yiji.openapi.sdk.common.utils.RSA;
import com.yiji.openapi.sdk.common.utils.Strings;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author zhike@yiji.com
 */
public class KeyPair {

	private static final Log logger = LogFactory.getLog(KeyPair.class);

	/** 对方的公钥 */
	private String publicKeyPath;
	/** 自己的私钥 */
	private String privateKeyPath;

	private PublicKey publicKey;
	private PrivateKey privateKey;

	private boolean load = false;

	public KeyPair() {
		super();
	}

	public KeyPair(String publicKeyPath, String privateKeyPath) {
		super();
		this.publicKeyPath = publicKeyPath;
		this.privateKeyPath = privateKeyPath;
	}

	public void loadKeys() {
		if (load) {
			return;
		}
		if (Strings.isNotBlank(publicKeyPath)) {
			try {
				Resource resource = new DefaultResourceLoader().getResource(publicKeyPath);
				this.publicKey = RSA.loadPublicKey(IOUtils.toString(resource.getInputStream()), RSA.DEFAULT_KEY_ALGO,
						null);
				logger.info("load public key success. file:"+publicKeyPath+",info:\n"+this.publicKey);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (Strings.isNotBlank(privateKeyPath)) {
			try {
				Resource resource = new DefaultResourceLoader().getResource(privateKeyPath);
				this.privateKey = RSA.loadPrivateKey(IOUtils.toString(resource.getInputStream()), RSA.DEFAULT_KEY_ALGO,
						null);
				logger.info("load private key success. file:"+privateKeyPath+",info:\n"+this.privateKey);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		load = true;
	}

	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	public void setPublicKeyPath(String publicKeyPath) {
		this.publicKeyPath = publicKeyPath;
	}

	public String getPrivateKeyPath() {
		return privateKeyPath;
	}

	public void setPrivateKeyPath(String privateKeyPath) {
		this.privateKeyPath = privateKeyPath;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String toString() {
		return "{publicKeyPath:" + publicKeyPath + ", privateKeyPath:" + privateKeyPath + "}";
	}

}
