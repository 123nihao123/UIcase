package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CallCommon;
import framework.common.CallLogCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.SettingCommon;

public class Contact extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
   }
	     
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
 	//添加一个联系人
	public static void test_001() throws UiObjectNotFoundException 
	{
		//主体
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		ContactCommon.checkNameTelMail("zhanxun", "1234", "zhanxun@spreadtrum.com");
	}
	
	//查看添加联系人中更多字段
	public static void test_002() throws UiObjectNotFoundException 
	{
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/floating_action_button", "添加新联系人");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text2","本机");
		excute(Object_Text,Operation_ClickWait,"更多字段");
		check(Object_TextScroll,Operation_checkExist,"聊天工具","vertical");
		check(Object_TextScroll,Operation_checkExist,"备注","vertical");
		check(Object_TextScroll,Operation_checkExist,"网站","vertical");
	}
	
	//查看添加联系人头像选项
	public static void test_003() throws UiObjectNotFoundException 
	{
		//主体
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/floating_action_button", "添加新联系人");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text2","本机");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/photo_touch_intercept_overlay");
		check(Object_Text,Operation_checkExist,"拍照");
		check(Object_Text,Operation_checkExist,"选择照片");
	}
	
	//添加收藏和取消收藏
	public static void test_006() throws UiObjectNotFoundException 
	{
		//主体
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_star","添加到收藏");
		check(Object_ResourceId,Operation_DescEqualTrue,"com.android.contacts:id/menu_star","从收藏中移除");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_star","从收藏中移除");
		check(Object_ResourceId,Operation_DescEqualTrue,"com.android.contacts:id/menu_star","添加到收藏");
	}
	
	//进入收藏界面检查联系人
	public static void test_007() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"收藏");
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.contacts:id/contact_tile_list_empty","没有收藏。");
	}
	
	//判断是否有导入导出字样
	public static void test_008() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"收藏");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"导入/导出");
		check(Object_ResourceId,Operation_TextEqualTrue,"android:id/alertTitle","导入/导出联系人");
	}
	
	//判断有“是否清楚常用联系人”字样
	public static void test_009() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","10086","zhanxun@spreadtrum.com");
		SettingCommon.endCall();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		//主体
		excute(Object_Text,Operation_ClickWait,"收藏");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除常用联系人");
		check(Object_Text,Operation_checkExist,"是否清除常用联系人？");
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		CallLogCommon.deleteAllLog("全部");
	}
	
	//清除常用联系人
	public static void test_010() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","10086","zhanxun@spreadtrum.com");
		SettingCommon.endCall();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		//主体
		excute(Object_Text,Operation_ClickWait,"收藏");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除常用联系人");
		check(Object_Text,Operation_checkExist,"是否清除常用联系人？");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,"常用联系人");
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		CallLogCommon.deleteAllLog("全部");
	}
	
	//添加联系人至收藏
	public static void test_011() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"收藏");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"添加联系人至收藏");
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Text,Operation_ClickWait,"完成");
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.contacts:id/contact_tile_name","zhanxun");
	}
	
	//显示所有联系人
	public static void test_012() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		check(Object_Text,Operation_checkExist,"zhanxun");
		check(Object_Text,Operation_checkExist,"SIM1");
		check(Object_Text,Operation_checkExist,"SIM2");
	}
	
	//显示本机联系人
	public static void test_013() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.selectDisplayContact("本机");
		ContactCommon.checkDisplayContacts("本机");
	}
	
	//显示SIM1联系人
	public static void test_014() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.selectDisplayContact("SIM1");
		ContactCommon.checkDisplayContacts("SIM1");
	}
	
	//显示SIM2联系人
	public static void test_015() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.selectDisplayContact("SIM2");
		ContactCommon.checkDisplayContacts("SIM2");
	}
	
	//有自定义联系人字样
	public static void test_016() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.selectDisplayContact("自定义");
		ContactCommon.checkDisplayContacts("自定义");
	}
	
	//设置自定义联系人显示
	public static void test_017() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.selectDisplayContact("SIM1","确定");
		check(Object_Text,Operation_checkExist,"SIM1");
	}
	
	//取消设置自定义联系人
	public static void test_018() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.selectDisplayContact("SIM1","取消");
		excute(Object_Device,Operation_PressBack);
		if((Boolean) excute(Object_Text,Operation_Exists,"Spreadtrum"))
		{
			check(Object_Text,Operation_checkExist,"4 位联系人");			
		}
		else
			check(Object_Text,Operation_checkExist,"3 位联系人");	
		check(Object_Text,Operation_checkExist,"zhanxun");
		check(Object_Text,Operation_checkExist,"SIM1");
		check(Object_Text,Operation_checkExist,"SIM2");
	}
	
	//显示有电话号码的联系人
	public static void test_019() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addName("SIM1","SIM1");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addName("SIM2","SIM2");
		excute(Object_Device,Operation_PressBack);
		//主体
		ContactCommon.selectDisplayContact("有电话号码的联系人");
		ContactCommon.checkDisplayContacts("有电话号码的联系人");
	}
	
	//复制联系人到SIM1
	public static void test_020() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.copyContactTo("zhanxun", "SIM1");
		ContactCommon.checkCopyContact("zhanxun",2);
	}
	
	//复制联系人到SIM2
	public static void test_021() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.copyContactTo("zhanxun", "SIM2");
		ContactCommon.checkCopyContact("zhanxun",2);
	}
	
	//复制联系人到本机
	public static void test_022() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.copyContactTo("SIM1", "本机");
		ContactCommon.checkCopyContact("SIM1",2);
	}
	
	//取消复制到本机
	public static void test_023() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.copyContactTo("SIM1", "本机","确定","取消");
		if((Boolean) excute(Object_Text,Operation_Exists,"Spreadtrum"))
		{
			check(Object_Text,Operation_checkExist,"4 位联系人");			
		}
		else
			check(Object_Text,Operation_checkExist,"3 位联系人");	
	}
	
	//联系人导出vcf文件
	public static void test_024() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		DeviceCommon.deleteFile("/sdcard/Download", "contacts.vcf");
		//主体
		ContactCommon.exportVcf("zhanxun");
		DeviceCommon.searchFile("/sdcard/Download", "contacts.vcf");
		//清场
		DeviceCommon.deleteFile("/sdcard/Download", "contacts.vcf");
	}
	
	//从vcf文件导入联系人
	public static void test_025() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		ContactCommon.exportVcf("zhanxun");
		ContactCommon.BatchDelete("所有联系人");
		//主体
		ContactCommon.importVcf("zhanxun","contacts.vcf");
		check(Object_Text,Operation_checkExist,"zhanxun");
		//清场
		DeviceCommon.deleteFile("/sdcard/Download", "contacts.vcf");
	}
	
	//跳转到分享联系人界面
	public static void test_026() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"导入/导出");
		excute(Object_Text,Operation_ClickWait,"分享所显示的联系人");
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Text,Operation_ClickWait,"完成");
		ContactCommon.checkShare();
	}
	
	//跳转到信息发送界面
	public static void test_027() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		ContactCommon.sendByMsg("zhanxun");
	}
	
	//本机添加联系人至群组
	public static void test_028() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addCommonThreeContacts("本机");
		//主体
		String[] testmember = {"test1","test2","test3"};
		ContactCommon.addGroup("本机","Test1",testmember);
		excute(Object_Device,Operation_PressBack);
		String[] member = {"test1","test2","test3"};
		ContactCommon.checkGroup("Test1",member,"本机");
		//清场
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		ContactCommon.deleteGroup("all");
	}
	
	//删除本机群组
	public static void test_029() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addCommonThreeContacts("本机");
		String[] testmember = {"test1","test2","test3"};
		ContactCommon.addGroup("本机","Test1",testmember);
		ContactCommon.backContactHome();
		//主体
		ContactCommon.deleteGroup("Test1");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"群组");
		check(Object_Text,Operation_checkNoExist,"Test1");
	}
	
	//SIM1添加群组
	public static void test_030() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addCommonThreeContacts("SIM1");
		//主体
		String[] testmember = {"test1","test2","test3"};
		ContactCommon.addGroup("SIM1","Test2",testmember);
		excute(Object_Device,Operation_PressBack);
		String[] member = {"test1","test2","test3"};
		ContactCommon.checkGroup("Test2",member,"SIM卡");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"SIM1");
		excute(Object_Device,Operation_PressBack);
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//删除SIM1群组
	public static void test_031() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addCommonThreeContacts("SIM1");
		String[] testmember = {"test1","test2","test3"};
		ContactCommon.addGroup("SIM1","Test2",testmember);
		ContactCommon.backContactHome();
		//主体
		ContactCommon.deleteGroup("all");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		check(Object_Text,Operation_checkNoExist,"Test2");
		excute(Object_Device,Operation_PressBack);
	}
	
	//SIM2添加群组
	public static void test_032() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addCommonThreeContacts("SIM2");
		//主体
		String[] testmember = {"test1","test2","test3"};
		ContactCommon.addGroup("SIM2","Test3",testmember);
		excute(Object_Device,Operation_PressBack);
		String[] member = {"test1","test2","test3"};
		ContactCommon.checkGroup("Test3",member,"SIM卡");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"SIM2");
		excute(Object_Device,Operation_PressBack);
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//删除SIM2群组
	public static void test_033() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addCommonThreeContacts("SIM2");
		String[] testmember = {"test1","test2","test3"};
		ContactCommon.addGroup("SIM2","Test3",testmember);
		ContactCommon.backContactHome();
		//主体
		ContactCommon.deleteGroup("all");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		check(Object_Text,Operation_checkNoExist,"Test3");
		excute(Object_Device,Operation_PressBack);
	}
	
	//修改群组名
	public static void test_034() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addGroup("SIM2","Test3");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		//主体
		ContactCommon.modifyGroup("Test3");
		excute(Object_ResourceId,Operation_SetText,"com.android.contacts:id/group_name","Test4");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/save_menu_item");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkNoExist,"Test3");
		check(Object_Text,Operation_checkExist,"Test4");
		excute(Object_Device,Operation_PressBack);
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//删除群组成员
	public static void test_035() throws UiObjectNotFoundException
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameAndTel("本机", "zhanxun", "123");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		String[] contactToGroup = {"zhanxun"};
		ContactCommon.addContactToGroup(contactToGroup);
		//主体
		ContactCommon.deleteGroupMember("Test3","zhanxun","完成");
		ContactCommon.checkGroupMember("Test3", "zhanxun", "不存在");
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//舍弃删除群组成员
	public static void test_036() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameAndTel("本机", "zhanxun", "123");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		String[] contactToGroup = {"zhanxun"};
		ContactCommon.addContactToGroup(contactToGroup);
		//主体
		ContactCommon.deleteGroupMember("Test3","zhanxun","舍弃更改");
		ContactCommon.checkGroupMember("Test3", "zhanxun", "存在");
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//添加群组成员,没有多余的联系人添加
	public static void test_037() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameAndTel("本机", "zhanxun", "123");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		ContactCommon.addALLContactToGroup();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_Text,Operation_ClickWait,"Test3");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "修改");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/select_group_member", "点击添加联系人");	
		check(Object_Text,Operation_checkExist,"没有联系人。");
		ContactCommon.backContactHome();
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//添加群组成员
	public static void test_037_1() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameAndTel("本机", "zhanxun", "123");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addNameAndTel("本机", "zhanxun1", "123");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		String[] contactToGroup = {"zhanxun"};
		ContactCommon.addContactToGroup(contactToGroup);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_Text,Operation_ClickWait,"Test3");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "修改");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/select_group_member", "点击添加联系人");
		check(Object_Text,Operation_checkExist,"zhanxun1");
		excute(Object_Text,Operation_ClickWait,"zhanxun1");
		excute(Object_Text,Operation_ClickWait,"完成");
		ContactCommon.backContactHome();
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//在群组里群发邮件
	public static void test_038() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		String[] contactToGroup = {"zhanxun"};
		ContactCommon.addContactToGroup(contactToGroup);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_Text,Operation_ClickWait,"Test3");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"群发邮件");
		excute(Object_Text,Operation_WaitForExists,"群发邮件","5000");
		check(Object_Text,Operation_checkExist,"帐户设置");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//在群组里群发短信
	public static void test_040() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		String[] contactToGroup = {"zhanxun"};
		ContactCommon.addContactToGroup(contactToGroup);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_Text,Operation_ClickWait,"Test3");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"群发信息");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/compose_message_text","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/compose_message_text");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//查看群组成员详情
	public static void test_041() throws UiObjectNotFoundException 
	{
		//前提
		ContactCommon.deleteGroup("all");
		ContactCommon.addName("本机","zhanxun");
		excute(Object_Device,Operation_PressBack);
		ContactCommon.addGroup("本机", "Test3");
		String[] contactToGroup = {"zhanxun"};
		ContactCommon.addContactToGroup(contactToGroup);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_Text,Operation_ClickWait,"Test3");
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		check(Object_ResIdText,Operation_checkExist,"com.android.contacts:id/header","群组");
		ContactCommon.backContactHome();
		//清场
		ContactCommon.deleteGroup("all");
	}
	
	//查看SIM卡容量
	public static void test_042() 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"高级选项");
		excute(Object_Text,Operation_ClickWait,"SIM卡容量");
		check(Object_Text,Operation_checkExist,"SIM卡容量");
		check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/simUsage");
	}
	
	//整理联系人
	public static void test_043() 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"高级选项");
		check(Object_Text,Operation_checkExist,"整理联系人");
		excute(Object_Text,Operation_ClickWait,"整理联系人");
		check(Object_Text,Operation_checkExist,"无重复联系人");
	}
	
	//编辑功能验证,保存
	public void test_044() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		check(Object_Text,Operation_checkExist,"zhanxun");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_edit");
		check(Object_Text,Operation_checkExist,"修改联系人");
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Text,Operation_SetText,"zhanxun","zhanxun01");
		excute(Object_Text,Operation_SetText,"1234","123");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_save");
		check(Object_Text,Operation_checkExist,"zhanxun01");
		check(Object_Text,Operation_checkExist,"123");
	}
	
	//编辑功能验证,取消保存
	public void test_045() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		check(Object_Text,Operation_checkExist,"zhanxun");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_edit");
		check(Object_Text,Operation_checkExist,"修改联系人");
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Text,Operation_SetText,"zhanxun","zhanxun01");
		excute(Object_Text,Operation_SetText,"1234","12345");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"舍弃更改");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"zhanxun");
		check(Object_Text,Operation_checkExist,"1234");
	}
	
	//点击大头贴
	public void test_046() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addPhoneWithPicWorkaround("zhanxun02","1234","拍照");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun02");
		check(Object_Text,Operation_checkExist,"zhanxun02");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_edit");
		check(Object_Text,Operation_checkExist,"修改联系人");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/photo_icon");
		check(Object_Text,Operation_checkExist,"拍摄新照片");
	}
	//取消删除单一联系人
	public void test_047() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		check(Object_Text,Operation_checkExist,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"zhanxun");
	}
	//确定删除单一联系人
	public void test_048() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		check(Object_Text,Operation_checkExist,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,"zhanxun");
	}
		  
	//批量删除联系人
	public void test_049() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts("本机");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"批量删除");
		excute(Object_Text,Operation_ClickWait,"test1");
		excute(Object_Text,Operation_ClickWait,"test2");
		check(Object_PeerTextClass,Operation_CheckedTrue,"test1","android.widget.CheckBox");
		check(Object_PeerTextClass,Operation_CheckedTrue,"test2","android.widget.CheckBox");
		//点击选择全部
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
		check(Object_Text,Operation_checkExist,"取消全部");
		//点击完成
		excute(Object_Text,Operation_ClickWait,"完成");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"取消全部");
		//点击取消全部
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
		check(Object_Text,Operation_checkExist,"选择(0)");
		//点击取消
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"通讯录");
	}
		 
	public void test_050() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts("本机");
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"批量删除");
		excute(Object_Text,Operation_ClickWait,"test1");
		excute(Object_Text,Operation_ClickWait,"test2");
		excute(Object_Text,Operation_ClickWait,"完成");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkNoExist,"test1");
		check(Object_Text,Operation_checkNoExist,"test2");
		}
	//搜索存在的联系人
	public void test_051() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts("本机");
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_search");
		excute(Object_Text,Operation_SetText,"查找联系人","test1");
		check(Object_ResIdText,Operation_checkExist,"com.android.contacts:id/cliv_name_textview","test1");
		check(Object_Text,Operation_checkExist,"匹配到 1个条目");
	}
	//搜索不存在的联系人
	public void test_052() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts("本机");
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/menu_search");
		excute(Object_Text,Operation_SetText,"查找联系人","spreadtrum01");
		check(Object_Text,Operation_checkExist,"没有联系人");
	}
	//通过联系人进入信息界面
	public void test_053() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun01","10086","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun01");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/icon_alternate");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/compose_message_text","10000");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/compose_message_text");
	}
	//通过联系人进入拨号界面
	public void test_054() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun01","10086","zhanxun@spreadtrum.com");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun01");
		//拨号
		excute(Object_Text,Operation_ClickWait,"10086");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/label","10000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/label");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		CallLogCommon.deleteAllLog("全部");
		
	}
	//联系人界面查看最近拨号
	public void test_055() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		CallLogCommon.deleteAllLog("全部");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.addNameTelMail("本机","zhanxun01","10086","zhanxun@spreadtrum.com");
		//主体
		//拨号
		excute(Object_Text,Operation_ClickWait,"10086");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/label","10000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/label");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
		Wait(1000);
		check(Object_Text,Operation_checkExist,"最近");
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		CallLogCommon.deleteAllLog("全部");
	} 
	//通过联系人进入邮箱
	public void test_056() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addNameTelMail("本机","zhanxun01","10086","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun01");
		excute(Object_Text,Operation_ClickWait,"zhanxun@spreadtrum.com");
		check(Object_Text,Operation_checkExist,"帐户设置");
	}

