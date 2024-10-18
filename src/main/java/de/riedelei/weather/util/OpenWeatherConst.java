package de.riedelei.weather.util;

public class OpenWeatherConst {

    public static final String APIKEY2 = "c5b4da3f4057c95b48ffd41706fe58ea";

    public static final String APIKEY = "d1d99ad31ee399d08809c5ab47684d07";

    public enum UNIT {
        METRIC ,
        STANDARD,
        IMPERAL
    }

    public enum LANGUAGE {
        DE,
        EN
    }

    public enum LIMIT {

        EINS(1),
        ZWEI(2),
        DREI(3),
        VIER(4),
        FUNF(5);

        private int value;

        private LIMIT(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}


