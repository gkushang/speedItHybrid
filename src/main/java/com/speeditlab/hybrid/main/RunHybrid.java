package com.speeditlab.hybrid.main;

import java.io.IOException;

import com.speeditlab.hybrid.driver.TcDriver;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by kugajjar on 2/6/15.
 */
public class RunHybrid
{
    public static void main(String[] args) throws IOException
    {
        new TcDriver().execute(new ClassPathResource("testcases/Login.xlsm").getFile().getAbsolutePath(), "LOGIN");
    }

}
