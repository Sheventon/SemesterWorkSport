package listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import repositories.*;
import service.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

/*
 * created: 14-10-2020 - 22:52
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        try {
            Class.forName("com.zaxxer.hikari.HikariConfig");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HikariConfig hikari = new HikariConfig();

        hikari.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        hikari.setDriverClassName(properties.getProperty("db.jdbc.driver.classname"));
        hikari.setUsername(properties.getProperty("db.jdbc.username"));
        hikari.setPassword(properties.getProperty("db.jdbc.password"));
        hikari.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));

        HikariDataSource dataSource = new HikariDataSource(hikari);

        SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(simpleJdbcTemplate);
        UserCookiesRepository userCookiesRepository = new UserCookiesRepositoryJdbcImpl(simpleJdbcTemplate);

        UsersService<Long> userService = new UsersServiceImpl(usersRepository);
        CookieService<Long> cookieService = new CookieServiceImpl(userCookiesRepository);

        RecordsRepository recordsRepository = new RecordsRepositoryJdbcImpl(simpleJdbcTemplate, dataSource);
        SectionsRepository sectionsRepository = new SectionsRepositoryJdbcImpl(simpleJdbcTemplate);

        RecordsService<Long> recordsService = new RecordsServiceImpl(recordsRepository, sectionsRepository);

        servletContext.setAttribute("usersService", userService);
        servletContext.setAttribute("cookieService", cookieService);
        servletContext.setAttribute("recordsService", recordsService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
