package testcase;

import java.io.IOException;
import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.sprd.socket.SocketUtil;
import com.spreadtrum.itestapi.ITestApi;
import com.spreadtrum.itestapi.ITestApp;

import static framework.data.ObjectType.Object_Device;
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
	
	public Interaction(){}
	
	public Interaction(String option) throws IOException, InterruptedException{
		if(option.contains("mtcall"))
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
		int x=0,y=0;
		switch(option){
		case "answer":
			if(isWakeup){
				x = UiDevice.getInstance().getDisplayWidth() * 7 / 10;
				y = UiDevice.getInstance().getDisplayHeight() * 4 / 25;
			}
			else{
				//x =
				//y =
			}
			break;
		case "reject":
			if(isWakeup){
				x = UiDevice.getInstance().getDisplayWidth() * 3 / 10;
				y = UiDevice.getInstance().getDisplayHeight() * 4 / 25;
			}
			else{
				//x =
				//y =
			}
			break;
		case "rejectBySMS":
			if(isWakeup){
				Assert.assertTrue("Error: no rejectBySMS mode!!!",false);
			}
			else{
				//x =
				//y =
			}
			break;
		}
		Wait(1500);
		if(isWakeup){
			UiDevice.getInstance().click(x,y);
		}
		else{
			//UiDevice.getInstance().drag(startX, startY, x, y, 10);
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
	
	private void receiveCall(String simNumber) throws IOException {
		Wait(1500);
		UiDevice.getInstance().click(
				UiDevice.getInstance().getDisplayWidth() * 7 / 10,
				UiDevice.getInstance().getDisplayHeight() * 4 / 25);
		Assert.assertTrue(simNumber + " receiveCall Fail", getAnswerStatus());
		System.out.println(simNumber + " mtCall answer success!");
	}
	
	private void rejectCall(String simNumber) throws IOException {
		Wait(1500);
		UiDevice.getInstance().click(
				UiDevice.getInstance().getDisplayWidth() * 3 / 10,
				UiDevice.getInstance().getDisplayHeight() * 4 / 25);
		Assert.assertTrue(simNumber + " mtCall reject Fail", getRejectStatus());
		System.out.println(simNumber + " mtCall reject success!");
	}
	
	private void rejectBySMS(String simNumber) throws IOException {
		Wait(1500);
		UiDevice.getInstance().click(
				UiDevice.getInstance().getDisplayWidth() * 1 / 2,
				UiDevice.getInstance().getDisplayHeight() * 1 / 2);
		Assert.assertTrue(simNumber + " mtCall rejectBySMS Fail", getRejectStatus());
		System.out.println(simNumber + " mtCall rejectBySMS success!");
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
	
	private boolean getAnswerStatus() {
		int counts = 0;
		boolean result = false;
		while (true) {
			String Status = itest.getCommandReturn("GetCallState");
			if (Status.equals("OFFHOOK") || Status.equals("ACTIVE")) {
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
	
	private boolean getRejectStatus() {
		int counts = 0;
		boolean result = false;
		while (true) {
			String Status = itest.getCommandReturn("GetCallState");
			if (Status.equals("DISCONNECTING") || Status.equals("DISCONNECTED") || Status.equals("IDLE")) {
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
