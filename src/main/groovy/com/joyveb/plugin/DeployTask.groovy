package com.joyveb.plugin

import org.gradle.api.tasks.TaskAction

class DeployTask extends GlassfishTask {
	
	@TaskAction
	def exec(){
		super.exec("deploy")
	}

}
