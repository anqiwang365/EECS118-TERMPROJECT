package Function;

import java.util.Date;

public class ParkingStack {

	public int length1;// ��¼stack1�ĳ���
	public int length2;// ��¼stack2�ĳ���
	public Car[] carStack1;// ͣ����
	public Car[] carStack2;// ���ڵ�������ʱջ

	// ��ʼ��
	public ParkingStack(int length1, int length2, Car[] carStack1, Car[] carStack2) {
		this.length1 = length1;
		this.length2 = length2;
		this.carStack1 = carStack1;
		this.carStack2 = carStack2;
	}

	// ����ͣ�����Ĵ�СΪ5,��������ʱջ��СΪ5
	public ParkingStack() {
		carStack1 = new Car[5]; // 5����λ��ͣ���� ��ջ1
		carStack2 = new Car[5]; // ��������ʱջ��λΪ5 ��ջ2
		length1 = 0;
		length2 = 0;
	}

	// ��ջ������ͣ����
	public void push(String value) {
		Car newcar = new Car(value);
		int n = length1;
		carStack1[n] = newcar;
		Date now = new Date();// ��¼ϵͳʱ��
		int hour = now.getHours();
		int minute = now.getMinutes();
		int second = now.getSeconds();
		carStack1[n].inhour = hour;
		carStack1[n].inminute = minute;
		carStack1[n].insecond = second;
		length1++;
	}

	// ��ջ2�ص�ջ1,������ʱ�ĵ�����ջ�ص�ͣ������
	public void push2() {
		int n = length1;
		carStack1[n] = getTop2();
		length1++;
		length2--;
	}

	// ��ջ1 ��ջ2,����ͣ��������ʱͣ������ջ�����ڴ�ͣ����������
	public void pop() {
		Car temp = getTop1();
		int n = length2;
		carStack2[n] = temp;
		carStack2[n].count++;// ������������
		length1--;
		length2++;
	}

	

	// Ҫ�뿪ͣ�����ĳ�����ջ1 ������ʱ��ջ2�����뿪ʱ��
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
	
	// ��ջ2,�뿪��ʱ������ջ
	public void pop3() {
		length2--;
	}

	// �õ�ջ1��Ԫ�ؼ�ͣ������һ����
	public Car getTop1() {

		return carStack1[length1 - 1];

	}

	// �õ�ջ2��Ԫ�ؼ����ڵ�����ջ�ĵ�һ��Ԫ��
	public Car getTop2() {

		return carStack2[length2 - 1];

	}

	// ͣ�������м�����
	public int getLength() {

		return length1;

	}

	// ���ͣ�����е�i�ų�λ�ĳ��ƺ�
	public String getNum(int i) {

		return carStack1[i - 1].number;

	}

	// �쿴��λ���,���ͣ�����ĳ�������Ϣ
	public String print() {
		StringBuilder print = new StringBuilder();
		int i = 0;
		for (i = 0; i < length1; i++) {
			print.append(i + 1 + "�ų�λ��" + carStack1[i].number);
		}
		return print.toString();
	}

}
