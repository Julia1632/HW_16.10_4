package com.example;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by User on 24.10.2017.
 */

public class CalculatorMethods {
    private static final String CALC = "calc?input=";

        private String mBasePath;

        public CalculatorMethods(final String pBasePath) {
            if (pBasePath.charAt(pBasePath.length() - 1) == '/') {
                mBasePath = pBasePath;
            } else {
                mBasePath = pBasePath + "/";
            }
        }

        public String calculateSum(final int a, final int b) {
            return evaluate(a + "+" + b);
        }

        public String calculateMult(final int a, final int b){
            return evaluate(a + "*" + b);
        }

        public String evaluate(String input) {
            try {
                return mBasePath + CALC + URLEncoder.encode(input, "UTF-8");
            } catch (final UnsupportedEncodingException pE) {
                throw new IllegalStateException(pE);
            }
        }

}

