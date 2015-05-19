package com.tuanlv.logic;

import java.util.ArrayList;

import com.tuanlv.protocal.Request;
import com.tuanlv.protocal.Response;

/**
 * ������Ҫ���ܵ�ʵ��
 * @author ITcms
 *
 */
public class ThisPhone {
	public static MyContext myContext = new MyContext();
	public static ArrayList<Response> responseList = new ArrayList<Response>();
	/**
	 * ִ�е�¼����
	 */
	public static String login(String username, String password){
		//���ɵ�¼����
		Request rq = Request.login(username, password);
		//��������
		String result = SocketControl.sendRequest(rq);
		//������ͳɹ������޸������ı�ʾ���ڵ�¼
		if(result.equals(MyMessage.SUCCESS)){
			myContext.loginUpdate(username, password);
		}
		return result;
	}
	/**
	 * ִ��ע�����
	 * @param username
	 * @param password
	 * @return
	 */
	public static String register(String username, String password){
		Request rq = Request.register(username, password);
		
		String result = SocketControl.sendRequest(rq);
		if(result.equals(MyMessage.SUCCESS)){
			myContext.registerUpdate(username, password);
		}
		
		return result;
	}
	public static String logout(String username){
		Request rq = Request.logout(username);
		
		String result = SocketControl.sendRequest(rq);
		//������ͳɹ������޸������ı�ʾ���ڵ�¼
		if(result.equals(MyMessage.SUCCESS)){
			myContext.logoutUpdate(username);
		}
		return result;
	}
	/**
	 * ������Ӧ��������������
	 * @return
	 */
	public static String dealResponse(){
		while(responseList != null && responseList.size() != 0){
			Response currentResponse = responseList.remove(0);
			//����Ӧ���д���
			myContext.update(currentResponse);
		}
		return MyMessage.SUCCESS;
	}
	public static boolean isConn(){
		return SocketControl.getConnStatus();
	}
	public static String conn(){
		return SocketControl.conn();
	}
	public static String desConn(){
		return SocketControl.disConn();
	}
	public static String recieveDataFromServer(){
		Response rs = SocketControl.recieveResponse();
		if(rs == null){
			return MyMessage.ERROR;
		} else{
			responseList.add(rs);
			return MyMessage.SUCCESS;
		}
	}
}
