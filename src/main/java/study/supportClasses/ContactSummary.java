package study.supportClasses;

import java.io.Serializable;


    public class ContactSummary implements Serializable {

        private String firstNameSummary;
        private String lastNameSummary;
        private String homeTelNumber;

        public ContactSummary(String firstNameSummary, String lastNameSummary, String homeTelNumber) {
            this.firstNameSummary = firstNameSummary;
            this.lastNameSummary = lastNameSummary;
            this.homeTelNumber = homeTelNumber;
        }

        @Override
        public String toString() {
            return "ContactSummary{" +
                    "firstNameSummary='" + firstNameSummary + '\'' +
                    ", lastNameSummary='" + lastNameSummary + '\'' +
                    ", homeTelNumber='" + homeTelNumber + '\'' +
                    '}';
        }
    }


