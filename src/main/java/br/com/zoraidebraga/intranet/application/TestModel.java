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

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestModel
{
    private String msg;

    public TestModel()
    {
        super();
    }

    public TestModel(String msg)
    {
        this.msg = msg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        TestModel testModel = (TestModel) o;

        return !(msg != null ? !msg.equals(testModel.msg) : testModel.msg != null);

    }

    @Override
    public int hashCode()
    {
        return msg != null ? msg.hashCode() : 0;
    }
}
