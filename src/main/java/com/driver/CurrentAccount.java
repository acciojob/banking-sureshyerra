package com.driver;

public class CurrentAccount extends BankAccount {
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        if (balance < 5000) {
            throw new Exception("Insufficient Balance");
        }

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if (!isNumberValid(tradeLicenseId)) {
            String rearrangeId = arrangeString(tradeLicenseId);
            if (rearrangeId == "") {
                throw new Exception("Valid License can not be generated");
            } else {
                this.tradeLicenseId = rearrangeId;
            }
        }
    }


        public char getCountChar ( int[] count){
            int max = 0;
            char ch = 0;
            for (int i = 0; i < 26; i++) {
                if (count[i] > max) {
                    max = count[i];
                    ch = (char) ((int) 'A' + i);
                }
            }
            return ch;
        }
        public String arrangeString (String S){
            int n = S.length();
            int[] count = new int[26];
            for (char ch : S.toCharArray()) {
                count[(int) ch - (int) 'A']++;
            }
            char ch_max = getCountChar(count);
            int maxCount = count[(int) ch_max - (int) 'A'];
            if (n % 2 == 0) {
                if (maxCount > (n / 2) + 1) {
                    return "";
                }
            } else {
                if (maxCount > (n / 2) + 2) {
                    return "";

                }
            }
            char[] ans = new char[n];
            int idx = 0;
            for (idx = 0; idx < n; idx = idx + 2) {
                if (count[ch_max] > 0) {
                    ans[idx] = ch_max;
                    count[ch_max]--;
                } else {
                    break;
                }
            }
            for (int i = 0; i < 26; i++) {
                char ch = (char) ('A' + i);
                while (count[ch] > 0) {
                    if (idx >= n) {
                        idx = 1;
                    }
                    ans[idx] = ch;
                    idx = idx + 2;
                    count[ch]--;
                }
            }
            String res = "";
            for (int i = 0; i < n; i++) {
                res += ans[i];
            }
            return res;
        }



        public boolean isNumberValid (String licenceId){
            for (int i = 0; i < licenceId.length() - 1; i++) {
                if (licenceId.charAt(i) == licenceId.charAt(i + 1)) {
                    return false;
                }
            }
            return true;
        }

    }

