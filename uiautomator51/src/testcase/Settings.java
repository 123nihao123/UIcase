package testcase;

import java.io.IOException;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.CallLogCommon;
import framework.common.SettingCommon;
import framework.common.EmailCommon;
import framework.common.CallCommon;
import framework.driver.ObjectFind;
import framework.driver.OperationUiDevice;
import framework.driver.OperationUiObject;
//插两张SIM卡，SD卡及手机内存中要有预存联系人
public class Settings extends UiAutomatorTestCase
{
	
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Setting);	
   }
	       
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	
	/**
	 * 进入WLAN
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */

	public void test_012() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/title","WLAN");
		check(Object_Text,Operation_checkExist,"WLAN");
	}
	
	/**
	 * 验证WLAN开关是关闭的
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_013() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/title","WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_text","关闭");
		}
		else
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_text","开启");
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_text","关闭");
		}
	}
	
	/**
	 * 打开WLAN，出现不同的WiFi
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_014() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		else
		{
			excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
		}
		
		
		
	}	
}