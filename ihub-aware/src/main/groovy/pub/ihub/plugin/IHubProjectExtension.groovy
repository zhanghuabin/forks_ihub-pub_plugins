/*
 * Copyright (c) 2021 Henry 李恒 (henry.box@outlook.com).
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
package pub.ihub.plugin

import org.gradle.api.Project

/**
 * IHub项目扩展特征
 * @author liheng
 */
abstract class IHubProjectExtension implements IHubExtension {

	protected Project project

	protected String getProjectName() {
		project.name
	}

	protected Project getRootProject() {
		project.rootProject
	}

	boolean isRelease() {
		project.version ==~ /(\d+\.)+\d+/
	}

	protected boolean isRoot() {
		projectName == rootProject.name
	}

	protected File getRootDir() {
		rootProject.projectDir
	}

	@Override
	String findProjectProperty(String key) {
		project.findProperty key
	}

	Map<String, Object> getLocalProperties() {
		new File(rootDir, '.java-local.properties').with {
			exists() ? withInputStream { is ->
				new Properties().tap { load(is) }
			} : [:]
		} as Map
	}

}
