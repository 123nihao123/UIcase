package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteDatabase;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class MessageCommon {
	
	
	/**
	  * 切换视图模式
	  * @param ViewName 取值范围：消息视图，文件夹视图
	  */
	 public static void switchView(String ViewName)
	 {
	  excute(Object_Device, Operation_PressMenu);
	  if ((Boolean)excute(Object_Text, Operation_Exists, ViewName)) 
	   excute(Object_Text, Operation_ClickWait, ViewName);
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
	
}