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

    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int maxCount = 0;
        for (String s : counts.keySet()){
            int currCount = counts.get(s);
            if (maxCount < currCount){
                maxCount = currCount;
            }
        }
        return maxCount;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        ArrayList<String> mostVisiters = new ArrayList<String>();
        int maxCount = mostNumberVisitsByIP(counts);
        for (String s : counts.keySet()){
            if (counts.get(s).equals(maxCount)){
                mostVisiters.add(s);
            }
        }
        return mostVisiters;
    }

    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> daysAndIPs = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records){
            String currDate = le.getAccessTime().toString().substring(4,10);
            String currIP = le.getIpAddress();
            if (!daysAndIPs.containsKey(currDate)){
                ArrayList<String> currIPList = new ArrayList<String>();
                currIPList.add(currIP);
                daysAndIPs.put(currDate,currIPList);
            }else {
                ArrayList<String> currIPList = daysAndIPs.get(currDate);
                currIPList.add(currIP);
                daysAndIPs.put(currDate,currIPList);
            }
        }
        return daysAndIPs;
    }

    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> daysAndIPs){
        String busyDay = "";
        int count = 0;
        for (String day : daysAndIPs.keySet()){
            if (count < daysAndIPs.get(day).size()){
                count = daysAndIPs.get(day).size();
                busyDay = day;
            }
        }
        return busyDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> daysAndIPs, String day){
        ArrayList<String> dailyList = daysAndIPs.get(day);
        ArrayList<String> returnList = new ArrayList<String>();
        HashMap<String,Integer> dailyCount = new HashMap<String, Integer>();
        int maxCount = 0;
        for (String IP : dailyList){
            if (!dailyCount.containsKey(IP)){
                dailyCount.put(IP,1);
            }else {
                dailyCount.put(IP,dailyCount.get(IP)+1);
            }
        }
        for (String IP : dailyCount.keySet()){
            if (maxCount < dailyCount.get(IP)){
                maxCount = dailyCount.get(IP);
            }
        }
        for (String IP : dailyCount.keySet()){
            if (maxCount == dailyCount.get(IP)){
                returnList.add(IP);
            }
        }
        return returnList;
    }
}
