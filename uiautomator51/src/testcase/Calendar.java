package testcase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import android.graphics.Rect;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CalendarCommon;
import framework.common.CameraCommon;
import framework.common.DeviceCommon;

public class Calendar extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Calender);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 进入日历
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/action_today");
	}
	/**
	 * 日历显示当前时间
	 */
	public static void test_002()
	{
		//主体
		CalendarCommon.switchMode("日");
		SimpleDateFormat dataformat =  new SimpleDateFormat("yyyy年M月d日");   
		Date now = new Date();
		String time=dataformat.format(now);
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.calendar:id/top_button_date",time);
	}
	/**
	 * 点击左上角的三角号部分
	 */
	public static void test_003()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calendar:id/top_button_date");
		String [] str={"日","周","月","日程"};
		CameraCommon.checkForExist(str);
	}
	/**
	 * 点击下拉框上的“日”功能项
	 */
	public static void test_004()
	{
		//主体
		CalendarCommon.switchMode("日");
		SimpleDateFormat dataformat =  new SimpleDateFormat("yyyy年M月d日");   
		Date now = new Date();
		String time=dataformat.format(now);
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/main_pane");
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.calendar:id/top_button_date",time);
	}
	/**
	 * 点击下拉框上的“周”功能项
	 */
	public static void test_005()
	{
		//主体
		CalendarCommon.switchMode("周");
		SimpleDateFormat dataformat =  new SimpleDateFormat("yyyy年M月");   
		Date now = new Date();
		String time=dataformat.format(now);
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/main_pane");
		check(Object_ResourceId,Operation_TextEqualTrue,"com.android.calendar:id/top_button_date",time);
	}
	/**
	 * 点击下拉框上的“月”功能项
	 */
	public static void test_006()
	{
		//主体
		CalendarCommon.switchMode("月");
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/month");
	}
	/**
	 * 点击下拉框上的“日程”功能项
	 */
	public static void test_007()
	{
		//主体
		CalendarCommon.switchMode("日程");
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/agenda_events_list");
	}
	/**
	 * 点击页面上的更多（菜单选项）
	 */
	public static void test_011()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		String [] str={"新建活动","刷新","选择日期","搜索","显示的日历","设置"};
		CameraCommon.checkForExist(str);
	}
	/**
	 * 点击新建活动功能项
	 */
	public static void test_012()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"新建活动");
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/edit_event");
	}
	/**
	 * 点击选择日期功能项
	 */
	public static void test_014()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"选择日期");
		check(Object_ResourceId,Operation_checkExist,"com.android.calendar:id/day_picker_selected_date_layout");
	}
	/**
	 * 点击搜索功能项
	 */
	public static void test_015()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"搜索");
		check(Object_Text,Operation_checkExist,"搜索…");
	}
	/**
	 * 点击显示日历
	 */
	public static void test_016()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"显示的日历");
		check(Object_Text,Operation_checkExist,"同步的日历");
	}
	/**
	 * 点击设置
	 */
	public static void test_017()
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"设置");
		check(Object_Text,Operation_checkExist,"常规设置");
	}
	/**
	 * 新建日程
	 */
	public static void test_019()
	{
		CalendarCommon.switchMode("日");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calendar:id/switcher");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x / 2, y / 2);
		UiDevice.getInstance().click(x / 2, y / 2);
		check(Object_Text, Operation_checkExist, "完成");
	}
}
