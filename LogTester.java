import java.util.*;

public class LogTester {
	
	public static void main(String[] args) {
		LogAnalyzer myLog = new LogAnalyzer();
		//myLog.readFile("data/logFiles/short-test_log.txt");
		//myLog.readFile("data/logFiles/weblog3-short_log.txt");
		//myLog.readFile("data/logFiles/weblog1_log.txt");
		myLog.readFile("data/logFiles/weblog2_log.txt");
		HashMap<String, ArrayList<String>> counts = myLog.IPsForDays();
		//myLog.printAll();
		//System.out.println(myLog.countUniqueIPs() + " Unique IPs");
		//myLog.printAllHigherThanNum(400);
		System.out.println("Unique IP Visits on specific day: " + myLog.uniqueIPVisitsOnDay("Sep 24"));
		System.out.println("Number of unique IPs in Range: " +myLog.countUniqueIPsInRange(200,299));
		//System.out.println(myLog.countVisitsPerIP());
		System.out.println(myLog.mostNumberVisitsByIP(myLog.countVisitsPerIP()));
		System.out.println(myLog.IPsMostVisits(myLog.countVisitsPerIP()));
		//System.out.println(myLog.IPsForDays());
		System.out.println(myLog.dayWithMostIPVisits(counts));
		System.out.println(myLog.IPsWithMostVisitsOnDay(counts, "Sep 29"));
	}
}
