package testcase;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.DeviceParameter.*;
import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.DeviceCommon;
import framework.common.SettingCommon;

public class PreSetup extends UiAutomatorTestCase
{
	public static String sim1Num, sim2Num;
	public static String simFlag; //"00" for no sim, "10" for sim1, "01" for sim2, "11" for sim1&sim2
	
	public PreSetup(){}
	
	public PreSetup(String option) throws RemoteException, UiObjectNotFoundException, IOException{
		if(option.equals("SIM")){
			initialSIM();
		}
	}
	
	protected void setUp() 
    {
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
	}
	
	protected void tearDown() 
    {
		
    }
	
	public void test_000() throws UiObjectNotFoundException, RemoteException 
	{	
		DeviceCommon.enterApp(Devices_Desc_Setting);
		DeviceCommon.removePermissions();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_Text,Operation_ClickWait,"SIM 卡");
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMNames("SIM 卡插槽 1","SIM1");
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMNames("SIM 卡插槽 2","SIM2");
		excute(Object_Device, Operation_PressBack);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		excute(Object_Text, Operation_ClickWait, "休眠");
		excute(Object_Text, Operation_ClickWait, "30分钟");
	}
	
	public void initialSIM() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		String [] simId = {"SIM1", "SIM2"};
		Map<String, String> map = DeviceCommon.GetSIMInfo();
		switch(map.size()){
		case 0:
			simFlag = "00";
			break;
		case 1:
			Set<String> simInfo = map.keySet();
			Object[] deviceArrary = simInfo.toArray();
			String sim = deviceArrary[0].toString();
			if(sim.equals(simId[0])){
				sim1Num = (String)map.get(simId[0]);
				simFlag = "10";
			}
			else if(sim.equals(simId[1])){
				sim2Num = (String)map.get(simId[1]);
				simFlag = "01";
			}
			break;
		case 2:
			sim1Num = (String)map.get(simId[0]);
			sim2Num = (String)map.get(simId[1]);
			simFlag = "11";
			break;
		default:
			Assert.assertTrue("None sim status !!!",false);
			break;
		}
	}
}