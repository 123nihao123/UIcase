package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CameraCommon;
import framework.common.DeviceCommon;
import framework.common.SettingCommon;

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
		Assert.assertTrue(CameraCommon.isInPanorama());
	}
	/**
	 * 进入相机模式
	 * @throws UiObjectNotFoundException 
	 */
	public static void test_004() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		Assert.assertTrue(CameraCommon.isInCamera());
	}
	/**
	 * 进入视频模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_005() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		Assert.assertTrue(CameraCommon.isInVideo());
	}
	/**
	 * 进入动画模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_006() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("动画");
		Assert.assertTrue(CameraCommon.isInGif());
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
		String[] str;
		CameraCommon.enterResQaSubSetting();
		excute(Object_Text,Operation_ClickWait,"后置摄像头照片");
		str=CameraCommon.chooseCameraPixelByScreenSize();
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
		String[] str;
		CameraCommon.enterResQaSubSetting();
		excute(Object_Text,Operation_ClickWait,"后置摄像头视频");
		str=CameraCommon.chooseVideoPixelByScreenSize();
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
	/**
	 * 快门声音开关默认是开启的
	 */
	public static void test_014() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterMainSetting();
		check(Object_ResourceId,Operation_CheckedTrue,"android:id/switchWidget");
	}
	/**
	 * 点击快门声音开关
	 */
	public static void test_015() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterMainSetting();
		excute(Object_ResourceId,Operation_ClickWait,"android:id/switchWidget");
		check(Object_ResourceId,Operation_CheckedFalse,"android:id/switchWidget");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"android:id/switchWidget");
	}
	/**
	 * 点击存储路径，弹出对话框
	 */
	public static void test_016() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterMainSetting();
		excute(Object_Text,Operation_ClickWait,"存储路径");
		check(Object_Text,Operation_checkExist,"取消");
	}
	/**
	 * 进入高级设置
	 */
	public static void test_017() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		String[] str={"手动曝光","定格显示","时间戳","防闪烁","照片质量","智能检测","色彩效果","取景模式","白平衡","连续拍摄","对比度",
				"亮度","感光度","饱和度","还原拍照默认设置","编码类型","防闪烁","延时","慢录","还原视频默认设置","GIF大小","GIF帧数","还原动画默认设置"};
		for(int i=0;i<str.length;i++)
		{
			check(Object_TextScroll,Operation_checkExist,str[i],"vertical");
		}
	}
	/**
	 * 手动曝光开关默认关闭
	 */
	public static void test_018() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","0");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/switchWidget","0");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","0");  
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 * 定格显示开关默认关闭
	 */
	public static void test_019() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","1");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/switchWidget","1");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","1");  
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 * 时间戳开关默认关闭
	 */
	public static void test_020() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","2");
		excute(Object_ResIdInstance,Operation_ClickWait,"android:id/switchWidget","2");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","2");  
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 * 防闪烁
	 */
	public static void test_021() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_Text,Operation_ClickWait,"防闪烁");
		String[] str={"50Hz","60Hz"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 * 照片质量
	 */
	public static void test_022() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_Text,Operation_ClickWait,"照片质量");
		String[] str={"极精细","精细","正常"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 * 智能检测
	 */
	public static void test_023() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_Text,Operation_ClickWait,"智能检测");
		String[] str={"关闭","人脸检测","笑脸拍照"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();	
	}
	/**
	 * 色彩效果
	 */
	public static void test_024() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		//小屏幕手机滑到色彩效果附近
		excute(Object_TextScroll,Operation_Exists,"取景模式","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"色彩效果","vertical");
		String[] str={"关闭","黑白","反色","旧照片","冷色","怀旧"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 * 取景模式
	 */
	public static void test_025() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"取景模式","vertical");
		String[] str={"运动","夜间","自动","正常","肖像","风景"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 *白平衡
	 */
	public static void test_026() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"白平衡","vertical");	
		String[] str={"白炽灯","日光灯","自动","晴天","阴天"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 *连续拍摄
	 */
	public static void test_027() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"连续拍摄","vertical");	
		String[] str={"3","6","10","关闭"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 *对比度
	 */
	public static void test_028() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"对比度","vertical");	
		String[] str={"3","2","1","0","-1","-2","-3"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 *亮度
	 */
	public static void test_029() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"亮度","vertical");	
		String[] str={"6","5","4","3","2","1","0"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();	
	}
	/**
	 *感光度
	 */
	public static void test_030() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		//防止滑动后下面的值获取不到
		excute(Object_TextScroll,Operation_Exists,"测光","vertical");	
		excute(Object_TextScroll,Operation_ClickWait,"感光度","vertical");	
		String[] str={"自动","1600","800","400","200","100"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 *测光
	 */
	public static void test_031() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"测光","vertical");	
		String[] str={"帧平均","中心测光","点"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[0]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[0]);
		//清场
		CameraCommon.restoreCameraSetting();	
	}
	/**
	 *饱和度
	 */
	public static void test_032() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"饱和度","vertical");	
		String[] str={"3","2","1","0","-1","-2","-3"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[0]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[0]);
		//清场
		CameraCommon.restoreCameraSetting();
	}
	/**
	 *还原拍照默认设置
	 */
	public static void test_033() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"还原拍照默认设置","vertical");	
		check(Object_Text,Operation_checkExist,"要恢复拍照默认设置吗?");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"设置");
		excute(Object_Text,Operation_ClickWait,"还原拍照默认设置");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
	}
	/**
	 *编码类型
	 */
	public static void test_034() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"编码类型","vertical");	
		String[] str={"MPEG4","H.264"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[0]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[0]);
		//清场
		CameraCommon.restoreVideoSetting();	
	}
	/**
	 *防闪烁(视频中)
	 */
	public static void test_035() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		//使手机先滑动到视频中防闪烁附近
		excute(Object_TextScroll,Operation_Exists,"编码类型","vertical");	
		excute(Object_Text,Operation_ClickWait,"防闪烁","vertical");	
		String[] str={"50Hz","60Hz"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[1]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreVideoSetting();	
	}
	/**
	 *延时
	 */
	public static void test_036() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"延时","vertical");	
		String[] str={"关闭","间隔0.12秒(4倍)","间隔0.3秒(10倍)","间隔1秒(30倍)","间隔2秒(60倍)","间隔3秒(90倍)","间隔5秒(150倍)","间隔10秒(300倍)","间隔15秒(450倍)","间隔30秒(900倍)","间隔60秒(1800倍)"};
		for(int i=0;i<str.length;i++)
		{
			check(Object_TextScroll,Operation_checkExist,str[i],"vertical");
		}
		excute(Object_TextScrollWithResId,Operation_ClickWait,"android:id/select_dialog_listview",str[1],"vertical");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[1]);
		//清场
		CameraCommon.restoreVideoSetting();		
	}
	/**
	 *慢录
	 */
	public static void test_037() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"慢录","vertical");	
		String[] str={"3","2","关闭"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[0]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[0]);
		//清场
		CameraCommon.restoreVideoSetting();		
	}
	/**
	 *还原视频默认设置
	 */
	public static void test_038() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"还原视频默认设置","vertical");	
		check(Object_Text,Operation_checkExist,"要恢复视频默认设置吗?");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"设置");
		excute(Object_Text,Operation_ClickWait,"还原视频默认设置");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
	}
	/**
	 *GIF大小
	 */
	public static void test_039() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"GIF大小","vertical");	
		String[] str={"140x140","200x200","260x260"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[0]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[0]);
		//清场
		CameraCommon.restoreGifSetting();	
	}
	/**
	 *GIF帧数
	 */
	public static void test_040() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"GIF帧数","vertical");	
		String[] str={"10","15","20"};
		CameraCommon.checkForExist(str);
		excute(Object_Text,Operation_ClickWait,str[0]);
		check(Object_ResIdText,Operation_checkExist,"android:id/summary",str[0]);
		//清场
		CameraCommon.restoreGifSetting();	
	}
	/**
	 *还原动画默认设置
	 */
	public static void test_041() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.enterAdvSubSetting();
		excute(Object_TextScroll,Operation_ClickWait,"还原动画默认设置","vertical");	
		check(Object_Text,Operation_checkExist,"要恢复动画默认设置吗?");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,"设置");
		excute(Object_Text,Operation_ClickWait,"还原动画默认设置");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
	}
	/**
	 * 进入后置相机
	 * @throws UiObjectNotFoundException
	 */
	public static void test_042() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		check(Object_Description, Operation_checkExist, "后置摄像头");
	}
	/**
	 * 拍照
	 * @throws UiObjectNotFoundException
	 */
	public static void test_043() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		int num = CameraCommon.getMediaCount("拍照");
		System.out.println(num);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/rounded_thumbnail_view", "10000");
		int Num = CameraCommon.getMediaCount("拍照");
		System.out.println(Num);
		Assert.assertEquals(Num, num+1);
	}
	/**
	 * 点击浏览位置
	 * @throws UiObjectNotFoundException
	 */
	public static void test_044() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/rounded_thumbnail_view", "10000");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/rounded_thumbnail_view");
		check(Object_Description, Operation_checkExist, "切换到图库");
	}
	/**
	 * 点击美颜图标
	 * @throws UiObjectNotFoundException
	 */
	public static void test_045() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/makeup_seekbar");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
	}
	/**
	 * 调节美化程度
	 */
	public static void test_046() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.camera2:id/makeup_seekbar");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x, y);
		String str1 = DeviceCommon.parseTagFromXml("/data/data/com.android.camera2/shared_prefs/com.android.camera2_preferences.xml", "pref_makeup_mode_level_key");
		System.out.println("The value is "+ str1);
		Wait(2000);
		UiDevice.getInstance().click(x / 2, y);
		String str2 = DeviceCommon.parseTagFromXml("/data/data/com.android.camera2/shared_prefs/com.android.camera2_preferences.xml", "pref_makeup_mode_level_key");
		System.out.println("The value is "+ str2);
		try{
			Assert.assertFalse(str1.equals(str2));
		}
		//清场
		finally{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		}
	}
	/**
	 * 点击美颜拍照
	 * @throws UiObjectNotFoundException
	 */
	public static void test_047() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/rounded_thumbnail_view", "10000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/rounded_thumbnail_view");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
	}
	/**
	 * 点击美颜拍照后预览
	 * @throws UiObjectNotFoundException
	 */
	public static void test_048() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/rounded_thumbnail_view", "10000");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/rounded_thumbnail_view");
		check(Object_Description, Operation_checkExist, "切换到图库");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
	}
	/**
	 * 退出美颜拍照模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_049() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.camera2:id/makeup_seekbar");
	}
	/**
	 * 点击选项图标
	 * @throws UiObjectNotFoundException
	 */
	public static void test_050() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button");//倒计时器ID
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/grid_lines_toggle_button");//网格线ID
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/hdr_plus_toggle_button");//HDR ID
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/flash_toggle_button");//闪光灯ID
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/camera_toggle_button");//切换摄像头ID
	}
	/**
	 * 点击倒计时
	 * @throws UiObjectNotFoundException
	 */
	public static void test_051() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		CameraCommon.setCountdown("倒计时器已关闭");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button", "倒计时器已关闭");
		CameraCommon.setCountdown("倒计时器的时长已设为3秒");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button", "倒计时器的时长已设为3秒");
		CameraCommon.setCountdown("倒计时器的时长已设为10秒");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button", "倒计时器的时长已设为10秒");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/countdown_toggle_button");
	}
	/**
	 * 点击网格线
	 * @throws UiObjectNotFoundException
	 */
	public static void test_052() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		CameraCommon.switchGridlines("网格线已开启");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/grid_lines_toggle_button", "网格线已开启");
		CameraCommon.switchGridlines("网格线已关闭");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/grid_lines_toggle_button", "网格线已关闭");
	}
	/**
	 * 点击闪光灯
	 * @throws UiObjectNotFoundException
	 */
	public static void test_053() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		CameraCommon.setFlash("闪光灯已打开");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/flash_toggle_button", "闪光灯已打开");
		CameraCommon.setFlash("闪光灯已关闭");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/flash_toggle_button", "闪光灯已关闭");
		CameraCommon.setFlash("自动闪光");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/flash_toggle_button", "自动闪光");
	}
	/**
	 * 切换前后置摄像头
	 * @throws UiObjectNotFoundException
	 */
	public static void test_054() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/camera_toggle_button", "前置摄像头");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/camera_toggle_button", "后置摄像头");
	}
	/**
	 * 查看倒计时
	 * @throws UiObjectNotFoundException
	 */
	public static void test_055() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		CameraCommon.setCountdown("倒计时器的时长已设为3秒");
		String str1 = DeviceCommon.parseTagFromXml("/data/data/com.android.camera2/shared_prefs/com.android.camera2_preferences.xml", "pref_camera_countdown_duration_key");
		String str = "3";
		Assert.assertEquals(str, str1);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/mode_options_overlay");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/countdown_timer_indicator");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.setCountdown("倒计时器已关闭");
	}
	/**
	 * 查看闪光灯
	 * @throws UiObjectNotFoundException
	 */
	public static void test_056() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		CameraCommon.setFlash("闪光灯已打开");
		check(Object_Description, Operation_checkExist, "闪光灯已打开");
		//清场
		CameraCommon.setFlash("自动闪光");
	}
	/**
	 * 切换到前置
	 * @throws UiObjectNotFoundException
	 */
	public static void test_058() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/camera_toggle_button", "前置摄像头");
		//清场
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}
	/**
	 * 点击美颜图标
	 * @throws UiObjectNotFoundException
	 */
	public static void test_059() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/makeup_seekbar");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}
	/**
	 * 调节美化程度
	 */
	public static void test_060() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.camera2:id/makeup_seekbar");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x, y);
		String str1 = DeviceCommon.parseTagFromXml("/data/data/com.android.camera2/shared_prefs/com.android.camera2_preferences.xml", "pref_makeup_mode_level_key");
		System.out.println("The value is "+ str1);
		Wait(2000);
		UiDevice.getInstance().click(x / 2, y);
		String str2 = DeviceCommon.parseTagFromXml("/data/data/com.android.camera2/shared_prefs/com.android.camera2_preferences.xml", "pref_makeup_mode_level_key");
		System.out.println("The value is "+ str2);
		try{
			Assert.assertFalse(str1.equals(str2));
		}
		//清场
		finally{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		}
		
	}
	/**
	 * 点击美颜拍照
	 * @throws UiObjectNotFoundException
	 */
	public static void test_061() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/rounded_thumbnail_view", "10000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/rounded_thumbnail_view");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/btn_beauty_button");
	}
	/**
	 * 点击选项图标
	 * @throws UiObjectNotFoundException
	 */
	public static void test_062() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button");//倒计时器ID
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/grid_lines_toggle_button");//网格线ID
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/camera_toggle_button");//切换摄像头ID
		//清场
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}
	/**
	 * 点击倒计时
	 * @throws UiObjectNotFoundException
	 */
	public static void test_063() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		CameraCommon.setCountdown("倒计时器已关闭");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button", "倒计时器已关闭");
		CameraCommon.setCountdown("倒计时器的时长已设为3秒");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button", "倒计时器的时长已设为3秒");
		CameraCommon.setCountdown("倒计时器的时长已设为10秒");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/countdown_toggle_button", "倒计时器的时长已设为10秒");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/countdown_toggle_button");
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}
	/**
	 * 点击网格线
	 * @throws UiObjectNotFoundException
	 */
	public static void test_064() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		CameraCommon.switchGridlines("网格线已开启");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/grid_lines_toggle_button", "网格线已开启");
		CameraCommon.switchGridlines("网格线已关闭");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/grid_lines_toggle_button", "网格线已关闭");
		//清场
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}
	/**
	 * 切换前后置摄像头
	 * @throws UiObjectNotFoundException
	 */
	public static void test_065() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("相机");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		excute(Object_ResIdDesc, Operation_WaitForExists, "com.android.camera2:id/camera_toggle_button", "前置摄像头", "10000");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		excute(Object_ResIdDesc, Operation_WaitForExists, "com.android.camera2:id/camera_toggle_button", "后置摄像头", "10000");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/camera_toggle_button", "后置摄像头");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		excute(Object_ResIdDesc, Operation_WaitForExists, "com.android.camera2:id/camera_toggle_button", "前置摄像头", "10000");
		check(Object_ResIdDesc, Operation_checkExist, "com.android.camera2:id/camera_toggle_button", "前置摄像头");
		//清场
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}
	
	/**
	 * 点击 视频-后置摄像头 -  页面上有网格模式、闪光灯、前后摄像头切换三个功能项
	 */
	public static void test_066() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/grid_lines_toggle_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/flash_toggle_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 点击 视频-后置摄像头 -  页面上有网格模式、默认是关闭的，打开再关闭
	 */
	public static void test_067() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		check(Object_Description,Operation_checkExist,"网格线已关闭");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/grid_lines_toggle_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/grid_lines_toggle_button");
		check(Object_Description,Operation_checkExist,"网格线已关闭");
	}

	/**
	 * 点击 视频-后置摄像头 -  页面上有闪光灯、默认是关闭的，打开再关闭
	 */
	public static void test_068() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		check(Object_Description,Operation_checkExist,"手电筒已关闭");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/flash_toggle_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/flash_toggle_button");
		check(Object_Description,Operation_checkExist,"手电筒已关闭");
	}
	
	/**
	 * 点击 视频-后置摄像头 -  页面上有前后摄像头、切换至前置摄像头
	 */
	public static void test_069() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		check(Object_Description,Operation_checkExist,"前置摄像头");
		//清场
		CameraCommon.switchFrontBackCamera("后置摄像头");
	}

	/**
	 * 点击 视频-后置摄像头- 开始录像，页面上有计时，和停止和暂停按钮
	 */
	public static void test_070() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/recording_time");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/btn_video_pause");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
	}
	
	/**
	 * 点击 视频-后置摄像头- 开始录像，点击暂停，录像暂停
	 */
	public static void test_071() throws UiObjectNotFoundException
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/btn_video_pause");
		String time = (String) excute(Object_ResourceId,Operation_GetText,"com.android.camera2:id/recording_time");
		String time1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.camera2:id/recording_time");
		Assert.assertEquals(time, time1);
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
	}
	
	/**
	 * 点击 视频- 开始录像，点击暂停，录像暂停,继续录制
	 */
	public static void test_072() throws UiObjectNotFoundException
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/btn_video_pause");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/btn_video_pause");
		String time = (String) excute(Object_ResourceId,Operation_GetText,"com.android.camera2:id/recording_time");
		String time1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.camera2:id/recording_time");
		Assert.assertNotSame(time, time1);
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
	}
	
	/**
	 * 点击 视频- 开始录像，停止录像
	 */
	public static void test_073() throws UiObjectNotFoundException
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.camera2:id/btn_video_pause");
		
	}
	
	/**
	 * 点击 视频 -前置摄像头- 菜单，有网格，切换摄像头模式
	 */
	public static void test_074() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/grid_lines_toggle_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/camera_toggle_button");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 点击 视频-前置摄像头 -  页面上有网格模式、默认是关闭的，打开再关闭
	 */
	public static void test_075() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		check(Object_Description,Operation_checkExist,"网格线已关闭");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/grid_lines_toggle_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/grid_lines_toggle_button");
		check(Object_Description,Operation_checkExist,"网格线已关闭");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 点击 视频-前置摄像头 -  切换至后置摄像头
	 */
	public static void test_076() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		if((Boolean)excute(Object_Description,Operation_Exists,"前置摄像头"))
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
		}else
		{
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
			excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
		}
		check(Object_Description,Operation_checkExist,"后置摄像头");
	}
	
	/**
	 * 点击 视频-前置摄像头- 开始录像，页面上有计时，和停止和暂停按钮
	 */
	public static void test_077() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/recording_time");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/btn_video_pause");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_Description,Operation_ClickWait,"选项");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 点击 视频-前置摄像头- 开始录像，页面上有计时，和停止和暂停按钮
	 */
	public static void test_078() throws UiObjectNotFoundException 
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/recording_time");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/btn_video_pause");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_Description,Operation_ClickWait,"选项");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 点击 视频-前置摄像头- 开始录像，点击暂停，录像暂停,继续录制
	 */
	public static void test_079() throws UiObjectNotFoundException
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/btn_video_pause");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/btn_video_pause");
		String time = (String) excute(Object_ResourceId,Operation_GetText,"com.android.camera2:id/recording_time");
		String time1 = (String) excute(Object_ResourceId,Operation_GetText,"com.android.camera2:id/recording_time");
		Assert.assertNotSame(time, time1);
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_Description,Operation_ClickWait,"选项");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 点击 视频-前置摄像头- 开始录像，停止录像
	 */
	public static void test_080() throws UiObjectNotFoundException
	{
		//主体
		CameraCommon.switchMode("视频");
		excute(Object_Description,Operation_ClickWait,"选项");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/shutter_button");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.camera2:id/btn_video_pause");
		//清场
		excute(Object_Description,Operation_ClickWait,"选项");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.camera2:id/camera_toggle_button");
	}
	
	/**
	 * 动画模式&后置摄像头开启条件下，检查闪光灯和摄像头切换是否存在
	 * @throws UiObjectNotFoundException
	 */
	public static void test_081() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/flash_toggle_button");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/camera_toggle_button");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，打开/关闭闪光灯
	 * @throws UiObjectNotFoundException
	 */
	public static void test_082() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/flash_toggle_button");
		check(Object_Description, Operation_checkExist, "手电筒已打开");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/flash_toggle_button");
		check(Object_Description, Operation_checkExist, "手电筒已关闭");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，切换到前置摄像头
	 * @throws UiObjectNotFoundException
	 */
	public static void test_083() throws UiObjectNotFoundException 
	{
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/camera_toggle_button");
		excute(Object_Description, Operation_WaitForExists, "前置摄像头","10000");
		check(Object_Description, Operation_checkExist, "前置摄像头");
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/camera_toggle_button");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，切换摄像/拍照模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_084() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/video_switcher");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/collage_grid_item_id");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/video_switcher");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.camera2:id/collage_grid_item_id");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_085() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/gif_progress_bar");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_086() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		check(Object_Text, Operation_checkExist, "编辑");
		
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，点击编辑
	 * @throws UiObjectNotFoundException
	 */
	public static void test_087() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"编辑","horizontal");//点击编辑
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_Text, Operation_checkExist, "右转");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，点击特效
	 * @throws UiObjectNotFoundException
	 */
	public static void test_088() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_ResIdInstance, Operation_ClickWait, "com.android.camera2:id/ugif_edit_cate_icon_id","1");//点击特效
		UiDevice device = UiDevice.getInstance();
		SettingCommon.take_temp_pic(device, "888");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		SettingCommon.take_temp_pic(device, "999");
		check(Object_Text, Operation_checkExist, "彩虹");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，点击相框
	 * @throws UiObjectNotFoundException
	 */
	public static void test_089() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"相框","horizontal");//点击相框
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/top_sub_menu_ok");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，点击底纹
	 * @throws UiObjectNotFoundException
	 */
	public static void test_090() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"底纹","horizontal");//点击底纹
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/top_sub_menu_ok");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，点击速度
	 * @throws UiObjectNotFoundException
	 */
	public static void test_091() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"速度","horizontal");//点击速度
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_Text, Operation_checkExist, "慢");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，点击模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_092() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"模式","horizontal");//点击模式
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/top_sub_menu_ok");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，保存动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_093() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		int startnum=CameraCommon.getMediaCount("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/ugif_topbar_btn_save");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/ugif_topbar_btn_save","10000");
		int endnum=CameraCommon.getMediaCount("动画");
		boolean boo=(startnum+1==endnum);
		Assert.assertTrue(boo);
