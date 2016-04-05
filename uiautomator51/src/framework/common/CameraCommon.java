package framework.common;

import com.android.uiautomator.core.UiObjectNotFoundException;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.excute.Excute.*;
import framework.common.DeviceCommon;

public class CameraCommon 
{
	/**
	 * 切换模式
	 * @throws UiObjectNotFoundException 
	 */
	public static void switchMode(String ModeName) throws UiObjectNotFoundException
	{
		//excute(Object_ResourceId,Operation_WaitForExists,"com.android.camera2:id/shutter_button","10000");
		//使用WaitForExists时，全景和动画模式会出错，现使用Wait()
		Wait(5000);
		DeviceCommon.swipe("Right",10, 1);
		excute(Object_Text,Operation_ClickWait,ModeName);
	}
	/**
	 * 进入设置界面
	 * @throws UiObjectNotFoundException 
	 */
	public static void enterMainSetting() throws UiObjectNotFoundException
	{
		//excute(Object_ResourceId,Operation_WaitForExists,"com.android.camera2:id/shutter_button","10000");
		Wait(5000);
		DeviceCommon.swipe("Right",10, 1);
		if((Boolean)excute(Object_Description,Operation_Exists,"设置"))
		{
			excute(Object_Description,Operation_ClickWait,"设置");
		}
		else
		{
			excute(Object_Text,Operation_ClickWait,"相机");
			excute(Object_ResourceId,Operation_WaitForExists,"com.android.camera2:id/shutter_button","10000");
			DeviceCommon.swipe("Right", 10, 1);
			excute(Object_Description,Operation_ClickWait,"设置");
		}
			
	}
	/**
	 * 进入分辨率和画质
	 * @throws UiObjectNotFoundException 
	 */
	public static void enterResQaSubSetting() throws UiObjectNotFoundException
	{
		enterMainSetting();
		excute(Object_Text,Operation_ClickWait,"分辨率和画质");
	}
	/**
	 * 进入高级设置
	 * @throws UiObjectNotFoundException 
	 */
	public static void enterAdvSubSetting() throws UiObjectNotFoundException
	{
		enterMainSetting();
		excute(Object_Text,Operation_ClickWait,"高级");
	}
	/**
	 * check数组中text元素存在
	 */
	public static void checkForExist(String[] name)
	{
		for(int i=0;i<name.length;i++)
		{
			check(Object_Text,Operation_checkExist,name[i]);
		}
	}

}
