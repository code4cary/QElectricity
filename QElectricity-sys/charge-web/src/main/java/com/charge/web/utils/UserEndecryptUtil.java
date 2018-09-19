package com.charge.web.utils;

import com.charge.entity.po.wechat.user.User;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.Key;

public class UserEndecryptUtil {
	/**
	 * base64进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytBase64(String password) {
		byte[] bytes = password.getBytes();
		return Base64.encodeToString(bytes);
	}

	/**
	 * base64进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptBase64(String cipherText) {
		return Base64.decodeToString(cipherText);
	}

	/**
	 * 16进制加密
	 * 
	 * @param password
	 * @return
	 */
	public static String encrytHex(String password) {
		byte[] bytes = password.getBytes();
		return Hex.encodeToString(bytes);
	}

	/**
	 * 16进制解密
	 * 
	 * @param cipherText
	 * @return
	 */
	public static String decryptHex(String cipherText) {
		return new String(Hex.decode(cipherText));
	}

	public static String generateKey() {
		AesCipherService aesCipherService = new AesCipherService();
		Key key = aesCipherService.generateNewKey();
		return Base64.encodeToString(key.getEncoded());
	}


	/**
	 *	对openid和session_key进行md5加密,并返回密文，包含在User对象中
	 *
	 * @param openId
	 *
	 * @param session_key
	 *
	 * @param hashIterations
	 *
     * @return
     */
	public static String md5OpenIDSessionKey(String openId, String session_key, int hashIterations) {
		SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
		String salt = secureRandomNumberGenerator.nextBytes().toHex();

		// 组合session_key,两次迭代,对openid和session_key进行加密  skey为登录态
		String skey = new Md5Hash(openId, session_key + salt, hashIterations).toBase64();

		return skey;
	}

	/**
	 * 对密码进行md5加密,并返回密文和salt，包含在User对象中
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param hashIterations
	 *            迭代次数
	 * @return UserEntity对象，包含密文和salt
	 */
	public static User md5Password(String userName, String password, int hashIterations) {
		SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
		String salt = secureRandomNumberGenerator.nextBytes().toHex();
		// 组合username,两次迭代，对密码进行加密
		String password_cryto = new Md5Hash(password, userName + salt, hashIterations).toBase64();
		User user = new User();

		return user;
	}

//	public static void main(String[] args) {
//		User md5pwd = md5Password("12@qq.com", "123456", 2);
//		System.out.println("UcsN9eTNVUpNuWqA4FZ9Ng==" + " aes解密的明文是：" + md5pwd.getPassword() + "   "
//				+ md5pwd.getCredentialsSalt());
//	}

	public static String getCrptPwdByUserSalt(String userName, String password,String salt,int hashIterations){
		 // 组合username,两次迭代，对密码进行加密
		return new Md5Hash(password, userName + salt, hashIterations).toBase64();
	}
}
