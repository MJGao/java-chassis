/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicecomb.springboot.starter.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ServerList;

@Configuration
public class CseRibbonClientConfiguration {

    @Autowired
    private CseRoutesProperties cseRoutesProperties;

    public CseRibbonClientConfiguration() {
    }

    @Bean
    public ServerList<?> ribbonServerList(IClientConfig config) {
        String serviceName = config.getClientName();
        String appID = cseRoutesProperties.getAppID();
        String versionRule = cseRoutesProperties.getVersionRule(serviceName);
        return new CseServerListWrapper(appID, serviceName, versionRule, "rest");
    }

}
