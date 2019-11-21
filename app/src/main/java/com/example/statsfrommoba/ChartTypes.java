package com.example.statsfrommoba;

public enum ChartTypes {
    COLUMNCHART {
        @Override
        public String toString() {
            return "ColumnChart";
        }
    },
    AREACHART {
        @Override
        public String toString() {
            return "AreaChart";
        }
    },
    STEPPEDAREACHART {
        @Override
        public String toString() {
            return "SteppedAreaChart";
        }
    }
}
