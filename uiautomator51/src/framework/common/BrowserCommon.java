package framework.common;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.*;


public class BrowserCommon 
{
	/**
	 * 进入到收藏界面的各个Tab
	 * @param name 取值范围：书签，历史记录，保存的网页
	 */
	public static void enterCollectionTab(String name)
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.browser:id/bookmarks");
		excute(Object_Text,Operation_ClickWait,name);
	}
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


