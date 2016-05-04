package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import junit.framework.Assert;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.DeviceCommon;
import framework.common.HomeScreenCommon;
import framework.driver.ObjectFind;

public class HomeScreen extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
    }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 进入主屏界面   
	 */
	public static void test_001() 
	{
		excute(Object_Device, Operation_PressHome);
		check(Object_Description, Operation_checkExist, "应用");
	}
	/**
	 * 左右滑动
	 */
	public static void test_002() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		//将三个指定的控件分别放置在三个屏幕中
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","FM 电台","vertical",
				Integer.toString(HomeScreenCommon.screenWidth/2), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","录音机","vertical",
				Integer.toString(HomeScreenCommon.screenWidth), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","图库","vertical",
				Integer.toString(HomeScreenCommon.screenWidth), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		excute(Object_Device, Operation_PressHome);
		DeviceCommon.swipe("Left", 10, 1);
		excute(Object_Text,Operation_DragToCoordinate,"图库",Integer.toString(HomeScreenCommon.screenWidth),
				Integer.toString(HomeScreenCommon.screenHeight/2),"20");
		//左右滑屏并检查
		DeviceCommon.swipe("Right", 10, 1);
		check(Object_Text,Operation_checkExist,"录音机");
		DeviceCommon.swipe("Left", 10, 1);
		check(Object_Text,Operation_checkExist,"图库");
		DeviceCommon.swipe("Right", 10, 2);
		check(Object_Text,Operation_checkExist,"FM 电台");
        //清场
		HomeScreenCommon.deleteView(Object_Text, "FM 电台");
		DeviceCommon.swipe("Left", 10, 1);
		HomeScreenCommon.deleteView(Object_Text, "录音机");
		DeviceCommon.swipe("Left", 10, 1);
		HomeScreenCommon.deleteView(Object_Text, "图库");
	}
	/**
	 * 点击应用图标         
	 * @throws UiObjectNotFoundException
	 */
	public static void test_003() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		//判断主屏上是否有相应控件，若没有，则创建一个控件
		boolean bool = false;
		if(!(boolean)excute(Object_Text,Operation_Exists,"电话"))
		{
			bool=true;
			excute(Object_Description,Operation_ClickWait,"应用");
			//滑动找到控件并拖动到指定坐标处
			excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","电话","vertical",
					Integer.toString(HomeScreenCommon.screenWidth/2), 
					Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		}
		excute(Object_Text,Operation_ClickWait,"电话");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/search_box_collapsed");
		//如果创建了新控件，执行完后将其删除
		if(bool)
		{
			excute(Object_Device, Operation_PressHome);
			HomeScreenCommon.deleteView(Object_Text, "电话");
		}
	}
	/**
	 * 删除主屏上的控件
	 * @throws UiObjectNotFoundException
	 */
	public static void test_005() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		//在主屏上创建一个控件
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","FM 电台","vertical",
				Integer.toString(HomeScreenCommon.screenWidth/2), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		//删除此控件
		HomeScreenCommon.deleteView(Object_Text, "FM 电台");
		check(Object_Text,Operation_checkNoExist,"FM 电台");
	}
	/**
	 * 删除主屏页面所有图标
	 * @throws UiObjectNotFoundException
	 */
	public static void test_006() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
	    //判断当前有几个屏幕存在
		int screenNumber = ObjectFind.byResourceId("com.android.launcher3:id/page_indicator").getChildCount(); 
		//滑动到最后一个屏幕
		DeviceCommon.swipe("Left", 10, screenNumber-1);
		//再添加一个屏幕并放置控件
		excute(Object_Description,Operation_ClickWait,"应用");
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","FM 电台","vertical",
				Integer.toString(HomeScreenCommon.screenWidth), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		excute(Object_Description,Operation_ClickWait,"应用");
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/apps_list_view","录音机","vertical",
				Integer.toString(HomeScreenCommon.screenWidth), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		DeviceCommon.swipe("Left",10,1);
		//在新创建的屏幕上执行删除控件操作，直至删除整个屏幕
		HomeScreenCommon.deleteView(Object_Text, "FM 电台");
		HomeScreenCommon.deleteView(Object_Text, "录音机");
		//检查屏幕是否被删除
		Assert.assertEquals("屏幕未被成功删除",screenNumber,new UiObject(new UiSelector().resourceId("com.android.launcher3:id/page_indicator"))
					.getChildCount());
	}
	/**
	 * 在主屏的空白部分长按
	 */
	public static void test_007() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		//主屏空白部分检测困难，这里选择长按下面资源ID代表的区域可以达到相同效果
		excute(Object_ResourceId,Operation_LongClick,"com.android.launcher3:id/page_indicator");
		check(Object_Text,Operation_checkExist,"壁纸");
		check(Object_Text,Operation_checkExist,"小部件");
		check(Object_Text,Operation_checkExist,"设置");
	}
	/**
	 *点击壁纸
	 */
	public static void test_008() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		excute(Object_ResourceId,Operation_LongClick,"com.android.launcher3:id/page_indicator");
		excute(Object_Text,Operation_ClickWait,"壁纸");
		check(Object_Text,Operation_checkExist,"设置壁纸");
	}
	/**
	 * 点击小部件
	 */
	public static void test_009() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		excute(Object_ResourceId,Operation_LongClick,"com.android.launcher3:id/page_indicator");
		excute(Object_Text,Operation_ClickWait,"小部件");
		check(Object_ClassName,Operation_checkExist,"android.support.v7.widget.RecyclerView");
		excute(Object_TextScrollWithResId,Operation_DragToCoordinate,"com.android.launcher3:id/widgets_list_view","数字时钟","vertical",
				Integer.toString(HomeScreenCommon.screenWidth/2), 
				Integer.toString(HomeScreenCommon.screenHeight/2), "20");
		check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/the_clock");
		//清场
		HomeScreenCommon.deleteView(Object_ResourceId, "com.android.deskclock:id/the_clock");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.deskclock:id/the_clock");
	}
	/**
	 * 点击设置
	 */
	public static void test_010() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		excute(Object_ResourceId,Operation_LongClick,"com.android.launcher3:id/page_indicator");
		excute(Object_Text,Operation_ClickWait,"设置");
		check(Object_Text,Operation_checkExist,"允许旋转");
	}
	/**
	 * 点击设置后，点击允许旋转
	 */
	public static void test_011() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		excute(Object_ResourceId,Operation_LongClick,"com.android.launcher3:id/page_indicator");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.launcher3:id/settings_button");
		try
		{
			if((boolean)excute(Object_ResourceId,Operation_IsChecked,"android:id/switchWidget"))
			{
				Assert.fail("主屏默认旋转状态有误");
			}
			else
			{
				excute(Object_Text,Operation_ClickWait,"允许旋转");
				check(Object_Text,Operation_checkExist,"开启");
			}
		}
		finally
		{
			if((boolean)excute(Object_ResourceId,Operation_IsChecked,"android:id/switchWidget"))
			{
				excute(Object_Text,Operation_ClickWait,"允许旋转");
				check(Object_Text,Operation_checkExist,"关闭");
			}
		}
	}
	/**
	 * 点击启动器
	 */
	public static void test_012() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		check(Object_Description,Operation_checkExist,"搜索应用");
	}
	/**
	 * 在启动器中任选一个应用
	 */
	public static void test_013() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		excute(Object_Text,Operation_ClickWait,"电话");
		check(Object_Text,Operation_checkExist,"输入姓名或电话号码");
	}
	/**
	 * 在启动器中长按应用图标
	 */
	public static void test_014() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		String str = ObjectFind.byResIdIndex("com.android.launcher3:id/icon","8").getText();
		ObjectFind.byResIdIndex("com.android.launcher3:id/icon","8").longClick();
		excute(Object_Device, Operation_PressHome);
		check(Object_Text,Operation_checkExist,str);
		//清场
		HomeScreenCommon.deleteView(Object_Text, str);
		check(Object_Text,Operation_checkNoExist,str);
	}
	/**
	 * 点击启动器上的搜索图标
	 */
	public static void test_015() throws UiObjectNotFoundException
	{
		excute(Object_Device, Operation_PressHome);
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		String str = ObjectFind.byResIdIndex("com.android.launcher3:id/icon","8").getText();
		ObjectFind.byResIdIndex("com.android.launcher3:id/icon","8").longClick();
		excute(Object_Device, Operation_PressHome);
		check(Object_Text,Operation_checkExist,str);
		DeviceCommon.enterApp(Devices_Desc_Applycation);
		excute(Object_Description,Operation_ClickWait,"搜索应用");
		Wait(1000);
		String text = (String)excute(Object_ResourceId,Operation_GetText,"com.android.launcher3:id/search_box_input");
		UiDevice.getInstance().click(HomeScreenCommon.screenWidth/3, 5*HomeScreenCommon.screenHeight/6);
		Wait(1000);
		//检查前后两次操作是否引起搜索框内容变化
		Assert.assertNotSame("输入法不能正常使用",text,
				(String)excute(Object_ResourceId,Operation_GetText,"com.android.launcher3:id/search_box_input"));
		//清场
		excute(Object_Device, Operation_PressHome);
		HomeScreenCommon.deleteView(Object_Text, str);
		check(Object_Text,Operation_checkNoExist,str);
	}
}
