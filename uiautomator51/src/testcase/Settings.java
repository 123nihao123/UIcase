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
	 * 验证WLAN开关是开启的
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 * @throws IOException 
	 */
	public void test_014() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/title","WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		else
		{
			excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
		}
		check(Object_ResourceId, Operation_checkExist,"com.android.settings:id/switch_text","开启");
		String Num = DeviceCommon.runADBCommand("settings get global wifi_on");
		String num = "1\n";
		Assert.assertEquals(Num, num);
		
	}
	
	
	/**
	 * 打开WLAN，出现不同的WiFi
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_016() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/title","WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		else
		{
			excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
		}
		check(Object_Text,Operation_checkExist,"Testteam");
		check(Object_Text,Operation_checkExist,"TSTest23");
		check(Object_Text,Operation_checkExist,"ThunderSoft23");
		
	}	
	
	/**
	 * 连接一个wifi
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_017() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/title","WLAN");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		}
		else
		{
			excute(Object_ResourceId, Operation_Exists,"com.android.settings:id/switch_text","开启");
		}
		SettingCommon.addWifi("Testteam", "WPA/WPA2 PSK", "test12345678");
		//清场
		
	}
	
	/**
	 * 验证显示 添加网络，刷新，高级选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_018() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/title","WLAN");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_ResourceId,Operation_checkExist,"android:id/title","添加网络");
		check(Object_ResourceId,Operation_checkExist,"android:id/title","刷新");
		check(Object_ResourceId,Operation_checkExist,"android:id/title","高级");
	}
	
	/**
	 * WLAN-选项-高级界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_019() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait,"com.android.settings:id/title","WLAN");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"高级");
		check(Object_Text,Operation_checkExist,"高级WLAN");
	}
	
	/**
	 * 关闭WIFI
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_020() throws UiObjectNotFoundException, RemoteException 
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
		if((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.settings:id/switch_text","关闭"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget","关闭");
		}
		else
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
		}
	}
	
	/**
	 * 开启蓝牙按钮	
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_023() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"蓝牙");
	    if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
		    excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		    check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
		}
		else
		{
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
		}   
	    //清场
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
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
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
		}
		else
		{
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
		}  	
		check(Object_Text,Operation_WaitForExists,"1","5000");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
	}
	
	/**
	 * 关闭蓝牙按钮
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_026() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		if((Boolean) excute(Object_Text,Operation_Exists,"关闭"))
		{	
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			excute(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","开启");
		}
		else
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
			check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/switch_widget","关闭");
		}  	
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
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_ResourceId,Operation_checkExist,"android:id/title","刷新");
		check(Object_ResourceId,Operation_checkExist,"android:id/title","重命名此设备");
		check(Object_ResourceId,Operation_checkExist,"android:id/title","显示收到的文件");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_WaitForExists,"未启用","5000");
		check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.settings:id/universal_switch","0");
		//清场
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","0");
		excute(Object_Text,Operation_ClickWait,"确定");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		check(Object_ResourceId,Operation_checkExist,"android:id/alertTitle","SIM 卡插槽 1");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/sim_name","SIM1");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"android:id/summary","SIM1");
		
		
	}
	
	/**
	 * 更改SIM1颜色
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_033() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		excute(Object_Text,Operation_ClickWait,"粉红色");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/color_text","粉红色");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","0");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/display_number","123");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"android:id/summary","123");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Text,Operation_WaitForExists,"未启用","40000");
		check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.settings:id/universal_switch","1");
		//清场
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","1");
		excute(Object_Text,Operation_ClickWait,"确定");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		check(Object_ResourceId,Operation_checkExist,"android:id/alertTitle","SIM 卡插槽 2");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/sim_name","SIM2");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"android:id/summary","SIM2");
	}
	
	/**
	 * 更改SIM2颜色
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public void test_038() throws UiObjectNotFoundException, RemoteException 
	{
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		excute(Object_Text,Operation_ClickWait,"紫色");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/color_text");
		check(Object_ResourceId,Operation_checkExist,"com.android.settings:id/color_text","紫色");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/summary","1");
		excute(Object_ResourceId,Operation_SetText,"com.android.settings:id/display_number","123");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"android:id/summary","123");
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
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
		String Num = DeviceCommon.runADBCommand("settings get global data_remain_unchanged");
		String num = "0\n";
		Assert.assertEquals(Num, num);
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
		check(Object_ResourceId,Operation_checkExist,"android:id/alertTitle","选择用于通话的 SIM 卡");
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
		check(Object_ResourceId,Operation_checkExist,"android:id/alertTitle","主卡选择");
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
		excute(Object_Text,Operation_ClickWait,"SIM1");
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
		excute(Object_Text,Operation_ClickWait,"主卡选择");
		excute(Object_Text,Operation_ClickWait,"SIM2");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResIdInstance,Operation_TextEqualTrue,"android:id/summary","5","SIM2");
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
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "7");
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
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "8");
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
		
		
		
		
		
		
		
		
	
}