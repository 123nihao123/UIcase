package testcase;

import static framework.data.ObjectType.Object_Device;
import static framework.data.OperationType.Operation_WakeUp;
import static framework.data.ResIdTextAndDesc.Devices_Desc_Call;
import static framework.data.ResIdTextAndDesc.Devices_Desc_Message;
import static framework.excute.Excute.ClearBackgroundApp;
import static framework.excute.Excute.Wait;
import static framework.excute.Excute.excute;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import framework.common.CallLogCommon;
import framework.common.DeviceCommon;
import framework.common.PhoneCommon;

public class Phone extends UiAutomatorTestCase
{

	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Call);
   }
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	
	/**
	 * Workaround of  not working @BeforeClass @AfterClass
	 * Compile is ok but @BeforeClass @AfterClass can not be recognized because of extending UiAutomatorTestCase
	 */
	public void test_0000(){  
        System.out.println("before class!!!!!!!!!!!!!!!!!!!!!!!!");
        PhoneCommon.fillPhoneData();
 //       CallLogCommon.fillCallLogData();
    }
	
}