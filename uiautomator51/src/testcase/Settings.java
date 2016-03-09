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
		
		
		
		
		
		
		
		
		
		
	
}