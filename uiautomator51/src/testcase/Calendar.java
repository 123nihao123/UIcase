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
	 * 新建日程界面
	 */
	public static void test_019()
	{
		//主体
		CalendarCommon.switchMode("日");
		Rect ModArea = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calendar:id/switcher");
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		UiDevice.getInstance().click(x / 2, y / 2);
		UiDevice.getInstance().click(x / 2, y / 2);
		check(Object_Text, Operation_checkExist, "完成");
	}
	/**
	 * 日程模式
	 */
	public static void test_023()
	{
		//主体
		CalendarCommon.switchMode("日程");
		check(Object_ClassContainsText, Operation_checkExist, "android.widget.TextView", "触摸可查看");
	}
	/**
	 * 新建日程
	 */
	public static void test_024()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_ResourceId, Operation_SetText, "com.android.calendar:id/title", "zhanxun");
		excute(Object_ResourceId, Operation_SetText, "com.android.calendar:id/location", "Nanjing");
		excute(Object_Text, Operation_ClickWait, "完成");
		CalendarCommon.switchMode("日程");
		check(Object_Text, Operation_checkExist, "zhanxun");
	}
	/**
	 * 开始日期
	 */
	public static void test_025()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/start_date");
		check(Object_ResourceId, Operation_checkExist, "com.android.calendar:id/date_picker_month");//月id
	}
	/**
	 * 开始时间
	 */
	public static void test_026()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/start_time");
		check(Object_ResourceId, Operation_checkExist, "com.android.calendar:id/time_display");//时间id
	}
	/**
	 * 点击时区
	 */
	public static void test_027()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/timezone_button");
		check(Object_ResIdText, Operation_checkExist, "com.android.calendar:id/searchBox", "   输入国家/地区名称");
	}
	/**
	 * 重复
	 */
	public static void test_028()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_TextScroll, Operation_Exists, "重复", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/rrule");
		check(Object_Text, Operation_checkExist, "每周重复");
	}
	/**
	 * 重复开关状态
	 */
	public static void test_029()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_TextScroll, Operation_Exists, "重复", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/rrule");
		if(!(boolean) excute(Object_ResourceId, Operation_IsChecked, "com.android.calendar:id/repeat_switch"))
			excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeat_switch");
		check(Object_ResourceId, Operation_CheckedTrue, "com.android.calendar:id/repeat_switch");//重复按钮
		//清场
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeat_switch");
	}
	/**
	 * 重复选项
	 */
	public static void test_030()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_TextScroll, Operation_Exists, "重复", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/rrule");
		if(!(boolean) excute(Object_ResourceId, Operation_IsChecked, "com.android.calendar:id/repeat_switch"))
			excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeat_switch");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/spinner_item");
		check(Object_Text, Operation_checkExist, "每天重复");
		check(Object_Text, Operation_checkExist, "每周重复");
		check(Object_Text, Operation_checkExist, "每月重复");
		check(Object_Text, Operation_checkExist, "每年重复");
		//清场
		excute(Object_Device, Operation_PressBack);
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeat_switch");
	}
	/**
	 * 每天重复
	 */
	public static void test_031()
	{
		//主体
		CalendarCommon.repeat("每天重复");
		check(Object_ResIdText, Operation_checkExist, "com.android.calendar:id/spinner_item", "每天重复");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/endSpinner");
		excute(Object_Text, Operation_ClickWait, "无限重复");
		check(Object_Text, Operation_checkExist, "无限重复");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/endSpinner");
		excute(Object_Text, Operation_ClickWait, "直到某个日期");
		check(Object_Text, Operation_checkExist, "直到");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/endSpinner");
		excute(Object_Text, Operation_ClickWait, "限定次数");
		check(Object_Text, Operation_checkExist, "重复");
	}
	/**
	 * 每周重复
	 */
	public static void test_032()
	{
		//主体
		CalendarCommon.repeat("每周重复");
		check(Object_ResIdText, Operation_checkExist, "com.android.calendar:id/spinner_item", "每周重复");
		if(!(boolean) excute(Object_Text, Operation_IsChecked, "周日"))
			excute(Object_Text, Operation_ClickWait, "周日");
		if(!(boolean) excute(Object_Text, Operation_IsChecked, "周一"))
			excute(Object_Text, Operation_ClickWait, "周一");
		excute(Object_Text, Operation_ClickWait, "完成");
		check(Object_ResIdContainsText, Operation_checkExist, "com.android.calendar:id/rrule", "每1周的周日, 周一");
	}
	/**
	 * 每个月重复
	 */
	public static void test_033()
	{
		//主体
		CalendarCommon.repeat("每月重复");
		check(Object_ResIdText, Operation_checkExist, "com.android.calendar:id/spinner_item", "每月重复");
		if(!(boolean) excute(Object_Text, Operation_IsChecked, "在每个月的同一天"))
		{
			excute(Object_Text, Operation_ClickWait, "在每个月的同一天");
			check(Object_Text, Operation_CheckedTrue, "在每个月的同一天");
		}	
		if(!(boolean) excute(Object_ResourceId, Operation_IsChecked, "com.android.calendar:id/repeatMonthlyByNthDayOfTheWeek"))
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeatMonthlyByNthDayOfTheWeek");
			check(Object_ResourceId, Operation_CheckedTrue, "com.android.calendar:id/repeatMonthlyByNthDayOfTheWeek");
		}
	}
	/**
	 * 每年重复
	 */
	public static void test_034()
	{
		//主体
		CalendarCommon.repeat("每年重复");
		check(Object_ResIdText, Operation_checkExist, "com.android.calendar:id/spinner_item", "每年重复");
		if(!(boolean) excute(Object_Text, Operation_IsChecked, "在每个月的同一天"))
		{
			excute(Object_Text, Operation_ClickWait, "在每个月的同一天");
			check(Object_Text, Operation_CheckedTrue, "在每个月的同一天");
		}	
		if(!(boolean) excute(Object_ResourceId, Operation_IsChecked, "com.android.calendar:id/repeatMonthlyByNthDayOfTheWeek"))
		{
			excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/repeatMonthlyByNthDayOfTheWeek");
			check(Object_ResourceId, Operation_CheckedTrue, "com.android.calendar:id/repeatMonthlyByNthDayOfTheWeek");
		}
	}
	/**
	 * 提醒设置
	 */
	public static void test_035()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_TextScroll, Operation_Exists, "提醒", "vertical");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/text1", "10 分钟");
		check(Object_Text, Operation_checkExist, "15 分钟");
		check(Object_Text, Operation_checkExist, "20 分钟");
	}
	/**
	 * 将我的状态设置为
	 */
	public static void test_036()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_TextScroll, Operation_Exists, "将我的状态显示为", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/availability");
		check(Object_Text, Operation_checkExist, "忙碌");
		check(Object_Text, Operation_checkExist, "有空");
	}
	/**
	 * 活动性质
	 */
	public static void test_037()
	{
		//主体
		CalendarCommon.createEvent();
		excute(Object_TextScroll, Operation_Exists, "活动性质", "vertical");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.calendar:id/visibility");
		check(Object_Text, Operation_checkExist, "默认");
		check(Object_Text, Operation_checkExist, "不公开");
		check(Object_Text, Operation_checkExist, "公开");
	}
	/**
	 * 选择日期
	 */
	public static void test_039()
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "选择日期");
		String desc = (String) excute(Object_ClassIndex, Operation_GetDesc, "android.view.View", "15");
		System.out.println(desc);
		String[] name =desc.split(" ");
		excute(Object_Description, Operation_ClickWait, desc);
		excute(Object_Text, Operation_ClickWait, "完成");
		check(Object_ResIdContainsText, Operation_checkExist, "com.android.calendar:id/top_button_date", name[0]);
	}
	/**
	 * 搜索
	 */
	public static void test_040()
	{
		//前提
		CalendarCommon.createEvent();
		excute(Object_ResourceId, Operation_SetText, "com.android.calendar:id/title", "zhanxun");
		excute(Object_ResourceId, Operation_SetText, "com.android.calendar:id/location", "Nanjing");
		excute(Object_Text, Operation_ClickWait, "完成");
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "android:id/search_src_text", "zhanxun");
		excute(Object_Device, Operation_PressEnter);
		check(Object_ResIdText, Operation_checkExist, "com.android.calendar:id/title", "zhanxun");
	}
	/**
	 * 清除搜索
	 */
	public static void test_041()
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "搜索");
		excute(Object_ResourceId, Operation_SetText, "android:id/search_src_text", "zhanxun");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/search_close_btn");
		check(Object_ResIdText, Operation_checkNoExist, "android:id/search_src_text", "zhanxun");
	}
	/**
	 * 搜索菜单
	 */
	public static void test_042()
	{
		//主体
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "搜索");
		excute(Object_Description, Operation_ClickWait, "更多选项");
		check(Object_Text, Operation_checkExist, "今天");
		check(Object_Text, Operation_checkExist, "新建活动");
		check(Object_Text, Operation_checkExist, "刷新");
		check(Object_Text, Operation_checkExist, "选择日期");
		check(Object_Text, Operation_checkExist, "显示的日历");
		check(Object_Text, Operation_checkExist, "设置");
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
