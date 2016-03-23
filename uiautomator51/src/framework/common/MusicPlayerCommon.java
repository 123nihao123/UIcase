package framework.common;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.driver.AndroidObject;

public class MusicPlayerCommon 
{
	
	public static void addnewplaylist()
	{
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
	}
	public static void longclicknewplaylist(String playlistName)
	{
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
	}
	public static void addandlongclicknewplaylist()
	{
		excute(Object_Text, Operation_ClickWait, "歌曲");
		excute(Object_ResourceId, Operation_LongClick, "com.android.music:id/line1");
		excute(Object_Text, Operation_ClickWait, "添加到播放列表");
		excute(Object_Text, Operation_ClickWait, "新建播放列表");
		String playlistName = (String)excute(Object_ResourceId, Operation_GetText, "com.android.music:id/playlist");
		excute(Object_Text, Operation_ClickWait, "保存");
		excute(Object_Text, Operation_ClickWait, "播放列表");
		excute(Object_Text, Operation_LongClick, playlistName);
	}
	public static void deleteallplaylist()
	{
		int num = (Integer) excute(Object_ResourceId, Operation_GetChildCount, "android:id/list");
		for (int i=1; i<num; i++)
		{
			excute(Object_ResIdInstance, Operation_LongClick, "com.android.music:id/icon", "1");
			excute(Object_Text, Operation_ClickWait, "删除");
			excute(Object_Text, Operation_ClickWait, "确定");
		}
		
	}
	
}