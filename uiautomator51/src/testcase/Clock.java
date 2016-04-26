package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;


import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;
import framework.common.ClockCommon;

public class Clock extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Clock);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * 进入倒计时
	 */
	public void test_030(){  
		//主体
        excute(Object_Description,Operation_ClickWait,"计时器");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/timer_time_text");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/key_middle");
    }
	
	/**
	 * 进入倒计时--存在播放图标
	 */
	public void test_031(){  
		//主体
        excute(Object_Description,Operation_ClickWait,"计时器");
        excute(Object_Text,Operation_ClickWait,"1");
        excute(Object_Text,Operation_ClickWait,"0");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/key_middle");
    }
	
	
	
	

}
