package be.devriendt.advent.s2020;

import java.util.List;

public class Day4PassportProcessing {

    private static class Document {
        String byr;
        String iyr;
        String eyr;
        String hgt;
        String hcl;
        String ecl;
        String pid;
        String cid;

        public boolean isValidWithoutDataValidation() {
            return isNonEmpty(byr) && isNonEmpty(iyr) && isNonEmpty(eyr) && isNonEmpty(hgt)
                    && isNonEmpty(hcl) && isNonEmpty(ecl) && isNonEmpty(pid);
        }

        public boolean isValidWithDataValidation() {
            if (isValidWithoutDataValidation()) {
                return validYear(byr, 1920, 2002)
                        && validYear(iyr, 2010, 2020)
                        && validYear(eyr, 2020, 2030)
                        && validHgt()
                        && validHcl()
                        && validEcl()
                        && validPid();
            }
            return false;
        }

        private boolean validYear(String field, int min, int max) {
            int year = Integer.parseInt(field);
            return year >= min && year <= max;
        }

        private boolean validHgt() {
            int height = Integer.parseInt(hgt.replaceAll("[\\D]", ""));
            if (hgt.endsWith("cm")) {
                return height >= 150 && height <= 193;
            } else if (hgt.endsWith("in")) {
                return height >= 59 && height <= 76;
            }
            return false;
        }

        private boolean validHcl() {
            return hcl.matches("#[0-9a-f]{6}");
        }

        private boolean validEcl() {
            return ecl.matches("(amb|blu|brn|gry|grn|hzl|oth)");
        }

        private boolean validPid() {
            return pid.matches("\\d{9}");
        }
    }

    public static int countNumberOfValidPassports(List<String> input) {
        return countNumberOfValidPassports(input, false);
    }

    public static int countNumberOfValidPassportsWithDataValidation(List<String> input) {
        return countNumberOfValidPassports(input, true);
    }

    private static int countNumberOfValidPassports(List<String> input, boolean dataValidation) {
        int validPassports = 0;
        Document curDoc = new Document();

        for (String line: input) {
            String[] vars = line.split(" ");
            if (vars.length > 0 && vars[0].length() > 0) {
                for(int i = 0; i < vars.length; i++) {
                    String[] parts = vars[i].split(":");

                    if (parts[0].equals("byr")) {
                        curDoc.byr = parts[1];
                    } else if (parts[0].equals("iyr"))  {
                        curDoc.iyr = parts[1];
                    } else if (parts[0].equals("eyr")) {
                        curDoc.eyr = parts[1];
                    } else if (parts[0].equals("hgt")) {
                        curDoc.hgt = parts[1];
                    } else if (parts[0].equals("hcl")) {
                        curDoc.hcl = parts[1];
                    } else if (parts[0].equals("ecl")) {
                        curDoc.ecl = parts[1];
                    } else if (parts[0].equals("pid")) {
                        curDoc.pid = parts[1];
                    } else if (parts[0].equals("cid")) {
                        curDoc.cid = parts[1];
                    }
                }
            } else {
                if (isValid(curDoc, dataValidation)) {
                    validPassports++;
                }
                curDoc = new Document();
            }
        }

        if (isValid(curDoc, dataValidation)) {
            validPassports++;
        }

        return validPassports;
    }

    private static boolean isValid(Document doc, boolean dataValidation) {
        if (!dataValidation) {
            return doc.isValidWithoutDataValidation();
        }
        return doc.isValidWithDataValidation();
    }

    private static boolean isNonEmpty(String str) {
        return str != null && !str.isEmpty();
    }

}
