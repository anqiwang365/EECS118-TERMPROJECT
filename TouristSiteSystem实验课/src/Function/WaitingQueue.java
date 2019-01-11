package Function;

public class WaitingQueue {

	private Car header;
	private Car current;
	private int size;// 队列的大小

	public WaitingQueue()

	{
		header = new Car("");
		current = header;
		size = 0;
	}

	// 把当前位置之后插入一个新结点

	public void insertcar(String value) {

		Car newcar = new Car(value);
		newcar.setLink(current.link);
		current.setLink(newcar);
		current = newcar;
		size += 1;
		System.out.println("*********************************************");
		System.out.println("您的车牌号:" + newcar.number);
		System.out.println("停车场已满，您现在在等待中，您的位置是" + size + "个位置");
		System.out.println("*********************************************");

	}

	// 删除当前位置结点后面的结点

	public void delete() {

		Car replace;
		replace = current.link;
		if (replace == null)
			return;
		current.setLink(replace.link);
		replace = null;
		size -= 1;

	}

	// 删除第i个结点

	public void delete(int i) {
		if (i > size || i <= 0)
			return;
		locate(i - 1);
		delete();
	}

	// 由当前结点到下一个结点
	public Car getNext() {

		if (current == null)
			return null;
		current = current.link;
		return current;
	}
	// 得到队列的最后一辆车

	public Car getLast() {

		while (current.link != null) {
			getNext();
		}
		return current;

	}

	// 由车牌号找到车

	public Car find(String value) {

		current = header;
		while (current.number != value) {
			if (current.link == null) {
				System.out.println("找不到需要的数！！！！");
				current = header;
				break;
			}
			current = current.link;
		}
		return current;
	}

	// 取得第m位置的元素

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

	// 取得队列第一个元素
	public Car getHeader() {
		return header.link;
	}

	// 队列里有多少车
	public int getLength() {
		return size;
	}

	// 察看车位
	public String print() {
		StringBuilder print = new StringBuilder();
		current = header.getLink();
		while (current != null) {
			int i = 1;
			// System.out.print(i+"号车位："+current.getNum());
			// System.out.println(" ");
			print.append(i + "号车位：" + current.getNum() + "\n");

			i++;
			getNext();
		}
		return print.toString();
	}
}
