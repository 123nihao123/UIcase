package testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
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
	 * 检查“设置”字样
	 */
	public static void test_001() 
	{
		//主体
		check(Object_Text,Operation_checkExist,"设置");
	}
	
	/**
	 * 检查设置中四大项的字样
	 */
	public static void test_002() 
	{
		//主体
		check(Object_TextScroll,Operation_checkExist,"无线和网络","vertical");
		check(Object_TextScroll,Operation_checkExist,"设备","vertical");
		check(Object_TextScroll,Operation_checkExist,"个人","vertical");
		check(Object_TextScroll,Operation_checkExist,"系统","vertical");
	}
	
	/**
	 * 检查搜索图标
	 */
	public static void test_003() 
	{
		//主体
		check(Object_ResIdDesc,Operation_checkExist,"com.android.settings:id/search","搜索设置");
	}
	
	/**
	 * 检查“搜索...”字样
	 */
	public static void test_004() 
	{
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		check(Object_Text,Operation_checkExist,"搜索…");
	}
	
	/**
	 * 查看搜索内容
	 */
	public static void test_005() 
	{
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		excute(Object_ResourceId,Operation_SetText,"android:id/search_src_text","t");
		check(Object_Text,Operation_checkExist,"搜索结果");
		check(Object_TextInstance,Operation_checkExist,"文字转语音 (TTS) 输出","1");
	}
	
	/**
	 * 点击进入搜索内容
	 * @throws IOException 
	 */
	public static void test_007() throws IOException 
	{
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		excute(Object_ResourceId,Operation_SetText,"android:id/search_src_text","t");
		excute(Object_Text,Operation_ClickWait,"文字转语音 (TTS) 输出");
		check(Object_Text,Operation_checkExist,"语言和输入法");
		//清场
		DeviceCommon.runADBCommand("pm clear com.android.settings");
	}
	
	/**
	 * 查看最近执行的搜索
	 * @throws IOException 
	 */
	public static void test_008() throws IOException 
	{
		//前提
		SettingCommon.setSearchRecord("t");
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		check(Object_Text,Operation_checkExist,"最近执行的搜索");
		check(Object_Text,Operation_checkExist,"t");
		//清场
		DeviceCommon.runADBCommand("pm clear com.android.settings");
	}
	
	/**
	 * 检查搜索界面向上的小箭头
	 * @throws IOException
	 */
	public static void test_009() throws IOException 
	{
		//前提
		SettingCommon.setSearchRecord("t");
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/icon");
		//清场
		DeviceCommon.runADBCommand("pm clear com.android.settings");
	}
	
	
	/**
	 * 点击最近执行的搜索
	 * @throws IOException 
	 */
	public static void test_010() throws IOException 
	{
		//前提
		SettingCommon.setSearchRecord("t");
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/icon");
		check(Object_Text,Operation_checkExist,"搜索结果");
		check(Object_TextInstance,Operation_checkExist,"文字转语音 (TTS) 输出","1");
		//清场
		DeviceCommon.runADBCommand("pm clear com.android.settings");
	}
	
	/**
	 * 删除搜索内容
	 */
	public static void test_011() 
	{
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.settings:id/search","搜索设置");
		excute(Object_ResourceId,Operation_SetText,"android:id/search_src_text","t");
		excute(Object_ResourceId,Operation_ClickWait,"android:id/search_close_btn");
		check(Object_Text,Operation_checkNoExist,"t");
	}
	
	
	/**
	 * 进入WLAN
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */

	public void test_012() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		check(Object_Text,Operation_checkExist,"WLAN");
	}
	
	/**
	 * 验证第一次进入WLAN开关是关闭的
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_013() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_text","关闭");
	}
	
	/**
	 * 验证WLAN开关是开启的
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException 
	 */
	public void test_014() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		check(Object_ResIdText, Operation_checkExist,"com.android.settings:id/switch_text","开启");
		String num = DeviceCommon.runADBCommand("settings get global wifi_on");
		String num1 = num.substring(0, 1);
		String num2 = "1";
		Assert.assertEquals(num1, num2);
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	
	
	/**
	 * 打开WLAN，出现不同的WiFi
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_016() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		check(Object_Text,Operation_checkExist,"Testteam");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		
	}	
	
	/**
	 * 连接一个wifi
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_017() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		else
		{
			excute(Object_ResIdText, Operation_Exists,"com.android.settings:id/switch_text","开启");
		}
		SettingCommon.connectWifi("Testteam", "WPA/WPA2 PSK", "test12345678");
		//清场
		SettingCommon.disConnectWifi("Testteam");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		
	}
	
	/**
	 * 验证显示 添加网络，刷新，高级选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_018() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","添加网络");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","刷新");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","高级");
	}
	
	/**
	 * WLAN-选项-高级界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_019() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"高级");
		check(Object_Text,Operation_checkExist,"高级WLAN");
	}
	
	/**
	 * 关闭WIFI
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException 
	 */
	public void test_020() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"开启"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
			String num = DeviceCommon.runADBCommand("settings get global wifi_on");
			String num1 = num.substring(0, 1);
			String num2 = "0";
			Assert.assertEquals(num1, num2);
	}
	
	/**
	 * 进入蓝牙界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_021() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		check(Object_Text,Operation_checkExist,"蓝牙");
		
	}
	
	/**
	 * 进入蓝牙，切换开关
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_022() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
	}
	
	/**
	 * 开启蓝牙按钮	
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException 
	 */
	public void test_023() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"蓝牙");
	    if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
		    excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
	    String num = DeviceCommon.runADBCommand("settings get global bluetooth_on");
		String num1 = num.substring(0, 1);
		String num2 = "1";
		Assert.assertEquals(num1, num2);  
	    //清场
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	
	/**
	 * 开启蓝牙按钮	，搜索到一些蓝牙设备
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_024() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		} 	
		excute(Object_Text,Operation_WaitForExists,"SupportBT","7000");
		check(Object_Text,Operation_checkExist,"SupportBT");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	
	/**
	 * 关闭蓝牙按钮
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException 
	 */
	public void test_026() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//前提
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
		}
		else
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
		}  	
		String num = DeviceCommon.runADBCommand("settings get global bluetooth_on");
		String num1 = num.substring(0, 1);
		String num2 = "0";
		Assert.assertEquals(num1, num2);  
	}
	
	/**
	 * 验证菜单中有 刷新，重命名此设备，显示收到的文件  
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_027() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		excute(Object_Description,Operation_ClickWait,"更多选项");
		
		check(Object_Text,Operation_WaitForExists,"刷新","15000");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","重命名此设备");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","显示收到的文件");
		//清场
		excute(Object_Device,Operation_PressBack);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	
	/**
	 * 进入SIM卡界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_028() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		check(Object_Text,Operation_checkExist,"SIM 卡");
	}
	
	/**
	 * 查看SIM1和SIM2的状态为开启的
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_029() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.settings:id/universal_switch","0");
		check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.settings:id/universal_switch","1");
	}
	
	/**
	 * 禁用SIM1
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_030() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		String simname = (String) excute(Object_ResIdInstance,Operation_GetText,"android:id/summary","2");//读取数据网络在哪张卡上
//		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","2"))
//		{
//			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
//		}
		if(simname.equals("SIM1")&&(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","2"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"注意", "300000");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		else
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"未启用","300000");
		}
		check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.settings:id/universal_switch","0");
		//清场
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_WaitForExists,"SIM 卡","300000");
		
	}
	
	/**
	 * 点击SIM卡插槽1，弹出对话框
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_031() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","0"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		check(Object_Text,Operation_checkExist,"SIM 卡插槽 1");
	}
	
	/**
	 * 设置SIM1名称
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_032() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","0"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		SettingCommon.editSIMName("SIM 卡插槽 1");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/sim_name","SIM1");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_ClickWait,"SIM 卡插槽 1");
		check(Object_Text,Operation_checkExist,"SIM1");
		
		
	}
	
	/**
	 * 更改SIM1颜色
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_033() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","0"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		excute(Object_Text,Operation_ClickWait,"粉红色");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		check(Object_Text,Operation_checkExist,"粉红色");
		//清场
		excute(Object_Text,Operation_ClickWait,"青色");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 更改SIM1号码
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_034() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","0"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		SettingCommon.editSIMNumber("SIM 卡插槽 1");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/display_number","123");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_PeerTextID,Operation_TextContainsTrue,"SIM 卡插槽 1","android:id/summary","123");

	}
	
	
	/**
	 * 禁用SIM2
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_035() throws UiObjectNotFoundException, RemoteException 
	{
			//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		String simname = (String) excute(Object_ResIdInstance,Operation_GetText,"android:id/summary","2");//读取数据网络在哪张卡上
		if(simname.equals("SIM2")&&(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","2"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"注意","300000");//如果数据在SIM2上，会多弹出一个提示窗口
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		else
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"未启用","300000");
		}
			
		check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.settings:id/universal_switch","1");
		//清场
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_WaitForExists,"SIM 卡","300000");
		
	}
	
	/**
	 * 点击SIM卡插槽2，弹出对话框
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_036() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","1"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		check(Object_Text,Operation_checkExist,"SIM 卡插槽 2");
	}
	
	/**
	 * 设置SIM2名称
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_037() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","1"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		SettingCommon.editSIMName("SIM 卡插槽 2");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/sim_name","SIM2");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_ClickWait,"SIM 卡插槽 2");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/sim_name","SIM2");
	}
	
	/**
	 * 更改SIM2颜色
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_038() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","1"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		excute(Object_Text,Operation_ClickWait,"紫色");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		check(Object_Text,Operation_checkExist,"紫色");
		//清场
		excute(Object_Text,Operation_ClickWait,"青色");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 更改SIM2号码
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_039() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","1"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Text,Operation_WaitForExists,"SIM 卡插槽 1","10000");
		}
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		SettingCommon.editSIMNumber("SIM 卡插槽 2");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/display_number","456");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_PeerTextID,Operation_TextContainsTrue,"SIM 卡插槽 2","android:id/summary","456");
	}
	
	/**
	 * 禁用数据流量
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException 
	 */
	public void test_040() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		if((Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"com.android.settings:id/universal_switch","2"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
		}
		String num0 = DeviceCommon.runADBCommand("settings get global data_remain_unchanged");
		String num1 = num0.substring(0, 1);
		String num2 = "0";
		Assert.assertEquals(num1, num2);
		//清场
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
	}
		
	
	
	/**
	 * 点击移动数据网络，弹出对话框
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_041() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"移动数据网络");
		check(Object_Text,Operation_checkExist,"选择用于数据网络的 SIM 卡");	
	}
	
	/**
	 * 数据连接到SIM1
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_042() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"移动数据网络");
		excute(Object_Text,Operation_ClickWait,"SIM1");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","2","SIM1");
	}
	
	/**
	 * 数据连接到SIM2
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_043() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"移动数据网络");
		excute(Object_Text,Operation_ClickWait,"SIM2");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","2","SIM2");
	}
	
	/**
	 * 选择用于通话的SIM卡界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_044() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"通话");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","选择用于通话的 SIM 卡");
	}
		
	/**
	 * 选择用于通话的SIM卡界面--选择每次都询问
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_045() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"每次都询问");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","每次都询问");
	}	
		
	/**
	 * 选择用于通话的SIM卡界面--选择SIM1
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_046() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"SIM1");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM1");
	}		
		
		
	/**
	 * 选择用于通话的SIM卡界面--选择SIM2
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_047() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"SIM2");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","3","SIM2");
	}		
		
	/**
	 * 选择用于信息的SIM卡界面--选择每次都询问
	 * 暂无此选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
//	public void test_048() throws UiObjectNotFoundException, RemoteException 
//	{
		//主体
//		excute(Object_Text,Operation_ClickWait,"SIM 卡");
//		excute(Object_Text,Operation_ClickWait,"信息");
//		excute(Object_Text,Operation_ClickWait,"每次都询问");
//		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","每次都询问");
//	}	
		
	/**
	 * 选择用于信息的SIM卡界面--选择SIM1
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_049() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"信息");
		excute(Object_Text,Operation_ClickWait,"SIM1");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM1");
	}		
		
		
	/**
	 * 选择用于信息的SIM卡界面--选择SIM2
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_050() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"信息");
		excute(Object_Text,Operation_ClickWait,"SIM2");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","4","SIM2");
	}			
		
	/**
	 * 主卡选择--验证弹出框
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_051() throws UiObjectNotFoundException, RemoteException 
	{	
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"主卡选择");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","主卡选择");
	}	
		
	/**
	 * 主卡选择SIM1
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_052() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_Text,Operation_ClickWait,"主卡选择");
		
		SettingCommon.selectPrimaryCard("SIM1");
		
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","5","SIM1");
	}		
		
		
	/**
	 * 主卡选择SIM2
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_053() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		SettingCommon.selectPrimaryCard("SIM2");
		
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","5","SIM2");
		
		//清场
		SettingCommon.selectPrimaryCard("SIM1");

	}				
		
	/**
	 * 进入流量使用情况界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_054() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		check(Object_Text,Operation_checkExist,"流量使用情况");	
	}

	/**
	 * 进入流量使用情况界面，验证菜单上有text"限制后台流量“”显示wlan流量”“网络限制”“移动网络”
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_055() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","限制后台流量");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","显示WLAN流量");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","网络限制");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","移动网络");
	}
	
	/**
	 * 进入流量使用情况界面，跳转到“网络限制”页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_056() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"网络限制");
		check(Object_Text,Operation_checkExist,"网络限制");
	}
	
	/**
	 * 进入流量使用情况界面，跳转到“显示WLAN流量”页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_057() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		  excute(Object_Text,Operation_ClickWait,"流量使用情况");
		  excute(Object_Device,Operation_PressMenu);
		  if((Boolean) excute(Object_Text, Operation_Exists, "隐藏WLAN流量"))
		  {
		   excute(Object_Text,Operation_ClickWait,"隐藏WLAN流量");
		   excute(Object_Device,Operation_PressMenu);
		  }
		  //主体
		  excute(Object_Text,Operation_ClickWait,"显示WLAN流量");
		  check(Object_Text, Operation_checkExist, "WLAN");
		  //清场
		  excute(Object_Device,Operation_PressMenu);
		  excute(Object_Text,Operation_ClickWait,"隐藏WLAN流量");
	}
	
	/**
	 * 进入流量使用情况界面，跳转到“移动网络”页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_058() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"移动网络");
		check(Object_Text,Operation_checkExist,"移动网络设置");
	}

	/**
	 * 隐藏WLAN流量
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_059() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		excute(Object_Device,Operation_PressMenu);
		if((Boolean) excute(Object_Text, Operation_Exists, "显示WLAN流量"))
		{
			excute(Object_Text,Operation_ClickWait,"显示WLAN流量");
			excute(Object_Device,Operation_PressMenu);
		}
		//主体
		excute(Object_Text,Operation_ClickWait,"隐藏WLAN流量");
		check(Object_Text, Operation_checkNoExist, "WLAN");
	}
	/**
	 * 显示WLAN流量
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_060() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		excute(Object_Device,Operation_PressMenu);
		if((Boolean) excute(Object_Text, Operation_Exists, "显示WLAN流量"))
		{
			excute(Object_Text,Operation_ClickWait,"显示WLAN流量");
		}else{
			excute(Object_Device,Operation_PressBack);
		}
		check(Object_Text, Operation_checkExist, "WLAN");
		//清场
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"隐藏WLAN流量");
	}
	/**
	 * 关闭移动数据网络
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_061() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"流量使用情况");
		if((Boolean) excute(Object_ClassInstance, Operation_IsChecked, "android.widget.Switch", "0"))
		{
			excute(Object_Text,Operation_ClickWait,"移动数据网络");
		}
		if((Boolean) excute(Object_Text,Operation_Exists,"确定"))
		{
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		check(Object_ClassInstance, Operation_CheckedFalse, "android.widget.Switch", "0");
	}
	/**
	 * 进入更多
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_062() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"更多");
		check(Object_Text, Operation_checkExist, "更多");
	}
	/**
	 * 进入更多，打开飞行模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_063() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"更多");
		if(!(Boolean) excute(Object_ResourceId, Operation_IsChecked, "android:id/switchWidget"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"android:id/switchWidget");
		}
		check(Object_ResourceId, Operation_CheckedTrue, "android:id/switchWidget");
		
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"android:id/switchWidget");
	}
	/**
	 * 进入更多，关闭飞行模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_064() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_Text,Operation_ClickWait,"更多");
		if(!(Boolean) excute(Object_ResourceId, Operation_IsChecked, "android:id/switchWidget"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"android:id/switchWidget");
		}
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/switchWidget");
		check(Object_ResourceId, Operation_CheckedFalse, "android:id/switchWidget");
	}
	/**
	 * 网络共享与便携式热点
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_065() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"更多");
		excute(Object_Text,Operation_ClickWait,"网络共享与便携式热点");
		check(Object_Text, Operation_checkExist, "便携式WLAN热点");
	}
	/**
	 * 进入VPN
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_067() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"更多");
		excute(Object_Text,Operation_ClickWait,"VPN");
		check(Object_Text, Operation_checkExist, "VPN");
	}
	/**
	 * 进入移动网络
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_068() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"更多");
		excute(Object_Text,Operation_ClickWait,"移动网络");
		check(Object_Text, Operation_checkExist, "移动网络设置");
	}
	/**
	 * 进入移动网络
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_069() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"更多");
		excute(Object_Text,Operation_ClickWait,"手机套餐");
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * 便携式WLAN热点默认为关闭，蓝牙网络共享默认为关闭，显示WLAN热点设置及用户管理字样
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_070() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","1");
		check(Object_Text, Operation_checkExist, "WLAN热点设置及用户管理");
	}
	
	/**
	 * 便携式WLAN热点被打开  通过页面开关判断
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_071() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		if(!(Boolean) excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","0"))
		{
			excute(Object_Text, Operation_ClickWait, "便携式WLAN热点");
		}
		excute(Object_Text, Operation_WaitForExists, "便携式热点“AndroidAP”已激活","10000");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","0");
		//清场
		excute(Object_Text, Operation_ClickWait, "便携式WLAN热点");	
	}
	/**
	 * 便携式WLAN热点被打开，通过开关的真实状态进行判断
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_072() throws UiObjectNotFoundException, RemoteException ,IOException
	{	
		//前提
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		if(!(Boolean) excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","0"))
		{
			excute(Object_Text, Operation_ClickWait, "便携式WLAN热点");
		}
		excute(Object_Text, Operation_WaitForExists, "便携式热点“AndroidAP”已激活","10000");
		//主体
		String num1 = DeviceCommon.runADBCommand("settings get global softap_enabling_or_enabled");
		String num2 = num1.substring(0, 1);
		String num3 = "1";
		Assert.assertEquals(num2, num3);
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/switchWidget","0");	
	}
	/**
	 * 蓝牙网络热点被打开
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_073() throws UiObjectNotFoundException,RemoteException,IOException
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		if((Boolean) check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","1"))
		{
			excute(Object_Text, Operation_ClickWait, "蓝牙网络共享");
		}
		
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","1");
		//清场
		excute(Object_Text, Operation_ClickWait, "蓝牙网络共享");
		new Settings().setUp();
		excute(Object_Text, Operation_ClickWait, "蓝牙");
		excute(Object_Text, Operation_ClickWait, "开启");
	}
	/**
	 * WLAN 热点设置及用户管理界面判断
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */ 
	public static void test_074() throws UiObjectNotFoundException,RemoteException
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		check(Object_Text, Operation_checkExist, "WLAN热点设置及用户管理");
	}
	/**
	 * WLAN上热点被打开
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_075() throws UiObjectNotFoundException,RemoteException,IOException
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_ResIdText, Operation_ClickWait ,"com.android.settings:id/switch_widget","关闭");
		check(Object_ResIdText, Operation_CheckedTrue ,"com.android.settings:id/switch_widget","开启");//页面开关判断
		String num1 = DeviceCommon.runADBCommand("settings get global softap_enabling_or_enabled");//底层真实状态判断
		String num2 = num1.substring(0, 1);
		String num3 = "1";
		Assert.assertEquals(num2, num3);
		//清场
		excute(Object_ResIdText, Operation_ClickWait ,"com.android.settings:id/switch_widget","开启");
	}
	/**
	 * 弹出“WLAN热点开启”的对话框
	 * @throws UiObjectNotFoundException
	 * 
	 * @throws RemoteException
	 */
	public static void test_076() throws UiObjectNotFoundException,RemoteException
	{	
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle","保持WLAN热点开启");
	}
	/**
	 * 点击保持WLAN热点开启，选中总是选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_077() throws UiObjectNotFoundException,RemoteException
	{	
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		excute(Object_Text, Operation_ClickWait, "总是");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		check(Object_Text, Operation_CheckedTrue, "总是");
	}
	/**
	 * 点击保持WLAN热点开启，选中空闲5分钟后关闭选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_078() throws UiObjectNotFoundException,RemoteException
	{	
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		excute(Object_Text, Operation_ClickWait, "空闲5分钟后关闭");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		check(Object_Text, Operation_CheckedTrue, "空闲5分钟后关闭");
	}
	/**
	 * 点击保持WLAN热点开启，选中空闲10分钟后关闭选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_079() throws UiObjectNotFoundException,RemoteException
	{	
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		excute(Object_Text, Operation_ClickWait, "空闲10分钟后关闭");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		check(Object_Text, Operation_CheckedTrue, "空闲10分钟后关闭");
	}
	/**
	 * 保持WLAN热点开启对话框，点击取消后消失
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_080() throws UiObjectNotFoundException,RemoteException
	{	
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_Text, Operation_ClickWait, "保持WLAN热点开启");
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_checkNoExist, "取消");
		
	}
	/**
	 * 点击设置WLAN热点，弹出对话框
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_081() throws UiObjectNotFoundException,RemoteException
	{	
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "网络共享与便携式热点");
		excute(Object_Text, Operation_ClickWait , "WLAN热点设置及用户管理");
		excute(Object_Text, Operation_ClickWait, "设置WLAN热点");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle","设置WLAN热点");
	}
	/**
	 * 新建vpn
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_082() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterVPN();
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/vpn_create");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			SettingCommon.SetPIN();
		}
		SettingCommon.SetVPN("VPN_Spreadtrum", "www.spreadtrum.com");
		excute(Object_Text,Operation_WaitForExists,"VPN_Spreadtrum", "10000");
		check(Object_Text, Operation_checkExist, "VPN_Spreadtrum");
		//清场
		SettingCommon.RemovePIN();
	}
	/**
	 * 连接vpn
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_084() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		SettingCommon.EnterVPN();
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/vpn_create");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			SettingCommon.SetPIN();
		}
		SettingCommon.SetVPN("VPN_Spreadtrum", "www.spreadtrum.com");
		excute(Object_Text,Operation_WaitForExists,"VPN_Spreadtrum", "10000");
		//主体
		excute(Object_Text, Operation_ClickWait, "VPN_Spreadtrum");
		check(Object_Text, Operation_checkExist, "连接到VPN_Spreadtrum");
		//清场
		SettingCommon.RemovePIN();
	}
	/**
	 * 连接vpn,无法获取控件信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_085() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		SettingCommon.EnterVPN();
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/vpn_create");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			SettingCommon.SetPIN();
		}
		SettingCommon.SetVPN("VPN_Spreadtrum", "www.spreadtrum.com");
		excute(Object_Text,Operation_WaitForExists,"VPN_Spreadtrum", "10000");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/manage");
		check(Object_Text, Operation_checkExist, "编辑VPN配置文件");
		//清场
		SettingCommon.RemovePIN();
	}
	/**
	 * 进入移动网络界面，移动数据网络漫游默认关闭
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_086() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		check(Object_ResIdText, Operation_CheckedFalse, "android:id/switchWidget","关闭");
	}
	/**
	 * 进入移动网络界面，点击偏好设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_087() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "偏好设置");
		check(Object_Text, Operation_checkExist, "偏好设置");
	}
	/**
	 * 默认首选网络模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_088() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		if (!(Boolean) excute(Object_Text, Operation_Exists, "首选网络类型"))
		{
			excute(Object_Text, Operation_ClickWait, "SIM2");
		}
		excute(Object_Text, Operation_ClickWait, "首选网络类型");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle","首选网络类型");
	}
	/**
	 * 默认首选网络模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_089() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		if (!(Boolean) excute(Object_Text, Operation_Exists, "首选网络类型"))
		{
			excute(Object_Text, Operation_ClickWait, "SIM2");
		}
		excute(Object_Text, Operation_ClickWait, "首选网络类型");
		check(Object_ResIdText, Operation_CheckedTrue, "android:id/text1","自动");
	}
	/**
	 * 默认首选网络模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_090() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		if (!(Boolean) excute(Object_Text, Operation_Exists, "首选网络类型"))
		{
			excute(Object_Text, Operation_ClickWait, "SIM2");
		}
		excute(Object_Text, Operation_ClickWait, "首选网络类型");
		excute(Object_Text, Operation_ClickWait, "仅限 WCDMA");
		excute(Object_Text, Operation_ClickWait, "首选网络类型");
		check(Object_ResIdText, Operation_CheckedTrue, "android:id/text1","仅限 WCDMA");
		//清场
		excute(Object_Text, Operation_ClickWait, "自动");
	}
	/**
	 * 默认首选网络模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_091() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		if (!(Boolean) excute(Object_Text, Operation_Exists, "首选网络类型"))
		{
			excute(Object_Text, Operation_ClickWait, "SIM2");
		}
		excute(Object_Text, Operation_ClickWait, "首选网络类型");
		excute(Object_Text, Operation_ClickWait, "仅限 GSM");
		excute(Object_Text, Operation_ClickWait, "首选网络类型");
		check(Object_ResIdText, Operation_CheckedTrue, "android:id/text1","仅限 GSM");
		//清场
		excute(Object_Text, Operation_ClickWait, "自动");
	}
	/**
	 * 进入APN
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_092() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "接入点名称 (APN)");
		check(Object_Text, Operation_checkExist, "APN");
	}
	/**
	 * 默认APN数量
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_093() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "接入点名称 (APN)");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
		Assert.assertEquals(Num , 3);
	}
	/**
	 * 进入添加APN界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_094() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "接入点名称 (APN)");
		excute(Object_Description, Operation_ClickWait, "新建 APN");
		check(Object_Text, Operation_checkExist, "修改接入点");
	}
	/**
	 * 舍弃添加APN
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_095() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "接入点名称 (APN)");
		excute(Object_Description, Operation_ClickWait, "新建 APN");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title", "名称");
		excute(Object_ResourceId, Operation_SetText, "android:id/edit", "VPN_Spreadtrum");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button1");
		excute(Object_Text, Operation_ClickWait, "APN");
		excute(Object_ResourceId, Operation_SetText, "android:id/edit", "cmwep");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button1");
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait, "放弃");
		check(Object_Text, Operation_checkNoExist, "VPN_Spreadtrum");
	}
	/**
	 * 重置apn
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_096() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "接入点名称 (APN)");
		excute(Object_Description, Operation_ClickWait, "新建 APN");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title", "名称");
		excute(Object_ResourceId, Operation_SetText, "android:id/edit", "VPN_Spreadtrum");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button1");
		excute(Object_Text, Operation_ClickWait, "APN");
		excute(Object_ResourceId, Operation_SetText, "android:id/edit", "cmwep");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button1");
		excute(Object_Device, Operation_PressBack);
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait, "重置为默认设置");
		excute(Object_Text, Operation_WaitForExists, "APN", "10000");
		check(Object_Text, Operation_checkNoExist, "VPN_Spreadtrum");
	}
	/**
	 * 网络运营商
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_098() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "更多");
		excute(Object_Text, Operation_ClickWait, "移动网络");
		excute(Object_Text, Operation_ClickWait, "网络运营商");
		check(Object_Text, Operation_checkExist, "搜索网络");
		check(Object_Text, Operation_checkExist, "自动选择");
	}	
	
	/**
	 * 检查“显示”字样
	 */
	public static void test_099() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		check(Object_Text,Operation_checkExist,"显示");
	}
	
	/**
	 * 检查“屏幕亮度”字样
	 */
	public static void test_100() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"亮度");
		check(Object_Text,Operation_checkExist,"屏幕亮度");
	}
	
	/**
	 * 设置屏幕亮度为最小
	 * @throws IOException
	 */
	public static void test_101() throws IOException
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		if((Boolean) excute(Object_ResourceId,Operation_IsChecked,"android:id/switchWidget"))
			excute(Object_Text,Operation_ClickWait,"自动调节亮度");
		excute(Object_Text, Operation_ClickWait, "亮度");
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
	
