package Function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DemoParking {
	public ParkingStack parking; // ͣ����
	public WaitingQueue waiting; // ����
	public String x1; // ѡ��
	public String x2; // ����ĳ��ƺ�
	public String x3; // �����ĳ��ƺ�

	public DemoParking() {
		parking = new ParkingStack(); // ��ʼ��ջ
		waiting = new WaitingQueue(); // ��ʼ������
		x1 = "";
		x2 = "";
		x3 = "";
	}

	// ��Ա�������˵�

	public void menu() {
		
		try {

			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			x1 = keyin.readLine();
		} catch (IOException e) {
			System.out.print("������");
		}
		choice();
	}

	// ��Ա������ѡ��

	public void choice() {
		if (x1.equals("1")) { // ����1 ����
			System.out.print("�����복�ƺ�:");
			try {
				BufferedReader keyin1 = new BufferedReader(new InputStreamReader(System.in));
				x2 = new String(keyin1.readLine());
			} catch (IOException e) {
				System.out.print("������");
			}
			in(x2);//x2������복�ƺ�
			menu();
		}
		if (x1.equals("2")) { // ����2 ����
			System.out.print("�����복�ƺ�:");
			try {
				BufferedReader keyin1 = new BufferedReader(new InputStreamReader(System.in));
				x3 = new String(keyin1.readLine());
			} catch (IOException e) {
				System.out.print("������");
			}
			out(x3);//x3�����ĳ��ƺ�
		}
		if (x1.equals("3")) { // ����3 ��ѯ
			Allprint();
			menu();
		}
		if (x1.equals("4")) { // ����4 �˳�

		} else { // ���벻���Ϲ涨 ���ز˵�
			menu();
		}
	}

	
	
	// ��Ա���������복
	public String in(String value) {
		StringBuilder comein = new StringBuilder();
		if (parking.getLength() < 5) { // ��ջ
			parking.push(value);
		
		comein.append("���ĳ��ƺ�:" + parking.getTop1().number+"\n");
		comein.append("����ͣ����ʱ��:" + parking.getTop1().inhour + ":" + parking.getTop1().inminute + ":"
					+ parking.getTop1().insecond);
		
		} else {
			waiting.insertcar(value); // �����
		}
		
		return comein.toString();
	}

	// ��Ա������������

	public String out(String value) {
		int i = parking.getLength();
		int b = parking.getLength();
		StringBuilder comeout = new StringBuilder();
		// ֻ��ջ���г� ���ҵ�����
		if (waiting.getLength() == 0) {
			while (parking.getTop1().number.equals(value) == false) {
				parking.pop(); // ��ʼ����
				i--;
			}
			parking.pop2(); // ������������ ����ջ2
			int s = ((parking.getTop2().outhour - parking.getTop2().inhour) * 60 + parking.getTop2().outminute
					- parking.getTop2().inminute) * 60 + parking.getTop2().outsecond - parking.getTop2().insecond
					- 2 * parking.getTop2().count;
			System.out.println(" ���ĳ��ƺ�:" + parking.getTop2().number);
			System.out.println(" ����ʱ��:" + parking.getTop2().inhour + ":" + parking.getTop2().inminute + ":"
					+ parking.getTop2().insecond);
			System.out.println(" �뿪ʱ��:" + parking.getTop2().outhour + ":" + parking.getTop2().outminute + ":"
					+ parking.getTop2().outsecond);
			System.out.println(" ��������:" + parking.getTop2().count);
			comeout.append(" ���ĳ��ƺ�:" + parking.getTop2().number+"\n");
			comeout.append(" ����ʱ��:" + parking.getTop2().inhour + ":" + parking.getTop2().inminute + ":"
					+ parking.getTop2().insecond);
			comeout.append(" �뿪ʱ��:" + parking.getTop2().outhour + ":" + parking.getTop2().outminute + ":"
					+ parking.getTop2().outsecond);
			comeout.append(" ���ķ���:" + s + "Ԫ"+"\n");
			comeout.append(" ��ӭ�´�����");
			
			parking.pop3(); // �ٴ�ջ2�е���
			if (i == b) {
			} else {
				for (int n = i; n < b; n++) { // ��������ջ1
					parking.push2();
				}
			}
		} else {
			// ���ڶ��л�����ջ���ʱ������г����Ⱥ����ͣ����
			while (parking.getNum(i).equals(value) == false) { // ջ
				i--;
				if (i == 0) { // ������У���ʱ�Ⱥ�ĳ��ڵȺ�����м��ڱ���ϵȴ�
					int a = waiting.getLength();
					while (waiting.locate(a).number.equals(value) == false) {
						a--;
					}
				
					comeout.append(" ���ĳ��ƺ�:" + waiting.locate(a).number+"\n");
					comeout.append("��ӭ�´�����");
					
					
					waiting.delete(a);
					break;
				}
			}

			if (i > 0) { // Ҫ�����ĳ���ջ��
				int c = i;
				for (; i < b; i++) {
					parking.pop();
				}
				parking.pop2();

				int s = ((parking.getTop2().outhour - parking.getTop2().inhour) * 60 + parking.getTop2().outminute
						- parking.getTop2().inminute) * 60 + parking.getTop2().outsecond - parking.getTop2().insecond
						- 2 * parking.getTop2().count;
				comeout.append(" ���ĳ��ƺ�:" + parking.getTop2().number);
				comeout.append(" ����ʱ��:" + parking.getTop2().inhour + ":" + parking.getTop2().inminute + ":"
						+ parking.getTop2().insecond);
				comeout.append(" �뿪ʱ��:" + parking.getTop2().outhour + ":" + parking.getTop2().outminute + ":"
						+ parking.getTop2().outsecond);
				
				
				if (s < 0) {
					s = 0; // ��������Ϊ0
				}
			
				comeout.append(" ���ķ���:" + s+"\n");
				comeout.append(" ��ӭ�´�����");
				
				parking.pop3();
				for (; c < b; c++) {
					parking.push2();
				}
				parking.push(waiting.getHeader().number); // ���еĵ�һ��Ԫ����ջ
				waiting.delete(1); // ɾ�����еĵ�һ��Ԫ��
			}
		}
		return comeout.toString();
	}

	// ��ѯ��λ��� ����ջ�кͶ����е�

	public String Allprint() {
		StringBuilder all = new StringBuilder();
		
		all.append("ͣ������:"+"\n");
		all.append(parking.print());
		
		if (waiting.getLength() > 0) {
		
			waiting.print();
			all.append("�ȴ���:"+"\n");
			all.append(waiting.print());
		}
	
		return all.toString();
	}

	public static void main(String[] args) throws IOException { // Ӧ�ã���

		DemoParking demo = new DemoParking();
		demo.menu();

	}
}
