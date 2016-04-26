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
        excute(Object_Description,Operation_ClickWait,"计时器");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/timer_time_text");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/key_middle");
    }
	
	/**
	 * 进入倒计时--存在播放图标
	 */
	public void test_031(){  
		//主体
        excute(Object_Description,Operation_ClickWait,"计时器");
        excute(Object_Text,Operation_ClickWait,"1");
        excute(Object_Text,Operation_ClickWait,"0");
        check(Object_ResourceId,Operation_checkExist,"com.android.deskclock:id/key_middle");
    }
	
	
	
	

}
