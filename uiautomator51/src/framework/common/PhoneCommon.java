package framework.common;


import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.check;
import static framework.excute.Excute.excute;

import com.android.uiautomator.core.UiObjectNotFoundException;

import android.database.sqlite.SQLiteDatabase;

public class PhoneCommon{
	
	/**
	 * Description:添加case中所需的数据，添加case中所需的数据.
	 */
	public static void fillPhoneData(){
		String dbPath = "/data/data/com.android.providers.contacts/databases/contacts2.db"; 
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath); 
		DeviceCommon.deleteAllFromDatabase(database,"calls");
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,geocoded_location,subscription_component_name","'10086',"+getDate("今天")+",158,3,'"+DeviceCommon.getSIMID("SIM1")+"','江苏省南京市','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,geocoded_location,subscription_component_name","'10000',"+getDate("今天")+",158,3,'"+DeviceCommon.getSIMID("SIM1")+"','江苏省南京市','com.android.phone/com.android.services.telephony.TelephonyConnectionService'");
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,geocoded_location,subscription_component_name","'10001',"+getDate("今天")+",158,3,'"+DeviceCommon.getSIMID("SIM1")+"','江苏省南京市','com.android.phone/com.android.services.telephony.TelephonyConnectionService'");
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,geocoded_location,subscription_component_name","'10010',"+getDate("今天")+",158,3,'"+DeviceCommon.getSIMID("SIM1")+"','江苏省南京市','com.android.phone/com.android.services.telephony.TelephonyConnectionService'");
		//int total = DeviceCommon.getRecordCountInDatabase(database,"calls"); 
		//System.out.println("call log'count : " + total); 
		database.close();
		
	}
	/**
	 * Description: 根据fillCallLogData()中的时间毫秒转换为以秒来计算
	 * @param day 前天  昨天  今天
	 * @return Beforeyesterdaytime，Yesterdaytime，Todaytime;
	 */
	public static long getDate(String day) 
	{
		if (day.equals("前天"))
		{
			long Beforeyesterdaytime = System.currentTimeMillis()-2*24*3600*1000;
			System.out.println(Beforeyesterdaytime);
			return Beforeyesterdaytime;
		}
		else if(day.equals("昨天"))
		{
			long Yesterdaytime = System.currentTimeMillis()-1*24*3600*1000;
			System.out.println(Yesterdaytime);
			return Yesterdaytime;
		} 
		else if(day.equals("今天"))
		{
			long Todaytime = System.currentTimeMillis()-0*24*3600*1000;
			System.out.println(Todaytime);
			return Todaytime;
		}
		return 0;
	}
	/**
	 * 进入设置界面
	 */
	public static void enterSettings()
	{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
	}
	/**
	 * 进入SIM卡1的通话设置
	 */
	public static void enterSIM1Settings()
	{
		PhoneCommon.enterSettings();
		excute(Object_Text,Operation_ClickWait,"通话帐户");
		excute(Object_Text,Operation_ClickWait,"SIM1");
	}
	/**
	 * 搜索菜单
	 * @param menu 取值范围：设置，通话记录，新建联系人
	 */
	public static void Searchmenu(String menu) 
	{
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/dialtacts_options_menu_button");
		excute(Object_Text, Operation_ClickWait, menu);
		excute(Object_Text, Operation_WaitForExists, menu, "10000");
	}
	
	/**
	 * 拨打电话
	 * @param num 号码
	 * @param num 选择SIM卡
	 * @throws UiObjectNotFoundException 
	 */
	public static void Makecall(String num, int SIMnum) throws UiObjectNotFoundException 
	{
		Wait(1000);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/floating_action_button");
		excute(Object_ResourceId, Operation_SetText, "com.android.dialer:id/digits", num);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/dialpad_floating_action_button");
		excute(Object_Text, Operation_WaitForExists, "用于外拨电话的帐户", "30000");
		 if((Boolean) excute(Object_Text,Operation_Exists,"用于外拨电话的帐户"))
		 {
			 CallCommon.makeCallByDualcard(SIMnum);
		 }
		//主体
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.dialer:id/holdButton", "100000");
	}
	
}