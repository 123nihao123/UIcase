package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.io.IOException;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;

public class Notification extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
   }
	    
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 屏幕的最顶端有状态栏图标
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_001() throws UiObjectNotFoundException
	{
		//主体
		DeviceCommon.enterApp(Devices_Desc_Call);
		check(Object_ResourceId,Operation_checkExist,"android:id/statusBarBackground");
	}
	/**
	 * 状态栏上有 时间和日期显示，在左上角
	 */
	public static void test_004()
	{
		//主体
		UiDevice.getInstance().openNotification();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/time_view","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.systemui:id/time_view");
		check(Object_ResourceId,Operation_checkExist,"com.android.systemui:id/date_expanded");
	}
	/**
	 * 状态栏上有快捷方式,快捷方式有较多个
	 */
	public static void test_005()
	{
		//主体
		UiDevice.getInstance().openQuickSettings();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/slider","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.systemui:id/quick_settings_container");
		//check(Object_ResourceId,Operation_checkExist,"com.android.systemui:id/date_expanded");
	}
	/**
	 * 调节亮度 快捷方式
	 * @throws IOException 
	 */
	public static void test_006() throws IOException
	{
		//主体
		UiDevice.getInstance().openQuickSettings();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/slider","5000");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.systemui:id/slider");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x / 2, y);
		String i = DeviceCommon.runADBCommand("settings get system screen_brightness");
		String s[] = i.split("\n");
		UiDevice.getInstance().click(x / 3, y);
		String j = DeviceCommon.runADBCommand("settings get system screen_brightness");
		String s1[] = j.split("\n");
		Assert.assertFalse(s[0].equals(s1[0]));
		//清场
		DeviceCommon.runADBCommand("settings put system screen_brightness 25");
	}
	/**
	 * 点击 蓝牙快捷方式 下边的小三角号(直接点击下边的蓝牙字样)
	 * @throws IOException 
	 */
	public static void test_010() throws IOException 
	{
		//主体
		UiDevice.getInstance().openQuickSettings();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/slider","5000");
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","蓝牙");
		//清场
		excute(Object_Text,Operation_ClickWait,"开启");
	}
	/**
	 * 点击 数据快捷方式 下边的小三角号(直接点击下边的数据字样)
	 */
	public static void test_012() 
	{
		//主体
		UiDevice.getInstance().openQuickSettings();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/slider","5000");
		excute(Object_Text,Operation_ClickWait,"数据");
		check(Object_Text,Operation_checkExist,"流量使用情况");
	}
	/**
	 * 点击页面右上角的 设置 图标
	 */
	public static void test_019() 
	{
		//主体
		UiDevice.getInstance().openQuickSettings();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/slider","5000");
		excute(Object_Description,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_WaitForExists,"设置","5000");
		check(Object_Text,Operation_checkExist,"设置");
	}
	/**
	 * 点击页面右上角的  电量 图标
	 */
	public static void test_021() 
	{
		//主体
		UiDevice.getInstance().openQuickSettings();
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.systemui:id/slider","5000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.systemui:id/battery");
		check(Object_Text,Operation_checkExist,"电池");
	}

}
