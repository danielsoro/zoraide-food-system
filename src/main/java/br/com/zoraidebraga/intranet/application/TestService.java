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
import br.com.zoraidebraga.intranet.repositories.TestRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("test")
public class TestService
{

    @Inject
    private TestRepository testRepository;

    @GET
    @Produces({ APPLICATION_JSON })
    public List<TestModel> listModels()
    {
        return testRepository.findAll();
    }
}