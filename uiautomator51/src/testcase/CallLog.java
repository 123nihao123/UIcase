package testcase;

//import org.junit.AfterClass;  
//import org.junit.BeforeClass;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;
import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import framework.common.CallCommon;
import framework.common.CallLogCommon;
import framework.common.ContactCommon;
import framework.common.DeviceCommon;
import framework.common.SettingCommon;

public class CallLog extends UiAutomatorTestCase
{
	/*
	@BeforeClass 
    public static void beforeClass()
	{  
        System.out.println("Fill CallLog Data in BeforeClass");
        CallLogCommon.fillCallLogData();
    }
	
	@AfterClass  
    public static void afterClass()
	{  
        System.out.println("Delete CallLog data AfterAlass");  
        CallLogCommon.deleteAllFromCallLog();
    }  
	*/
	
	/**
	 * Workaround of  not working @BeforeClass @AfterClass
	 * Compile is ok but @BeforeClass @AfterClass can not be recognized because of extending UiAutomatorTestCase
	 */
	public void test_0000(){  
        System.out.println("before class!!!!!!!!!!!!!!!!!!!!!!!!");
        CallLogCommon.fillCallLogData();
    }
	
	public void test_9999(){  
        System.out.println("after class...");
        CallLogCommon.deleteAllFromCallLog();
    }
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
   }
	     
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 * 检查通话记录字样
	 */
	public static void test_001() 
	{
		//主体
		check(Object_Text,Operation_checkExist,"通话记录");
	}
	
	/**
	 * 检查通话记录中全部，未接来电，已拨电话，已接电话字样
	 */
	public static void test_002() 
	{
		//主体
		check(Object_Text,Operation_checkExist,"未接电话");
		check(Object_Text,Operation_checkExist,"已拨电话");
		check(Object_Text,Operation_checkExist,"已接电话");
	}
	
	/**
	 * 查看通话记录中不是联系人的通话记录
	 */
	public static void test_003() 
	{
		//主体
		check(Object_TextScroll,Operation_checkExist,"10086","vertical");
	}
	
	/**
	 * 查看联系人的通话记录
	 * @throws RemoteException
	 * @throws UiObjectNotFoundException
	 */
	public static void test_004() throws RemoteException, UiObjectNotFoundException 
	{
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.addNameAndTel("本机", "zhanxun", "10086");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		check(Object_TextScroll,Operation_checkExist,"zhanxun","vertical");
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
	}
	
	/**
	 * 查看通话记录中显示的时间
	 */
	public static void test_005() 
	{
		//主体
		check(Object_Text,Operation_checkExist,"今天");
		check(Object_Text,Operation_checkExist,"昨天");
		check(Object_TextScroll,Operation_checkExist,"更早","vertical");
	}
	
	/**
	 * 查看通话记录的号码是中国移动的号码
	 */
	public static void test_006() 
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/call_account_label","中国移动");
	}
	
	/**
	 * 查看通话记录的号码是SIM1进行的
	 */
	public static void test_007() 
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/call_account_label","SIM1");
	}
	
	/**
	 * 查看通话记录号码有归属地
	 */
	public static void test_008() 
	{
		//主体
		check(Object_ResIdInstance,Operation_TextContainsTrue,"com.android.dialer:id/call_location_and_date","2","江苏省南京市");
	}
	
	/**
	 * 选中全部通话记录条目
	 * @throws UiObjectNotFoundException
	 */
	public static void test_010() throws UiObjectNotFoundException 
	{
		//主体
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==5);
	}
	
	/**
	 * 选中未接电话条目，查看条目
	 * @throws UiObjectNotFoundException
	 */
	public static void test_011() throws UiObjectNotFoundException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==3);
	}
	
	/**
	 * 选中已拨电话电话条目，查看条目
	 * @throws UiObjectNotFoundException
	 */
	public static void test_012() throws UiObjectNotFoundException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==3);
	}
	
	/**
	 * 选中已接电话电话条目，查看条目
	 * @throws UiObjectNotFoundException
	 */
	public static void test_013() throws UiObjectNotFoundException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==3);
	}
	
	/**
	 * 全部通话记录中查看“今天”字样
	 */
	public static void test_014() 
	{
		//主体
		check(Object_TextScroll,Operation_checkExist,"今天","vertical");
	}
	
	/**
	 * 全部通话记录中查看“昨天”字样
	 */
	public static void test_015() 
	{
		//主体
		check(Object_TextScroll,Operation_checkExist,"昨天","vertical");
	}
	
	/**
	 * 全部通话记录中查看“更早”字样
	 */
	public static void test_016() 
	{
		//主体
		check(Object_TextScroll,Operation_checkExist,"更早","vertical");
	}
	
	/**
	 * 未接通话记录中查看“今天”字样
	 */
	public static void test_017() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		check(Object_TextScroll,Operation_checkExist,"今天","vertical");
	}
	
	/**
	 * 未接通话记录中查看“昨天”字样
	 */
	public static void test_018() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		check(Object_TextScroll,Operation_checkExist,"昨天","vertical");
	}
	
	/**
	 * 未接通话记录中查看“更早”字样
	 */
	public static void test_019() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		check(Object_TextScroll,Operation_checkExist,"更早","vertical");
	}
	
	/**
	 * 已拨通话记录中查看“今天”字样
	 */
	public static void test_020() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		check(Object_TextScroll,Operation_checkExist,"今天","vertical");
	}
	
	/**
	 * 已拨通话记录中查看“昨天”字样
	 */
	public static void test_021() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		check(Object_TextScroll,Operation_checkExist,"昨天","vertical");
	}
	
	/**
	 * 已拨通话记录中查看“更早”字样
	 */
	public static void test_022() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		check(Object_TextScroll,Operation_checkExist,"更早","vertical");
	}
	
	/**
	 * 已接通话记录中查看“今天”字样
	 */
	public static void test_023() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		check(Object_TextScroll,Operation_checkExist,"今天","vertical");
	}
	
	/**
	 * 已接通话记录中查看“昨天”字样
	 */
	public static void test_024() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		check(Object_TextScroll,Operation_checkExist,"昨天","vertical");
	}
	
	/**
	 * 已接通话记录中查看“更早”字样
	 */
	public static void test_025() 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		check(Object_TextScroll,Operation_checkExist,"更早","vertical");
	}
	
	
	/**
	 * Description: 全部-筛选-所有联系人
	 * @throws UiObjectNotFoundException, RemoteException
	 */ 
	public static void test_026() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM1");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM2");
	}
	
	/**
	 * Description: 全部-筛选-SIM1 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_027() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM1 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==3);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
		
		
	}
	
	/**
	 * Description: 全部-筛选-SIM2 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_028() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM2 通话记录");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM2");
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	 * Description: 未接-筛选-所有通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_029() throws UiObjectNotFoundException, RemoteException
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM1");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM2");
	}
	
	/**
	 * Description: 未接-筛选-SIM1 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_030() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM1 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==1);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	 * Description: 未接-筛选-SIM2 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_031() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM2 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==2);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	 * Description: 已拨-筛选-所有通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_032() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM1");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM2");
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	

	/**
	 * Description: 已拨-筛选-SIM1 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_033() throws UiObjectNotFoundException , RemoteException
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM1 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==1);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	 * Description: 已拨-筛选-SIM2 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_034() throws UiObjectNotFoundException, RemoteException
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM2 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==2);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	 * Description: 已接-筛选-所有通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_035() throws UiObjectNotFoundException, RemoteException
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM1");
		check(Object_ClassContainsText,Operation_checkExist,"android.widget.TextView","SIM2");
	}
	

	/**
	 * Description: 已接-筛选-SIM1 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_036() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM1 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==1);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	 * Description: 已接-筛选-SIM2 通话记录
	 * @throws UiObjectNotFoundException, RemoteException
	 */
	public static void test_037() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"SIM2 通话记录");
		UiObject a = (UiObject) excute(Object_ResourceId,Operate_ReturnObject,"com.android.dialer:id/recycler_view");
		Assert.assertTrue(a.getChildCount()==2);
		//清场
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"查看");
		excute(Object_Text,Operation_ClickWait,"所有通话记录");
	}
	
	/**
	  * Description:展开详情页面，展开的页面上有text“新建联系人”“添加到联系人”“发送信息”“通话详情”“添加至黑名单”
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_038() throws UiObjectNotFoundException, RemoteException 
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/create_new_contact_action");
	  check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/add_to_existing_contact_action");
	  check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/send_message_action");
	  check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/details_action");
	  check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/add_to_blacklist_action");
	 }
	  
	 
	 
	 /**
	  * Description:从通话记录中增加联系人
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_039() throws UiObjectNotFoundException, RemoteException
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  excute(Object_Text,Operation_ClickWait,"新建联系人");
	  check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/add_account_button","添加新帐户");
	 }
	 
	 /**
	  * Description:从通话记录添加到联系人
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_040() throws UiObjectNotFoundException, RemoteException
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  excute(Object_Text,Operation_ClickWait,"添加到联系人");
	  check(Object_Text,Operation_checkExist,"选择联系人");
	 }
	 
	 /**
	  * Description:选择一条记录跳转到短信界面
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_041() throws UiObjectNotFoundException, RemoteException
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/name");
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/send_message_action");
	  excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/conversation_title","5000");
	  check(Object_ResourceId,Operation_checkExist,"com.android.messaging:id/conversation_title",Number);
	 }
	 
	 
	 /**
	  * Description:选择一条记录进入通话详情
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_042() throws UiObjectNotFoundException, RemoteException
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  excute(Object_Text,Operation_ClickWait,"通话详情");
	  check(Object_Text,Operation_checkExist,"通话详情");
	 }
	 
	 /**
	  * Description:选择一条记录跳转到添加到黑名单
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_043() throws UiObjectNotFoundException, RemoteException
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/add_to_blacklist_action");
	  check(Object_Text,Operation_checkExist,"拦截类型");
	 }
	 
	 /**
	  * Description:选择一条记录展开，再收起
	  * @throws UiObjectNotFoundException, RemoteException
	  */
	 public static void test_044() throws UiObjectNotFoundException, RemoteException
	 {
	  //主体
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
	  check(Object_ResourceId,Operation_checkNoExist,"com.android.dialer:id/create_new_contact_action");
	 }
	/**
	  * 通话记录-从卡1打出电话
	  * @throws UiObjectNotFoundException
	  * @throws RemoteException
	  */
	 public static void test_045() throws UiObjectNotFoundException, RemoteException 
	 {
	  //前提
	  ClearBackgroundApp();
	  DeviceCommon.enterApp(Devices_Desc_Call);
	  Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
	  int WideArea = ModArea.width();
	  int x = ModArea.centerX();
	  int y = ModArea.centerY();
	  //主体
	  excute(Object_Description,Operation_ClickWait,"更多选项");
	  excute(Object_Text,Operation_ClickWait,"通话记录");
	  excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/primary_action_button");
	  excute(Object_ResourceId, Operation_WaitForExists, "android:id/select_dialog_listview","10000");
	  excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/label","0");
	  Wait(10000);
	  UiDevice.getInstance().click(x, y);
	  
	 }
	
	
	
	/**
	 * 通话记录-从卡2打出电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_046() throws UiObjectNotFoundException, RemoteException 
	{	
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_ResourceId, Operation_ClickWait, "com.android.dialer:id/primary_action_button");
		excute(Object_ResourceId, Operation_WaitForExists, "android:id/select_dialog_listview","10000");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/label","1");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
		
	}
	
	/**
	 * 通话记录-展开
	 * @throws UiObjectNotFoundException
	 */
	public static void test_047() throws UiObjectNotFoundException 
	{	
		//主体
		excute(Object_ResourceId, Operation_LongClick, "com.android.dialer:id/primary_action_view");
		check(Object_Text, Operation_checkExist, "复制到剪贴板");
		check(Object_Text, Operation_checkExist, "呼叫之前编辑号码");
				
	}
	
	
	/**
	 * 通话记录查看全部通话记录按钮
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException 
	 */
	public static void test_048() throws UiObjectNotFoundException, RemoteException 
	{	
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"最近");
		check(Object_TextScroll, Operation_checkExist, "查看全部通话记录","vertical");
		
	}
	
 	/**
 	 * 查看全部通话记录,通48条，去掉
 	 * @throws UiObjectNotFoundException
 	 * @throws RemoteException
 	 
	public static void test_049() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		check(Object_Text,Operation_checkExist,"通话记录");
	}*/
	/**
	 * 进入通话详情
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_050() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
	}
	/**
	 * 查看通话记录详情界面内容
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_051() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/caller_name");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/date");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/duration");
	}
	
	/**
	 * 查看通话记录详情拨打电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_052() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_back_button");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/label","10000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/label");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
	}
	/**
	 * 查看通话记录详情拨打IP电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_053() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		DeviceCommon.enterApp(Devices_Desc_Call);
		Rect ModArea = (Rect) excute(Object_ResourceId,Operation_GetBounds,"com.android.dialer:id/floating_action_button");
		int WideArea = ModArea.width();
		int x = ModArea.centerX();
		int y = ModArea.centerY();
		CallLogCommon.addIPCall();
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_back_button");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/label","10000");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/label");
		Wait(10000);
		UiDevice.getInstance().click(x, y);
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		CallLogCommon.removeIPCall();
	}
	/**
	 * 查看通话记录详情界面发送信息
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_054() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/caller_name");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/message_icon");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.messaging:id/conversation_title","10000");
		check(Object_Text,Operation_checkExist,Number);
	}
	/**
	 * 查看通话记录详情界面添加联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_055() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/add_contact_icon");
		excute(Object_Text,Operation_WaitForExists,"选择联系人","10000");
		check(Object_Text,Operation_checkExist,"选择联系人");
	}
	/**
	 * 查看通话记录详情界面查看不存在添加联系人选项
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_056() throws UiObjectNotFoundException, RemoteException 
	{
		//前提
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Text,Operation_ClickWait,"全部");
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/add_contact_icon");
		excute(Object_Text,Operation_WaitForExists,"选择联系人","10000");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"创建新联系人");
		excute(Object_Text,Operation_ClickWait,"本机");
		excute(Object_Text,Operation_SetText,"姓名","addname");
		excute(Object_ResIdDesc,Operation_ClickWait,"com.android.contacts:id/menu_save", "保存");
		check(Object_ResourceId,Operation_checkNoExist,"com.android.dialer:id/add_contact_icon");
		//清场
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_PhoneBook);
		ContactCommon.BatchDelete("所有联系人");
	}
	/**
	 * 半屏查看联系人详情
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_057() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/quick_contact_photo");
		check(Object_ResourceId,Operation_checkExist,"com.android.contacts:id/third_icon");
	}
	/**
	 * 删除单条联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_158() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_TextScroll,Operation_ClickWait,"222222","vertical");
		excute(Object_TextScroll,Operation_ClickWait,"通话详情","vertical");
		String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/caller_name");
		System.out.println(Number);
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/menu_remove_from_call_log");
		check(Object_Text,Operation_checkNoExist,Number);
	}
	/**
	 * 呼叫之前编辑，包含第60条
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_059() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"呼叫之前编辑号码");
		excute(Object_ResourceId,Operation_WaitForExists,"com.android.dialer:id/dialpad_floating_action_button","10000");
		check(Object_ResourceId,Operation_checkExist,"com.android.dialer:id/dialpad_floating_action_button");
	}
	/**
	 * 未接电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_061() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"未接电话");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"未接电话");		
	}
	/**
	 * 已拨电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_062() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已拨电话");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"外拨电话");
	}
	/**
	 * 已接电话
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_063() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Text,Operation_ClickWait,"已接电话");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.dialer:id/call_log_row");
		excute(Object_Text,Operation_ClickWait,"通话详情");
		check(Object_Text,Operation_checkExist,"通话详情");
		check(Object_Text,Operation_checkExist,"来电");
	}
	/**
	 * 进入清除通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_064() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		check(Object_Text,Operation_checkExist,"清除通话记录");
	}
	/**
	 * 查看全选清除通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_065() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkExist,"全选");
	}
	/**
	 * 确认全选通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_066() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全选");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
		System.out.println(Num);
		for(int i=0; i<Num; i++)
		{
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon",String.valueOf(i));
		}
	}
	/**
	 * 删除全部通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_267() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		CallLogCommon.deleteAllLog("全部");
		ClearBackgroundApp();
		DeviceCommon.enterApp(Devices_Desc_Call);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"通话记录");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		check(Object_Text,Operation_checkNoExist,"清除通话记录");
	}
	/**
	 * 确认单选删除通话记录
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_068() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/call_icon","0");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
		System.out.println(Num);
		for(int i=0; i<Num; i++)
		{
			check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon","0");
			if(i>0){
				check(Object_ResIdInstance,Operation_CheckedFalse,"com.android.dialer:id/call_icon",String.valueOf(i));
			}
		}
	}
	/**
	 * 选择全部通话记录
	 * @throws UiObjectNotFoundException
	 */
	public static void test_069() throws UiObjectNotFoundException 
	{	
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_ResIdInstance,Operation_ClickWait,"com.android.dialer:id/call_icon","2");
		check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon","2");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"全选");
		int Num = ((Integer)(excute(Object_ResourceId, Operation_GetChildCount, "android:id/list"))).intValue();
	    System.out.println(Num);
	    for(int i=0; i<Num; i++)
	    {
	     check(Object_ResIdInstance,Operation_CheckedTrue,"com.android.dialer:id/call_icon",String.valueOf(i));
	    }
	}
	/**
	 * 取消删除单条联系人
	 * @throws UiObjectNotFoundException
	 * @throws RemoteException
	 */
	public static void test_070() throws UiObjectNotFoundException, RemoteException 
	{
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		String Number=(String) excute(Object_ResourceId,Operation_GetText,"com.android.dialer:id/line1");
		excute(Object_Text,Operation_ClickWait,Number);
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"取消");
		check(Object_Text,Operation_checkExist,Number);
	}
 	/**
 	 * 清除一条所选的通话记录
 	 * @throws UiObjectNotFoundException
 	 */
	public static void test_171() throws UiObjectNotFoundException 
	{	
		//主体
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"清除通话记录");
		excute(Object_TextScroll,Operation_ClickWait,"444444","vertical");
		excute(Object_Description,Operation_ClickWait,"更多选项");
		excute(Object_Text,Operation_ClickWait,"删除全部选中的通话记录");
		excute(Object_Text,Operation_ClickWait,"确定");
		check(Object_Text, Operation_checkNoExist, "444444");
	}	
}
