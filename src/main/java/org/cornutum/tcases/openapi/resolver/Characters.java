//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.cornutum.tcases.openapi.resolver;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public interface Characters {
    Characters.Any ANY = new Characters.Any();
    Characters.Ascii ASCII = new Characters.Ascii();
    Characters.CookieValue COOKIE_VALUE = new Characters.CookieValue();
    Characters.Token TOKEN = new Characters.Token();

    String getName();

    boolean allowed(char var1);

    default boolean allowed(String value) {
        String chars = Objects.toString(value, "");
        return IntStream.range(0, chars.length()).allMatch((i) -> {
            return this.allowed(chars.charAt(i));
        });
    }

    default Optional<String> filtered(String value) {
        String chars = Objects.toString(value, "");
        StringBuilder filtered = new StringBuilder();
        IntStream.range(0, chars.length()).mapToObj((i) -> {
            return chars.charAt(i);
        }).filter((c) -> {
            return this.allowed(c);
        }).forEach((c) -> {
            filtered.append(c);
        });
        return value != null && (filtered.length() != 0 || chars.isEmpty()) ? Optional.of(filtered.toString()) : Optional.empty();
    }

    static Characters delimited(final Characters chars, final char delimiter) {
        return new Characters.Base() {
            public String getName() {
                return String.format("%s(%s)", chars.getName(), Character.getName(Character.codePointAt(new char[]{delimiter}, 0)));
            }

            public boolean allowed(char c) {
                return chars.allowed(c) && c != delimiter;
            }
        };
    }

    public static class Token extends Characters.Base {
        private static final String separators_ = "()<>@,;:\\\"/[]?={}";
        private static final String token_ = tokenChars();

        public Token() {
        }

        public String getName() {
            return "TOKEN";
        }

        public boolean allowed(char c) {
            return token_.indexOf(c) >= 0;
        }

        private static String tokenChars() {
            String ascii = Characters.Ascii.chars();
            StringBuilder tokenChars = new StringBuilder();
            IntStream.range(0, ascii.length()).mapToObj((i) -> {
                return ascii.charAt(i);
            }).filter((c) -> {
                return "()<>@,;:\\\"/[]?={}".indexOf(c) < 0;
            }).forEach((c) -> {
                tokenChars.append(c);
            });
            return tokenChars.toString();
        }
    }

    public static class CookieValue extends Characters.Base {
        private static final String excluded_ = " \",;\\";
        private static final String cookieValue_ = cookieValueChars();

        public CookieValue() {
        }

        public String getName() {
            return "COOKIE_VALUE";
        }

        public boolean allowed(char c) {
            return cookieValue_.indexOf(c) >= 0;
        }

        private static String cookieValueChars() {
            String ascii = Characters.Ascii.chars();
            StringBuilder cookieValueChars = new StringBuilder();
            IntStream.range(0, ascii.length()).filter((i) -> {
                return " \",;\\".indexOf(ascii.charAt(i)) < 0;
            }).forEach((i) -> {
                cookieValueChars.append(ascii.charAt(i));
            });
            return cookieValueChars.toString();
        }
    }

    public static class Ascii extends Characters.Base {
        private static final String ascii_ = asciiChars();

        public Ascii() {
        }

        public String getName() {
            return "ASCII";
        }

        public boolean allowed(char c) {
            return ascii_.indexOf(c) >= 0;
        }

        public static String chars() {
            return ascii_;
        }

        private static String asciiChars() {
            StringBuilder asciiChars = new StringBuilder();
            IntStream.range(0, 128).filter(Characters.Any::isPrintable).forEach((codePoint) -> {
                asciiChars.appendCodePoint(codePoint);
            });
            return asciiChars.toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }

    public static class Any extends Characters.Base {
        private static final List<Integer> notVisible_ = Arrays.asList(15, 19, 0);

        public Any() {
        }

        public String getName() {
            return "ANY";
        }

        public boolean allowed(char c) {
            return isPrintable(c);
        }

        public static boolean isPrintable(char c) {
            return c == ' ' || !Character.isSpaceChar(c) && !notVisible_.contains(Character.getType(c));
        }

        public static boolean isPrintable(int codePoint) {
            return Character.toChars(codePoint)[0] == ' ' || !Character.isSpaceChar(codePoint) && !notVisible_.contains(Character.getType(codePoint));
        }
    }

    public abstract static class Base implements Characters {
        public Base() {
        }

        public String toString() {
            return String.format("Characters[%s]", this.getName());
        }
    }
}
