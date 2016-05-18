package framework.common;

import java.io.IOException;
import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.sprd.socket.SocketUtil;
import com.spreadtrum.itestapi.ITestApi;
import com.spreadtrum.itestapi.ITestApp;

import static framework.data.ObjectType.Object_Device;
import static framework.data.ObjectType.Object_ResIdInstance;
import static framework.data.OperationType.*;
import static framework.excute.Excute.*;

public class InteractionCommon {
	//mtCall
	private String testSN = DeviceCommon.getSerialNumber();
	private String simNum;
	private SocketUtil socketUtil = new SocketUtil();
	private ITestApi itest;
	private boolean isWakeup = true;
	private String [] EndCall = {"EndCall"};
	
	public static String mtCall_answer = "answer";
	public static String mtCall_reject = "reject";
	public static String mtCall_rejectBySMS = "rejectBySMS";
	public static String mtCall_wakeUp = "mtCall_wakeUp";
	public static String mtCall_sleep = "mtCall_sleep";
	public static String normalMtCall = "mtCall";
	public static String FireWall = "FireWall";
	
	public InteractionCommon(){}
	/**
	 * 交互初始化
	 * @param option
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public InteractionCommon(String option) throws IOException, InterruptedException{
		if(option.contains(normalMtCall))
		{
			Process proc = Runtime.getRuntime().exec("am start -n com.spreadtrum.itestapp/com.spreadtrum.itestapp.TestClientActivity");
		    int ret = proc.waitFor();
		    if (ret != 0)
		    	System.out.println("initial itestapp result:" + ret);
			itest = ITestApp.newInstance();
			socketUtil.startSocket();
			UiDevice.getInstance().pressHome();
			if(option.contains("sleep")){
				isWakeup = false;
				excute(Object_Device, Operation_Sleep);
			}
		}
		else if(option.contains(FireWall)){
			socketUtil.startSocket();
		}
	}
	/**
	 * 短信交互主函数
	 * @param option
	 * @throws IOException
	 */
	public void SMS(String simNumber) throws IOException{
		simNum = simNumber;
		int timeout = 7;//min
		socketUtil.sendMsg("sprdtest sms " + simNum + " 2 "+ testSN);
		Assert.assertTrue("send cmd fail!!!", readServerBack(simNumber,timeout,"SMS"));
		System.out.println("SMS request send to assistantDevice ok~~");
	}
	/**
	 * 兼容所有SIM卡情况
	 * @param option
	 * @throws IOException
	 */
	public void CmtCall(String option) throws IOException{
		switch(DeviceCommon.simFlag){
		case "00":
			Assert.assertTrue("No SIMCard in phone !!!",false);
			break;
		case "10":
			mtCall(DeviceCommon.sim1Num,option);
			break;
		case "01":
			mtCall(DeviceCommon.sim2Num,option);
			break;
		case "11":
			mtCall(DeviceCommon.sim1Num,option);
			Wait(2000);
			mtCall(DeviceCommon.sim2Num,option);
			break;
		default:
			Assert.assertTrue("Error: simFlag got error!!!",false);
			break;
		}
	}
	/**
	 * 被叫主函数
	 * @param simNumber
	 * @param option:"answer","reject","rejectBySMS"
	 * @throws IOException
	 */
	public void mtCall(String simNumber,String option) throws IOException{
		simNum = simNumber;
		int timeout = 7;//min
		socketUtil.sendMsg("sprdtest call " + simNum + " 2 "+ testSN);
		Assert.assertTrue("send cmd fail!!!", readServerBack(simNum,timeout,normalMtCall));
		System.out.println("mtCall request send to assistantDevice ok~~");
		
		if(option.equals(InteractionCommon.FireWall))
			return;
		if(getIncomingStatus()){
			action(option);
		} 
		else{
			socketUtil.sendMsg("sprdtest assistant client status update "+ testSN + " true");
			Wait(3000);
			socketUtil.sendMsg("sprdtest call " + simNum + " 2 "+ testSN);
			if(getIncomingStatus())
				action(option);
		}
		Wait(2000);
		if(option.equals("answer")){
			itest.sendCommand(EndCall);
		}
		else{
			System.out.println("Because of reject, wait for 20s.");
			Wait(20000);
		}
		socketUtil.sendMsg("sprdtest assistant client status update "+ testSN + " true");
	}
	/**
	 * 被叫电话打过来时，通过界面接听或拒接
	 * @param option
	 * @throws IOException
	 */
	public void action(String option) throws IOException{
		int DisplayWidth = UiDevice.getInstance().getDisplayWidth();
		int	DisplayHeight = UiDevice.getInstance().getDisplayHeight();	
		//灭屏滑动的初始位置
		int start_x = DisplayWidth * 1/2;
		int start_y = DisplayHeight * 12/17;
		int x=0,y=0;
		switch(option){
		case "answer":
			if(isWakeup){
				//弹出框无法识别，先写死
				x = DisplayWidth * 7/10;
				y = DisplayHeight * 4/25;
			}
			else{
				x = DisplayWidth;
				y = start_y;
			}
			break;
		case "reject":
			if(isWakeup){
				//弹出框无法识别，先写死
				x = DisplayWidth * 3/10;
				y = DisplayHeight * 4/25;
			}
			else{
				x = 0;
				y = start_y;
			}
			break;
		case "rejectBySMS":
			if(isWakeup){
				Assert.assertTrue("Error: no rejectBySMS mode!!!",false);
			}
			else{
				x = start_x;
				y = DisplayHeight * 1/2;
			}
			break;
		}
		Wait(1500);
		if(isWakeup){
			UiDevice.getInstance().click(x,y);
		}
		else{
			UiDevice.getInstance().drag(start_x, start_y, x, y, 10);
			if(option.equals("rejectBySMS")){
				excute(Object_ResIdInstance,Operation_ClickWait,"android:id/text1","0");
			}
		}	
		Assert.assertTrue(simNum + option + " fail!!!", getStatus(option));
		System.out.println(simNum + option + " success ~~");
	}
	/**
	 * 被叫流程结束后，关闭所有服务
	 */
	public void interactionClose(String option){
		if(option.contains(normalMtCall)){
			itest.sendCommand(EndCall);
			itest.finish();
			isWakeup = true;
			Wait(5000);
		}
		socketUtil.sendMsg("sprdtest assistant client status update "+ simNum + " true");
		socketUtil.closeSocket();
	}
	/**
	 * 读取服务器是否成功分配辅助机进行被叫服务的状态
	 * 	"[serverBack]OK"：成功分配了辅助机进行被叫服务；
	 * 	"[serverBack]assistantDeviceNum is 0"：辅助机数量为0，终止被叫服务；
	 * 	"[serverBack]all assistantDevices are busy"：所有辅助机全忙，等待30s，待辅助机释放
	 * @param simNum
	 * @param timeout
	 * @return
	 */
	private boolean readServerBack(String simNum, int timeout, String interactionAction){
		String serverBack = "";
		String assistantDeviceOK= "[serverBack]OK";
		String noassistantDevices = "[serverBack]assistantDeviceNum is 0";
		String allDevicesBusy = "[serverBack]all assistantDevices are busy";
		long initialTime = System.currentTimeMillis();
		do{
			try{
				serverBack = socketUtil.readTestClientBack();
				System.out.println(">>>>>>>>>serverBack = "+serverBack);
				System.out.println("[readServerBack] = " + serverBack);
				if(serverBack.contains(noassistantDevices)){
					System.out.println("[readServerBack]" + noassistantDevices);
					return false;
				}
				else if(serverBack.contains(allDevicesBusy)){
					System.out.println("[readServerBack]" + allDevicesBusy + ", wait 30s!!!");
					Wait(30 * 1000);
					socketUtil.sendMsg("sprdtest assistant client status update "+ testSN + " true");
					Wait(1000);
					switch(interactionAction){
					case "mtCall":
						socketUtil.sendMsg("sprdtest call " + simNum + " 2 "+ testSN);
						break;
					case "SMS":
						socketUtil.sendMsg("sprdtest sms " + simNum + " 2 "+ testSN);
						break;
					default:
						Assert.assertTrue("Error: no this interactionAction!!!",false);
						break;
					}
				}
				if(System.currentTimeMillis() - initialTime > timeout * 60 * 1000){
					System.out.println("[readServerBack]" + "timeout!!!" + "value = " + timeout);
					return false;
				}
			}catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(!serverBack.contains(assistantDeviceOK));
		return true;
	}
	/**
	 * 判断是否来电
	 * @return
	 */
	private boolean getIncomingStatus() {
		int counts = 0;
		boolean result = false;
		while (true) {
			String Status = itest.getCommandReturn("GetCallState");
			System.out.println("========" + Status + "=======");
			if(Status.equals("INCOMING") || Status.equals("RING")){
				result = true;
				break;
			}
			else{
				Wait(1000);
				counts += 1;
				if(counts > 120){
					break;
				}
			}
		}
		return result;
	}
	/**
	 * 获取通话状态
	 * @param option
	 * @return
	 */
	private boolean getStatus(String option) {
		int counts = 0;
		boolean result = false;
		boolean condition = false;
		while (true) {
			String Status = itest.getCommandReturn("GetCallState");
			switch(option){
			case "answer":
				condition = Status.equals("OFFHOOK") || Status.equals("ACTIVE");
				break;
			case "reject":
			case "rejectBySMS":
				condition = Status.equals("DISCONNECTING") || Status.equals("DISCONNECTED") || Status.equals("IDLE");
				break;
			}
			if (condition) {
				result = true;
				break;
			} else {
				Wait(1000);
				counts += 1;
				if (counts > 30) {
					break;
				}
			}
		}
		return result;
	}
}