//本机联系人详情-复制到SIM1
	public void test_057() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"复制");
		check(Object_Text,Operation_checkExist,"复制到帐户");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);	
	}
	
	//本机联系人详情-复制到SIM1
	public void test_058() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"复制");
		excute(Object_Text,Operation_ClickWait,"SIM1");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		excute(Object_Text,Operation_ClickWait,"SIM1");
		check(Object_Text,Operation_checkExist,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		excute(Object_Text,Operation_ClickWait,"所有联系人");	
	}		
			
	//本机联系人详情-复制给SIM2
	public void test_059() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"复制");
		excute(Object_Text,Operation_ClickWait,"SIM2");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		excute(Object_Text,Operation_ClickWait,"SIM2");
		check(Object_Text,Operation_checkExist,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		excute(Object_Text,Operation_ClickWait,"所有联系人");		
		}
	
	//联系人详情-本机联系人-分享
	public void test_060() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"分享");
		check(Object_ResourceId,Operation_TextContainsTrue,"android:id/title","分享");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
	}	
	//联系人详情-本机联系人-分享-电子邮件
	public void test_061() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"分享");
		excute(Object_Text,Operation_ClickWait,"电子邮件");
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text,Operation_WaitForExists,"账户设置","3000");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
	}
	
	
	//联系人详情-本机联系人-分享-信息
	public void test_062() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"分享");
		excute(Object_Text,Operation_ClickWait,"信息");
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		excute(Object_Text,Operation_ClickWait,"新消息");
		check(Object_Text,Operation_WaitForExists,"收件人","2000");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
	}
	
	
	//联系人详情-本机联系人-分享-蓝牙
	public void test_063() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"分享");
		excute(Object_Text,Operation_ClickWait,"蓝牙");
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text,Operation_WaitForExists,"要现在开启蓝牙吗？","2000");
		excute(Object_Text,Operation_ClickWait,"取消");
		excute(Object_Device,Operation_PressBack);
	}
	
	//联系人详情-本机联系人-设置铃声
	public void test_064() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"设置铃声");
		check(Object_Text,Operation_checkExist,"选择要使用的应用：");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
	}
	
	//联系人详情-本机联系人-放在主屏
	public void test_065() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ContactCommon.addCommonThreeContacts();
		//主体
		excute(Object_Text,Operation_ClickWait,"zhanxun");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"放在主屏幕上");
		excute(Object_Device, Operation_PressHome);
		check(Object_TextScroll,Operation_checkExist,"zhanxun","horizontal");
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
	}
		
		//联系人详情-本机联系人-添加至黑名单
		public void test_066() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"zhanxun");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"添加至黑名单");
			DeviceCommon.enterApp("来电防火墙");
			excute(Object_Text,Operation_ClickWait,"黑名单");
			check(Object_Text,Operation_checkExist,"zhanxun");
			//请现场
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"批量删除");
			excute(Object_ResourceId,Operation_ClickWait,"com.sprd.firewall:id/selete_all");
			excute(Object_Text,Operation_ClickWait,"完成");
			excute(Object_Text,Operation_ClickWait,"确定");
			DeviceCommon.enterApp("通讯录");
		}
		
		//联系人详情-从本机联系人-黑名单中删除
		public void test_067() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			excute(Object_Text,Operation_ClickWait,"zhanxun");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"添加至黑名单");
			DeviceCommon.enterApp("来电防火墙");
			excute(Object_Text,Operation_ClickWait,"黑名单");
			check(Object_Text,Operation_checkExist,"zhanxun");
			//主体
			DeviceCommon.enterApp("通讯录");
			excute(Object_Text,Operation_ClickWait,"zhanxun");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"从黑名单中删除");
			DeviceCommon.enterApp("来电防火墙");
			check(Object_Text,Operation_checkNoExist,"zhanxun");
			//清现场
			DeviceCommon.enterApp("通讯录");
		}
		
		//SIM1联系人详情-复制到本机
		public void test_068() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"复制");
			excute(Object_Text,Operation_ClickWait,"本机");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"要显示的联系人");
			excute(Object_Text,Operation_ClickWait,"本机");
			check(Object_Text,Operation_checkExist,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"要显示的联系人");
			excute(Object_Text,Operation_ClickWait,"所有联系人");	
		}
		
		
		
		//SIM1联系人详情-复制给SIM2
		public void test_069() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"复制");
			excute(Object_Text,Operation_ClickWait,"SIM2");
			excute(Object_Text,Operation_ClickWait,"确定");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"要显示的联系人");
			excute(Object_Text,Operation_ClickWait,"SIM2");
			check(Object_Text,Operation_checkExist,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"要显示的联系人");
			excute(Object_Text,Operation_ClickWait,"所有联系人");
		}
		
		//SIM1联系人详情-分享
		public void test_070() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"分享");
			check(Object_ResourceId,Operation_TextContainsTrue,"android:id/title","分享");
			//check(Object_Text,Operation_checkExist,"联系人分享方式");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressBack);
		}
		
		//SIM联系人详情-分享-电子邮件
		public void test_071() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"分享");
			excute(Object_Text,Operation_ClickWait,"电子邮件");
			if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
			{
				excute(Object_Text,Operation_ClickWait,"仅此一次");
			}
			check(Object_Text,Operation_WaitForExists,"账户设置","2000");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressBack);
		}
		
		
		//本机联系人-分享-信息
		public void test_072() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"zhanxun");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"分享");
			excute(Object_Text,Operation_ClickWait,"信息");
			if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
			{
				excute(Object_Text,Operation_ClickWait,"仅此一次");
			}
			excute(Object_Text,Operation_ClickWait,"新消息");
			check(Object_Text,Operation_WaitForExists,"收件人","3000");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressBack);
		}
		
		//SIM1联系人-分享-蓝牙
		public void test_073() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"分享");
			excute(Object_Text,Operation_ClickWait,"蓝牙");
			if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
			{
				excute(Object_Text,Operation_ClickWait,"仅此一次");
			}
			check(Object_Text,Operation_WaitForExists,"要现在开启蓝牙吗？","3000");
			excute(Object_Text,Operation_ClickWait,"取消");
			excute(Object_Device,Operation_PressBack);
		}
		
		//联系人详情-SIM1-添加至黑名单
		public void test_074() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"添加至黑名单");
			DeviceCommon.enterApp("来电防火墙");
			excute(Object_Text,Operation_ClickWait,"黑名单");
			check(Object_Text,Operation_checkExist,"SIM1");
			//清现场
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"批量删除");
			excute(Object_ResourceId,Operation_ClickWait,"com.sprd.firewall:id/selete_all");
			excute(Object_Text,Operation_ClickWait,"完成");
			excute(Object_Text,Operation_ClickWait,"确定");
			DeviceCommon.enterApp("通讯录");
		}
		
		//联系人详情-SIM1-从黑名单中删除
		public void test_075() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"添加至黑名单");
			DeviceCommon.enterApp("来电防火墙");
			excute(Object_Text,Operation_ClickWait,"黑名单");
			check(Object_Text,Operation_checkExist,"SIM1");
			//主体
			DeviceCommon.enterApp("通讯录");
			excute(Object_Text,Operation_ClickWait,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"从黑名单中删除");
			DeviceCommon.enterApp("来电防火墙");
			check(Object_Text,Operation_checkNoExist,"SIM1");
			//清现场
			DeviceCommon.enterApp("通讯录");
		}
		
		//我的资料-进入我的资料
		public void test_076() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.deleteMyData();
			//主体
			excute(Object_Text,Operation_ClickWait,"设置我的个人资料");
			check(Object_Text,Operation_checkExist,"我的本机个人资料");
			excute(Object_Device,Operation_PressBack);
			
			
		}
		
		//我的资料-编辑我的资料
		public void test_077() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.deleteMyData();	
			//主体
			excute(Object_Text,Operation_ClickWait,"设置我的个人资料");
			excute(Object_Text,Operation_SetText,"姓名","test01");
			excute(Object_Text,Operation_SetText,"电话","123456");
			excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
			excute(Object_Device,Operation_PressBack);
			check(Object_Text,Operation_checkExist,"test01");
			//清现场
			excute(Object_Text,Operation_ClickWait,"我");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
		}
		
		//长按本机联系人
		public void test_078() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_LongClick,"zhanxun");
			check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/selection_count_text","1");
			check(Object_PeerTextClass,Operation_CheckedTrue,"zhanxun","android.widget.CheckBox");
			excute(Object_Device,Operation_PressBack);
			
		}
		//列表-长按SIM1联系人-验证SIM1是选中的
		public void test_079() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_LongClick,"SIM1");
			check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/selection_count_text","1");
			check(Object_PeerTextClass,Operation_CheckedTrue,"SIM1","android.widget.CheckBox");
			excute(Object_Device,Operation_PressBack);
		}
		
		//列表-长按SIM联系人，选中两个联系人
		public void test_080() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_LongClick,"SIM1");
			excute(Object_Text,Operation_ClickWait,"zhanxun");
			check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/selection_count_text","2"); 
			check(Object_PeerTextClass,Operation_CheckedTrue,"SIM1","android.widget.CheckBox");
			check(Object_PeerTextClass,Operation_CheckedTrue,"zhanxun","android.widget.CheckBox");
			excute(Object_Device,Operation_PressBack);
		}
		
		//列表-长按SIM1联系人-合并
		//无法验证toast文本
		public void test_081() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_LongClick,"SIM1");
			excute(Object_Text,Operation_ClickWait,"zhanxun");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"合并");
			System.out.println("check toast!");
			if ((Boolean)excute(Object_Text,Operation_WaitForExists,"USIM卡联系人不支持合并","2000"))
			{
				System.out.println("True");
			}
		}
		
		//长按SIM1联系人-删除SIM1联系人
		public void test_082() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_LongClick,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"确定");
			check(Object_Text, Operation_checkNoExist,"SIM1");
		}
		
		//长按SIM1联系人-分享方式
		public void test_083() throws UiObjectNotFoundException, RemoteException 
		{
			//前提
			ContactCommon.addCommonThreeContacts();
			//主体
			excute(Object_Text,Operation_LongClick,"SIM1");
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"分享");
			check(Object_ResourceId,Operation_TextContainsTrue,"android:id/title","分享");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressBack);
		}
		
}
