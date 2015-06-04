/*
 * Copyright 2015 Zoraide Braga. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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

import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class TestServiceTest
{
    @Deployment
    public static WebArchive createDeployment()
    {

        final File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml").resolve(
                "org.apache.deltaspike.core:deltaspike-core-api",
                "org.apache.deltaspike.core:deltaspike-core-impl")
                .withTransitivity()
                .asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClasses(ApplicationConfiguration.class, TestService.class)
                .addAsLibraries(dependencies);
    }

    @ArquillianResource
    private URL webappUrl;

    @Test
    public void should_call_test_service() throws Exception
    {

        // given
        final WebClient webClient = WebClient.create(webappUrl.toURI());

        // when
        final TestModel testModel = webClient.path("intranet/test/")
                .accept(APPLICATION_JSON)
                .get(TestModel.class);

        // then
        assertNotNull(testModel);
        assertEquals("Hello World", testModel.getMsg());
    }

}
