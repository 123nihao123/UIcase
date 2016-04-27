package testcase;

import static framework.data.ObjectType.Object_Device;
import static framework.data.OperationType.Operation_WakeUp;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.ClearBackgroundApp;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.excute;
import android.os.RemoteException;

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

}
