package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import android.graphics.Rect;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;
import framework.common.MessageCommon;

public class Message extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp( Devices_Desc_Message);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 进入信息
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.mmsfolderview:id/action_bar");//状态栏
	}
	/**
	 * 进入信息无信息时查看
	 */
	public static void test_222()
	{
		//主体
		check(Object_Text,Operation_checkExist,"您的信息对话将列在此处");
	}
	/**
	 * 进入信息菜单
	 */
	public static void test_003()
	{
		//主体
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text,Operation_checkExist,"设置");
		check(Object_Text,Operation_checkExist,"已归档的对话");
		check(Object_Text,Operation_checkExist,"无线警报");
	}
	/**
	 * 进入信息文件夹视图
	 */
	public static void test_004()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		check(Object_ResourceId, Operation_checkExist, "com.android.mmsfolderview:id/actionbar_spinner");
	}
	/**
	 * 已归档的对话
	 */
	public static void test_005()
	{
		//主体
		MessageCommon.switchView("已归档的对话");
		check(Object_ClassText, Operation_checkExist, "android.widget.TextView", "已归档的对话");
	}
	/**
	 * 设置
	 */
	public static void test_006()
	{
		//主体
		MessageCommon.switchView("设置");
		check(Object_ClassText, Operation_checkExist, "android.widget.TextView", "设置");
	}
	/**
	 * 设置
	 */
	public static void test_007()
	{
		//主体
		MessageCommon.switchView("无线警报");
		check(Object_ClassText, Operation_checkExist, "android.widget.TextView", "小区广播");
	}
	/**
	 * 点击短信
	 */
	public static void test_008()
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.messaging:id/swipeableContent");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.messaging:id/action_call", "10000");//拨号按钮
		check(Object_ResourceId, Operation_checkExist, "com.android.messaging:id/action_call");
	}
	/**
	 * 长按短信
	 */
	public static void test_009()
	{
		//主体
		excute(Object_ResourceId, Operation_LongClick, "com.android.messaging:id/swipeableContent");
		check(Object_Description, Operation_checkExist, "转到上一层级");
		check(Object_Description, Operation_checkExist, "归档");
	}
	/**
	 * 长按短信取消
	 */
	public static void test_010()
	{
		//主体
		MessageCommon.Longclickmessage("转到上一层级");
		check(Object_Description, Operation_checkNoExist, "转到上一层级");
	}
	/**
	 * 长按短信归档
	 */
	public static void test_011()
	{
		//主体
		String Name = (String) excute(Object_ResIdInstance, Operation_GetText, "com.android.messaging:id/conversation_name", "0");
		MessageCommon.Longclickmessage("归档");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "已归档的对话");
		check(Object_Text, Operation_checkExist, Name);
		//清场
		excute(Object_Text, Operation_LongClick, Name);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.messaging:id/action_unarchive");
	}
	/**
	 * 长按短信删除
	 */
	public static void test_012()
	{
		//主体
		MessageCommon.Longclickmessage("删除");
		check(Object_Text, Operation_checkExist, "要删除这些对话吗？");
	}
	/**
	 * 长按短信取消通知
	 */
	public static void test_013()
	{
		//主体
		MessageCommon.Longclickmessage("关闭通知");
		check(Object_ResourceId, Operation_checkExist, "com.android.messaging:id/conversation_notification_bell");
		//清场
		MessageCommon.Longclickmessage("开启通知");
	}
	/**
	 * 长按短信添加到联系人
	 */
	public static void test_014()
	{
		//主体
		MessageCommon.Longclickmessage("添加到通讯录");
		check(Object_Text, Operation_checkExist, "要添加到通讯录吗？");
	}
	/**
	 * 长按短信阻止收件人
	 */
	public static void test_015()
	{
		//主体
		MessageCommon.Longclickmessage("屏蔽");
		check(Object_Text, Operation_checkExist, "您将继续接收来自此号码的信息，但不会再收到相关通知。此对话将被归档。");
	}
	/**
	 * 文件夹视图
	 */
	public static void test_016()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		check(Object_ResourceId, Operation_checkExist, "com.android.mmsfolderview:id/actionbar_spinner");//选项按钮
	}
	/**
	 * 文件夹视图
	 */
	public static void test_017()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.mmsfolderview:id/actionbar_spinner");
		check(Object_Text, Operation_checkExist, "收件箱");
		check(Object_Text, Operation_checkExist, "已发送");
		check(Object_Text, Operation_checkExist, "发件箱");
		check(Object_Text, Operation_checkExist, "草稿箱");
	}
	/**
	 * 文件夹视图收件箱
	 */
	public static void test_018()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.Menuoption("收件箱");
		check(Object_Text, Operation_checkExist, "收件箱");
	}
	public static void test_024()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.Menuoption("收件箱");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.mmsfolderview:id/action_sortby");
		excute(Object_Text, Operation_ClickWait, "按时间降序");
		int num = (int) excute(Object_ResourceId, Operation_GetChildCount, "android:id/list");
		String [] tim=new String[num];
		for(int i = 0; i<num; i++)
		{
			String Time = (String) excute(Object_ResIdInstance, Operation_GetText, "com.android.mmsfolderview:id/conversation_timestamp", String.valueOf(i));
			String prefix = Time.substring(Time.lastIndexOf("午")+1);
			SimpleDateFormat format =  new SimpleDateFormat("mm:ss");        
			Date date = format.parse(prefix);
			tim[i]=Time;
			
		}
		System.out.print("****************************"+tim);
	}
	
	public static void test_073() throws UiObjectNotFoundException 
	{
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
}
	
