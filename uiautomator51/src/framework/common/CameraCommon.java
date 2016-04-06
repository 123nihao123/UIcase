package framework.common;

import android.graphics.Rect;

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
	/**
	 * 切换前后摄像头
	 * @param Camera 取值范围：后置摄像头，前置摄像头
	 */
	public static void switchFrontBackCamera(String Camera)
	{
		String CameraDesc = (String) excute(Object_ResourceId, Operation_GetDesc, "com.android.camera2:id/camera_toggle_button");
		if (!CameraDesc.equals(Camera))
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/camera_toggle_button");
		}
	}
	/**
	 * 点击相机功能
	 * @param function 相机功能：倒计时，网格线，HDR，闪光灯
	 * @param Num 点击次数
	 */
	public static void Camerafunction(String function, int Num)
	{
		for (int i = 0;i<Num;i++)
		{
			if (function.equals("倒计时"))
				excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/countdown_toggle_button");
			else if(function.equals("网格线"))
				excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/grid_lines_toggle_button");
			else if(function.equals("HDR"))
				excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/hdr_plus_toggle_button");
			else if(function.equals("闪光灯"))
				excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/flash_toggle_button");
		}
	}
	/**
	 * 开关网格线
	 * @param status 网格状态：网格线已关闭，网格线已开启
	 */
	public static void switchGridlines(String status)
	{
		String GridDesc = (String) excute(Object_ResourceId, Operation_GetDesc, "com.android.camera2:id/grid_lines_toggle_button");
		if (!GridDesc.equals(status))
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/grid_lines_toggle_button");
		}
	}
	/**
	 * 开关HDR
	 * @param status HDR状况： HDR已关闭，HDR已开启
	 */
	public static void switchHDR(String status)
	{
		String HDRDesc = (String) excute(Object_ResourceId, Operation_GetDesc, "com.android.camera2:id/hdr_plus_toggle_button");
		if (!HDRDesc.equals(status))
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/hdr_plus_toggle_button");
		}
	}
	/**
	 * 点击倒计时器
	 * @param status 倒计时器状态：倒计时器已关闭, 倒计时器的时长已设为3秒, 倒计时器的时长已设为10秒
	 */
	public static void setCountdown(String status)
	{
		String statu[] = {"倒计时器已关闭", "倒计时器的时长已设为3秒", "倒计时器的时长已设为10秒"};
		for(int i=0; i<statu.length; i++)
		{
			if (status.equals(statu[i]))
			{
				do{
					String countdown = (String) excute(Object_ResourceId, Operation_GetDesc, "com.android.camera2:id/countdown_toggle_button");
					if(countdown.equals(status))
						break;
					excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/countdown_toggle_button");
				}
				while(true);
			}
		}
	}
	/**
	 * 设置闪光灯
	 * @param status 取值范围：自动闪光, 闪光灯已打开, 闪光灯已关闭
	 */
	public static void setFlash(String status)
	{
		String statu[] = {"自动闪光", "闪光灯已打开", "闪光灯已关闭"};
		for(int i=0; i<statu.length; i++)
		{
			if (status.equals(statu[i]))
			{
				do{
					String countdown = (String) excute(Object_ResourceId, Operation_GetDesc, "com.android.camera2:id/flash_toggle_button");
					if(countdown.equals(status))
						break;
					excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/flash_toggle_button");
				}
				while(true);
			}
		}
	}	
}
