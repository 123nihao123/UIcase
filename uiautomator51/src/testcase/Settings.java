package testcase;

import java.io.IOException;

import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.CallLogCommon;
import framework.common.SettingCommon;
import framework.common.EmailCommon;
import framework.common.CallCommon;
import framework.driver.ObjectFind;
import framework.driver.OperationUiDevice;
import framework.driver.OperationUiObject;

//插两张SIM卡，SD卡及手机内存中要有预存联系人
public class Settings extends UiAutomatorTestCase {

	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException {
		System.out.println("Enter the setUp!!!");
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Setting);

	}

	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException {
	}
/*
	public void test_000() throws UiObjectNotFoundException, RemoteException {
		DeviceCommon.removePermissions();
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMName("SIM 卡插槽 1", "SIM1");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMName("SIM 卡插槽 2", "SIM2");
		Wait(500);
	}

	// 设置VPN，
	public void test_001_001() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterVPN();
		excute(Object_ResourceId, Operation_ClickWait,
				"com.android.settings:id/vpn_create");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			SettingCommon.SetPIN();
			SettingCommon.SetVPN("PPTP", "vpn1.e2010.mobility-lab.com");
		} else {
			SettingCommon.SetVPN("PPTP", "vpn1.e2010.mobility-lab.com");
		}
		// Wait(500);
		check(Object_Text, Operation_checkExist, "PPTP");

	}

	public void test_001_002() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterVPN();
		SettingCommon.ConnectVPN("PPTP", "vpnuser", " Password123");
	}

	// 浏览Facebook
	public void test_001_003() throws UiObjectNotFoundException,
			RemoteException {
		try {
			SettingCommon.ConnectBrowser("www.facebook.com");
		} finally {
			SettingCommon.RemovePIN();
		}

	}

	// 添加L2TPVPN
	public void test_001_004() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterVPN();
		excute(Object_ResourceId, Operation_ClickWait,
				"com.android.settings:id/vpn_create");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			SettingCommon.SetPIN();
			// Wait(500);
			SettingCommon
					.SetL2TPVPN("L2TP", "144.188.130.240", "", "", " test");
		} else {
			SettingCommon
					.SetL2TPVPN("L2TP", "144.188.130.240", "", "", " test");
		}
		// Wait(500);
		check(Object_Text, Operation_Exists, "L2TP");

	}

	// 连接L2TPVPN，上网
	public void test_001_005() throws UiObjectNotFoundException,
			RemoteException {
		try {
			SettingCommon.EnterVPN();
			SettingCommon.ConnectVPN("L2TP", "test3", "Password123");
			Wait(5000);
			SettingCommon.ConnectBrowser("www.facebook.com");
		} finally {
			SettingCommon.RemovePIN();
		}
	}

	// 通过4G拨号
	public void test_001_006() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMove();
		Wait(500);
		SettingCommon.Select4date();
		Wait(5000);
		SettingCommon.endCall();
		// String failcase="test_001_006";
		// OperationUiDevice.takeScreenshot(getUiDevice(), failcase);
	}

	// 通过4G上网
	public void test_001_007() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMove();
		Wait(500);
		SettingCommon.Select4date();
		Wait(1000);
		SettingCommon.ConnectBrowser("www.baidu.com");
	}

	// 通过3G拨号
	public void test_001_008() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMove();
		Wait(500);
		SettingCommon.Select3date();
		Wait(5000);
		SettingCommon.endCall();
	}

	// 通过3G上网
	public void test_001_009() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMove();
		SettingCommon.Select3date();
		SettingCommon.ConnectBrowser("www.baidu.com");
	}

	// 通过2G拨号
	public void test_001_010() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMove();
		SettingCommon.Select2date();
		SettingCommon.endCall();
	}

	// 2G上网
	public void test_001_011() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMove();
		SettingCommon.Select2date();
		SettingCommon.ConnectBrowser("www.baidu.com");
	}

	// 查看流量使用,包含test__002_002
	public void test_002_001() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMoveData();
		Wait(500);
		SettingCommon.GetMoveData();
	}

	// 显示WLAN流量
	public void test_002_003() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMoveData();
		SettingCommon.WifiData("显示WLAN流量");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "隐藏WLAN流量");
	}

	// 隐藏WLAN流量
	public void test_002_004() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.EnterMoveData();
		SettingCommon.WifiData("隐藏WLAN流量");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "显示WLAN流量");
	}

	// 打开，关闭，滑动流量限制
	public void test_002_005() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.OpenDaralimit();
		SettingCommon.CloseDaralimit();
		SettingCommon.DragDaralimit();
		excute(Object_ResourceId, Operation_ClickWait,
				"com.android.settings:id/sweep_warning");
		Wait(500);
		check(Object_ResourceId, Operation_TextEqualTrue,
				"android:id/numberpicker_input", "5120");
	}

	// PIN屏幕锁
	public void test_005_001() throws UiObjectNotFoundException,
			RemoteException {
		try {
			SettingCommon.Entersecurity();
			SettingCommon.SetPIN();
			SettingCommon.unLock();
			check(Object_Text, Operation_checkExist, "屏幕锁定方式");
		} finally {
			SettingCommon.RemovePIN();
		}
	}

	// 修改SIM名称为特殊字符
	public void test_013_001() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMName("SIM 卡插槽 1", "@#$");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMName("SIM 卡插槽 2", "*&^");
		Wait(500);
	}

	// 修改SIM1名称为数字
	public void test_013_003() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMName("SIM 卡插槽 1", "1234567890");
	}

	// 修改SIM2名称为数字
	public void test_013_004() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMName("SIM 卡插槽 2", "0987654321");
	}

	// 设置SIM1为主卡
	public void test_013_005() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMName("SIM 卡插槽 1", "SIM1");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "主卡选择");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
		Wait(500);
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			Wait(30000);
		} else
			Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "5", "SIM1");
	}

	// 设置SIM2为主卡
	public void test_013_006() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		Wait(500);
		SettingCommon.SIMsettings("SIM 卡插槽 2");
		SettingCommon.SIMName("SIM 卡插槽 2", "SIM2");
		excute(Object_Text, Operation_ClickWait, "主卡选择");
		Wait(1000);
		excute(Object_Text, Operation_ClickWait, "SIM2");
		Wait(500);
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
			Wait(40000);
		} else
			Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "5", "SIM2");
	}

	// 主卡在SIM1设置SIM1拨号
	public void test_013_007() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM1");
		SettingCommon.endCall();
	}

	// 主卡在SIM1设置SIM2拨号
	public void test_013_008() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM2");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM2");
		SettingCommon.endCall();
	}

	// 主卡在SIM2设置SIM1拨号
	public void test_013_009() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM1");
		SettingCommon.endCall();
	}

	// 主卡在SIM2设置SIM2拨号
	public void test_013_010() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM2");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM2");
		SettingCommon.endCall();
	}

	// 主卡在SIM1设置SIM1拨号
	public void test_013_011() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM1");
		SettingCommon.endCall();
	}

	// 主卡在SIM1设置SIM2拨号
	public void test_013_012() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM2");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM2");
		SettingCommon.endCall();
	}

	// 主卡在SIM1拨号每次询问选SIM1
	public void test_013_013() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "每次都询问");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "每次都询问");
		SettingCommon.SelectendCall(1);
	}

	// 主卡在SIM1拨号每次都询问选SIM2
	public void test_013_014() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.SelectendCall(2);
	}

	// 主卡在SIM2拨号选SIM1
	public void test_013_015() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		SettingCommon.SIMsettings("SIM 卡插槽 1");
		SettingCommon.SIMName("SIM 卡插槽 1", "SIM1");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM1");
		SettingCommon.endCall();
	}

	// 主卡在SIM2拨号选SIM2
	public void test_013_016() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM2");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "SIM2");
		SettingCommon.endCall();
	}

	// 主卡在SIM2拨号每次都询问选SIM1
	public void test_013_017() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		excute(Object_Text, Operation_ClickWait, "通话");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "每次都询问");
		Wait(500);
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "3", "每次都询问");
		SettingCommon.SelectendCall(1);
	}

	// 主卡在SIM2拨号每次都询问选SIM2
	public void test_013_018() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.SelectendCall(2);
	}

	// 主卡在SIM1信息选SIM1
	public void test_013_019() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "信息");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "4", "SIM1");
		Wait(500);
	}

	// 主卡在SIM1信息选SIM2
	public void test_013_020() throws UiObjectNotFoundException,
			RemoteException {
		test_013_005();
		excute(Object_Text, Operation_ClickWait, "信息");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM2");
		check(Object_ResIdInstance, Operation_TextEqualTrue,
				"android:id/summary", "4", "SIM2");
		Wait(500);
	}

	// 主卡在SIM2信息选SIM1
	public void test_013_021() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		excute(Object_Text, Operation_ClickWait, "信息");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM1");
	}

	// 主卡在SIM2信息选SIM2
	public void test_013_022() throws UiObjectNotFoundException,
			RemoteException {
		test_013_006();
		excute(Object_Text, Operation_ClickWait, "信息");
		Wait(500);
		excute(Object_Text, Operation_ClickWait, "SIM2");
	}

	// 主卡在SIM1信息选SIM1发送短信
	public void test_013_023() throws UiObjectNotFoundException,
			RemoteException {
		test_013_019();
		SettingCommon.SendMS("10010");
		Wait(500);
		check(Object_ResIdDesc, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM1，SIM 卡选择器");

	}

	// 主卡在SIM1信息选SIM1发送彩信
	public void test_013_024() throws UiObjectNotFoundException,
			RemoteException {
		test_013_019();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_ResIdDesc, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM1，SIM 卡选择器");
	}

	// 主卡在SIM1信息选SIM2发送短信
	public void test_013_025() throws UiObjectNotFoundException,
			RemoteException {
		test_013_020();
		SettingCommon.SendMS("10010");
		Wait(500);
		check(Object_ResIdDesc, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM2，SIM 卡选择器");
	}

	// 主卡在SIM1信息选SIM2发送彩信
	public void test_013_026() throws UiObjectNotFoundException,
			RemoteException {
		test_013_020();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_ResIdDesc, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM2，SIM 卡选择器");
	}

	// 主卡在SIM1信息选SIM1发送短信
	public void test_013_027() throws UiObjectNotFoundException,
			RemoteException {
		test_013_020();
		SettingCommon.SendMS("10010");
		Wait(500);
		check(Object_Description, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM1，SIM 卡选择器");
	}

	// 主卡在SIM2信息选SIM1发送彩信
	public void test_013_028() throws UiObjectNotFoundException,
			RemoteException {
		test_013_020();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_Description, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM1，SIM 卡选择器");
	}

	// 主卡在SIM2信息选SIM2发送短信
	public void test_013_029() throws UiObjectNotFoundException,
			RemoteException {
		test_013_020();
		SettingCommon.SendMS("10010");
		Wait(500);
		check(Object_Description, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM2，SIM 卡选择器");
	}

	// 主卡在SIM2信息选SIM2发送彩信
	public void test_013_030() throws UiObjectNotFoundException,
			RemoteException {
		test_013_020();
		SettingCommon.SendMMS("10010");
		Wait(500);
		check(Object_Description, Operation_Exists,
				"com.android.messaging:id/self_send_icon", "已选择SIM2，SIM 卡选择器");
	}

	// 主卡在SIM1信息选SIM1发送禁用SIM1发送短信
	public void test_013_045() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_019();
			SettingCommon.enableCard("开启");
			Wait(500);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM2");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard("关闭");
		}

	}

	// 主卡在SIM1信息选SIM2发送禁用SIM1发送短信
	public void test_013_046() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_020();
			SettingCommon.enableCard("开启");
			Wait(500);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM2");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard("关闭");
		}
	}

	// 主卡在SIM1信息选SIM1发送禁用SIM2发送短信
	public void test_013_047() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_019();
			SettingCommon.enableCard2("开启");
			Wait(500);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM1");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard2("关闭");
		}

	}

	// 主卡在SIM1信息选SIM1发送禁用SIM2发送短信
	public void test_013_048() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_020();
			SettingCommon.enableCard2("开启");
			Wait(500);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM1");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard2("关闭");
		}
	}

	// 主卡在SIM2信息选SIM1发送禁用SIM1发送短信
	public void test_013_049() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_021();
			SettingCommon.enableCard("开启");
			Wait(500);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM2");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard("关闭");
		}
	}

	// 主卡在SIM2信息选SIM2发送禁用SIM1发送短信
	public void test_013_050() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_022();
			SettingCommon.enableCard("开启");
			Wait(500);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM2");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard("关闭");
		}
	}

	// 主卡在SIM2信息选SIM2发送禁用SIM2发送短信
	public void test_013_051() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_021();
			SettingCommon.enableCard2("开启");
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM1");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard2("关闭");
		}
	}

	// 主卡在SIM2信息选SIM1发送禁用SIM2发送短信
	public void test_013_052() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_022();
			SettingCommon.enableCard2("开启");
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM1");
			SettingCommon.SendMS("10010");
		} finally {
			SettingCommon.enableCard2("关闭");
		}
	}

	// 主卡在SIM2信息选SIM1发送禁用SIM2发送短信
	public void test_013_053() throws UiObjectNotFoundException,
			RemoteException {
		try {
			ClearBackgroundApp();
			DeviceCommon.enterApp(Devices_Desc_Setting);
			excute(Object_Text, Operation_ClickWait, "SIM 卡");
			Wait(500);
		} finally {
			SettingCommon.enableCard("开启");
			Wait(1000);
			SettingCommon.enableCard2("关闭");
		}
	}

	// 发送信息
	public void test_013_054() throws UiObjectNotFoundException,
			RemoteException {
		SettingCommon.SendMS("10010");
	}

	// 拨打紧急拨号
	public void test_013_055() throws UiObjectNotFoundException,
			RemoteException {
		try {
			ClearBackgroundApp();
			DeviceCommon.enterApp(Devices_Desc_Call);
			Rect ModArea = (Rect) excute(Object_ResourceId,
					Operation_GetBounds,
					"com.android.dialer:id/floating_action_button");
			int WideArea = ModArea.width();
			int x = ModArea.centerX();
			int y = ModArea.centerY();
			CallCommon.makeCallByDailer("112");
			Wait(10000);
			UiDevice.getInstance().click(x, y);
		} finally {
			SettingCommon.enableCard("关闭");
			Wait(1000);
			SettingCommon.enableCard2("关闭");
		}
	}

	public void test_006_001() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_TextScroll, Operation_ClickWait, "日期和时间", "vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.set_check_date("2016", "10");
		SettingCommon.set_check_time("上午", "6", "30");
		excute(Object_Text, Operation_ClickWait, "自动确定日期和时间");
	}

	public void test_006_002() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_TextScroll, Operation_ClickWait, "日期和时间", "vertical");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.set_check_time_zone();
		excute(Object_Text, Operation_ClickWait, "自动确定时区");
	}

	public void test_006_003() throws UiObjectNotFoundException,
			RemoteException, IOException {
		excute(Object_TextScroll, Operation_ClickWait, "日期和时间", "vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.open_close_24_time("开启");
		SettingCommon.set_time("100111592015.50", 10000);
		// 检测时间设置是否正确
		SettingCommon.check_am_pm("pm", "12", "00");
		excute(Object_Text, Operation_ClickWait, "自动确定日期和时间");
		excute(Object_Text, Operation_ClickWait, "自动确定时区");
	}

	public void test_006_004() throws UiObjectNotFoundException,
			RemoteException, IOException {
		excute(Object_TextScroll, Operation_ClickWait, "日期和时间", "vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.open_close_24_time("开启");
		SettingCommon.set_time("100123592015.50", 10000);
		// 检测时间设置是否正确
		SettingCommon.check_am_pm("am", "12", "00");
		excute(Object_Text, Operation_ClickWait, "自动确定日期和时间");
		excute(Object_Text, Operation_ClickWait, "自动确定时区");
	}

	public void test_006_005() throws UiObjectNotFoundException,
			RemoteException, IOException {
		excute(Object_TextScroll, Operation_ClickWait, "日期和时间", "vertical");
		SettingCommon.open_close_auto_date("开启");
		SettingCommon.open_close_auto_time_zone("开启");
		SettingCommon.open_close_24_time("开启");
		SettingCommon.set_time("123123592037.50", 10000);
		SettingCommon.check_biggest_date();
		// 检测时间设置是否正确
		SettingCommon.check_am_pm("am", "12", "00");
	}

	public void test_006_006() throws UiObjectNotFoundException,
			RemoteException, IOException {
		excute(Object_TextScroll, Operation_ClickWait, "日期和时间", "vertical");
		SettingCommon.open_close_auto_date("关闭");
		SettingCommon.open_close_auto_time_zone("关闭");
		SettingCommon.check_auto();
	}

	public void test_005_002() throws UiObjectNotFoundException,
			RemoteException, IOException {
		excute(Object_Device, Operation_PressHome);
		ClearBackgroundApp();
		SettingCommon.install_apk("GPS.apk");
		check(Object_Text, Operation_checkExist, "应用安装完成。");
	}

	public void test_007_001() throws UiObjectNotFoundException,
			RemoteException, IOException {
		excute(Object_TextScroll, Operation_ClickWait, "应用", "vertical");
		SettingCommon.uninstall_apk("GPS Test");
		check(Object_Text, Operation_checkNoExist, "GPS Test");
	}

	public void test_004_001() throws UiObjectNotFoundException,
			RemoteException, IOException {
		// 测试时需要提供账户跟密码
		excute(Object_TextScroll, Operation_ClickWait, "帐户", "vertical");
		// SettingCommon.add_mail_account(mail_address,"个人",password,name);
		// SettingCommon.check_mail_account("个人",mail_address);
	}

	public static void test_004_002() throws UiObjectNotFoundException {
		// 测试时需要提供账户跟密码，并且修改Exchange设置
		excute(Object_TextScroll, Operation_ClickWait, "帐户", "vertical");
		// SettingCommon.add_mail_account(mail_address, "Exchange", password,
		// name);
		// SettingCommon.check_mail_account("Exchange", mail_address);
	}

	public static void test_005_003() {
		excute(Object_TextScroll, Operation_ClickWait, "安全", "vertical");
		excute(Object_Text, Operation_ClickWait, "屏幕锁定方式");
		excute(Object_Text, Operation_ClickWait, "滑动");
		excute(Object_Device, Operation_Sleep);
		Wait(2000);
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();
		check(Object_Text, Operation_checkExist, "滑动");
	}

	public static void test_003_001() throws UiObjectNotFoundException {
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		SettingCommon.set_pic("动态壁纸");
		excute(Object_Device, Operation_PressHome);
		excute(Object_ResourceId, Operation_WaitForExists,
				"com.android.launcher3:id/workspace", "2000");
		// Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		SettingCommon.set_pic("壁纸");
		excute(Object_Device, Operation_PressHome);
		excute(Object_ResourceId, Operation_WaitForExists,
				"com.android.launcher3:id/workspace", "2000");
		// Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen_new");
		SettingCommon.check_pic("HomeScreen", "HomeScreen_new", 0.8d);
		SettingCommon.delete_pic("HomeScreen", "HomeScreen_new");
	}

	public static void test_003_002() {
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		excute(Object_Text, Operation_ClickWait, "壁纸");
		check(Object_Text, Operation_checkExist, "选择壁纸来源");
	}

	public static void test_003_003() throws UiObjectNotFoundException {
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		SettingCommon.set_pic("动态壁纸");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		SettingCommon.set_pic("图库");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen_new");
		SettingCommon.check_pic("HomeScreen", "HomeScreen_new", 0.8d);
		SettingCommon.delete_pic("HomeScreen", "HomeScreen_new");
	}

	public static void test_003_004() throws UiObjectNotFoundException,
			IOException {
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		SettingCommon.set_pic("动态壁纸");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen");
		DeviceCommon.enterApp(Devices_Desc_Setting);
		excute(Object_TextScroll, Operation_ClickWait, "显示", "vertical");
		SettingCommon.set_pic("文件管理器");
		excute(Object_Device, Operation_PressHome);
		Wait(1000);
		SettingCommon.take_temp_pic(UiDevice.getInstance(), "HomeScreen_new");
		SettingCommon.check_pic("HomeScreen", "HomeScreen_new", 0.8d);
		SettingCommon.delete_pic("HomeScreen", "HomeScreen_new");
	}

	// 以下为石亚辉*************************************************************************************
	// 设置-主菜单界面最上角点击搜索
	public static void test_009_001() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_ResourceId, Operation_ClickWait,
				"com.android.settings:id/search");
		check(Object_ResourceId, Operation_checkExist,
				"com.android.settings:id/search");
	}

	// 输入字符/数字等进行搜索操作
	public void test_009_002() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_ResourceId, Operation_ClickWait,
				"com.android.settings:id/search");
		excute(Object_Text, Operation_SetText, "搜索…", "WLAN");
		excute(Object_ResIdText, Operation_Exists,
				"com.android.settings:id/title", "WLAN");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "WLAN");
	}

	// 添加无线网络
	public void test_010_001() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "WLAN");
		if ((Boolean) excute(Object_Text, Operation_Exists, "关闭")) {
			excute(Object_ResourceId, Operation_ClickWait,
					"com.android.settings:id/switch_widget");
		} else {
			excute(Object_ResourceId, Operation_Exists,
					"com.android.settings:id/switch_text", "开启");
		}
		excute(Object_Description, Operation_ClickWait, "更多选项");
		excute(Object_Text, Operation_ClickWait, "添加网络");
		SettingCommon.addWifi("Testteam", "WPA/WPA2 PSK", "test12345678");
		Wait(15000);
		SettingCommon.checkWifiConnect("Testteam");
	}

	// 用无线网浏览网页
	public void test_010_002() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "WLAN");
		if ((Boolean) excute(Object_Text, Operation_Exists, "关闭")) {
			excute(Object_ResourceId, Operation_ClickWait,
					"com.android.settings:id/switch_widget");
		} else {
			excute(Object_ResourceId, Operation_Exists,
					"com.android.settings:id/switch_text", "开启");
		}
		if ((Boolean) excute(Object_ResourceId, Operation_TextEqualTrue,
				"android:id/summary", "已连接")) {
			SettingCommon.ConnectBrowser("www.baidu.com");
		} else {
			excute(Object_Description, Operation_ClickWait, "更多选项");
			excute(Object_Text, Operation_ClickWait, "添加网络");
			SettingCommon.addWifi("Testteam", "WPA/WPA2 PSK", "test12345678");
			Wait(8000);
			SettingCommon.checkWifiConnect("Testteam");
			SettingCommon.ConnectBrowser("www.baidu.com");
			Wait(2000);
		}
	}

	// 查看已保存网络
	public void test_010_003() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "WLAN");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "已保存的网络");
		Wait(1000);
		SettingCommon.checkSaveWifi("Testteam");
	}

	// 取消已保存网络
	public void test_010_004() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "WLAN");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "已保存的网络");
		SettingCommon.checkSaveWifi("Testteam");
		excute(Object_Text, Operation_ClickWait, "Testteam");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button3",
				"取消保存");
		Wait(1000);
		check(Object_Text, Operation_checkNoExist, "android:id/title",
				"Testteam");
		excute(Object_Device, Operation_PressBack);
		SettingCommon.closeWifi();
	}

	// 蓝牙搜索,重用名更改
	public void test_011_001() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "蓝牙");
		if ((Boolean) excute(Object_Text, Operation_Exists, "关闭")) {
			excute(Object_ResourceId, Operation_ClickWait,
					"com.android.settings:id/switch_widget");
		} else {
			excute(Object_ResourceId, Operation_Exists,
					"com.android.settings:id/switch_text", "开启");
		}
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "重命名此设备");
		excute(Object_ResourceId, Operation_SetText,
				"com.android.settings:id/edittext", "test");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button1");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "重命名此设备");
		check(Object_ResourceId, Operation_checkExist,
				"com.android.settings:id/edittext", "test");
		excute(Object_ResourceId, Operation_ClickWait, "android:id/button2");
	}

	// 与蓝牙设备配对
	public void test_011_002() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "蓝牙");
		if ((Boolean) excute(Object_Text, Operation_Exists, "关闭")) {
			excute(Object_ResourceId, Operation_ClickWait,
					"com.android.settings:id/switch_widget");
		} else {
			excute(Object_ResourceId, Operation_Exists,
					"com.android.settings:id/switch_text", "开启");
		}
		Wait(15000);
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "刷新");
		Wait(15000);
		check(Object_ResIdText, Operation_checkExist, "android:id/title", "1");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title", "1");
		Wait(6000);
		if ((Boolean) excute(Object_ResIdText, Operation_checkExist,
				"android:id/alertTitle", "要与1配对吗？")) {
			excute(Object_ResIdText, Operation_ClickWait, "android:id/button1",
					"配对");
		} else {
			Wait(5000);
			excute(Object_ResIdText, Operation_ClickWait, "android:id/button1",
					"配对");
		}

	}

	// 主卡为SIM1，SIM1作为移动数据浏览百度
	public void test_013_031() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"主卡选择");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM1");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
		}
		Wait(40000);
		SettingCommon.Selectdate();
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"移动数据网络");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM1");
		Wait(2000);
		SettingCommon.ConnectBrowser("www.baidu.com");
		Wait(2000);
	}

	// 主卡为SIM1，SIM2作为移动数据浏览百度
	public void test_013_032() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"主卡选择");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM1");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
		}
		Wait(20000);
		SettingCommon.Selectdate();
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"移动数据网络");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM2");
		Wait(2000);
		SettingCommon.ConnectBrowser("www.baidu.com");
		Wait(2000);
	}

	// 主卡为SIM2，SIM1作为移动数据浏览百度
	public void test_013_033() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"主卡选择");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM2");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
		}
		Wait(40000);
		SettingCommon.Selectdate();
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"移动数据网络");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM1");
		Wait(2000);
		SettingCommon.ConnectBrowser("www.baidu.com");
		Wait(2000);
	}

	// 主卡为SIM2，SIM2作为移动数据浏览百度
	public void test_013_034() throws UiObjectNotFoundException,
			RemoteException {
		excute(Object_Text, Operation_ClickWait, "SIM 卡");
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"主卡选择");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM2");
		if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
			excute(Object_Text, Operation_ClickWait, "确定");
		}
		Wait(20000);
		SettingCommon.Selectdate();
		excute(Object_ResIdText, Operation_ClickWait, "android:id/title",
				"移动数据网络");
		excute(Object_ResIdText, Operation_ClickWait,
				"com.android.settings:id/title", "SIM2");
		Wait(2000);
		SettingCommon.ConnectBrowser("www.baidu.com");
		Wait(2000);
	}

	// 主卡为SIM1，禁用SIM1
	public void test_013_035() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_005();
			SettingCommon.enableCard("开启");
		} finally {
			SettingCommon.enableCard("关闭");
		}

	}

	// 主卡为SIM1，禁用SIM2
	public void test_013_036() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_005();
			SettingCommon.enableCard2("开启");
		} finally {
			SettingCommon.enableCard2("关闭");
		}

	}

	// 主卡为SIM1，禁用SIM1和SIM2
	public void test_013_037() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_005();
			SettingCommon.enableCard("开启");
			SettingCommon.enableCard2("开启");
		} finally {
			SettingCommon.enableCard("关闭");
			SettingCommon.enableCard2("关闭");
		}
	}

	// 主卡为SIM2,禁用SIM1
	public void test_013_038() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_006();
			SettingCommon.enableCard("开启");
			Wait(500);
		} finally {
			SettingCommon.enableCard("关闭");
		}

	}

	// 主卡为SIM2,禁用SIM2
	public void test_013_039() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_006();
			SettingCommon.enableCard2("开启");
		} finally {
			SettingCommon.enableCard2("关闭");
		}

	}

	// 主卡为SIM2，禁用SIM1和SIM2
	public void test_013_040() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_006();
			SettingCommon.enableCard("开启");
			SettingCommon.enableCard2("开启");
		} finally {
			SettingCommon.enableCard("关闭");
			SettingCommon.enableCard2("关闭");
		}

	}

	// 主卡为SIM1，禁用SIM1，通信、短信等无法设置
	public void test_013_041() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_005();
			SettingCommon.enableCard("开启");
			Wait(20000);
			if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
				excute(Object_Text, Operation_ClickWait, "确定");
			}
			Wait(2000);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "3", "SIM2");
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM2");
		} finally {
			SettingCommon.enableCard("关闭");
		}
	}

	// 主卡为SIM1，禁用SIM2，通信、短信等无法设置
	public void test_013_042() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_005();
			SettingCommon.enableCard2("开启");
			Wait(20000);
			if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
				excute(Object_Text, Operation_ClickWait, "确定");
			}
			Wait(2000);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "3", "SIM1");
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM1");
		} finally {
			SettingCommon.enableCard2("关闭");
		}
	}

	// 主卡为SIM2，禁用SIM1，通信、短信等无法设置
	public void test_013_043() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_006();
			SettingCommon.enableCard("开启");
			Wait(20000);
			if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
				excute(Object_Text, Operation_ClickWait, "确定");
			}
			Wait(2000);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "3", "SIM2");
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM2");
		} finally {
			SettingCommon.enableCard("关闭");
		}
	}

	// 主卡为SIM2，禁用SIM2，通信、短信等无法设置
	public void test_013_044() throws UiObjectNotFoundException,
			RemoteException {
		try {
			test_013_006();
			SettingCommon.enableCard2("开启");
			Wait(20000);
			if ((Boolean) excute(Object_Text, Operation_Exists, "注意")) {
				excute(Object_Text, Operation_ClickWait, "确定");
			}
			Wait(2000);
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "3", "SIM1");
			check(Object_ResIdInstance, Operation_TextEqualTrue,
					"android:id/summary", "4", "SIM1");
		} finally {
			SettingCommon.enableCard2("关闭");
		}
	}
*/
	/**
	 * 进入提示音和通知
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_133() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		check(Object_Text,Operation_checkExist,"提示和通知");
	}
	/**
	 * 进入提示音和通知
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_134() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		check(Object_Text,Operation_checkExist,"勿扰");
	}
	/**
	 * 仅允许优先打扰内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_135() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		check(Object_ResIdInstance,Operation_EnabledFalse,"android:id/switchWidget","0");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","1");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","2");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","3");
	}
	/**
	 * 关闭提醒
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_136() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"提醒");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","1");
		//清场
		excute(Object_Text,Operation_ClickWait,"提醒");
	}
	/**
	 * 关闭活动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_137() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"活动");
		check(Object_ResIdInstance,Operation_CheckedFalse,"android:id/switchWidget","2");
		//清场
		excute(Object_Text,Operation_ClickWait,"活动");
	}
	/**
	 * 打开重复来电者
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_138() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"重复来电者");
		check(Object_ResIdInstance,Operation_CheckedTrue,"android:id/switchWidget","3");
		//清场
		excute(Object_Text,Operation_ClickWait,"重复来电者");
	}
	/**
	 * 点击通话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_139() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		check(Object_Text,Operation_checkExist,"来自任何人");
		check(Object_Text,Operation_checkExist,"仅限来自联系人");
		check(Object_Text,Operation_checkExist,"仅限来自收藏的联系人");
		check(Object_Text,Operation_checkExist,"无");
	}
	/**
	 * 点击来自任何人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_140() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"来自任何人");
		check(Object_Text,Operation_checkExist,"来自任何人");
	}
	/**
	 * 点击仅限来自联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_141() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"仅限来自联系人");
		check(Object_Text,Operation_checkExist,"仅限来自联系人");
	}
	/**
	 * 点击仅限来自收藏的联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_142() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"仅限来自收藏的联系人");
		check(Object_Text,Operation_checkExist,"仅限来自收藏的联系人");
	}
	/**
	 * 点击无
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_143() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"仅允许优先打扰内容");
		excute(Object_Text,Operation_ClickWait,"通话");
		excute(Object_Text,Operation_ClickWait,"无");
		check(Object_Text,Operation_checkExist,"无");
	}
	/**
	 * 点击自动规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_144() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		check(Object_Text,Operation_checkExist,"自动规则");
	}
	/**
	 * 查看自动规则界面
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_145() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		check(Object_Text,Operation_checkExist,"周一至周五夜间");
		check(Object_Text,Operation_checkExist,"周末");
		check(Object_Text,Operation_checkExist,"活动");
		check(Object_Text,Operation_checkExist,"添加规则");
	}
	/**
	 * 周一至周五夜间默认开关显示
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_146() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		check(Object_ResourceId,Operation_CheckedFalse,"com.android.settings:id/switch_widget");
	}
	/**
	 * 打开周一至周五夜间开关
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_147() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
		check(Object_ResourceId,Operation_CheckedTrue,"com.android.settings:id/switch_widget");
		//清场
		excute(Object_ResourceId,Operation_ClickWait,"com.android.settings:id/switch_widget");
	}
	/**
	 * 点击周一至周五夜间规则名称
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_148() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_Text,Operation_ClickWait,"规则名称");
		check(Object_Text,Operation_checkExist,"规则名称");
	}
	/**
	 * 点击周一至周五夜间星期几
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_149() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_Text,Operation_ClickWait,"星期几");
		check(Object_Text,Operation_checkExist,"星期几");
	}
	/**
	 * 点击周一至周五夜间默认选择
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_150() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"提示和通知","vertical");
		excute(Object_Text,Operation_ClickWait,"勿扰");
		excute(Object_Text,Operation_ClickWait,"自动规则");
		excute(Object_Text,Operation_ClickWait,"周一至周五夜间");
		excute(Object_Text,Operation_ClickWait,"星期几");
		check(Object_Text,Operation_CheckedTrue,"星期日");
		check(Object_Text,Operation_CheckedTrue,"星期一");
		check(Object_Text,Operation_CheckedTrue,"星期二");
		check(Object_Text,Operation_CheckedTrue,"星期三");
		check(Object_Text,Operation_CheckedTrue,"星期四");
		check(Object_Text,Operation_CheckedFalse,"星期五");
		check(Object_Text,Operation_CheckedFalse,"星期六");
	}
	/**
	 * 点击周一至周五夜间开始时间
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_151() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "开始时间");
		check(Object_ResourceId, Operation_checkExist, "android:id/parentPanel");
	}
	/**
	 * 点击周一至周五夜间结束时间
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_152() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "结束时间");
		check(Object_ResourceId, Operation_checkExist, "android:id/parentPanel");
	}
	/**
	 * 点击周一至周五夜间勿扰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_153() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		check(Object_Text, Operation_checkExist, "仅限优先打扰");
		check(Object_Text, Operation_checkExist, "仅限闹钟");
		check(Object_Text, Operation_checkExist, "完全阻止");
	}
	/**
	 * 点击周一至周五夜间勿扰仅限优先打扰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_154() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "仅限优先打扰");
		check(Object_Text, Operation_checkExist, "仅限优先打扰");
	}
	/**
	 * 点击周一至周五夜间勿扰仅限闹钟
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_155() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "仅限闹钟");
		check(Object_Text, Operation_checkExist, "仅限闹钟");
	}
	/**
	 * 点击周一至周五夜间勿扰完全阻止
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_156() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "完全阻止");
		check(Object_Text, Operation_checkExist, "完全阻止");
	}
	/**
	 * 点击周一至周五夜间勿扰查看删除
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_157() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周一至周五夜间");
		//excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		check(Object_Text, Operation_checkExist, "要删除“周一至周五夜间”规则吗？");
	}
	/**
	 * 点击周一至周五夜间勿扰删除规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_158() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		excute(Object_Text, Operation_SetText, "输入规则名称", "Test");
		excute(Object_Text, Operation_ClickWait, "确定");
		excute(Object_Device, Operation_PressBack);
		//主体
		excute(Object_Text, Operation_ClickWait, "Test");
		//excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		excute(Object_Text, Operation_ClickWait, "删除");
		check(Object_Text, Operation_checkNoExist, "Test");
	}
	/**
	 * 点击周末
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_159() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "周末");
		check(Object_Text, Operation_checkExist, "周末");
	}
	/**
	 * 点击活动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_160() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "规则名称");
		check(Object_Text, Operation_checkExist, "活动");
	}
	/**
	 * 点击活动中的在以下活动期间
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_161() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "在以下日历活动期间：");
		check(Object_Text, Operation_checkExist, "所有日历");
		check(Object_Text, Operation_checkExist, "Local Calendar");
	}
	/**
	 * 点击活动中的回复内容如下的活动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_162() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "回复内容如下的活动：");
		check(Object_Text, Operation_checkExist, "“参加”、“可能参加”或“未回复”");
		check(Object_Text, Operation_checkExist, "“参加”或“可能参加”");
		check(Object_Text, Operation_checkExist, "是");
	}
	/**
	 * 点击活动中的勿扰
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_163() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "活动");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		check(Object_Text, Operation_checkExist, "仅限优先打扰");
		check(Object_Text, Operation_checkExist, "仅限闹钟");
		check(Object_Text, Operation_checkExist, "完全阻止");
	}
	/**
	 * 查看添加规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_164() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		check(Object_Text, Operation_checkExist, "添加规则");
	}
	/**
	 * 添加时间规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_165() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		excute(Object_Text, Operation_SetText, "输入规则名称", "1");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "1");
		//清场
		excute(Object_Text, Operation_ClickWait, "1");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		excute(Object_Text, Operation_ClickWait, "删除");
	}
	/**
	 * 添加事件规则
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_166() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "勿扰");
		excute(Object_Text, Operation_ClickWait, "自动规则");
		excute(Object_Text, Operation_ClickWait, "添加规则");
		excute(Object_Text, Operation_SetText, "输入规则名称", "2");
		excute(Object_Text, Operation_ClickWait, "事件规则");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "2");
		//清场
		excute(Object_Text, Operation_ClickWait, "2");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.settings:id/delete");
		excute(Object_Text, Operation_ClickWait, "删除");
	}
	/**
	 * 投射
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_167() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "投射");
		check(Object_Text, Operation_checkExist, "未在附近找到设备。");
	}
	/**
	 * 设备锁定时
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_168() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "设备锁定时");
		check(Object_Text, Operation_checkExist, "显示所有通知内容");
		check(Object_Text, Operation_checkExist, "完全不显示通知");
	}
	/**
	 * 显示所有通知内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_169() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "设备锁定时");
		excute(Object_Text, Operation_ClickWait, "显示所有通知内容");
		check(Object_Text, Operation_checkExist, "显示所有通知内容");
	}
	/**
	 * 不存在170，完全不显示通知内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_171() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "设备锁定时");
		check(Object_Text, Operation_checkExist, "完全不显示通知");
		check(Object_Text, Operation_checkExist, "完全不显示通知");
	}
	/**
	 * 应用通知
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_172() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "应用通知");
		check(Object_Text, Operation_checkExist, "应用通知");
	}
	/**
	 * 通知使用权
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_173() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"提示和通知", "vertical");
		excute(Object_Text, Operation_ClickWait, "通知使用权");
		check(Object_Text, Operation_checkExist, "没有任何已安装的应用请求通知访问权限。");
	}
	/**
	 * 情景模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_174() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		check(Object_Text, Operation_checkExist, "情景模式");
	}
	/**
	 * 情景模式默认选择标准
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_175() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 点击静音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_176() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "1");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "1");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 点击静音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_177() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "2");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "2");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 点击户外
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_178() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "3");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "3");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 * 编辑标准
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_179() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		check(Object_Text, Operation_checkExist, "编辑");
	}
	/**
	 * 编辑标准默认勾选
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_180() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "1");
		excute(Object_TextScroll, Operation_checkExist,"拨号键盘提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "0");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "1");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "2");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "3");
	}
	/**
	 * 勾选通话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_181() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_Text, Operation_ClickWait,"通话");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "0");
		//清场
		excute(Object_Text, Operation_ClickWait,"通话");
	}
	/**
	 * 勾选信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_182() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_Text, Operation_ClickWait,"信息");
		check(Object_ResIdInstance, Operation_CheckedTrue, "android:id/checkbox", "1");
		//清场
		excute(Object_Text, Operation_ClickWait,"信息");
	}
	/**
	 * 点击音量
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_183() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_Text, Operation_ClickWait,"音量");
		check(Object_Text, Operation_checkExist, "铃声音量");
	}
	/**
	 * SIM1来电铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_184() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "5");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * SIM2来电铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_185() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "6");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * SIM1信息铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_186() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "7");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 * SIM2信息铃声
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_187() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_ResIdInstance, Operation_ClickWait, "android:id/title", "8");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 *默认通知提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_188() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "默认通知提示音", "vertical");
		if((Boolean)excute(Object_Text,Operation_Exists,"媒体存储"))
		{
			excute(Object_Text,Operation_ClickWait,"媒体存储");
		}
		if((Boolean)excute(Object_Text,Operation_Exists,"仅此一次"))
		{
			excute(Object_Text,Operation_ClickWait,"仅此一次");
		}
		check(Object_Text, Operation_checkExist, "确定");
	}
	/**
	 *点击拨号键盘提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_189() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "拨号键盘提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "0");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "拨号键盘提示音", "vertical");
	}
	/**
	 *屏幕锁定提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_190() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "屏幕锁定提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "1");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "屏幕锁定提示音", "vertical");
	}
	/**
	 *触摸提示音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_191() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "触摸提示音", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "2");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "触摸提示音", "vertical");
	}
	/**
	 *触摸时振动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_192() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"标准");
		excute(Object_Text, Operation_ClickWait,"编辑");
		excute(Object_TextScroll, Operation_ClickWait, "触摸时振动", "vertical");
		check(Object_ResIdInstance, Operation_CheckedFalse, "android:id/checkbox", "3");
		//清场
		excute(Object_TextScroll, Operation_ClickWait, "触摸时振动", "vertical");
	}
	/**
	 *启用静音
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_193() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"静音");
		excute(Object_Text, Operation_ClickWait,"启用");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "1");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 *启用振动
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_194() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"振动");
		excute(Object_Text, Operation_ClickWait,"启用");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "2");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 *启用户外
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_195() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Text, Operation_ClickWait,"户外");
		excute(Object_Text, Operation_ClickWait,"启用");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "3");
		//清场
		excute(Object_ResIdInstance, Operation_ClickWait,"com.android.settings:id/audio_profile_radiobutton", "0");
	}
	/**
	 *菜单
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_196() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Device, Operation_PressMenu);
		check(Object_Text, Operation_checkExist, "添加");
		check(Object_Text, Operation_checkExist, "重置");
	}
	/**
	 *添加情景模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_197() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "添加");
		//excute(Object_Text, Operation_WaitForExists, "请输入情景模式名称", "10000");
		check(Object_Text, Operation_checkExist, "请输入情景模式名称");
		excute(Object_ResourceId, Operation_SetText, "android:id/custom","1");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_Text, Operation_checkExist, "1");
		//清场
		excute(Object_Text, Operation_ClickWait, "1");
		excute(Object_Text, Operation_ClickWait, "删除");
		excute(Object_Text, Operation_ClickWait, "确定");
	}
	/**
	 *重置情景模式
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_198() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll, Operation_ClickWait,"情景模式", "vertical");
		excute(Object_Device, Operation_PressMenu);
		excute(Object_Text, Operation_ClickWait, "重置");
		excute(Object_Text, Operation_ClickWait, "确定");
		check(Object_ResIdInstance, Operation_CheckedTrue, "com.android.settings:id/audio_profile_radiobutton", "0");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.settings:id/audio_profile_radiobutton", "1");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.settings:id/audio_profile_radiobutton", "2");
		check(Object_ResIdInstance, Operation_CheckedFalse, "com.android.settings:id/audio_profile_radiobutton", "3");
	}
}