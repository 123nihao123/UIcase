package testcase;

import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
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
	
	public void test_000() throws UiObjectNotFoundException, RemoteException 
	{	
		DeviceCommon.enterApp(Devices_Desc_Setting);
		DeviceCommon.removePermissions();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMNames("SIM 卡插槽 1","SIM1");
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMNames("SIM 卡插槽 2","SIM2");
		excute(Object_Device, Operation_PressBack);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		excute(Object_Text, Operation_ClickWait, "休眠");
		excute(Object_Text, Operation_ClickWait, "30分钟");
	}
}