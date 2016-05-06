package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.BrowserCommon;
import framework.common.DeviceCommon;
import framework.common.VideoPlayerCommon;

public class VideoPlayer extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_VideoPlayer);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 点击播放视频
	 */
	public static void test_052()
	{
		//前提
//		VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideo();
		check(Object_ResourceId, Operation_checkExist, "android:id/action_bar_title");
		check(Object_Description, Operation_checkExist, "分享方式");
		check(Object_Description, Operation_checkExist, "更多选项");
		check(Object_Description, Operation_checkExist, "视频播放器时间栏");
	}
	/**
	 * 暂停播放功能
	 */
	public static void test_054()
	{
		//前提
//		VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideo();
		Rect ModArea = (Rect) excute(Object_Description, Operation_GetBounds, "视频播放器时间栏");
		int x = ModArea.centerX();
		int y = ModArea.top;
		UiDevice.getInstance().click(x , y-10);
	}
	/**
	 * 分享
	 */
	public static void test_056()
	{
		//前提
//		VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideo();
		excute(Object_Description, Operation_GetBounds, "分享方式");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "蓝牙");
		check(Object_Text, Operation_checkExist, "电子邮件");
	}
}
