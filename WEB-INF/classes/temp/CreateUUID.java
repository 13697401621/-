package temp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class CreateUUID {
	
	@Test
	public void fun() {
		String uuid = UUID.randomUUID().toString();
		String s = uuid.replaceAll("-", "");
		System.out.println(s.toUpperCase());
	}
	
	@Test
	public void getNowTime() {
		Date newTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeString = sdf.format(newTime);
		System.out.println(timeString);
	}
}
