/*
 * Copyright 2015 Zoraide Braga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.zoraidebraga.intranet.application;

import br.com.zoraidebraga.intranet.br.com.zoraidebraga.intranet.models.TestModel;
import br.com.zoraidebraga.intranet.produces.EntityManagerProducer;
import br.com.zoraidebraga.intranet.repositories.TestRepository;
import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;

@RunWith(Arquillian.class)
public class TestServiceTest
{
    @Deployment
    public static WebArchive createDeployment()
    {

        final File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml").resolve(
                "org.apache.deltaspike.core:deltaspike-core-api",
                "org.apache.deltaspike.core:deltaspike-core-impl",
                "org.apache.deltaspike.modules:deltaspike-jpa-module-api",
                "org.apache.deltaspike.modules:deltaspike-jpa-module-impl",
                "org.apache.deltaspike.modules:deltaspike-data-module-api",
                "org.apache.deltaspike.modules:deltaspike-data-module-impl")
                .withTransitivity()
                .asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClasses(ApplicationConfiguration.class, TestService.class, TestModel.class, TestRepository.class, EntityManagerProducer.class)
                .addAsLibraries(dependencies)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
    }

    @ArquillianResource
    private URL webappUrl;

    @Test
    public void should_call_test_service() throws Exception
    {

        // given
        final WebClient webClient = WebClient.create(webappUrl.toURI());

        // when
        final Response response = webClient.path("intranet/test/").getResponse();

        // then

    }
}
