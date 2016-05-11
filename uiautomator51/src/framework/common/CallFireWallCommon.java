package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.*;
import android.database.sqlite.SQLiteDatabase;

public class CallFireWallCommon 
{
	/**
	 * 添加一个黑名单
	 */
	public static void addBlackContact(String name,String number,Boolean isSMS,Boolean isCalls)
	{
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"添加");
		excute(Object_Device,Operation_PressBack);
		excute(Object_ResourceId,Operation_SetText,"com.sprd.firewall:id/black_calls_add_edit_label_name",name);
		excute(Object_ResourceId,Operation_SetText,"com.sprd.firewall:id/black_calls_add_edit_label",number);
		if (isSMS)
			excute(Object_Text, Operation_ClickWait,"短信");
		if (isCalls)
			excute(Object_Text, Operation_ClickWait,"电话");
		excute(Object_Text, Operation_ClickWait,"确定");
	}
	/**
	 * Description:添加电话拦截一条记录
	 */
	public static void fillIncomingCallData(){
		String dbPath = "/data/data/com.sprd.firewall/databases/block.db"; 
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath); 
		DeviceCommon.deleteAllFromDatabase(database,"block_recorded");
		DeviceCommon.insertToDatabase(database,"block_recorded","mumber_value,block_date","'10086',"+DeviceCommon.getDate("今天")+""); 
		//int total = DeviceCommon.getRecordCountInDatabase(database,"block_recorded"); 
		//System.out.println("call log'count : " + total); 
		database.close();
	}	
	/**
	 * Description:添加短信拦截一条记录
	 */
	public static void fillSMSData(){
		String dbPath = "/data/data/com.sprd.firewall/databases/block.db"; 
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath); 
		DeviceCommon.deleteAllFromDatabase(database,"sms_block_recorded");
		DeviceCommon.insertToDatabase(database,"sms_block_recorded","mumber_value,sms_content,block_date","'10086','123',"+DeviceCommon.getDate("今天")+""); 
		//int total = DeviceCommon.getRecordCountInDatabase(database,"sms_block_recorded"); 
		//System.out.println("call log'count : " + total); 
		database.close();
	}
	
	/**
	 * Description:添加三条黑名单联系人
	 */
	public static void fillThreeBlockContact(){
		String dbPath = "/data/data/com.sprd.firewall/databases/block.db"; 
		SQLiteDatabase database = DeviceCommon.openDatabase(dbPath); 
		DeviceCommon.deleteAllFromDatabase(database,"black_mumbers");
		DeviceCommon.insertToDatabase(database,"black_mumbers","mumber_value,block_type,name","'123456','3','TestForBoth'"); 
		DeviceCommon.insertToDatabase(database,"black_mumbers","mumber_value,block_type,name","'1234567','2','TestForTele'"); 
		DeviceCommon.insertToDatabase(database,"black_mumbers","mumber_value,block_type,name","'1234568','1','TestForSMS'"); 
		//int total = DeviceCommon.getRecordCountInDatabase(database,"sms_block_recorded"); 
		//System.out.println("call log'count : " + total); 
		database.close();
	}
	
	/**
	 * 批量删除:选择模块：黑名单，电话记录，短信记录
	 */
	public static void BatchDelete(String type){
		excute(Object_Text, Operation_ClickWait, type);
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "批量删除");
		if(type.equals("电话记录")){
			excute(Object_ResourceId, Operation_ClickWait, "com.sprd.firewall:id/Log_selete_all");
		}else if(type.equals("短信记录")){
			excute(Object_ResourceId, Operation_ClickWait, "com.sprd.firewall:id/Sms_Log_selete_all");
		}
		else if(type.equals("黑名单")){
			excute(Object_ResourceId, Operation_ClickWait, "com.sprd.firewall:id/selete_all");
		}	
		excute(Object_Text, Operation_ClickWait, "完成");
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 * 通过长按编辑一条记录查看拦截方式
	 * @param name 姓名
	 * @param isSMS 是否短信拦截
	 * @param isCall 是否电话拦截
	 */
	public static void checkInterceptionType(String name,Boolean isSMS,Boolean isCall)
	{
		excute(Object_Text,Operation_LongClick,name);
		excute(Object_Text, Operation_ClickWait, "编辑");
		excute(Object_Device,Operation_PressBack);
		if(isSMS && isCall)
		{
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.sprd.firewall:id/type_checkbox","0");
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.sprd.firewall:id/type_checkbox","1");
		}
		else if(isSMS && !isCall)
		{
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.sprd.firewall:id/type_checkbox","0");
			check(Object_ResIdInstance,Operation_CheckedFalse,"com.sprd.firewall:id/type_checkbox","1");
		}
		else if(!isSMS && isCall)
		{
			check(Object_ResIdInstance,Operation_CheckedFalse,"com.sprd.firewall:id/type_checkbox","0");
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.sprd.firewall:id/type_checkbox","1");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
