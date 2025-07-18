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

package scouterx.webapp.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 8. 28.
 */
@Getter
@Setter
public class ServerViewDetail extends ServerView{
    String ip;
    String timezone;
    @Builder
    public ServerViewDetail(int id, String name,
                            boolean connected,
                            long serverTime,
                            String version,
                            String ip,
                            String timezone
                           ){
        super(id,name,connected,serverTime,version);
        this.ip = ip;
        this.timezone = timezone;
    }

}
