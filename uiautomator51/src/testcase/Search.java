package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CallLogCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.SearchCommon;

public class Search extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Search);	
   }
	       
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 设置预置数据
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_000() throws UiObjectNotFoundException
	{
		//主体
		CallLogCommon.fillCallLogData();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);	
		ContactCommon.addName("本机", "SearchContact");
	}
	/**
	 * 还原默认设置
	 */
	public static void test_999() throws UiObjectNotFoundException
	{
		//主体
		String[] str={"网络","通讯录"};
		SearchCommon.chooseSearchItem(str);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);	
		ContactCommon.BatchDelete("所有联系人");
	}
	/**
	 * 进入搜索页面
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResIdText,Operation_checkExist,"android:id/title","搜索");	
	}
	/**
	 * 进入搜索页面
	 */
	public static void test_002()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_indicator");
		String[] str={"全部","网络","通讯录"};
		DeviceCommon.checkForExist(str);
	}
	/**
	 * 进入搜索设置页面
	 */
	public static void test_003()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_indicator");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_edit_items");
		String[] str={"搜索设置","可搜索项","默认搜索引擎","清除快捷方式"};
		DeviceCommon.checkForExist(str);
	}
	/**
	 * 搜索设置页面——点击可搜索项
	 */
	public static void test_005()
	{
		//主体
		SearchCommon.enterSearchSettings("可搜索项");
		String[] str={"网络","信息","应用","日历","通讯录","通话记录","音乐"};
		DeviceCommon.checkForExist(str);
	}
	/**
	 * 搜索设置页面——点击可搜索项全部勾选
	 */
	public static void test_006()
	{
		//主体
		SearchCommon.enterSearchSettings("可搜索项");
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		for(int i=0;i<num1;i++)
		{
			if(!(Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"android:id/checkbox",String.valueOf(i)))
				excute(Object_ResIdInstance,Operation_ClickWait,"android:id/checkbox",String.valueOf(i));
		}
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_indicator");
		int num2=(int)excute(Object_ResourceId,Operation_GetChildCount,"com.android.quicksearchbox:id/corpus_grid");
		Assert.assertTrue(num2==num1+1);
	}
	/**
	 * 搜索设置页面——默认引擎
	 */
	public static void test_007()
	{
		//主体
		SearchCommon.enterSearchSettings("默认搜索引擎");
		String[] str={"百度","谷歌","必应"};
		DeviceCommon.checkForExist(str);
	}
	/**
	 * 搜索设置页面——默认引擎——谷歌
	 */
	public static void test_008()
	{
		//主体
		SearchCommon.setSearchEngine("谷歌");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.browser:id/url","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/url");
		check(Object_ResourceId,Operation_TextContainsTrue,"com.android.browser:id/url","google");
	}
	/**
	 * 搜索设置页面——默认引擎——必应
	 */
	public static void test_010()
	{
		//主体
		SearchCommon.setSearchEngine("必应");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/url");
		check(Object_ResourceId,Operation_TextContainsTrue,"com.android.browser:id/url","bing");
	}
	/**
	 * 清除快捷方式
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_011() throws UiObjectNotFoundException
	{
		//前提  创建一个快捷方式
		SearchCommon.searchRange("应用");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","s");
		excute(Object_Text,Operation_ClickWait,"SIM卡工具包");
		excute(Object_Device,Operation_PressBack);
		DeviceCommon.ClearTextField("com.android.quicksearchbox:id/search_src_text");
		//主体
		SearchCommon.enterSearchSettings("清除快捷方式");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/title","1");
		excute(Object_Text,Operation_WaitForExists,"清除","5000");
		excute(Object_Text,Operation_ClickWait,"清除");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkNoExist,"SIM卡工具包");
	}
	/**
	 * 搜索范围——全部
	 * 在全部的状态下搜索，联系人和音乐都会出现
	 */
	public static void test_012()
	{
		//主体
		SearchCommon.searchRange("全部");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","search");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Text,Operation_WaitForExists,"SearchMusic","25000");
		check(Object_Text,Operation_checkExist,"SearchContact");
		check(Object_Text,Operation_checkExist,"SearchMusic");
	}
	/**
	 * 搜索范围——网络
	 */
	public static void test_013()
	{
		//主体
		SearchCommon.searchRange("网络");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","search");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/search_go_btn");
		check(Object_ResourceId,Operation_checkExist,"com.android.browser:id/url");
	}
	/**
	 * 搜索范围——应用
	 */
	public static void test_014()
	{
		//主体
		SearchCommon.searchRange("应用");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","s");
		check(Object_Text,Operation_checkExist,"SIM卡工具包");
		excute(Object_Text,Operation_ClickWait,"SIM卡工具包");
		excute(Object_Text,Operation_WaitForExists,"SIM卡工具包","5000");
		check(Object_Text,Operation_checkExist,"SIM卡工具包");
	}
	/**
	 * 搜索范围——日历
	 */
	public static void test_015()
	{
		//主体
		SearchCommon.searchRange("日历");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","search");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/search_go_btn");
		check(Object_ClassContainsText, Operation_checkExist, "android.widget.TextView", "触摸可查看");
	}
	/**
	 * 搜索范围——通讯录
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_016() throws UiObjectNotFoundException
	{
		//主体
		DeviceCommon.enterApp(Devices_Desc_Search);	
		SearchCommon.searchRange("通讯录");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","search");
		check(Object_Text,Operation_checkExist,"SearchContact");
		excute(Object_Text,Operation_ClickWait,"SearchContact");
		excute(Object_Text,Operation_WaitForExists,"添加电话号码","5000");
		check(Object_Text,Operation_checkExist,"添加电话号码");
	}
	/**
	 * 搜索范围——通话记录
	 */
	public static void test_017()
	{
		//主体
		SearchCommon.searchRange("通话记录");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","10086");
		check(Object_Text,Operation_checkExist,"10086");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.quicksearchbox:id/text2","10086");
		check(Object_Text,Operation_checkExist,"通话详情");
	}
	/**
	 * 搜索范围——音乐
	 */
	public static void test_018()
	{
		//主体
		SearchCommon.searchRange("音乐");
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","search");
		check(Object_Text,Operation_checkExist,"SearchMusic");
		excute(Object_Text,Operation_ClickWait,"SearchMusic");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.music:id/pause","5000");
		check(Object_ResourceId, Operation_checkExist, "com.android.music:id/pause");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.music:id/pause");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
