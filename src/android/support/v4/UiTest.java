package android.support.v4;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.Surface;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import java.io.File;

public class UiTest extends UiAutomatorTestCase {

	public void testDemo() throws UiObjectNotFoundException {
        UiDevice.getInstance().pressHome();
        UiObject browserObject = new UiObject(new UiSelector().text("浏览器"));
//        UiObject browserObject = new UiObject(new UiSelector().className("android.widget.TextView"));
        browserObject.clickAndWaitForNewWindow();
        UiObject editObject = new UiObject(new UiSelector().className("android.widget.EditText"));
		editObject.click();
		UiDevice.getInstance().pressDelete();
        editObject.setText("www.baidu.com");
        UiDevice.getInstance().pressEnter();
        sleep(2000);
    }
	public void testDemo2(){
		UiDevice.getInstance().pressHome();
		sleep(2000);
		//adb shell uiautomator runtest demo.jar -c com.mypack.UiTest#testDemo2 -e phone 188888
		Bundle bundle = getParams();
		String phone = bundle.getString("phone");
		System.out.println("phone: "+phone);
	}
	public void testRecent() throws RemoteException {
		UiDevice.getInstance().pressRecentApps();
		sleep(2000);
	}
	public void testKeyCode(){
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_A);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_B);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_C);

		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_A,1);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_B,1);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_C,1);
	}
	public void testClick() throws UiObjectNotFoundException {
//		UiDevice.getInstance().click(600,300);
//		int h = UiDevice.getInstance().getDisplayHeight();
//		int w = UiDevice.getInstance().getDisplayWidth();
//		UiDevice.getInstance().click(w/2,h/2);
		UiObject phone = new UiObject(new UiSelector().text("电话"));
		Rect r = phone.getBounds();
		int x0 = r.left;
		int y0 = r.top;
		int x1 = r.right;
		int y1 = r.bottom;

		int centerX = r.centerX();
		int centerY = r.centerY();

		System.out.println("["+x0+","+y0+"]");
		System.out.println("["+x1+","+y1+"]");
	}

	public void testDragAndSwipe() {
		//拖拽
//		int startX,startY,endX,endY,steps;
//		startX = 943;
//		startY = 238;
//		endX = 635;
//		endY = 616;
//		steps = 50;
//		UiDevice.getInstance().drag(startX,startY,endX,endY,steps);
		//滑动
//		int h = UiDevice.getInstance().getDisplayHeight();
//		int w = UiDevice.getInstance().getDisplayWidth();
//		UiDevice.getInstance().swipe(w-50,h/2h/2,10);
		//画图 - 解锁
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		Point p4 = new Point();
		Point p5 = new Point();
		p1.x = 290;p1.y = 1229;
		p2.x = 286;p2.y = 1486;
		p3.x = 286;p3.y = 1736;
		p4.x = 543; p4.y = 1483;
		p5.x = 800;p5.y = 1233;
		Point[] pp = {p1,p2,p3,p4,p5};
		UiDevice.getInstance().swipe(pp,10);
	}
	public void testOrientation() throws RemoteException {
		UiDevice.getInstance().setOrientationLeft();
		UiDevice.getInstance().setOrientationRight();
		if(UiDevice.getInstance().isNaturalOrientation()){
			UiDevice.getInstance().setOrientationLeft();
		}
		int a = UiDevice.getInstance().getDisplayRotation();//获取屏幕旋转的角度
		if(a== Surface.ROTATION_0){
			System.out.println(0+"===+++");
		}
		if(a== Surface.ROTATION_90){
			System.out.println(90+"===+++");
		}
		if(a== Surface.ROTATION_180){
			System.out.println(180+"===+++");
		}
		if(a== Surface.ROTATION_270){
			System.out.println(270+"===+++");
		}
	}

	public void testWakeupAndSleep() throws RemoteException {
		if(!UiDevice.getInstance().isScreenOn()){
//			UiDevice.getInstance().sleep();
			UiDevice.getInstance().wakeUp();
		}
	}

	//截屏
	public void testScreen() throws RemoteException {
		UiDevice.getInstance().takeScreenshot(new File("/sdcard/test1.png"));
	}

	//等待空闲
	public void testIdle() throws RemoteException {
		UiDevice.getInstance().click(950,1800);
		UiDevice.getInstance().waitForIdle(2000);
	}

	//包名， 通知栏， 快速设置，布局文件
	public void testGetpackage() throws RemoteException {
		String packageName = UiDevice.getInstance().getCurrentPackageName();
		System.out.println(packageName);
		UiDevice.getInstance().openNotification();
		sleep(3000);
		UiDevice.getInstance().openQuickSettings();
		//导出布局文件
		UiDevice.getInstance().dumpWindowHierarchy("n.xml");
	}

	//实例测试
	public void testBrower() throws RemoteException {
		//灭屏 - 亮屏 - 解锁 - 单击浏览器 - 单击输入框 - 输入www.baidu.com - 按回车键 - 旋转屏幕 - 截屏 - 。。。
		UiDevice.getInstance().sleep();
		sleep(2000);
		if(!UiDevice.getInstance().isScreenOn()){
			UiDevice.getInstance().wakeUp();
		}
		sleep(2000);
		int h = UiDevice.getInstance().getDisplayHeight();
		int w = UiDevice.getInstance().getDisplayWidth();
		//滑动
		UiDevice.getInstance().swipe(w/2,h-50,w/2,50,10);
		sleep(2000);
		//画图 - 解锁
		Point p1 = new Point();
		Point p2 = new Point();
		Point p3 = new Point();
		Point p4 = new Point();
		Point p5 = new Point();
		p1.x = 290;p1.y = 1229;
		p2.x = 286;p2.y = 1486;
		p3.x = 286;p3.y = 1736;
		p4.x = 543; p4.y = 1483;
		p5.x = 800;p5.y = 1233;
		Point[] pp = {p1,p2,p3,p4,p5};
		UiDevice.getInstance().swipe(pp,10);
		sleep(3000);
		UiDevice.getInstance().pressHome();
		//点击浏览器
		sleep(2000);
		UiDevice.getInstance().click(664,1391);
		//点击输入框
		UiDevice.getInstance().click(480,154);
		sleep(2000);
		UiDevice.getInstance().pressDelete();
		sleep(2000);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_W);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_W);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_W);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_PERIOD);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_B);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_A);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_I);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_D);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_U);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_PERIOD);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_C);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_O);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_M);
		UiDevice.getInstance().pressEnter();
		sleep(2000);
		UiDevice.getInstance().setOrientationLeft();
		sleep(2000);
		UiDevice.getInstance().takeScreenshot(new File("/sdcard/browser.png"));
		sleep(2000);
	}


	public void testProguard() throws Exception{

		int i = 0;
		while (i < 3) {
//			mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
			UiDevice.getInstance().pressHome();
			UiDevice.getInstance().pressHome();
			Thread.sleep(1500);
//            mDevice.click(950, 1600);
			UiDevice.getInstance().click(620, 1144);
			Thread.sleep(1500);
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().click(950, 1600);
			Thread.sleep(1500);
			UiDevice.getInstance().pressBack();
//            mDevice.pressHome();
//            mDevice.pressHome();
//        UiObject x=mDevice.findObject(new UiSelector().text("支付宝"));
//        x.clickAndWaitForNewWindow();
//        x.click();
			i++;
		}
	}
	public static void main(String[] args) {
		String jarName = "demo";
		String testClass = "android.support.v4.UiTest";
		String testName = "testProguard";
		String androidId = "1";
		new Helper(jarName,testClass,testName,androidId);
		System.out.println("++++++++++++++");
//		testName = "testDragAndSwipe";
//		new Helper(jarName,testClass,testName,androidId);
	}
}
