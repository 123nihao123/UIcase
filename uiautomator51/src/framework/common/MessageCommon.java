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

import android.database.Cursor;
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
		MessageCommon.switchView("消息视图");
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
	 * @param optionName 取值范围：添加到联系人，呼叫，呼叫前编辑，删除
	 */
	public static void longclickmessage(String Name)
	{
		excute(Object_ResourceId, Operation_LongClick, "com.android.mmsfolderview:id/conversation_name");
		excute(Object_Text, Operation_ClickWait, Name);
	}
     /**
      * 进入各个信箱
      * @param boxName：收件箱、发件箱、已发送、草稿箱
      */
	public static void enterMessageBox(String boxName)
	{
		switchView("文件夹视图");
		if (!(Boolean)excute(Object_Text,Operation_Exists,boxName))
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.mmsfolderview:id/actionbar_spinner");
			excute(Object_Text,Operation_ClickWait,boxName);
		}
	}
	/**
	 * 添加短信号码和内容
	 */
	public static void newMessageWithNumAndContent(String Num ,String Content)
	{
		MessageCommon.switchView("消息视图");
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
			MessageCommon.enterMessageBox("收件箱");
		}
		else if(BoxName.equals("已发送"))
		{
			MessageCommon.enterMessageBox("已发送");
		}
		else if(BoxName.equals("发件箱"))
		{
			MessageCommon.enterMessageBox("发件箱");
		}
		else if(BoxName.equals("草稿箱"))
		{
			MessageCommon.deleteAllMessageIn("草稿箱");
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
			MessageCommon.enterMessageBox(Menu[i]);
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
		String strReturn= DeviceCommon.extractField(info,"\\d\\d\\d\\d/\\d+/\\d+\\s\\d\\d:\\d\\d");
		//System.out.println("FileTime is"+strReturn);
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
		//System.out.println("Format To times:"+date.getTime());
		return date.getTime();
	}
	
	public static  void cancelPrompt (String switchbutton) throws UiObjectNotFoundException
	{
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "应用", "vertical");
		excute(Object_TextScroll, Operation_ClickWait, "信息", "vertical");
		excute(Object_TextScroll, Operation_ClickWait, "通知", "vertical");
		String txt = (String) excute(Object_ResIdInstance, Operation_GetText, "android:id/switchWidget","1");
		if (!txt.equals(switchbutton))
		{
			excute(Object_ResIdInstance, Operation_ClickWait, "android:id/switchWidget","1");
		}
		DeviceCommon.enterApp( Devices_Desc_Message);
	}

	/**
	 * 删除所有表的数据
	 */
	public static void delAllFromDB()
	{
		String dbName= "/data/data/com.android.providers.telephony/databases/mmssms.db";
		SQLiteDatabase db = DeviceCommon.openDatabase(dbName);
		String tableName;
		//String sql1 = "SELECT name FROM sqlite_master WHERE type='table'";
		//String sql ="DELETE FROM tableName";
		String sql;

		Cursor cursor = db.query("sqlite_master",new String[]{"name"},"type=?",new String[]{"table"},null,null,null,null);
		while (cursor.moveToNext()) {
			tableName = cursor.getString(0);
			//System.out.println("tableName is: "+ tableName );
			sql = "DELETE FROM "+tableName;
			System.out.println("SQL is: "+ sql);
			db.execSQL(sql);
		}
		cursor.close();
		DeviceCommon.closeDatabase(db);
	}

	/**
	 * 填充测试数据
	 */
	public static void fillSMSDB()
	{
		String [] numList= {"+8610086","+8618914760001","+8618914760002","+8618914760003","+8618914760004","+8618914760005","+8618914760006"};
		//1-SIM1;2-SIM2
		String [] simIDList= {"1","1","2","2","2","2","1"};
		//1-收件箱；2-已发；5-发件箱；
		String [] typeList = {"1","1","1","1","2","5","5"};
		String [] recipIDs=new String[numList.length];
		String dbName= "/data/data/com.android.providers.telephony/databases/mmssms.db";
		SQLiteDatabase db = DeviceCommon.openDatabase(dbName);
		for (String num : numList)
		{
			DeviceCommon.insertToDatabase(db, "canonical_addresses", "address", "'"+num+"'");
		}
		int i=0,j=0;
		Cursor cursor;
		long addrID,threadID;
		for(i=0;i<numList.length;i++)
		{
			cursor = db.query("canonical_addresses",new String[]{"_id"},"address=?",new String[]{numList[i]},null,null,null,null);
			while (cursor.moveToNext()) {
				addrID = cursor.getLong(0);
				System.out.println("addrID is: "+ addrID );
				DeviceCommon.insertToDatabase(db, "threads", "recipient_ids", String.valueOf(addrID));
				recipIDs[j++]= String.valueOf(addrID);
			}
			cursor.close();
		}
		long now = System.currentTimeMillis();
		long time;
		String msg;
		for(i=0;i<numList.length;i++)
		{
			System.out.println("recipIDs: "+ recipIDs[i] );
			cursor = db.query("threads",new String[]{"_id"},"recipient_ids=?",new String[]{recipIDs[i]},null,null,null,null);
			while (cursor.moveToNext()) {
				threadID = cursor.getLong(0);
				System.out.println("threadID is: "+ threadID );
				time = now - i*1000*60*60L;
				msg = "testsms"+i;
			//	DeviceCommon.insertToDatabase(db, "sms", "address,date,date_sent,type,body,sub_id,service_center,creator,thread_id", numList[i]+"," + time + "," + time+",1,'test2',2,'+8613800510541','com.android.messaging',"+String.valueOf(threadID));
				DeviceCommon.insertToDatabase(db, "sms", "address,date,date_sent,body,type,sub_id,service_center,creator,read,thread_id", "'"+numList[i]+"'," + time + "," + time+",'"+msg+ "'," +typeList[i]+","+simIDList[i]+",'+8613800510541','com.android.messaging','1',"+String.valueOf(threadID));
			}
			cursor.close();
		}
		DeviceCommon.closeDatabase(db);
	}

}
