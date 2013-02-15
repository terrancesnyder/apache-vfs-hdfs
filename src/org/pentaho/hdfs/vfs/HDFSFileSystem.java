/*
 * Copyright 2010 Pentaho Corporation.  All rights reserved.
 *                   
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 * @author Michael D'Amour
 */
package org.pentaho.hdfs.vfs;

import java.util.Collection;

import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.AbstractFileSystem;
import org.apache.commons.vfs2.provider.GenericFileName;
import org.apache.hadoop.conf.Configuration;

public class HDFSFileSystem extends AbstractFileSystem implements FileSystem {
	
	private org.apache.hadoop.fs.FileSystem hdfs;

	protected HDFSFileSystem(final FileName rootName, final FileSystemOptions fileSystemOptions) {
		super(rootName, null, fileSystemOptions);
	}
	
	public org.apache.hadoop.fs.FileSystem getHDFSFileSystem() throws FileSystemException {
		if (hdfs == null) {
		      Configuration conf = new Configuration();
		      GenericFileName genericFileName = (GenericFileName) getRootName();
		      String url = "hdfs://" + genericFileName.getHostName() + ":" + genericFileName.getPort();
		      conf.set("fs.default.name", url);

		      String replication = System.getProperty("dfs.replication", "3");
		      conf.set("dfs.replication", replication);

		      if (genericFileName.getUserName() != null && !"".equals(genericFileName.getUserName())) {
		        conf.set("hadoop.job.ugi", genericFileName.getUserName() + ", " + genericFileName.getPassword());
		      }
		      try {
		        hdfs = org.apache.hadoop.fs.FileSystem.get(conf);
		      } catch (Throwable t) {
		        throw new FileSystemException("Could not getHDFSFileSystem() for " + url, t);
		      }
		    }
		    return hdfs;
	  }

	@Override
	protected FileObject createFile(AbstractFileName name) throws Exception {
		return new HDFSFileObject(name, this);
	}

	@Override
	protected void addCapabilities(Collection<Capability> caps) {
		caps.addAll(HDFSFileProvider.capabilities);
	}

}
