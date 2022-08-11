package main.java.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int count = 0;
    int retryCount = 3; // Especificamos cuantos reintentos queremos implementar

    //Maneja los reintentos en ciclo
    @Override
    public boolean retry(ITestResult iTestResult) {
        while (count < retryCount){
            count++;
            return true;
        }
        return false;
    }
}
