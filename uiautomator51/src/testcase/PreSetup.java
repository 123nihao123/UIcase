package testcase;

import java.io.IOException;

import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.DeviceCommon;
import framework.common.SettingCommon;

public class PreSetup extends UiAutomatorTestCase
{
	protected void setUp()
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
	}
	
	protected void tearDown() 
    {
		
    }
	
	public void test_000() throws UiObjectNotFoundException, RemoteException, IOException 
	{	
		DeviceCommon.enterApp(Devices_Desc_Setting);
		DeviceCommon.removePermissions();
		ClearBackgroundApp();
		DeviceCommon.runADBCommand("settings put system screen_off_timeout 1800000");
		DeviceCommon.changeSIMName();

	}
}