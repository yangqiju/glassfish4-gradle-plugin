package com.joyveb.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class DeployTask extends DefaultTask {
	
	@TaskAction
	def exec(){
		def deployfile =  project.file(project.war.archivePath)
		if(!deployfile.exists()) {
			println "${deployfile.absolutePath} is not exists.."
			return
		}
		def cmd
		if(System.getenv('glassfishpwd')!=null){
			cmd=System.getenv('GLASSFISH_HOME')+'/bin/asadmin --user admin -W '+System.getenv('glassfishpwd')
		}else{
			cmd=System.getenv('GLASSFISH_HOME')+'/bin/asadmin'
		}
		
		cmd = cmd+ " deploy --name ${project.name} --contextroot ${project.name} ${deployfile.absolutePath}"
		def proc = cmd.execute()
		proc.in.each { println it }
		proc.err.each {println '[ERROR]'+it }
		println "deploy ${project.name} success.."
	}

}
