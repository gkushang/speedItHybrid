/*-----------------------------------------------------------------------------------------*\
 |  Copyright (c) 2015 Kushang Gajjar <g.kushang@gmail.com>                                 |
 |                                                                                          |
 |  Proprietor : Kushang G Gajjar                                                           |
 |                                                                                          |
 |  All Rights Reserved.             |                                                      |
 |  - Permission is hereby not granted without signing agreement with the Proprietor.       |
 |  - This Software cannot be distributed without signing agreement with the Proprietor.    |
 \*---------------------------------------------------------------------------------------- */

package com.speeditlab.locators.main;

import java.io.IOException;

import com.speeditlab.hybrid.exception.ViewNotFound;
import com.speeditlab.locators.scan.Scanner;


/**
 * Created by kugajjar on 2/6/15.
 */
public class GenerateLocators
{
    public static void main(String[] args) throws IOException, ViewNotFound
    {
        new Scanner().run();
    }

}