//		check(Object_ResourceId, Operation_EnabledFalse, "com.android.camera2:id/ugif_topbar_btn_save");
		
	}
	/**
	 * 动画模式&后置摄像头开启条件下，录制动画，播放动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_094() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/gif_play_icon");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.camera2:id/gif_play_icon");
	}
	/**
	 * 动画模式&后置摄像头开启条件下，连续拍摄动画，完成后进入编辑
	 * @throws UiObjectNotFoundException
	 */
	public static void test_095() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("后置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/video_switcher");
		for(int i=0;i<3;i++)
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
			Wait(1000);
		}
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/gif_finish");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		check(Object_Text, Operation_checkExist, "编辑");
	}
	/**
	 * 动画模式&前置摄像头开启条件下,切换后置摄像头
	 * @throws UiObjectNotFoundException
	 */
	public static void test_096() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/camera_toggle_button");
		excute(Object_Description, Operation_WaitForExists, "后置摄像头","10000");
		check(Object_Description, Operation_checkExist, "后置摄像头");	
	}
	/**
	 * 动画模式&前置摄像头开启条件下，切换录制、拍摄
	 * @throws UiObjectNotFoundException
	 */
	public static void test_097() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/video_switcher");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/collage_grid_item_id");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/video_switcher");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.camera2:id/collage_grid_item_id");
	}	
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_098() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/gif_progress_bar");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_099() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		check(Object_Text, Operation_checkExist, "编辑");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，点击编辑
	 * @throws UiObjectNotFoundException
	 */
	public static void test_100() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"编辑","horizontal");//点击编辑
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_Text, Operation_checkExist, "右转");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，点击特效
	 * @throws UiObjectNotFoundException
	 */
	public static void test_101() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"特效","horizontal");//点击特效
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_Text, Operation_checkExist, "彩虹");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，点击相框
	 * @throws UiObjectNotFoundException
	 */
	public static void test_102() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"相框","horizontal");//点击相框
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/top_sub_menu_ok");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，点击底纹
	 * @throws UiObjectNotFoundException
	 */
	public static void test_103() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"底纹","horizontal");//点击底纹
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/top_sub_menu_ok");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，点击速度
	 * @throws UiObjectNotFoundException
	 */
	public static void test_104() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"速度","horizontal");//点击速度
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_Text, Operation_checkExist, "慢");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，点击模式
	 * @throws UiObjectNotFoundException
	 */
	public static void test_105() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","30000");
		excute(Object_TextScroll, Operation_ClickWait,"模式","horizontal");//点击模式
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/top_sub_menu_cancel","20000");
		check(Object_ResourceId, Operation_checkExist, "com.android.camera2:id/top_sub_menu_ok");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，保存动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_106() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		int startnum=CameraCommon.getMediaCount("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/ugif_topbar_btn_save");
		excute(Object_ResourceId, Operation_WaitForExists, "com.android.camera2:id/ugif_topbar_btn_save","10000");
		int endnum=CameraCommon.getMediaCount("动画");
		boolean boo=(startnum+1==endnum);
		Assert.assertTrue(boo);
	  //check(Object_ResourceId, Operation_EnabledFalse, "com.android.camera2:id/ugif_topbar_btn_save");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，录制动画，播放动画
	 * @throws UiObjectNotFoundException
	 */
	public static void test_107() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/gif_play_icon");
		check(Object_ResourceId, Operation_checkNoExist, "com.android.camera2:id/gif_play_icon");
	}
	/**
	 * 动画模式&前置摄像头开启条件下，连续拍摄动画，完成后进入编辑
	 * @throws UiObjectNotFoundException
	 */
	public static void test_108() throws UiObjectNotFoundException 
	{	
		//前提
		CameraCommon.switchMode("动画");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/three_dots");
		CameraCommon.switchFrontBackCamera("前置摄像头");
		//主体
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/video_switcher");
		for(int i=0;i<3;i++)
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/shutter_button");
			Wait(1000);
		}
		excute(Object_ResourceId, Operation_ClickWait, "com.android.camera2:id/gif_finish");
		excute(Object_Text, Operation_WaitForExists, "编辑","20000");
		check(Object_Text, Operation_checkExist, "编辑");
	}
}
