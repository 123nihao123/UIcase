package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.*;

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

}
