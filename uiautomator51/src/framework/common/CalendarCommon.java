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

}