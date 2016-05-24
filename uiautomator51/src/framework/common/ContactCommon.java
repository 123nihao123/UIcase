package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

import framework.driver.GetObjectStatusByText;

public class ContactCommon {
	
	/**
	 * 批量删除联系人
	 * @param DeleTarget：所有联系人，SIM1，SIM2，本机，自定义，有电话号码的联系人。
	 * @throws UiObjectNotFoundException
	 */
	public static void BatchDelete(String DeleTarget) throws UiObjectNotFoundException{
		selectDisplayContact(DeleTarget);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"批量删除");
		
		if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.contacts:id/emptyText"))
		{
			System.out.println("Contacts were already empty.");
		}
		else
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button","完成");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
			Wait(2000);
		}
		backContactHome();
	}
	
	/**
	 * 返回联系人主界面
	 * @throws UiObjectNotFoundException
	 */
	public static void backContactHome() throws UiObjectNotFoundException {
		UiObject ContactTitle = (UiObject) excute(Object_ClassText,Operate_ReturnObject,"android.widget.TextView","通讯录");
		while(!ContactTitle.exists())
		{
			excute(Object_Device, Operation_PressBack);
		}
	}
	
	/**
	 * 添加一个只有名字的联系人
	 * @param position：本机，SIM1，SIM2
	 * @param name：姓名
	 * @throws UiObjectNotFoundException
	 */
	public static void addName(String position, String name) throws UiObjectNotFoundException{
		selectContactPosition(position);
		excute(Object_Text,Operation_SetText,"姓名",name);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
	}
	
	/**
	 * 添加有姓名，电话的联系人
	 * @param position：本机，SIM1，SIM2
	 * @param name：联系人姓名
	 * @param number：联系人电话号码
	 * @throws UiObjectNotFoundException
	 */
	public static void addNameAndTel(String position, String name,String number) throws UiObjectNotFoundException{
		selectContactPosition(position);
		excute(Object_Text,Operation_SetText,"姓名",name);
		excute(Object_Device,Operation_PressEnter);
		excute(Object_Text,Operation_SetText,"电话",number);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
	}
	
	/**
	 * 添加一个有姓名，电话，电子邮件的联系人
	 * @param position：本机，SIM1，SIM2
	 * @param name：姓名
	 * @param number：电话号码
	 * @param mail：电子邮件
	 * @throws UiObjectNotFoundException
	 */
	public static void addNameTelMail(String position, String name, String number, String mail) throws UiObjectNotFoundException 
	{
		selectContactPosition(position);
		excute(Object_Text,Operation_SetText,"姓名",name);
		excute(Object_Text,Operation_SetText,"电话",number);
		excute(Object_Text,Operation_SetText,"电子邮件",mail);
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
	}
	
	/**
	 * 添加带头像的联系人
	 * @param name：名字
	 * @param number：电话号码
	 * @param option：拍照，选择图片
	 * @throws UiObjectNotFoundException
	 */
	public static void addPhoneWithPicWorkaround(String name,String number,String option) throws UiObjectNotFoundException
	{
		selectContactPosition("本机");
		//在页面不需要滑动时使用Object_TextScroll会出错
		excute(Object_Text,Operation_SetText,"姓名",name);
		excute(Object_Text,Operation_SetText,"电话",number);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/photo_icon");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", option);
		if(option.equals("拍照"))
		{
			excute(Object_ResourceId,Operation_WaitForExists,"com.android.camera2:id/shutter_button","10000");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
			excute(Object_ResourceId,Operation_WaitForExists,"com.android.camera2:id/done_button","10000");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/done_button");
			
		}
		else if(option.equals("选择照片"))
		{
			Wait(2000);
			//选择图片
			UiObject photo = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.documentsui:id/icon_mime");
			UiObject gallery = (UiObject) excute(Object_ResIdText,Operate_ReturnObject,"android:id/title", "图库");
			if((Boolean) excute(Object_Description,Operation_Exists,"显示根目录"))
			{
				excute(Object_Description,Operation_ClickWait,"显示根目录");
			}
			if((Boolean) excute(Object_Text,Operation_Exists,"图片"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "图片");
				while((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.documentsui:id/icon_mime"))
				{
				excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/icon_mime");
					Wait(2000);
				}
				while((Boolean) excute(Object_ClassInstance,Operation_Exists,"android.widget.LinearLayout","0"))
				{
					excute(Object_ClassInstance,Operation_ClickWait,"android.widget.LinearLayout","0");
					Wait(2000);
				}
			}
			//图库操作
			else if(gallery.exists())
			{
				gallery.clickAndWaitForNewWindow();
				UiDevice uiDevice = UiDevice.getInstance();
				uiDevice.click(uiDevice.getDisplayWidth() / 2,uiDevice.getDisplayHeight()/2);
				Wait(2000);
				uiDevice.click(uiDevice.getDisplayWidth()/2,uiDevice.getDisplayHeight()/2);
			}
		}
		excute(Object_ResIdText,Operation_ClickWait,"com.android.gallery3d:id/filtershow_done", "保存");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
	}
	
	/**
	 * 分别在本机，SIM1，SIM2上添加一个测试的联系人，
	 * @throws UiObjectNotFoundException
	 */
	public static void addCommonThreeContacts() throws UiObjectNotFoundException
	{
		addNameTelMail("本机","zhanxun","1234","zhanxun@spreadtrum.com");
		excute(Object_Device,Operation_PressBack);
		addNameAndTel("SIM1", "SIM1", "123");
		excute(Object_Device,Operation_PressBack);
		addNameAndTel("SIM2", "SIM2", "234");
		excute(Object_Device,Operation_PressBack);
	}
	
	/**
	 * 添加三个测试的联系人
	 * @param position：本机，SIM1，SIM2
	 * @throws UiObjectNotFoundException
	 */
	public static void addCommonThreeContacts(String position) throws UiObjectNotFoundException 
	{
		String name[] = {"test1","test2","test3"};
		String number[] = {"1234","12345","123456"};
		String mail[] = {"zhanxun01@spreadtrum.com","zhanxun02@spreadtrum.com","zhanxun03@spreadtrum.com"};
		for(int i=0;i<name.length;i++)
		{
			addNameTelMail(position,name[i],number[i],mail[i]);
			excute(Object_Device, Operation_PressBack);
		}
	}

	/**
	 * 点击添加联系人按钮
	 * @param position：本机，SIM1，SIM2
	 * @throws UiObjectNotFoundException
	 */
	public static void selectContactPosition(String position) throws UiObjectNotFoundException{
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/floating_action_button", "添加新联系人");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text2",position);
	} 
	
	/**
	 * 检查有姓名跟电话号码的联系人
	 * @param name：联系人姓名
	 * @param number：联系人电话号码
	 * @throws UiObjectNotFoundException
	 */
	public static void checkNameAndTel(String name,String number) throws UiObjectNotFoundException{
		check(Object_ResourceId, Operation_TextEqualTrue,"com.android.contacts:id/large_title", name);
		check(Object_ResourceId, Operation_TextEqualTrue, "com.android.contacts:id/header", number);
	}
	
	/**
	 * 检查一个联系人的姓名，电话号码，电子邮件
	 * @param name：姓名
	 * @param number：电话号码
	 * @param mail：电子邮件
	 * @throws UiObjectNotFoundException
	 */
	public static void checkNameTelMail(String name,String number,String mail) throws UiObjectNotFoundException
	{
		check(Object_ResourceId, Operation_TextEqualTrue,"com.android.contacts:id/large_title", name);
		check(Object_ResIdInstance, Operation_TextEqualTrue, "com.android.contacts:id/header","0", number);
		check(Object_ResIdInstance, Operation_TextEqualTrue, "com.android.contacts:id/header","1", mail);
	}
	
	/**
	 * 检查联系人数量
	 * @param name：联系人姓名
	 * @param num:匹配数目
	 */
	public static void checkCopyContact(String name, int num) 
	{
		excute(Object_Description,Operation_ClickWait,"搜索");
		excute(Object_Text,Operation_SetText,"查找联系人",name);
		check(Object_Text,Operation_checkExist,"匹配到 "+num+"个条目");
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
	}
	
	/**
	 * 删除联系人，默认确定删除
	 * @param deleteName：联系人姓名
	 * @throws UiObjectNotFoundException
	 */
	public static void deleteContact(String deleteName) throws UiObjectNotFoundException{ 
		deleteContact(deleteName,Devices_Text_Operator_Determine);
	}
	
	/**
	 * 删除联系人
	 * @param deleteName：联系人姓名
	 * @param decision：确定，取消
	 * @throws UiObjectNotFoundException
	 */
	public static void deleteContact(String deleteName, String decision) throws UiObjectNotFoundException{ 
		excute(Object_Text,Operation_ClickWait, deleteName);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title","删除");
		if(decision.equals(Devices_Text_Operator_Determine))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		}
		else if(decision.equals(Devices_Text_Operator_Cancel))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button2", Devices_Text_Operator_Cancel);
		}
	}
	
	/**
	 * 添加群组，默认在本机
	 * @param name：群组名
	 * @throws UiObjectNotFoundException
	 */
	public static void addGroup(String name) throws UiObjectNotFoundException{
		addGroup("本机",name);
	}
	
	/**
	 * 在本机或SIM卡，添加群组
	 * @param index：本机，SIM1，SIM2
	 * @param name：群组名
	 * @throws UiObjectNotFoundException
	 */
	//index = "本机"或"SIM 卡"
	public static void addGroup(String index,String name) throws UiObjectNotFoundException{
		addGroup(index, name, null);
	}
	
	/**
	 * 在本机或SIM卡，添加群组，并添加群组成员
	 * @param index：本机，SIM1，SIM2
	 * @param name：群组名
	 * @param member：群组成员名
	 */
	public static void addGroup(String index,String name, String[] member) 
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_add_group", "添加群组");
		if((Boolean) excute(Object_Text,Operation_Exists,"要在哪个帐户下创建群组？"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/text2", index);
		}
		excute(Object_ResourceId,Operation_SetText,"com.android.contacts:id/group_name",name);
		if(member!=null)
		{
			excute(Object_Text,Operation_ClickWait,"点击添加联系人");
			for(int i=0;i<member.length;i++)
			{
				excute(Object_Text,Operation_ClickWait, member[i]);			
			}
			excute(Object_Text,Operation_ClickWait,"完成");			
		}
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/save_menu_item");
	}
	
	/**
	 * 添加联系人至当前群组
	 * @param name：联系人
	 * @throws UiObjectNotFoundException
	 */
	public static void addContactToGroup(String[] name) throws UiObjectNotFoundException{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "修改");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/select_group_member", "点击添加联系人");
		for(int i=0;i<name.length;i++)
		{
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", name[i]);
		}
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button","完成");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item","完成");
		for(int i=0;i<name.length;i++)
		{
		Assert.assertTrue("Error: add contact to group fail!!!",
				(Boolean) excute(Object_Text,Operation_Exists,name[i]));
		}
		backContactHome();
	}
	
	/**
	 * 添加所有联系人至当前群组
	 * @throws UiObjectNotFoundException
	 */
	public static void addALLContactToGroup() throws UiObjectNotFoundException{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "修改");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/select_group_member", "点击添加联系人");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
		
		UiObject selectNum = (UiObject) excute(Object_ResourceId, Operate_ReturnObject,"com.android.contacts:id/select_contact_num");
		//int num1=getNum(selectNum.getText());
		
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button","完成");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item","完成");
		/*
		//保证数据更新
		Wait(10000);
		UiObject addedNum = (UiObject) excute(Object_ClassContainsText, Operate_ReturnObject,"android.widget.TextView","人来自");
		int num2=getNum(addedNum.getText());
		
		Assert.assertTrue("Error: add all contact to group fail!!!",num1==num2);
		*/
		backContactHome();
	}
	
	/**
	 * 选择一个群组点击修改
	 *  @param groupName：群组名
	 */
	public static void modifyGroup(String groupName) throws UiObjectNotFoundException{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/label", groupName);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "修改");
	}
	
	/**
	 * 删除一个或所有群组
	 * @param index：all或者群组名
	 */
	public static void deleteGroup(String index) throws UiObjectNotFoundException{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "群组");
		if((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.contacts:id/empty"))
		{
			System.out.println("Groups were already empty.");
		}
		else
		{
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "删除群组");
			if(index.equals("all"))
			{
				excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/select_group_cb");
			}
			else
			{
				excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/label",index);
			}
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button","完成");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		}
		backContactHome();
	}
	
	/**
	 * 删除群组成员
	 * @param Groupname：群组名称
	 * @param name：成员姓名
	 * @param decision：完成，舍弃更改
	 * @throws UiObjectNotFoundException
	 */
	public static void deleteGroupMember(String Groupname, String name, String decision) throws UiObjectNotFoundException 
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"群组");
		excute(Object_Text,Operation_ClickWait,Groupname);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "修改");
		excute(Object_PeerTextClass,Operation_ClickWait,name,"android.widget.FrameLayout");
		if(decision.equals("完成"))
			excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item",decision);
		if(decision.equals("舍弃更改"))
		{
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_Text,Operation_ClickWait,decision);
			if((Boolean) excute(Object_Text,Operation_Exists,"要舍弃您所做的更改吗？"))
				excute(Object_Text,Operation_ClickWait,"确定");
		}
		backContactHome();
	}
	
	/**
	 * 检查群组及群组成员
	 * @param Groupname：群组名称
	 * @param member：联系人姓名
	 * @param index：本机，SIM卡
	 */
	public static void checkGroup(String Groupname, String[] member, String index) 
	{
		excute(Object_Text,Operation_WaitForExists,member.length+" 个联系人","10000");
		check(Object_Text,Operation_checkExist,Groupname);
		excute(Object_Text,Operation_ClickWait,Groupname);
		for(int i=0; i<member.length;i++)
		{
			check(Object_Text,Operation_checkExist,member[i]);
		}
		if(index.equals("本机"))
			check(Object_Text,Operation_checkExist,"本机中有 "+member.length+" 个联系人");
		if(index.equals("SIM卡"))
			check(Object_Text,Operation_checkExist,"SIM 卡中有 "+member.length+" 个联系人");
	}
	
	/**
	 * 检查群组成员是否存在
	 * @param Groupname：群组名称
	 * @param name：成员姓名
	 * @param check：存在，不存在
	 * @throws UiObjectNotFoundException
	 */
	public static void checkGroupMember(String Groupname, String name, String check) throws UiObjectNotFoundException 
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"群组");
		excute(Object_Text,Operation_ClickWait,Groupname);
		if(check.equals("存在"))
			check(Object_Text,Operation_checkExist,name);
		if(check.equals("不存在"))
			check(Object_Text,Operation_checkNoExist,name);
		backContactHome();
	}
	
	/**
	 * 复制联系人到
	 *  @param contactName：联系人
	 *  @param destn:本机，SIM1，SIM2
	 */
	public static void copyContactTo(String contactName,String destn) throws UiObjectNotFoundException{
		copyContactTo(contactName, destn, Devices_Text_Operator_Determine, "完成");
	}
	
	/**
	 * 	复制联系人到
	 *  @param contactName：联系人
	 *  @param destn:本机，SIM1，SIM2
	 *  @param decision：确定，取消
	 */
	public static void copyContactTo(String contactName,String destn, String decision) throws UiObjectNotFoundException{
		if(decision.equals(Devices_Text_Operator_Cancel))
		copyContactTo(contactName, destn, Devices_Text_Operator_Cancel, Devices_Text_Operator_Cancel);
	}
	
	/**
	 * 	复制联系人到
	 *  @param contactName：联系人
	 *  @param destn:本机，SIM1，SIM2
	 *  @param Deci1：确定，取消
	 *  @param Deci2：确定，取消
	 */
	public static void copyContactTo(String contactName,String destn, String Deci1, String Deci2) throws UiObjectNotFoundException{
		selectDisplayContact("所有联系人");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "导入/导出");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "复制到帐户");
		excute(Object_ResIdContainsText,Operation_ClickWait,"android:id/text2", destn);
		if(Deci1.equals(Devices_Text_Operator_Determine))
		{
			if((Boolean) excute(Object_Text,Operation_Exists,Devices_Text_Operator_Determine))
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", contactName);
			Wait(1000);
			if(Deci2.equals("完成"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button", "完成");
			}
			else if(Deci2.equals(Devices_Text_Operator_Cancel))
			{
				excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cancel_menu_item_button", Devices_Text_Operator_Cancel);
			}
		}
		else if(Deci1.equals(Devices_Text_Operator_Cancel))
		{
			excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cancel_menu_item_button", Devices_Text_Operator_Cancel);
		}
	}
	
	/**
	 * 显示指定联系人
	 * @param DeleTarget：所有联系人，SIM1，SIM2，本机，自定义，有电话号码的联系人。
	 * @throws UiObjectNotFoundException
	 */
	public static void selectDisplayContact(String DeleTarget) throws UiObjectNotFoundException{

		excute(Object_Text,Operation_ClickWait,"所有联系人");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		if((Boolean) excute(Object_ResourceId,Operation_IsChecked,"com.android.contacts:id/checkbox"))
			excute(Object_Text,Operation_ClickWait,"有电话号码的联系人");
		excute(Object_Text,Operation_ClickWait, DeleTarget);
	}
	
	/**
	 * 自定义联系人设置
	 * @param DeleTarget:SIM1,SIM2
	 * @param index:确定，取消
	 */
	public static void selectDisplayContact(String DeleTarget, String index)
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		excute(Object_Text,Operation_ClickWait,"自定义");
		excute(Object_Text,Operation_ClickWait,DeleTarget);
		if(!(Boolean) excute(Object_ResIdInstance,Operation_IsChecked,"android:id/checkbox","0"))
			excute(Object_ResIdInstance,Operation_ClickWait,"android:id/checkbox","0");
		excute(Object_Text,Operation_ClickWait,index);
		excute(Object_Text,Operation_WaitForExists,"通讯录","5000");
	}
	
	/**
	 * 检查显示联系人
	 * @param name：本机，SIM1，SIM2，自定义，有电话号码的联系人
	 */
	public static void checkDisplayContacts(String name)
	{
		if(name.equals("本机"))
		{
			check(Object_Text,Operation_checkExist,"zhanxun");
			check(Object_Text,Operation_checkNoExist,"SIM1");
			check(Object_Text,Operation_checkNoExist,"SIM2");
		}
		if(name.equals("SIM1"))
		{
			check(Object_Text,Operation_checkNoExist,"zhanxun");
			check(Object_Text,Operation_checkExist,"SIM1");
			check(Object_Text,Operation_checkNoExist,"SIM2");
		}
		if(name.equals("SIM2"))
		{
			check(Object_Text,Operation_checkNoExist,"zhanxun");
			check(Object_Text,Operation_checkNoExist,"SIM1");
			check(Object_Text,Operation_checkExist,"SIM2");
		}
		if(name.equals("自定义"))
		{
			check(Object_Text,Operation_checkExist,"自定义视图");
			excute(Object_Device,Operation_PressBack);
			excute(Object_Device,Operation_PressBack);
		}
		if(name.equals("有电话号码的联系人"))
		{
			excute(Object_Device,Operation_PressBack);
			check(Object_Text,Operation_checkExist,"zhanxun");
		}
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"要显示的联系人");
		if((Boolean) excute(Object_ResourceId,Operation_IsChecked,"com.android.contacts:id/checkbox"))
			excute(Object_Text,Operation_ClickWait,"有电话号码的联系人");
		excute(Object_Text,Operation_ClickWait,"所有联系人");
	}
	
	/**
	 * 导出联系人
	 * @param name：需要导出的联系人姓名
	 */
	public static void exportVcf(String name) 
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"导入/导出");
		excute(Object_Text,Operation_ClickWait,"导出为 .vcf 文件");
		excute(Object_Text,Operation_ClickWait,name);
		excute(Object_Text,Operation_ClickWait,"完成");
		if((Boolean)excute(Object_Text,Operation_Exists,"要允许文档访问您设备上的照片、媒体内容和文件吗？"))
			{
				excute(Object_Text,Operation_ClickWait,"允许");
				excute(Object_Text,Operation_ClickWait,"下载");
			}
		if((Boolean)excute(Object_Text,Operation_Exists,"要允许通讯录允许应用访问短信附件吗？"))
			{
				excute(Object_Text,Operation_ClickWait,"允许");
			}
		excute(Object_Text,Operation_ClickWait,"保存");
	}
	
	/**
	 * 导入联系人
	 * @param name：联系人姓名
	 * @param vcfname:vcf文件名
	 */
	public static void importVcf(String name, String vcfname) 
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"导入/导出");
		excute(Object_Text,Operation_ClickWait,"从 .vcf 文件导入");
		excute(Object_Text,Operation_ClickWait, vcfname);
		excute(Object_Text,Operation_WaitForExists,name,"5000");
	}
	
	/**
	 * 通过短信发送
	 * @param name：联系人姓名
	 */
	public static void sendByMsg(String name) 
	{
		excute(Object_Text,Operation_ClickWait,name);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/icon_alternate");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/compose_message_text","5000");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/compose_message_text");
	}
	
	/**
	 * 检查分享方式
	 */
	public static void checkShare() 
	{
		boolean boo=(Boolean)excute(Object_Text,Operation_Exists,"分享方式")||(Boolean)excute(Object_Text,Operation_Exists,"使用其他应用");
		Assert.assertTrue(boo);
		//if((Boolean) excute(Object_Text,Operation_Exists,"分享方式"))
			///check(Object_Text,Operation_ClickWait,"分享方式");
		//if((Boolean) excute(Object_Text,Operation_Exists,"使用其他应用"))
			//check(Object_Text,Operation_ClickWait,"使用其他应用");
	}
	
	/**
	 * 清除我的个人资料
	 * @throws UiObjectNotFoundException
	 */
	public static void deleteMyData() throws UiObjectNotFoundException{
		  if((Boolean)excute(Object_Text,Operation_Exists,"设置我的个人资料"))
		  { 
		   System.out.println("");
		  }
		  else
		  {
		   excute(Object_Text,Operation_ClickWait,"我");
		   excute(Object_Device,Operation_PressMenu);
		   excute(Object_Text,Operation_ClickWait,"删除");
		   excute(Object_Text,Operation_ClickWait,"确定");
		   check(Object_Text,Operation_checkExist,"设置我的个人资料");
		  }
		 }
	

	//以下为未调用的方法
	public static void addPhoneWithPic(String name,String number,String option) throws UiObjectNotFoundException{
		selectContactPosition("本机");
		excute(Object_TextScroll,Operation_SetText,"姓名","vertical",name);
		excute(Object_Device, Operation_PressBack);
		excute(Object_TextScroll,Operation_SetText,"电话","vertical",number);
		excute(Object_TextScroll,Operation_ClickWait,"更改","vertical");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", option);
		if(option.equals("拍照"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
			Wait(1000);
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/done_button");
			
		}
		else if(option.equals("选择照片"))
		{
			Wait(2000);
			//选择图片
			UiObject photo = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.documentsui:id/icon_mime");
			UiObject gallery = (UiObject) excute(Object_ResIdText,Operate_ReturnObject,"android:id/title", "图库");
			if((Boolean) excute(Object_Description,Operation_Exists,"显示根目录"))
			{
				excute(Object_Description,Operation_ClickWait,"显示根目录");
			}
			if((Boolean) excute(Object_Text,Operation_Exists,"图片"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "图片");
				while((Boolean) excute(Object_ResourceId,Operation_Exists,"com.android.documentsui:id/icon_mime"))
				{
				excute(Object_ResourceId,Operation_ClickWait,"com.android.documentsui:id/icon_mime");
					Wait(2000);
				}
				while((Boolean) excute(Object_ClassInstance,Operation_Exists,"android.widget.LinearLayout","0"))
				{
					excute(Object_ClassInstance,Operation_ClickWait,"android.widget.LinearLayout","0");
					Wait(2000);
				}
			}
			//图库操作
			else if(gallery.exists())
			{
				gallery.clickAndWaitForNewWindow();
				UiDevice uiDevice = UiDevice.getInstance();
				uiDevice.click(uiDevice.getDisplayWidth() / 2,uiDevice.getDisplayHeight()/2);
				Wait(2000);
				uiDevice.click(uiDevice.getDisplayWidth()/2,uiDevice.getDisplayHeight()/2);
			}
		}
		excute(Object_ResIdText,Operation_ClickWait,"com.android.gallery3d:id/filtershow_done", "保存");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
	}

	public static void modifyContact(String text, String modText) throws UiObjectNotFoundException{
		modifyContact(text, modText, Devices_Text_Operator_Determine);
	}
	public static void modifyContact(String text, String modText, String decision) throws UiObjectNotFoundException{
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_edit", "修改");
		UiObject ModArea = (UiObject) excute(Object_DescScroll, Operate_ReturnObject,text, "vertical");
		int WideArea = ModArea.getBounds().width();
		int x = ModArea.getBounds().centerX()+(3 * WideArea);
		int y = ModArea.getBounds().centerY();
		UiDevice.getInstance().drag(x, y, x, y, 0);
		excute(Object_ResIdDesc,Operation_ClickWait,"android:id/cut","剪切");
		ModArea.setText(modText);
		excute(Object_Device, Operation_PressBack);
		
		if(decision.equals(Devices_Text_Operator_Determine))
		{
			excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/save_menu_item", "完成");
		}
		else if(decision.equals(Devices_Text_Operator_Cancel))
		{
			excute(Object_Device, Operation_PressBack);
			check(Object_Text, Operation_checkExist,"要舍弃您所做的更改吗？");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
		}
		
	}
	
	public static void importContact(String option, String importPath) throws UiObjectNotFoundException{
		importContact(option, importPath, Devices_Text_Operator_Determine);
	}
	
	public static void importContact(String option, String importPath, String decision) throws UiObjectNotFoundException{
		selectDisplayContact("所有联系人");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "导入/导出");
		
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", importPath);
		
		if(new GetObjectStatusByText("未在SD卡上找到 vCard 文件。").getObjectStatus("isExist"))
		{
			excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
			Assert.assertTrue("!!!未在SD卡上找到 vCard 文件!!!",false);	
		}
		Wait(5000);
		if(new GetObjectStatusByText("选择 vCard 文件").getObjectStatus("isExist")){
			if(option.equals("one"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "导入一个 vCard 文件");
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
				excute(Object_ClassIndex,Operation_ClickWait,"android.widget.CheckedTextView","0");
			}
			else if(option.equals("more"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "导入多个 vCard 文件");
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
				excute(Object_ClassIndex,Operation_ClickWait,"android.widget.CheckedTextView","0");
				excute(Object_ClassIndex,Operation_ClickWait,"android.widget.CheckedTextView","1");
			}
			else if(option.equals("all"))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "导入所有 vCard 文件");
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
			}
			if(decision.equals(Devices_Text_Operator_Determine))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
				Wait(4000);
			}
			else if(decision.equals(Devices_Text_Operator_Cancel))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button2", Devices_Text_Operator_Cancel);
			}
		}	
	}
	
	public static void judgeImport() throws UiObjectNotFoundException {
		judgeImport(Devices_Text_Operator_Determine);
	}
	public static void judgeImport(String decision) throws UiObjectNotFoundException {
		String regex = "顺利导入";
		UiDevice.getInstance().openNotification();
		Wait(500);
		UiObject importResult = new UiObject(new UiSelector().resourceId("android:id/title").textContains(regex));
		if(decision.equals(Devices_Text_Operator_Determine))
		{
			Assert.assertTrue("Error: import failed!!!",importResult.exists());
		}
		else if(decision.equals(Devices_Text_Operator_Cancel))
		{
			Assert.assertTrue("Error: import failed!!!",!importResult.exists());
		}
		excute(Object_Device, Operation_PressBack);
	}
	
	public static String exportContact(String contactName,String exportPath) throws UiObjectNotFoundException{
		return exportContact(contactName,exportPath, Devices_Text_Operator_Determine);
	}
	
	public static String exportContact(String contactName,String exportPath, String decision) throws UiObjectNotFoundException{
		selectDisplayContact("所有联系人");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "导入/导出");
		
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", exportPath);
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/cliv_name_textview", contactName);
		Wait(1000);
		excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button", "完成");

		UiObject vcf = (UiObject) excute(Object_ClassResId,Operate_ReturnObject,"android.widget.TextView", "android:id/message");
		String vcfName = vcf.getText();
		System.out.println(vcfName);
		String regEx = ".*/.*/.*/(.*.vcf)";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(vcfName);
		if(matcher.find())
		{
			//系统会将您的联系人列表导出到以下文件：/storage/sdcard0/00002.vcf。
			System.out.println("matcher: >>>>> = " + matcher.group(1));
			if(decision.equals(Devices_Text_Operator_Determine))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
			}
			else if(decision.equals(Devices_Text_Operator_Cancel))
			{
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button2", Devices_Text_Operator_Cancel);
			}
		}
		else
		{
			Assert.assertTrue("Error: matcher: not found!!!",false);
		}
		return matcher.group(1);
	}
	
	public static void judgeCopy(String sourceName, String destn) throws UiObjectNotFoundException {
		judgeCopy(sourceName, destn, Devices_Text_Operator_Determine);
	}
	
	public static void judgeCopy(String sourceName, String destn, String decision) throws UiObjectNotFoundException {
		selectDisplayContact(destn);
		boolean flag = decision.equals(Devices_Text_Operator_Determine) ? true : false;
		
		if(flag)
			check(Object_TextScroll, Operation_checkExist, sourceName,"vertical");
		else
			check(Object_TextScroll, Operation_checkNoExist, sourceName,"vertical");
		
	}
	//事先在本机创建，然后分别复制到SIM1、SIM2、本机
	public static void loopCopy(int loopNum, int total) throws UiObjectNotFoundException {
		ContactCommon.BatchDelete("所有联系人");
		if(!(Boolean) excute(Object_Text,Operation_IsChecked,"所有联系人"))
		{
			excute(Object_Text,Operation_ClickWait,"所有联系人");
		}
		for(int i=0;i<loopNum;i++)
		{
			ContactCommon.addNameAndTel("本机",PhoneName+Integer.toString(i), TelNumber+Integer.toString(i));
			excute(Object_Device, Operation_PressBack);
		}
		UiObject contactCount=(UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.contacts:id/contacts_count");
		int num = getNum(contactCount.getText());
		if(num < total)
		{
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "导入/导出");
			excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "复制到帐户");
			UiCollection listview=new UiCollection(new UiSelector().className("android.widget.ListView").resourceId("android:id/select_dialog_listview"));
			int count=listview.getChildCount();
			excute(Object_Device, Operation_PressBack);
			storageCopy(count, num, total);
		}	
	}
	
	public static void storageCopy(int countNum, int num, int total) throws UiObjectNotFoundException {
		UiObject contactCount=(UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.contacts:id/contacts_count");
		int i = 0;
		while(num < total)
		{
			if(i < countNum)
			{
				selectDisplayContact("所有联系人");//翻倍增长
				excute(Object_Description,Operation_ClickWait,"更多选项");
				excute(Object_ResIdText,Operation_ClickWait,"android:id/title", "导入/导出");
				excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", "复制到帐户");
				
				excute(Object_ClassInstance,Operation_ClickWait,"android.widget.ImageView",Integer.toString(i));
				
				if((Boolean) excute(Object_Text,Operation_Exists,Devices_Text_Operator_Determine))
				excute(Object_ResIdText,Operation_ClickWait,"android:id/button1", Devices_Text_Operator_Determine);
				
				excute(Object_ResourceId,Operation_ClickWait,"com.android.contacts:id/select_contact_cb");
				excute(Object_ResIdText,Operation_ClickWait,"com.android.contacts:id/save_menu_item_button", "完成");
				System.out.println(">>>>>>>>>>>waitting "+(int) (num*(10.0/total))+" s");
				Wait((int) (num*(10.0/total)));
				Wait(2000);//保证contactCount控件更新
				num=getNum(contactCount.getText());
				System.out.println(">>>>>>>>>>>num = "+num);
				i++;
			}
			else
				i = 0;
		}
	}
	//从字符串中提取数字
	public static int getNum(String str) 
	{
		String str2 = "";
		if(str != null && !"".equals(str)){
			str=str.trim();
			for(int i=0;i<str.length();i++){
				if(str.charAt(i)>=48 && str.charAt(i)<=57)
				{
					str2+=str.charAt(i);
				}
			}
		}
		else
		{
			Assert.assertTrue("Error: The getting texts are empty!!!", false);
		}
		if(str2.equals(""))
			return 0;
		else
			return Integer.parseInt(str2);
	}
	
	public static void judgeExport(String regex, String destn) throws UiObjectNotFoundException {
		judgeExport(regex, destn, Devices_Text_Operator_Determine);
	}
	
	public static void judgeExport(String regex, String destn, String decision) throws UiObjectNotFoundException {
		DeviceCommon.enterApp(Devices_Desc_FileManage);
		excute(Object_ClassName,Operation_ClickWait,"android.widget.Spinner");
		String text = destn.contains("内部") ? "手机" : "存储卡"; 
		excute(Object_ResIdText,Operation_ClickWait,"android:id/text1", text);
		boolean flag =(Boolean) excute(Object_TextScrollWithResId,Operation_Exists,FileManage_ResId_More_Scroll, regex,"vertical");
		if(decision.equals(Devices_Text_Operator_Determine))
		{
			Assert.assertTrue("Error: 导出到"+destn+"上的vCard文件并未找到!!!",flag);
		}
		else if(decision.equals(Devices_Text_Operator_Cancel))
		{
			Assert.assertTrue("Error: 导出到"+destn+"取消操作失败!!!",!flag);
		}
		DeviceCommon.exitApp();
	}
	
	public static int[] matchResult(String[] name,String[] number,String matcher) throws UiObjectNotFoundException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<name.length;i++)
		{
			if(name[i].contains(matcher) || number[i].contains(matcher))
			{
				list.add(i);
				System.out.println(">>>>>>>>>>>"+i);
			}
		}
		System.out.println("<<<<<<<<<<<"+list);
		
		int[] result = new int[list.size()];
		for(int i=0; i < list.size(); i++)
		{
			result[i] = list.get(i);
		}
		return result;
	}
}
