package testcase;

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
import framework.common.DeviceCommon;

public class Interaction {
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
	
	public Interaction(){}
	
	public Interaction(String option) throws IOException, InterruptedException{
		if(option.contains("mtCall"))
		{
			Process proc = Runtime.getRuntime().exec("am start -n com.spreadtrum.itestapp/com.spreadtrum.itestapp.TestClientActivity");
		    int ret = proc.waitFor();
		    if (ret != 0)
		    	System.out.println("initial itestapp result:" + ret);
			itest = ITestApp.newInstance();
			socketUtil.startSocket();
			if(option.contains("sleep")){
				isWakeup = false;
				excute(Object_Device, Operation_Sleep);
			}
		}
	}
	/**
	 * 兼容所有SIM卡状况
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
	
	public void mtCall(String simNumber,String option) throws IOException{
		simNum = simNumber;
		int timeout = 7;//min
		socketUtil.sendMsg("sprdtest call " + simNum + " 2 "+ testSN);
		Assert.assertTrue("send cmd fail!!!", readmtCallBack(simNum,timeout));
		
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
		if(option.equals("answer"))
			itest.sendCommand(EndCall);
		socketUtil.sendMsg("sprdtest assistant client status update "+ testSN + " true");
	}
	
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
	
	public void mtCallClose(){
		itest.sendCommand(EndCall);
		itest.finish();
		Wait(5000);
		socketUtil.sendMsg("sprdtest assistant client status update "+ simNum + " true");
		socketUtil.closeSocket();
		isWakeup = true;
	}
	
	private boolean readmtCallBack(String simNum, int timeout){
		String mtCallBack = "";
		String assistantDeviceOK= "[mtCall]OK";
		String noassistantDevices = "[mtCall]assistantDeviceNum is 0";
		String allDevicesBusy = "[mtCall]all assistantDevices are busy";
		long initialTime = System.currentTimeMillis();
		do{
			try{
				mtCallBack = socketUtil.readTestClientBack();
				System.out.println("[readmtCallBack] = " + mtCallBack);
				if(mtCallBack.contains(noassistantDevices)){
					System.out.println("[readmtCallBack]" + noassistantDevices);
					return false;
				}
				else if(mtCallBack.contains(allDevicesBusy)){
					System.out.println("[readmtCallBack]" + allDevicesBusy + ", wait 30s!!!");
					Wait(30 * 1000);
					socketUtil.sendMsg("sprdtest assistant client status update "+ testSN + " true");
					Wait(1000);
					socketUtil.sendMsg("sprdtest call " + simNum + " 2 "+ testSN);
				}
				if(System.currentTimeMillis() - initialTime > timeout * 60 * 1000){
					System.out.println("[readmtCallBack]" + "timeout!!!" + "value = " + timeout);
					return false;
				}
			}catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(!mtCallBack.contains(assistantDeviceOK));
		return true;
	}
	
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
