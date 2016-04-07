package com.joyveb.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class GlassfishTask extends DefaultTask {
	
	def exec(cmd){
		def deployfile =  project.file(project.war.archivePath)
		if(!deployfile.exists()) {
			println "${deployfile.absolutePath} is not exists.."
			return
		}
		def allcmd
		if(System.getenv('glassfishpwd')!=null){
			allcmd=System.getenv('GLASSFISH_HOME')+'/bin/asadmin --user admin -W '+System.getenv('glassfishpwd')
		}else{
			allcmd=System.getenv('GLASSFISH_HOME')+'/bin/asadmin'
		}
		if("deploy".equals(cmd)){
			allcmd = allcmd+ " deploy --name ${project.name} --contextroot ${project.name} ${deployfile.absolutePath}"
		}else if("undeploy".equals(cmd)){
			allcmd = allcmd+ " undeploy --name ${project.name} --contextroot ${project.name} "
		}
		def proc = allcmd.execute()
		proc.in.each { println it }
		proc.err.each {println '[ERROR]'+it }
		println cmd+" ${project.name} success.."
	}

}
