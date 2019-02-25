package ds.hashtablesarray;

public class SimpleHashtable {

	private StoredEmployee[] hashtable;
	
	public SimpleHashtable() {
		hashtable = new StoredEmployee[10];
	}
	
	public void put(String key, Employee employee) {
		int hashedKey = hashKey(key);
		if (occupied(hashedKey)) {
			int stopIndex = hashedKey;
			hashedKey = incrementHashedKey(hashedKey);
			
			while (occupied(hashedKey) && hashedKey != stopIndex) {
				hashedKey = (hashedKey + 1) % hashtable.length; 
			}
		}
		if (occupied(hashedKey)) {
			System.out.println("Sorry, there's already an employee at position " + hashedKey);
		} else {
			hashtable[hashedKey] = new StoredEmployee(key, employee);
		}
	}
	
	public Employee remove(String key) {
		int hashedKey = findKey(key);
		System.out.println("hashedKey = " + hashedKey); 
		if (hashedKey == -1) {
			return null;
		}
		
		Employee employee = hashtable[hashedKey].getEmployee();
		hashtable[hashedKey] = null;
		return employee;
	}
	
	public Employee get(String key) {
		int hashedKey = findKey(key);
		if (hashedKey == -1) {
			return null;
		}
		return hashtable[hashedKey].getEmployee();
	}
	
	private int hashKey(String key) {
		return key.length() % hashtable.length;
	}
	
	private int findKey(String key) {
		int hashedKey = hashKey(key);
		if (hashtable[hashedKey] != null && hashtable[hashedKey].getKey().equals(key)) { 
			return hashedKey;
		}
		
		int stopIndex = hashedKey;
		hashedKey = incrementHashedKey(hashedKey);
		
		while (hashedKey != stopIndex && 
				hashtable[hashedKey] != null && 
				!hashtable[hashedKey].getKey().equals(key)) {
			hashedKey = (hashedKey + 1) % hashtable.length;
		}
		
		if (hashtable[hashedKey] != null && 
				hashtable[hashedKey].getKey().equals(key)) {
			return hashedKey;
		} else {
			return -1;
		}
	}
	
	private int incrementHashedKey(int hashedKey) {
		if (hashedKey == hashtable.length - 1) {
			hashedKey = 0;
		} else {
			hashedKey++;
		}
		
		return hashedKey;
	}
	
	private boolean occupied(int index) {
		return hashtable[index] != null; 
	}
	
	public void printHashtable() {
		for (int i = 0; i < hashtable.length; i++) {
			if (hashtable[i] == null) {
				System.out.println("empty"); 
			} else {
				System.out.println("Position " + i + ": " + hashtable[i].getEmployee());
			}
		}
	}
}
