package Function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DemoParking {
	public ParkingStack parking; // 停车场
	public WaitingQueue waiting; // 队列
	public String x1; // 选择
	public String x2; // 开入的车牌号
	public String x3; // 开出的车牌号

	public DemoParking() {
		parking = new ParkingStack(); // 初始化栈
		waiting = new WaitingQueue(); // 初始化队列
		x1 = "";
		x2 = "";
		x3 = "";
	}

	// 成员方法：菜单

	public void menu() {
		
		try {

			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			x1 = keyin.readLine();
		} catch (IOException e) {
			System.out.print("出错了");
		}
		choice();
	}

	// 成员方法：选择

	public void choice() {
		if (x1.equals("1")) { // 输入1 开入
			System.out.print("请输入车牌号:");
			try {
				BufferedReader keyin1 = new BufferedReader(new InputStreamReader(System.in));
				x2 = new String(keyin1.readLine());
			} catch (IOException e) {
				System.out.print("出错了");
			}
			in(x2);//x2代表进入车牌号
			menu();
		}
		if (x1.equals("2")) { // 输入2 开出
			System.out.print("请输入车牌号:");
			try {
				BufferedReader keyin1 = new BufferedReader(new InputStreamReader(System.in));
				x3 = new String(keyin1.readLine());
			} catch (IOException e) {
				System.out.print("出错了");
			}
			out(x3);//x3开出的车牌号
		}
		if (x1.equals("3")) { // 输入3 查询
			Allprint();
			menu();
		}
		if (x1.equals("4")) { // 输入4 退出

		} else { // 输入不符合规定 返回菜单
			menu();
		}
	}

	
	
	// 成员方法：开入车
	public String in(String value) {
		StringBuilder comein = new StringBuilder();
		if (parking.getLength() < 5) { // 入栈
			parking.push(value);
		
		comein.append("您的车牌号:" + parking.getTop1().number+"\n");
		comein.append("进入停车场时间:" + parking.getTop1().inhour + ":" + parking.getTop1().inminute + ":"
					+ parking.getTop1().insecond);
		
		} else {
			waiting.insertcar(value); // 入队列
		}
		
		return comein.toString();
	}

	// 成员方法：开出车

	public String out(String value) {
		int i = parking.getLength();
		int b = parking.getLength();
		StringBuilder comeout = new StringBuilder();
		// 只有栈里有车 查找到开出
		if (waiting.getLength() == 0) {
			while (parking.getTop1().number.equals(value) == false) {
				parking.pop(); // 开始倒车
				i--;
			}
			parking.pop2(); // 开出这辆车， 进入栈2
			int s = ((parking.getTop2().outhour - parking.getTop2().inhour) * 60 + parking.getTop2().outminute
					- parking.getTop2().inminute) * 60 + parking.getTop2().outsecond - parking.getTop2().insecond
					- 2 * parking.getTop2().count;
			System.out.println(" 您的车牌号:" + parking.getTop2().number);
			System.out.println(" 进入时间:" + parking.getTop2().inhour + ":" + parking.getTop2().inminute + ":"
					+ parking.getTop2().insecond);
			System.out.println(" 离开时间:" + parking.getTop2().outhour + ":" + parking.getTop2().outminute + ":"
					+ parking.getTop2().outsecond);
			System.out.println(" 倒车次数:" + parking.getTop2().count);
			comeout.append(" 您的车牌号:" + parking.getTop2().number+"\n");
			comeout.append(" 进入时间:" + parking.getTop2().inhour + ":" + parking.getTop2().inminute + ":"
					+ parking.getTop2().insecond);
			comeout.append(" 离开时间:" + parking.getTop2().outhour + ":" + parking.getTop2().outminute + ":"
					+ parking.getTop2().outsecond);
			comeout.append(" 您的费用:" + s + "元"+"\n");
			comeout.append(" 欢迎下次再来");
			
			parking.pop3(); // 再从栈2中弹出
			if (i == b) {
			} else {
				for (int n = i; n < b; n++) { // 将车倒回栈1
					parking.push2();
				}
			}
		} else {
			// 看在队列还是在栈里，此时便道上有车辆等候进入停车场
			while (parking.getNum(i).equals(value) == false) { // 栈
				i--;
				if (i == 0) { // 检验队列，此时等候的车在等候队列中即在便道上等待
					int a = waiting.getLength();
					while (waiting.locate(a).number.equals(value) == false) {
						a--;
					}
				
					comeout.append(" 您的车牌号:" + waiting.locate(a).number+"\n");
					comeout.append("欢迎下次再来");
					
					
					waiting.delete(a);
					break;
				}
			}

			if (i > 0) { // 要开出的车在栈里
				int c = i;
				for (; i < b; i++) {
					parking.pop();
				}
				parking.pop2();

				int s = ((parking.getTop2().outhour - parking.getTop2().inhour) * 60 + parking.getTop2().outminute
						- parking.getTop2().inminute) * 60 + parking.getTop2().outsecond - parking.getTop2().insecond
						- 2 * parking.getTop2().count;
				comeout.append(" 您的车牌号:" + parking.getTop2().number);
				comeout.append(" 进入时间:" + parking.getTop2().inhour + ":" + parking.getTop2().inminute + ":"
						+ parking.getTop2().insecond);
				comeout.append(" 离开时间:" + parking.getTop2().outhour + ":" + parking.getTop2().outminute + ":"
						+ parking.getTop2().outsecond);
				
				
				if (s < 0) {
					s = 0; // 费用最少为0
				}
			
				comeout.append(" 您的费用:" + s+"\n");
				comeout.append(" 欢迎下次再来");
				
				parking.pop3();
				for (; c < b; c++) {
					parking.push2();
				}
				parking.push(waiting.getHeader().number); // 队列的第一个元素入栈
				waiting.delete(1); // 删除队列的第一个元素
			}
		}
		return comeout.toString();
	}

	// 查询车位情况 包括栈中和队列中的

	public String Allprint() {
		StringBuilder all = new StringBuilder();
		
		all.append("停车场中:"+"\n");
		all.append(parking.print());
		
		if (waiting.getLength() > 0) {
		
			waiting.print();
			all.append("等待中:"+"\n");
			all.append(waiting.print());
		}
	
		return all.toString();
	}

	public static void main(String[] args) throws IOException { // 应用！！

		DemoParking demo = new DemoParking();
		demo.menu();

	}
}
