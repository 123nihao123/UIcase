package testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.CallLogCommon;
import framework.common.GalleryCommon;
import framework.common.SettingCommon;
import framework.common.EmailCommon;
import framework.common.CallCommon;
import framework.driver.ObjectFind;
import framework.driver.OperationUiDevice;
import framework.driver.OperationUiObject;

//保证相机是正常可以使用的，蓝牙是关闭的。
public class Gallery extends UiAutomatorTestCase
{
	
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_PictureStore);	
   }
	       
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	public static void test_0000() throws UiObjectNotFoundException 
	{   //前提
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"应用","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"图库","vertical");
		excute(Object_Text,Operation_ClickWait,"存储空间");
		check(Object_ResIdText, Operation_ClickWait, "com.android.settings:id/button", "清除数据");
		excute(Object_Text,Operation_ClickWait,"确定");
	}
	/**
	 *查看进入图库
	 */
	public static void test_001() 
	{
		//前提
		 GalleryCommon.deleteMoreGallery();
		//主体
		 check(Object_ResourceId,Operation_checkExist,"com.android.gallery3d:id/gl_root_view");
		//整个大框的ID
	}
	/**
	 *查看下拉框
	 */
	public static void test_002() 
	{
		//前提
		GalleryCommon.deleteMoreGallery();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		check(Object_Text,Operation_checkExist,"相册");
		check(Object_Text,Operation_checkExist,"地点");
		check(Object_Text,Operation_checkExist,"时间");
		check(Object_Text,Operation_checkExist,"人物");
		check(Object_Text,Operation_checkExist,"标签");
	}
	/**
	 *相册模式
	 */
	public static void test_003() 
	{
		//前提
		GalleryCommon.deleteMoreGallery();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"相册");
		check(Object_Text,Operation_checkExist,"相册");
	}
	/**
	 *查看地点
	 */
	public static void test_004() 
	{
		//前提
		GalleryCommon.deleteMoreGallery();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"地点");
		check(Object_Text,Operation_checkExist,"地点");
	}
	/**
	 *查看时间
	 */
	public static void test_005() 
	{
		//前提
		GalleryCommon.deleteMoreGallery();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"android:id/action_bar_spinner");
		excute(Object_Text,Operation_ClickWait,"时间");
		check(Object_Text,Operation_checkExist,"时间");
	}
	//test_006,test_007同test_005相同,故没写
	public static void test_008() 
	{
		//前提
		GalleryCommon.deleteMoreGallery();
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.gallery3d:id/action_camera");
		check(Object_ResourceId,Operation_checkExist,"com.android.camera2:id/mode_options_overlay");
	}
	
	public static void test_009() 
	{
		//前提
		 GalleryCommon.deleteMoreGallery();
		//主体
		 excute(Object_Device,Operation_PressMenu);
		 check(Object_Text,Operation_checkExist,"选择相册");
	}
	/**
	 *选择相册
	 */
	public static void test_010() 
	{
		//前提
		GalleryCommon.deleteMoreGallery();
		//主体
		excute(Object_Device,Operation_PressMenu);
		check(Object_Text,Operation_checkExist,"选择相册");
		check(Object_Text,Operation_ClickWait,"选择相册");
		check(Object_Text,Operation_checkExist,"选中了 0 项");
	}
	/**
	 *查看进入群组
	 */
	public static void test_011() 
	{
		//前提
		 GalleryCommon.deleteMoreGallery();
		 GalleryCommon.morePicture();
		//主体
		 excute(Object_Description ,Operation_ClickWait,"更多选项");
		 excute(Object_Text ,Operation_ClickWait,"选择相册");
		 excute(Object_Text, Operation_ClickWait,"选中了 0 项");
 		 excute(Object_Text, Operation_ClickWait,"全选");
 		 excute(Object_Description, Operation_ClickWait,"更多选项");
 		 check(Object_Text,Operation_checkExist,"删除");
 	     check(Object_Text,Operation_checkExist,"详细信息");
	}
	/**
	 *分享
	 */
	public static void test_012()
	{   
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	  //主体
	    check(Object_ResourceId,Operation_WaitForExists,"android:id/image","30000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    check(Object_Text,Operation_checkExist,"信息");
	    check(Object_Text,Operation_checkExist,"电子邮件");
	    check(Object_Text,Operation_checkExist,"蓝牙");
	
	}
	/**
	 *蓝牙分享
	 */
	public static void test_013()
	{
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	  //主体
	    check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"蓝牙"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"蓝牙");
	    }
	    check(Object_Text,Operation_checkExist,"开启");
	}
	/**
	 *电子邮件分享
	 */
	public static void test_014()
	{
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	  //主体
	    check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"电子邮件"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"电子邮件");
	    }
	    check(Object_ResourceId,Operation_checkExist,"com.android.email:id/setup_fragment_content");
	}
	/**
	 *信息分享
	 */
	public static void test_015()
	{
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	    //主体
	    check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"信息"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"信息");
	    }
	    check(Object_ResourceId,Operation_checkExist,"android:id/list");
	}
	/**
	 *删除群组
	 */
	public static void test_016()
	{
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	    //主体
	    check(Object_Description ,Operation_WaitForExists,"更多选项","10000");
	    excute(Object_Description ,Operation_ClickWait,"更多选项");
	    excute(Object_Text,Operation_ClickWait,"删除");
	    check(Object_ResourceId ,Operation_checkExist,"android:id/content");
	}
	/**
	 *群组的删除与取消
	 */
	public static void test_017()
	{
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	    //主体
	    check(Object_Description ,Operation_WaitForExists,"更多选项","10000");
	    excute(Object_Description ,Operation_ClickWait,"更多选项");
	    excute(Object_Text,Operation_ClickWait,"删除");
	    excute(Object_Text,Operation_ClickWait,"取消");
	 }
	/**
	 *群组的删除与取消
	 */
	public static void test_018()
	{
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.morePicture();
	    GalleryCommon.clickGroup();
	    //主体
	    check(Object_Description ,Operation_WaitForExists,"更多选项","10000");
	    excute(Object_Description ,Operation_ClickWait,"更多选项");
	    excute(Object_Text,Operation_ClickWait,"删除");
	    excute(Object_Text,Operation_ClickWait,"确定");
	    excute(Object_Text, Operation_checkExist,"相机");
	 }
	/**
	 *群组的详细信息
	 */
	public static void test_019()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
	    GalleryCommon.clickGroup();
	    //主体
	    check(Object_Description ,Operation_WaitForExists,"更多选项","10000");
	    excute(Object_Description ,Operation_ClickWait,"更多选项");
	    excute(Object_Text,Operation_ClickWait,"详细信息");
	    excute(Object_ResourceId, Operation_checkExist,"android:id/content");
	}
	/**
	 *进入群组内部
	 */
	public static void test_020() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
	    check(Object_ResourceId ,Operation_checkExist,"android:id/action_bar_spinner");
	}
	/**
	 *进入群组内部菜单
	 */
	public static void test_021()  
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
	    check(Object_ResourceId ,Operation_WaitForExists,"android:id/text2","10000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/text2");
	   
	    check(Object_Text ,Operation_checkExist,"网格视图");
	    check(Object_Text ,Operation_checkExist,"幻灯片视图");
	}
	/**
	 *进入幻灯片视图模式
	 */
	public static void test_022() throws UiObjectNotFoundException, RemoteException 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
	    check(Object_ResourceId ,Operation_WaitForExists,"android:id/text2","10000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/text2");
	    excute(Object_Text ,Operation_ClickWait,"幻灯片视图");
	    String view = (String) excute(Object_ResourceId,Operation_GetText,"android:id/text2");
	    Assert.assertEquals("幻灯片视图", view);
	}
	/**
	 *进入网络视图模式
	 */
	public static void test_023() throws UiObjectNotFoundException, RemoteException 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
	    check(Object_ResourceId ,Operation_WaitForExists,"android:id/text2","10000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/text2");
	    excute(Object_Text ,Operation_ClickWait,"网格视图");
	    String view = (String) excute(Object_ResourceId,Operation_GetText,"android:id/text2");
	    Assert.assertEquals("网格视图", view);
	}
	/**
	 *群组内菜单
	 */
	public static void test_024() throws UiObjectNotFoundException, RemoteException 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description ,Operation_ClickWait,"更多选项");
	    check(Object_Text ,Operation_checkExist,"播放幻灯片");
	    check(Object_Text ,Operation_checkExist,"选择条目");
	    check(Object_Text ,Operation_checkExist,"分组依据");
	}
	/**
	 *幻灯片播放
	 */
	public static void test_025() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description ,Operation_ClickWait,"更多选项");
	    check(Object_Text ,Operation_ClickWait,"播放幻灯片");
	    check(Object_ResourceId ,Operation_checkNoExist,"android:id/action_bar_container");
	  //因为点击图片进去的话，上面会有框，我对这个框进行判断
	}
	/**
	 *选择条目
	 */
	public static void test_026() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description ,Operation_ClickWait,"更多选项");
	    check(Object_Text ,Operation_ClickWait,"选择条目");
	    excute(Object_Text, Operation_Exists,"选中了 0 项");
	 }
	/**
	 *选择条目的内容
	 */
	public static void test_027() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description ,Operation_ClickWait,"更多选项");
	    check(Object_Text ,Operation_ClickWait,"选择条目");
	    GalleryCommon.clickGallery();
	    check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"蓝牙"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"蓝牙");
	    }
	    check(Object_Text,Operation_checkExist,"开启");
	 }
	/**
	 *选择条目的内容
	 */
	public static void test_028() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"删除");
		check(Object_Text,Operation_checkExist,"编辑");
		check(Object_Text,Operation_checkExist,"向左旋转");
		check(Object_Text,Operation_checkExist,"向右旋转");
		check(Object_Text,Operation_checkExist,"剪裁");
		check(Object_Text,Operation_checkExist,"详细信息");
    }
	/**
	 *选择条目的删除
	 */
	public static void test_029() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		//GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
	}
	/**
	 *选择条目的删除（取消）
	 */
	public static void test_030() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkNoExist,"要删除所选内容吗？");
	}
	/**
	 *选择条目的删除（确定）
	 */
	public static void test_031() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_Text,Operation_checkExist,"要删除所选内容吗？");
		excute(Object_Text,Operation_ClickWait,"确定");
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"选择相册");
	    check(Object_Text,Operation_checkNoExist,"选中了 0 项");
	    //删除之后一个图片也没有了
	}
	/**
	 *向左旋转
	 */
	public static void test_032() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"向左旋转");
		//通过详细信息里面的浏览模式来识别。
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"详细信息");
		check(Object_Text,Operation_checkExist,"浏览模式: 270");
	}
	/**
	 *向右旋转
	 */
	public static void test_033() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"向右旋转");
		//通过详细信息里面的浏览模式来识别。
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"详细信息");
		 check(Object_Text,Operation_checkExist,"浏览模式: 90");
	}
	/**
	 *裁剪
	 */
	public static void test_034() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"剪裁");
		check(Object_ResourceId ,Operation_checkExist,"com.android.gallery3d:id/cropView");
	}
	/**
	 *图片设置
	 */
	public static void test_035() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"将照片设置为");
		check(Object_Text ,Operation_checkExist,"设置为");
	}
	/**
	 *页面的详细信息
	 */
	public static void test_036() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Text,Operation_WaitForExists,"相机","10000");
		GalleryCommon.clickGallery();
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"详细信息");
		check(Object_Text,Operation_checkExist,"浏览模式: 0");
		//因为每张照片的标题，时间可能不同的。既然主要是验证详细信息，判断下面的控件关闭也可以
		check(Object_Text ,Operation_checkExist,"关闭");
		
	}
	//37与36相同
	/**
	 *分组依据
	 */
	public static void test_038() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"分组依据");
		check(Object_Text ,Operation_checkExist,"地点");
		check(Object_Text ,Operation_checkExist,"时间");
		check(Object_Text ,Operation_checkExist,"人物");
		check(Object_Text ,Operation_checkExist,"标签");
	}
	/**
	 *按地点分组
	 */
	
	public static void test_039() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"分组依据");
		check(Object_Text ,Operation_ClickWait,"地点");
		check(Object_Text ,Operation_checkExist,"按位置分组");
	}
	/**
	 *按时间分组
	 */
	public static void test_040() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"分组依据");
		check(Object_Text ,Operation_ClickWait,"时间");
		check(Object_Text ,Operation_checkExist,"按时间分组");
	}
	/**
	 *按人物分组
	 */
	public static void test_041() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"分组依据");
		check(Object_Text ,Operation_ClickWait,"人物");
		check(Object_Text ,Operation_checkExist,"按人物");
	}
	/**
	 *按标签分组
	 */
	public static void test_042() 
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text, Operation_ClickWait,"分组依据");
		check(Object_Text ,Operation_ClickWait,"标签");
		check(Object_Text ,Operation_checkExist,"按标签分组");
	}
	/**
	 *分享
	 * @throws UiObjectNotFoundException 
	 */
	
	public static void test_043() throws UiObjectNotFoundException 
	{ 
		//前提
		
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll,Operation_ClickWait,"应用","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"图库","vertical");
		excute(Object_Text,Operation_ClickWait,"存储空间");
		check(Object_ResIdText, Operation_ClickWait, "com.android.settings:id/button", "清除数据");
		excute(Object_Text,Operation_ClickWait,"确定");
		DeviceCommon.enterApp(Devices_Desc_PictureStore);
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"分享方式","10000");
		excute(Object_Description ,Operation_ClickWait,"分享方式");
		excute(Object_Text,Operation_checkExist,"蓝牙");
		excute(Object_Text,Operation_checkExist,"电子邮件");
		excute(Object_Text,Operation_checkExist,"信息");
	}
	/**
	 *蓝牙分享
	 */
	public static void test_044()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"蓝牙"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"蓝牙");
	    }
	    check(Object_Text,Operation_checkExist,"开启");
	}
	/**
	 *信息分享
	 */
	public static void test_045()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"信息"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"信息");
	    }
	    check(Object_ResourceId,Operation_checkExist,"android:id/list");
	}
	/**
	 *电子邮件分享
	 */
	public static void test_046()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_ResourceId,Operation_WaitForExists,"android:id/image","20000");
	    excute(Object_ResourceId ,Operation_ClickWait,"android:id/image");
	    if(!(Boolean) excute(Object_Text,Operation_Exists,"电子邮件"))
		{	
	    	excute(Object_Device,Operation_PressBack);
	    	excute(Object_ResIdIndex,Operation_ClickWait,"android:id/default_activity_button","1");
		}
	    else
	    { 
	    	excute(Object_Text,Operation_ClickWait,"电子邮件");
	    }
	    check(Object_ResourceId,Operation_checkExist,"com.android.email:id/setup_fragment_content");
	}
	/**
	 *图片的更多选项
	 */
	public static void test_047()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_checkExist,"删除");
		excute(Object_Text,Operation_checkExist,"播放幻灯片");
		excute(Object_Text,Operation_checkExist,"编辑");
		excute(Object_Text,Operation_checkExist,"向左旋转");
		excute(Object_Text,Operation_checkExist,"向右旋转");
		excute(Object_Text,Operation_checkExist,"详细信息");
		excute(Object_Text,Operation_checkExist,"打印");
	}
	/**
	 *图片的删除
	 */
	public static void test_048()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_ResourceId,Operation_checkExist,"android:id/content");
	}
	/**
	 *图片删除（取消）
	 */
	public static void test_049()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_ResourceId,Operation_checkExist,"android:id/content");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkNoExist,"相机");
	}
	/**
	 *图片删除（确定）
	 */
	public static void test_050()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除");
		check(Object_ResourceId,Operation_checkExist,"android:id/content");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"相机");
		//删除之后无图片，故判断相机
	}
	/**
	 *播放幻灯片
	 */
	public static void test_051()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		check(Object_ResourceId,Operation_WaitForExists,"android:id/text2","10000");
		GalleryCommon.clickGallery();
		//check(Object_Description,Operation_WaitForExists,"分享方式","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"播放幻灯片");
		check(Object_ResourceId ,Operation_checkNoExist,"android:id/action_bar_container");
		//因为点击图片进去的话，上面会有框，而播放幻灯片的话，进去之后就没有框了，我对这个框进行判断
	}
	/**
	 *图片的编辑
	 */
	public static void test_052()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		check(Object_ResourceId,Operation_checkExist,"com.android.gallery3d:id/bottom_panel");
	}
	/**
	 *图片的左转
	 */
	public static void test_053()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		//主体
		check(Object_ResourceId,Operation_WaitForExists,"android:id/text2","10000");
		GalleryCommon.clickGallery();
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"向左旋转");
		//通过详细信息里面的浏览模式来识别（因为旋转了浏览模式肯定会发生变化）
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"详细信息");
		check(Object_Text,Operation_checkExist,"浏览模式: 270");
	}
	/**
	 *图片的右转
	 */
	public static void test_054()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		//主体
		GalleryCommon.clickGallery();
		check(Object_ResourceId,Operation_WaitForExists,"android:id/text2","10000");
		GalleryCommon.clickGallery();
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"向右旋转");
		//通过详细信息里面的浏览模式来识别（因为旋转了浏览模式肯定会发生变化）
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"详细信息");
		check(Object_Text,Operation_checkExist,"浏览模式: 90");
	}
	/**
	 *图片的剪裁
	 */
	public static void test_055()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		//主体
		GalleryCommon.clickGallery();
		check(Object_ResourceId,Operation_WaitForExists,"android:id/text2","10000");
		GalleryCommon.clickGallery();
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"剪裁");
		check(Object_ResourceId ,Operation_checkExist,"com.android.gallery3d:id/cropView");
	}
	/**
	 *图片的详细信息
	 */
	public static void test_056()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		//主体
		GalleryCommon.clickGallery();
		check(Object_ResourceId,Operation_WaitForExists,"android:id/text2","10000");
		GalleryCommon.clickGallery();
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"详细信息");
		check(Object_Text,Operation_checkExist,"浏览模式: 0");
		//因为每张照片的标题，时间可能不同的。既然主要是验证详细信息，判断下面的控件关闭也可以
		check(Object_Text ,Operation_checkExist,"关闭");
		
	}
	/**
	 *图片的打印
	 */
	public static void test_057()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.morePicture();
		GalleryCommon.clickGallery();
		check(Object_ResourceId,Operation_WaitForExists,"android:id/text2","10000");
		GalleryCommon.clickGallery();
		excute(Object_Description, Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"打印");
		check(Object_Text ,Operation_checkExist,"保存为 PDF");
	}
	//58与52相同
	/**
	 *图片的编辑
	 */
	public static void test_059()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		check(Object_Text ,Operation_checkExist,"保存");
	}
	//60同59相同
	/**
	 *图片的编辑
	 */
	public static void test_061()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"冲压");
		check(Object_Text ,Operation_ClickWait,"保存");
	}
	/**
	 *图片的编辑
	 */
	public static void test_062()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"复古");
		check(Object_Text ,Operation_ClickWait,"保存");
	}
	/**
	 *图片的编辑
	 */
	public static void test_063()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		check(Object_Text ,Operation_ClickWait,"保存");
	}
	/**
	 *图片的编辑
	 */
	public static void test_064()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"漂除银影");
		check(Object_Text ,Operation_ClickWait,"保存");
	}
	/**
	 *图片的编辑状态下的更多
	 */
	public static void test_065()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text ,Operation_checkExist,"重置");
		check(Object_Text ,Operation_checkExist,"信息");
		check(Object_Text ,Operation_checkExist,"显示已应用的效果");
		check(Object_Text ,Operation_checkExist,"导出");
		check(Object_Text ,Operation_checkExist,"打印");
	}
	/**
	 *图片的编辑
	 */
	public static void test_066()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"重置");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkNoExist,"保存并退出");
	}
	/**
	 *图片的编辑
	 */
	public static void test_067()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"信息");
		check(Object_Text ,Operation_checkExist,"EXIF 数据");
	}
	/**
	 *图片的编辑
	 */
	public static void test_068()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"显示已应用的效果");
		check(Object_ResourceId ,Operation_checkExist,"com.android.gallery3d:id/toggleVersionsPanel");
		//检测的是原图与效果图的对比的旁边的控件的resourceId
	}
	/**
	 *图片的编辑
	 */
	public static void test_069()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"导出");
		check(Object_Text,Operation_checkExist,"导出平面图片");
    }
	/**
	 *图片的编辑
	 */
	
	//图片导出，完成之后（编辑完的），然后直接点击退出（不保存）
	public static void test_070()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"导出");
		excute(Object_Text,Operation_ClickWait,"完成");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_ClickWait,"退出");
		//图片导出，完成之后（编辑完的），然后直接点击退出（不保存）
		excute(Object_Device,Operation_PressBack);
		excute(Object_Device,Operation_PressBack);
		GalleryCommon.clickGallery();
		excute(Object_Text,Operation_WaitForExists,"相机","20000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择条目");
		excute(Object_Text,Operation_ClickWait,"选中了 0 项");
		excute(Object_Text,Operation_ClickWait,"全选");
		excute(Object_Text,Operation_checkExist,"选中了 2 项");
		//因为是没保存退出，但是检测的是2张图片，故为导出的图片。
    }
	/**
	 *图片的编辑
	 */
	public static void test_071()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"打印");
		check(Object_Text,Operation_checkExist,"保存为 PDF");
	}
	/**
	 *图片的编辑
	 */
	/////////
	//7273   通过退出来判断
	//////
	public static void test_072()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"保存并退出");
		excute(Object_Device,Operation_PressBack);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.gallery3d:id/undoButton");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkNoExist,"保存并退出");
		//因为处于编辑状态退出时会有一个保存并推出的框，但是上一步之后就会没有这个了框了
	}
	public static void test_073()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"编辑");
		excute(Object_Description,Operation_ClickWait,"黑白");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"保存并退出");
		excute(Object_Device,Operation_PressBack);
		//因为处于编辑状态退出时会有一个保存并推出的框，但是上一步之后就会没有这个了框了
		excute(Object_ResourceId,Operation_ClickWait,"com.android.gallery3d:id/undoButton");
		check(Object_Text,Operation_checkNoExist,"保存并退出");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.gallery3d:id/redoButton");
		excute(Object_Device,Operation_PressBack);
		check(Object_Text,Operation_checkExist,"保存并退出");
		//道理同72一样后面也是家了判断
	}
	/**
	 *图片的剪裁
	 */
	public static void test_074()
	{ 
		//前提
		GalleryCommon.deleteMoreGallery();
		GalleryCommon.clickGallery();
		//主体
		check(Object_Description,Operation_WaitForExists,"更多选项","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"剪裁");
		check(Object_ResourceId,Operation_checkExist,"com.android.gallery3d:id/cropView");
	}
	}







