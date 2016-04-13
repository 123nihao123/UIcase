package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class MessageCommon {
	/**
	 * 切换视图模式
	 * @param ViewName 取值范围：消息视图，文件夹视图,已归档的对话,设置，无线警报
	 */
	public static void switchView(String ViewName)
	{
		excute(Object_Device, Operation_PressMenu);
		if ((Boolean)excute(Object_Text, Operation_Exists, ViewName)) 
		{
			excute(Object_Text, Operation_ClickWait, ViewName);
		}else{
			excute(Object_Device, Operation_PressBack);
		}
	}
	/**
	 * 消息模式，新建短信点击附件
	 * @param num
	 * @throws UiObjectNotFoundException
	 */
	public static void addNewMessageAttach(String num) throws UiObjectNotFoundException
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		if((Boolean)excute(Object_Text,Operation_Exists,"消息视图"))
		{
			excute(Object_Text,Operation_ClickWait,"消息视图");
		}else
		{
			excute(Object_Device, Operation_PressBack);
		}
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/start_new_conversation_button");
		excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/recipient_text_view",num);
		excute(Object_Device, Operation_PressEnter);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/recipient_text_view");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/attach_media_button");
	}
	/**
	 * 进入常规设置
	 * @throws UiObjectNotFoundException
	 */
	public static void enterGeneralSetting() throws UiObjectNotFoundException
	{
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "设置");
		excute(Object_Text, Operation_ClickWait, "常规");
	}
	/**
	 * 进入SIM卡设置
	 * @throws UiObjectNotFoundException
	 */
	public static void enterSIMSetting() throws UiObjectNotFoundException
	{
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "设置");
		excute(Object_ClassContainsText, Operation_ClickWait, "android.widget.TextView","SIM");
	}
	/**
	 * 长按消息操作
	 * @param optionName 取值范围：转到上一层级，归档，删除，关闭通知，开启通知，添加到通讯录，屏蔽,添加到联系人,呼叫,呼叫前编辑,删除
	 */
	public static void Longclickmessage(String optionName)
	{
		if ((Boolean)excute(Object_ResourceId, Operation_Exists, "com.android.mmsfolderview:id/conversation_name"))
		{
		excute(Object_ResourceId, Operation_LongClick, "com.android.mmsfolderview:id/conversation_name");
		}else{
			excute(Object_ResourceId, Operation_LongClick, "com.android.messaging:id/conversation_name");
		}
		if ((Boolean)excute(Object_Description, Operation_Exists, optionName))
		{
			excute(Object_Description, Operation_ClickWait, optionName);
		}else{
			excute(Object_Device, Operation_PressBack);
		}
	}
	/**
	 * 文件视图下拉菜单操作
	 * @param optionName 取值范围：收件箱，已发送，发件箱，草稿箱
	 */
	public static void Menuoption(String optionName)
	{
		excute(Object_ResourceId, Operation_ClickWait, "com.android.mmsfolderview:id/actionbar_spinner");
		excute(Object_Text, Operation_ClickWait, optionName);
	}
     /**
	 * 进入收件箱文件夹
	 */
	public static void enterInbox()
	{
		switchView("文件夹视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.mmsfolderview:id/actionbar_spinner");
		excute(Object_Text,Operation_ClickWait,"收件箱");
	}
	/**
	 * 进入已发送文件夹
	 */
	public static void enterOutBox()
	{
		switchView("文件夹视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.mmsfolderview:id/actionbar_spinner");
		excute(Object_Text,Operation_ClickWait,"已发送");
	}
	/**
	 * 进入发件箱文件夹
	 */
	public static void enterSent()
	{
		switchView("文件夹视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.mmsfolderview:id/actionbar_spinner");
		excute(Object_Text,Operation_ClickWait,"发件箱");
	}
	/**
	 * 进入草稿箱文件夹
	 */
	public static void enterDrafts()
	{
		switchView("文件夹视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.mmsfolderview:id/actionbar_spinner");
		excute(Object_Text,Operation_ClickWait,"草稿箱");
	}
	/**
	 * 添加短信号码和内容
	 */
	public static void newMessageWithNumAndContent(String Num ,String Content)
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		if((Boolean)excute(Object_Text,Operation_Exists,"消息视图"))
		{
			excute(Object_Text,Operation_ClickWait,"消息视图");
		}else
		{
			excute(Object_Device, Operation_PressBack);
		}
		excute(Object_ResourceId,Operation_ClickWait,"com.android.messaging:id/start_new_conversation_button");
		excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/recipient_text_view",Num);
		excute(Object_Device, Operation_PressEnter);
		excute(Object_ResourceId,Operation_SetText,"com.android.messaging:id/compose_message_text",Content);
	}
	
	/**
	 * 长按录音
	 */
	public static  void longClickAudio()
	{
		Rect bound = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.messaging:id/record_button_visual");
		int x = bound.centerX();
		int y = bound.centerY();
		UiDevice.getInstance().drag(x,y,x, y, 400);
	}
	
}
