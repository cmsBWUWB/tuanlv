package com.tuanlv.logic;

import java.io.*;
import java.net.*;

import com.tuanlv.protocal.Request;
import com.tuanlv.protocal.Response;
import com.tuanlv.protocal.ServerInfo;

/**
 * 用于管理socket连接
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
	 * 连接到服务器，如果成功，则返回success，如果失败，则返回netnotwork或者error
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
	 * 发送请求，如果为空，则返回error，如果发送失败，则返回netnotwork，如果发送成功，则返回success
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
	 * 接收数据，如果出错，则返回空，如果接收成功，则返回对象
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
	 * 断开连接，断开成功则返回success，否则返回netnotwork
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
