package com.pluralsight;

import java.time.LocalDateTime;

public class Years {
    public class years{
        private LocalDateTime thisMonth;
        private LocalDateTime lastMonth;
        private LocalDateTime thisYear;
        private LocalDateTime lastYear;

        public void setThisMonth(LocalDateTime thisMonth) {
            this.thisMonth = thisMonth;
        }

        public LocalDateTime getLastMonth() {
            return lastMonth;
        }

        public void setLastMonth(LocalDateTime lastMonth) {
            this.lastMonth = lastMonth;
        }

        public LocalDateTime getThisYear() {
            return thisYear;
        }

        public void setThisYear(LocalDateTime thisYear) {
            this.thisYear = thisYear;
        }

        public LocalDateTime getLastYear() {
            return lastYear;
        }

        public void setLastYear(LocalDateTime lastYear) {
            this.lastYear = lastYear;
        }
    }public years(LocalDateTime thisMonth, LocalDateTime lastMonth, LocalDateTime thisYear, LocalDateTime lastYear){
        this.thisMonth = thisMonth;
        this.lastMonth = lastMonth;
        this.thisYear = thisYear;
        this.lastYear = lastYear;
    }
}
