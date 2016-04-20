package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import static framework.data.ResIdTextAndDesc.Devices_Desc_Call;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CallLogCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.PhoneCommon;

public class Phone extends UiAutomatorTestCase
{

	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Call);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * Workaround of  not working @BeforeClass @AfterClass
	 * Compile is ok but @BeforeClass @AfterClass can not be recognized because of extending UiAutomatorTestCase
	 */
	public void test_0000(){  
        System.out.println("before class!!!!!!!!!!!!!!!!!!!!!!!!");
        PhoneCommon.fillPhoneData();
    }
	public void test_9999(){  
        System.out.println("after class...");
        CallLogCommon.deleteAllFromCallLog();
    }
	
	/**
	 * 进入拨号盘
	 */
	public void test_001(){  
		//主体
        check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/floating_action_button");
    }
	
	/**
	 * 输入数字
	 */
	public void test_002(){  
		//主体
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/four");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/six");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/seven");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/eight");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/nine");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/zero");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/star");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/pound");
        check(Object_Text,Operation_checkExist,"1234567890*#");
    }
	
	/**
	 * 输入数字123
	 */
	public void test_003(){  
		//主体
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
        check(Object_Text,Operation_checkExist,"123");
    }
	
	/**
	 * 进入拨号盘-输入数字--删除
	 */
	public void test_004(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/deleteButton");
	    check(Object_ResIdText,Operation_checkExist,"com.android.dialer:id/digits","12");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/deleteButton");
	    check(Object_ResIdText,Operation_checkExist,"com.android.dialer:id/digits","1");
    }
	
	/**
	 * 进入拨号盘-输入数字--长按删除
	 */
	public void test_005(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
	    excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
	    excute(Object_ResourceId,Operation_LongClick,"com.android.dialer:id/deleteButton");
	    check(Object_ResIdText,Operation_checkNoExist,"com.android.dialer:id/digits","123");
    }
	
	/**
	 * 进入拨号盘-菜单-延长暂停时间2秒
	 */
	public void test_006(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/dialpad_overflow");
		excute(Object_Text,Operation_ClickWait,"延长暂停时间2秒");
		check(Object_ResIdText,Operation_checkExist,"com.android.dialer:id/digits","123,");	
	}
	
	/**
	 * 进入拨号盘-菜单-延长等待时间
	 */
	public void test_007(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/dialpad_overflow");
		excute(Object_Text,Operation_ClickWait,"延长等待时间");
		check(Object_ResIdText,Operation_checkExist,"com.android.dialer:id/digits","123;");	
	}
	
	/**
	 * 进入拨号盘-菜单-IP拨号
	 */
	public void test_008(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/one");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/two");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/dialpad_overflow");
		excute(Object_Text,Operation_ClickWait,"IP 拨号");
		check(Object_Text,Operation_checkExist,"IP 拨号列表");	
	}
	
	/**
	 * 进入拨号盘-长按0，出现“+”号
	 */
	public void test_009(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_LongClick,"com.android.dialer:id/zero");
		check(Object_ResIdText,Operation_checkExist,"com.android.dialer:id/digits","+");	
	}
	
	/**
	 * 进入拨号盘-输入数字出现新建联系人和添加到联系人
	 */
	public void test_010(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
		check(Object_Text,Operation_checkExist,"新建联系人");	
		check(Object_Text,Operation_checkExist,"添加到联系人");
	}
	
	/**
	 * 进入拨号盘-输入数字出现新建联系人，添加到联系人，发送信息
	 */
	public void test_011(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"新建联系人");	
		check(Object_Text,Operation_checkExist,"添加到联系人");
		check(Object_Text,Operation_checkExist,"发送信息");
	}
	
	/**
	 * 进入拨号盘-输入数字新建联系人
	 */
	public void test_012(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Text,Operation_ClickWait,"新建联系人");
		excute(Object_Text,Operation_ClickWait,"本机");
		check(Object_Text,Operation_checkExist,"新增联系人");	
		//清场
		excute(Object_Device,Operation_PressBack);
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 进入拨号盘-输入数字添加到联系人
	 */
	public void test_013(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Text,Operation_ClickWait,"添加到联系人");
		if((Boolean)excute(Object_Text,Operation_Exists,"没有联系人"))
		{
			check(Object_Text,Operation_Exists,"没有联系人");
		}
		else
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview","0");
			if((Boolean)excute(Object_Text,Operation_Exists,"123"))
			{
				check(Object_Text,Operation_checkExist,"123");
			}
			else
			{
				check(Object_TextScroll,Operation_checkExist,"123","vertical");
			}
		}
		//清场
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"舍弃更改");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	
	/**
	 * 进入拨号盘-输入数字发送信息
	 */
	public void test_014(){  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Text,Operation_ClickWait,"发送信息");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/attach_media_button","6000");
		check(Object_ResIdText,Operation_checkExist,"com.android.messaging:id/conversation_title","35");	
	}
	
	/**
	 * 进入拨号盘-匹配联系人
	 * @throws UiObjectNotFoundException 
	 */
	public void test_015() throws UiObjectNotFoundException{  
		//前提
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.addNameAndTel("本机", "zhanxun35","1357");
		//主体
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/three");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/five");
		check(Object_ResourceId,Operation_TextContainsTrue,"com.android.dialer:id/cliv_data_view","35");	
		//清场
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
	}
	
	/**
	 * 进入拨号盘-收起拨号盘
	 */
	public void test_016() {  
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_action_button");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"输入姓名或电话号码");	
	}
	
	/**
	 * 进入拨号盘-收藏
	 */
	public void test_017() {  
		//主体
		excute(Object_Description,Operation_ClickWait,"快速拨号");
		if((Boolean)excute(Object_ResIdText,Operation_Exists,"com.android.dialer:id/emptyListViewAction","添加常用联系人"))
		{
			check(Object_ResIdText,Operation_checkExist,"com.android.dialer:id/emptyListViewAction","添加常用联系人");
		}
		else
		{
			check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/contact_star_icon");	
		}
		
	}
	
	/**
	 * 进入拨号盘-最近通话
	 */
	public void test_018() {  
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		if(!(Boolean)excute(Object_Text,Operation_Exists,"查看全部通话记录"))
		{
			excute(Object_TextScroll,Operation_Exists,"查看全部通话记录","vertical");
		}
		check(Object_Text,Operation_checkExist,"查看全部通话记录");	
	}
	
	/**
	 * 进入拨号盘-联系人
	 */
	public void test_019() {  
		//主体
		excute(Object_Description,Operation_ClickWait,"联系人");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/floating_action_button");	
	}
	
	/**
	 * 进入拨号盘-收藏一个联系人拨出
	 * @throws UiObjectNotFoundException 
	 */
	public void test_020() throws UiObjectNotFoundException {  
		//前提
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.addNameAndTel("本机", "zhanxun","10086");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_star");
		//主体
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"快速拨号");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.dialer:id/contact_tile_name","zhanxun");
		excute(Object_Text,Operation_WaitForExists,"用于外拨电话的帐户", "10000");
		if((Boolean)excute(Object_Text,Operation_Exists,"用于外拨电话的帐户"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/label","0");
		}
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/floating_end_call_action_button","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/floating_end_call_action_button");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_end_call_action_button");
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-展开的部分上有text“ 新建联系人”、“添加到联系人”“发送信息”“通话详情”“添加至黑名单”
	 */
	public void test_021(){  
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		check(Object_Text,Operation_checkExist,"新建联系人");	
		check(Object_Text,Operation_checkExist,"添加到联系人");
		check(Object_Text,Operation_checkExist,"发送信息");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"添加至黑名单");
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-新建联系人
	 */
	public void test_022(){  
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_Text,Operation_ClickWait,"新建联系人");
		check(Object_Text,Operation_checkExist,"通讯录");	
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-添加至联系人
	 */
	public void test_023(){  
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_Text,Operation_ClickWait,"添加到联系人");
		check(Object_Text,Operation_checkExist,"选择联系人");	
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-发送信息
	 */
	public void test_024(){  
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_Text,Operation_ClickWait,"发送信息");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/compose_message_text","6000");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/compose_message_text");	
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-通话详情
	 */
	public void test_025() {
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/menu_remove_from_call_log","6000");
		check(Object_Text,Operation_checkExist,"通话清单");	
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-添加到黑名单
	 */
	public void test_026() {
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_Text,Operation_ClickWait,"添加至黑名单");
		check(Object_Text,Operation_checkExist,"从黑名单中删除");	
		//清场
		excute(Object_Text,Operation_ClickWait,"从黑名单中删除");
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话-收起
	 */
	public void test_027() {
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		check(Object_Text,Operation_checkNoExist,"新建联系人");	
	}
	
	/**
	 * 进入拨号盘-点击一条最近通话详情，拨号
	 */
	public void test_029() {
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/name","0");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_back_button");
		excute(Object_Text,Operation_WaitForExists,"用于外拨电话的帐户", "10000");
		if((Boolean)excute(Object_Text,Operation_Exists,"用于外拨电话的帐户"))
		{
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/label","0");
		}
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/floating_end_call_action_button","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/floating_end_call_action_button");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/floating_end_call_action_button");
	}
	
	/**
	 * 进入拨号盘-长按一条最近通话详情，对话框上有text "复制到剪贴板" "呼叫之前编辑号码
	 */
	public void test_030() {
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		excute(Object_ResIdInstance,Operation_LongClick,"com.android.dialer:id/name","0");
		check(Object_Text,Operation_checkExist,"复制到剪贴板");
		check(Object_Text,Operation_checkExist,"呼叫之前编辑号码");
	}
	
	/**
	 * 进入拨号盘-查看全部通话记录
	 */
	public void test_032() {
		//主体
		excute(Object_Description,Operation_ClickWait,"最近");
		if(!(Boolean)excute(Object_Text,Operation_Exists,"查看全部通话记录"))
		{
			excute(Object_TextScroll,Operation_Exists,"查看全部通话记录","vertical");
		}
		excute(Object_Text,Operation_ClickWait,"查看全部通话记录");
		check(Object_Text,Operation_checkExist,"通话记录");	
	}
	/**
	 * 进入设置界面
	 */
	public static void test_073() 
	{
		//主体
		PhoneCommon.enterSettings();
		check(Object_Text, Operation_checkExist,"设置");
	}
	/**
	 * 显示选项
	 */
	public static void test_074() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "显示选项");
		check(Object_Text, Operation_checkExist,"排序方式");
	}
	/**
	 * 排序方式
	 */
	public static void test_075() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "显示选项");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "名字");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","名字");
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "姓氏");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","姓氏");
		//清场
		excute(Object_Text, Operation_ClickWait, "排序方式");
		excute(Object_Text, Operation_ClickWait, "名字");
	}
	/**
	 * 姓名格式
	 */
	public static void test_076() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "显示选项");
		excute(Object_Text, Operation_ClickWait, "姓名格式");
		excute(Object_Text, Operation_ClickWait, "名字在前");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","名字在前");
		excute(Object_Text, Operation_ClickWait, "姓名格式");
		excute(Object_Text, Operation_ClickWait, "姓氏在前");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","姓氏在前");
		//清场
		excute(Object_Text, Operation_ClickWait, "姓名格式");
		excute(Object_Text, Operation_ClickWait, "名字在前");
	}
	/**
	 * IP拨号列表
	 */
	public static void test_077() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "IP 拨号设置");
		check(Object_Text,Operation_checkExist,"IP 拨号列表");
	}
	/**
	 * IP拨号列表新建
	 */
	public static void test_078() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "IP 拨号设置");
		excute(Object_Text, Operation_ClickWait, "新建");
		excute(Object_ResourceId,Operation_SetText,"com.android.phone:id/ip_editor","17951");
		excute(Object_Text, Operation_ClickWait, "完成");
		check(Object_ResIdText,Operation_checkExist,"com.android.phone:id/text","17951");
		//清场
		excute(Object_Text, Operation_ClickWait, "移除");
		excute(Object_Text, Operation_ClickWait, "17951");
		excute(Object_Text, Operation_ClickWait, "完成");
	}
	/**
	 * IP拨号列表移除
	 */
	public static void test_079() 
	{
		//前提
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "IP 拨号设置");
		excute(Object_Text, Operation_ClickWait, "新建");
		excute(Object_ResourceId,Operation_SetText,"com.android.phone:id/ip_editor","17951");
		excute(Object_Text, Operation_ClickWait, "完成");
		//主体
		excute(Object_Text, Operation_ClickWait, "移除");
		excute(Object_Text, Operation_ClickWait, "17951");
		excute(Object_Text, Operation_ClickWait, "完成");
		check(Object_Text,Operation_checkNoExist,"17951");
	}
	/**
	 * 通话账户
	 */
	public static void test_081() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "通话帐户");
		check(Object_Text,Operation_checkExist,"通话帐户");
	}
	/**
	 * 选择通话账户
	 */
	public static void test_082() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "通话帐户");
		excute(Object_Text, Operation_ClickWait, "选择通话帐户");
		check(Object_ResourceId,Operation_checkExist,"android:id/select_dialog_listview");
		excute(Object_Text, Operation_ClickWait, "每次都询问");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","每次都询问");
		excute(Object_Text, Operation_ClickWait, "选择通话帐户");
		excute(Object_Text, Operation_ClickWait, "SIM1 SIM1");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","SIM1");
		excute(Object_Text, Operation_ClickWait, "选择通话帐户");
		excute(Object_Text, Operation_ClickWait, "SIM2 SIM2");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","SIM2");
		//清场
		excute(Object_Text, Operation_ClickWait, "选择通话帐户");
		excute(Object_Text, Operation_ClickWait, "每次都询问");
	}
	/**
	 * SIM1设置界面
	 */
	public static void test_083() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		check(Object_Text,Operation_checkExist,"通话设置 (SIM1)");
	}
	/**
	 * 语音信箱
	 */
	public static void test_084() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "语音信箱");
		check(Object_Text,Operation_checkExist,"语音信箱（SIM1）");
	}
	/**
	 * 固定拨号
	 */
	public static void test_085() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "固定拨号");
		check(Object_Text,Operation_checkExist,"固定拨号 (SIM1)");
	}
	/**
	 * 来电转接
	 */
	public static void test_086() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "来电转接");
		if ((Boolean)excute(Object_Text,Operation_Exists,"当前数据连接处于关闭状态，是否设置数据连接？"))
		{
			excute(Object_Text, Operation_ClickWait, "是");
			excute(Object_Text,Operation_WaitForExists,"移动数据网络","3000");
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Text, Operation_ClickWait, "来电转接");
		}
		check(Object_Text,Operation_checkExist,"语音来电转接");
	}
	/**
	 * 呼叫限制
	 */
	public static void test_087() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "呼叫限制");
		if ((Boolean)excute(Object_Text,Operation_Exists,"当前数据连接处于关闭状态，是否设置数据连接？"))
		{
			excute(Object_Text, Operation_ClickWait, "是");
			excute(Object_Text,Operation_WaitForExists,"移动数据网络","3000");
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Text, Operation_ClickWait, "呼叫限制");
		}
		check(Object_Text,Operation_checkExist,"呼叫限制设置");
	}
	/**
	 * 其他设置
	 */
	public static void test_088() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "其他设置");
		if ((Boolean)excute(Object_Text,Operation_Exists,"当前数据连接处于关闭状态，是否设置数据连接？"))
		{
			excute(Object_Text, Operation_ClickWait, "是");
			excute(Object_Text,Operation_WaitForExists,"移动数据网络","3000");
			excute(Object_ResIdInstance,Operation_ClickWait,"com.android.settings:id/universal_switch","2");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Text, Operation_ClickWait, "其他设置");
		}
		check(Object_Text,Operation_checkExist,"其他设置 (SIM1)");
	}
	/**
	 * SDN列表
	 */
	public static void test_089() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "SDN 列表");
		check(Object_Text,Operation_checkNoExist,"通话设置 (SIM1)");
		check(Object_Text,Operation_checkExist,"SDN 列表");
	}
	/**
	 * LND列表
	 */
	public static void test_091() 
	{
		//主体
		PhoneCommon.enterSIM1Settings();
		excute(Object_Text, Operation_ClickWait, "LND 列表");
		check(Object_Text,Operation_checkNoExist,"通话设置 (SIM1)");
		check(Object_Text,Operation_checkExist,"LND 列表");
	}
	/**
	 * 快速回复
	 */
	public static void test_093() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "快速回复");
		check(Object_Text,Operation_checkExist,"修改快速回复");
	}
	/**
	 * 快速拨号设置
	 */
	public static void test_094() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "快速拨号设置");
		check(Object_Text,Operation_checkExist,"快速拨号设置");
	}
	/**
	 * 通话录音
	 */
	public static void test_095() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "通话录音");
		check(Object_Text,Operation_checkExist,"自动录音");
		check(Object_ResourceId,Operation_CheckedFalse,"android:id/checkbox");
	}
	/**
	 * 通话连接提示
	 */
	public static void test_096() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "通话连接提示");
		check(Object_Text,Operation_checkExist,"通话连接后发出振动反馈");
		check(Object_ResourceId,Operation_CheckedFalse,"android:id/checkbox");
	}
	/**
	 * 通话连接提示
	 */
	public static void test_097() 
	{
		//主体
		PhoneCommon.enterSettings();
		excute(Object_Text, Operation_ClickWait, "来电翻转静音");
		check(Object_ResIdText,Operation_checkExist,"android:id/title","来电翻转静音");
		check(Object_ResourceId,Operation_CheckedFalse,"android:id/checkbox");
	}
}