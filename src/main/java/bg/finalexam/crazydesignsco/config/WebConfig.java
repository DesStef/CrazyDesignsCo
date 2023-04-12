package bg.finalexam.crazydesignsco.config;

import bg.finalexam.crazydesignsco.service.impl.MaintenanceInterceptorImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    private final MaintenanceInterceptorImpl maintenanceInterceptorImpl;

    public WebConfig(LocaleChangeInterceptor localeChangeInterceptor, MaintenanceInterceptorImpl maintenanceInterceptorImpl) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.maintenanceInterceptorImpl = maintenanceInterceptorImpl;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(maintenanceInterceptorImpl);
    }
}
