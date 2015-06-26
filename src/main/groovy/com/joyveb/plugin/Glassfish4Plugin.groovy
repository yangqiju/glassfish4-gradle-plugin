package com.joyveb.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class Glassfish4Plugin implements Plugin<Project>{

	void apply(Project project) {
		project.task("deploy2glassfish",type:DeployTask).setDescription("glassfish4 deploy task")
	}
}
