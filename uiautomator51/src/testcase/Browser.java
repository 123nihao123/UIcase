package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;



import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


import framework.common.BrowserCommon;
import framework.common.DeviceCommon;

public class Browser extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Browser);

   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }

	
	/**
	 * 进入浏览器
	 */
	public static void test_000()
	{
		//主体
		  excute(Object_Description, Operation_WaitForExists, "更多选项", "10000");
		  excute(Object_Description, Operation_ClickWait, "更多选项");
		  excute(Object_TextScroll, Operation_ClickWait, "设置", "vertical");
		  excute(Object_Text, Operation_ClickWait,"常规");
		  excute(Object_Text, Operation_ClickWait,"设置主页");
		  excute(Object_Text, Operation_ClickWait,"空白页");
		  excute(Object_Device, Operation_PressBack);
		  excute(Object_Device, Operation_PressBack);
		  excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		  excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/closetab");
		  
	}
	/**
	 * 进入浏览器
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/url");
	}
	
	/**
	 * 进入www.baidu.com
	 */
	public static void test_002()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		check(Object_Description,Operation_checkExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入www.baidu.com,清空地址栏
	 */
	public static void test_003()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/clear");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/url","搜索或输入网址");
	}
	
	/**
	 * 进入www.baidu.com,刷新
	 */
	public static void test_004()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_Description,Operation_ClickWait,"刷新网页");
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		check(Object_Description,Operation_checkExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入www.baidu.com,返回前一页
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_005() throws UiObjectNotFoundException
	{
		//前提
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/closetab");
		//主体
		DeviceCommon.enterApp(Devices_Desc_Browser);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/back");
		excute(Object_Text,Operation_WaitForExists,"about:blank","50000");
		check(Object_Description,Operation_checkNoExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入www.baidu.com,跳转下一页
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_006() throws UiObjectNotFoundException
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.baidu.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/url");
		excute(Object_ResourceId,Operation_SetText,"com.android.browser:id/url","www.meituan.com");
		excute(Object_Device, Operation_PressEnter);
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/favicon","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/back");
		excute(Object_Description,Operation_WaitForExists,"百度一下,你就知道","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/forward");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/favicon","50000");
		check(Object_Description,Operation_checkNoExist,"百度一下,你就知道");
	}
	
	/**
	 * 进入添加tab页面
	 */
	public static void test_007()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/newtab");
	}
	
	/**
	 * 添加tab页面
	 */
	public static void test_008()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/newtab");
		excute(Object_Text,Operation_WaitForExists,"about:blank","50000");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/tabcount","2");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		if ((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.browser:id/closetab"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/closetab","1");
		}
	}
	
	/**
	 * 添加tab页面,删除一个窗口
	 */
	public static void test_009()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/newtab");
		excute(Object_Text,Operation_WaitForExists,"about:blank","50000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.browser:id/closetab","1");
		excute(Object_Device, Operation_PressBack);
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/tabcount","1");
	}
	
	/**
	 * 进入书签
	 */
	public static void test_010()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"书签");
		check(Object_Text,Operation_checkExist,"书签和历史");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单，菜单上有 功能项：退出、主页、历史记录、设置
	 */
	public static void test_011()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"退出");
		check(Object_Text,Operation_checkExist,"主页");
		check(Object_Text,Operation_checkExist,"历史记录");
		check(Object_Text,Operation_checkExist,"设置");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：退出
	 */
	public static void test_012()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"退出");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.browser:id/newtab");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：主页
	 */
	public static void test_013()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"主页");
		check(Object_ResIdText,Operation_checkExist,"com.android.browser:id/url","about:blank");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：历史记录
	 */
	public static void test_014()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"历史记录");
		check(Object_Text,Operation_checkExist,"今天");
	}
	
	/**
	 *  添加tab页面，弹出功能下拉菜单：设置
	 */
	public static void test_015()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/tabs");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		check(Object_Text,Operation_checkExist,"常规");
	}
	
	
	/**
	 * 主页设置
	 */
	public static void test_057()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		check(Object_Text, Operation_checkExist, "当前页");
		check(Object_Text, Operation_checkExist, "空白页");
		check(Object_Text, Operation_checkExist, "默认页");
		check(Object_Text, Operation_checkExist, "页面导航");
		check(Object_Text, Operation_checkExist, "其他");
	}
	/**
	 * 当前页
	 */
	public static void test_058()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		String hmoepage = (String) excute(Object_ResourceId, Operation_GetText, "android:id/summary");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "当前页");
		excute(Object_Text, Operation_WaitForExists, hmoepage, "10000");
		check(Object_Text, Operation_checkExist, hmoepage);
	}
	/**
	 * 空白页
	 */
	public static void test_059()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "空白页");
		check(Object_Text, Operation_checkExist, "空白页");
	}
	/**
	 * 默认页
	 */
	public static void test_060()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "默认页");
		excute(Object_Text, Operation_WaitForExists, "http://www.wo.com.cn", "10000");
		check(Object_Text, Operation_checkExist, "http://www.wo.com.cn");
	}
	/**
	 * 页面导航
	 */
	public static void test_061()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "页面导航");
		excute(Object_Text, Operation_WaitForExists, "页面导航", "10000");
		check(Object_Text, Operation_checkExist, "页面导航");
	}
	/**
	 * 其它主页
	 */
	public static void test_062()
	{
		//主体
		BrowserCommon.settingMenu("常规");
		excute(Object_Text, Operation_ClickWait, "设置主页");
		excute(Object_Text, Operation_ClickWait, "其他");
		excute(Object_ResourceId, Operation_SetText, "android:id/custom", "www.baidu.com");
		excute(Object_Text, Operation_ClickWait, "确定");
		excute(Object_Text, Operation_WaitForExists, "http://www.baidu.com/", "10000");
		check(Object_Text, Operation_checkExist, "http://www.baidu.com/");
	}
	/**
	 * 清除缓存
	 */
	public static void test_063()
	{
		//主体
		BrowserCommon.PrivacySecurity("清除缓存");
	}
	/**
	 * 清除缓存
	 */
	public static void test_064()
	{
		//主体
		BrowserCommon.PrivacySecurity("清除历史记录");
	}
	/**
	 * 显示安全警告
	 */
	public static void test_065()
	{
		//主体
		BrowserCommon.settingMenu("隐私和安全");
		if((boolean) excute(Object_ResIdInstance, Operation_IsChecked, "android:id/checkbox", "0"))
			excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox", "0");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "0");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "0");
	}
}