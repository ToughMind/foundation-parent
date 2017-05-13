package record.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import com.alibaba.fastjson.JSON;

/**
 * 测试双层比较的情况下，先分组再比较和全放在一个比较器，哪个效率更高。
 * <P>
 * 充分说明复杂排序，还是想办法写一个比较器，然后调用Collects.sort()方法排序。
 * 
 * @author 刘泉
 * @date 2017年1月19日 下午4:40:16
 */
public class ComparableTest {

	public static void main(String[] args) {
		List<Item> list = new ArrayList<>();
		List<ItemComparator> list2 = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			Random random = new Random();
			Item item = new Item(random.nextInt(4), random.nextInt(10), random.nextInt(10),
					String.valueOf(random.nextInt(10)));
			list.add(item);
			ItemComparator ic = new ItemComparator(item);
			list2.add(ic);
		}
		long start = System.currentTimeMillis();
		List<Item> l0 = new ArrayList<>();
		List<Item> l1 = new ArrayList<>();
		List<Item> l2 = new ArrayList<>();
		List<Item> l3 = new ArrayList<>();
		for (Item item : list) {
			if (item.getPriority() == 0) {
				l0.add(item);
			}
			if (item.getPriority() == 1) {
				l1.add(item);
			}
			if (item.getPriority() == 2) {
				l2.add(item);
			}
			if (item.getPriority() == 3) {
				l3.add(item);
			}
		}
		Collections.sort(l0, new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				if (o1.rank < o2.rank) {
					return -1;
				} else if (o1.rank > o2.rank) {
					return 1;
				}
				return 0;
			}
		});
		Collections.sort(l1, new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				if (o1.rank < o2.rank) {
					return 1;
				} else if (o1.rank > o2.rank) {
					return -1;
				}
				return 0;
			}
		});
		Collections.sort(l2, new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				if (o1.price < o2.price) {
					return 1;
				} else if (o1.price > o2.price) {
					return -1;
				}
				return 0;
			}
		});
		Collections.sort(l3, new Comparator<Item>() {

			@Override
			public int compare(Item o1, Item o2) {
				if (o1.price < o2.price) {
					return 1;
				} else if (o1.price > o2.price) {
					return -1;
				}
				return 0;
			}
		});
		List<Item> newList = new ArrayList<>();
		newList.addAll(l0);
		newList.addAll(l1);
		newList.addAll(l2);
		newList.addAll(l3);
		System.out.println("分组排序前：");
		System.out.println(JSON.toJSONString(list));
		System.out.println("分组排序后：");
		System.out.println(JSON.toJSONString(newList));
		System.out.println("耗时：" + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		Collections.sort(list2);
		System.out.println("集合工具类排序前：");
		System.out.println(JSON.toJSONString(list2));
		System.out.println("集合工具类分组排序后：");
		System.out.println(JSON.toJSONString(list2));
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
	}

	static class Item {

		private int priority;

		private int rank;

		private int price;

		private String last;

		public Item(int priority, int rank, int price, String last) {
			super();
			this.priority = priority;
			this.rank = rank;
			this.price = price;
			this.last = last;
		}

		public int getPriority() {
			return priority;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public String getLast() {
			return last;
		}

		public void setLast(String last) {
			this.last = last;
		}

	}

	static class ItemComparator implements Comparable<ItemComparator> {

		private Item item;

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public ItemComparator(Item item) {
			this.item = item;
		}

		@Override
		public int compareTo(ItemComparator other) {
			if (this.item.priority < other.item.priority) {
				return -1;
			} else if (this.item.priority > other.item.priority) {
				return 1;
			}
			if (this.item.priority == 0) {
				if (this.item.rank < other.item.rank) {
					return -1;
				} else if (this.item.rank > other.item.rank) {
					return 1;
				}
			}
			if (this.item.priority == 1) {
				if (this.item.rank > other.item.rank) {
					return -1;
				} else if (this.item.rank < other.item.rank) {
					return 1;
				}
			}
			if (this.item.priority == 2) {
				if (this.item.price < other.item.price) {
					return -1;
				} else if (this.item.price > other.item.price) {
					return 1;
				}
			}
			if (this.item.priority == 3) {
				if (this.item.price > other.item.price) {
					return -1;
				} else if (this.item.price < other.item.price) {
					return 1;
				}
			}
			return 0;
			// return this.item.last.compareTo(other.item.last);
		}

	}

}
