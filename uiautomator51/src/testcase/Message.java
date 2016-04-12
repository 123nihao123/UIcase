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

import framework.common.CameraCommon;
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
}
