package Function;

public class WaitingQueue {

	private Car header;
	private Car current;
	private int size;// ���еĴ�С

	public WaitingQueue()

	{
		header = new Car("");
		current = header;
		size = 0;
	}

	// �ѵ�ǰλ��֮�����һ���½��

	public void insertcar(String value) {

		Car newcar = new Car(value);
		newcar.setLink(current.link);
		current.setLink(newcar);
		current = newcar;
		size += 1;
		System.out.println("*********************************************");
		System.out.println("���ĳ��ƺ�:" + newcar.number);
		System.out.println("ͣ�����������������ڵȴ��У�����λ����" + size + "��λ��");
		System.out.println("*********************************************");

	}

	// ɾ����ǰλ�ý�����Ľ��

	public void delete() {

		Car replace;
		replace = current.link;
		if (replace == null)
			return;
		current.setLink(replace.link);
		replace = null;
		size -= 1;

	}

	// ɾ����i�����

	public void delete(int i) {
		if (i > size || i <= 0)
			return;
		locate(i - 1);
		delete();
	}

	// �ɵ�ǰ��㵽��һ�����
	public Car getNext() {

		if (current == null)
			return null;
		current = current.link;
		return current;
	}
	// �õ����е����һ����

	public Car getLast() {

		while (current.link != null) {
			getNext();
		}
		return current;

	}

	// �ɳ��ƺ��ҵ���

	public Car find(String value) {

		current = header;
		while (current.number != value) {
			if (current.link == null) {
				System.out.println("�Ҳ�����Ҫ������������");
				current = header;
				break;
			}
			current = current.link;
		}
		return current;
	}

	// ȡ�õ�mλ�õ�Ԫ��

	public Car locate(int m) {

		if (m > size)
			return null;
		current = header;
		int i;
		for (i = m; i > 0; i--) {
			getNext();
		}
		return current;
	}

	// ȡ�ö��е�һ��Ԫ��
	public Car getHeader() {
		return header.link;
	}

	// �������ж��ٳ�
	public int getLength() {
		return size;
	}

	// �쿴��λ
	public String print() {
		StringBuilder print = new StringBuilder();
		current = header.getLink();
		while (current != null) {
			int i = 1;
			// System.out.print(i+"�ų�λ��"+current.getNum());
			// System.out.println(" ");
			print.append(i + "�ų�λ��" + current.getNum() + "\n");

			i++;
			getNext();
		}
		return print.toString();
	}
}
