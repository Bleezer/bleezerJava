
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import org.junit.Test;

import java.util.*;

public class Tester
{
    @Test
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    @Test
    public void testLogAnalyzer() {
        LogAnalyzer testLagAnalyzer = new LogAnalyzer();
        testLagAnalyzer.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\WebLogProgram\\short-test_log");
        testLagAnalyzer.printAll();
    }

    @Test
    public void testUniqIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }

    @Test
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\UniqueIPData\\weblog1_log");
        int num = 400;
        System.out.println("Higher than " + num + " :");
        la.printAllHigherThanNum(num);
    }

    @Test
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        ArrayList<String> dailyIPs = la.uniqueIPVisitsOnDay("Sep 24");
        for (String s : dailyIPs){
            System.out.println(s);
        }
        System.out.println(dailyIPs.size());
    }

    @Test
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        int low = 400;
        int high = 499;
        int uniqueIPs = la.countUniqueIPsInRange(low,high);
        System.out.println("There are " + uniqueIPs + " IPs between Status Code " + low + " and " + high);
    }

    @Test
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\UniqueIPData\\short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }

    @Test
    public void testCountVisitsPerIPcount() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\UniqueIPData\\short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts.size());
    }

    @Test
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }

    @Test
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(counts));
    }



    @Test
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog1_log");
        HashMap<String,ArrayList<String>> daysAndIPs = la.iPsForDays();
        System.out.println(daysAndIPs);
    }

    @Test
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        HashMap<String,ArrayList<String>> daysAndIPs = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(daysAndIPs));
    }

    @Test
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("C:\\workspace\\Java\\stuffs\\c2w3\\weblog2_log");
        HashMap<String,ArrayList<String>> daysAndIPs = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(daysAndIPs,"Sep 29"));
    }

}