//	/**
//	 * 设置屏幕亮度为最大
//	 * @throws UiObjectNotFoundException
//	 * @throws IOException
//	 */
//	public static void test_102() throws UiObjectNotFoundException, IOException 
//	{
//		//主体
//		excute(Object_Text,Operation_ClickWait,"显示");
//		if((Boolean) excute(Object_ResourceId,Operation_IsChecked,"android:id/switchWidget"))
//			excute(Object_Text,Operation_ClickWait,"自动调节亮度");
//		SettingCommon.setScreenBrightness("最大");
//		String i = DeviceCommon.runADBCommand("settings get system screen_brightness");
//		String actual[] = i.split("\n");
//		Assert.assertEquals("Brightness value is not correct", "255", actual[0]);
//		//清场
//		DeviceCommon.runADBCommand("settings put system screen_brightness 25");
//	}
	
	/**
	 * 打开自动调节亮度开关
	 */
	public static void test_103() 
	{
		//前提
		excute(Object_Text,Operation_ClickWait,"显示");
		if((Boolean) excute(Object_ResourceId,Operation_IsChecked,"android:id/switchWidget"))
			excute(Object_Text,Operation_ClickWait,"自动调节亮度");
		//主体
		excute(Object_Text,Operation_ClickWait,"自动调节亮度");
		Assert.assertTrue("开启".equals(excute(Object_ResourceId,Operation_GetText,"android:id/switchWidget")));
		//清场
		if((Boolean) excute(Object_ResourceId,Operation_IsChecked,"android:id/switchWidget"))
			excute(Object_Text,Operation_ClickWait,"自动调节亮度");
	}
	
	/**
	 * 检查壁纸中“选择壁纸来源”字样
	 */
	public static void test_104() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"壁纸");
		check(Object_Text,Operation_checkExist,"选择壁纸来源");
	}
	
	/**
	 * 检查动态壁纸中“光环螺旋”字样
	 */
	public static void test_105() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"壁纸");
		excute(Object_Text,Operation_ClickWait,"动态壁纸");
		check(Object_Text,Operation_checkExist,"光环螺旋");
	}
	
	/**
	 * 检查壁纸中图库
	 */
	public static void test_106() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"壁纸");
		excute(Object_Text,Operation_ClickWait,"图库");
		check(Object_ResourceId,Operation_checkExist,"com.android.gallery3d:id/gl_root_view");
	}
	
	/**
	 * 检查壁纸中文件管理器
	 */
	public static void test_107() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"壁纸");
		excute(Object_Text,Operation_ClickWait,"文件管理器");
		if((Boolean)excute(Object_Text,Operation_Exists,"选择存储位置"))
			excute(Object_Text,Operation_ClickWait,"手机");
		check(Object_ResourceId,Operation_checkExist,"com.sprd.fileexplorer:id/list_paste");
	}
	
	/**
	 * 检查“休眠”字样
	 */
	public static void test_108() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","休眠");
	}
	
	/**
	 * 设置休眠时间为15秒
	 */
	public static void test_109() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"15秒");
		check(Object_Text,Operation_checkExist,"无操作15秒后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 设置休眠时间为30秒
	 */
	public static void test_110() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30秒");
		check(Object_Text,Operation_checkExist,"无操作30秒后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 设置休眠时间为1分钟
	 */
	public static void test_111() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"1分钟");
		check(Object_Text,Operation_checkExist,"无操作1分钟后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 设置休眠时间为2分钟
	 */
	public static void test_112() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"2分钟");
		check(Object_Text,Operation_checkExist,"无操作2分钟后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 设置休眠时间为5分钟
	 */
	public static void test_113() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"5分钟");
		check(Object_Text,Operation_checkExist,"无操作5分钟后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 设置休眠时间为10分钟
	 */
	public static void test_114() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"10分钟");
		check(Object_Text,Operation_checkExist,"无操作10分钟后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 设置休眠时间为30分钟
	 */
	public static void test_115() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
		check(Object_Text,Operation_checkExist,"无操作30分钟后");
		//清场
		excute(Object_Text,Operation_ClickWait,"休眠");
		excute(Object_Text,Operation_ClickWait,"30分钟");
	}
	
	/**
	 * 查看“互动屏保”字样
	 */
	public static void test_117() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"互动屏保");
		check(Object_Text,Operation_checkExist,"互动屏保");
	}
	
	/**
	 * 查看互动屏保开关状态
	 */
	public static void test_118() 
	{
		//前提
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"互动屏保");
		if(!(Boolean) excute(Object_ResourceId,Operation_IsChecked,"com.android.settings:id/switch_widget"))
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		//主体
		check(Object_ResourceId,Operation_CheckedTrue,"com.android.settings:id/switch_widget");
	}
	
	/**
	 * 检查互动屏保关闭时字样
	 */
	public static void test_119() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		check(Object_Text,Operation_checkExist,"要控制手机在插入基座时和/或休眠状态下的行为，请启用“互动屏保”。");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	
	/**
	 * 选中互动屏保中万花筒
	 */
	public static void test_120() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Text,Operation_ClickWait,"万花筒");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"万花筒");
		//清场
		excute(Object_Text,Operation_ClickWait,"互动屏保");
		excute(Object_Text,Operation_ClickWait,"时钟");
	}
	/**
	 * 选中互动屏保中时钟
	 */
	public static void test_121() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Text,Operation_ClickWait,"时钟");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"时钟");
	}
	
	/**
	 * 选中互动屏保中照片桌面
	 */
	public static void test_122() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Text,Operation_ClickWait,"照片桌面");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"照片桌面");
		//清场
		excute(Object_Text,Operation_ClickWait,"互动屏保");
		excute(Object_Text,Operation_ClickWait,"时钟");
	}
	
	/**
	 * 选中互动屏保中相框
	 */
	public static void test_123() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Text,Operation_ClickWait,"相框");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"相框");
		//清场
		excute(Object_Text,Operation_ClickWait,"互动屏保");
		excute(Object_Text,Operation_ClickWait,"时钟");
	}
	
	/**
	 * 检查互动屏保中“立即启动”和“何时启动”字样
	 */
	public static void test_124() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"立即启动");
		check(Object_Text,Operation_checkExist,"何时启动");
	}
	
	/**
	 * 互动屏保中选择立即启动
	 */
	public static void test_125() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"立即启动");
		check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/digital_clock");
	}
	
	/**
	 * 互动屏保中选择何时启动
	 */
	public static void test_126() 
	{
		//前提
		SettingCommon.setDefaultScreensavers();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"何时启动");
		check(Object_Text,Operation_checkExist,"插入基座时");
		check(Object_Text,Operation_checkExist,"充电时");
		check(Object_Text,Operation_checkExist,"以上任一情况");
	}
	
	/**
	 * 查看“字体大小”字样
	 */
	public static void test_127() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"字体大小");
		check(Object_ResourceId,Operation_checkExist,"android:id/parentPanel");
	}
	
	/**
	 * 设置字体大小为小
	 */
	public static void test_128() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"小");
		check(Object_PeerTextID,Operation_checkExist,"字体大小","android:id/summary","小");
		//清场
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"正常");
	}
	
	/**
	 * 设置字体大小为正常
	 */
	public static void test_129() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"正常");
		check(Object_PeerTextID,Operation_checkExist,"字体大小","android:id/summary","正常");
	}
	
	/**
	 * 设置字体大小为大
	 */
	public static void test_130() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"大");
		check(Object_PeerTextID,Operation_checkExist,"字体大小","android:id/summary","大");
		//清场
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"正常");
	}
	
	/**
	 * 设置字体大小为超大
	 */
	public static void test_131() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"超大");
		check(Object_PeerTextID,Operation_checkExist,"字体大小","android:id/summary","超大");
		//清场
		excute(Object_Text,Operation_ClickWait,"字体大小");
		excute(Object_Text,Operation_ClickWait,"正常");
	}
	
	/**
	 * 检查设备旋转时“旋转屏幕内容方向”和“保持纵向”字样
	 */
	public static void test_132() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"显示");
		excute(Object_Text,Operation_ClickWait,"设备旋转时");
		check(Object_Text,Operation_checkExist,"旋转屏幕内容方向");
		check(Object_Text,Operation_checkExist,"保持纵向");
	}
	

	/**
	 * 进入提示音和通知
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_133() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		check(Object_Text,Operation_checkExist,"提示和通知");
	}
	/**
	 * 进入提示音和通知
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_134() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		check(Object_Text,Operation_checkExist,"勿扰");
	}
	/**
	 * 仅允许优先打扰内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_135() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		check(Object_ResIdInstance,Operation_EnabledFalse,"android:id/switchWidget","0");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","1");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","2");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","3");
	}
	/**
	 * 关闭提醒
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_136() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"提醒");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","1");
		//清场
		excute(Object_Text,Operation_ClickWait,"提醒");
	}
	/**
	 * 关闭活动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_137() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"活动");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","2");
		//清场
		excute(Object_Text,Operation_ClickWait,"活动");
	}
	/**
	 * 打开重复来电者
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_138() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"重复来电者");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","3");
		//清场
		excute(Object_Text,Operation_ClickWait,"重复来电者");
	}
	/**
	 * 点击通话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_139() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		check(Object_Text,Operation_checkExist,"来自任何人");
		check(Object_Text,Operation_checkExist,"仅限来自联系人");
		check(Object_Text,Operation_checkExist,"仅限来自收藏的联系人");
		check(Object_Text,Operation_checkExist,"无");
	}
	/**
	 * 点击来自任何人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_140() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"来自任何人");
		check(Object_Text,Operation_checkExist,"来自任何人");
	}
	/**
	 * 点击仅限来自联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_141() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"仅限来自联系人");
		check(Object_Text,Operation_checkExist,"仅限来自联系人");
	}
	/**
	 * 点击仅限来自收藏的联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_142() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"仅限来自收藏的联系人");
		check(Object_Text,Operation_checkExist,"仅限来自收藏的联系人");
	}
	/**
	 * 点击无
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_143() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"无");
		check(Object_Text,Operation_checkExist,"无");
	}
	/**
	 * 点击自动规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_144() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		check(Object_Text,Operation_checkExist,"自动规则");
	}
	/**
	 * 查看自动规则界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_145() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		check(Object_Text,Operation_checkExist,"周一至周五夜间");
		check(Object_Text,Operation_checkExist,"周末");
		check(Object_Text,Operation_checkExist,"活动");
		check(Object_Text,Operation_checkExist,"添加规则");
	}
	/**
	 * 周一至周五夜间默认开关显示
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_146() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		check(Object_ResourceId,Operation_CheckedFalse,"com.android.settings:id/switch_widget");
	}
	/**
	 * 打开周一至周五夜间开关
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_147() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		check(Object_ResourceId,Operation_CheckedTrue,"com.android.settings:id/switch_widget");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	/**
	 * 点击周一至周五夜间规则名称
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_148() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_Text,Operation_ClickWait,"规则名称");
		check(Object_Text,Operation_checkExist,"规则名称");
	}
	/**
	 * 点击周一至周五夜间星期几
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_149() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_Text,Operation_ClickWait,"星期几");
		check(Object_Text,Operation_checkExist,"星期几");
	}
	/**
	 * 点击周一至周五夜间默认选择
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_150() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_Text,Operation_ClickWait,"星期几");
		check(Object_Text,Operation_CheckedTrue,"星期日");
		check(Object_Text,Operation_CheckedTrue,"星期一");
		check(Object_Text,Operation_CheckedTrue,"星期二");
		check(Object_Text,Operation_CheckedTrue,"星期三");
		check(Object_Text,Operation_CheckedTrue,"星期四");
		check(Object_Text,Operation_CheckedFalse,"星期五");
		check(Object_Text,Operation_CheckedFalse,"星期六");
	}
	/**
	 * 点击周一至周五夜间开始时间
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_151() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "开始时间");
		check(Object_ResourceId, Operation_checkExist, "android:id/parentPanel");
	}
	/**
	 * 点击周一至周五夜间结束时间
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_152() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "结束时间");
		check(Object_ResourceId, Operation_checkExist, "android:id/parentPanel");
	}
	/**
	 * 点击周一至周五夜间勿扰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_153() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		check(Object_Text, Operation_checkExist, "仅限优先打扰");
		check(Object_Text, Operation_checkExist, "仅限闹钟");
		check(Object_Text, Operation_checkExist, "完全阻止");
	}
	/**
	 * 点击周一至周五夜间勿扰仅限优先打扰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_154() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "仅限优先打扰");
		check(Object_Text, Operation_checkExist, "仅限优先打扰");
	}
	/**
	 * 点击周一至周五夜间勿扰仅限闹钟
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_155() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "仅限闹钟");
		check(Object_Text, Operation_checkExist, "仅限闹钟");
	}
	/**
	 * 点击周一至周五夜间勿扰完全阻止
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_156() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "完全阻止");
		check(Object_Text, Operation_checkExist, "完全阻止");
	}
	/**
	 * 点击周一至周五夜间勿扰查看删除
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_157() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		//excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		check(Object_Text, Operation_checkExist, "要删除“周一至周五夜间”规则吗？");
	}
	/**
	 * 点击周一至周五夜间勿扰删除规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_158() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		excute(Object_Text, Operation_SetText, "输入规则名称", "Test");
		excute(Object_Text, Operation_ClickWait, "确定");
		excute(Object_Device, Operation_PressBack);
		//主体
		excute(Object_Text, Operation_ClickWait, "Test");
		//excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkNoExist, "Test");
	}
	/**
	 * 点击周末
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_159() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周末");
		check(Object_Text, Operation_checkExist, "周末");
	}
	/**
	 * 点击活动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_160() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "规则名称");
		check(Object_Text, Operation_checkExist, "活动");
	}
	/**
	 * 点击活动中的在以下活动期间
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_161() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "在以下日历活动期间：");
		check(Object_Text, Operation_checkExist, "所有日历");
		check(Object_Text, Operation_checkExist, "Local Calendar");
	}
	/**
	 * 点击活动中的回复内容如下的活动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_162() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "回复内容如下的活动：");
		check(Object_Text, Operation_checkExist, "“参加”、“可能参加”或“未回复”");
		check(Object_Text, Operation_checkExist, "“参加”或“可能参加”");
		check(Object_Text, Operation_checkExist, "是");
	}
	/**
	 * 点击活动中的勿扰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_163() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		check(Object_Text, Operation_checkExist, "仅限优先打扰");
		check(Object_Text, Operation_checkExist, "仅限闹钟");
		check(Object_Text, Operation_checkExist, "完全阻止");
	}
	/**
	 * 查看添加规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_164() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		check(Object_Text, Operation_checkExist, "添加规则");
	}
	/**
	 * 添加时间规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_165() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		excute(Object_Text, Operation_SetText, "输入规则名称", "1");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "1");
		//清场
		excute(Object_Text, Operation_ClickWait, "1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		excute(Object_Text, Operation_ClickWait, "删除");
	}
	/**
	 * 添加事件规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_166() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		excute(Object_Text, Operation_SetText, "输入规则名称", "2");
		excute(Object_Text, Operation_ClickWait, "事件规则");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "2");
		//清场
		excute(Object_Text, Operation_ClickWait, "2");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		excute(Object_Text, Operation_ClickWait, "删除");
	}
	/**
	 * 投射
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_167() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "投射");
		check(Object_Text, Operation_checkExist, "未在附近找到设备。");
	}
	/**
	 * 设备锁定时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_168() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "设备锁定时");
		check(Object_Text, Operation_checkExist, "显示所有通知内容");
		check(Object_Text, Operation_checkExist, "完全不显示通知");
	}
	/**
	 * 显示所有通知内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_169() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "设备锁定时");
		excute(Object_Text, Operation_ClickWait, "显示所有通知内容");
		check(Object_Text, Operation_checkExist, "显示所有通知内容");
	}
	/**
	 * 不存在170，完全不显示通知内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_171() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "设备锁定时");
		check(Object_Text, Operation_checkExist, "完全不显示通知");
		check(Object_Text, Operation_checkExist, "完全不显示通知");
	}
	/**
	 * 应用通知
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_172() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "应用通知");
		check(Object_Text, Operation_checkExist, "应用通知");
	}
	/**
	 * 通知使用权
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_173() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "通知使用权");
		check(Object_Text, Operation_checkExist, "没有任何已安装的应用请求通知访问权限。");
	}
	/**
	 * 情景模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_174() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		check(Object_Text, Operation_checkExist, "情景模式");
	}
	/**
	 * 情景模式默认选择标准
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_175() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 点击静音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_176() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "1");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "1");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 点击静音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_177() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "2");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "2");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 点击户外
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_178() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "3");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "3");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 编辑标准
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_179() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		check(Object_Text, Operation_checkExist, "编辑");
	}
	/**
	 * 编辑标准默认勾选
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_180() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "1");
		excute(Object_TextScroll, Operation_checkExist,"拨号键盘提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "1");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "2");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "3");
	}
	/**
	 * 勾选通话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_181() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_Text, Operation_ClickWait,"通话");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "0");
		//清场
		excute(Object_Text, Operation_ClickWait,"通话");
	}
	/**
	 * 勾选信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_182() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_Text, Operation_ClickWait,"信息");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "1");
		//清场
		excute(Object_Text, Operation_ClickWait,"信息");
	}
	/**
	 * 点击音量
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_183() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_Text, Operation_ClickWait,"音量");
		check(Object_Text, Operation_checkExist, "铃声音量");
	}
	/**
	 * SIM1来电铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_184() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "5");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * SIM2来电铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_185() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "6");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * SIM1信息铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_186() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "SIM1 SIM1 信息铃声", "vertical");
		//excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "7");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * SIM2信息铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_187() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "SIM2 SIM2 信息铃声", "vertical");
		//excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "8");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 *默认通知提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_188() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "默认通知提示音", "vertical");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 *点击拨号键盘提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_189() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "拨号键盘提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "0");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "拨号键盘提示音", "vertical");
	}
	/**
	 *屏幕锁定提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_190() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "屏幕锁定提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "1");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "屏幕锁定提示音", "vertical");
	}
	/**
	 *触摸提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_191() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "触摸提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "2");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "触摸提示音", "vertical");
	}
	/**
	 *触摸时振动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_192() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "触摸时振动", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "3");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "触摸时振动", "vertical");
	}
	/**
	 *启用静音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_193() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"静音");
		excute(Object_Text, Operation_ClickWait,"启用");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "1");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 *启用振动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_194() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"振动");
		excute(Object_Text, Operation_ClickWait,"启用");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "2");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 *启用户外
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_195() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"户外");
		excute(Object_Text, Operation_ClickWait,"启用");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "3");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 *菜单
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_196() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "添加");
		check(Object_Text, Operation_checkExist, "重置");
	}
	/**
	 *添加情景模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_197() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "添加");
		//excute(Object_Text, Operation_WaitForExists, "请输入情景模式名称", "10000");
		check(Object_Text, Operation_checkExist, "请输入情景模式名称");
		excute(Object_ResourceId, Operation_SetText, "android:id/custom","1");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "1");
		//清场
		excute(Object_Text, Operation_ClickWait, "1");
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 *重置情景模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_198() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "重置");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.settings:id/audio_profile_radiobutton", "1");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.settings:id/audio_profile_radiobutton", "2");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.settings:id/audio_profile_radiobutton", "3");
	}
	/**
	 *进入应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_199() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		check(Object_Text, Operation_checkExist, "应用");
	}
	/**
	 *进入应用设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_200() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		check(Object_Text, Operation_checkExist, "配置应用");
	}
	/**
	 *进入应用权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_201() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "应用权限");
		check(Object_Text, Operation_checkExist, "应用权限");
	}
	/**
	 *进入位置权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_202() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("位置信息");
		check(Object_Text, Operation_checkExist, "位置信息权限");
	}
	/**
	 *进入存储权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_203() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("存储空间");
		check(Object_Text, Operation_checkExist, "存储空间权限");
	}
	/**
	 *进入日历权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_204() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("日历");
		check(Object_Text, Operation_checkExist, "日历权限");
	}
	/**
	 *进入日历权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_205() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("电话");
		check(Object_Text, Operation_checkExist, "电话权限");
	}
	/**
	 *进入相机权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_206() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("相机");
		check(Object_Text, Operation_checkExist, "相机权限");
	}
	/**
	 *进入短信权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_207() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("短信");
		check(Object_Text, Operation_checkExist, "短信权限");
	}
	/**
	 *进入身体传感器权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_208() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("身体传感器");
		check(Object_Text, Operation_checkExist, "身体传感器权限");
	}
	/**
	 *进入通讯录权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_209() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("通讯录");
		check(Object_Text, Operation_checkExist, "通讯录权限");
	}
	/**
	 *进入麦克风权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_210() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("麦克风");
		check(Object_Text, Operation_checkExist, "麦克风权限");
	}
	/**
	 *进入其他权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_211() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		check(Object_Text, Operation_checkExist, "其他权限");
	}
	/**
	 *进入访问短信附件权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_212() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "访问短信附件");
		check(Object_Text, Operation_checkExist, "访问短信附件权限");
	}
	/**
	 *进入读取电子邮件附件权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_213() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "读取电子邮件附件");
		check(Object_Text, Operation_checkExist, "读取电子邮件附件权限");
	}
	/**
	 *开关读取电子邮件附件权限
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_214() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "读取电子邮件附件");
		if((Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "com.android.packageinstaller:id/switchWidget", "0"))
		{
			excute(Object_ResIdInstance, Operation_ClickWait, "com.android.packageinstaller:id/switchWidget", "0");
			check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.packageinstaller:id/switchWidget", "0");
		}else{
			excute(Object_ResIdInstance, Operation_ClickWait, "com.android.packageinstaller:id/switchWidget", "0");
			check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.packageinstaller:id/switchWidget", "0");
		}
	}
	/**
	 *显示系统应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_215() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "读取电子邮件附件");
		excute(Object_Device, Operation_PressMenu);
		if(!(Boolean)excute(Object_Text, Operation_Exists, "显示系统应用"))
		{
			excute(Object_Text, Operation_ClickWait, "隐藏系统应用");
			excute(Object_Device, Operation_PressMenu);
		}
		check(Object_Text, Operation_checkExist, "显示系统应用");
	}
	/**
	 *点击显示系统应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_216() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "读取电子邮件附件");
		excute(Object_Device, Operation_PressMenu);
		if((Boolean)excute(Object_Text, Operation_Exists, "显示系统应用"))
		{
			excute(Object_Text, Operation_ClickWait, "显示系统应用");
		}
		check(Object_Text, Operation_checkExist, "Exchange服务");
		//清场
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "隐藏系统应用");
	}
	/**
	 *隐藏系统应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_217() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "读取电子邮件附件");
		excute(Object_Device, Operation_PressMenu);
		if((Boolean)excute(Object_Text, Operation_Exists, "显示系统应用"))
		{
			excute(Object_Text, Operation_ClickWait, "显示系统应用");
			excute(Object_Device, Operation_PressMenu);
		}
		check(Object_Text, Operation_checkExist, "隐藏系统应用");
		//清场
		excute(Object_Text, Operation_ClickWait, "隐藏系统应用");
	}
	/**
	 *点击隐藏系统应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_218() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		SettingCommon.EnterPermission("其他权限");
		excute(Object_Text, Operation_ClickWait, "读取电子邮件附件");
		if((Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "com.android.packageinstaller:id/switchWidget", "0"))
		{
			excute(Object_ResIdInstance, Operation_ClickWait, "com.android.packageinstaller:id/switchWidget", "0");
		}
		excute(Object_Device, Operation_PressMenu);
		if((Boolean)excute(Object_Text, Operation_Exists, "显示系统应用"))
		{
			excute(Object_Text, Operation_ClickWait, "显示系统应用");
			excute(Object_Device, Operation_PressMenu);
		}
		excute(Object_Text, Operation_ClickWait, "隐藏系统应用");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.packageinstaller:id/switchWidget", "0");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.packageinstaller:id/switchWidget", "1");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.packageinstaller:id/switchWidget", "2");
	}
	/**
	 *点击应用链接
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_220() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "应用链接");
		check(Object_Text, Operation_checkExist, "应用链接");
	}
	/**
	 *点击浏览器应用链接
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_221() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "应用链接");
		excute(Object_Text, Operation_ClickWait, "浏览器");
		check(Object_Text, Operation_checkExist, "应用链接");
		check(Object_Text, Operation_checkExist, "其他默认设置");
	}
	/**
	 *点击默认应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_222() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "默认应用");
		check(Object_Text, Operation_checkExist, "默认应用");
	}
	/**
	 *辅助应用和语音输入
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_224() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "默认应用");
		excute(Object_Text, Operation_ClickWait, "辅助应用和语音输入");
		check(Object_Text, Operation_checkExist, "辅助应用和语音输入");
	}
	/**
	 *浏览器应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_225() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "默认应用");
		excute(Object_Text, Operation_ClickWait, "浏览器应用");
		check(Object_ResourceId, Operation_checkExist, "android:id/select_dialog_listview");
	}
	/**
	 *电话应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_226() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "默认应用");
		excute(Object_Text, Operation_ClickWait, "“电话”应用");
		check(Object_ResourceId, Operation_checkExist, "android:id/select_dialog_listview");
	}
	/**
	 *短信应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_227() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "默认应用");
		excute(Object_Text, Operation_ClickWait, "短信应用");
		check(Object_ResourceId, Operation_checkExist, "android:id/select_dialog_listview");
	}
	/**
	 *在其他应用的上层显示
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_228() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "在其他应用的上层显示");
		check(Object_Text, Operation_checkExist, "在其他应用的上层显示");
	}
	/**
	 *在其他应用的上层显示点击图库
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_230() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "在其他应用的上层显示");
		String appname = (String)excute(Object_ResIdInstance, Operation_GetText, "android:id/title", "0");
		excute(Object_Text, Operation_ClickWait, appname);
		check(Object_Text, Operation_checkExist, "允许在其他应用的上层显示");
	}
	/**
	 *在其他应用的上层显示点击菜单键
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_231() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "在其他应用的上层显示");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "显示系统进程");
		check(Object_Text, Operation_checkExist, "重置应用偏好设置");
	}
	/**
	 *在其他应用的上层显示点击显示系统进程
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_233() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "在其他应用的上层显示");
		excute(Object_Device, Operation_PressMenu);
		if((Boolean)excute(Object_Text, Operation_Exists, "显示系统进程"))
		{
			excute(Object_Text, Operation_ClickWait, "显示系统进程");
		}
		check(Object_TextScroll, Operation_checkExist, "电话", "vertical");
		check(Object_TextScroll, Operation_checkExist, "工厂测试工具", "vertical");
		//清场
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "隐藏系统进程");
	}
	/**
	 *在其他应用的上层显示点击重置偏好设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_234() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "在其他应用的上层显示");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "重置应用偏好设置");
		check(Object_Text, Operation_checkExist, "要重置应用偏好设置吗？");
	}
	/**
	 *修改系统设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_235() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "修改系统设置");
		check(Object_Text, Operation_checkExist, "可修改系统设置");
	}
	/**
	 *修改系统设置点击电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_236() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "修改系统设置");
		excute(Object_TextScroll, Operation_ClickWait, "电话", "vertical");
		check(Object_Text, Operation_checkExist, "修改系统设置");
	}
	/**
	 *修改系统设置点击电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_237() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "修改系统设置");
		excute(Object_TextScroll, Operation_ClickWait, "电话", "vertical");
		if((Boolean)excute(Object_ResourceId, Operation_IsChecked, "android:id/switchWidget"))
		{
			excute(Object_ResourceId, Operation_ClickWait, "android:id/switchWidget");
			check(Object_ResourceId, Operation_CheckedFalse, "android:id/switchWidget");
		}else{
			excute(Object_ResourceId, Operation_ClickWait, "android:id/switchWidget");
			check(Object_ResourceId, Operation_CheckedTrue, "android:id/switchWidget");
		}
	}
	/**
	 *应用信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_238() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "修改系统设置");
		excute(Object_TextScroll, Operation_ClickWait, "电话", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/app_settings");
		check(Object_Text, Operation_checkExist, "应用信息");
	}
	/**
	 *应用信息点击菜单
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_239() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "修改系统设置");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "显示系统进程");
		check(Object_Text, Operation_checkExist, "重置应用偏好设置");
	}
	/**
	 *电池优化
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_240() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "电池优化");
		check(Object_Text, Operation_checkExist, "电池优化");
	}
	/**
	 *电池优化未优化
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_241() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "电池优化");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");
		check(Object_Text, Operation_checkExist, "未优化");
		check(Object_Text, Operation_checkExist, "所有应用");
	}
	/**
	 *电池优化所有应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_242() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "电池优化");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");
		excute(Object_Text, Operation_ClickWait, "所有应用");
		check(Object_Text, Operation_checkExist, "所有应用");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");
		excute(Object_Text, Operation_ClickWait, "未优化");
	}
	/**
	 *打印处理服务
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_243() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "电池优化");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/text1");
		excute(Object_Text, Operation_ClickWait, "所有应用");
		excute(Object_Text, Operation_ClickWait, "打印处理服务");
		check(Object_ResIdText, Operation_checkExist, "android:id/alertTitle", "打印处理服务");
	}
	/**
	 *重置应用偏好设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_244() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"应用", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/advanced");
		excute(Object_Text, Operation_ClickWait, "电池优化");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "重置应用偏好设置");
	}
	/**
	 *存储设备和 USB
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_245() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"存储设备和 USB", "vertical");
		check(Object_Text, Operation_checkExist, "存储设备和 USB");
	}
	/**
	 *内部存储设备
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_246() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"存储设备和 USB", "vertical");
		excute(Object_Text, Operation_ClickWait,"内部存储设备");
		check(Object_Text, Operation_checkExist, "内部存储设备");
	}
	/**
	 *SD卡存储
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_247() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"存储设备和 USB", "vertical");
		excute(Object_ResIdContainsText, Operation_ClickWait, "android:id/title","SD");
		check(Object_ResourceId, Operation_checkExist, "com.android.documentsui:id/menu_sort");//SD卡界面排序按钮
	}
	/**
	 *电池
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_248() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		check(Object_Text, Operation_checkExist, "电池");
	}
	/**
	 *省电管理
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_249() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Text, Operation_ClickWait, "省电管理");
		check(Object_Text, Operation_checkExist, "您还没有安装第三方应用");
	}
	/**
	 *省电管理
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_250() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Description, Operation_ClickWait, "刷新");
	}
	/**
	 *省电菜单
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_252() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "节电助手");
		check(Object_Text, Operation_checkExist, "电池优化");
	}
	/**
	 *节电助手
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_253() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		check(Object_Text, Operation_checkExist, "节电助手");
	}
	/**
	 *节电助手
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_254() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		check(Object_ResourceId, Operation_CheckedFalse, "com.android.settings:id/switch_widget");//开关
	}
	/**
	 *打开节电助手
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_255() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		if(!(Boolean)excute(Object_ResourceId, Operation_IsChecked, "com.android.settings:id/switch_widget"))
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		}
		//check(Object_ResourceId, Operation_CheckedFalse, "com.android.settings:id/switch_widget");
		//连接USB时无法打开节电助手
	}
	/**
	 *点击自动开启
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_256() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		excute(Object_Text, Operation_ClickWait, "自动开启");
		check(Object_Text, Operation_checkExist, "一律不");
		check(Object_Text, Operation_checkExist, "电量剩余5%时");
		check(Object_Text, Operation_checkExist, "电量剩余15%时");
	}
	/**
	 *一律不
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_258() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		excute(Object_Text, Operation_ClickWait, "自动开启");
		excute(Object_Text, Operation_ClickWait, "一律不");
		check(Object_Text, Operation_checkExist, "一律不");
	}
	/**
	 *电量剩余5%时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_259() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		excute(Object_Text, Operation_ClickWait, "自动开启");
		excute(Object_Text, Operation_ClickWait, "电量剩余5%时");
		check(Object_Text, Operation_checkExist, "电量剩余5%时");
	}
	/**
	 *电量剩余15%时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_260() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "节电助手");
		excute(Object_Text, Operation_ClickWait, "自动开启");
		excute(Object_Text, Operation_ClickWait, "电量剩余15%时");
		check(Object_Text, Operation_checkExist, "电量剩余15%时");
	}
	/**
	 *电池优化
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_261() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"电池", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "电池优化");
		check(Object_Text, Operation_checkExist, "电池优化");
	}
	/**
	 * 跳转到内存页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_262() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");
		check(Object_Text, Operation_checkExist, "平均内存使用量");
	}
	
	/**
	 * 下拉框有“3小时” “6小时” “12小时” “1天”字样
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_263() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");
		excute(Object_Text, Operation_ClickWait, "3小时");
		check(Object_Text, Operation_checkExist, "3小时");
		check(Object_Text, Operation_checkExist, "6小时");
		check(Object_Text, Operation_checkExist, "12小时");
		check(Object_Text, Operation_checkExist, "1天");
		
	}
	/**
	 * 选定3小时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_264() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");	
		excute(Object_Text, Operation_ClickWait, "3小时");
		excute(Object_Text, Operation_ClickWait, "3小时");
		check(Object_Text, Operation_checkExist, "3小时");
	}
	/**
	 * 选定6小时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_265() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");	
		excute(Object_Text, Operation_ClickWait, "3小时");
		excute(Object_Text, Operation_ClickWait, "6小时");
		check(Object_Text, Operation_checkExist, "6小时");
	}
	/**
	 * 选定12小时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_266() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");	
		excute(Object_Text, Operation_ClickWait, "3小时");
		excute(Object_Text, Operation_ClickWait, "12小时");
		check(Object_Text, Operation_checkExist, "12小时");
	}
	/**
	 * 选定一天
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_267() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");	
		excute(Object_Text, Operation_ClickWait, "3小时");
		excute(Object_Text, Operation_ClickWait, "1天");
		check(Object_Text, Operation_checkExist, "1天");
	}
	
	/**
	 * 点击各个应用使用的内存
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_268() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");
		excute(Object_Text, Operation_ClickWait, "各个应用使用的内存");
		check(Object_Text, Operation_checkExist, "应用的内存使用量");
		
	}
	
	/**
	 * 按最高内存使用量排序
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_269() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		List<Integer> list=new ArrayList<Integer>();
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");
		excute(Object_Text, Operation_ClickWait, "各个应用使用的内存");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "按最高使用量排序");
		int count=(Integer)excute(Object_ResourceId, Operation_GetChildCount, "android:id/list");
		for (int i = 0; i < count-1; i++){
		String str1=(String)excute(Object_ResIdInstance, Operation_GetText, "android:id/summary",String.valueOf(i));
		String str2[]=str1.split(" ");
		int j=Integer.parseInt(str2[0]);
		list.add(j);
		}
		
		for(int k=0;k<count-2;k++){
		boolean boo=list.get(k)>=list.get(k+1);
		Assert.assertEquals(true, boo);
		}
	}
	/**
	 * 按平均内存使用量排序
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_270() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		List<Integer> list=new ArrayList<Integer>();
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");
		excute(Object_Text, Operation_ClickWait, "各个应用使用的内存");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "按最高使用量排序");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "按平均使用量排序");
		int count=(Integer)excute(Object_ResourceId, Operation_GetChildCount, "android:id/list");
		for (int i = 0; i < count-1; i++){
		String str1=(String)excute(Object_ResIdInstance, Operation_GetText, "android:id/summary",String.valueOf(i));
		String str2[]=str1.split(" ");
		int j=Integer.parseInt(str2[0]);
		list.add(j);
		}
		
		for(int k=0;k<count-2;k++){
		boolean boo=list.get(k)>=list.get(k+1);
		Assert.assertEquals(true, boo);
		}
	}
	/**
	 * 任意应用实际使用页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_271() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "内存","vertical");
		excute(Object_Text, Operation_ClickWait, "各个应用使用的内存");
		excute(Object_Text, Operation_ClickWait, "Android 操作系统");
		check(Object_Text, Operation_checkExist, "平均内存使用量");
		check(Object_Text, Operation_checkExist, "频率");
		check(Object_Text, Operation_checkExist, "最高使用量");
	}
	/**
	 * 点击用户页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_272() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "用户","vertical");
		check(Object_Text, Operation_checkExist, "添加用户");
	}
	/**
	 * 点击页面上位置信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_273() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "位置信息","vertical");
		check(Object_Text, Operation_checkExist, "最近的位置信息请求");
	}
	/**
	 * 位置信息默认开关状态
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_274() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "位置信息","vertical");
		check(Object_ResIdText, Operation_CheckedFalse, "com.android.settings:id/switch_widget","关闭");
	}
	/**
	 *打开位置信息开关 
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_275() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "位置信息","vertical");
		if (!(Boolean)excute(Object_ResourceId, Operation_IsChecked, "com.android.settings:id/switch_widget")) {
			excute(Object_Text, Operation_ClickWait, "关闭");
		}
		check(Object_ResIdText, Operation_CheckedTrue, "com.android.settings:id/switch_widget","开启");
		//清场
		excute(Object_Text, Operation_ClickWait, "开启");
		
	}
	
	/**
	 * 打开位置信息里的模式选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_276() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait, "位置信息","vertical");
		if (!(Boolean)excute(Object_ResourceId, Operation_IsChecked, "com.android.settings:id/switch_widget")) 
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		}
		//主体
		excute(Object_Text, Operation_ClickWait, "模式");
		check(Object_Text, Operation_checkExist, "位置信息模式");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		
	}
	/**
	 * 节电模式被选中
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_277() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait, "位置信息","vertical");
		if (!(Boolean)excute(Object_ResourceId, Operation_IsChecked, "com.android.settings:id/switch_widget")) 
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		}
		//主体
		excute(Object_Text, Operation_ClickWait, "模式");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox","1");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox","1");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		
	}
	/**
	 * 仅限设备被选中
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_278() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait, "位置信息","vertical");
		if (!(Boolean)excute(Object_ResourceId, Operation_IsChecked, "com.android.settings:id/switch_widget")) 
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		}
		//主体
		excute(Object_Text, Operation_ClickWait, "模式");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox","2");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox","2");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/switch_widget");
		
	}
	/**
	 * 跳转到“安全”界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_279() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		check(Object_Text, Operation_checkExist, "安全");
	}
	
	/**
	 * 安全-验证屏幕锁定方式：“无”“滑动”“图案”“PIN码”“密码”
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_280() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		check(Object_Text, Operation_checkExist, "无");
		check(Object_Text, Operation_checkExist, "滑动");
		check(Object_Text, Operation_checkExist, "图案");
		check(Object_Text, Operation_checkExist, "PIN码");
		check(Object_Text, Operation_checkExist, "密码");
	}
	
	/**
	 *  安全-验证屏幕锁定方式：无
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_281() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"无");
		check(Object_PeerTextID,Operation_TextEqualTrue,"屏幕锁定方式","android:id/summary","无");
	}
	
	/**
	 * 安全-验证屏幕锁定方式：滑动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_282() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"滑动");
		check(Object_PeerTextID,Operation_TextEqualTrue,"屏幕锁定方式","android:id/summary","滑动");
		//清场
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"无");
	}
	
	/**
	 * 安全-验证屏幕锁定方式：图案
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_283() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"图案");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/headerText","绘制解锁图案");
		excute(Object_Text,Operation_ClickWait,"取消");
	}
	
	/**
	 * 安全-验证屏幕锁定方式：Pin码
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_284() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"PIN码");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/headerText","PIN码至少应为4位");
		excute(Object_Text,Operation_ClickWait,"取消");
	}
	
	/**
	 * 安全-验证屏幕锁定方式：密码
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_285() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"密码");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/headerText","密码至少应包含4个字符");
		excute(Object_Text,Operation_ClickWait,"取消");
	}
	
	/**
	 * 安全-锁定屏幕消息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_286() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"滑动");
		//主体
		excute(Object_Text,Operation_ClickWait,"锁定屏幕消息");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","锁定屏幕消息");
		excute(Object_Text,Operation_ClickWait,"取消");
		//清场
		excute(Object_Text,Operation_ClickWait,"屏幕锁定方式");
		excute(Object_Text,Operation_ClickWait,"无");
	}
	
	/**
	 *  安全-加密手机
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_287() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"加密手机");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/initiate_encrypt","加密手机");
		
	}
	
	/**
	 * 安全-设置SIM卡锁定方式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_288() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_Text,Operation_ClickWait,"设置SIM卡锁定方式");
		check(Object_ResIdText,Operation_checkExist,"android:id/switchWidget","关闭");
	}
	
	/**
	 * 安全-设备管理器
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_289() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"设备管理器","vertical");
		check(Object_Text,Operation_ClickWait,"设备管理器");
	}
	
	/**
	 * 安全-信任的凭据
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_290() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"信任的凭据","vertical");
		check(Object_ResIdText,Operation_WaitForExists,"com.android.settings:id/trusted_credential_status","开启","1500");
	}
	
	/**
	 * 安全-从SD卡安装
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_291() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"从SD卡安装","vertical");
		check(Object_Text,Operation_WaitForExists,"打开文件","3000");
	}
	
	/**
	 * 安全-清除凭证 默认置灰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_292() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		check(Object_TextScroll,Operation_EnabledFalse,"清除凭据","vertical");
	}
	
	/**
	 * 安全-信任的代理 默认置灰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_293() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		check(Object_TextScroll,Operation_EnabledFalse,"信任的代理","vertical");
	}
	
	/**
	 * 安全-屏幕固定
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_294() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"屏幕固定","vertical");
		check(Object_Text,Operation_checkExist,"屏幕固定");
	}
	
	/**
	 * 安全-权查看使用情况的应用
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_295() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"安全","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"有权查看使用情况的应用","vertical");
		check(Object_Text,Operation_checkExist,"有权查看使用情况的应用");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"显示系统进程");
		check(Object_Text,Operation_checkExist,"重置应用偏好设置");
	}
	
	/**
	 * 进入账户界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_296() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"帐户","vertical");
		check(Object_Text,Operation_checkExist,"帐户");
	}
	
	/**
	 * 账户-添加账户界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_297() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"帐户","vertical");
		excute(Object_Text,Operation_ClickWait,"添加帐户");
		check(Object_Text,Operation_checkExist,"添加帐户");
	}
	
	/**
	 * 账户-添加账户界面-菜单
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_298() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"帐户","vertical");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"自动同步数据");
	}
	
	/**
	 * 进入 语言与输入法 界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_299() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		check(Object_Text,Operation_checkExist,"语言和输入法");
	}
	
	/**
	 * 进入 语言与输入法 --点击语言,中文（繁体）”、“中文（简体）”、“english”三个条目
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_300() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"语言");
		check(Object_Text,Operation_checkExist,"中文 (繁體)");
		check(Object_Text,Operation_checkExist,"中文 (简体)");
		check(Object_Text,Operation_checkExist,"English");
	}
	
	
	/** 
	 *  进入 语言与输入法 --点击语言--切换为 中文（繁体）
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_301() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"语言");
		excute(Object_Text,Operation_ClickWait,"中文 (繁體)");
		check(Object_Text,Operation_checkExist,"語言");
		//清场
		excute(Object_Text,Operation_ClickWait,"語言");
		excute(Object_Text,Operation_ClickWait,"中文 (简体)");
	}
	
	/** 
	 *  进入 语言与输入法 --点击语言--切换为 中文 (简体)
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_302() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"语言");
		excute(Object_Text,Operation_ClickWait,"中文 (简体)");
		check(Object_Text,Operation_checkExist,"语言");
		//清场
		excute(Object_Text,Operation_ClickWait,"语言");
		excute(Object_Text,Operation_ClickWait,"中文 (简体)");
	}
	
	/** 
	 *  进入 语言与输入法 --点击语言--切换为 English"
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_303() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"语言");
		excute(Object_Text,Operation_ClickWait,"English");
		check(Object_Text,Operation_checkExist,"Language");
		//清场
		excute(Object_Text,Operation_ClickWait,"Language");
		excute(Object_Text,Operation_ClickWait,"中文 (简体)");
	}
	
	/**
	 * 进入 语言与输入法--拼写和检查
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_304() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"拼写检查工具");
		check(Object_Text,Operation_checkExist,"拼写检查工具");
	}
	
	/**
	 * 进入 语言与输入法--拼写和检查--验证默认按钮是开启的
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */	
	public static void test_305() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"拼写检查工具");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
	}
	
	/**
	 * 进入 语言与输入法--拼写和检查--关闭按钮
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */	
	public static void test_306() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"拼写检查工具");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/switch_widget","开启");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
		//清场
		excute(Object_ResIdText,Operation_ClickWait,"com.android.settings:id/switch_widget","关闭");
		
	}
	
	/**
	 * 进入 语言与输入法--拼写和检查--点击语言
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_307() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"拼写检查工具");
		excute(Object_Text,Operation_ClickWait,"语言");
		check(Object_Text,Operation_checkExist,"使用系统语言");
		check(Object_Text,Operation_checkExist,"英文");
		check(Object_Text,Operation_checkExist,"英文 (美国)");
		check(Object_Text,Operation_checkExist,"英文 (英国)");
	}
	
	/**
	 * 进入 语言与输入法--拼写和检查--"Android拼写检查工具''
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_308() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"拼写检查工具");
		check(Object_ResIdContainsText,Operation_checkExist,"android:id/title","Android 拼写检查工具 ");
	}
	
	/**
	 * 进入 语言与输入法--拼写和检查--"Android拼写检查工具'--验证按钮默认为开启状态
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_309() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"拼写检查工具");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/pref_right_button");
		check(Object_ResourceId,Operation_CheckedTrue,"android:id/switchWidget");
	}
	
	/**
	 * 进入 语言与输入法--个人字典
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_310() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"所有语言");
		check(Object_Text,Operation_checkExist,"个人字典");
	}
	
	/**
	 * 进入 语言与输入法--个人字典-添加
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_311() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"所有语言");
		excute(Object_Description,Operation_ClickWait,"添加");
		check(Object_Text,Operation_checkExist,"输入字词");	
	}
	
	/**
	 * 进入 语言与输入法--个人字典--英文（美国）页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_312() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"英文 (美国)");
		check(Object_Text,Operation_checkExist,"个人字典");
		check(Object_Text,Operation_checkExist,"英文 (美国)");
	}
	
	/**
	 * 进入 语言与输入法--个人字典--英文（美国）页面--添加
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_313() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"英文 (美国)");
		excute(Object_Description,Operation_ClickWait,"添加");
		check(Object_Text,Operation_checkExist,"输入字词");
	}
	
	/**
	 * 进入 语言与输入法--个人字典--中文 (中国)页面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_314() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"中文 (中国)");
		check(Object_Text,Operation_checkExist,"个人字典");
		check(Object_Text,Operation_checkExist,"中文 (中国)");
	}
	
	/**
	 * 进入 语言与输入法--个人字典--中文 (中国)页面--添加
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_315() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"中文 (中国)");
		excute(Object_Description,Operation_ClickWait,"添加");
		check(Object_Text,Operation_checkExist,"输入字词");
	}
	
	/**
	 * 进入 语言与输入法--个人字典--中文 (中国)页面--添加1后删除
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_316() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"个人字典");
		excute(Object_Text,Operation_ClickWait,"中文 (中国)");
		excute(Object_Description,Operation_ClickWait,"添加");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/user_dictionary_add_word_text","1");
		excute(Object_Description,Operation_ClickWait,"向上导航");
		excute(Object_Text,Operation_ClickWait,"1");
		excute(Object_Description,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkNoExist,"1");
	}
	
	/**
	 * 进入 语言与输入法--当前输入法-弹出更改键盘
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_317() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"当前输入法");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","更改键盘");
		
	}
	

	/**
	 * 进入 语言与输入法--Android 键盘 (AOSP)
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_318() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"Android 键盘 (AOSP)");
		check(Object_Text,Operation_checkExist,"Android 键盘设置 (AOSP)");
	}
	
	/**
	 * 进入 语言与输入法--Android 键盘 (AOSP)--点击语言
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_319() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"Android 键盘 (AOSP)");
		excute(Object_Text,Operation_ClickWait,"语言");
		check(Object_Text,Operation_checkExist,"使用系统语言");
	}
	
	/**
	 * 进入 语言与输入法--Android 键盘 (AOSP)--点击外观和布局
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_320() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"Android 键盘 (AOSP)");
		excute(Object_Text,Operation_ClickWait,"外观和布局");
		check(Object_Text,Operation_checkExist,"主题背景");
		check(Object_Text,Operation_checkExist,"自定义输入样式");
	}
	
	/**
	 * 进入 语言与输入法--Android 键盘 (AOSP)--点击文字更正
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_321() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"Android 键盘 (AOSP)");
		excute(Object_Text,Operation_ClickWait,"文字更正");
		check(Object_Text,Operation_checkExist,"附加字典");
	}
	
	/**
	 * 进入 语言与输入法--Android 键盘 (AOSP)--点击高级
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_322() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"Android 键盘 (AOSP)");
		excute(Object_Text,Operation_ClickWait,"高级");
		check(Object_Text,Operation_checkExist,"弹出字符隐藏延迟");
		check(Object_Text,Operation_checkExist,"按键振动时长");
	}
	
	/**
	 * 进入 语言与输入法--文字转语音 (TTS) 输出
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_323() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_Text,Operation_ClickWait,"文字转语音 (TTS) 输出");
		check(Object_Text,Operation_checkExist,"首选引擎");
	}
	
	/**
	 * 进入 语言与输入法--指针速度
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_324() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"语言和输入法","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"指针速度","vertical");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","指针速度");
	}
	
	/**
	 * 进入 备份和重置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_325() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"备份和重置","vertical");
		check(Object_Text,Operation_checkExist,"备份和重置");
	}
	
	/**
	 * 进入 备份和重置--重置网络设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_326() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"备份和重置","vertical");
		excute(Object_Text,Operation_ClickWait,"重置网络设置");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/initiate_reset_network","重置设置");
	}
	
	/**
	 * 进入 备份和重置--恢复出厂设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_327() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"备份和重置","vertical");
		excute(Object_Text,Operation_ClickWait,"恢复出厂设置");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/initiate_master_clear","恢复手机出厂设置");
	}
	
	/**
	 * 
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_328() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"定时开关机","vertical");
		check(Object_Text,Operation_checkExist,"定时开关机");
	}
	
	/**
	 * 检查“日期和时间”字样
	 */
	public static void test_329() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		check(Object_Text,Operation_checkExist,"日期和时间");
	}
	
	/**
	 * 查看日期和时间中默认按钮开关状态，部分区域置灰
	 */
	public static void test_330() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/switchWidget","0","开启");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/switchWidget","1","关闭");
		check(Object_Text,Operation_EnabledFalse,"设置日期");
		check(Object_Text,Operation_EnabledFalse,"设置时间");
		check(Object_Text,Operation_EnabledFalse,"选择时区");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		check(Object_Text,Operation_CheckedTrue,"使用网络提供时间");
	}
	
	/**
	 * 关闭自动确定日期和时间，检查设置日期和设置时间为可用
	 */
	public static void test_331() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"关闭");
		check(Object_Text,Operation_EnabledTrue,"设置日期");
		check(Object_Text,Operation_EnabledTrue,"设置时间");
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"使用网络提供时间");
	}
	
	/**
	 * 关闭自动确定日期和时间，进入设置日期界面
	 */
	public static void test_332() 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"关闭");
		//主体
		excute(Object_Text,Operation_ClickWait,"设置日期");
		check(Object_ResourceId,Operation_checkExist,"android:id/datePicker");
		excute(Object_Device,Operation_PressBack);
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"使用网络提供时间");
	}
	
	/**
	 * 设置日期为2015年1月1日
	 */
	public static void test_333() throws UiObjectNotFoundException
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"关闭");
		//主体
		excute(Object_Text,Operation_ClickWait,"设置日期");
		excute(Object_ResourceId,Operation_ClickWait,"android:id/date_picker_header_year");
		//Object_TextScroll和scrollTextIntoView接口有个未名原因的bug,在年列表中只能滑动一屏
		excute(Object_TextScroll,Operation_ClickWait,"2015","vertical");
		/*UiScrollable yearList = new UiScrollable(new UiSelector().scrollable(true));
		while(!(Boolean) excute(Object_ResIdText, Operation_Exists, "android:id/text1","2015"))
		{
			yearList.scrollTextIntoView("2015");
		}
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1","2015");*/
		
		String date = (String) excute(Object_ResourceId,Operation_GetText,"android:id/date_picker_header_date");
		String actual[] = date.split("月");
		int month=Integer.valueOf(actual[0]).intValue();
		while(month-->1)
			excute(Object_Description,Operation_ClickWait,"上个月");	
		excute(Object_Text,Operation_ClickWait,"1");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"2015年1月1日");
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"使用网络提供时间");
	}
	
	/**
	 * 关闭自动确定日期和时间，进入设置时间界面
	 */
	public static void test_334() 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"关闭");
		//主体
		excute(Object_Text,Operation_ClickWait,"设置时间");
		check(Object_ResourceId,Operation_checkExist,"android:id/timePicker");
		excute(Object_Device,Operation_PressBack);
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"使用网络提供时间");
	}
	
	/**
	 * 设置时间为上午8点30分
	 */
	public static void test_335() 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"关闭");
		//主体
		excute(Object_Text,Operation_ClickWait,"设置时间");
		excute(Object_ResourceId,Operation_ClickWait,"android:id/am_label");
		excute(Object_Description,Operation_ClickWait,"8");
		excute(Object_Description,Operation_ClickWait,"30");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"上午8:30");
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定日期和时间");
		excute(Object_Text,Operation_ClickWait,"使用网络提供时间");
	}
	
	/**
	 * 关闭自动确定时区
	 */
	public static void test_336() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","0");
		check(Object_Text,Operation_EnabledTrue,"选择时区");
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
	}
	
	/**
	 * 进入选择时区界面
	 */
	public static void test_337() 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
		//主体
		excute(Object_Text,Operation_ClickWait,"选择时区");
		check(Object_Text,Operation_checkExist,"选择时区");
		excute(Object_Device,Operation_PressBack);
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
	}
	
	/**
	 * 设置时区为香港
	 */
	public static void test_338() 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
		//主体
		excute(Object_Text,Operation_ClickWait,"选择时区");
		excute(Object_TextScroll,Operation_ClickWait,"香港", "vertical");
		check(Object_Text,Operation_checkExist,"GMT+08:00 香港标准时间");
		//清场
		excute(Object_Text,Operation_ClickWait,"自动确定时区");
	}
	
	/**
	 * 设置时间为24小时制
	 */
	public static void test_339() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"日期和时间", "vertical");
		excute(Object_Text,Operation_ClickWait,"使用 24 小时制");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","1");
		String time = (String) excute(Object_PeerTextID,Operation_GetText,"使用 24 小时制","android:id/summary");
		Assert.assertTrue(!time.contains("上午")&&!time.contains("下午"));
		//清场
		excute(Object_Text,Operation_ClickWait,"使用 24 小时制");
	}
	
	/**
	 * 进入无障碍界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_340() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		check(Object_Text,Operation_checkExist,"无障碍");
	}
	
	/**
	 * 进入无障碍界面-显示未安装任何服务
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_341() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","未安装任何服务");
	}
	
	/**
	 * 进入无障碍界面--字幕
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_342() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"字幕");
		check(Object_Text,Operation_checkExist,"字幕");
	}
	
	/**
	 * 进入无障碍界面--字幕--按钮关闭，语言等置灰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_343() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"字幕");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
		check(Object_Text,Operation_EnabledFalse,"语言");
		check(Object_Text,Operation_EnabledFalse,"文字大小");
		check(Object_Text,Operation_EnabledFalse,"字幕样式");
	}
	
	/**
	 * 进入无障碍界面--字幕--按钮开启，语言等可设置
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_344() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"字幕");
		if((Boolean)excute(Object_ResIdText, Operation_Exists,"com.android.settings:id/switch_widget","关闭"))
		{
			excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","关闭");
		}
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
		check(Object_Text,Operation_EnabledTrue,"语言");
		check(Object_Text,Operation_EnabledTrue,"文字大小");
		check(Object_Text,Operation_EnabledTrue,"字幕样式");
		//清场
		excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","开启");
	}
	
	/**
	 * 进入无障碍界面--字幕--按钮开启，点击语言
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_345() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"字幕");
		if((Boolean)excute(Object_ResIdText, Operation_Exists,"com.android.settings:id/switch_widget","关闭"))
		{
			excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","关闭");
		}
		excute(Object_Text, Operation_ClickWait,"语言");
		check(Object_Text,Operation_checkExist,"English");
		check(Object_Text,Operation_checkExist,"中文 (繁體)");
		check(Object_Text,Operation_checkExist,"中文 (简体)");
		excute(Object_Text, Operation_ClickWait,"取消");
		//清场
		excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","开启");
	}
	
	/**
	 * 进入无障碍界面--字幕--按钮开启，点击文字大小
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_346() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"字幕");
		if((Boolean)excute(Object_ResIdText, Operation_Exists,"com.android.settings:id/switch_widget","关闭"))
		{
			excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","关闭");
		}
		excute(Object_Text, Operation_ClickWait,"文字大小");
		check(Object_Text,Operation_checkExist,"小");
		check(Object_Text,Operation_checkExist,"正常");
		check(Object_Text,Operation_checkExist,"大");
		excute(Object_Text, Operation_ClickWait,"取消");
		//清场
		excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","开启");
	}
	
	/**
	 * 进入无障碍界面--字幕--按钮开启，点击字幕样式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_347() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"字幕");
		if((Boolean)excute(Object_ResIdText, Operation_Exists,"com.android.settings:id/switch_widget","关闭"))
		{
			excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","关闭");
		}
		excute(Object_Text, Operation_ClickWait,"字幕样式");
		check(Object_ResIdText,Operation_checkExist,"android:id/alertTitle","字幕样式");
		excute(Object_Text, Operation_ClickWait,"取消");
		//清场
		excute(Object_ResIdText, Operation_ClickWait,"com.android.settings:id/switch_widget","开启");
	}
	
	/**
	 * 进入无障碍界面--放大手势
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_348() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		excute(Object_Text, Operation_ClickWait,"放大手势");
		check(Object_Text,Operation_checkExist,"放大手势");
		check(Object_ResIdText,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
	}
	
	/**
	 * 进入无障碍界面--大号字体
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_349() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"无障碍", "vertical");
		if((Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"android:id/switchWidget","0"))
		{
			excute(Object_Text, Operation_ClickWait,"大号字体");
		}
		Rect bound = (Rect) excute(Object_Text,Operation_GetBounds,"大号字体");
		excute(Object_Text, Operation_ClickWait,"大号字体");
		check(Object_ResIdText,Operation_checkExist,"android:id/switchWidget","开启");
		Rect bound1 = (Rect) excute(Object_Text,Operation_GetBounds,"大号字体");
		Assert.assertNotSame(bound, bound1);
		//清场
		excute(Object_Text, Operation_ClickWait,"大号字体");
	}
	
	
	/**
	 * 高对比度文字
	 */
	public static void test_350() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "无障碍", "vertical");
		excute(Object_TextScroll, Operation_Exists, "高对比度文字", "vertical");
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", "1")) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "高对比度文字", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", "1");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "高对比度文字", "vertical");
	}
	/**
	 * 按电源按钮结束通话
	 */
	public static void test_351() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "无障碍", "vertical");
		excute(Object_TextScroll, Operation_Exists, "按电源按钮结束通话", "vertical");
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", "2")) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "按电源按钮结束通话", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", "2");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "按电源按钮结束通话", "vertical");
	}
	/**
	 * 自动旋转屏幕
	 */
	public static void test_352() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "无障碍", "vertical");
		excute(Object_TextScroll, Operation_Exists, "自动旋转屏幕", "vertical");
		if ((Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", "3")) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "自动旋转屏幕", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget", "3");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "自动旋转屏幕", "vertical");
	}
	/**
	 * 说出密码
	 */
	public static void test_353() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "无障碍", "vertical");
		excute(Object_TextScroll, Operation_Exists, "说出密码", "vertical");
		Rect textArea = (Rect) excute(Object_Text, Operation_GetBounds, "说出密码");
        int i = 0;
        do{
            Rect switchButton = (Rect) excute(Object_ResIdInstance, Operation_GetBounds, "android:id/switchWidget",Integer.toString(i));
            if(Math.abs(textArea.centerY() - switchButton.centerY()) <= 1)
                break;
            i++;
        }
        while(true);
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", Integer.toString(i))) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "说出密码", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", Integer.toString(i));
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "说出密码", "vertical");
	}
	/**
	 * 文字转语音（TTS)输出
	 */
	public static void test_354() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "无障碍", "vertical");
		excute(Object_TextScroll, Operation_ClickWait, "文字转语音 (TTS) 输出", "vertical");
		check(Object_Text, Operation_checkExist, "首选引擎");
	}
	/**
	 * 触摸和按住延迟
	 */
	public static void test_355() 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "无障碍", "vertical");
		excute(Object_TextScroll, Operation_ClickWait, "触摸和按住延迟", "vertical");
		check(Object_Text, Operation_checkExist, "短");
		check(Object_Text, Operation_checkExist, "中");
		check(Object_Text, Operation_checkExist, "长");
	}
	/**
	 * 颜色反转
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_356() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"无障碍","vertical");
		excute(Object_TextScroll,Operation_Exists,"颜色反转","vertical");
		SettingCommon.take_temp_pic(getUiDevice(), "1");
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", "3")) 
		{
			excute(Object_TextScroll,Operation_ClickWait,"颜色反转","vertical");
		}
//		ClearBackgroundApp();
//		Wait(1000);
//		DeviceCommon.enterApp(Devices_Desc_Setting);
//		excute(Object_TextScroll,Operation_ClickWait,"无障碍","vertical");
//		excute(Object_TextScroll,Operation_Exists,"颜色反转","vertical");
//		SettingCommon.take_temp_pic(getUiDevice(), "2");
	    check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", "3");//验证开关
//		SettingCommon.check_pic("1", "2", 0.8);//验证颜色不同
		//清场
//		SettingCommon.delete_pic("1", "2");
		excute(Object_TextScroll,Operation_ClickWait,"颜色反转","vertical");
	}
	/**
	 * 色彩校正
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_357() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"无障碍","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"色彩校正","vertical");
		check(Object_Text,Operation_checkExist,"校正模式");
		check(Object_Text,Operation_checkExist,"关闭");
	}
	/**
	 * 打印
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_358() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"打印","vertical");
		check(Object_Text,Operation_checkExist,"打印");
	}
	/**
	 * 关于手机
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_359() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"关于手机","vertical");
		check(Object_Text,Operation_checkExist,"系统软件更新");
	}
	/**
	 * 系统软件更新
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_360() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"关于手机","vertical");
		excute(Object_Text,Operation_ClickWait,"系统软件更新");
		check(Object_Text,Operation_checkExist,"系统软件更新");
	}
	/**
	 * 状态信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_361() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"关于手机","vertical");
		excute(Object_Text,Operation_ClickWait,"状态信息");
		check(Object_Text,Operation_checkExist,"电池状态");
	}
	/**
	 * 状态信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_362() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "关于手机", "vertical");
		excute(Object_Text, Operation_ClickWait, "状态信息");
		excute(Object_Text, Operation_ClickWait, "SIM卡状态");
		check(Object_TextScroll, Operation_checkExist, "网络", "vertical");
		check(Object_TextScroll, Operation_checkExist, "信号强度", "vertical");
		check(Object_TextScroll, Operation_checkExist, "移动网络类型", "vertical");
		check(Object_TextScroll, Operation_checkExist, "服务状态", "vertical");
		check(Object_TextScroll, Operation_checkExist, "漫游", "vertical");
		check(Object_TextScroll, Operation_checkExist, "移动网络状态", "vertical");
		check(Object_TextScroll, Operation_checkExist, "IMEI", "vertical");
		check(Object_TextScroll, Operation_checkExist, "IMEI SV", "vertical");
	}
	/**
	 * 点击IMEI
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_363() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "关于手机", "vertical");
		excute(Object_Text, Operation_ClickWait, "状态信息");
		excute(Object_Text, Operation_ClickWait, "SIM卡状态");
		excute(Object_TextScroll, Operation_ClickWait, "IMEI", "vertical");
		check(Object_TextScroll, Operation_checkExist, "IMEI", "vertical");
	}
	/**
	 * 法律信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_364() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "关于手机", "vertical");
		excute(Object_Text, Operation_ClickWait, "法律信息");
		check(Object_Text, Operation_checkExist, "开放源代码许可");
	}
	/**
	 * 开放源代码许可
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_365() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "关于手机", "vertical");
		excute(Object_Text, Operation_ClickWait, "法律信息");
		excute(Object_Text, Operation_ClickWait, "开放源代码许可");
		check(Object_Text, Operation_checkExist, "开放源代码许可");
	}
	/**
	 * 系统 WebView 许可
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_366() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait, "关于手机", "vertical");
		excute(Object_Text, Operation_ClickWait, "法律信息");
		excute(Object_Text, Operation_ClickWait, "系统 WebView 许可");
		check(Object_Text, Operation_checkExist, "系统 WebView 许可");
	}


}