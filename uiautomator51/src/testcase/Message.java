package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import android.graphics.Rect;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



import framework.common.CallCommon;
import framework.common.CameraCommon;
import framework.common.ContactCommon;
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
	/**
	 * 文件夹视图已发送
	 */
	public static void test_019()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.Menuoption("已发送");
		check(Object_Text, Operation_checkExist, "已发送");
	}
	/**
	 * 文件夹视图发件箱
	 */
	public static void test_020()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.Menuoption("发件箱");
		check(Object_Text, Operation_checkExist, "发件箱");
	}
	/**
	 * 文件夹视图草稿箱
	 */
	public static void test_021()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.Menuoption("草稿箱");
		check(Object_Text, Operation_checkExist, "草稿箱");
	}
	/**
	 * 文件夹视图新建
	 */
	public static void test_022()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.mmsfolderview:id/action_compose_new");
		check(Object_ResIdText, Operation_checkExist, "com.android.messaging:id/recipient_text_view", "收件人");
	}
	/**
	 * 文件夹视图排序
	 */
	public static void test_023()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.mmsfolderview:id/action_sortby");
		check(Object_Text, Operation_checkExist, "按时间降序");
	}
	public static void test_024() throws ParseException
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
	/**
	 * 文件夹视图菜单
	 */
	public static void test_028()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "消息视图");
		check(Object_Text, Operation_checkExist, "无线警报");
		check(Object_Text, Operation_checkExist, "删除信息");
		check(Object_Text, Operation_checkExist, "设置");
		check(Object_Text, Operation_checkExist, "显示选项");
	}
	/**
	 * 文件夹视图切换消息视图
	 */
	public static void test_029()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("消息视图");
		check(Object_Text, Operation_checkExist, "信息");
	}
	/**
	 * 文件夹视图切换小区广播
	 */
	public static void test_030()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("无线警报");
		check(Object_Text, Operation_checkExist, "小区广播");
	}
	/**
	 * 文件夹视图切换显示选项
	 */
	public static void test_031()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("显示选项");
		check(Object_Text, Operation_checkExist, "取消");
	}
	/**
	 * 文件夹视图切换显示全部
	 */
	public static void test_032()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("显示选项");
		excute(Object_Text, Operation_ClickWait, "显示全部信息");
		check(Object_Text, Operation_checkExist, "SIM1");
		check(Object_Text, Operation_checkExist, "SIM2");
	}
	/**
	 * 文件夹视图切换显示SIM1
	 */
	public static void test_033()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("显示选项");
		excute(Object_Text, Operation_ClickWait, "SIM1");
		check(Object_Text, Operation_checkExist, "SIM1");
		check(Object_Text, Operation_checkNoExist, "SIM2");
	}
	/**
	 * 文件夹视图切换显示SIM2
	 */
	public static void test_034()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("显示选项");
		excute(Object_Text, Operation_ClickWait, "SIM2");
		check(Object_Text, Operation_checkExist, "SIM2");
		check(Object_Text, Operation_checkNoExist, "SIM1");
	}
	/**
	 * 文件夹视图删除
	 */
	public static void test_035()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("删除信息");
		check(Object_Text, Operation_checkExist, "全选");
	}
	/**
	 * 文件夹视图选择一个删除
	 */
	public static void test_036()
	{
		//主体
		MessageCommon.switchView("文件夹视图");
		MessageCommon.switchView("删除信息");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.mmsfolderview:id/swipeableContent");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text, Operation_checkExist, "确认要删除所选的信息？");
	}
	/**
	 * 文件夹视图设置
	 */
	public static void test_037()
	{
		//前提
		MessageCommon.switchView("文件夹视图");
		//主体
		MessageCommon.switchView("设置");
		check(Object_Text, Operation_checkExist, "常规");
	}
	/**
	 * 文件夹视图长按菜单
	 */
	public static void test_038()
	{
		//前提
		MessageCommon.switchView("文件夹视图");
		//主体
		excute(Object_ResourceId, Operation_LongClick, "com.android.messaging:id/swipeableContent");
		check(Object_Text, Operation_checkExist, "屏蔽");
		check(Object_Text, Operation_checkExist, "呼叫");
		check(Object_Text, Operation_checkExist, "呼叫前编辑");
		check(Object_Text, Operation_checkExist, "删除");
	}
	/**
	 * 文件夹视图长按添加到联系人
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_039() throws UiObjectNotFoundException
	{
		//前提
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		DeviceCommon.enterApp( Devices_Desc_Message);
		MessageCommon.switchView("文件夹视图");
		//主体
		MessageCommon.Longclickmessage("添加到联系人");
		check(Object_Text, Operation_checkExist, "选择联系人");
	}
	/**
	 * 文件夹视图长按呼叫
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_040() throws UiObjectNotFoundException
	{
		//前提
		MessageCommon.switchView("文件夹视图");
		//主体
		MessageCommon.Longclickmessage("呼叫");
		if ((Boolean)excute(Object_Text, Operation_Exists, "用于外拨电话的帐户"))
			excute(Object_ResIdInstance, Operation_ClickWait, "com.android.dialer:id/label", "0");
		
		check(Object_ResourceId, Operation_checkExist, "com.android.dialer:id/floating_end_call_action_button");//挂断按钮
		//清场
		CallCommon.endCall();
	}
	/**
	 * 文件夹视图长按呼叫前编辑
	 * @throws 
	 */
	public static void test_041()
	{
		//前提
		MessageCommon.switchView("文件夹视图");
		//主体
		MessageCommon.Longclickmessage("呼叫前编辑");
		check(Object_ResourceId, Operation_checkExist, "com.android.dialer:id/dialpad_floating_action_button");//拨号按钮
	}
	/**
	 * 文件夹视图长按点击删除
	 * @throws 
	 */
	public static void test_042()
	{
		//前提
		MessageCommon.switchView("文件夹视图");
		//主体
		MessageCommon.Longclickmessage("删除");
		check(Object_Text, Operation_checkExist, "要删除此信息吗？");
	}
	/**
	 * 收件箱内点击一条短信
	 */
	public static void test_043()
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		check(Object_Text,Operation_checkExist,"信息详情");
	}
    /**
     * 收件箱回复短信
     */
	public static void test_044() 
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Text,Operation_ClickWait,"回复");
		check(Object_Description,Operation_checkExist,"添加附件");
	}
	/**
     * 收件箱信息详细界面点击菜单
     */
	public static void test_045()
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		String[] str={"新信息","转发","编辑","删除"};
		CameraCommon.checkForExist(str);
	}
	/**
     * 收件箱菜单新建信息
     */
	public static void test_046() 
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"新信息");
        check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/recipient_text_view");
	}
	/**
     * 收件箱菜单转发
     */
	public static void test_048() 
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"转发");
        check(Object_Text,Operation_checkExist,"转发信息");
	}
	/**
     * 收件箱菜单编辑
     */
	public static void test_049() 
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"编辑");
        check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/compose_message_text");
	}
	/**
     * 收件箱菜单删除
     */
	public static void test_050() 
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
        check(Object_Text,Operation_checkExist,"要删除此信息吗？");
	}
	/**
     * 收件箱复制文字
     */
	public static void test_051() 
	{
		//主体
		MessageCommon.enterInbox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_ResourceId,Operation_LongClick,"com.android.messaging:id/content");
        check(Object_Text,Operation_checkExist,"复制文字");
        excute(Object_Text,Operation_ClickWait,"复制文字");
        check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/copy_part_message");
	}
	/**
     * 已发送放缩文字大小
     */
	public static void test_052() 
	{
		//主体
		MessageCommon.enterOutBox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		Rect ModArea0 = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.messaging:id/content");
		int y0 = ModArea0.centerY();
		excute(Object_ResourceId,Operation_LongClick,"android:id/zoomOut");
		Rect ModArea1 = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.messaging:id/content");
		int y1 = ModArea1.centerY();
		boolean boo=y0>y1;
		Assert.assertTrue(boo);
		excute(Object_ResourceId,Operation_LongClick,"android:id/zoomIn");
		Rect ModArea2 = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.messaging:id/content");
		int y2 = ModArea2.centerY();
		boolean boo1=y1<y2;
		Assert.assertTrue(boo1);
	}
	/**
     * 已发送转发
     */
	public static void test_053() 
	{
		//主体
		MessageCommon.enterOutBox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Text,Operation_ClickWait,"转发");
		check(Object_Text,Operation_checkExist,"转发信息");
	}
	/**
     * 已发送新信息
     */
	public static void test_054() 
	{
		//主体
		MessageCommon.enterOutBox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"新信息");
        check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/recipient_text_view");
	}
	/**
     * 已发送删除
     */
	public static void test_055() 
	{
		//主体
		MessageCommon.enterOutBox();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
        check(Object_Text,Operation_checkExist,"要删除此信息吗？");
	}
	/**
     * 发件箱重发信息 
     */
	public static void test_056()  
	{
		//前提
		MessageCommon.newMessageWithNumAndContent("11111111","SendFail");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/self_send_icon");
		excute(Object_Text,Operation_WaitForExists,"发送失败。触摸即可重试。","60000");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		//主体
		MessageCommon.enterSent();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Text,Operation_ClickWait,"重发");
		check(Object_Text,Operation_checkExist,"正在发送…");
	}
	/**
     * 发件箱新信息
     */
	public static void test_057()  
	{
		//主体
		MessageCommon.enterSent();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"新信息");
        check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/recipient_text_view");
	}
	/**
     * 发件箱转发 
     */
	public static void test_058() 
	{
		//主体
		MessageCommon.enterSent();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"转发");
        check(Object_Text,Operation_checkExist,"转发信息");
	}
	/**
     * 发件箱删除 
     */
	public static void test_059() 
	{
		//主体
		MessageCommon.enterSent();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
        check(Object_Text,Operation_checkExist,"要删除此信息吗？");
	}
	/**
     * 草稿箱编辑
     */
	public static void test_060() 
	{
		//前提
		MessageCommon.newMessageWithNumAndContent("10010","Draft");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		//主体
		MessageCommon.enterDrafts();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		//清场
		
	}
	/**
     * 草稿箱新信息
     */
	public static void test_061()
	{
		//主体
		MessageCommon.enterDrafts();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"新信息");
        check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/recipient_text_view");
	}
	/**
     * 草稿箱删除 
     */
	public static void test_062()
	{
		//主体
		MessageCommon.enterDrafts();
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.mmsfolderview:id/conversation_snippet","0");
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"删除");
        check(Object_Text,Operation_checkExist,"要删除此信息吗？");
	}
	/**
	 * 点击新建图标
	 */
	public static void test_063()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		if((Boolean)excute(Object_Text,Operation_Exists,"消息视图"))
		{
			excute(Object_Text,Operation_ClickWait,"消息视图");
		}else
		{
			excute(Object_Device, Operation_PressBack);
		}
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/start_new_conversation_button");
		check(Object_Text,Operation_checkExist,"收件人");
	}
	/**
	 * 输入短信号码和内容
	 */
	public static void test_064()
	{
		//主体
		MessageCommon.newMessageWithNumAndContent("10086", "123");
		check(Object_ResIdText,Operation_checkExist,"com.android.messaging:id/compose_message_text","123");
	}
	/**
	 * 点击附件图标
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_065() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/camera_preview");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/mediapicker_tabstrip");	
	}
	/**
	 * 默认是拍照
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_066() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		check(Object_Description,Operation_checkExist,"拍照");
	}
	/**
	 * 视频
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_067() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/camera_swap_mode_button");
		check(Object_Description,Operation_checkExist,"停止拍摄并添加视频附件");
	}
	/**
	 * 选择图库
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_068() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"选择此设备上的图片");
		check(Object_Description,Operation_checkExist,"从文档库中选择图片");
		//清场
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
	/**
	 * 录音
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_069() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"录制语音");
		check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/record_button_visual");
		//清场
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
	/**
	 * 视频播放
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_070() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"视频");
		check(Object_Text,Operation_checkExist,"点击按钮，您将进入视频选择界面");
		//清场
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
	/**
	 * 视音频铃声
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_071() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"音频");
		check(Object_Text,Operation_checkExist,"点击按钮，您将进入音频选择界面");
		//清场
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
	/**
	 * 联系人附件
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_072() throws UiObjectNotFoundException
	{
		//主体
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"名片");
		check(Object_Text,Operation_checkExist,"点击按钮，您将进入联系人选择界面");
		//清场
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
	public static void test_073() throws UiObjectNotFoundException 
	{
		MessageCommon.addNewMessageAttach("10086");
		excute(Object_Description,Operation_ClickWait,"拍照或录像");
	}
	/**
	 * 进入常规设置，点击默认信息应用
	 * @throws UiObjectNotFoundException
	 */
	public static void test_102() throws UiObjectNotFoundException 
	{	
		//主体
		MessageCommon.enterGeneralSetting();
		excute(Object_Text, Operation_ClickWait, "默认信息应用");
		check(Object_Text, Operation_checkExist, "要更改信息应用吗？");
	}
	/**
	 * 进入常规设置，验证信息发送提示音默认打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_103() throws UiObjectNotFoundException 
	{	
		//主体
		MessageCommon.enterGeneralSetting();
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","0");
	}
	/**
	 * 进入常规设置，信息发送提示音点击一下后，验证被关闭
	 * @throws UiObjectNotFoundException
	 */
	public static void test_104() throws UiObjectNotFoundException 
	{	
		//主体
		MessageCommon.enterGeneralSetting();
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","0");
		excute(Object_Text, Operation_ClickWait, "信息发送提示音");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","0");
		//清场
		excute(Object_Text, Operation_ClickWait, "信息发送提示音");
	}	
	/**
	 * 进入常规设置，验证通知默认打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_105() throws UiObjectNotFoundException 
	{	
		//主体
		MessageCommon.enterGeneralSetting();
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","1");
	}	
	/**
	 * 进入常规设置，点击一下通知，验证开关被关闭
	 * @throws UiObjectNotFoundException
	 */
	public static void test_106() throws UiObjectNotFoundException 
	{	
		//主体
		MessageCommon.enterGeneralSetting();
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","1");
		excute(Object_Text, Operation_ClickWait, "通知");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","1");
		//清场
		excute(Object_Text, Operation_ClickWait, "通知");
	}	
	/**
	 * 进入高级设置
	 * @throws UiObjectNotFoundException
	 */
	public static void test_107() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_Text, Operation_checkExist, "群发彩信");
	}
	/**
	 * 进入高级设置 ，点击群发彩信
	 * @throws UiObjectNotFoundException
	 */
	public static void test_108() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_Text, Operation_ClickWait, "群发彩信");
		check(Object_Text, Operation_checkExist, "将单条短信发送给所有收件人。只有您能收到相应回复");
		check(Object_Text, Operation_checkExist, "将单条彩信发送给所有收件人");
	}
	/**
	 *  进入高级设置 ,自动检索的默认状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_111() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","0");//自动检索
	}
	/**
	 *  进入高级设置 ,点击一下自动检索，开关被关闭
	 * @throws UiObjectNotFoundException
	 */
	public static void test_112() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		if ((Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","0")) 
		{
			excute(Object_Text, Operation_ClickWait, "自动检索");
		}
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","0");
		//清场
		excute(Object_Text, Operation_ClickWait, "自动检索");
	}	
	/**
	 *  进入高级设置 ,漫游时自动检索的默认状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_113() throws UiObjectNotFoundException 
	{
		//前提
		MessageCommon.enterSIMSetting();
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","0")) 
		{
			excute(Object_Text, Operation_ClickWait, "自动检索");
		}
		//主体
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/switchWidget","1");//漫游时自动检索
	}	
	/**
	 *  进入高级设置 ,点击一下漫游时自动检索开关，此开关被打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_114() throws UiObjectNotFoundException 
	{
		//前提
		MessageCommon.enterSIMSetting();
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","0")) 
		{
			excute(Object_Text, Operation_ClickWait, "自动检索");
		}
		//主体
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","1")) 
		{
			excute(Object_Text, Operation_ClickWait, "漫游时自动检索");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","1");
		//清场
		excute(Object_Text, Operation_ClickWait, "漫游时自动检索");
	}
	/**
	 * 进入高级设置，点击彩信有效期
	 * @throws UiObjectNotFoundException
	 */
	public static void test_115() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_Text, Operation_ClickWait, "彩信有效期");
		String[] str={"不设定","1小时","12小时","24小时","2天","最大值"};
		for (int i = 0; i < str.length; i++) 
		{
			check(Object_Text, Operation_checkExist, str[i]);
		}
	}
	/**
	 * 进入高级设置，检测彩信送达报告默认状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_116() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_ResIdInstance, Operation_CheckedFalse,"android:id/switchWidget","2");
	}
	/**
	 * 进入高级设置，点击彩信送达报告开关，开关被打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_117() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","2")) {
			excute(Object_Text, Operation_ClickWait, "彩信送达报告");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget","2");
		//清场
		excute(Object_Text, Operation_ClickWait, "彩信送达报告");
	}
	/**
	 * 进入高级设置，允许返回彩信送达报告默认开关状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_118() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_TextScroll, Operation_CheckedFalse, "允许返回彩信送达报告", "vertical");
	}	
	/**
	 * 进入高级设置，点击允许返回彩信送达报告开关，开关被打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_119() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_TextScroll, Operation_Exists, "允许返回彩信送达报告", "vertical");
		Rect textArea = (Rect) excute(Object_Text, Operation_GetBounds, "允许返回彩信送达报告");
        int i = 0;
        do{
            Rect switchButton = (Rect) excute(Object_ResIdInstance, Operation_GetBounds, "android:id/switchWidget",Integer.toString(i));
            if(Math.abs(textArea.centerY() - switchButton.centerY()) <= 50)
                break;
            i++;
        }
        while(true);
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", Integer.toString(i))) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "允许返回彩信送达报告", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", Integer.toString(i));
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "允许返回彩信送达报告", "vertical");
	}
	/**
	 * 进入高级设置，彩信阅读报告默认开关状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_120() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_TextScroll, Operation_CheckedFalse, "彩信阅读报告", "vertical");
	}	
	/**
	 * 进入高级设置，点击彩信阅读报告开关，开关被打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_121() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_TextScroll, Operation_Exists, "彩信阅读报告", "vertical");
		Rect textArea = (Rect) excute(Object_Text, Operation_GetBounds, "彩信阅读报告");
        int i = 0;
        do{
            Rect switchButton = (Rect) excute(Object_ResIdInstance, Operation_GetBounds, "android:id/switchWidget",Integer.toString(i));
            if(Math.abs(textArea.centerY() - switchButton.centerY()) <= 25)
                break;
            i++;
        }
        while(true);
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", Integer.toString(i))) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "彩信阅读报告", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", Integer.toString(i));
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "彩信阅读报告", "vertical");
	}
	/**
	 * 进入高级设置，允许返回彩信已读回执默认开关状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_122() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_TextScroll, Operation_CheckedFalse, "允许返回彩信已读回执", "vertical");
	}	
	/**
	 * 进入高级设置，点击允许返回彩信已读回执开关，开关被打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_123() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_TextScroll, Operation_Exists, "允许返回彩信已读回执", "vertical");
		Rect textArea = (Rect) excute(Object_Text, Operation_GetBounds, "允许返回彩信已读回执");
        int i = 0;
        do{
            Rect switchButton = (Rect) excute(Object_ResIdInstance, Operation_GetBounds, "android:id/switchWidget",Integer.toString(i));
            if(Math.abs(textArea.centerY() - switchButton.centerY()) <= 50)
                break;
            i++;
        }
        while(true);
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", Integer.toString(i))) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "允许返回彩信已读回执", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", Integer.toString(i));
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "允许返回彩信已读回执", "vertical");
	}
	/**
	 * 进入高级设置，短信送达情况报告默认状态
	 * @throws UiObjectNotFoundException
	 */
	public static void test_124() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		check(Object_TextScroll, Operation_CheckedFalse, "短信送达情况报告", "vertical");
	}
	/**
	 * 进入高级设置，点击短信送达情况报告开关，开关被打开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_125() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_TextScroll, Operation_Exists, "短信送达情况报告", "vertical");
		Rect textArea = (Rect) excute(Object_Text, Operation_GetBounds, "短信送达情况报告");
        int i = 0;
        do{
            Rect switchButton = (Rect) excute(Object_ResIdInstance, Operation_GetBounds, "android:id/switchWidget",Integer.toString(i));
            if(Math.abs(textArea.centerY() - switchButton.centerY()) <= 25)
                break;
            i++;
        }
        while(true);
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget", Integer.toString(i))) 
		{
			excute(Object_TextScroll, Operation_ClickWait, "短信送达情况报告", "vertical");
		}
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/switchWidget", Integer.toString(i));
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "短信送达情况报告", "vertical");
		
	}
	/**
	 * 进入高级设置，可以编辑短信中心号
	 * @throws UiObjectNotFoundException
	 */
	public static void test_126() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_TextScroll, Operation_ClickWait, "SIM SIM1 短信中心号码","vertical");
		check(Object_Text, Operation_checkExist, "管理短信中心号码");
	}
	/**
	 * 进入高级设置，点击短信信有效期
	 * @throws UiObjectNotFoundException
	 */
	public static void test_127() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterSIMSetting();
		excute(Object_TextScroll, Operation_ClickWait, "短信有效期", "vertical");
		String[] str={"不设定","1小时","12小时","24小时","2天","最大值"};
		for (int i = 0; i < str.length; i++) 
		{
			check(Object_Text, Operation_checkExist, str[i]);
		}
	}
	/**
	 * 进入常规设置，编辑签名
	 * @throws UiObjectNotFoundException
	 */
	public static void test_128() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterGeneralSetting();
		if (!(Boolean)excute(Object_ResIdInstance, Operation_IsChecked, "android:id/switchWidget","2")) {
			excute(Object_Text, Operation_ClickWait, "附加签名");
		}
		excute(Object_Text, Operation_ClickWait, "编辑签名");
		excute(Object_ResourceId, Operation_SetText, "android:id/edit","testname");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "testname");
		//清场
		excute(Object_Text, Operation_ClickWait, "编辑签名");
		excute(Object_ResourceId, Operation_SetText,"android:id/edit","");
		excute(Object_Text, Operation_ClickWait, "确定");
		excute(Object_Text, Operation_ClickWait, "附加签名");
	}
	/**
	 * 进入常规设置，点击常用短语
	 * @throws UiObjectNotFoundException
	 */
	public static void test_129() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterGeneralSetting();
		excute(Object_Text, Operation_ClickWait, "常用短语");
		check(Object_Text, Operation_checkExist, "添加");
	}
	/**
	 * 进入常规设置，进入常用短语，点击添加
	 * @throws UiObjectNotFoundException
	 */
	public static void test_130() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterGeneralSetting();
		excute(Object_Text, Operation_ClickWait, "常用短语");
		excute(Object_Text, Operation_ClickWait, "添加");
		check(Object_Text, Operation_checkExist, "添加常用短语");
	}
	/**
	 * 进入常规设置，进入常用短语，长按任意一条短语，跳出删除界面
	 * @throws UiObjectNotFoundException
	 */
	public static void test_131() throws UiObjectNotFoundException 
	{
		//主体
		MessageCommon.enterGeneralSetting();
		excute(Object_Text, Operation_ClickWait, "常用短语");
		excute(Object_ResIdInstance, Operation_LongClick, "com.android.messaging:id/text_view","0");
		check(Object_Text, Operation_checkExist, "删除");
	}
	
	
	
}
