import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;

public class LogAnalyzer {

    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()){
            LogEntry currRecords = WebLogParser.parseEntry(s);
            records.add(currRecords);

        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            int currStatusCode = le.getStatusCode();
            if (currStatusCode > num){
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueList = new ArrayList<String>();

        for (LogEntry le : records){
            String currDate = le.getAccessTime().toString().substring(4,10);
            String currIP = le.getIpAddress();
            if (!uniqueList.contains(currIP) && currDate.equals(someday)){
                uniqueList.add(le.getIpAddress());
            }
        }
        return uniqueList;
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            int currStatusCode = le.getStatusCode();
            String currIP = le.getIpAddress();
            if (low <= currStatusCode && currStatusCode <= high && !uniqueIPs.contains(currIP)){
                uniqueIPs.add(currIP);
            }
        }
        return uniqueIPs.size();
    }

    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String, Integer>();

        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)){
                counts.put(ip,1);
            }else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }

}
