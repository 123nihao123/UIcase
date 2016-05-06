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
		//VideoPlayerCommon.deleteAllVideo();
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 无视频资源
	 */
	public static void test_101()
	{
		//前提
		VideoPlayerCommon.deleteAllVideo();
		//主体
		check(Object_Text,Operation_checkExist,"相机");
		//清场
		VideoPlayerCommon.addVideo(1);
	}
	/**
	 * 页面上有相关视频
	 */
	public static void test_002()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		check(Object_Text,Operation_checkNoExist,"相机");	
	}
	/**
	 * 选择视频模式
	 */
	public static void test_003()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"视频");
		check(Object_Text,Operation_checkExist,"视频");
	}
	/**
	 * 选择地点模式
	 */
	public static void test_004()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"地点");
		check(Object_Text,Operation_checkExist,"地点");
	}
	/**
	 * 选择时间模式
	 */
	public static void test_005()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"时间");
		check(Object_Text,Operation_checkExist,"时间");
	}
	/**
	 * 选择人物模式
	 */
	public static void test_006()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"人物");
		check(Object_Text,Operation_checkExist,"人物");
	}
	/**
	 * 选择标签模式
	 */
	public static void test_007()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"标签");
		check(Object_Text,Operation_checkExist,"标签");
	}
	/**
	 * 进入相机模式
	 */
	public static void test_008()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"切换到相机");
		check(Object_Description,Operation_checkExist,"快门");
	}

	/**
	 * 点击页面上的更多（菜单）
	 */
	public static void test_009()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text, Operation_checkExist,"选择视频");
	}
	/**
	 * 选择群组界面
	 */
	public static void test_010()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		excute(Object_Device,Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait,"选择视频");
		check(Object_Text,Operation_checkExist,"选中了 0 项");
	}
	/**
	 * 选择一个群组
	 */
	public static void test_011()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		check(Object_Text,Operation_checkExist,"选中了 1 项");
		check(Object_Description,Operation_checkExist,"分享方式");
	}
	/**
	 * 选择一个群组,点击更多选项
	 */
	public static void test_012()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		excute(Object_Description,Operation_WaitForExists,"更多选项","5000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"删除");
		check(Object_Text,Operation_checkExist,"详细信息");
	}
	/**
	 * 选择一个群组,分享功能
	 */
	public static void test_013()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		excute(Object_Description,Operation_WaitForExists,"分享方式","5000");
		if((Boolean)excute(Object_Description,Operation_Exists,"使用信息分享"))
		{
			excute(Object_Description,Operation_ClickWait,"分享方式");
			check(Object_Text,Operation_checkExist,"蓝牙");
			check(Object_Text,Operation_checkExist,"电子邮件");
		}
		else if ((Boolean)excute(Object_Description,Operation_Exists,"使用蓝牙分享"))
		{
			excute(Object_Description,Operation_ClickWait,"分享方式");
			check(Object_Text,Operation_checkExist,"信息");
			check(Object_Text,Operation_checkExist,"电子邮件");
		}
		else if((Boolean)excute(Object_Description,Operation_Exists,"使用电子邮件分享"))
		{
			excute(Object_Description,Operation_ClickWait,"分享方式");
			check(Object_Text,Operation_checkExist,"信息");
			check(Object_Text,Operation_checkExist,"蓝牙");
		}
		else
		{
			excute(Object_Description,Operation_ClickWait,"分享方式");
			check(Object_Text,Operation_checkExist,"信息");
			check(Object_Text,Operation_checkExist,"蓝牙");
			check(Object_Text,Operation_checkExist,"电子邮件");
		}
	}
	/**
	 * 选择一个群组,分享功能——蓝牙
	 */
	public static void test_014()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		VideoPlayerCommon.shareVideoGroupBy("蓝牙");
		excute(Object_Text,Operation_WaitForExists,"开启","5000");
		check(Object_Text,Operation_checkExist,"开启");
	}
	/**
	 * 选择一个群组,分享功能——信息
	 */
	public static void test_015()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		VideoPlayerCommon.shareVideoGroupBy("信息");
		excute(Object_Text,Operation_WaitForExists,"新消息","5000");
		check(Object_Text,Operation_checkExist,"新消息");
	}
	/**
	 * 选择一个群组,分享功能——电子邮件
	 */
	public static void test_016()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		VideoPlayerCommon.shareVideoGroupBy("电子邮件");
		excute(Object_Text,Operation_WaitForExists,"帐户设置","5000");
		check(Object_Text,Operation_checkExist,"帐户设置");
	}
	/**
	 * 选择一个群组,更多——删除
	 */
	public static void test_017()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		excute(Object_Description,Operation_WaitForExists,"更多选项","5000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	/**
	 * 选择一个群组,更多——删除——确定
	 */
	public static void test_018()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		excute(Object_Description,Operation_WaitForExists,"更多选项","5000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"删除");
		excute(Object_Text, Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"相机");
		//清场
		VideoPlayerCommon.addVideo(1);
	}
	/**
	 * 选择一个群组,更多——详细信息
	 */
	public static void test_020()
	{
		//前提
		//VideoPlayerCommon.addVideo(1);
		//主体
		VideoPlayerCommon.clickVideoGroup();
		excute(Object_Description,Operation_WaitForExists,"更多选项","5000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"详细信息");
		check(Object_ResourceId,Operation_checkExist,"android:id/alertTitle");
	}
	/**
	 * 点击播放视频
	 */
	public static void test_052()
	{
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
		//主体
		VideoPlayerCommon.clickVideo();
		
		Rect ModArea = (Rect) excute(Object_Description, Operation_GetBounds, "视频播放器时间栏");
		int x = ModArea.centerX();
		int y = ModArea.top;
		Wait(5000);
		check(Object_Description, Operation_checkNoExist, "播放视频");
		UiDevice.getInstance().click(x , y-10);
		UiDevice.getInstance().click(x , y-10);
		Wait(5000);
		check(Object_Description, Operation_checkExist, "播放视频");
	}
	/**
	 * 分享
	 */
	public static void test_056()
	{
		//主体
		VideoPlayerCommon.clickVideo();
		excute(Object_Description, Operation_ClickWait, "分享方式");
		check(Object_Text, Operation_checkExist, "信息");
		check(Object_Text, Operation_checkExist, "蓝牙");
		check(Object_Text, Operation_checkExist, "电子邮件");
	}
	/**
	 * 信息分享
	 */
	public static void test_058()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("分享方式", "信息");
		excute(Object_Text, Operation_WaitForExists, "新消息", "10000");
		check(Object_Text, Operation_checkExist, "新消息");
	}
	/**
	 * 信息分享
	 */
	public static void test_059()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("分享方式","蓝牙");
		if((boolean) excute(Object_Text, Operation_Exists, "开启"))
			excute(Object_Text, Operation_ClickWait, "开启");
		excute(Object_Text, Operation_WaitForExists, "选择蓝牙设备", "10000");
		check(Object_Text, Operation_checkExist, "选择蓝牙设备");
	}
	/**
	 * 信息分享
	 */
	public static void test_060()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("分享方式","电子邮件");
		excute(Object_Text, Operation_WaitForExists, "帐户设置", "10000");
		check(Object_Text, Operation_checkExist, "帐户设置");
	}
	/**
	 * 更多菜单
	 */
	public static void test_061()
	{
		//主体
		VideoPlayerCommon.clickVideo();
		Rect Area = (Rect) excute(Object_Description, Operation_GetBounds, "视频播放器时间栏");
		int x1 = Area.centerX();
		int y1 = Area.top;
		UiDevice.getInstance().click(x1 , y1-10);
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "跳转至");
		check(Object_Text, Operation_checkExist, "原始尺寸");
		check(Object_Text, Operation_checkExist, "参数设置");
		check(Object_Text, Operation_checkExist, "回放模式");
		check(Object_Text, Operation_checkExist, "声道设置");
		check(Object_Text, Operation_checkExist, "耳机");
	}
	/**
	 * 跳转至
	 */
	public static void test_062()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "跳转至");
		excute(Object_Text, Operation_WaitForExists, "搜索全部", "10000");
		check(Object_Text, Operation_checkExist, "搜索全部");
	}
	/**
	 * 原始尺寸
	 */
	public static void test_063()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "原始尺寸");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "全屏播放");
	}
	/**
	 * 全屏播放
	 */
	public static void test_064()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "原始尺寸");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "全屏播放");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "原始尺寸");
	}
	/**
	 * 参数设置
	 */
	public static void test_065()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "参数设置");
		excute(Object_Text, Operation_WaitForExists, "参数设置", "10000");
		check(Object_Text, Operation_checkExist, "参数设置");
	}
	/**
	 * 回放模式
	 */
	public static void test_066()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "回放模式");
		excute(Object_Text, Operation_WaitForExists, "回放模式", "10000");
		check(Object_Text, Operation_checkExist, "不回放");
		check(Object_Text, Operation_checkExist, "单曲回放");
		check(Object_Text, Operation_checkExist, "全部回放");
	}
	/**
	 * 不回放
	 */
	public static void test_067()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "回放模式");
		excute(Object_Text, Operation_WaitForExists, "回放模式", "10000");
		excute(Object_Text, Operation_ClickWait, "不回放");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "回放模式");
		check(Object_Text, Operation_CheckedTrue, "不回放");
	}
	/**
	 * 单曲回放
	 */
	public static void test_068()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "回放模式");
		excute(Object_Text, Operation_WaitForExists, "回放模式", "10000");
		excute(Object_Text, Operation_ClickWait, "单曲回放");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "回放模式");
		check(Object_Text, Operation_CheckedTrue, "单曲回放");
	}
	/**
	 * 全部回放
	 */
	public static void test_069()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "回放模式");
		excute(Object_Text, Operation_WaitForExists, "回放模式", "10000");
		excute(Object_Text, Operation_ClickWait, "全部回放");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "回放模式");
		check(Object_Text, Operation_CheckedTrue, "全部回放");
	}
	/**
	 * 全部回放
	 */
	public static void test_070()
	{
		//主体
		VideoPlayerCommon.Playscreenmenu("更多选项", "声道设置");
		excute(Object_Text, Operation_WaitForExists, "声道设置", "10000");
		check(Object_Text, Operation_checkExist, "声道设置");
	}
}
