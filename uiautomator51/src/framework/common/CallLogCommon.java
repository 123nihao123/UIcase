package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.database.sqlite.SQLiteDatabase;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class CallLogCommon {
	
	/**
	 * 删除指定位置的所有log
	 * @param place
	 * @throws UiObjectNotFoundException
	 */
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
	
	/**
	 * Description:添加case中所需的数据，添加case中所需的数据.3条SIM1，6条SIM2，SIM1包含今天（此条包含归属地），昨天，更早通话记录，SIM2包含已接，未接，已拨通话记录，
	 */
	public static void fillCallLogData(){
		String dbPath = "/data/data/com.android.providers.contacts/databases/contacts2.db"; 
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath); 
		DeviceCommon.deleteAllFromDatabase(database,"calls");
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,geocoded_location,subscription_component_name","'10086',"+DeviceCommon.getDate("今天")+",158,3,'"+DeviceCommon.getSIMID("SIM1")+"','江苏省南京市','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'10001',"+DeviceCommon.getDate("昨天")+",158,1,'"+DeviceCommon.getSIMID("SIM1")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'10010',1456560265000,168,2,'"+DeviceCommon.getSIMID("SIM1")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'10000',"+DeviceCommon.getDate("昨天")+",59,2,'"+DeviceCommon.getSIMID("SIM2")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'111111',"+DeviceCommon.getDate("今天")+",59,1,'"+DeviceCommon.getSIMID("SIM2")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'222222',1456560265000,59,3,'"+DeviceCommon.getSIMID("SIM2")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'333333',"+DeviceCommon.getDate("今天")+",59,2,'"+DeviceCommon.getSIMID("SIM2")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'444444',1456560265000,59,1,'"+DeviceCommon.getSIMID("SIM2")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		DeviceCommon.insertToDatabase(database,"calls","number,date,duration,type,subscription_id,subscription_component_name","'555555',"+DeviceCommon.getDate("昨天")+",59,3,'"+DeviceCommon.getSIMID("SIM2")+"','com.android.phone/com.android.services.telephony.TelephonyConnectionService'"); 
		//int total = DeviceCommon.getRecordCountInDatabase(database,"calls"); 
		//System.out.println("call log'count : " + total); 
		database.close();
		
	}
	
	/**
	 * 删除所有Call Log
	 */
	public static void deleteAllFromCallLog(){
		String dbPath = "/data/data/com.android.providers.contacts/databases/contacts2.db";
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath);
		DeviceCommon.deleteAllFromDatabase(database,"calls");
		database.close();
		
	}
	
//	/**
//	 * Description: 根据fillCallLogData()中的时间毫秒转换为以秒来计算
//	 * @param day 前天  昨天  今天
//	 * @return Beforeyesterdaytime，Yesterdaytime，Todaytime;
//	 */
//	public static long getDate(String day) 
//	{
//		if (day.equals("前天"))
//		{
//			long Beforeyesterdaytime = System.currentTimeMillis()-2*24*3600*1000;
//			System.out.println(Beforeyesterdaytime);
//			return Beforeyesterdaytime;
//		}
//		else if(day.equals("昨天"))
//		{
//			long Yesterdaytime = System.currentTimeMillis()-1*24*3600*1000;
//			System.out.println(Yesterdaytime);
//			return Yesterdaytime;
//		} 
//		else if(day.equals("今天"))
//		{
//			long Todaytime = System.currentTimeMillis()-0*24*3600*1000;
//			System.out.println(Todaytime);
//			return Todaytime;
//		}
//		return 0;
//	}
	
	/**
	 * 新建IP拨号
	 * @throws UiObjectNotFoundException
	 */
	public static void addIPCall() throws UiObjectNotFoundException{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_ClickWait,"IP 拨号设置");
		excute(Object_Text,Operation_ClickWait,"新建");
		excute(Object_ResourceId,Operation_SetText,"com.android.phone:id/ip_editor","86");
		excute(Object_Text,Operation_ClickWait,"完成");
	}
	
	/**
	 * 移除IP拨号
	 * @throws UiObjectNotFoundException
	 */
	public static void removeIPCall() throws UiObjectNotFoundException{
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_ClickWait,"IP 拨号设置");
		excute(Object_Text,Operation_ClickWait,"移除");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.phone:id/checkbox_select_all");
		excute(Object_Text,Operation_ClickWait,"完成");
	}
}
