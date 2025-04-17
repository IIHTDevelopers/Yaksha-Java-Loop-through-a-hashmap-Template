package com.yaksha.assignment;

import java.util.HashMap;
import java.util.Map;

public class HashMapOperations {

	public static void main(String[] args) {
		// **Creating a HashMap**
		Map<String, Integer> hashMap = new HashMap<>();

		// **Adding items to the HashMap using put()**
		hashMap.put("One", 1);
		hashMap.put("Two", 2);
		hashMap.put("Three", 3);
		System.out.println("HashMap after adding items: " + hashMap);

		// **Accessing an item using get()**
		Integer value = hashMap.get("Two");
		System.out.println("Value of key 'Two': " + value);

		// **Removing an item using remove()**
		hashMap.remove("Three");
		System.out.println("HashMap after removing item with key 'Three': " + hashMap);

		// **Looping through the HashMap using for-each loop**
		System.out.println("Looping through the HashMap:");
		for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}
	}
}
