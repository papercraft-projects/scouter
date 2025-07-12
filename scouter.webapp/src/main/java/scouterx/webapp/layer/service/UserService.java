/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package scouterx.webapp.layer.service;

import lombok.extern.slf4j.Slf4j;
import scouterx.webapp.framework.client.model.AgentModelThread;
import scouterx.webapp.framework.client.net.LoginMgr;
import scouterx.webapp.framework.client.net.LoginRequest;
import scouterx.webapp.framework.client.server.Server;
import scouterx.webapp.framework.client.server.ServerManager;
import scouterx.webapp.framework.configure.ConfigureManager;
import scouterx.webapp.layer.consumer.AccountConsumer;
import scouterx.webapp.framework.exception.ErrorState;
import scouterx.webapp.model.scouter.SUser;
import scouterx.webapp.request.LoginRequestByServer;
import scouterx.webapp.view.CommonResultView;
import scouterx.webapp.view.LoginServerView;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 8. 27.
 */
@Slf4j
public class UserService {
    private final AccountConsumer accountConsumer;

    public UserService() {
        this.accountConsumer = new AccountConsumer();
    }

    public void login(final Server server, final SUser user) {
        boolean result = accountConsumer.login(server, user);
        if (!result) {
            throw ErrorState.LOGIN_FAIL.newBizException();
        }
    }
    public CommonResultView<LoginServerView> session(LoginRequestByServer loginRequest){
        //-
        Server server = new Server(loginRequest.getServer(), String.valueOf(loginRequest.getPort()));
        server.setUserId(loginRequest.getUser().getId());
        server.setPassword(loginRequest.getUser().getPassword());
        ServerManager srvMgr = ServerManager.getInstance();
        if (Objects.isNull(srvMgr.getServer(server.getId()))) {
            srvMgr.addServer(server);
        }
        LoginRequest result = LoginMgr.login(server);

        if(result.success){
            AgentModelThread.getInstance().fetchObjectList();
            if( server.getGroup().equals("admin") ) {
                ConfigureManager.getConfigure().setServerConfig(server);
            }
            log.info("Successfully log in to {}:{}", server.getIp(), server.getPort());
            return CommonResultView.success(LoginServerView.builder()
                    .ip(loginRequest.getServer())
                    .port(loginRequest.getPort())
                    .id(server.getId())
                    .name(server.getName())
                    .email(server.getEmail())
                    .group(server.getGroup())
                    .version(server.getVersion())
                    .name(server.getName())
                    .connected(true)
                    .timezone(server.getTimezone())
                    .serverTime(server.getCurrentTime())
                    .policy(server.getGroupPolicy())
                    .build());

        }else{
            srvMgr.removeServer(server.getId());
            log.error("Fail to log in to {}:{}", server.getIp(), server.getPort());
        }
        return new CommonResultView(result.code,"FAIL",LoginServerView.builder().build());
    }
}
