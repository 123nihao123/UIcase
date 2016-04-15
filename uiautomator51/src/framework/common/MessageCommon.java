package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 消息视图长按消息操作
	 * @param optionName 取值范围：转到上一层级，归档，删除，关闭通知，开启通知，添加到通讯录，屏蔽
	 */
	public static void Longclickmessage(String optionName)
	{
		excute(Object_ResourceId, Operation_LongClick, "com.android.messaging:id/conversation_name");
		excute(Object_Description, Operation_ClickWait, optionName);
	}
	/**
	 * 文件夹视图长按消息操作
	 * @param optionName 取值范围：转到上一层级，归档，删除，关闭通知，开启通知，添加到通讯录，屏蔽
	 */
	public static void longclickmessage(String Name)
	{
		excute(Object_ResourceId, Operation_LongClick, "com.android.mmsfolderview:id/conversation_name");
		excute(Object_Text, Operation_ClickWait, Name);
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
		excute(Object_Text,Operation_ClickWait,"发件箱");
	}
	/**
	 * 进入发件箱文件夹
	 */
	public static void enterSent()
	{
		switchView("文件夹视图");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.mmsfolderview:id/actionbar_spinner");
		excute(Object_Text,Operation_ClickWait,"已发送");
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
	/**
	 * 删除某个文件夹下的所有消息
	 * @param BoxName 取值范围：收件箱、已发送、发件箱、草稿箱
	 */
	public static  void deleteAllMessageIn(String BoxName)
	{
		if(BoxName.equals("收件箱"))
		{
			MessageCommon.enterInbox();
		}
		else if(BoxName.equals("已发送"))
		{
			MessageCommon.enterSent();
		}
		else if(BoxName.equals("发件箱"))
		{
			MessageCommon.enterOutBox();
		}
		else if(BoxName.equals("草稿箱"))
		{
			MessageCommon.enterDrafts();
		}
		if((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.mmsfolderview:id/conversation_snippet"))
		{
			excute(Object_Device,Operation_PressMenu);
			excute(Object_Text,Operation_ClickWait,"删除信息");
			excute(Object_Text,Operation_ClickWait,"全选");
			excute(Object_Text,Operation_ClickWait,"删除");
			excute(Object_Text,Operation_ClickWait,"删除");
		}
	}
	/**
	 * 删除全部信息
	 */
	public static  void deleteAllMessage()
	{
		String Menu[] = {"收件箱", "已发送", "发件箱", "草稿箱"};
		for (int i =0;i<Menu.length; i++)
		{
			MessageCommon.Menuoption(Menu[i]);
			excute(Object_Device, Operation_PressMenu);
			excute(Object_Text, Operation_ClickWait, "删除信息");
			if ((Boolean)excute(Object_Text, Operation_Exists, "全选"))
			{
				excute(Object_Text, Operation_ClickWait, "全选");
				excute(Object_Text, Operation_ClickWait, "删除");
				excute(Object_Text, Operation_ClickWait, "删除");
			}else{
				excute(Object_Device, Operation_PressBack);
			}
		}
	}
	public static String extractFileTime(String info)
	{
		//System.out.println(info);
		String strReturn= extractField(info,"\\d\\d\\d\\d/\\d/\\d\\d\\s\\d\\d:\\d\\d");
		//System.out.println("FileTime is"+strReturn);
		return strReturn;
	}
	public static String extractField(String info, String ptn)
	{
		String strReturn="";
		Pattern p = Pattern.compile(ptn);
		Matcher m = p.matcher(info);
		while (m.find())
		{
			strReturn = m.group();
			//System.out.println("in while strReturn: " +strReturn);
		}
		return strReturn;
	}
	/**
	 * 判断是否按文件时间排好序。缺省为升序。
	 * @param strArray
	 * @param isReverse - true:降序排列  false:升序排列，缺省为升序
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSortedByTime(String[] strArray,boolean isReverse) throws ParseException
	{
		boolean valReturn =true;
		float timeArray[]= new float[strArray.length];

		if (strArray.length==1) return valReturn;

		for(int i=0;i<strArray.length;i++)
		{
			timeArray[i] = stringToTime(extractFileTime(strArray[i]));
		}

		for(float t : timeArray) {
		System.out.println(t);
		}

		if(isReverse)
		{
			//降序
			for(int i=0;i<timeArray.length-1;i++)
			{
				if(timeArray[i]<timeArray[i+1])
				{
					valReturn = false;
					break;
				}
			}
		}
		else
		{
			//升序
			for(int i=0;i<timeArray.length-1;i++)
			{
				if(timeArray[i]>timeArray[i+1])
				{
					valReturn = false;
					break;
				}
			}
		}
		return valReturn;
	}
	/**
	 * 由格式如"1970-01-06 11:45:55"的字符串获得时间
	 * @param strTime - 格式如"1970-01-06 11:45:55"
	 * @return
	 * @throws ParseException
	 */
	public static long stringToTime(String strTime) throws ParseException
	{
		SimpleDateFormat format =  new SimpleDateFormat("yyyy/MM/dd HH:mm");        
		Date date = format.parse(strTime);
		//System.out.print("Format To times:"+date.getTime());
		return date.getTime();
	}
}
