package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import android.database.sqlite.SQLiteDatabase;

import com.android.uiautomator.core.UiObjectNotFoundException;

public class CallLogCommon {
	
	public static void deleteAllLog(String place) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallLogCommon: deleteAllLog======");
		
		excute(Object_Text,Operation_ClickWait,place);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		if((Boolean) excute(Object_Text,Operation_Exists,"清除通话记录"))
		{
			excute(Object_Text,Operation_ClickWait,"清除通话记录");
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_Text,Operation_ClickWait,"全选");
			excute(Object_Description,Operation_ClickWait,"更多选项");
			excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
			excute(Object_Text,Operation_ClickWait,"确定");	
		}
		excute(Object_Device, Operation_PressBack);	
			
	}
	
	public static void deleteLog(String place, String name) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallLogCommon: deleteLog======");
		
		excute(Object_Text,Operation_ClickWait,place);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");		
		DeviceCommon.selectMore(name);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Device, Operation_PressBack);
	}
	
	public static void deleteLogWithCancel(String place, String name) throws UiObjectNotFoundException
	{
		System.out.println("======Start to excute CallLogCommon: deleteLogWithCancel======");
		
		excute(Object_Text,Operation_ClickWait,place);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");		
		DeviceCommon.selectMore(name);
		excute(Object_Device, Operation_PressBack);
	}

	public static void checkCallLog(String type) throws UiObjectNotFoundException{
		DeviceCommon.enterApp(Devices_Desc_CallLog);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,type);
	}
	
	
	/**
	 * Description:添加case中所需的数据4条数据，SIM1包含一条未接，一条已接，一条已拨，SIM卡信息，归属地 ，SIM2一条已拨记录
	 * @throws UiObjectNotFoundException
	 */
	public static void fillCallLogData() throws UiObjectNotFoundException{
		String dbPath = "/data/data/com.android.providers.contacts/databases/contacts2.db"; 
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,geocoded_location,subscription_component_name","'10086',"+getDate("今天")+",158,3,'89860060101550262647','江苏省南京市','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'10001',"+getDate("昨天")+",158,1,'89860060101550262647','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'10010',1456560265000,168,2,'89860060101550262647','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'10000',"+getDate("昨天")+",59,2,'898600f0101550115010','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'111111',"+getDate("今天")+",59,1,'898600f0101550115010','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'222222',1456560265000,59,3,'898600f0101550115010','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'333333',"+getDate("今天")+",59,2,'898600f0101550115010','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'444444',1456560265000,59,1,'898600f0101550115010','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'555555',"+getDate("昨天")+",59,3,'898600f0101550115010','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
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
	
}
