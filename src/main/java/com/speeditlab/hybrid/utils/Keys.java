package com.speeditlab.hybrid.utils;

/**
 * Created by kugajjar on 2/6/15.
 */
public interface Keys
{
    String BROWSER = "browser";


    public interface TestCase
    {
        String START_TEST = "START TEST";
        String END_TEST = "END TEST";


        public interface Rows
        {
            Integer START_ROW = 7;
        }


        public interface Columns
        {
            Integer KEYWORD = 0;
            Integer FIELD_NAME = 1;
            Integer VALUE = 2;
        }
    }
}
