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

import framework.common.DeviceCommon;
import framework.common.ClockCommon;

public class Clock extends UiAutomatorTestCase
{
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Clock);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	

	/**
	 * 进入闹钟界面
	 */
	public static void test_012()
	{  
		//主体
        ClockCommon.switchMode("闹钟");
        check(Object_ResIdInstance,Operation_TextEqualTrue,"com.android.deskclock:id/digital_clock","0","上午8:30");
        check(Object_ResIdInstance,Operation_TextEqualTrue,"com.android.deskclock:id/digital_clock","1","上午9:00");
        check(Object_ResIdInstance,Operation_checkNoExist,"com.android.deskclock:id/digital_clock","2");
        check(Object_ResIdInstance,Operation_TextEqualTrue,"com.android.deskclock:id/daysOfWeek","0","周一, 周二, 周三, 周四, 周五");
        check(Object_ResIdInstance,Operation_TextEqualTrue,"com.android.deskclock:id/daysOfWeek","1","周日, 周六");
        check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.deskclock:id/onoff","0");
        check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.deskclock:id/onoff","1");
    }
	/**
	 * 打开一个闹钟开关
	 */
	public static void test_013()
	{  
		//主体
        ClockCommon.switchMode("闹钟");
        excute(Object_ResIdInstance,Operation_ClickWait,"com.android.deskclock:id/onoff","0");
        check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.deskclock:id/onoff","0");
        //清场
        excute(Object_ResIdInstance,Operation_ClickWait,"com.android.deskclock:id/onoff","0");  
    }
	/**
	 * 点击闹钟上的8：30部分
	 */
	public static void test_014()
	{  
		//主体
        ClockCommon.switchMode("闹钟");
        excute(Object_Text,Operation_ClickWait,"上午8:30");
        check(Object_ResourceId,Operation_checkExist,"android:id/radial_picker");
    }
	/**
	 * 点击闹钟上的8：30下边的^这个符号
	 */
	public static void test_015()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        check(Object_Text,Operation_checkExist,"重复");
    }
	/**
	 * 点击重复跳出设置对话框
	 */
	public static void test_016()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_Text,Operation_ClickWait,"重复");
        excute(Object_Text,Operation_ClickWait,"重复");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/repeat_radiogroup");
        //清场
        excute(Object_Text,Operation_ClickWait,"工作日");
        excute(Object_Text,Operation_ClickWait,"确定");
    }
	/**
	 * 重复选择每天
	 */
	public static void test_017()
	{  
		//主体
        ClockCommon.enterRepeatSettings();
        excute(Object_Text,Operation_ClickWait,"每天");
        int count=(Integer)excute(Object_ClassName, Operation_GetChildCount, "android.widget.ListView");
        for(int i=0;i<count;i++)
        	check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/text1",String.valueOf(i));	
        excute(Object_Text,Operation_ClickWait,"确定");
        excute(Object_Description,Operation_ClickWait,"折叠闹钟");
        check(Object_Text,Operation_checkExist,"每天");
        //清场
        ClockCommon.enterRepeatSettings();
        excute(Object_Text,Operation_ClickWait,"工作日");
        excute(Object_Text,Operation_ClickWait,"确定");
    }
	/**
	 * 重复选择工作日
	 */
	public static void test_018()
	{  
		//主体
		ClockCommon.enterRepeatSettings();
        excute(Object_Text,Operation_ClickWait,"工作日");
        int count=(Integer)excute(Object_ClassName, Operation_GetChildCount, "android.widget.ListView");
        for(int i=1;i< count-1;i++)
        	check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/text1",String.valueOf(i));
        check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/text1","0");
        check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/text1",String.valueOf(count-1));
        excute(Object_Text,Operation_ClickWait,"确定");
        excute(Object_Description,Operation_ClickWait,"折叠闹钟");
        check(Object_Text,Operation_checkExist,"周一, 周二, 周三, 周四, 周五");
    }
	/**
	 * 重复选择单次
	 */
	public static void test_019()
	{  
		//主体
        ClockCommon.enterRepeatSettings();
        excute(Object_Text,Operation_ClickWait,"单次");
        int count=(Integer)excute(Object_ClassName, Operation_GetChildCount, "android.widget.ListView");
        for(int i=0;i<count;i++)
        	check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/text1",String.valueOf(i));
        //清场
        excute(Object_Text,Operation_ClickWait,"工作日");
        excute(Object_Text,Operation_ClickWait,"确定");
    }
	
	/**
	 * 勾选对话框上任意星期组合
	 */
	public static void test_021()
	{  
		//主体
        ClockCommon.enterRepeatSettings();
        excute(Object_Text,Operation_ClickWait,"周一");
        check(Object_ResourceId,Operation_CheckedFalse,"com.android.deskclock:id/every_day");
        check(Object_ResourceId,Operation_CheckedFalse,"com.android.deskclock:id/working_days");
        check(Object_ResourceId,Operation_CheckedFalse,"com.android.deskclock:id/never");
        excute(Object_Text,Operation_ClickWait,"确定");
        excute(Object_Description,Operation_ClickWait,"折叠闹钟");
        check(Object_Text,Operation_checkExist,"星期一");
        //清场
        ClockCommon.enterRepeatSettings();
        excute(Object_Text,Operation_ClickWait,"工作日");
        excute(Object_Text,Operation_ClickWait,"确定");
    }
	/**
	 * 在展开的设置页面上点击铃铛图标
	 */
	public static void test_022()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/choose_ringtone");
        if ((Boolean)excute(Object_Text,Operation_Exists,"使用媒体存储完成操作"))
        {
        	check(Object_Text,Operation_checkExist,"使用媒体存储完成操作");
            excute(Object_Text,Operation_ClickWait,"仅此一次");
        }
        else
        {   
        	check(Object_Text,Operation_checkExist,"媒体存储");
        	excute(Object_Text,Operation_ClickWait,"媒体存储");
        	if ((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
        		excute(Object_Text,Operation_ClickWait,"仅此一次");
        }
        String str = (String)excute(Object_ResIdInstance,Operation_GetText,"android:id/text1","1");
        excute(Object_ResIdInstance,Operation_ClickWait,"android:id/text1","1");
        excute(Object_Text,Operation_ClickWait,"确定");
        check(Object_Text,Operation_checkExist,str);
    }
	/**
	 * 在展开的设置页面上点击振动
	 */
	public static void test_023()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_Text,Operation_ClickWait,"振动");
        check(Object_Text,Operation_CheckedTrue,"振动");
        excute(Object_Text,Operation_ClickWait,"振动");
        check(Object_Text,Operation_CheckedFalse,"振动");
    }
	/**
	 * 点击 闹钟时长10分钟
	 */
	public static void test_024()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_Text,Operation_ClickWait,"闹铃时长 10 分钟");
        check(Object_Text,Operation_checkExist,"闹铃时长");
    }
	/**
	 * 在对话框上点击1分钟
	 */
	public static void test_025()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_Text,Operation_ClickWait,"闹铃时长 10 分钟");
        excute(Object_Text,Operation_ClickWait,"1 分钟");
        check(Object_Text,Operation_checkExist,"闹铃时长 1 分钟");
        //清场
        excute(Object_Text,Operation_ClickWait,"闹铃时长 1 分钟");
        excute(Object_Text,Operation_ClickWait,"10 分钟");
    }
	/**
	 * 弹出删除框
	 */
	public static void test_026()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_Description,Operation_ClickWait,"删除闹钟");
        check(Object_Text,Operation_checkExist,"闹钟已删除");
        check(Object_Text,Operation_checkExist,"撤消");
        //清场
        excute(Object_Text,Operation_ClickWait,"撤消");
    }
	/**
	 * 撤销删除
	 */
	public static void test_027()
	{  
		//主体
        ClockCommon.enterAlarmSettings();
        excute(Object_Description,Operation_ClickWait,"删除闹钟");
        excute(Object_Text,Operation_ClickWait,"撤消");
        check(Object_Text,Operation_checkExist,"上午8:30");  
    }
	/**
	 * 新建闹钟
	 */
	public static void test_028()
	{  
		//主体
		ClockCommon.switchMode("闹钟");
		excute(Object_Description,Operation_ClickWait,"添加闹钟");
		excute(Object_Text,Operation_ClickWait,"上午");
		excute(Object_Description,Operation_ClickWait,"12");
		excute(Object_Description,Operation_ClickWait,"0");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text,Operation_checkExist,"上午12:00");
		//清场
		excute(Object_Description,Operation_ClickWait,"删除闹钟");
    }
	/**
	 * 更多菜单
	 */
	public static void test_029()
	{  
		//主体
		ClockCommon.switchMode("闹钟");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"设置");
		excute(Object_Text,Operation_ClickWait,"设置");
		check(Object_Text,Operation_checkExist,"时钟");
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 进入倒计时
	 */
	public void test_030(){  
		//主体
		ClockCommon.switchMode("计时器");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/timer_time_text");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/key_middle");
    }
	
	/**
	 * 进入倒计时--存在播放图标,点击播放开始
	 */
	public void test_031(){  
		//主体
		ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"1");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        check(Object_ResIdText,Operation_checkExist,"com.android.deskclock:id/seconds","10");
        check(Object_Description,Operation_checkExist,"开始");
        excute(Object_Description,Operation_ClickWait,"开始");
        check(Object_Description,Operation_checkExist,"停止");
        //清场
        excute(Object_Description,Operation_ClickWait,"删除");
    }
	
	/**
	 * 进入倒计时--存在播放图标,下边有删除、暂停/开始、添加倒计时的图标
	 */
	public void test_032(){  
		//主体
		ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"2");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        excute(Object_Description,Operation_ClickWait,"开始");
        check(Object_Description,Operation_checkExist,"停止");
        check(Object_Description,Operation_checkExist,"删除");
        check(Object_Description,Operation_checkExist,"添加计时器");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/timer_time_text");
        //清场
        excute(Object_Description,Operation_ClickWait,"删除");
    }
	
	/**
	 * 进入倒计时--在对话框上输入“1”，页面上标签位置显示为“1"
	 */
	public void test_033(){  
		//主体
        ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"2");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/timer_label");
        excute(Object_ClassName,Operation_SetText,"android.widget.EditText","1");
        excute(Object_Text,Operation_ClickWait,"确定");
        check(Object_ResIdText,Operation_checkExist,"com.android.deskclock:id/timer_label","1");
        //清场
        excute(Object_Description,Operation_ClickWait,"删除");
    }
	
	/**
	 * 进入倒计时--恢复最初按键
	 */
	public void test_034(){  
		//主体
		ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"2");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_Description,Operation_ClickWait,"停止");
        excute(Object_Description,Operation_ClickWait,"重置");
        check(Object_Description,Operation_checkExist,"0 分钟 20 秒");
        //清场
        excute(Object_Description,Operation_ClickWait,"删除");
    }
	
	/**
	 * 进入倒计时--暂停
	 */
	public void test_035(){  
		//主体
		ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"2");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_Description,Operation_ClickWait,"停止");
        check(Object_Description,Operation_checkExist,"开始");
        //清场
        excute(Object_Description,Operation_ClickWait,"删除");
    }
	
	/**
	 * 进入倒计时--继续播放
	 */
	public void test_036(){  
		//主体
        ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"2");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        excute(Object_Description,Operation_ClickWait,"开始");
        String time = (String)excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/timer_time_text");
        System.out.println("starttime: "+time);
        String time1 = (String)excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/timer_time_text");
        System.out.println("starttime: "+time1);
        Assert.assertNotSame(time1, time);
        //清场
        excute(Object_Description,Operation_ClickWait,"删除");
    }
	

	/**
	 * 进入倒计时--删除倒计时
	 */
	public void test_037(){  
		//主体
		ClockCommon.switchMode("计时器");
        excute(Object_Text,Operation_ClickWait,"2");
        excute(Object_ResIdText,Operation_ClickWait,"com.android.deskclock:id/key_middle","0");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_Description,Operation_ClickWait,"删除");
        check(Object_Description,Operation_checkNoExist,"删除");
    }
	
	
	
	/**
	 * 进入计时
	 */
	public void test_039(){  
		//主体
		ClockCommon.switchMode("秒表");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/stopwatch_time_text");
        check(Object_Description,Operation_checkExist,"开始");
    }
	
	/**
	 * 进入计时--开始
	 */
	public void test_040(){  
		//主体
        ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_Description,Operation_ClickWait,"停止");
        String time = (String)excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/stopwatch_time_text");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_Description,Operation_ClickWait,"停止");
        String time1 = (String)excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/stopwatch_time_text");
        Assert.assertNotSame(time, time1);
        //清场
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	/**
	 * 进入计时--最下端有计次、暂停、停止图标
	 */
	public void test_041(){  
		//主体
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        check(Object_ResIdDesc,Operation_checkExist,"com.android.deskclock:id/right_button","停止");
        check(Object_ResIdDesc,Operation_checkExist,"com.android.deskclock:id/fab","停止");
        check(Object_ResIdDesc,Operation_checkExist,"com.android.deskclock:id/left_button","一圈");
        //清场
        excute(Object_Description,Operation_ClickWait,"停止");
        excute(Object_Description,Operation_ClickWait,"重置");
        
    }
	
	/**
	 * 进入计时--最下端有恢复最初、暂停、分享图标
	 */
	public void test_042(){  
		//主体
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_Description,Operation_ClickWait,"停止");
        check(Object_Description,Operation_checkExist,"重置");
        check(Object_Description,Operation_checkExist,"开始");
        check(Object_Description,Operation_checkExist,"分享");
        //清场
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	/**
	 * 进入计时--记次
	 */
	public void test_043(){  
		//主体
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/left_button");
        check(Object_ResIdText,Operation_checkExist,"com.android.deskclock:id/lap_number","# 2");
        //清场
        excute(Object_Description,Operation_ClickWait,"停止");
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	/**
	 * 进入计时--计时暂停
	 */
	public void test_044(){  
		//主体
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/fab");
        String time =  (String) excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/stopwatch_time_text");
        String time1 =  (String) excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/stopwatch_time_text");
        Assert.assertEquals(time, time1);
        //清场
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	/**
	 * 进入计时--计时停止
	 */
	public void test_045(){  
		//主体
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/right_button");
        check(Object_Description,Operation_checkExist,"分享");
        //清场
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	/**
	 * 进入计时--计时暂停，开始
	 */
	public void test_046(){  
		//前提
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/fab");
        //主体
        excute(Object_Description,Operation_ClickWait,"开始");
        String time =  (String) excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/stopwatch_time_text");
        String time1 =  (String) excute(Object_ResourceId,Operation_GetDesc,"com.android.deskclock:id/stopwatch_time_text");
        Assert.assertNotSame(time, time1);
        //清场
        excute(Object_Description,Operation_ClickWait,"停止");
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	/**
	 * 进入计时--计时暂停，恢复初始状态
	 */
	public void test_047(){  
		//前提
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/fab");
        //主体
        excute(Object_Description,Operation_ClickWait,"重置");
        check(Object_ResIdDesc,Operation_checkExist,"com.android.deskclock:id/stopwatch_time_text","0 分钟 0 秒");
    }
	
	/**
	 * 进入计时--计时暂停，分享
	 */
	public void test_048(){  
		//前提
		ClockCommon.switchMode("秒表");
        excute(Object_Description,Operation_ClickWait,"开始");
        excute(Object_ResourceId,Operation_ClickWait,"com.android.deskclock:id/fab");
        //主体
        excute(Object_Description,Operation_ClickWait,"分享");
        check(Object_Text,Operation_checkExist,"信息");
        check(Object_Text,Operation_checkExist,"电子邮件");
        check(Object_Text,Operation_checkExist,"蓝牙");
        //清场
        excute(Object_Device, Operation_PressBack);
        excute(Object_Description,Operation_ClickWait,"重置");
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
