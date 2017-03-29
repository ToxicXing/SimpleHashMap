
public class HashMapTester {
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		System.out.println("Put [one, 1]");
		map.put("one", 1);
		System.out.println("Put [two, 2]");
		map.put("two", 2);
		System.out.println("Put [three, 3]");
		map.put("three", 3);
		System.out.println("Put [four, 4]");
		map.put("four", 4);
		System.out.println("The capacity of this map is " + map.capacity);
		System.out.println("The szie of this map is " + map.size);
		System.out.println("-------------Map get method test---------------------");
		System.out.println("The capacity of this map should be 16.");
		System.out.println("The value of one is " + map.get("one"));
		System.out.println("The value of two is " + map.get("two"));
		System.out.println("The value of three is " + map.get("three"));
		System.out.println("The value of four is " + map.get("four"));
		System.out.println("The value of five is " + map.get("five"));
		System.out.println("The value of null is " + map.get(null));
		System.out.println("-------------Map containsKey method test---------------------");
		System.out.println("Map contains one? "+ map.containsKey("one"));
		System.out.println("Map contains two? "+ map.containsKey("two"));
		System.out.println("Map contains three? "+ map.containsKey("three"));
		System.out.println("Map contains four? "+ map.containsKey("four"));
		System.out.println("Map contains five? "+ map.containsKey("five"));
		System.out.println("-------------Map entry update---------------------");
		map.put("four", 44);
		System.out.println("The value of four is " + map.get("four"));
	}
}
