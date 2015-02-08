package com.speeditlab.hybrid.enums;

/**
 * Created by kugajjar on 2/6/15.
 */
public interface Keys
{
    public interface TestCase
    {
        String START_TEST = "START TEST";
        String END_TEST = "END TEST";


        public interface Rows
        {
            Integer START_ROW = 8;
        }


        public interface Columns
        {
            Integer KEYWORD = 0;
            Integer FIELD_NAME = 1;
            Integer VALUE = 2;
        }
    }
}
