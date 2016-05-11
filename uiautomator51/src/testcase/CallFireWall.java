package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;
import framework.common.CallFireWallCommon;
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
	 */
	public static void test_000()
	{
		//主体
		CallFireWallCommon.fillIncomingCallData();
		CallFireWallCommon.fillSMSData();
		CallFireWallCommon.fillThreeBlockContact();
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
	 * 进入短信记录-长按一条记录
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
}
