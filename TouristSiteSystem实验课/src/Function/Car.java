package Function;


public class Car {
	public String number;// 车牌号
	public int inhour; // 进入小时
	public int inminute; // 进入分
	public int insecond; // 进入秒
	public int outhour; // 开出小时
	public int outminute; // 开出分
	public int outsecond; // 开出秒
	public int count; // 倒车次数
	public Car link; // 队列的指针

	// 构造方法1

	public Car(String num) {
		this.number = num;
		this.inhour = 0;
		this.inminute = 0;
		this.insecond = 0;
		this.outhour = 0;
		this.outminute = 0;
		this.outsecond = 0;
		int count = 0;
		Car link = null;
	}

	// 构造方法2

	public Car() {

		this.number = "";
		this.inhour = 0;
		this.inminute = 0;
		this.insecond = 0;
		this.outhour = 0;
		this.outminute = 0;
		this.outsecond = 0;
		int count = 0;
		Car link = null;
	}

	// 成员方法：得到指针

	public Car getLink() {

		return link;
	}

	// 成员方 法：修改指针

	public void setLink(Car n) {

		link = n;

	}

	// 成员方法；得到车牌号

	public String getNum() {

		return number;

	}

}
