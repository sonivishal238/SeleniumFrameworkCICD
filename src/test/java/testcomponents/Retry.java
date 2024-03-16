package testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	// After completing all of the listeners, the test comes to the retry method by default.
	@Override
	public boolean retry(ITestResult result) {
		
		if(count < maxTry) {
			
			// return true means the test should be re-ran
			count++;
			return true;
		}
		return false;
	}

}
