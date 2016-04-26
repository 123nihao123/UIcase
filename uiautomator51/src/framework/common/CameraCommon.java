package framework.common;

import com.android.uiautomator.core.UiDevice;
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
		if (ModeName.equals("全景"))
		{
			if (isInPanorama())
			{
				return;
			}
		}
		else if (ModeName.equals("相机"))
		{
			if (isInCamera())
			{
				return;
			}
		}
		else if (ModeName.equals("视频"))
		{
			if (isInVideo())
			{
				return;
			}
		}
		else if (ModeName.equals("动画"))
		{
			if (isInGif())
			{
				return;
			}
		}
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
    /**
	 * 判断是否在全景模式
	 */
	public static boolean isInPanorama()
	{
		if ((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/pano_preview_textureview"))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	/**
	 * 判断是否在相机模式
	 */
	public static boolean isInCamera()
	{
		if ((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/btn_beauty_button"))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	/**
	 * 判断是否在视频模式
	 */
	public static boolean isInVideo()
	{
		if ((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/face_view")
				&& (!(Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/pano_preview_textureview"))
				&& (!(Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/btn_beauty_button"))
				&& (!(Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/gif_progress_text")))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	/**
	 * 判断是否在动画模式
	 */
	public static boolean isInGif()
	{
		if ((Boolean)excute(Object_ResourceId,Operation_Exists,"com.android.camera2:id/gif_progress_text"))
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	/**
	 * 还原拍照默认设置
	 */
	public static void restoreCameraSetting() throws UiObjectNotFoundException
	{
		excute(Object_TextScroll,Operation_ClickWait,"还原拍照默认设置","vertical");	
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 还原视频默认设置
	 */
	public static void restoreVideoSetting() throws UiObjectNotFoundException
	{
		excute(Object_TextScroll,Operation_ClickWait,"还原视频默认设置","vertical");	
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 * 还原动画默认设置
	 */
	public static void restoreGifSetting() throws UiObjectNotFoundException
	{
		excute(Object_TextScroll,Operation_ClickWait,"还原动画默认设置","vertical");	
		excute(Object_Text,Operation_ClickWait,"确定");
	}

	/**
	 * 获得/sdcard/DCIM/Camera下通过摄像，拍照，动画产生的文件个数，
	 * @param type - "摄像"，"拍照"，"动画"
	 * @return
	 */
	public static int getMediaCount(String type)
	{
		if(type.equals("摄像"))
		{
			return DeviceCommon.getFileCount("/storage/sdcard0/DCIM/Camera", "mp4");
		}
		else if(type.equals("拍照"))
		{
			return DeviceCommon.getFileCount("/storage/sdcard0/DCIM/Camera", "jpg");
		}
		else if(type.equals("动画"))
		{
			return DeviceCommon.getFileCount("/storage/sdcard0/DCIM/Camera", "gif");
		}
		return 0;
	}
	/**
	 * 根据屏幕大小判断使用哪个相机像素数组
	 */
	public static String[] chooseCameraPixelByScreenSize()
	{
	    String[] str;
		String[] str1={"(4:3) 5.0百万像素","(4:3) 3.1百万像素","(4:3) 1.9百万像素","(16:9) 2.1百万像素","(16:9) 0.9百万像素"};
		String[] str2={"(4:3) 8.0百万像素","(4:3) 5.0百万像素","(4:3) 3.1百万像素","(16:9) 7.2百万像素","(16:9) 2.1百万像素","(16:9) 0.9百万像素"};
		int hight=UiDevice.getInstance().getDisplayHeight();
		int width=UiDevice.getInstance().getDisplayWidth();
		if(hight==854&&width==480)
	    {
	    	str=str1;
	    }
		else
		{
			str=str2;
		}
		return str;
	}
	/**
	 * 根据屏幕大小判断使用哪个视频像素数组
	 */
	public static String[] chooseVideoPixelByScreenSize()
	{
	    String[] str;
	    String[] str1={"HD 720p","SD 480p","CIF"};
		String[] str2={"HD 1080p","HD 720p","SD 480p"};
		int hight=UiDevice.getInstance().getDisplayHeight();
		int width=UiDevice.getInstance().getDisplayWidth();
		if(hight==854&&width==480)
	    {
	    	str=str1;
	    }
		else
		{
			str=str2;
		}
		return str;
	}
}
