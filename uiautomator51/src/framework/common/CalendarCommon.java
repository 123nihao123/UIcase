package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.excute;

public class CalendarCommon 
{
	/**
	 * 切换模式
	 * @param name 取值范围：日，周，月，日程
	 */
	public static void switchMode(String name)
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calendar:id/top_button_date");
		excute(Object_Text,Operation_ClickWait,name);
	}
	/**
	 * 进入新建日程界面
	 */
	public static void createEvent()
	{
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "新建活动");
	}
	
	/**
	 * 进入设置界面
	 */
	public static void enterSettings(String name)
	{
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_ClickWait,name);
	}
	/**
	 * 重复
	 * @param name 取值范围：每天重复，每月重复，每周重复，每年重复
	 */
	public static void repeat(String name)
	{
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "新建活动");
		excute(Object_TextScroll, Operation_Exists, "重复", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/rrule");
		if(!(boolean) excute(Object_ResourceId, Operation_IsChecked, "com.android.calendar:id/repeat_switch"))
			excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeat_switch");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/spinner_item");
		excute(Object_Text, Operation_ClickWait, name);
	}
}
