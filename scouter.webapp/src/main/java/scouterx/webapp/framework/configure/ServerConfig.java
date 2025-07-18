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

package scouterx.webapp.framework.configure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 8. 26.
 */
@Getter
@Setter
@AllArgsConstructor
public class ServerConfig {
    String ip;
    String port;
    String id;
    String password;

    public String getProperty(){
        return String.join(":",ip,port,id,password);
    }
    public boolean toEqual(String ip, String port){
        return ip.equals(this.ip) && port.equals(this.port);

    }
}
