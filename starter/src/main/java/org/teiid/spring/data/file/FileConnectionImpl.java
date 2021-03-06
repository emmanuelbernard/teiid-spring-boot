/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.teiid.spring.data.file;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import javax.resource.ResourceException;

import org.teiid.resource.spi.BasicConnection;
import org.teiid.translator.FileConnection;


public class FileConnectionImpl extends BasicConnection implements FileConnection {
	    
	private File parentDirectory;
	private Map<String, String> fileMapping;
	private boolean allowParentPaths;
	private static final Pattern parentRef = Pattern.compile("(^\\.\\.(\\\\{2}|/)?.*)|((\\\\{2}|/)\\.\\.)"); //$NON-NLS-1$
	
	public FileConnectionImpl(String parentDirectory, Map<String, String> fileMapping, boolean allowParentPaths) {
		this.parentDirectory = new File(parentDirectory);
		if (fileMapping == null) {
			fileMapping = Collections.emptyMap();
		}
		this.fileMapping = fileMapping;
		this.allowParentPaths = allowParentPaths;
	}
	
	@Override
	public File getFile(String path) throws ResourceException {
    	if (path == null) {
    		return this.parentDirectory;
        }
		String altPath = fileMapping.get(path);
		if (altPath != null) {
			path = altPath;
		}
    	if (!allowParentPaths && parentRef.matcher(path).matches()) {	
			throw new ResourceException("Parent path .. not allowed in file path " + path); //$NON-NLS-1$
		}
    	
    	if (new File(path).isAbsolute()) {
    		return new File(path);
    	}
		return new File(parentDirectory, path);	
    }

	@Override
	public void close() throws ResourceException {
		
	}
}
