package Function;


public class Car {
	public String number;// ���ƺ�
	public int inhour; // ����Сʱ
	public int inminute; // �����
	public int insecond; // ������
	public int outhour; // ����Сʱ
	public int outminute; // ������
	public int outsecond; // ������
	public int count; // ��������
	public Car link; // ���е�ָ��

	// ���췽��1

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

	// ���췽��2

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

	// ��Ա�������õ�ָ��

	public Car getLink() {

		return link;
	}

	// ��Ա�� �����޸�ָ��

	public void setLink(Car n) {

		link = n;

	}

	// ��Ա�������õ����ƺ�

	public String getNum() {

		return number;

	}

}
