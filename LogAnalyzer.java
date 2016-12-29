
import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
	  private ArrayList<LogEntry> records;
	     
	     public LogAnalyzer() {
	    	 records = new ArrayList<LogEntry>();
	     }
	        
	     public void readFile(String filename) {
	         FileResource f = new FileResource(filename);
	         for (String line : f.lines()) {
	        	 records.add(WebLogParser.parseEntry(line));
	         }
	         
	     }
	     
	     public int countUniqueIPs() {
	    	 HashMap<String, Integer> counts = countVisitsPerIP();
	    	 return counts.size();
	     }
	     
	     public void printAllHigherThanNum(int num) {
	    	 for (LogEntry le : records) {
	    		 int statusCode = le.getStatusCode();
	    		 if (statusCode > num)  {
	    			 System.out.println(statusCode);
	    		 }
	    	 }
	     }
	     
	     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
	    	 ArrayList<String> uniqIPs = new ArrayList<String>();
	    	 for (LogEntry le : records) {
	    		 String ipAddr = le.getIpAddress();
	    		 Date date = le.getAccessTime();
	    		 String d = date.toString().substring(4, 10);
	    		 if (!uniqIPs.contains(ipAddr)) {
	    			 if(d.contains(someday)) {
	    				 uniqIPs.add(ipAddr);
	    			 }
	    		 }
	    	 }
	    	System.out.println("Number of unique visits on " +someday+ ": "+uniqIPs.size());
	    	return uniqIPs;
	     }
	     
	     public int countUniqueIPsInRange(int low, int high) {
	    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
	    	 for (LogEntry le : records) {
	    		 int statusCode = le.getStatusCode();
	    		 String ipAddr = le.getIpAddress();
	    		 if (statusCode >= low && statusCode <= high)  {
	    			 if (!uniqueIPs.contains(ipAddr)) {
	    				 uniqueIPs.add(ipAddr);
	    			 }
	    		 }
	    	 }
	    	 return uniqueIPs.size();
	     }
	     
	     public HashMap<String, Integer> countVisitsPerIP(){
	    	 HashMap<String, Integer> counts = new HashMap<String, Integer>();
	    	 for (LogEntry le : records) {
	    		 String ip = le.getIpAddress();
	    		 if (!counts.containsKey(ip)) {
	    			 counts.put(ip, 1);
	    		 }
	    		 else {
	    			 counts.put(ip, counts.get(ip) + 1);
	    		 }
	    	 }
	    	 return counts;
	     }
	     
	     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
	    	int maxNum = 0; 
	    	for (String ip : counts.keySet()) {
	    		if (counts.get(ip) > maxNum) {
	    			maxNum = counts.get(ip);
	    		}
	    	}
	    	return maxNum;	    	 
	     }
	     
	     public ArrayList<String> IPsMostVisits(HashMap<String, Integer> counts) {
	    	int maxNum = mostNumberVisitsByIP(counts);
	    	ArrayList<String> IPCounts = new ArrayList<String>();
	    	for (String ip : counts.keySet()) {
	    		if (counts.get(ip) == maxNum) {
	    			IPCounts.add(ip);
	    		}
	    	}
			return IPCounts;
	     }
	     
	     public HashMap<String, ArrayList<String>> IPsForDays() {
	    	HashMap<String, ArrayList<String>> counts = new HashMap<String, ArrayList<String>>();
	    	for (LogEntry le : records) {
	    		//get IP address
	    		String ipAddr = le.getIpAddress();
	    		// gets date site is accessed
	    		Date date = le.getAccessTime();
	    		// converts date to string
	    		String d = date.toString().substring(4, 10);
	    		// creates empty ArrayList
	    		ArrayList<String> IpAddrCount = new ArrayList<String>();
	    		if (counts.containsKey(d)) {
	    		counts.get(d).add(ipAddr);
	    		}
	    		else {
	    			IpAddrCount.add(ipAddr);
	    			counts.put(d, IpAddrCount);
	    		}
	    	}
			return counts; 
	     }
	     
	     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> counts) {
	    	IPsForDays();
	    	int mostVisits = 0;
	    	String DayWithMostVisits = "";
	    	for (String day : counts.keySet()) {
	    		ArrayList<String> numIPs = counts.get(day);
	    		int numVisits = numIPs.size();
	    		if (numVisits > mostVisits) {
	    			mostVisits = numVisits;
	    			DayWithMostVisits = day;
	    		}
	    	}
			return DayWithMostVisits;
	    	 
	     }
	     
	     public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> counts, String day) {
	    	HashMap<String, ArrayList<String>> IpForDays = IPsForDays();
	    	// gets ArrayList of IP visits for specific day
	    	ArrayList<String> specificDay = IpForDays.get(day);
	    	ArrayList<String> mostVisit = new ArrayList<String>();
	    	HashMap<String, Integer> IpCount = new HashMap<String, Integer>();
	    	Integer count = 0;
	    	// creates HashMap with IP and # of times seen as value
	    	for (String ip : specificDay) {
	    		if (IpCount.containsKey(ip)) {
	    			IpCount.put(ip, IpCount.get(ip) + 1);
	    		}
	    		else {
	    			IpCount.put(ip, 1);
	    		}
	    	}
	    	System.out.println(IpCount);
	    	// finds IP with greatest value and adds to MostVisit
	    	for (String ip : IpCount.keySet()) {
	    		if (IpCount.get(ip) > count) {
	    			count = IpCount.get(ip);
	    			mostVisit.add(ip);
	    		}
	    		
	    	}
	    	return mostVisit;
	    	}
	    	
	        
	     public void printAll() {
	         for (LogEntry le : records) {
	             System.out.println(le);
	         }
	     }
}
