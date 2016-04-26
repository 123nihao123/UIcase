package framework.common;

import static framework.data.ObjectType.Object_Description;
import static framework.data.OperationType.Operation_ClickWait;
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

}
