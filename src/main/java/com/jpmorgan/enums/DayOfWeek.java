package com.jpmorgan.enums;

/**
 * @author aayushnagar Enum to find out what day of the week it is.
 */

public enum DayOfWeek {
	
        SUNDAY(1),
        MONDAY(2),
        TUESDAY(3),
        WEDNESDAY(4),
        THURSDAY(5),
        FRIDAY(6),
        SATURDAY(7);

        private final int value;

        DayOfWeek(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

}
