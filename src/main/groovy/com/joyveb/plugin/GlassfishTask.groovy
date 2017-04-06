package com.joyveb.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

class GlassfishTask extends DefaultTask {
	
	def exec(cmd){
		def deployfile =  project.file(project.war.archivePath)
		if(!deployfile.exists()) {
			println "${deployfile.absolutePath} is not exists.."
			return
		}else{
			println "package absolute path ${deployfile.absolutePath}.."
		}
		def allcmd='/usr/bin/oadmin '
		if("deploy".equals(cmd)){
			allcmd = allcmd+ " deploy --property implicitCdiEnabled=false  --name ${project.name} --contextroot ${project.name} ${deployfile.absolutePath}"
		}else if("undeploy".equals(cmd)){
			allcmd = allcmd+ " undeploy  ${project.name} "
		}
		println "commond ::$allcmd"
		def proc = allcmd.execute()
		println cmd+" ${project.name} success.."
		allcmd = "/usr/bin/oadmin  list-applications"
		allcmd.execute();
	}

}
