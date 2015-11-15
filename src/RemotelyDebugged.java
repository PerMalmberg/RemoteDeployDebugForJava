/**
 * Created by Per Malmberg on 2015-11-14.
 */
public class RemotelyDebugged {
	public static void main( String[] args ) {

		try {
			int i = 0;
			while( true ) {
				System.out.print(".");
				Thread.sleep(100);
				++i;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
