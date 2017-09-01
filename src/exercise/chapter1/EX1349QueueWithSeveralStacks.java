//�����޸�ջ��ʵ��һ�����У���֤ÿ�����в�������������) ��ֻ��Ҫ�����ε�ջ������

//ԭ�㷨��6��ջ
// QueueWithSevenStacks is based on the description of a six list queue lisp implementation 
//   by Hood and Melville at 
//   https://ecommons.cornell.edu/bitstream/handle/1813/6273/80-433.pdf?sequence=1&isAllowed=y
//lispʵ�֣�
// list[recopy,lendiff,#copy,H,T,h,H',T',Hr]
//an empty queue : list[false,0,0,NIL,NIL,NIL,NIL,NIL,NIL]
// Insert[q,v] = 
//  if !recopy ^ lendiff>0 list[False,lendiff-1,0,H,cons[v,T],NIL,NIL,NIL,NIL]
//     !recopy ^ lendiff=0 Onestep(Onestep([True,0,0,H,cons[v,T],H,NIL,NIL,NIL]))
//      recopy             Onestep(Onestep([True,lendiff-1,#copy,H,T,H,H',cons[v,T],Hr]))
//  fi
//
// Delete[q] = 
//  if !recopy ^ lendiff>0 list[False,lendiff-1,0,cdr[H],T,NIL,NIL,NIL,NIL]
//     !recopy ^ lendiff=0 Onestep(Onestep([True,0,0,cdr[H],T,cdr[H],NIL,NIL,NIL]))
//      recopy             Onestep(Onestep([True,lendiff-1,#copy,H,T,cdr[h],H',T',Hr]))
//  fi
//
// Query[q] =
//  if !recopy car[H]
//      recopy car[h]
//
// Onestep[q] =
//  if !recopy             q
//      recopy ^ !null[H] ^ !null[T]
//          list[True,lendiff+1,#copy+1,cdr[H],cdr[T],h,cons[car[T],H'],T',cons[car[H],Hr]]
//      recopy ^  null[H] ^ !null[T]
//          list[True,lendiff+1,#copy  ,NIL   ,NIL   ,h,cons[car[T],H'],T',Hr]
//      recopy ^  null[H] ^  null[T] ^ copy>1
//          list[True,lendiff+1,#copy-1,NIL   ,NIL   ,h,cons[car[Hr],H'],T',cdr[Hr]]
//      recopy ^  null[H] ^ !null[T]
//          list[false,lendiff+1,0  ,cons[car[Hr],H'],T',NIL,NIL,NIL,NIL]

// ����˵��6��ջ������˵��ά����ͬһ�������ݽṹ�ϵ�6��ջָ��
// ����һ�㷨��java����ʵ��ʱ������java���Ա�������ԣ������һ��ջ
//lisp ��list�ṹʵ����һ������ �� h = H , cdr[h],H��h����ָ���������ָ��
//���� H->Item1->Item2->...->null
//             ^
//             |
//             h
//
// �� java �У�����û��ָ�����ͣ��� h = H ,h.pop()
// h��H����ͬһ���ݽṹ��h.pop()ʵ��Ҳ��H.pop() ������ĸ��ƣ�ʱ�临�Ӷ���O(n)
// ���ԣ������ʹ����һ��ջ H2,H2��push��pop��H1ͬ������һ�ռ仨����ȫ������java�������������

// �� stackoverflow �� antti.huima �Ļش�
//http://stackoverflow.com/questions/5538192/how-to-implement-a-queue-with-three-stacks
//>######SUMMARY
//>>O(1) algorithm is known for 6 stacks  
//>>O(1) algorithm is known for 3 stacks, 
//but using lazy evaluation which in practice corresponds to having extra internal data structures, 
//so it does not constitute a solution  
//>>People near Sedgewick have confirmed they are not aware of a 3-stack solution within all the constraints 
//of the original question

//thanks to GitHub user  zalacer������ο������Ľ��
// https://github.com/zalacer/projects-tn/blob/862c13adc573d6bd88ab242790f67eb5bebc243f/Algorithms4edCh1%2B2/src/ds/QueueWithSevenStacks.java

