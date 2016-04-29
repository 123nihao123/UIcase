package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.check;
import static framework.excute.Excute.excute;

public class BrowserCommon 
{
	/**
	 * 设置菜单
	 * @param menu
	 */
	
	public static void settingMenu(String menu)
	{
		excute(Object_Description, Operation_WaitForExists, "更多选项", "10000");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_TextScroll, Operation_ClickWait, "设置", "vertical");
		excute(Object_Text, Operation_ClickWait, menu);
	}
	/**
	 * 隐私与安全菜单
	 * @param menu
	 */
	public static void PrivacySecurity(String menu)
	{
		excute(Object_Description, Operation_WaitForExists, "更多选项", "10000");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_TextScroll, Operation_ClickWait, "设置", "vertical");
		excute(Object_Text, Operation_ClickWait, "隐私和安全");
		excute(Object_Text, Operation_ClickWait, menu);
		excute(Object_Text, Operation_ClickWait, "取消");
		check(Object_Text, Operation_EnabledTrue, menu);
		excute(Object_Text, Operation_ClickWait, menu);
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_EnabledFalse, menu);
	}
}