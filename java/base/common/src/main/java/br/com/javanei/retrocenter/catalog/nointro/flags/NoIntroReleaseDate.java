package br.com.javanei.retrocenter.catalog.nointro.flags;

public final class NoIntroReleaseDate {
    public static String fromTag(String tag) {
        if (tag.matches("\\d\\d\\d\\d")) {
            return tag;
        }
        if (tag.matches("\\d\\d.\\d\\d.\\d\\d\\d\\d") || tag.matches("\\d\\d-\\d-\\d\\d\\d\\d\\d")) {
            return parseDate(new Integer(tag.substring(6)), new Integer(tag.substring(3, 5)), new Integer(tag.substring(0, 2)));
        }
        if (tag.matches("\\d.\\d.\\d\\d\\d\\d") || tag.matches("\\d-\\d-\\d\\d\\d\\d")) {
            return parseDate(new Integer(tag.substring(4)), new Integer(tag.substring(2, 3)), new Integer(tag.substring(0, 1)));
        }
        if (tag.matches("\\d\\d.\\d.\\d\\d\\d\\d")) {
            return parseDate(new Integer(tag.substring(5)), new Integer(tag.substring(3, 4)), new Integer(tag.substring(0, 2)));
        }
        if (tag.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
            return tag;
        }
        if (tag.matches("\\d\\d\\d\\d-\\d\\d-\\w\\w")) {
            return tag.substring(0, 7);
        }
        if (tag.matches("\\d\\d\\d\\d-\\d-\\d\\d")) {
            return parseDate(new Integer(tag.substring(0, 4)), new Integer(tag.substring(5, 6)), new Integer(tag.substring(7)));
        }
        if (tag.matches("\\d\\d\\d\\d-\\w\\w-\\w\\w")
                || tag.matches("\\d\\d\\d\\d-\\w\\w-\\w\\w.+")) {
            throw new RuntimeException("@@@@@ Unknown date tag: " + tag);
            /*
            int year = Integer.parseInt(tag.substring(0, 4));
            game.setYear(year);
            return true;
            */
        }
        if (tag.matches("\\d\\d.\\d\\d.\\d\\d-\\d\\d\\d\\d")) {
            throw new RuntimeException("@@@@@ Unknown date tag: " + tag);
            /*
            int year = Integer.parseInt(tag.substring(6));
            game.setYear(year);
            return true;
            */
        }

        if (tag.matches("\\d\\d-\\d\\d-\\d\\d")) {
            return parseDate(Integer.parseInt(tag.substring(0, 2)), Integer.parseInt(tag.substring(2)), null);
        }
        if (tag.matches("\\d.\\d.\\d\\d")) {
            return parseDate(Integer.parseInt(tag.substring(4)), Integer.parseInt(tag.substring(2, 3)), Integer.parseInt(tag.substring(0, 1)));
        }
        if (tag.matches("\\w\\w\\w \\d\\d")) {
            int year = Integer.parseInt(tag.substring(4));
            int month = 0;
            switch (tag.substring(0, 3).toLowerCase()) {
                case "jan":
                    month = 1;
                    break;
                case "fev":
                    month = 2;
                    break;
                case "mar":
                    month = 3;
                    break;
                case "abr":
                    month = 4;
                    break;
                case "may":
                    month = 5;
                    break;
                case "jun":
                    month = 6;
                    break;
                case "jul":
                    month = 7;
                    break;
                case "aug":
                    month = 8;
                    break;
                case "sep":
                    month = 9;
                    break;
                case "oct":
                    month = 10;
                    break;
                case "nov":
                    month = 11;
                    break;
                case "dec":
                    month = 12;
                    break;
                default:
                    throw new RuntimeException("@@@@@ Unknown date tag: " + tag);
            }
            return parseDate(year, month, null);
        }
        if (tag.matches("\\d.\\d\\d.\\d\\d")) {
            int year = Integer.parseInt(tag.substring(5));
            return parseDate(year, Integer.parseInt(tag.substring(2, 4)), Integer.parseInt(tag.substring(0, 1)));
        }
        if (tag.matches("\\d\\d.\\d.\\d\\d")) {
            int year = Integer.parseInt(tag.substring(5));
            return parseDate(year, Integer.parseInt(tag.substring(3, 4)), Integer.parseInt(tag.substring(0, 2)));
        }
        if (tag.matches("January \\d\\d\\d\\d")) {
            return tag.substring(8) + "-01";
        }
        if (tag.matches("February \\d\\d\\d\\d")) {
            return tag.substring(9) + "-02";
        }
        if (tag.matches("March \\d\\d\\d\\d")) {
            return tag.substring(6) + "-03";
        }
        if (tag.matches("April \\d\\d\\d\\d")) {
            return tag.substring(6) + "-04";
        }
        if (tag.matches("May \\d\\d\\d\\d")) {
            return tag.substring(4) + "-05";
        }
        if (tag.matches("June \\d\\d\\d\\d")) {
            return tag.substring(5) + "-06";
        }
        if (tag.matches("July \\d\\d\\d\\d")) {
            return tag.substring(5) + "-07";
        }
        if (tag.matches("August \\d\\d\\d\\d")) {
            return tag.substring(7) + "-08";
        }
        if (tag.matches("September \\d\\d\\d\\d")) {
            return tag.substring(10) + "-09";
        }
        if (tag.matches("October \\d\\d\\d\\d")) {
            return tag.substring(8) + "-10";
        }
        if (tag.matches("November \\d\\d\\d\\d")) {
            return tag.substring(9) + "-11";
        }
        if (tag.matches("December \\d\\d\\d\\d")) {
            return tag.substring(9) + "-12";
        }
        return null;
    }

    private static String parseDate(Integer year, Integer month, Integer day) {
        StringBuilder sb = new StringBuilder();
        if (year < 1000) {
            if (year > 20) {
                year += 1900;
            } else {
                year += 2000;
            }
        }
        if (month <= 12 && day <= 12) {
            //TODO: "Impossible to differentiate month and day"));
        }
        sb.append(year);
        if (month != null && month > 0) {
            if (day != null && day > 0) {
                if (month > 13) {
                    // Parece que está invertido o mes com o dia.... Então inverte os valores
                    Integer tmp = month;
                    month = day;
                    day = tmp;
                }
                sb.append("-");
                if (month < 10) sb.append("0");
                sb.append(month).append("-");
                if (day < 10) sb.append("0");
                sb.append(day);
            } else {
                sb.append("-");
                if (month < 10) sb.append("0");
                sb.append(month);
            }
        }
        return sb.toString();
    }
}
