import java.util.*;
public class HashMap<K,V>{
	static final int DEFAULT_CPAPCITY = 1 << 2;
	HashEntry<K,V>[] table;
	int size;
	int capacity;
	public HashMap() {
		capacity = DEFAULT_CPAPCITY;
	}
	public HashMap(int cap) {
		this.capacity = cap;
	}
	// Node -> Map.Entry
	//		-> Node of LinkedList
	class HashEntry<K, V> {
		final int hash;
		final K key;
		V value;
		HashEntry<K,V> next;
		// constructor
		HashEntry(int hash, K key, V value, HashEntry<K,V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
		public final String toString() { return key + "=" + value; }
		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}
	}
	static final int hash(Object key) {
		return (key == null) ? 0 : key.hashCode();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		HashEntry<K, V> oldNode;  int hash = hash(key);
		if (table == null || table.length == 0) {
			table = (HashEntry<K, V>[]) new HashEntry[capacity];
		}
		int len = table.length;
		int index = (len - 1) & hash;
		if (table[index] == null) {
			// table[index]处没有Node
			table[index] = new HashEntry<K,V>(hash, key, value, null);
		} else {
			// table[index]处有node，但是不知道有的那些nodes的key是不是一样的。
			HashEntry<K, V> curNode;
			oldNode = table[index];
	
			if (oldNode.hash == hash && 
					oldNode.key == key || (key != null && oldNode.key.equals(key))){
				// hash值一样，key也一样,说明找到了node，说明put的key已经存在在HashMap里了，要更新value。
				curNode = oldNode;
			} else {
				// hash值不一样/key不一样/都不一样  -> 找next节点。
				for (int count = 0; ; count++) {
					System.out.println("LinkedList: Not " + oldNode.key);
					curNode = oldNode.next;
					if (oldNode.next == null) {
						// 如果是链表尾
						oldNode.next = new HashEntry(hash, key, value, null);
						break;
					}
					if (curNode.hash == hash &&
							(curNode.key == key || (key != null && curNode.key.equals(key)))) {
						// 找到了Node，node是curNode
						break;
					}
					oldNode = curNode;
				}
			}
			if (curNode != null) {
				V oldValue = curNode.value;
				curNode.value = value;
				return oldValue;
			}
		}
		size++;
		return null;
	}
	public V get(Object key) {
		int hash = hash(key);
		int len = table.length;
		if (table != null && table.length > 0 && table[hash & (len - 1)] != null) {
			HashEntry<K, V> firstNode = table[hash & (len - 1)];
			if (firstNode.hash == hash && 
					firstNode.key == key || (key != null && firstNode.key.equals(key))) {
				return firstNode.value;
			}
			HashEntry<K, V> curNode = firstNode.next;
			if (firstNode.next != null) {
				do {
					if (curNode.hash == hash &&
							curNode.key == key || (curNode.key != null && curNode.key.equals(key))) {
						return curNode.value;
					} 
				}while ((curNode = curNode.next) != null);
			}
		}
		return null;
	}
	
	public boolean containsKey(Object key) {
		return get(key) != null;
	}
	
	public V remove(Object key) {
		int hash = hash(key);
		int len = table.length;
		int index = hash & (len - 1);
		if (table != null && table.length > 0 && table[index] != null) {
			HashEntry<K, V> prevNode = table[index];
			HashEntry<K, V> curNode = null; HashEntry<K, V> nodeRemoved = null;
			if (prevNode.hash == hash && 
					prevNode.key == key || (key != null && prevNode.key.equals(key))) {
				nodeRemoved = prevNode;
			}
			curNode = prevNode.next;
			if (prevNode.next != null) {
				do {
					if (curNode.hash == hash && 
							curNode.key == key || (key != null && curNode.key.equals(key))) {
						nodeRemoved = curNode;
						break;
					}
					prevNode = curNode;
				} while ((curNode = curNode.next) != null);
			}
			if (nodeRemoved != null) {
				if (nodeRemoved == prevNode) {
					table[index] = nodeRemoved.next;
				} else {
					prevNode.next = nodeRemoved.next;
				}
				size--;
				return nodeRemoved.value;
			}
		}
		return null;
	}
}
