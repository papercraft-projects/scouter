package scouterx.webapp.framework.mapper;
/*
 *  Copyright 2015 the original author or authors. 
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Created by Gun Lee(gunlee01@gmail.com) on 2017. 10. 27.
 */
@Provider
public class JerseyMapperProvider implements ContextResolver<ObjectMapper> {
	private static ObjectMapper apiMapper;
	static {
		apiMapper = new ObjectMapper().configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, false);
	}
	@Override
	public ObjectMapper getContext(Class<?> type)
	{
		return apiMapper;
	}
}
