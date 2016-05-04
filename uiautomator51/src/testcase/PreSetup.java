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
//		ClearBackgroundApp();
		if(DeviceCommon.simFlag.equals("11"))
	    {
			DeviceCommon.enterApp(Devices_Desc_Setting);
			excute(Object_TextScroll,Operation_ClickWait,"SIM 卡", "vertical");
			excute(Object_Text, Operation_ClickWait, "SIM 卡插槽 1");
			changeSIMName("SIM1");
			excute(Object_Text, Operation_ClickWait, "SIM 卡插槽 2");
			changeSIMName("SIM2");
		}else if(DeviceCommon.simFlag.equals("10"))
		{
			DeviceCommon.enterApp(Devices_Desc_Setting);
			excute(Object_TextScroll,Operation_ClickWait,"SIM 卡", "vertical");
			excute(Object_Text, Operation_ClickWait, "SIM 卡插槽 1");
			changeSIMName("SIM1");
		}else if(DeviceCommon.simFlag.equals("01"))
		{
			DeviceCommon.enterApp(Devices_Desc_Setting);
			excute(Object_TextScroll,Operation_ClickWait,"SIM 卡", "vertical");
			excute(Object_Text, Operation_ClickWait, "SIM 卡插槽 2");
			changeSIMName("SIM2");
		}
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		excute(Object_Text, Operation_ClickWait, "休眠");
		excute(Object_Text, Operation_ClickWait, "30分钟");
	}
	/**
	 * 查看SIM卡名称，如果不是需要的名称，修改SIM名称
	 * @param SIMName SIM卡名称
	 * @throws UiObjectNotFoundException
	 */
	protected void changeSIMName(String SIMName) throws UiObjectNotFoundException 
    {
		String Name = (String) excute(Object_ResourceId, Operation_GetText, "com.android.settings:id/sim_name");
		if(!Name.equals(SIMName))
		{
			DeviceCommon.ClearTextField("com.android.settings:id/sim_name");
			SettingCommon.SIMNames(SIMName);
		}else{
			excute(Object_Device, Operation_PressBack);
			excute(Object_Text, Operation_ClickWait, "取消");
		}
    }
}