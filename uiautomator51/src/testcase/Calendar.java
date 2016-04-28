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
	public static void test_023()
	{
		CalendarCommon.switchMode("日程");
		check(Object_ResIdContainsText, Operation_checkExist, "触摸可查看");
	}
	public static void test_024()
	{
		CalendarCommon.enternewCalendar();
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/start_date");
		check(Object_Text, Operation_checkExist, "zhanxun");
	}
	
	/**
	 * 同步日历
	 */
	public static void test_044()
	{
		//主体
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"显示的日历");
		excute(Object_Text,Operation_ClickWait,"同步的日历");
		check(Object_ResIdText, Operation_checkExist, "android:id/action_bar_title","同步的日历");
	}
	
	/**
	 * 常规设置
	 */
	public static void test_047()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		check(Object_ResIdText, Operation_checkExist, "android:id/action_bar_title","常规设置");
	}
	
	/**
	 * 常规设置--隐藏您不参加的活动
	 */
	public static void test_048()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"隐藏您不参加的活动");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/checkbox","0");
		//清场
		excute(Object_Text,Operation_ClickWait,"隐藏您不参加的活动");
	}
	
	/**
	 * 常规设置--显示周数
	 */
	public static void test_049()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"显示周数");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/checkbox","1");
		//清场
		excute(Object_Text,Operation_ClickWait,"显示周数");
	}
	
	/**
	 * 常规设置--一周起始日
	 */
	public static void test_050()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		check(Object_Text,Operation_checkExist,"星期六");
		check(Object_Text,Operation_checkExist,"星期日");
		check(Object_Text,Operation_checkExist,"星期一");
	}
	
	/**
	 * 常规设置--语言区域的默认设置
	 */
	public static void test_052()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","语言区域的默认设置");
	}
	
	/**
	 * 常规设置--一周起始日-周六
	 */
	public static void test_053()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		excute(Object_Text,Operation_ClickWait,"星期六");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		CalendarCommon.switchMode("月");
		check(Object_ResIdText,Operation_checkExist,"com.android.calendar:id/d0_label","周六");
		//清场
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_ClickWait,"常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		excute(Object_Text,Operation_ClickWait,"语言区域的默认设置");
	}
	
	/**
	 * 常规设置--一周起始日-周日
	 */
	public static void test_054()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		excute(Object_Text,Operation_ClickWait,"星期日");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		CalendarCommon.switchMode("月");
		check(Object_ResIdText,Operation_checkExist,"com.android.calendar:id/d0_label","周日");
		//清场
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_ClickWait,"常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		excute(Object_Text,Operation_ClickWait,"语言区域的默认设置");
	}
	
	/**
	 * 常规设置--一周起始日-周一
	 */
	public static void test_055()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		excute(Object_Text,Operation_ClickWait,"星期一");
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		CalendarCommon.switchMode("月");
		check(Object_ResIdText,Operation_checkExist,"com.android.calendar:id/d0_label","周一");
		//清场
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text,Operation_ClickWait,"设置");
		excute(Object_Text,Operation_ClickWait,"常规设置");
		excute(Object_Text,Operation_ClickWait,"一周起始日");
		excute(Object_Text,Operation_ClickWait,"语言区域的默认设置");
	}
	
	/**
	 * 常规设置--家里时区
	 */
	public static void test_056()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"采用家里的时区");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/checkbox","3");
		check(Object_Text,Operation_EnabledTrue,"家里时区");
		//清场
		excute(Object_Text,Operation_ClickWait,"采用家里的时区");
	}
	
	/**
	 * 常规设置--家里时区
	 */
	public static void test_057()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"采用家里的时区");
		excute(Object_Text,Operation_ClickWait,"家里时区");
		check(Object_Text,Operation_EnabledTrue,"中国标准时间");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_Device, Operation_PressBack);
		excute(Object_Text,Operation_ClickWait,"采用家里的时区");
	}
	
	/**
	 * 常规设置--清除搜索记录
	 */
	public static void test_058()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_Text,Operation_ClickWait,"清除搜索记录");
	}
	
	/**
	 * 常规设置--通知
	 */
	public static void test_059()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/checkbox","3");
	}
	
	/**
	 *常规设置-- 默认选项是10分钟
	 */
	public static void test_060()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_TextScroll,Operation_Exists,"默认提醒时间","vertical");
		check(Object_ResIdText,Operation_checkExist,"android:id/summary","10 分钟");
	}
	
	/**
	 *常规设置-- 快速回复
	 */
	public static void test_061()
	{
		//主体
		CalendarCommon.enterSettings("常规设置");
		excute(Object_TextScroll,Operation_Exists,"快速回复","vertical");
		excute(Object_Text,Operation_ClickWait,"快速回复");
		check(Object_Text,Operation_checkExist,"先开始，不用等我。");
		check(Object_Text,Operation_checkExist,"我会迟到几分钟。");
		check(Object_Text,Operation_checkExist,"我大概再过 10 分钟就可以到了。");
	}
	
	/**
	 *常规设置-- 关于日历
	 */
	public static void test_062()
	{
		//主体
		CalendarCommon.enterSettings("关于日历");
		check(Object_ResIdText,Operation_checkExist,"android:id/action_bar_title","关于日历");
	}
	
	/**
	 *常规设置--设置-添加账户
	 */
	public static void test_063()
	{
		//主体
		CalendarCommon.enterSettings("添加帐户");
		excute(Object_Text,Operation_WaitForExists,"帐户设置","3000");
		check(Object_Text,Operation_checkExist,"帐户设置");
	}
	
}
