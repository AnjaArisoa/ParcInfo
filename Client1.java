package parc;
import parc.*;
import java.io.*;
import java.net.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean ;
import com.sun.management.OperatingSystemMXBean;

public class Client1 {

   public static void main(String[] args) {

       // Server Host
       final String serverHost = "localhost";
        Socket socketOfClient = null;
       BufferedWriter os = null;
       BufferedReader is = null;
       

       try {
           socketOfClient = new Socket(serverHost, 12000);
           os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
           is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

       } catch (UnknownHostException e) {
           System.err.println("Don't know about host " + serverHost);
           return;
       } catch (IOException e) {
           System.err.println("Couldn't get I/O for the connection to " + serverHost);
           return;
       }
       while(true){
       try {
        OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        long prevUpTime = runtimeMXBean.getUptime();
        long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime();
        double cpuUsage;
    operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    long upTime = runtimeMXBean.getUptime();
    long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
    long elapsedCpu = processCpuTime - prevProcessCpuTime;
    long elapsedTime = upTime - prevUpTime;
    long total = (Runtime.getRuntime().totalMemory()/1024);
    long used  = (Runtime.getRuntime().totalMemory()/1024 - Runtime.getRuntime().freeMemory()/1024);
    long free  = (Runtime.getRuntime().freeMemory()/1024);
    File disk=new File("C:");
    File disk1=new File("D:");
    long totalspace=disk.getTotalSpace();
    long freespace=disk.getFreeSpace();
    long usedspace=disk.getUsableSpace();
    long totalspace1=disk1.getTotalSpace();
    long freespace1=disk1.getFreeSpace();
    long usedspace1=disk1.getUsableSpace();
        os.write("System architecture : " + operatingSystemMXBean.getArch());
        os.newLine();
        os.flush();
        os.write("Number of processor(s) : " + operatingSystemMXBean.getAvailableProcessors());
        os.newLine();
        os.flush();
        os.write("Operating system name : " + operatingSystemMXBean.getName());
        os.newLine();
        os.flush();
        os.write("Operating system version : " + operatingSystemMXBean.getVersion());
        os.newLine();
        os.flush();
        os.write("Time CPU: " + elapsedCpu+" "+elapsedTime);
        os.newLine();
        os.flush();
        os.write(" RAM Space used memory " + used+"KB");  
        os.newLine();
        os.flush();
        os.write("Space total memory " + total+"KB");
        os.newLine();
        os.flush();
        os.write("Space free memory " + free+"KB");
        os.newLine();
        os.flush();
        os.write(" C et DSpace used memory " + (usedspace+usedspace1)/(1024*1024)+"MB");
        os.newLine();
        os.flush();
        os.write("Space total memory " + (totalspace+totalspace1)/(1024*1024)+"MB");
        os.newLine();
        os.flush();
        os.write("Space free memory " + (freespace+freespace1)/(1024*1024)+"MB");
        os.newLine();
        os.flush();
       
           os.close();
           is.close();
           socketOfClient.close();
       } catch (UnknownHostException e) {
           System.err.println("Trying to connect to unknown host: " + e);
       } catch (IOException e) {
           break;
       }
    }
   
   }

}
