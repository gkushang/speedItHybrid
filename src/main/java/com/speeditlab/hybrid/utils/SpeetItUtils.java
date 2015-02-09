package com.speeditlab.hybrid.utils;

import java.io.IOException;

import com.speeditlab.hybrid.exception.SpeedItIOException;
import org.springframework.core.io.ClassPathResource;


/**
 * Created by kugajjar on 2/8/15.
 */
public class SpeetItUtils
{

    public static String getAbsolutePath(String fileName)
    {
        try
        {
            return new ClassPathResource(fileName).getFile().getAbsolutePath();
        }
        catch (IOException e)
        {
            throw new SpeedItIOException("File not found: " + fileName, e);
        }
    }
}
