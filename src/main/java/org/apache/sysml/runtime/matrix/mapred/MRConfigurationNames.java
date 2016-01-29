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

package org.apache.sysml.runtime.matrix.mapred;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.util.VersionInfo;

/**
 * This class provides a central local for used hadoop configuration properties. For portability, we support both hadoop
 * 1.x and 2.x and automatically map to the currently used cluster.
 * 
 */
public abstract class MRConfigurationNames {

	protected static final Log LOG = LogFactory.getLog(MRConfigurationNames.class.getName());

	public static final String DFS_BLOCKSIZE;
	public static final String DFS_METRICS_SESSION_ID;
	public static final String DFS_PERMISSIONS_ENABLED;
	public static final String MR_CLUSTER_LOCAL_DIR;
	public static final String MR_JOBTRACKER_ADDRESS;
	public static final String MR_JOBTRACKER_SYSTEM_DIR;
	public static final String MR_TASK_IO_SORT_MB;
	public static final String MR_TASKTRACKER_TASKCONTROLLER;

	// initialize to currently used cluster
	static {
		// determine hadoop version
		String version = VersionInfo.getBuildVersion();
		boolean hadoopVersion2 = version.startsWith("2");
		LOG.debug("Hadoop build version: " + version);

		if (hadoopVersion2) {
			LOG.debug("Using hadoop 2.x configuration properties.");
			DFS_BLOCKSIZE = "dfs.blocksize";
			DFS_METRICS_SESSION_ID = "dfs.metrics.session-id";
			DFS_PERMISSIONS_ENABLED = "dfs.permissions.enabled";
			MR_CLUSTER_LOCAL_DIR = "mapreduce.cluster.local.dir";
			MR_JOBTRACKER_ADDRESS = "mapreduce.jobtracker.address";
			MR_JOBTRACKER_SYSTEM_DIR = "mapreduce.jobtracker.system.dir";
			MR_TASK_IO_SORT_MB = "mapreduce.task.io.sort.mb";
			MR_TASKTRACKER_TASKCONTROLLER = "mapreduce.tasktracker.taskcontroller";
		} else { // any older version
			LOG.debug("Using hadoop 1.x configuration properties.");
			DFS_BLOCKSIZE = "dfs.block.size";
			DFS_METRICS_SESSION_ID = "session.id";
			DFS_PERMISSIONS_ENABLED = "dfs.permissions";
			MR_CLUSTER_LOCAL_DIR = "mapred.local.dir";
			MR_JOBTRACKER_ADDRESS = "mapred.job.tracker";
			MR_JOBTRACKER_SYSTEM_DIR = "mapred.system.dir";
			MR_TASK_IO_SORT_MB = "io.sort.mb";
			MR_TASKTRACKER_TASKCONTROLLER = "mapred.task.tracker.task-controller";
		}
	}
}