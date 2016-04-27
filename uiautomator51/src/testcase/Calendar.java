package testcase;

import static framework.data.ObjectType.Object_Device;
import static framework.data.ObjectType.Object_ResourceId;
import static framework.data.ObjectType.Object_Text;
import static framework.data.OperationType.Operation_GetBounds;
import static framework.data.OperationType.Operation_WakeUp;
import static framework.data.OperationType.Operation_checkExist;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.ClearBackgroundApp;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.check;
import static framework.excute.Excute.excute;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CalendarCommon;
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
	 * 
	 */
	public static void test_001()
	{
		CalendarCommon.switchMode("日程");
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
