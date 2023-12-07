package com.mrx.translator;

import java.util.Base64;

/**
 * 彩云小译
 *
 * @author Mr.X
 * @since 2023-12-07 下午 9:26:38
 */
public class CYTranslator {

    private static final String example = "5Y2t5nJ95YvJ55JZ";

    public static void main(String[] args) {
        String decrypted = Decrypt.decrypt(example);
        System.out.println(decrypted);
    }

    /**
     * 解密工具类<br/>
     * <pre>{@code
     *     function ot(t) {
     *     const n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
     *                 , r = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm"
     *                 , o = s=>n.indexOf(s)
     *                 , u = s=>o(s) > -1 ? r[o(s)] : s;
     *         return t.split("").map(u).join("")
     *     }
     * }</pre>
     */
    private static final class Decrypt {
        private static final String n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        private static final String r = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm";

        public static String decrypt(String enc) {
            StringBuilder sb = new StringBuilder();
            for (char c : enc.toCharArray()) {
                sb.append(u(c));
            }
            byte[] bytes = Base64.getDecoder().decode(sb.toString());
            return new String(bytes);
        }

        private static int o(char s) {
            return n.indexOf(s);
        }

        private static char u(char s) {
            return o(s) > -1 ? r.toCharArray()[o(s)] : s;
        }
    }

}
