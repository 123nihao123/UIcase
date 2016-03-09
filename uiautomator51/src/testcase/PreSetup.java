package testcase;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.DeviceCommon;
import framework.common.SettingCommon;

public class PreSetup extends UiAutomatorTestCase
{
	
	protected void setUp() 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
	}
	
	protected void tearDown() 
    {
		
    }
	
	public void test_001() throws UiObjectNotFoundException, RemoteException 
	{
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		excute(Object_Text, Operation_ClickWait, "休眠");
		excute(Object_Text, Operation_ClickWait, "30分钟");
		
	}
	
	
}