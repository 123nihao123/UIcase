package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.ClearBackgroundApp;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.check;
import static framework.excute.Excute.excute;

import java.io.IOException;

import junit.framework.Assert;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CallFireWallCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.InteractionCommon;
import framework.common.MessageCommon;

public class CallFireWall extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_CallFireWall);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 进入电话记录
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_000() throws UiObjectNotFoundException
	{
		//主体
		CallFireWallCommon.fillIncomingCallData();
		CallFireWallCommon.fillSMSData();
		CallFireWallCommon.fillThreeBlockContact();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		ContactCommon.addNameAndTel("本机", "forBoth", "10086111");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addNameAndTel("本机", "forCall", "10086112");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addNameAndTel("本机", "forSms", "10086113");
	}
	public static void test_999() throws UiObjectNotFoundException
	{
		//主体
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
	}
	/**
	 * 进入来电防火墙页面
	 */
	public static void test_001()
	{
		//主体
		check(Object_Text,Operation_checkExist,"来电防火墙");
	}
	/**
	 * 页面上有3个tab“黑名单'"电话记录”“短信记录”
	 */
	public static void test_002()
	{
		//主体
		check(Object_Text,Operation_checkExist,"黑名单");
		check(Object_Text,Operation_checkExist,"电话记录");
		check(Object_Text,Operation_checkExist,"短信记录");
	}
	/**
	 * 点击页面上的“黑名单”
	 */
	public static void test_103()
	{
		//前提
		CallFireWallCommon.BatchDelete("黑名单");
		//主体
		check(Object_Text,Operation_checkExist,"黑名单为空。");
	}
	/**
	 * 黑名单——菜单
	 */
	public static void test_108()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		String[] str={"添加","批量删除","添加拦截电话","添加拦截短信","添加拦截电话和短信"};
		DeviceCommon.checkForExist(str);
		check(Object_ClassInstance,Operation_EnabledFalse,"android.widget.LinearLayout","1");
	}
	/**
	 * 黑名单——菜单——添加
	 */
	public static void test_110()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"添加");
		check(Object_Text,Operation_checkExist,"添加联系人");
	}
	/**
	 * 黑名单——菜单——添加——确定
	 */
	public static void test_111()
	{
		//主体
		CallFireWallCommon.addBlackContact("Black", "1008611", "11");
		check(Object_ResIdText,Operation_checkExist,"com.sprd.firewall:id/phone_name","Black");
	}
	/**
	 * 查看黑名单的拦截类型(短信、电话)
	 */
	public static void test_012()
	{
		//主体
		CallFireWallCommon.checkInterceptionType("TestForBoth", "11");
	}
	/**
	 * 查看黑名单的拦截类型(短信)
	 */
	public static void test_013()
	{
		CallFireWallCommon.checkInterceptionType("TestForSMS", "10");
	}
	/**
	 * 查看黑名单的拦截类型(电话)
	 */
	public static void test_014()
	{
		CallFireWallCommon.checkInterceptionType("TestForTele", "01");
	}
	/**
	 * 长按一个黑名单
	 */
	public static void test_015()
	{
		//主体
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.firewall:id/phone_name","0");
		String[] str={"删除","编辑","详情"};
		DeviceCommon.checkForExist(str);
	}
	/**
	 * 长按一个黑名单——删除
	 */
	public static void test_016()
	{
		//主体
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.firewall:id/phone_name","0");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要移除黑名单吗？");
		excute(Object_Text,Operation_ClickWait,"确定");
		int num2=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		Assert.assertTrue(num2==num1-1);
	}
	/**
	 * 长按一个黑名单——编辑
	 */
	public static void test_017()
	{
		//主体
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.firewall:id/phone_name","0");
		excute(Object_Text,Operation_ClickWait,"编辑");
		check(Object_Text,Operation_checkExist,"添加联系人");
	}
	/**
	 * 长按一个黑名单——详情
	 */
	public static void test_018()
	{
		//主体
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.firewall:id/phone_name","0");
		excute(Object_Text,Operation_ClickWait,"详情");
		check(Object_ResourceId,Operation_TextContainsTrue,"android:id/message","姓名:");
		check(Object_ResourceId,Operation_TextContainsTrue,"android:id/message","号码:");
	}
	/**
	 * 有一个黑名单——点击菜单
	 */
	public static void test_019()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		String[] str={"添加","批量删除","添加拦截电话","添加拦截短信","添加拦截电话和短信"};
		DeviceCommon.checkForExist(str);
	}
	/**
	 * 有一个黑名单——点击菜单——添加
	 */
	public static void test_020()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"添加");
		check(Object_Text,Operation_checkExist,"添加联系人");
	}
	/**
	 * 有一个黑名单——点击菜单——批量删除
	 */
	public static void test_021()
	{
		//主体
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"批量删除");
		excute(Object_ResIdInstance,Operation_LongClick,"com.sprd.firewall:id/select","0");
		excute(Object_Text,Operation_ClickWait,"完成");
		excute(Object_Text,Operation_ClickWait,"确定");
		int num2=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		Assert.assertTrue(num2==num1-1);
	}
	/**
	 * “添加拦截电话”功能项
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_022() throws UiObjectNotFoundException
	{
		//主体
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"添加拦截电话");
		check(Object_Text,Operation_checkExist,"选择联系人");
		excute(Object_Text,Operation_ClickWait,"forCall");
		excute(Object_Text,Operation_ClickWait,"完成");
		int num2=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		CallFireWallCommon.checkInterceptionType("forCall", "01");
		Assert.assertTrue(num2==num1+1);
	}
	/**
	 * 添加拦截短信”功能项
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_023() throws UiObjectNotFoundException
	{
		//主体
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"添加拦截短信");
		check(Object_Text,Operation_checkExist,"选择联系人");
		excute(Object_Text,Operation_ClickWait,"forSms");
		excute(Object_Text,Operation_ClickWait,"完成");
		int num2=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		CallFireWallCommon.checkInterceptionType("forSms","10");
		Assert.assertTrue(num2==num1+1);
	}
	/**
	 * “添加拦截电话和短信”功能项
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_024() throws UiObjectNotFoundException
	{
		//主体
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"添加拦截电话和短信");
		check(Object_Text,Operation_checkExist,"选择联系人");
		excute(Object_Text,Operation_ClickWait,"forBoth");
		excute(Object_Text,Operation_ClickWait,"完成");
		int num2=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		CallFireWallCommon.checkInterceptionType("forBoth", "11");
		Assert.assertTrue(num2==num1+1);
	}
	/**
	 * 进入电话记录
	 */
	public static void test_104()
	{
		//前提
		CallFireWallCommon.BatchDelete("电话记录");
		//主体
		check(Object_Text, Operation_checkExist, "电话拦截记录为空。");
	}
	
	/**
	 * 进入电话记录-批量删除
	 */
	public static void test_105()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "电话记录");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_ClassName, Operation_EnabledFalse, "android.widget.LinearLayout");
	}
	
	/**
	 * 进入短信记录-批量删除
	 */
	public static void test_106()
	{
		//前提
		CallFireWallCommon.BatchDelete("短信记录");
		//主体
		check(Object_Text, Operation_checkExist, "短信拦截记录为空。");
	}
	
	/**
	 * 进入短信记录-批量删除
	 */
	public static void test_107()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "短信记录");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_ClassName, Operation_EnabledFalse, "android.widget.LinearLayout");
	}
	
	/**
	 * 进入电话记录-一条记录
	 */
	public static void test_025()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "电话记录");
		check(Object_ResourceId, Operation_checkExist, "com.sprd.firewall:id/ListView_customize");
	}
	
	/**
	 * 进入电话记录-长按一条记录
	 */
	public static void test_026()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "电话记录");
		excute(Object_ResIdInstance,Operation_LongClick , "com.sprd.firewall:id/ListView_customize","0");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "详情");
	}
	
	/**
	 * 进入电话记录-长按一条记录，删除该记录
	 */
	public static void test_027()
	{
		try{
			//主体
			excute(Object_Text, Operation_ClickWait, "电话记录");
			excute(Object_ResIdInstance,Operation_LongClick , "com.sprd.firewall:id/ListView_customize","0");
			excute(Object_Text, Operation_ClickWait, "删除");
			excute(Object_Text, Operation_ClickWait, "确定");
			check(Object_Text, Operation_checkExist, "电话拦截记录为空。");
		}
		finally{
			CallFireWallCommon.fillIncomingCallData();
		}
	}
	
	/**
	 * 进入电话记录-长按一条记录-详情
	 */
	public static void test_028()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "电话记录");
		excute(Object_ResIdInstance,Operation_LongClick , "com.sprd.firewall:id/ListView_customize","0");
		excute(Object_Text,Operation_ClickWait, "详情");
		check(Object_ResourceId, Operation_TextContainsTrue, "android:id/message","姓名");
		check(Object_ResourceId, Operation_TextContainsTrue, "android:id/message","号码");
		check(Object_ResourceId, Operation_TextContainsTrue, "android:id/message","时间");
	}
	
	/**
	 * 进入电话记录-批量删除
	 */
	public static void test_029()
	{
		try{
			//主体
			excute(Object_Text, Operation_ClickWait, "电话记录");
			excute(Object_Description, Operation_ClickWait, "更多选项");
			excute(Object_Text, Operation_ClickWait, "批量删除");
			excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.firewall:id/log_select","0");
			excute(Object_Text, Operation_ClickWait, "完成");
			excute(Object_Text, Operation_ClickWait, "确定");
			check(Object_Text, Operation_checkExist, "电话拦截记录为空。");
		}finally{
			CallFireWallCommon.fillIncomingCallData();
		}
	}
	
	/**
	 * 进入短信记录-一条记录
	 */
	public static void test_030()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "短信记录");
		check(Object_ResourceId, Operation_checkExist, "com.sprd.firewall:id/Sms_log_phone_name");
	}
	
	/**
	 * 进入短信记录-长按一条记录
	 */
	public static void test_031()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "短信记录");
		excute(Object_ResIdInstance,Operation_LongClick , "com.sprd.firewall:id/Sms_log_phone_name","0");
		check(Object_Text, Operation_checkExist, "删除");
		check(Object_Text, Operation_checkExist, "详情");
	}
	
	/**
	 * 进入短信记录-长按一条记录，删除该记录
	 */
	public static void test_032()
	{
		try{
			//主体
			excute(Object_Text, Operation_ClickWait, "短信记录");
			excute(Object_ResIdInstance,Operation_LongClick , "com.sprd.firewall:id/Sms_log_phone_name","0");
			excute(Object_Text, Operation_ClickWait, "删除");
			excute(Object_Text, Operation_ClickWait, "确定");
			check(Object_Text, Operation_checkExist, "短信拦截记录为空。");
		}
		finally{
			CallFireWallCommon.fillSMSData();
		}
	}
	
	/**
	 * 进入短信记录-长按一条记录-详情
	 */
	public static void test_033()
	{
		//主体
		excute(Object_Text, Operation_ClickWait, "短信记录");
		excute(Object_ResIdInstance,Operation_LongClick , "com.sprd.firewall:id/Sms_log_phone_name","0");
		excute(Object_Text,Operation_ClickWait, "详情");
		check(Object_ResourceId, Operation_TextContainsTrue, "android:id/message","姓名");
		check(Object_ResourceId, Operation_TextContainsTrue, "android:id/message","号码");
		check(Object_ResourceId, Operation_TextContainsTrue, "android:id/message","时间");
	}
	
	/**
	 * 进入短信记录-批量删除
	 */
	public static void test_034()
	{
		try{
			//主体
			excute(Object_Text, Operation_ClickWait, "短信记录");
			excute(Object_Description, Operation_ClickWait, "更多选项");
			excute(Object_Text, Operation_ClickWait, "批量删除");
			excute(Object_ResIdInstance, Operation_ClickWait, "com.sprd.firewall:id/Sms_log_select","0");
			excute(Object_Text, Operation_ClickWait, "完成");
			excute(Object_Text, Operation_ClickWait, "确定");
			check(Object_Text, Operation_checkExist, "短信拦截记录为空。");
		}finally{
			CallFireWallCommon.fillSMSData();
		}
	}
	/**
	 * 将一个电话号码添加到电话黑名单中,用此号码呼叫测试机，验证黑名单功能
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void test_035() throws IOException, InterruptedException
	{
		String BlackName = "callBlack";
		//前提
		CallFireWallCommon.BatchDelete("电话记录");
		CallFireWallCommon.BatchDelete("黑名单");
		CallFireWallCommon.addBlackContact(BlackName, CallFireWallCommon.BlackNum, "01");
		InteractionCommon callFireWall = new InteractionCommon(InteractionCommon.FireWall);
		//主体
		try{
			callFireWall.mtCall(DeviceCommon.getOneSIMNum(), InteractionCommon.FireWall);
			Wait(20000);
			excute(Object_Text, Operation_ClickWait, "电话记录");
			check(Object_Text, Operation_checkExist, BlackName);
		}finally{
			//清场
			callFireWall.interactionClose(InteractionCommon.FireWall);
			CallFireWallCommon.BatchDelete("黑名单");
			CallFireWallCommon.BatchDelete("电话记录");
		}
	}
	/**
	 * 号码未在黑名单,被叫成功
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_036() throws IOException, InterruptedException, UiObjectNotFoundException
	{
		//前提
		String option = InteractionCommon.mtCall_reject;
		InteractionCommon IMtCall = new InteractionCommon(InteractionCommon.mtCall_wakeUp);
		//主体
		try {
			DeviceCommon.enterApp(Devices_Desc_CallFireWall);
			excute(Object_Text, Operation_ClickWait, "黑名单");
			if(new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(CallFireWallCommon.BlackNum)){
				excute(Object_Text,Operation_LongClick,CallFireWallCommon.BlackNum);
				excute(Object_Text, Operation_ClickWait, "删除");
				excute(Object_Text, Operation_ClickWait, "确定");
			}
			IMtCall.mtCall(DeviceCommon.getOneSIMNum(), option);
		}finally{
			//清场
			IMtCall.interactionClose(InteractionCommon.normalMtCall);
		}
	}
	/**
	 * 将一个电话号码添加到短信黑名单中,用此号码发送短信给测试机，验证黑名单功能
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void test_037() throws IOException, InterruptedException
	{
		String BlackName = "SMSBlack";
		//前提
		CallFireWallCommon.BatchDelete("短信记录");
		CallFireWallCommon.BatchDelete("黑名单");
		CallFireWallCommon.addBlackContact(BlackName, CallFireWallCommon.BlackNum, "10");
		InteractionCommon SMSFireWall = new InteractionCommon(InteractionCommon.FireWall);
		//主体
		try{
			SMSFireWall.SMS(DeviceCommon.getOneSIMNum());
			Wait(20000);
			excute(Object_Text, Operation_ClickWait, "短信记录");
			check(Object_Text, Operation_checkExist, BlackName);
		}finally{
			//清场
			SMSFireWall.interactionClose(InteractionCommon.FireWall);
			CallFireWallCommon.BatchDelete("黑名单");
			CallFireWallCommon.BatchDelete("短信记录");
		}
	}
	/**
	 * 号码未在黑名单,请求发送短信成功
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_038() throws IOException, InterruptedException, UiObjectNotFoundException
	{
		//前提
		InteractionCommon SMSFireWall = new InteractionCommon(InteractionCommon.FireWall);
		DeviceCommon.enterApp(Devices_Desc_Message);
		MessageCommon.deleteAllMessageIn("收件箱");
		//主体
		try{
			DeviceCommon.enterApp(Devices_Desc_CallFireWall);
			excute(Object_Text, Operation_ClickWait, "黑名单");
			if(new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(CallFireWallCommon.BlackNum)){
				excute(Object_Text,Operation_LongClick,CallFireWallCommon.BlackNum);
				excute(Object_Text, Operation_ClickWait, "删除");
				excute(Object_Text, Operation_ClickWait, "确定");
			}
			SMSFireWall.SMS(DeviceCommon.getOneSIMNum());
			Wait(20000);
			DeviceCommon.enterApp(Devices_Desc_Message);
			MessageCommon.enterMessageBox("收件箱");
			Assert.assertTrue("The message needed isn't exist!!!",(Boolean)excute(Object_Text,Operation_Exists,"短信内容"));
		}finally{
			//清场
			SMSFireWall.interactionClose(InteractionCommon.FireWall);
			MessageCommon.deleteAllMessageIn("收件箱");
		}
	}
}
