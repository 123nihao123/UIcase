package testcase;


import junit.framework.Assert;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import static framework.data.ObjectType.*;
import static framework.data.OperationType.*;
import static framework.data.ResIdTextAndDesc.*;
import static framework.excute.Excute.*;

import framework.common.DeviceCommon;

//插两张SIM卡，SD卡及手机内存中要有预存联系人
public class Calculator extends UiAutomatorTestCase
{
	
	@Override
	protected void setUp() throws UiObjectNotFoundException, RemoteException 
    {			
		System.out.println("Enter the setUp!!!");	
		excute(Object_Device, Operation_WakeUp);
		DeviceCommon.unLock();	
		ClearBackgroundApp();
		Wait(1000);
		DeviceCommon.enterApp(Caculate_Text_Operator_jsq);	
   }
	       
	@Override
	protected void tearDown() throws UiObjectNotFoundException, RemoteException 
    {
    }
	/**
	 *进入计算器
	 */
	public static void test_001()
	{
		//主体
		check(Object_ResourceId,Operation_checkExist,"com.android.calculator2:id/pad_operator");
		//通过判断页面的加减乘除的ID判断
	}
	/**
	 *查看按键以及显示
	 */
	public static void test_002()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_2");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_3");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_4");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_5");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_6");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_7");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_8");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_9");
		String number = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("0123456789", number);
	}
	/**
	 *判断1+1=2
	 */
	public static void test_003()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/op_add");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("2", result);
	}
	/**
	 *判断1-1=0
	 */
	public static void test_004()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/op_sub");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("0", result);
	}
	/**
	 *判断1*1=1
	 */
	public static void test_005()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/op_mul");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("1", result);
	}
	/**
	 *判断1/1=1
	 */
	public static void test_006()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/op_div");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("1", result);
	}
	/**
	 *输入1234，然后删除4
	 */
	public static void test_007()
	{
		//主体
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_2");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_3");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_4");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/del");
		String number = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("123", number);
	}
	/**
	 *进入高级计算页面
	 */
	public static void test_008()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		check(Object_Text,Operation_checkExist,"sin");
		check(Object_Text,Operation_checkExist,"cos");
		check(Object_Text,Operation_checkExist,"tan");
		//需要通过滑动进入高级界面
	}
	/**
	 *计算sin90  注：这个计算器算得是弧度！
	 */
	public static void test_009()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"sin");
		Rect numeric = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_numeric");
		String x1 = Integer.toString(numeric.centerX());
		String y1 = Integer.toString(numeric.centerY());
		excute(Object_Device, Operation_DiviceDrag, x1, y1,"480" , y1, "10");//480最大
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_9");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_0");
		
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("0.8939966636", result);
	}
	/**
	 *计算cos0 = 1
	 */
	public static void test_010()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"cos");
		Rect numeric = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_numeric");
		String x1 = Integer.toString(numeric.centerX());
		String y1 = Integer.toString(numeric.centerY());
		excute(Object_Device, Operation_DiviceDrag, x1, y1,"480" , y1, "10");//480最大
	
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("1", result);
	}
	/**
	 *计算tan 0 = 0
	 */
	public static void test_011()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"tan");
		Rect numeric = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_numeric");
		String x1 = Integer.toString(numeric.centerX());
		String y1 = Integer.toString(numeric.centerY());
		excute(Object_Device, Operation_DiviceDrag, x1, y1,"480" , y1, "10");//480最大
	
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("0", result);
	}
	/**
	 *计算ln1= 0
	 */
	public static void test_012()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"ln");
		Rect numeric = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_numeric");
		String x1 = Integer.toString(numeric.centerX());
		String y1 = Integer.toString(numeric.centerY());
		excute(Object_Device, Operation_DiviceDrag, x1, y1,"480" , y1, "10");//480最大
	
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("0", result);
	}
	/**
	 *计算log10=1
	 */
	public static void test_013()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"log");
		Rect numeric = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_numeric");
		String x1 = Integer.toString(numeric.centerX());
		String y1 = Integer.toString(numeric.centerY());
		excute(Object_Device, Operation_DiviceDrag, x1, y1,"480" , y1, "10");//480最大
	
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_1");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_0");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("1", result);
	}
	/**
	 * 计算3！
	 */
	public static void test_014(){
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_3");
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"!");
		String number = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/result");
		Assert.assertEquals("6", number);
	}
	/**
	 *计算π=3.1415926536
	 */
	public static void test_015()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"π");
		String number = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/result");
		Assert.assertEquals("3.1415926536", number);
	}
	/**
	 *计算e^2的结果
	 */
	public static void test_016()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"e");
		excute(Object_Text,Operation_ClickWait,"^");
		Rect numeric = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_numeric");
		String x1 = Integer.toString(numeric.centerX());
		String y1 = Integer.toString(numeric.centerY());
		excute(Object_Device, Operation_DiviceDrag, x1, y1,"480" , y1, "10");//480最大
	
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/digit_2");
		excute(Object_ResourceId,Operation_ClickWait,"com.android.calculator2:id/eq");
		String result = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("7.3890560989", result);
	}
	/**
	 *输入"（）"并判断
	 */
	public static void test_017()
	{
		//主体
		Rect advanced = (Rect) excute(Object_ResourceId, Operation_GetBounds, "com.android.calculator2:id/pad_advanced");
		String x = Integer.toString(advanced.centerX());
		String y = Integer.toString(advanced.centerY());
		excute(Object_Device, Operation_DiviceDrag, x, y, "0", y, "10");
		excute(Object_Text,Operation_ClickWait,"(");
		excute(Object_Text,Operation_ClickWait,")");
		String number = (String) excute(Object_ResourceId,Operation_GetText,"com.android.calculator2:id/formula");
		Assert.assertEquals("()", number);
		
		}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
