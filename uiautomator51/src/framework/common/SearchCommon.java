package framework.common;

import static framework.data.ObjectType.Object_Device;
import static framework.data.ObjectType.Object_ResIdInstance;
import static framework.data.ObjectType.Object_ResourceId;
import static framework.data.ObjectType.Object_Text;
import static framework.data.OperationType.Operation_ClickWait;
import static framework.data.OperationType.Operation_GetChildCount;
import static framework.data.OperationType.Operation_IsChecked;
import static framework.data.OperationType.Operation_PressBack;
import static framework.data.OperationType.Operation_SetText;
import static framework.excute.Excute.excute;

public class SearchCommon 
{
	/**
	 * 进入搜索设置项
	 * @param name 取值范围：可搜索项,默认搜索引擎,清除快捷方式
	 */
	public static void enterSearchSettings(String name)
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_indicator");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_edit_items");
		excute(Object_Text,Operation_ClickWait,name);
	}
	/**
	 * 选择需要的搜索项
	 */
	public static void chooseSearchItem(String[] name)
	{
		SearchCommon.enterSearchSettings("可搜索项");
		int num1=(int)excute(Object_ResourceId,Operation_GetChildCount,"android:id/list");
		for(int i=0;i<num1;i++)
		{
			if((Boolean)excute(Object_ResIdInstance,Operation_IsChecked,"android:id/checkbox",String.valueOf(i)))
				excute(Object_ResIdInstance,Operation_ClickWait,"android:id/checkbox",String.valueOf(i));
		}
		for(int i=0;i<name.length;i++)
		{
			excute(Object_Text,Operation_ClickWait,name[i]);
		}
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
	}
	/**
	 * 选择搜索引擎
	 */
	public static void setSearchEngine(String engine)
	{
		SearchCommon.enterSearchSettings("默认搜索引擎");
		excute(Object_Text,Operation_ClickWait,engine);
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		excute(Object_ResourceId,Operation_SetText,"com.android.quicksearchbox:id/search_src_text","12345678");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/search_go_btn");
	}
	/**
	 * 选择搜索范围
	 */
	public static void searchRange(String name)
	{
		excute(Object_ResourceId,Operation_ClickWait,"com.android.quicksearchbox:id/corpus_indicator");
		excute(Object_Text,Operation_ClickWait,name);
	}

}
