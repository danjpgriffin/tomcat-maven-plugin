package org.apache.tomcat.maven.plugin.tomcat8.run;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.io.File;

import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.webresources.FileResource;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.maven.plugin.logging.Log;

public class MyDirContext extends StandardRoot {
	String buildOutputDirectory;

	String webAppPath;

	WebResourceSet webResourceSet;

	Log log;

	MyDirContext(String buildOutputDirectory, String webAppPath, Log log) {

		this.buildOutputDirectory = buildOutputDirectory;
		this.webAppPath = webAppPath;
		this.log = log;
	}

	@Override
	public WebResource getResource(String path) {

		log.debug("MyDirContext#getResource: " + path);
		if ("/WEB-INF/classes".equals(path)) {
			return new FileResource(this, this.webAppPath, new File(this.buildOutputDirectory), true);
		}

		File file = new File(path);
		if (file.exists()) {
			return new FileResource(this, this.webAppPath, file, true);
		}
		WebResource webResource = super.getResource(path);
		return webResource;
	}

	@Override
	public WebResource getClassLoaderResource(String path) {
		log.debug("MyDirContext#getClassLoaderResource: " + path);
		// here get resources from various paths
		return super.getClassLoaderResource(path);
	}

	@Override
	public WebResource[] listResources(String path) {
		log.debug("MyDirContext#listResources: " + path);
		return super.listResources(path);
	}

	@Override
	public WebResource[] getClassLoaderResources(String path) {
		log.debug("MyDirContext#getClassLoaderResources: " + path);
		return super.getClassLoaderResources(path);
	}

	@Override
	public WebResource[] getResources(String path) {
		log.debug("MyDirContext#getResources: " + path);
		return super.getResources(path);
	}

	@Override
	protected WebResource[] getResourcesInternal(String path, boolean useClassLoaderResources) {
		log.debug("MyDirContext#getResourcesInternal: " + path);
		return super.getResourcesInternal(path, useClassLoaderResources);
	}

}