/*
 * kindle electronic book push service
 *
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanli.kindle.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author wanli
 * @date 2018-12-12 16:56
 */
@EnableSwagger2
@Configuration
@ConditionalOnProperty("kindle.swagger.enable")
public class SwaggerConfig {

    private SecurityScheme apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    public Docket allDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Api Document")
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kindle Service Rest API Online")
                .description("Kindle electronic book push service api document")
                .contact(new Contact("Linus Taylor", "http://wanlinus.github.io", "wanlinus@qq.com"))
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }


}