package exercise.chapter1;

import java.util.NoSuchElementException;
import java.util.Stack;

public class EX1349QueueWithSeveralStacks {

	public static void main(String[] args) {
		QueueWithSevenStacks<Integer> queue = new QueueWithSevenStacks<Integer>();
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		queue.insert(4);
		queue.insert(5);
		queue.insert(6);
		System.out.println(queue.toString());
		queue.Delete();
		System.out.println(queue.toString());
		queue.Delete();
		System.out.println(queue.toString());
		queue.Delete();
		System.out.println(queue.toString());
		queue.Delete();
		System.out.println(queue.toString());
		queue.Delete();
		System.out.println(queue.toString());
		queue.Delete();
		System.out.println(queue.toString());

	}

}

class QueueWithSevenStacks<Item> {
	private boolean recopy = false;
	private int lenH = 0;
	private int lenT = 0;
	private int lenH1 = 0;
	private int lenT1 = 0;
	private int lenHr = 0;
	private Stack<Item> H;
	private Stack<Item> T;
	private Stack<Item> h;
	private Stack<Item> H1;
	private Stack<Item> H2;
	private Stack<Item> T1;
	private Stack<Item> Hr;

	public QueueWithSevenStacks() {
		H = new Stack<Item>();
		T = new Stack<Item>();
		h = new Stack<Item>();
		H1 = new Stack<Item>();
		H2 = new Stack<Item>();
		T1 = new Stack<Item>();
		Hr = new Stack<Item>();
	}

	public boolean isEmpty() {
		return lenH == 0 && lenHr == 0;
	}

	public void insert(Item item) {
		if (isEmpty()) {
			H.push(item);
			lenH++;
			return;
		}
		if (!recopy) {
			if (lenH > lenT) {
				lenT++;
				T.push(item);
			} else if (lenH == lenT) {
				recopy = true;
				T.push(item);
				lenT++;
				Onestep();
				Onestep();
			}
		} else {
			T1.push(item);
			lenT1++;
			Onestep();
			Onestep();
		}
	}

	public Item Delete() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty!");
		Item item = null;
		if (!recopy) {
			if (lenH > lenT) {
				lenH--;
				item = H.pop();
				h.pop();
			} else if (lenH == lenT) {
				recopy = true;
				item = H.pop();
				h.pop();
				lenH--;
				Onestep();
				Onestep();
			}
		} else {
			item = h.pop();
			lenHr--;
			Onestep();
			Onestep();
		}
		return item;
	}

	private void Onestep() {
		if (!recopy || isEmpty()) {

		} else if (lenH != 0 && lenT != 0) {
			lenHr++;
			Item item = T.pop();
			H1.push(item);
			H2.push(item);
			Hr.push(H.pop());
			lenH--;
			lenT--;
			lenH1++;
		} else if (lenH == 0 && lenT != 0) {
			Item item = T.pop();
			H1.push(item);
			H2.push(item);
			lenT--;
			lenH1++;
		} else if (lenH == 0 && lenT == 0 && (lenHr > 1)) {
			lenHr--;

			Item item = Hr.pop();
			H1.push(item);
			H2.push(item);
			lenH1++;

		} else if ((H.isEmpty()) && (T.isEmpty()) && (lenHr == 1)) {
			recopy = false;
			lenHr = 0;

			Item item = Hr.pop();
			H1.push(item);
			H2.push(item);
			lenH1++;

			Stack<Item> stmp = H1;
			H = stmp;
			H1 = new Stack<Item>();
			lenH = lenH1;
			lenH1 = 0;

			stmp = H2;
			h = H2;
			H2 = new Stack<Item>();

			stmp = T1;
			T = stmp;
			T1 = new Stack<Item>();
			lenT = lenT1;
			lenT1 = 0;

			Hr = new Stack<Item>();
		}

	}

	public String toString() {
		return lenH + " " + lenT + " " + " " + H.toString() + " " + T.toString() + " " + h.toString() + " "
				+ H1.toString() + " " + T1.toString() + " " + Hr.toString();
	}

}
