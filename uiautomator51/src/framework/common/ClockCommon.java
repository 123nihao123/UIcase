package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.excute;

public class ClockCommon
{
	/**
	 * 切换模式
	 * @param name 取值范围：闹钟，时钟，计时器，秒表
	 */
	public static void switchMode(String name)
	{
		excute(Object_Description,Operation_ClickWait,name);
	}
	/**
	 * 进入一个闹钟的展开设置界面
	 */
	public static void enterAlarmSettings()
	{
		if (!(Boolean)excute(Object_Description,Operation_Exists,"添加闹钟"))
			ClockCommon.switchMode("闹钟");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.deskclock:id/arrow","0");
	}
	/**
	 * 进入设置重复时间界面
	 */
	public static void enterRepeatSettings()
	{
		ClockCommon.enterAlarmSettings();
		excute(Object_Text,Operation_ClickWait,"重复");
        excute(Object_Text,Operation_ClickWait,"重复");
	}

}
