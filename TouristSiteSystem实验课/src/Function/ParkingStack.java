package Function;

import java.util.Date;

public class ParkingStack {

	public int length1;// 记录stack1的长度
	public int length2;// 记录stack2的长度
	public Car[] carStack1;// 停车场
	public Car[] carStack2;// 用于倒车的临时栈

	// 初始化
	public ParkingStack(int length1, int length2, Car[] carStack1, Car[] carStack2) {
		this.length1 = length1;
		this.length2 = length2;
		this.carStack1 = carStack1;
		this.carStack2 = carStack2;
	}

	// 设置停车场的大小为5,倒车的临时栈大小为5
	public ParkingStack() {
		carStack1 = new Car[5]; // 5个车位的停车场 即栈1
		carStack2 = new Car[5]; // 倒车的临时栈车位为5 即栈2
		length1 = 0;
		length2 = 0;
	}

	// 入栈即进入停车场
	public void push(String value) {
		Car newcar = new Car(value);
		int n = length1;
		carStack1[n] = newcar;
		Date now = new Date();// 记录系统时间
		int hour = now.getHours();
		int minute = now.getMinutes();
		int second = now.getSeconds();
		carStack1[n].inhour = hour;
		carStack1[n].inminute = minute;
		carStack1[n].insecond = second;
		length1++;
	}

	// 由栈2回到栈1,即由临时的倒车的栈回到停车场场
	public void push2() {
		int n = length1;
		carStack1[n] = getTop2();
		length1++;
		length2--;
	}

	// 出栈1 入栈2,即从停车场到临时停车场的栈，用于从停车场开出车
	public void pop() {
		Car temp = getTop1();
		int n = length2;
		carStack2[n] = temp;
		carStack2[n].count++;// 倒车次数增加
		length1--;
		length2++;
	}

	

	// 要离开停车场的车辆出栈1 并且暂时入栈2计算离开时间
	public void pop2() {
		int n = length1 - 1;
		Date now = new Date();
		int hour = now.getHours();
		int minute = now.getMinutes();
		int second = now.getSeconds();
		carStack1[n].outhour = hour;
		carStack1[n].outminute = minute;
		carStack1[n].outsecond = second;
		Car temp = getTop1();
		carStack2[length2] = temp;
		length1--;
		length2++;

	}
	
	// 出栈2,离开临时倒车的栈
	public void pop3() {
		length2--;
	}

	// 得到栈1顶元素即停车场第一个车
	public Car getTop1() {

		return carStack1[length1 - 1];

	}

	// 得到栈2顶元素即用于倒车的栈的第一个元素
	public Car getTop2() {

		return carStack2[length2 - 1];

	}

	// 停车场里有几辆车
	public int getLength() {

		return length1;

	}

	// 获得停车场中第i号车位的车牌号
	public String getNum(int i) {

		return carStack1[i - 1].number;

	}

	// 察看车位情况,输出停车场的车辆及信息
	public String print() {
		StringBuilder print = new StringBuilder();
		int i = 0;
		for (i = 0; i < length1; i++) {
			print.append(i + 1 + "号车位：" + carStack1[i].number);
		}
		return print.toString();
	}

}
