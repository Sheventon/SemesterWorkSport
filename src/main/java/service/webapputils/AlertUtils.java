package service.webapputils;

import java.io.PrintWriter;

/**
 * created: 17-10-2020 - 2:22
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class AlertUtils {
    public static void show(PrintWriter pw, String value, String location) {
        pw.println("<script type=\"text/javascript\">");
        pw.println("alert('" + value + "');");
        pw.println("location='" + location + "';");
        pw.println("</script>");
    }
}
