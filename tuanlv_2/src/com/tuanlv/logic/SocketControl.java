package com.tuanlv.logic;

import java.io.*;
import java.net.*;

import com.tuanlv.protocal.Request;
import com.tuanlv.protocal.Response;
import com.tuanlv.protocal.ServerInfo;

/**
 * ���ڹ���socket����
 * @author ITcms
 *
 */
public class SocketControl {
	private static boolean connStatus = false;
	private static Socket socket;
	private static InputStream sis;
	private static OutputStream sos;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	
	public static boolean getConnStatus(){
		return connStatus;
	}
	/**
	 * ���ӵ�������������ɹ����򷵻�success�����ʧ�ܣ��򷵻�netnotwork����error
	 * @return
	 */
	public static String conn(){
		try {
			socket = new Socket(ServerInfo.IP, ServerInfo.PORT);
			sis = socket.getInputStream();
			sos = socket.getOutputStream();
			ois = new ObjectInputStream(sis);
			oos = new ObjectOutputStream(sos);
			connStatus = true;
			return MyMessage.SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return MyMessage.ERROR;
			}
			connStatus = false;
			return MyMessage.NETNOTWORK;
		}
	}
	/**
	 * �����������Ϊ�գ��򷵻�error���������ʧ�ܣ��򷵻�netnotwork��������ͳɹ����򷵻�success
	 * @param rq
	 * @return
	 */
	public static String sendRequest(Request rq){
		if(oos == null){
			connStatus = false;
			return MyMessage.NETNOTWORK;
		}
		if(rq != null){
			try {
				oos.writeObject(rq);
				oos.flush();
				return MyMessage.SUCCESS;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				connStatus = false;
				return MyMessage.NETNOTWORK;
			}
		}
		return MyMessage.ERROR;
	}
	/**
	 * �������ݣ���������򷵻ؿգ�������ճɹ����򷵻ض���
	 * @return
	 */
	public static Response recieveResponse(){
		if(ois == null){
			return null;
		}
		Response currentResponse = null;
		try {
			currentResponse = (Response) ois.readObject();
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			connStatus = false;
			e.printStackTrace();
		}
		return currentResponse;
	}
	/**
	 * �Ͽ����ӣ��Ͽ��ɹ��򷵻�success�����򷵻�netnotwork
	 * @return
	 */
	public static String disConn(){
		try {
			if(oos != null)
				oos.close();
			if(ois != null)
				ois.close();
			if(socket != null)
				socket.close();
			connStatus = false;
			return MyMessage.SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connStatus = false;
			return MyMessage.NETNOTWORK;
		}
	}
}
