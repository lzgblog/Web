package com.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm{

	//给用户进行授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//principal获取用户信息
		Object result = principal.getPrimaryPrincipal();
		Set<String> roles = new HashSet();
		roles.add("user");//默认添加用户拥有uesr的权限  并放入SimpleAuthorizationInfo中
		if("admin".equals(result)){ //判断用户是否是admin
			roles.add("admin");//添加admin权限
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		return info;
	}

	//进行登入权限验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken=(UsernamePasswordToken)token;
		String username = uptoken.getUsername();
		if("unadmin".equals(username)){
			throw new UnknownAccountException("用户不存在"); 
		}
		
		//进行权限认证
		//Object principal=username; //用户名
		//Object credentials="123456";//密码
		//String realmName=getName();//调用父类的getName()即可
		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, realmName);
		
		//使用MD5加密的方式
		//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
				Object principal = username;
				//2). credentials: 密码. 
				Object credentials = null; //"fc1709d0a95a6be30bc5926fdb7f22f4";
				if("admin".equals(username)){
					credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
				}else if("user".equals(username)){
					credentials = "098d2c478e9c11555ce2823231e02ec1";
				}
				
				//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
				String realmName = getName();
				//4). 盐值. 
				ByteSource credentialsSalt = ByteSource.Util.bytes(username);
				
				SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal, credentials, realmName);
				info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
				return info;
			}
		//打印md5加密后的值
		public static void main(String[] args) {
			/*String hashAlgorithmName = "MD5";
			Object credentials = "123456";
			Object salt = ByteSource.Util.bytes("user");;
			int hashIterations = 1024;
				
			Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			System.out.println(result);*/
			String s="0";
			Integer i=0;
			System.out.println(s.equals(i));
			System.out.println(i.equals(Integer.parseInt(s)));
			Integer o = Integer.valueOf(s);
			System.out.println(o instanceof Integer);
		}
}

