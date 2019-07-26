
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CommandUtils {
	/**
	 *  
	 * @author haowen
	 * @time 2018�?3�?15日上�?11:43:37
	 * @Description  
	 * @param args 0 OutputMsg 1 failMsg
	 * @return
	 */
	public static String[]  command(String[] args) {
		Process p=null;
		BufferedReader reader2=null;
		BufferedReader reader=null;
		StringBuilder rs=new StringBuilder();
		StringBuilder error=new StringBuilder();

		try {
			p = Runtime.getRuntime().exec(args);
			
			
			reader = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			String line=null;
			while ((line = reader.readLine()) != null) {
				rs.append(line);
			}
			reader2 = new BufferedReader(
					new InputStreamReader(p.getErrorStream()));
			line=null;
			while ((line = reader.readLine()) != null) {
				error.append(line);
			}
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	
		}
		return new String[]{rs.toString(),error.toString()};
	}
	
	
	
	/**
	 *  media to wav,it will block current thread;
	 * @author haowen
	 * @time 2018�?3�?15日上�?11:44:43
	 * @Description 
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean MediaToWav(String from ,String to){
		String[] command = command(new String[]{"ffmpeg","-i",from,"-y",to });
		System.out.println(Arrays.toString(command));
		return new File(to).exists();
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		MediaToWav(args[0], args[1]);
	}
}
