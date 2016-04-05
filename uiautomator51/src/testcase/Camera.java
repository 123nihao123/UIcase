package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CameraCommon;
import framework.common.DeviceCommon;

public class Camera extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp( Devices_Text_Camera);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 进入相机
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
	}
	/**
	 * 向右滑动
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_002() throws UiObjectNotFoundException
	{
		//主体
		Wait(5000);
		DeviceCommon.swipe("Right",10, 1);
		String[] mode={"全景","相机","视频","动画"};
		CameraCommon.checkForExist(mode);
	}
	/**
	 * 进入全景模式
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_003() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("全景");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/pano_preview_textureview");
	}
	/**
	 * 进入相机模式
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_004() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/btn_beauty_button");
	}
	/**
	 * 进入视频模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_005() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/face_view");
	}
	/**
	 * 进入动画模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_006() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("动画");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/gif_progress_text");
	}
	/**
	 * 进入设置界面
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_007() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterMainSetting();
		check(Object_Text,Operation_checkExist,"设置");
	}
	/**
	 * 进入分辨率和画质
	 */
	public static void test_008() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterResQaSubSetting();
		check(Object_Text,Operation_checkExist,"后置摄像头照片");
	}
	/**
	 * 点击后置摄像头照片
	 */
	public static void test_010() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterResQaSubSetting();
		excute(Object_Text,Operation_ClickWait,"后置摄像头照片");
		String[] str={"(4:3) 5.0百万像素","(4:3) 3.1百万像素","(4:3) 1.9百万像素","(16:9) 2.1百万像素","(16:9) 0.9百万像素"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		excute(Object_Text,Operation_ClickWait,"后置摄像头照片");
		excute(Object_Text,Operation_ClickWait,str[0]);
	}
	/**
	 * 点击前置摄像头照片
	 */
	public static void test_011() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterResQaSubSetting();
		excute(Object_Text,Operation_ClickWait,"前置摄像头照片");
		String[] str={"(4:3) 1.9百万像素","(4:3) 0.3百万像素","(4:3) 0.1百万像素","(16:9) 0.9百万像素"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		excute(Object_Text,Operation_ClickWait,"前置摄像头照片");
		excute(Object_Text,Operation_ClickWait,str[0]);
	}
	/**
	 * 点击 “后置摄像头视频”
	 */
	public static void test_012() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterResQaSubSetting();
		excute(Object_Text,Operation_ClickWait,"后置摄像头视频");
		String[] str={"HD 720p","SD 480p","CIF"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		excute(Object_Text,Operation_ClickWait,"后置摄像头视频");
		excute(Object_Text,Operation_ClickWait,str[0]);
	}
	/**
	 * 点击 “前置摄像头视频”
	 */
	public static void test_013() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterResQaSubSetting();
		excute(Object_Text,Operation_ClickWait,"前置摄像头视频");
		String[] str={"SD 480p","CIF","QCIF"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		excute(Object_Text,Operation_ClickWait,"前置摄像头视频");
		excute(Object_Text,Operation_ClickWait,str[0]);
	}

}
