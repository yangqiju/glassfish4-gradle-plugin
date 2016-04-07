package com.joyveb.plugin

import org.gradle.api.tasks.TaskAction

class UndeployTask extends GlassfishTask {
	
	@TaskAction
	def exec(){
		super.exec("undeploy")
	}

}
