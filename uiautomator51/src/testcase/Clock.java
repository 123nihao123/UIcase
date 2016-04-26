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
